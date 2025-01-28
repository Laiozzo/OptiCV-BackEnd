package com.generation.opticvbeckend.controllers;

import com.generation.opticvbeckend.model.dto.ResetPasswordDto;
import com.generation.opticvbeckend.model.entities.MailRecuperoPassword;
import com.generation.opticvbeckend.model.entities.User;
import com.generation.opticvbeckend.model.repositories.MailRecuperoRepository;
import com.generation.opticvbeckend.model.repositories.UserRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/recover")
public class RecoveryMailController
{
	@Autowired
	private UserRepository uRepo;
	@Autowired
	private MailRecuperoRepository mRepo;
	@Autowired
	private JavaMailSender emailSender;

	@GetMapping("/{email}")
	public ResponseEntity<?> sendRecoverMail(@PathVariable String email) throws MessagingException
	{
		Optional<User> user= uRepo.findByEmail(email);
		if(user.isEmpty())

			return ResponseEntity.notFound().build();

		MailRecuperoPassword mailRecuperoPassword = new MailRecuperoPassword();
		mailRecuperoPassword.setUser(user.get());

		String randomica = RandomStringUtils.randomAlphanumeric(15);
		mailRecuperoPassword.setRecoveryCode(randomica);

		mRepo.save(mailRecuperoPassword);

		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

		helper.setFrom("jaitaprovemail@gmail.com");
		helper.setTo(user.get().getEmail());
		helper.setSubject("Recupero password Opti-CV");

		helper.setText("Per modificare la tua password clicca sul link --> <a href='http://localhost:4200/reset-password/"+randomica+"'>RECUPERO</a>", true);

		emailSender.send(message);

		return ResponseEntity.ok().build();
	}


	@PostMapping
	public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordDto dto)

	{
		Optional<MailRecuperoPassword> m=mRepo.findByRecoveryCode(dto.getRecoveryCode());

		if(m.isEmpty())
			return ResponseEntity.notFound().build();

		User daResettare = m.get().getUser();
		daResettare.setHashedPassword(DigestUtils.md5Hex(dto.getNewPassword()));

		uRepo.save(daResettare);
		m.get().setUsed(true);
		mRepo.save(m.get());

		return ResponseEntity.ok().build();

	}

}
