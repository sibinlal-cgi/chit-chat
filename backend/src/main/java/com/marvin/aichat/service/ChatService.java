package com.marvin.aichat.service;

import dev.langchain4j.model.chat.ChatLanguageModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatLanguageModel chatLanguageModel;

    public String ask(String question) {
        String prompt = "You are an onboarding assistant to Marv1n project. Answer questions using the provided context." +
                "If the answer is not in the context, say you don't know." +
                "question: " + question;
        return chatLanguageModel.generate(prompt);
    }
}
