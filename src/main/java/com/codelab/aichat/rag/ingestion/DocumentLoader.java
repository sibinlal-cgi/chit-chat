package com.codelab.aichat.rag.ingestion;

import com.codelab.aichat.model.SourceDocument;

import java.util.List;

public interface DocumentLoader {

    /**
     * Loads a list of source documents.
     * Example document loaders as below for future implementation
     * FileSystemDocumentLoader
     * PdfDocumentLoader
     * S3DocumentLoader
     * DatabaseDocumentLoader
     * @return a list of SourceDocument objects containing document data
     */

    List<SourceDocument> loadDocuments();
}
