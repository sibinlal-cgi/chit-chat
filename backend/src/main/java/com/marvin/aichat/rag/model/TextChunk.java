package com.marvin.aichat.rag.model;

public record TextChunk(String chunkId, String documentId, String content) {

    /**
     * may need chunk index to retrieve nearby chunks. This will improve quality
     * of the answer. TODO: Do this later
     */
}
