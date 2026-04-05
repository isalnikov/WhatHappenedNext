package com.example.aiwebagent.contract.source;

import com.example.aiwebagent.model.SourceType;
import java.time.LocalDateTime;

/**
 * Data Transfer Object (Record) for Source entity.
 * 
 * This is an immutable record used for transferring source data
 * between layers of the application.
 * 
 * @param id the unique identifier of the source
 * @param name the name of the source
 * @param url the URL or identifier of the source
 * @param type the type of source (WEBSITE or TELEGRAM_CHANNEL)
 * @param parseSelector the CSS selector for parsing
 * @param isActive whether the source is active
 * @param lastParsedAt the timestamp of last successful parse
 * @param createdAt the creation timestamp
 * @param updatedAt the last update timestamp
 * 
 * @author isalnikov
 * @version 1.0.0
 */
public record SourceDto(
    Long id,
    String name,
    String url,
    SourceType type,
    String parseSelector,
    boolean isActive,
    LocalDateTime lastParsedAt,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {
}
