package com.whathappenednext.contract;

import com.whathappenednext.domain.News;
import java.util.List;

/**
 * Contract for news parser operations.
 * Defines the interface for parsing news from various sources.
 */
public interface NewsParser {

    /**
     * Parses news from a source URL.
     *
     * @param url the source URL
     * @return list of parsed news items
     */
    List<News> parse(String url);

    /**
     * Parses news from a source URL with additional configuration.
     *
     * @param url the source URL
     * @param config the parsing configuration
     * @return list of parsed news items
     */
    List<News> parse(String url, ParserConfig config);

    /**
     * Checks if this parser supports the given URL.
     *
     * @param url the URL to check
     * @return true if supported, false otherwise
     */
    boolean supports(String url);

    /**
     * Gets the parser type name.
     *
     * @return the parser type name
     */
    String getType();

    /**
     * Parsing configuration record.
     */
    record ParserConfig(
        int maxItems,
        boolean includeFullContent,
        List<String> selectors,
        long timeoutMs
    ) {
        public ParserConfig {
            if (maxItems <= 0) {
                throw new IllegalArgumentException("Max items must be positive");
            }
            if (timeoutMs <= 0) {
                throw new IllegalArgumentException("Timeout must be positive");
            }
        }

        public static ParserConfig defaults() {
            return new ParserConfig(100, true, List.of(), 30000);
        }
    }
}
