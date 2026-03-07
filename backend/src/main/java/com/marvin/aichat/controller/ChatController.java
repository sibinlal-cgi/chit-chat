package com.marvin.aichat.controller;

import com.marvin.aichat.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @GetMapping("/test")
    public String test() {
        return "hii";
    }

    @PostMapping
    public String chat(@RequestBody String question) {
        return chatService.ask(question);
    }

}
