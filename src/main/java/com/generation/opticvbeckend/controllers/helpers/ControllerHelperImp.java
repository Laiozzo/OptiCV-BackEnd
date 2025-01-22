package com.generation.opticvbeckend.controllers.helpers;

import com.generation.opticvbeckend.model.dto.DTOConverter;
import com.generation.opticvbeckend.model.dto.JobTypeDTO;
import com.generation.opticvbeckend.model.entities.JobType;
import com.generation.opticvbeckend.model.repositories.JobTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ControllerHelperImp implements ControllerHelper
{
    @Autowired
    JobTypeRepository jobrepo;

    @Autowired
    DTOConverter converter;

    @Override
    public List<JobTypeDTO> getJobTypes() {
        return jobrepo.findAll().stream().map(batch-> converter.toDTO(batch)).toList();
    }

    @Override
    public JobTypeDTO getJobType(long id) {

        Optional<JobType> batch = jobrepo.findById(id);
        if(batch.isPresent())
            return converter.toDTO(batch.get());
        else
            System.out.println("Job type not found");
        return null;
    }
}
