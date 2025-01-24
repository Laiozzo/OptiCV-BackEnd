package com.generation.opticvbeckend.controllers;

import com.generation.opticvbeckend.controllers.helpers.ControllerHelper;
import com.generation.opticvbeckend.model.dto.JobTypeDTO;
import com.generation.opticvbeckend.model.dto.JobTypeListDTO;
import com.generation.opticvbeckend.model.entities.JobType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public void insert(@RequestBody JobTypeDTO jobTypeDTO){
        //JobType jobT = ch.









    }
}
