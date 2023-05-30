package network;

import model.library.Library;

import java.util.HashMap;

public class ShowLibrariesMessage extends Message {

    private HashMap<String, Library> librariesOfPlayers;

    public ShowLibrariesMessage(String sender, HashMap<String, Library> librariesOfPlayers) {
        super(sender, MessageType.SHOW_OTHERS_LIBRARY);
        this.librariesOfPlayers = librariesOfPlayers;
    }

    public HashMap<String, Library> getLibrariesOfPlayers() {
        return librariesOfPlayers;
    }
}
