package model;

import model.player.Player;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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
    public Player nextPlayer(){
        throw new NotImplementedException();
    }

    public void setPlayerInTurn(Player playerInTurn) {
        this.playerInTurn = playerInTurn;
    }
}
