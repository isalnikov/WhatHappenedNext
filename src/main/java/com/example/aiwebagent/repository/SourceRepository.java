package com.example.aiwebagent.repository;

import com.example.aiwebagent.model.Source;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data JPA repository for managing Source entities.
 * 
 * Provides CRUD operations and custom query methods for sources.
 * 
 * @author isalnikov
 * @version 1.0.0
 */
@Repository
public interface SourceRepository extends JpaRepository<Source, Long> {

    /**
     * Finds all active sources.
     * 
     * @return list of active sources
     */
    List<Source> findByIsActiveTrue();

    /**
     * Finds all sources of a specific type.
     * 
     * @param type the source type to filter by
     * @return list of sources of the specified type
     */
    List<Source> findByType(SourceType type);

    /**
     * Finds all active sources of a specific type.
     * 
     * @param type the source type to filter by
     * @return list of active sources of the specified type
     */
    List<Source> findByIsActiveTrueAndType(SourceType type);

    /**
     * Checks if a source with the given URL exists.
     * 
     * @param url the URL to check
     * @return true if a source with the URL exists, false otherwise
     */
    boolean existsByUrl(String url);

    /**
     * Finds a source by its URL.
     * 
     * @param url the URL of the source
     * @return the source with the given URL, or null if not found
     */
    Source findByUrl(String url);

    /**
     * Counts all active sources.
     * 
     * @return the count of active sources
     */
    long countByIsActiveTrue();

    /**
     * Custom query to find sources that haven't been parsed recently.
     * 
     * @return list of sources needing update
     */
    @Query("SELECT s FROM Source s WHERE s.isActive = true AND (s.lastParsedAt IS NULL OR s.lastParsedAt < :threshold)")
    List<Source> findSourcesNeedingUpdate(java.time.LocalDateTime threshold);
}
