package com.marvin.aichat.controller;

import com.marvin.aichat.model.DocumentSourceType;
import com.marvin.aichat.model.SourceDocument;
import com.marvin.aichat.service.ChatService;
import com.marvin.aichat.service.DocumentIngestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;
    private final DocumentIngestionService documentIngestionService;

    @GetMapping("/test")
    public String test() {
        return "hii";
    }

    @PostMapping
    public String chat(@RequestBody String question) {
        return chatService.ask(question);
    }

}
