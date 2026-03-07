package com.marvin.aichat.controller;

import com.marvin.aichat.model.DocumentSourceType;
import com.marvin.aichat.rag.chunking.ChunkingService;
import com.marvin.aichat.rag.model.ChunkEmbedding;
import com.marvin.aichat.rag.model.TextChunk;
import com.marvin.aichat.service.DocumentIngestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/v1/doc")
@RequiredArgsConstructor
public class DocumentIngestionController {

    private final DocumentIngestionService docIngestionService;
    private final ChunkingService chunkingService;

    /**
     * This API will ingest the file from the api.
     */

    @PostMapping("/ingest")
    public ResponseEntity<?> manualIngest(@RequestParam("file") MultipartFile file) {

        List<ChunkEmbedding> chunkEmbeddings = docIngestionService.doRagIngestion(file);
        return ResponseEntity.ok(
                "chunkEmbeddings created: " + chunkEmbeddings
        );
    }

    /**
     * This API will trigger file ingestion from a predefined location eg: project doc folder, s3, ftp etc
     * TODO: accept file name and type from api. complete flow in v2 release.
     *
     * @return
     */

    @GetMapping("/chunks")
    public ResponseEntity<?> autoIngestFromFileLocation() {
        String fileName = "company-handbook.txt";
        List<ChunkEmbedding> chunkEmbeddings = docIngestionService.loadDocAndIngest(DocumentSourceType.FILE, fileName);
        return ResponseEntity.ok(
                "chunkEmbeddings created: " + chunkEmbeddings
        );
    }
}
