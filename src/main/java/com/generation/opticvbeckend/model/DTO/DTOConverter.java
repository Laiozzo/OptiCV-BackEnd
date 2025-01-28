package com.generation.opticvbeckend.model.dto;

import com.generation.opticvbeckend.model.entities.JobType;
import com.generation.opticvbeckend.model.entities.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

@Service
public class DTOConverter
{
    public JobTypeDTO toDTO(JobType jobT){
        JobTypeDTO dto = new JobTypeDTO();
        dto.setJobName(jobT.getJobName());
        dto.setDescription(jobT.getDescription());
        dto.setId(jobT.getId());
        return dto;
    }
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
        userProfileDto.setUsername(profile.getUsername());
        userProfileDto.setDob(profile.getDob().toString());
        userProfileDto.setEmail(profile.getEmail());
        userProfileDto.setName(profile.getName());
        userProfileDto.setSurname(profile.getSurname());
        userProfileDto.setGender(profile.getGender());
        return userProfileDto;
    }
}
