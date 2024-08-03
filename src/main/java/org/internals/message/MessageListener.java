package org.internals.message;

public interface MessageListener {
    /**
     * Called to receive a message sent by a message producer.
     * @param message the message that was sent.
     */
    void messageReceived(Message message);
}
