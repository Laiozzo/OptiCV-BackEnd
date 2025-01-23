package com.generation.opticvbeckend.controllers;



import com.generation.opticvbeckend.automations.RequestData;
import com.generation.opticvbeckend.exceptions.InvalidPasswordException;
import com.generation.opticvbeckend.exceptions.InvalidUsernameException;
import com.generation.opticvbeckend.model.dto.UserDtoReqLogin;
import com.generation.opticvbeckend.model.dto.UserDtoReqReg;
import com.generation.opticvbeckend.model.dto.UserProfileDto;
import com.generation.opticvbeckend.model.entities.User;
import com.generation.opticvbeckend.model.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class Controller
{

	@Autowired
	CredentialService credentialService;
	@Autowired
	private UserRepository userRepository;

	@PostMapping ("/register")
	public void register (@RequestBody UserDtoReqReg registerDto) { credentialService.register(registerDto);
	}

	@PostMapping("/login")
	public String login (@RequestBody UserDtoReqLogin loginDto) {return credentialService.login(loginDto);}


	@PutMapping("/myprofile")
	public User modify(@RequestBody UserDtoReqReg modifyDto)
	{
		User daModificare = RequestData.getUser();
		//fagli fare la modifica
		daModificare.setName(modifyDto.getName());
		daModificare.setSurname(modifyDto.getSurname());
		daModificare.setUsername(modifyDto.getUsername());
		daModificare.setEmail(modifyDto.getEmail());
		daModificare.setDob(modifyDto.getDob());
		daModificare.setGender(modifyDto.getGender());
		daModificare.setHashedPassword(modifyDto.getPassword());

		return userRepository.save(daModificare);
	}

	@DeleteMapping
	public Long delete()
	{
		User daCancellare = RequestData.getUser();
		Long id=daCancellare.getId();
		userRepository.delete(daCancellare);
		return id;
	}

	@GetMapping( "/myprofile")
	public UserProfileDto getProfile()
	{
		return credentialService.getProfile();
	}

	@ExceptionHandler(InvalidUsernameException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String gestisciEccezioneUsurname(InvalidUsernameException e) {return e.getMessage();}

	@ExceptionHandler(InvalidPasswordException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public String gestisciEccezionePassword(InvalidPasswordException e) {return e.getMessage();}


}
