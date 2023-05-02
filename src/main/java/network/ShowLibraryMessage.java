package network;

import model.library.Library;

/**
 * Message which contains the player's library. USed to send the updated library after a move to the player.
 */
@Deprecated
public class ShowLibraryMessage extends Message{
    private final Library playerLibrary;

    /**
     * Default constructor.
     * @param sender is the sender of the message.
     * @param playerLibrary is the Library to be wrapped in this message.
     */
    public ShowLibraryMessage(String sender, Library playerLibrary) {
        super(sender, MessageType.SHOW_LIBRARY);
        this.playerLibrary = playerLibrary;
    }

    /**
     * Getter method to return the library.
     * @return the {@link Library library}.
     */
    public Library getPlayerLibrary() {
        return playerLibrary;
    }
}
