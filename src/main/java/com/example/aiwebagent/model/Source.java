package com.example.aiwebagent.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * JPA Entity representing a news source (website or Telegram channel).
 * 
 * This entity stores information about sources to be parsed for news content.
 * Supports both website URLs and Telegram channel identifiers.
 * 
 * @author isalnikov
 * @version 1.0.0
 */
@Entity
@Table(name = "sources")
public class Source {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(nullable = false, length = 1024)
    private String url;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SourceType type;

    @Column(length = 512)
    private String parseSelector;

    @Column(nullable = false)
    private boolean isActive;

    @Column
    private LocalDateTime lastParsedAt;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    /**
     * Default constructor required by JPA.
     */
    public Source() {
    }

    /**
     * Constructor with required fields.
     * 
     * @param name the name of the source
     * @param url the URL or identifier of the source
     * @param type the type of source (WEBSITE or TELEGRAM_CHANNEL)
     * @param isActive whether the source is active
     */
    public Source(String name, String url, SourceType type, boolean isActive) {
        this.name = name;
        this.url = url;
        this.type = type;
        this.isActive = isActive;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // Getters and Setters (No Lombok - Clean Code)

    /**
     * Gets the unique identifier of the source.
     * 
     * @return the source ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the source.
     * 
     * @param id the source ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the name of the source.
     * 
     * @return the source name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the source.
     * 
     * @param name the source name
     */
    public void setName(String name) {
        this.name = name;
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * Gets the URL or identifier of the source.
     * 
     * @return the source URL
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets the URL or identifier of the source.
     * 
     * @param url the source URL
     */
    public void setUrl(String url) {
        this.url = url;
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * Gets the type of the source.
     * 
     * @return the source type
     */
    public SourceType getType() {
        return type;
    }

    /**
     * Sets the type of the source.
     * 
     * @param type the source type
     */
    public void setType(SourceType type) {
        this.type = type;
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * Gets the CSS selector or parsing pattern for extracting news.
     * 
     * @return the parse selector
     */
    public String getParseSelector() {
        return parseSelector;
    }

    /**
     * Sets the CSS selector or parsing pattern for extracting news.
     * 
     * @param parseSelector the parse selector
     */
    public void setParseSelector(String parseSelector) {
        this.parseSelector = parseSelector;
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * Checks if the source is active.
     * 
     * @return true if active, false otherwise
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * Sets the active status of the source.
     * 
     * @param isActive the active status
     */
    public void setActive(boolean isActive) {
        this.isActive = isActive;
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * Gets the timestamp of the last successful parse.
     * 
     * @return the last parsed timestamp
     */
    public LocalDateTime getLastParsedAt() {
        return lastParsedAt;
    }

    /**
     * Sets the timestamp of the last successful parse.
     * 
     * @param lastParsedAt the last parsed timestamp
     */
    public void setLastParsedAt(LocalDateTime lastParsedAt) {
        this.lastParsedAt = lastParsedAt;
    }

    /**
     * Gets the creation timestamp.
     * 
     * @return the creation timestamp
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the creation timestamp.
     * 
     * @param createdAt the creation timestamp
     */
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Gets the last update timestamp.
     * 
     * @return the last update timestamp
     */
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Sets the last update timestamp.
     * 
     * @param updatedAt the last update timestamp
     */
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Source{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", type=" + type +
                ", isActive=" + isActive +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
