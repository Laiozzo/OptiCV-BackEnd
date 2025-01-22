package com.generation.opticvbeckend.AI;

import java.io.IOException;
import java.net.http.*;
import java.net.URI;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;

import com.generation.opticvbeckend.model.entities.CV;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class OpenAIClient {

    private final String baseUrl;
    private final String apiKey;

    public OpenAIClient(String baseUrl, String apiKey){
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
    }


    /**
     * Inseriamo il testo preso dal parsing, successivamente lo diamo in pasto all'IA
     * Per ottenere la classe CV con tutti i suoi campi in modo corretto
     */
    public String generatedCV(String cv){

        String input = """
                [
                    {"role":"system","content": "You are a helpful AI assistant."},
                    {"role":"user","content": "We need to create a CV class with this properties:     public CV(String name, String surname, String city, String country, String gender, String contactDetails, String education, String workExperience, String skills, String patent) {
                                                                                                          this.name = name;
                                                                                                          this.gender = gender;
                                                                                                          this.contactDetails = contactDetails;
                                                                                                          this.education = education;
                                                                                                          this.workExperience = workExperience;
                                                                                                          this.skills = skills;
                                                                                                          
                                                                                                      }"}\s
                ]""";

        //Crea il corpo della richiesta

        String requestBody = """
                {
                "model": "your-model",
                "messages":""" + input + """
                }
                """;

        //Inizializza il client HTTP
        HttpClient client = HttpClient.newHttpClient();

        //Crea la richiesta HTTP

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/chat/completions"))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + apiKey)
                .POST(BodyPublishers.ofString(requestBody))
                .build();

        try{
            //Invia la richiesta e ottieni la risponse
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

            System.out.println(response.body());

            //Restituisci la risposta come stringa JSON
            return response.body();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


}