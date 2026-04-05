package com.whathappenednext.domain;

import java.time.Instant;

/**
 * Record representing a news source (website, channel, RSS feed).
 * Uses Java 25 record feature for immutable data carrier.
 */
public record Source(
    Long id,
    String name,
    String url,
    SourceType type,
    boolean active,
    Instant createdAt,
    Instant updatedAt
) {
    public Source {
        // Validation in compact constructor
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or blank");
        }
        if (url == null || url.isBlank()) {
            throw new IllegalArgumentException("URL cannot be null or blank");
        }
        if (type == null) {
            throw new IllegalArgumentException("Type cannot be null");
        }
    }

    /**
     * Creates a new active Source with auto-generated timestamps.
     */
    public static Source create(String name, String url, SourceType type) {
        Instant now = Instant.now();
        return new Source(null, name, url, type, true, now, now);
    }

    /**
     * Returns a copy of this Source with the specified ID.
     */
    public Source withId(Long id) {
        return new Source(
            id,
            this.name,
            this.url,
            this.type,
            this.active,
            this.createdAt,
            Instant.now()
        );
    }

    /**
     * Returns a copy of this Source with the active status toggled.
     */
    public Source withActive(boolean active) {
        return new Source(
            this.id,
            this.name,
            this.url,
            this.type,
            active,
            this.createdAt,
            Instant.now()
        );
    }
}
