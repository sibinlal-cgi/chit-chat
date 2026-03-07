package com.marvin.aichat.utils;

import com.marvin.aichat.exception.CustomDocumentException;
import com.marvin.aichat.model.SourceDocument;
import lombok.extern.slf4j.Slf4j;
import org.apache.tika.Tika;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@Slf4j
public class TikaDocumentParser {

    private static final String FILE_NOT_FOUND = "File cant be empty or null.";
    Tika tika = new Tika();

    public SourceDocument parseDoc(MultipartFile file) {
        try {
            if(file == null) throw new IllegalArgumentException(FILE_NOT_FOUND);

            String content = tika.parseToString(file.getInputStream());
            if (content == null || content.trim().isEmpty()) throw new IllegalArgumentException(FILE_NOT_FOUND);

            String documentId = file.getOriginalFilename();
            log.info("Parsed document {} with {} characters ", documentId, content.length());
            return new SourceDocument(documentId, content);
        } catch (Exception ex) {
            throw new CustomDocumentException(FILE_NOT_FOUND);
        }
    }

}
