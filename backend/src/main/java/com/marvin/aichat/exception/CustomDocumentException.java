package com.marvin.aichat.exception;

public class CustomDocumentException extends RuntimeException {

    /**
     * TODO: Centralised exception handling
     */

    public CustomDocumentException(String message) {
        super(message);
    }

    public CustomDocumentException(String message, Throwable throwable) {
        super(message, throwable);
    }
}