package com.codelab.aichat.controller;

import com.codelab.aichat.model.SourceDocument;
import com.codelab.aichat.service.ChatService;
import com.codelab.aichat.service.DocumentIngestionService;
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

    @GetMapping("/loadDoc")
    public List<SourceDocument> loadDoc() {
        return documentIngestionService.loadDoc();
    }

}
