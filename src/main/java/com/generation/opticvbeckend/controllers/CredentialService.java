package com.generation.opticvbeckend.controllers;

import com.generation.opticvbeckend.automations.RequestData;
import com.generation.opticvbeckend.exceptions.InvalidPasswordException;
import com.generation.opticvbeckend.exceptions.InvalidUsernameException;
import com.generation.opticvbeckend.model.dto.DTOConverterV2;
import com.generation.opticvbeckend.model.dto.UserDtoReqLogin;
import com.generation.opticvbeckend.model.dto.UserDtoReqReg;
import com.generation.opticvbeckend.model.dto.UserProfileDto;
import com.generation.opticvbeckend.model.entities.User;
import com.generation.opticvbeckend.model.repositories.UserRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class CredentialService
{

	@Autowired
	DTOConverterV2 dtoConverter;
	@Autowired
	UserRepository uRepo;
	public String login(UserDtoReqLogin loginDto)
	{
		Optional<User> u = uRepo.findByUsername(loginDto.getUsername());

		if(u.isEmpty())
			throw new InvalidUsernameException("Username non valido");

		User user = u.get();//lo estraggo dall'optional
		String passwordHashata = DigestUtils.md5Hex(loginDto.getPassword());
		if(!user.getHashedPassword().equals(passwordHashata))
			throw new InvalidPasswordException("Password non valida");


		String prefisso = "";

		String suffisso = "";

		for(int i =0;i<4;i++)
		{
			prefisso+=(int)(Math.random()*10);
			suffisso+=(int)(Math.random()*10);
		}

		return prefisso+"-"+user.getId()+"-"+suffisso;
	}

	public String register(UserDtoReqReg userDtoReqReg)
	{
		User user = new User();

		user.setUsername(userDtoReqReg.getUsername());
		user.setEmail(userDtoReqReg.getEmail());
		user.setGender(userDtoReqReg.getGender());
		user.setName(userDtoReqReg.getName());
		user.setSurname(userDtoReqReg.getSurname());
		user.setDob(LocalDate.parse(userDtoReqReg.getDob()));

		String passwordHashata = DigestUtils.md5Hex(userDtoReqReg.getPassword());
		user.setHashedPassword(passwordHashata);

		uRepo.save(user);


		return "Registrazione effettuatata";
	}

	public User getUserByToken(String token)
	{
		if(token == null)
			throw new InvalidUsernameException("Token non valido");

		long id = Long.parseLong(token.split("-")[1]);
		Optional<User> u = uRepo.findById(id);
		if(u.isEmpty())
			throw new InvalidUsernameException("Token non valido");

		return u.get();
	}

	public UserProfileDto getProfile()
	{
		return dtoConverter.converterInUserProfileDto(RequestData.getUser());
	}

	public UserProfileDto modifyUser(User daModificare)
	{
		String passwordHashata = DigestUtils.md5Hex(daModificare.getHashedPassword());

		daModificare.setHashedPassword(passwordHashata);
		return dtoConverter.converterInUserProfileDto(uRepo.save(daModificare));
	}
}
