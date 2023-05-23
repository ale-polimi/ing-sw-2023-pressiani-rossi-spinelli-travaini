package observer;

import network.Message;

/**
 * Observer interface used in the entire game.
 */
public interface Observer {
    /**
     * This method is used to update the observers with a {@link Message}.
     * @param message is the message used to share information.
     */
    void update(Message message);
}
