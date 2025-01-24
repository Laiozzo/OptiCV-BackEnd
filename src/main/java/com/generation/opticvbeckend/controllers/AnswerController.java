package com.generation.opticvbeckend.controllers;

import com.generation.opticvbeckend.controllers.helpers.CvHelpers;
import com.generation.opticvbeckend.model.entities.Answer;
import com.generation.opticvbeckend.model.entities.CV;
import com.generation.opticvbeckend.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AnswerController
{
    @Autowired
    CvHelpers cvHelpers;

    @PostMapping("/answer")
    public List<String> generateAnswer(@RequestBody String jobDescription)
    {
        return cvHelpers.processLastFileCurrentUser(jobDescription);
    }

}
