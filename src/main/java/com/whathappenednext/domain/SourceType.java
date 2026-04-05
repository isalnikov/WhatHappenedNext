package com.whathappenednext.domain;

/**
 * Sealed interface for source types.
 * Uses Java 25 sealed classes feature for type safety.
 */
public sealed interface SourceType
    permits SourceType.Rss, SourceType.Html, SourceType.TelegramChannel {

    /**
     * RSS Feed source type.
     */
    final class Rss implements SourceType {
        @Override
        public String toString() {
            return "RSS";
        }
    }

    /**
     * HTML Website source type.
     */
    final class Html implements SourceType {
        @Override
        public String toString() {
            return "HTML";
        }
    }

    /**
     * Telegram Channel source type.
     */
    final class TelegramChannel implements SourceType {
        @Override
        public String toString() {
            return "TELEGRAM_CHANNEL";
        }
    }
}
