package com.generation.opticvbeckend.model.entities;


import com.generation.opticvbeckend.model.dto.UserDtoReqReg;
import com.generation.opticvbeckend.model.dto.UserDtoResp;
import com.generation.opticvbeckend.model.dto.UserProfileDto;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

@Service
public class DTOConverter
{
	public UserDtoResp convertInDtoResp(User user)
	{
		//ID_UTENTE-primaletterausername
		UserDtoResp userDtoResp = new UserDtoResp();
		String token  = user.getId()+"-"+user.getUsername().charAt(0)+"-";
		userDtoResp.setToken(token);


		return userDtoResp;
	}


	public User convertInUser(UserDtoReqReg dto)
	{
		User user = new User();
		user.setUsername(dto.getUsername());
		String passwordHashata = DigestUtils.md5Hex(dto.getPassword()).toUpperCase();
		user.setHashedPassword(passwordHashata);
		user.setEmail(dto.getEmail());
		user.setGender(dto.getGender());
		user.setDob(dto.getDob());
		user.setName(dto.getName());
		user.setSurname(dto.getSurname());
		return user;
	}

	public UserProfileDto converterInUserProfileDto (User profile)
	{
		UserProfileDto userProfileDto = new UserProfileDto();
		userProfileDto.setDob(profile.getDob().toString());
		userProfileDto.setEmail(profile.getEmail());
		userProfileDto.setName(profile.getName());
		userProfileDto.setSurname(profile.getSurname());
		userProfileDto.setGender(profile.getGender());
		return userProfileDto;
	}
}
