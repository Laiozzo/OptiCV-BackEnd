package com.generation.opticvbeckend.mapper;

import com.generation.opticvbeckend.model.dto.CVDTO;
import com.generation.opticvbeckend.model.entities.CV;

public class CVMapper {
    public static CV fromDTO(CVDTO dto){
        return new CV(
                dto.getName(),
                dto.getContactDetails(),
                dto.getEducation(),
                dto.getWorkExperience(),
                dto.getSkills()
        );
    }
}
