package com.whathappenednext.contract;

import com.whathappenednext.domain.News;
import java.util.List;
import java.util.Optional;

/**
 * Contract for news repository operations.
 * Defines the interface for CRUD operations on News entities.
 */
public interface NewsRepository {

    /**
     * Saves a news item to the repository.
     *
     * @param news the news item to save
     * @return the saved news item with generated ID
     */
    News save(News news);

    /**
     * Saves multiple news items to the repository.
     *
     * @param newsList the list of news items to save
     * @return the list of saved news items with generated IDs
     */
    List<News> saveAll(List<News> newsList);

    /**
     * Finds a news item by its ID.
     *
     * @param id the news item ID
     * @return Optional containing the news item if found
     */
    Optional<News> findById(Long id);

    /**
     * Finds all news items.
     *
     * @return list of all news items
     */
    List<News> findAll();

    /**
     * Finds news items by source URL.
     *
     * @param sourceUrl the source URL
     * @return list of news items from the specified source
     */
    List<News> findBySourceUrl(String sourceUrl);

    /**
     * Finds news items that have not been analyzed yet.
     *
     * @return list of unanalyzed news items
     */
    List<News> findUnanalyzed();

    /**
     * Finds news items that have been analyzed.
     *
     * @return list of analyzed news items
     */
    List<News> findAnalyzed();

    /**
     * Updates the analysis result for a news item.
     *
     * @param id the news item ID
     * @param analysisResult the analysis result
     * @return the updated news item
     */
    Optional<News> updateAnalysis(Long id, String analysisResult);

    /**
     * Deletes a news item by its ID.
     *
     * @param id the news item ID
     * @return true if deleted, false if not found
     */
    boolean deleteById(Long id);

    /**
     * Deletes all news items.
     */
    void deleteAll();

    /**
     * Counts the total number of news items.
     *
     * @return the total count
     */
    long count();

    /**
     * Finds news items with pagination.
     *
     * @param offset the offset
     * @param limit the maximum number of items to return
     * @return list of news items
     */
    List<News> findAll(int offset, int limit);
}
