package com.generation.opticvbeckend.configuration;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Component
public class LlamaApiClient {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper; // Per processare il JSON

    private final String apiUrl = "http://localhost:1234/v1/chat/completions";
    private final String apiKey = "lm-studio";

    public LlamaApiClient(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public String sendQuestion(String model, List<Map<String, String>> messages, double temperature) {
        // Corpo della richiesta
        Map<String, Object> requestBody = Map.of(
                "model", model,
                "messages", messages,
                "temperature", temperature
        );

        // Intestazioni
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

        try {
            // Invio della richiesta
            ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, requestEntity, String.class);

            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                // Parsing della risposta JSON
                JsonNode jsonResponse = objectMapper.readTree(response.getBody());

                // Estrai il contenuto della risposta
                JsonNode choices = jsonResponse.get("choices");
                if (choices != null && choices.isArray() && choices.size() > 0) {
                    JsonNode firstChoice = choices.get(0);
                    JsonNode message = firstChoice.get("message");
                    if (message != null) {
                        return message.get("content").asText(); // Contenuto del messaggio
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Errore durante la richiesta: " + e.getMessage();
        }

        return "Errore: risposta vuota o malformata.";
    }
}
