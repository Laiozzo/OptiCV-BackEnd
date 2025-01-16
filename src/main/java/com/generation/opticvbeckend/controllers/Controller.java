package com.generation.opticvbeckend.controllers;



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
	DTOConverter dtoConverter;
	@Autowired
	UserRepository uRepo;


	@PostMapping ("/register")
	public UserDtoResp register (@RequestBody UserDtoReqReg dto)
	{
		User u= dtoConverter.convertInUser(dto);
		u= uRepo.save(u);

		return dtoConverter.convertInDtoResp(u);

	}

	@PostMapping("/login")
	public UserDtoResp login (@RequestBody UserDtoReqLogin dto)
	{
		String passwordHashata = DigestUtils.md5Hex(dto.getPassword()).toUpperCase();

		Optional<User> u = uRepo.findByUsernameAndHashedPassword(dto.getUsername(), passwordHashata);

		if(u.isPresent())
			return dtoConverter.convertInDtoResp(u.get());
		else
			throw new RuntimeException("Username or password incorrect");
	}



	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public String gestisciUtenteNonGiusto(RuntimeException e)
	{
		return e.getMessage();
	}



}
