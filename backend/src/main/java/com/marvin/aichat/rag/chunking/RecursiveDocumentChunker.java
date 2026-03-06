package com.marvin.aichat.rag.chunking;

import com.marvin.aichat.model.SourceDocument;
import com.marvin.aichat.rag.model.TextChunk;
import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.DocumentSplitter;
import dev.langchain4j.data.document.splitter.DocumentSplitters;
import dev.langchain4j.data.segment.TextSegment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class RecursiveDocumentChunker implements DocumentChunker {

    /**
     * Using LangChain4j recursive splitter.
     * hard coded 500 chunksize and 50 overlap. TODO: Configure these values
     * TODO: This strategy can be reviewed and changed later. Add proper exception handling
     *
     */

    private final DocumentSplitter documentSplitter;

    public RecursiveDocumentChunker() {
        this.documentSplitter = DocumentSplitters.recursive(500, 50);
    }

    @Override
    public List<TextChunk> chunk(SourceDocument document) {

        //load doc
        Document doc = Document.from(document.content());

        //split using document splitter
        List<TextSegment> segments = documentSplitter.split(doc);
        List<TextChunk> chunks = new ArrayList<>();
        for (TextSegment segment : segments) {
            TextChunk chunk = new TextChunk(UUID.randomUUID().toString(),
                    document.id(), segment.text());
            chunks.add(chunk);
        }
        return chunks;
    }
}
