package com.generation.opticvbeckend.controllers;



import com.generation.opticvbeckend.exceptions.InvalidPasswordException;
import com.generation.opticvbeckend.exceptions.InvalidUsernameException;
import com.generation.opticvbeckend.model.dto.UserDtoReqLogin;
import com.generation.opticvbeckend.model.dto.UserDtoReqReg;
import com.generation.opticvbeckend.model.dto.UserDtoResp;
import com.generation.opticvbeckend.model.entities.DTOConverter;
import com.generation.opticvbeckend.model.entities.User;
import com.generation.opticvbeckend.model.repositories.UserRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class Controller
{

	@Autowired
	CredentialService credentialService;

	@PostMapping ("/register")
	public String register (@RequestBody UserDtoReqReg registerDto) {return credentialService.register(registerDto);
	}

	@PostMapping("/login")
	public String login (@RequestBody UserDtoReqLogin loginDto) {return credentialService.login(loginDto);}


	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String gestisciEccezioneUsurname(InvalidUsernameException e) {return e.getMessage();}

	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public String gestisciEccezionePassword(InvalidPasswordException e) {return e.getMessage();}


}
