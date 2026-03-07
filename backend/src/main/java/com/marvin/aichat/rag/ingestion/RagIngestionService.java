package com.marvin.aichat.rag.ingestion;

import com.marvin.aichat.exception.CustomDocumentException;
import com.marvin.aichat.model.SourceDocument;
import com.marvin.aichat.rag.chunking.ChunkingService;
import com.marvin.aichat.rag.model.TextChunk;
import com.marvin.aichat.service.FileValidationService;
import com.marvin.aichat.utils.TikaDocumentParser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RagIngestionService {

    /**
     * This Service will do parsing and chunking for a MultipartFile input
     */

    private final TikaDocumentParser documentParser;
    private final ChunkingService chunkingService;
    private final FileValidationService fileValidationService;

    private static final String FILE_NOT_FOUND = "File cant be empty or null.";

    public List<TextChunk> ingest(MultipartFile file) {
        fileValidationService.validateFile(file);
        SourceDocument document = documentParser.parseDoc(file);
        if(document == null) throw new CustomDocumentException(FILE_NOT_FOUND);
        return chunkingService.chunkDocuments(List.of(document));
    }
}
