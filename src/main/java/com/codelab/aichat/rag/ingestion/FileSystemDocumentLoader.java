package com.codelab.aichat.rag.ingestion;

import com.codelab.aichat.exception.CustomDocumentException;
import com.codelab.aichat.model.SourceDocument;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Component
public class FileSystemDocumentLoader implements DocumentLoader {

    @Override
    public List<SourceDocument> loadDocuments() {
        try {
            Path path = Path.of("src/main/resources/docs/company-handbook.txt");
            String content = Files.readString(path);
            System.out.println(content);
            SourceDocument document = new SourceDocument("company-handbook", content);
            return List.of(document);
        } catch (Exception ex) {
            throw new CustomDocumentException(ex.getMessage(), ex.getCause());
        }
    }
}
