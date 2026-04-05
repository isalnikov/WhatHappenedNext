package com.example.aiwebagent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Main application class for the AI Web Agent Bot.
 * This bot parses news from configured sources, analyzes them using Qwen CLI,
 * and sends results to Telegram users.
 * 
 * Features:
 * - Java 25 Virtual Threads for concurrent parsing
 * - H2 Database (dev) / PostgreSQL (prod)
 * - TDD with >90% code coverage
 * - Clean Code without Lombok
 * - Swagger/OpenAPI Documentation
 * - Context Engineering Architecture
 * 
 * @author isalnikov
 * @version 1.0.0
 */
@SpringBootApplication
@EnableScheduling
public class AiWebAgentBotApplication {

    private static final Logger logger = LoggerFactory.getLogger(AiWebAgentBotApplication.class);

    /**
     * Main entry point of the application.
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {
        logger.info("Starting AI Web Agent Bot Application...");
        SpringApplication.run(AiWebAgentBotApplication.class, args);
        logger.info("AI Web Agent Bot Application started successfully!");
    }
}
