package network;

public class EndGameMessage extends Message{
    private String winner;
    /**
     * Constructor for class EndGameMessage
     * @param sender      is the sender of the message.
     *
     */
    public EndGameMessage(String sender, String winner) {
        super(sender, MessageType.END_GAME);
        this.winner=winner;
    }

    /**
     * Getter for the EndGameMessage
     * @return The winner of the game
     */
    public String getWinner() {return winner;}
}

