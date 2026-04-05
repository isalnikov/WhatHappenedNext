package com.whathappenednext.contract;

/**
 * Contract for Telegram message sender operations.
 * Defines the interface for sending messages to Telegram users.
 */
public interface TelegramSender {

    /**
     * Sends a text message to a user.
     *
     * @param chatId the user's chat ID
     * @param message the message to send
     * @return true if sent successfully, false otherwise
     */
    boolean sendMessage(Long chatId, String message);

    /**
     * Sends a text message with markdown formatting.
     *
     * @param chatId the user's chat ID
     * @param message the message to send (markdown formatted)
     * @return true if sent successfully, false otherwise
     */
    boolean sendMessageWithMarkdown(Long chatId, String message);

    /**
     * Sends a text message with HTML formatting.
     *
     * @param chatId the user's chat ID
     * @param message the message to send (HTML formatted)
     * @return true if sent successfully, false otherwise
     */
    boolean sendMessageWithHtml(Long chatId, String message);

    /**
     * Sends analysis results to a user.
     *
     * @param chatId the user's chat ID
     * @param title the analysis title
     * @param content the analysis content
     * @return true if sent successfully, false otherwise
     */
    boolean sendAnalysisResult(Long chatId, String title, String content);

    /**
     * Sends an error notification to a user.
     *
     * @param chatId the user's chat ID
     * @param errorMessage the error message
     * @return true if sent successfully, false otherwise
     */
    boolean sendErrorNotification(Long chatId, String errorMessage);

    /**
     * Checks if the sender is available and ready.
     *
     * @return true if available, false otherwise
     */
    boolean isAvailable();
}
