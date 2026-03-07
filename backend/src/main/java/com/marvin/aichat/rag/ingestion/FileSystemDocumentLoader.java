package com.marvin.aichat.rag.ingestion;

import com.marvin.aichat.model.DocumentSourceType;
import com.marvin.aichat.exception.CustomDocumentException;
import com.marvin.aichat.model.SourceDocument;
import com.marvin.aichat.service.FileValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Component
@RequiredArgsConstructor
public class FileSystemDocumentLoader implements DocumentLoader {

    @Value("${marv1n.doc.file.path}")
    private String documentPath;

    private final ResourceLoader resourceLoader;
    private final FileValidationService fileValidationService;


    @Override
    public DocumentSourceType type() {
        return DocumentSourceType.FILE;
    }

    @Override
    public List<SourceDocument> loadDocuments(String fileName) {

        try {
            Resource resource = resourceLoader.getResource(documentPath + fileName);
            if (!resource.exists()) throw new CustomDocumentException("Could not find the file for document ingestion");

            File file = resource.getFile();
            fileValidationService.validate(file);

            String content = new String(resource.getInputStream().readAllBytes());
            SourceDocument document = new SourceDocument("company-handbook", content);
            return List.of(document);
        } catch (Exception ex) {
            throw new CustomDocumentException(ex.getMessage(), ex.getCause());
        }
    }
}
