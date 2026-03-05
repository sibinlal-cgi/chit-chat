package com.codelab.aichat.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class SourceDocument {

    private final String id;
    private final String content;

}
