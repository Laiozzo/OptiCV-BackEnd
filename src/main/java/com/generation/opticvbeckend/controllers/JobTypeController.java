package com.generation.opticvbeckend.controllers;

import com.generation.opticvbeckend.controllers.helpers.ControllerHelper;
import com.generation.opticvbeckend.model.dto.JobTypeDTO;
import com.generation.opticvbeckend.model.dto.JobTypeListDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/jobtypes")
public class JobTypeController
{
    @Autowired
    ControllerHelper ch;

    @GetMapping
    public JobTypeListDTO getAllJob(){
        JobTypeListDTO jobList = new JobTypeListDTO();
        jobList.setJobTypeList(ch.getJobTypes());
        return jobList;
    }
}
