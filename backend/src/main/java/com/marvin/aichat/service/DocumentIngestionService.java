package com.marvin.aichat.service;

import com.marvin.aichat.model.DocumentSourceType;
import com.marvin.aichat.model.SourceDocument;
import com.marvin.aichat.rag.chunking.ChunkingService;
import com.marvin.aichat.rag.ingestion.DocumentLoader;
import com.marvin.aichat.rag.ingestion.RagIngestionService;
import com.marvin.aichat.rag.model.TextChunk;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DocumentIngestionService {

    private final Map<DocumentSourceType, DocumentLoader> loaderMap;
    private final RagIngestionService ragIngestionService;
    private final ChunkingService chunkingService;

    public DocumentIngestionService(List<DocumentLoader> documentLoaders, RagIngestionService ragIngestionService, ChunkingService chunkingService) {
        this.loaderMap = documentLoaders.stream().collect(
                Collectors.toMap(DocumentLoader::type, Function.identity()));
        this.ragIngestionService = ragIngestionService;
        this.chunkingService = chunkingService;
    }

    public List<TextChunk> loadDocAndIngest(DocumentSourceType type, String fileName) {
        DocumentLoader docLoader = loaderMap.get(type);
        if (docLoader == null) {
            throw new IllegalArgumentException("Could not find the document loader.Verify the provided document type.");
        }
        List<SourceDocument> docs = docLoader.loadDocuments(fileName);
        List<TextChunk> chunks = chunkingService.chunkDocuments(docs);
        if(chunks.isEmpty()){
            log.info("chunks are empty for document {}", fileName);
        }
        return chunks;
    }

    public List<TextChunk> doRagIngestion(MultipartFile file) {
        List<TextChunk> chunks = ragIngestionService.ingest(file);
        log.info("Chunk size: " + chunks.size());
        return chunks;
    }
}
