package com.whathappenednext.domain;

import java.time.Instant;

/**
 * Record representing a news item.
 * Uses Java 25 record feature for immutable data carrier.
 */
public record News(
    Long id,
    String title,
    String content,
    String sourceUrl,
    String sourceName,
    Instant publishedAt,
    Instant createdAt,
    Instant analyzedAt,
    String analysisResult
) {
    public News {
        // Validation in compact constructor
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be null or blank");
        }
        if (content == null || content.isBlank()) {
            throw new IllegalArgumentException("Content cannot be null or blank");
        }
        if (sourceUrl == null || sourceUrl.isBlank()) {
            throw new IllegalArgumentException("Source URL cannot be null or blank");
        }
        if (publishedAt == null) {
            throw new IllegalArgumentException("Published date cannot be null");
        }
    }

    /**
     * Creates a new News with auto-generated timestamps.
     */
    public static News create(String title, String content, String sourceUrl, String sourceName, Instant publishedAt) {
        return new News(null, title, content, sourceUrl, sourceName, publishedAt, Instant.now(), null, null);
    }

    /**
     * Returns a copy of this News with the specified analysis result.
     */
    public News withAnalysis(String analysisResult) {
        return new News(
            this.id,
            this.title,
            this.content,
            this.sourceUrl,
            this.sourceName,
            this.publishedAt,
            this.createdAt,
            Instant.now(),
            analysisResult
        );
    }

    /**
     * Returns a copy of this News with the specified ID.
     */
    public News withId(Long id) {
        return new News(
            id,
            this.title,
            this.content,
            this.sourceUrl,
            this.sourceName,
            this.publishedAt,
            this.createdAt,
            this.analyzedAt,
            this.analysisResult
        );
    }
}
