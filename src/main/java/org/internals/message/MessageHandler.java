package org.internals.message;

import java.util.ArrayList;

/**
 * <h1>MessageHandler</h1>
 *
 * <p>A helper class to which message producer classes delegate the task of
 * maintaining and notifying listeners. </p>
 */
public class MessageHandler implements MessageProducer {
    private Message message;                          // message
    private ArrayList<MessageListener> listeners;     // listener list

    public MessageHandler() {
        this.listeners = new ArrayList<>();
    }

    @Override
    public void addMessageListener(MessageListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeMessageListener(MessageListener listener) {
           listeners.remove(listener);
    }

    @Override
    public void sendMessage(Message message) {
        this.message = message;
        notifyListeners();
    }

    /**
     * Notify each listener in the listener list by calling the listener's
     * messageReceived() method
     */
    private void notifyListeners() {
        for(MessageListener listener: listeners) {
            listener.messageReceived(message);
        }
    }
}
