package com.marvin.aichat.service;

import com.marvin.aichat.exception.CustomDocumentException;
import lombok.extern.slf4j.Slf4j;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.unit.DataSize;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.util.Set;

@Service
@Slf4j
public class FileValidationService {

    /**
     * Any future file validations can be written here.
     * TODO: checksum validation to prevent duplicate uploads.
     */

    @Value("${spring.servlet.multipart.max-file-size}")
    private DataSize maxFileSize;

    private static final Set<String> ALLOWED_TYPES = Set.of(
            "text/plain",
            "application/pdf",
            "text/html",
            "text/markdown",
            "text/x-markdown",
            "application/vnd.openxmlformats-officedocument.wordprocessingml.document"
    );

    private final Tika tika = new Tika();

    public void validateFile(MultipartFile file) {

        if (file == null || file.isEmpty())
            throw new IllegalArgumentException("upload file is empty");

        if (file.getSize() > maxFileSize.toBytes())
            throw new CustomDocumentException("File exceeds maximum " +
                    "allowed size of " + maxFileSize.toBytes() + "MB");

        validateMimeType(file);
    }

    public void validate(File file) {

        if (file == null || !file.exists()) {
            throw new IllegalArgumentException("File does not exist.");
        }

        if (file.length() > maxFileSize.toBytes()) {
            throw new IllegalArgumentException(
                    "File exceeds maximum allowed size: " + maxFileSize.toMegabytes() + " MB");
        }
        validateMimeType(file);
    }

    private void validateMimeType(MultipartFile file) {

        try {
            String mimeType = tika.detect(file.getInputStream());
            log.info("Detected MIME type: {}", mimeType);

            if (!ALLOWED_TYPES.contains(mimeType)) {
                throw new CustomDocumentException("Unsupported file type: " + mimeType);
            }

        } catch (Exception e) {
            throw new CustomDocumentException("Unable to detect file type", e);
        }
    }

    private void validateMimeType(File file) {

        try (FileInputStream fis = new FileInputStream(file)) {

            String mimeType = tika.detect(fis);
            log.info("Detected MIME type: {}", mimeType);

            if (!ALLOWED_TYPES.contains(mimeType)) {
                throw new CustomDocumentException("Unsupported file type: " + mimeType);
            }

        } catch (Exception e) {
            throw new CustomDocumentException("Unable to detect file type", e);
        }
    }
}
