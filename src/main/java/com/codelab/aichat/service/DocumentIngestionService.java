package com.codelab.aichat.service;

import com.codelab.aichat.DocumentSourceType;
import com.codelab.aichat.model.SourceDocument;
import com.codelab.aichat.rag.ingestion.DocumentLoader;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class DocumentIngestionService {

    private final Map<DocumentSourceType, DocumentLoader> loaderMap;

    public DocumentIngestionService(List<DocumentLoader> documentLoaders) {
        this.loaderMap = documentLoaders.stream().collect(
                Collectors.toMap(DocumentLoader::type, Function.identity()));
    }

    public List<SourceDocument> loadDoc(DocumentSourceType type) {
        DocumentLoader docLoader = loaderMap.get(type);
        if (docLoader == null) {
            throw new IllegalArgumentException("Could not find document loader");
        }
        return docLoader.loadDocuments();
    }
}
