package com.marvin.aichat.rag.chunking;

import com.marvin.aichat.model.SourceDocument;
import com.marvin.aichat.rag.model.TextChunk;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChunkingService {

    /**
     * Right now considering only one documentChunker. Later modify this class based on the strategy type to automatically
     * load the correct document chunker using a Strategy design pattern.
     */

    private final DocumentChunker documentChunker;

    public ChunkingService(DocumentChunker documentChunker) {
        this.documentChunker = documentChunker;
    }

    public List<TextChunk> chunkDocuments(List<SourceDocument> documents) {
        List<TextChunk> chunks = new ArrayList<>();
        for (SourceDocument document : documents) {
            chunks.addAll(documentChunker.chunk(document));
        }
        return chunks;
    }
}
