package com.whathappenednext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * WhatHappenedNext Application.
 * 
 * Intelligent AI Web Agent Telegram Bot that:
 * - Receives and stores a list of websites and channels to parse
 * - Parses news from these sources
 * - Saves news to H2 database
 * - Analyzes news via qwen code cli (data passed through stdin)
 * - Sends analysis results to users in Telegram
 * 
 * Built with Java 25, Spring Boot 4, following TDD and ContractFirst approaches.
 * Uses virtual threads (Project Loom), Records, Sealed classes, Pattern Matching.
 * No Lombok - clean code without magic!
 */
@SpringBootApplication
@EnableScheduling
public class WhatHappenedNextApplication {

    private static final Logger log = LoggerFactory.getLogger(WhatHappenedNextApplication.class);

    public static void main(String[] args) {
        log.info("Starting WhatHappenedNext Application...");
        SpringApplication.run(WhatHappenedNextApplication.class, args);
        log.info("WhatHappenedNext Application started successfully!");
    }
}
