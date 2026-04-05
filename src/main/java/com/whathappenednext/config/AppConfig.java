package com.whathappenednext.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Application configuration.
 * Configures core beans for the application.
 */
@Configuration
public class AppConfig {

    private static final Logger log = LoggerFactory.getLogger(AppConfig.class);

    @Value("${app.qwen.command:qwen}")
    private String qwenCommand;

    @Value("${app.parser.timeout-ms:30000}")
    private long parserTimeoutMs;

    @Value("${app.parser.max-items:100}")
    private int parserMaxItems;

    /**
     * Logs the application configuration on startup.
     */
    public AppConfig() {
        log.info("AppConfig initialized");
    }

    @Bean
    public AppProperties appProperties() {
        log.debug("Creating AppProperties bean");
        return new AppProperties(qwenCommand, parserTimeoutMs, parserMaxItems);
    }

    /**
     * Application properties record.
     */
    public record AppProperties(
        String qwenCommand,
        long parserTimeoutMs,
        int parserMaxItems
    ) {
        public AppProperties {
            if (qwenCommand == null || qwenCommand.isBlank()) {
                throw new IllegalArgumentException("Qwen command cannot be null or blank");
            }
            if (parserTimeoutMs <= 0) {
                throw new IllegalArgumentException("Parser timeout must be positive");
            }
            if (parserMaxItems <= 0) {
                throw new IllegalArgumentException("Parser max items must be positive");
            }
        }
    }
}
