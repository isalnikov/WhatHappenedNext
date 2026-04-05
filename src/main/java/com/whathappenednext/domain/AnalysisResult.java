package com.whathappenednext.domain;

import java.time.Instant;

/**
 * Sealed class for analysis results.
 * Uses Java 25 sealed classes feature for type-safe result handling.
 */
public sealed interface AnalysisResult
    permits AnalysisResult.Success, AnalysisResult.Error, AnalysisResult.Partial {

    /**
     * Successful analysis with complete result.
     */
    final class Success implements AnalysisResult {
        private final String content;
        private final Instant analyzedAt;

        public Success(String content) {
            if (content == null || content.isBlank()) {
                throw new IllegalArgumentException("Content cannot be null or blank");
            }
            this.content = content;
            this.analyzedAt = Instant.now();
        }

        public String content() {
            return content;
        }

        public Instant analyzedAt() {
            return analyzedAt;
        }

        @Override
        public String toString() {
            return "Success{analyzedAt=" + analyzedAt + ", contentLength=" + content.length() + "}";
        }
    }

    /**
     * Failed analysis with error information.
     */
    final class Error implements AnalysisResult {
        private final String errorMessage;
        private final Throwable cause;
        private final Instant failedAt;

        public Error(String errorMessage) {
            this(errorMessage, null);
        }

        public Error(String errorMessage, Throwable cause) {
            if (errorMessage == null || errorMessage.isBlank()) {
                throw new IllegalArgumentException("Error message cannot be null or blank");
            }
            this.errorMessage = errorMessage;
            this.cause = cause;
            this.failedAt = Instant.now();
        }

        public String errorMessage() {
            return errorMessage;
        }

        public Throwable cause() {
            return cause;
        }

        public Instant failedAt() {
            return failedAt;
        }

        @Override
        public String toString() {
            return "Error{failedAt=" + failedAt + ", message=" + errorMessage + "}";
        }
    }

    /**
     * Partial analysis with partial results.
     */
    final class Partial implements AnalysisResult {
        private final String content;
        private final String warning;
        private final Instant analyzedAt;

        public Partial(String content, String warning) {
            if (content == null || content.isBlank()) {
                throw new IllegalArgumentException("Content cannot be null or blank");
            }
            if (warning == null || warning.isBlank()) {
                throw new IllegalArgumentException("Warning cannot be null or blank");
            }
            this.content = content;
            this.warning = warning;
            this.analyzedAt = Instant.now();
        }

        public String content() {
            return content;
        }

        public String warning() {
            return warning;
        }

        public Instant analyzedAt() {
            return analyzedAt;
        }

        @Override
        public String toString() {
            return "Partial{analyzedAt=" + analyzedAt + ", warning=" + warning + "}";
        }
    }
}
