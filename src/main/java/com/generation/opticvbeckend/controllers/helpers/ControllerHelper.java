package com.generation.opticvbeckend.controllers.helpers;

import com.generation.opticvbeckend.model.dto.JobTypeDTO;

import java.util.List;

public interface ControllerHelper {

    List<JobTypeDTO> getJobTypes();
    JobTypeDTO getJobType(long id);



}
