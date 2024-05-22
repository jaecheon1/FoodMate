package com.demo.controller;

import com.demo.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @PostMapping("/send")
    public String sendMessage(@RequestParam String message) {
        return chatService.getChatbotResponse(message);
    }
}
