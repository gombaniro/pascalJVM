package org.internals.message;

public interface MessageProducer {
    /**
     * Add a listener tot he listener list.
     * @param listener listener the listener to add.
     */
     void addMessageListener(MessageListener listener);

    /**
     * Remove a listener from the listener list.
     * @param listener the listener to remove
     */
    void removeMessageListener(MessageListener listener);

    /**
     * Notifier listeners after setting the message.
     * @param message the message to set.
     */
    void sendMessage(Message message);
}
