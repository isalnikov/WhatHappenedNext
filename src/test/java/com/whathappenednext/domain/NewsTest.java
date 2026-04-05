package com.whathappenednext.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for News record.
 * Following TDD approach - tests first, then implementation.
 */
@DisplayName("News Record Tests")
class NewsTest {

    @Test
    @DisplayName("Should create News with valid data")
    void shouldCreateNewsWithValidData() {
        // Given
        String title = "Test News Title";
        String content = "Test news content here";
        String sourceUrl = "https://example.com/news";
        String sourceName = "Example News";
        Instant publishedAt = Instant.now();

        // When
        News news = News.create(title, content, sourceUrl, sourceName, publishedAt);

        // Then
        assertNotNull(news);
        assertEquals(title, news.title());
        assertEquals(content, news.content());
        assertEquals(sourceUrl, news.sourceUrl());
        assertEquals(sourceName, news.sourceName());
        assertEquals(publishedAt, news.publishedAt());
        assertNull(news.id());
        assertNotNull(news.createdAt());
        assertNull(news.analyzedAt());
        assertNull(news.analysisResult());
    }

    @Test
    @DisplayName("Should throw exception when title is null")
    void shouldThrowExceptionWhenTitleIsNull() {
        // Given
        String title = null;
        String content = "Test content";
        String sourceUrl = "https://example.com";
        String sourceName = "Example";
        Instant publishedAt = Instant.now();

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> 
            News.create(title, content, sourceUrl, sourceName, publishedAt)
        );
    }

    @Test
    @DisplayName("Should throw exception when title is blank")
    void shouldThrowExceptionWhenTitleIsBlank() {
        // Given
        String title = "   ";
        String content = "Test content";
        String sourceUrl = "https://example.com";
        String sourceName = "Example";
        Instant publishedAt = Instant.now();

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> 
            News.create(title, content, sourceUrl, sourceName, publishedAt)
        );
    }

    @Test
    @DisplayName("Should throw exception when content is null")
    void shouldThrowExceptionWhenContentIsNull() {
        // Given
        String title = "Test Title";
        String content = null;
        String sourceUrl = "https://example.com";
        String sourceName = "Example";
        Instant publishedAt = Instant.now();

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> 
            News.create(title, content, sourceUrl, sourceName, publishedAt)
        );
    }

    @Test
    @DisplayName("Should throw exception when content is blank")
    void shouldThrowExceptionWhenContentIsBlank() {
        // Given
        String title = "Test Title";
        String content = "   ";
        String sourceUrl = "https://example.com";
        String sourceName = "Example";
        Instant publishedAt = Instant.now();

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> 
            News.create(title, content, sourceUrl, sourceName, publishedAt)
        );
    }

    @Test
    @DisplayName("Should throw exception when source URL is null")
    void shouldThrowExceptionWhenSourceUrlIsNull() {
        // Given
        String title = "Test Title";
        String content = "Test content";
        String sourceUrl = null;
        String sourceName = "Example";
        Instant publishedAt = Instant.now();

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> 
            News.create(title, content, sourceUrl, sourceName, publishedAt)
        );
    }

    @Test
    @DisplayName("Should throw exception when source URL is blank")
    void shouldThrowExceptionWhenSourceUrlIsBlank() {
        // Given
        String title = "Test Title";
        String content = "Test content";
        String sourceUrl = "   ";
        String sourceName = "Example";
        Instant publishedAt = Instant.now();

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> 
            News.create(title, content, sourceUrl, sourceName, publishedAt)
        );
    }

    @Test
    @DisplayName("Should throw exception when publishedAt is null")
    void shouldThrowExceptionWhenPublishedAtIsNull() {
        // Given
        String title = "Test Title";
        String content = "Test content";
        String sourceUrl = "https://example.com";
        String sourceName = "Example";
        Instant publishedAt = null;

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> 
            News.create(title, content, sourceUrl, sourceName, publishedAt)
        );
    }

    @Test
    @DisplayName("Should create News with ID using withId method")
    void shouldCreateNewsWithIdUsingWithIdMethod() {
        // Given
        News news = News.create("Title", "Content", "https://example.com", "Source", Instant.now());
        Long id = 1L;

        // When
        News newsWithId = news.withId(id);

        // Then
        assertNotNull(newsWithId);
        assertEquals(id, newsWithId.id());
        assertEquals(news.title(), newsWithId.title());
        assertEquals(news.content(), newsWithId.content());
        assertEquals(news.sourceUrl(), newsWithId.sourceUrl());
    }

    @Test
    @DisplayName("Should create News with analysis result using withAnalysis method")
    void shouldCreateNewsWithAnalysisResultUsingWithAnalysisMethod() {
        // Given
        News news = News.create("Title", "Content", "https://example.com", "Source", Instant.now());
        String analysisResult = "This is the analysis result";

        // When
        News analyzedNews = news.withAnalysis(analysisResult);

        // Then
        assertNotNull(analyzedNews);
        assertEquals(analysisResult, analyzedNews.analysisResult());
        assertNotNull(analyzedNews.analyzedAt());
        assertEquals(news.title(), analyzedNews.title());
        assertEquals(news.content(), analyzedNews.content());
    }

    @Test
    @DisplayName("Should preserve original news immutability when adding analysis")
    void shouldPreserveOriginalNewsImmutabilityWhenAddingAnalysis() {
        // Given
        News originalNews = News.create("Title", "Content", "https://example.com", "Source", Instant.now());
        String analysisResult = "Analysis result";

        // When
        News analyzedNews = originalNews.withAnalysis(analysisResult);

        // Then
        assertNull(originalNews.analysisResult());
        assertEquals(analysisResult, analyzedNews.analysisResult());
        assertNotSame(originalNews, analyzedNews);
    }
}
