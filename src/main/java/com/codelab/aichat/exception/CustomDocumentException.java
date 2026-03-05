package com.codelab.aichat.exception;

public class CustomDocumentException extends RuntimeException {

    public CustomDocumentException(String message) {
        super(message);
    }

    public CustomDocumentException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
