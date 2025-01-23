package com.generation.opticvbeckend.controllers;

import com.generation.opticvbeckend.model.entities.Answer;
import com.generation.opticvbeckend.service.AnswerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/answers")
public class AnswerController
{
    private String message = "POSITION & JOB DESCRIPTION\\n\" +\n" +
            "                \"\\n\" +\n" +
            "                \"\\n\" +\n" +
            "                \"\\n\" +\n" +
            "                \"Cerchiamo un Java Developer per dei progetti in ambito Digital.\\n\" +\n" +
            "                \"\\n\" +\n" +
            "                \"\\n\" +\n" +
            "                \"\\n\" +\n" +
            "                \"\\n\" +\n" +
            "                \"\\n\" +\n" +
            "                \"Requisiti:\\n\" +\n" +
            "                \"\\n\" +\n" +
            "                \"Minimo 2-3 anni di esperienza nello sviluppo di applicazioni in Java con framework Spring, SpringBoot, preferibilmente in contesti Finance/Fintech, Telco, GDO o Retail.\\n\" +\n" +
            "                \"Esperienza nello sviluppo a microservizi tramite chiamate REST \\n\" +\n" +
            "                \"Conoscenza di container Docker e/o Kubernetes\\n\" +\n" +
            "                \"Buona conoscenza di DB Oracle, SQL, NoSQL \\n\" +\n" +
            "                \"Buon livello di inglese parlato (minimo livello B2)\\n\" +\n" +
            "                \"\\n\" +\n" +
            "                \"\\n\" +\n" +
            "                \"\\n\" +\n" +
            "                \"\\n\" +\n" +
            "                \"Sede di lavoro: Milano, ibrido \\n\" +\n" +
            "                \"\\n\" +\n" +
            "                \"Tipo di contratto: Tempo indeterminato\\n\" +\n" +
            "                \"\\n\" +\n" +
            "                \"Benefit: ticket, welfare\"";
    private final AnswerService answerService;

    public AnswerController(AnswerService answerService){
        this.answerService = answerService;
    }

    @GetMapping("/api/answers")
    public ResponseEntity<List<String>> getAnswers() {
        List<String> answers = answerService.processQuestions();
        return ResponseEntity.ok(answers); // Restituisci le risposte come lista JSON
    }

    @PostMapping("/process")
    public ResponseEntity<List<Answer>> processQuestion() {
        List<Answer> answer = answerService.processQuestions("POSITION & JOB DESCRIPTION\n" +
                "\n" +
                "\n" +
                "\n" +
                "Cerchiamo un Java Developer per dei progetti in ambito Digital.\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "Requisiti:\n" +
                "\n" +
                "Minimo 2-3 anni di esperienza nello sviluppo di applicazioni in Java con framework Spring, SpringBoot, preferibilmente in contesti Finance/Fintech, Telco, GDO o Retail.\n" +
                "Esperienza nello sviluppo a microservizi tramite chiamate REST \n" +
                "Conoscenza di container Docker e/o Kubernetes\n" +
                "Buona conoscenza di DB Oracle, SQL, NoSQL \n" +
                "Buon livello di inglese parlato (minimo livello B2)\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "Sede di lavoro: Milano, ibrido \n" +
                "\n" +
                "Tipo di contratto: Tempo indeterminato\n" +
                "\n" +
                "Benefit: ticket, welfare");
        return ResponseEntity.ok(answer);
    }
}
