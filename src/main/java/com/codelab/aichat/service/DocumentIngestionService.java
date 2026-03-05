package com.codelab.aichat.service;

import com.codelab.aichat.model.SourceDocument;
import com.codelab.aichat.rag.ingestion.DocumentLoader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentIngestionService {

    private final DocumentLoader documentLoader;

    public List<SourceDocument> loadDoc() {
        return documentLoader.loadDocuments();
    }
}
