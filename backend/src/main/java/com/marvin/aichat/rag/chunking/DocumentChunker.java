package com.marvin.aichat.rag.chunking;

import com.marvin.aichat.model.SourceDocument;
import com.marvin.aichat.rag.model.TextChunk;

import java.util.List;

public interface DocumentChunker {

    /**
     * There could be many chunking strategies. Any chosen strategy class can implement
     * this interface for chunking.
     *
     */

    List<TextChunk> chunk(SourceDocument document);

}
