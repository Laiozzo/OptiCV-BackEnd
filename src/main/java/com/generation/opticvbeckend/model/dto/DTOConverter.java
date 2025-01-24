package com.generation.opticvbeckend.model.dto;

import com.generation.opticvbeckend.model.entities.JobType;
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
}
