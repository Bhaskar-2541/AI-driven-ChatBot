package com.example.controller;


import org.springframework.web.bind.annotation.*;

import com.example.model.ChatMessage;
import com.example.service.ChatbotService;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin(origins = "*") // Allows connection from frontend
public class ChatController {

    private final ChatbotService chatbotService;

    // Constructor injection
    public ChatController(ChatbotService chatbotService) {
        this.chatbotService = chatbotService;
    }

    @PostMapping
    public ChatMessage getBotResponse(@RequestBody ChatMessage userRequest) {
        String botReply = chatbotService.generateResponse(userRequest.getMessage());
        return new ChatMessage(botReply);
    }
}
