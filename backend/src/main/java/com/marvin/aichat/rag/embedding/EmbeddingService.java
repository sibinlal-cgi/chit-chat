package com.marvin.aichat.rag.embedding;

import com.marvin.aichat.rag.model.ChunkEmbedding;
import com.marvin.aichat.rag.model.TextChunk;
import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.model.embedding.EmbeddingModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmbeddingService {

    private final EmbeddingModel embeddingModel;

    public List<ChunkEmbedding> generateEmbeddings(List<TextChunk> chunks) {

        List<ChunkEmbedding> embeddingsList = new ArrayList<>();
        for (TextChunk chunk : chunks) {

            Embedding embedding = embeddingModel.embed(chunk.content()).content();
            ChunkEmbedding chunkEmbedding = new ChunkEmbedding(
                    chunk.chunkId(), chunk.documentId(), chunk.content(), embedding.vector()
            );
            embeddingsList.add(chunkEmbedding);
        }
        return embeddingsList;
    }

}
