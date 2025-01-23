package com.generation.opticvbeckend.controllers;

import com.generation.opticvbeckend.model.entities.Answer;
import com.generation.opticvbeckend.service.AnswerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/answers")
public class AnswerController
{
    private final AnswerService answerService;

    public AnswerController(AnswerService answerService){
        this.answerService = answerService;
    }

    @PostMapping("/process")
    public ResponseEntity<List<Answer>> processQuestion() {
        List<Answer> answer = answerService.processQuestions();
        return ResponseEntity.ok(answer);
    }
}
