package com.codelab.aichat.service;

import dev.langchain4j.model.chat.ChatLanguageModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatLanguageModel chatLanguageModel;

    public String ask(String question) {
        String prompt = "You are my assistant. Answer the following question carefully!"+
                "question: " + question;
        return chatLanguageModel.generate(prompt);
    }
}
