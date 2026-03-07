package com.marvin.aichat.rag.embedding;

import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.googleai.GoogleAiEmbeddingModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name = "llm.provider", havingValue = "gemini")
public class GeminiEmbeddingConfig {

    @Value("${llm.gemini.api.key}")
    private String apiKey;

    @Value("${llm.gemini.Embedding.model}")
    private String embeddingModel;

    @Bean
    public EmbeddingModel embeddingModel() {
        return GoogleAiEmbeddingModel.builder()
                .apiKey(apiKey)
                .modelName(embeddingModel)
                .build();
    }
}