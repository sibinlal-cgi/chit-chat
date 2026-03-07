package com.marvin.aichat.service;

import com.marvin.aichat.exception.CustomDocumentException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.unit.DataSize;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service
public class FileValidationService {

    /**
     * Any future file validations can be written here.
     * TODO: checksum validation to prevent duplicate uploads.
     */

    @Value("${spring.servlet.multipart.max-file-size}")
    private DataSize maxFileSize;

    public void validateFile(MultipartFile file) {

        if (file == null || file.isEmpty())
            throw new IllegalArgumentException("upload file is empty");

        if (file.getSize() > maxFileSize.toBytes())
            throw new CustomDocumentException("File exceeds maximum " +
                    "allowed size of " + maxFileSize.toBytes() + "MB");
    }

    public void validate(File file) {

        if (file == null || !file.exists()) {
            throw new IllegalArgumentException("File does not exist.");
        }

        if (file.length() > maxFileSize.toBytes()) {
            throw new IllegalArgumentException(
                    "File exceeds maximum allowed size: " + maxFileSize.toMegabytes() + " MB");
        }
    }
}
