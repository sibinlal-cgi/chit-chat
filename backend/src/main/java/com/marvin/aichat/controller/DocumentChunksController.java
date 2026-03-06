package com.marvin.aichat.controller;

import com.marvin.aichat.model.DocumentSourceType;
import com.marvin.aichat.model.SourceDocument;
import com.marvin.aichat.rag.chunking.ChunkingService;
import com.marvin.aichat.rag.model.TextChunk;
import com.marvin.aichat.service.DocumentIngestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/doc")
@RequiredArgsConstructor
public class DocumentChunksController {

    private final DocumentIngestionService documentIngestionService;
    private final ChunkingService chunkingService;

    @GetMapping("/chunks")
    public List<TextChunk> getChunks() {
        List<SourceDocument> docs = documentIngestionService.loadDoc(DocumentSourceType.FILE);
        return chunkingService.chunkDocuments(docs);
    }
}
