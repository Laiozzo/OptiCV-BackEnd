package com.generation.opticvbeckend.service;

import com.generation.opticvbeckend.configuration.LlamaApiClient;
import com.generation.opticvbeckend.model.entities.Answer;
import com.generation.opticvbeckend.model.repositories.AnswerRepository;
import org.aspectj.weaver.patterns.TypePatternQuestions;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AnswerService {
    private final LlamaApiClient llamaApiClient;
    private final AnswerRepository answerRepository;


    public AnswerService(LlamaApiClient llamaApiClient, AnswerRepository answerRepository) {
        this.llamaApiClient = llamaApiClient;
        this.answerRepository = answerRepository;
    }


    public List<Answer> processQuestions(String jobDescription) {
        String question2 = "Scannerizza il CV caricato e gli elementi all'interno e la job description salvata; mettili a paragone e crea un percentuale di rating che indichi quanto sono compatibili " + jobDescription;

        List<String> questions = List.of(
                "Con il CV che ti ho dato, dammi una review generale",
                question2,
                "Dammi solo il rating finale",
                "Dammi la compatibilità delle Hard-Skill",
                "Dammi la compatibilità delle Soft Skill",
                "Dammi la compatibilità del livello di formazione",
                "Dammi la compatibilità del livello di esperienza lavorativa richiesto",
                "Dammi la compatibilità sul l'inserimento delle informazioni di contatto personali",
                "Dammi suggerimenti su come essere il perfetto candidato per questa posizione lavorativa",
                "Dammi suggerimenti su come modificare il curriculum"
        );
        List<Answer> answerList = new ArrayList<>();

        for(String question : questions) {
            List<Map<String, String>> messages = List.of(
                    Map.of("role", "system", "content", "Always answer in rhymes."),
                    Map.of("role", "user", "content", question)
            );

            // Invia la domanda al modello
            String response = llamaApiClient.sendQuestion("model-identifier", messages, 0.7);

            // Salva la risposta in una classe Answer
            Answer answer = new Answer(question, response);
            answerList.add(answer);

            // Salva nel DB (opzionale)
            answerRepository.save(answer);
        }

        return answerList;
    }
}




