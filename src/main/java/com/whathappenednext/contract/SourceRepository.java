package com.whathappenednext.contract;

import com.whathappenednext.domain.Source;
import java.util.List;
import java.util.Optional;

/**
 * Contract for source repository operations.
 * Defines the interface for CRUD operations on Source entities.
 */
public interface SourceRepository {

    /**
     * Saves a source to the repository.
     *
     * @param source the source to save
     * @return the saved source with generated ID
     */
    Source save(Source source);

    /**
     * Saves multiple sources to the repository.
     *
     * @param sources the list of sources to save
     * @return the list of saved sources with generated IDs
     */
    List<Source> saveAll(List<Source> sources);

    /**
     * Finds a source by its ID.
     *
     * @param id the source ID
     * @return Optional containing the source if found
     */
    Optional<Source> findById(Long id);

    /**
     * Finds all sources.
     *
     * @return list of all sources
     */
    List<Source> findAll();

    /**
     * Finds all active sources.
     *
     * @return list of active sources
     */
    List<Source> findActive();

    /**
     * Finds sources by type.
     *
     * @param type the source type class
     * @return list of sources of the specified type
     */
    <T extends com.whathappenednext.domain.SourceType> List<Source> findByType(Class<T> type);

    /**
     * Finds a source by its URL.
     *
     * @param url the source URL
     * @return Optional containing the source if found
     */
    Optional<Source> findByUrl(String url);

    /**
     * Updates the active status of a source.
     *
     * @param id the source ID
     * @param active the new active status
     * @return the updated source
     */
    Optional<Source> updateActiveStatus(Long id, boolean active);

    /**
     * Deletes a source by its ID.
     *
     * @param id the source ID
     * @return true if deleted, false if not found
     */
    boolean deleteById(Long id);

    /**
     * Deletes all sources.
     */
    void deleteAll();

    /**
     * Counts the total number of sources.
     *
     * @return the total count
     */
    long count();

    /**
     * Checks if a source with the given URL exists.
     *
     * @param url the source URL
     * @return true if exists, false otherwise
     */
    boolean existsByUrl(String url);
}
