package com.example.aiwebagent.contract.source;

import com.example.aiwebagent.contract.source.SourceDto;
import java.util.List;
import java.util.Optional;

/**
 * Contract interface for Source management operations.
 * 
 * Defines the API for adding, removing, listing, and managing news sources.
 * This interface follows the Contract First approach - defined before implementation.
 * 
 * @author isalnikov
 * @version 1.0.0
 */
public interface SourceService {

    /**
     * Adds a new source to the system.
     * 
     * @param sourceDto the source data transfer object containing source information
     * @return the saved source DTO with generated ID
     * @throws IllegalArgumentException if the source URL already exists or is invalid
     */
    SourceDto addSource(SourceDto sourceDto);

    /**
     * Removes a source by its ID.
     * 
     * @param id the unique identifier of the source to remove
     * @throws IllegalArgumentException if the source with given ID does not exist
     */
    void removeSource(Long id);

    /**
     * Lists all sources in the system.
     * 
     * @return list of all source DTOs
     */
    List<SourceDto> listSources();

    /**
     * Toggles the active status of a source.
     * 
     * @param id the unique identifier of the source to toggle
     * @return the updated source DTO
     * @throws IllegalArgumentException if the source with given ID does not exist
     */
    SourceDto toggleSource(Long id);

    /**
     * Gets a source by its ID.
     * 
     * @param id the unique identifier of the source
     * @return Optional containing the source DTO if found, empty otherwise
     */
    Optional<SourceDto> getSourceById(Long id);

    /**
     * Updates an existing source.
     * 
     * @param id the unique identifier of the source to update
     * @param sourceDto the source data transfer object containing updated information
     * @return the updated source DTO
     * @throws IllegalArgumentException if the source with given ID does not exist
     */
    SourceDto updateSource(Long id, SourceDto sourceDto);

    /**
     * Checks if a source with the given URL already exists.
     * 
     * @param url the URL to check
     * @return true if a source with the URL exists, false otherwise
     */
    boolean existsByUrl(String url);
}
