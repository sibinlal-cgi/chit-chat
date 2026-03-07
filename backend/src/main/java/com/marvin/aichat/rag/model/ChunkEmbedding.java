package com.marvin.aichat.rag.model;

public record ChunkEmbedding(
        String chunkId,
        String documentId,
        //int chunkIndex, : TODO
        String content,
        float[] embedding
) {
}
