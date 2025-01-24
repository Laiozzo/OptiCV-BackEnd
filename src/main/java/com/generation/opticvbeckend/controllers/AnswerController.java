package com.generation.opticvbeckend.controllers;

import com.generation.opticvbeckend.controllers.helpers.AnswerHelpers;
import com.generation.opticvbeckend.model.entities.Answer;
import com.generation.opticvbeckend.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AnswerController
{
    @Autowired
    AnswerHelpers answerHelpers;

    @GetMapping("answers")
    public ResponseEntity<List<String>> getAnswers() {
            List<String> answers = answerHelpers.processLastFileCurrentUser("INSERIRE JOB DESCRIPTION");
        return ResponseEntity.ok(answers); // Restituisci le risposte come lista JSON
    }
}
