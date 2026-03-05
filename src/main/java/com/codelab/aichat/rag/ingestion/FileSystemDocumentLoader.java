package com.codelab.aichat.rag.ingestion;

import com.codelab.aichat.DocumentSourceType;
import com.codelab.aichat.exception.CustomDocumentException;
import com.codelab.aichat.model.SourceDocument;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Component
public class FileSystemDocumentLoader implements DocumentLoader {

    @Value("${marv1n.doc.path}")
    private String documentPath;

    @Override
    public DocumentSourceType type() {
        return DocumentSourceType.FILE;
    }

    @Override
    public List<SourceDocument> loadDocuments() {
        try {
            Path path = Path.of(documentPath);
            String content = Files.readString(path);
            System.out.println(content);
            SourceDocument document = new SourceDocument("company-handbook", content);
            return List.of(document);
        } catch (Exception ex) {
            throw new CustomDocumentException(ex.getMessage(), ex.getCause());
        }
    }
}
