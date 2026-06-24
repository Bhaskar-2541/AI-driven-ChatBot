package com.example.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ChatbotService {

    @Value("${gemini.api.url}")
    private String apiUrl;

    @Value("${gemini.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    public ChatbotService() {
        this.restTemplate = new RestTemplate();
    }

    public String generateResponse(String userMessage) {
        try {
            // Append API key as a query parameter for Gemini
            String fullUrl = apiUrl + "?key=" + apiKey;

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            // Construct Gemini payload: { "contents": [{ "parts": [{ "text": "user message" }] }] }
            Map<String, String> textPart = new HashMap<>();
            textPart.put("text", userMessage);

            List<Map<String, String>> partsList = new ArrayList<>();
            partsList.add(textPart);

            Map<String, Object> contentObj = new HashMap<>();
            contentObj.put("parts", partsList);

            List<Map<String, Object>> contentsList = new ArrayList<>();
            contentsList.add(contentObj);

            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("contents", contentsList);

            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);
            ResponseEntity<Map> response = restTemplate.postForEntity(fullUrl, entity, Map.class);

            return extractTextFromGemini(response.getBody());

        } catch (Exception e) {
            return "Error interacting with Gemini Engine: " + e.getMessage();
        }
    }

    @SuppressWarnings("unchecked")
    private String extractTextFromGemini(Map<String, Object> responseBody) {
        try {
            List<Map<String, Object>> candidates = (List<Map<String, Object>>) responseBody.get("candidates");
            Map<String, Object> firstCandidate = candidates.get(0);
            Map<String, Object> content = (Map<String, Object>) firstCandidate.get("content");
            List<Map<String, Object>> parts = (List<Map<String, Object>>) content.get("parts");
            return (String) parts.get(0).get("text");
        } catch (Exception e) {
            return "Failed to parse AI response framework.";
        }
    }
}