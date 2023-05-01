package observer;

import network.Message;

public interface Observer {
    void update(Message message);
}
