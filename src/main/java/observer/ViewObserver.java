package observer;

import java.util.Map;

/**
 * Observer interface used only for the {@link view.View view}.
 */
public interface ViewObserver {
    void onUpdateServerInfo(Map<String, String> serverInfo);
    void onUpdateNickname(String nickname);
    void onMaxPlayers(int numOfPlayers);
}
