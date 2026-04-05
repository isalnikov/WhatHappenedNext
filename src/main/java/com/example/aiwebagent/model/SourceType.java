package com.example.aiwebagent.model;

/**
 * Enum representing the type of a news source.
 * 
 * Sources can be either regular websites or Telegram channels.
 * 
 * @author isalnikov
 * @version 1.0.0
 */
public enum SourceType {
    /**
     * A regular website with HTML content to be parsed using Jsoup.
     */
    WEBSITE,
    
    /**
     * A Telegram channel to be parsed via Telegram API or RSS feed.
     */
    TELEGRAM_CHANNEL
}
