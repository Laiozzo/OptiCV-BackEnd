package com.generation.opticvbeckend.service;

import com.generation.opticvbeckend.configuration.LlamaApiClient;
import com.generation.opticvbeckend.model.entities.Answer;
import com.generation.opticvbeckend.model.repositories.AnswerRepository;
import org.aspectj.weaver.patterns.TypePatternQuestions;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class AnswerService {
    private final LlamaApiClient llamaApiClient;
    private final AnswerRepository answerRepository;


    public AnswerService(LlamaApiClient llamaApiClient, AnswerRepository answerRepository) {
        this.llamaApiClient = llamaApiClient;
        this.answerRepository = answerRepository;
    }



    public List<String> processQuestions(String jobDescription, String cvParse) {

        List<String> questions = List.of(
                "Questo è il cv del candidato: "+ cvParse + "metti in relazione il cv con le richieste lavorative:" + jobDescription +
                "Valuta il candidato per le sua Hard Skill, per le sue Soft Skill e dammi una recensione generale. Per ogni valutazione inizia a scrivere con Hard Skill:, Soft Skill:, Recensione:, Voto:"
        );
        List<Answer> answerList = new ArrayList<>();
        List<String> responses = new ArrayList<>();

        for(String question : questions) {
            List<Map<String, String>> messages = List.of(
                    Map.of("role", "system", "content", "You are a helpful assistant."),
                    Map.of("role", "user", "content", question)
            );

            // Invia la domanda al modello
            String response = llamaApiClient.sendQuestion("model-identifier", messages, 0.7);

            // Salva la risposta in una classe Answer
            Answer answer = new Answer(question, response);
            answerList.add(answer);


            String responseCorrect = llamaApiClient.sendQuestion("meta-llama-3.1-8b-instruct", messages, 0.7);
            responses.add(response);
            // Salva nel DB (opzionale)
            answerRepository.save(answer);
        }

        Pattern  voto = Pattern.compile("Voto:.*" , Pattern.DOTALL);
        Pattern recensione = Pattern.compile("Recensione:.*", Pattern.DOTALL);
        Pattern softskill = Pattern.compile("Soft Skill:.*", Pattern.DOTALL);
        Pattern hardskill = Pattern.compile("Hard Skill:.*", Pattern.DOTALL);

        ArrayList<Pattern> regex = new ArrayList<>();
        regex.add(voto);
        regex.add(recensione);
        regex.add(softskill);
        regex.add(hardskill);

        String var = responses.get(0);
        List<String> res = new ArrayList<>();

        for(Pattern p : regex) {
            Matcher matcher = p.matcher(var);
            if(matcher.find()) {
                res.add(matcher.group());
            }
            var = matcher.replaceAll("");
        }

        Pattern number = Pattern.compile("\\d+");
        Matcher matcher = number.matcher(res.get(0));
        String percent = null;

        Random rand = new Random();


        if(matcher.find()) {
            percent = matcher.group();
            int i = Integer.parseInt(percent);
            i = (i * 10) - rand.nextInt(10);
            percent = String.valueOf(i);
            res.set(0, percent);
        }

        for(String s : res) {
            System.out.println(s);
        }

        return res;
    }
}




