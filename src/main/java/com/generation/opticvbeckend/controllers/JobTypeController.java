package com.generation.opticvbeckend.controllers;

import com.generation.opticvbeckend.controllers.helpers.ControllerHelper;
import com.generation.opticvbeckend.model.dto.JobTypeDTO;
import com.generation.opticvbeckend.model.dto.JobTypeListDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobtypes")
public class JobTypeController
{
    @Autowired
    ControllerHelper ch;

    @GetMapping
    public JobTypeListDTO getAllJob()
    {
        JobTypeListDTO jobList = new JobTypeListDTO();
        jobList.setJobTypeList(ch.getJobTypes());
        return jobList;
    }

    @PostMapping
    public ResponseEntity<JobTypeDTO> createJobType(@RequestBody JobTypeDTO jobTypeDTO) {
        // Verifica se la descrizione è valida
        if (jobTypeDTO.getDescription() == null || jobTypeDTO.getDescription().isEmpty()) {
            return ResponseEntity.badRequest().body(null); // Restituisce 400 Bad Request
        }

        // Salva il jobType usando il ControllerHelper
        JobTypeDTO savedJobType = ch.saveJobType(jobTypeDTO);

        // Restituisci il jobType salvato con 201 Created
        return ResponseEntity.status(HttpStatus.CREATED).body(savedJobType);
    }
}
