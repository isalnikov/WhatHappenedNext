package com.whathappenednext.contract;

import com.whathappenednext.domain.AnalysisResult;
import com.whathappenednext.domain.News;
import java.util.List;

/**
 * Contract for AI analyzer operations.
 * Defines the interface for analyzing news using qwen code cli.
 */
public interface AIAnalyzer {

    /**
     * Analyzes a single news item.
     *
     * @param news the news item to analyze
     * @return the analysis result
     */
    AnalysisResult analyze(News news);

    /**
     * Analyzes multiple news items in batch.
     *
     * @param newsList the list of news items to analyze
     * @return the combined analysis result
     */
    AnalysisResult analyzeBatch(List<News> newsList);

    /**
     * Analyzes news with a custom prompt.
     *
     * @param news the news item to analyze
     * @param prompt the custom prompt to use
     * @return the analysis result
     */
    AnalysisResult analyzeWithPrompt(News news, String prompt);

    /**
     * Analyzes multiple news items with a custom prompt.
     *
     * @param newsList the list of news items to analyze
     * @param prompt the custom prompt to use
     * @return the combined analysis result
     */
    AnalysisResult analyzeBatchWithPrompt(List<News> newsList, String prompt);

    /**
     * Checks if the analyzer is available and ready.
     *
     * @return true if available, false otherwise
     */
    boolean isAvailable();
}
