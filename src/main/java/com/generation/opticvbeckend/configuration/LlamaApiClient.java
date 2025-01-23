package com.generation.opticvbeckend.configuration;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class LlamaApiClient
{
    private final RestTemplate restTemplate;
    private final String apiUrl = "";
    private final String apiKey = "";

    public LlamaApiClient(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public String askLlamaApi(String question){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> payload = Map.of(
                "prompt", question,
                "max_tokens", 200
        );

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(payload, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, request, String.class);
        return response.getBody();
    }

}
