package model;

import model.player.Player;


public class Turn {
    private int turn;
    private Player playerInTurn;
    private boolean lastTurn;

    public Player getPlayerInTurn() {
        return playerInTurn;
    }
    public boolean isLastTurn() {
        return lastTurn;
    }
    

    public void setPlayerInTurn(Player playerInTurn) {
        this.playerInTurn = playerInTurn;
    }
}
