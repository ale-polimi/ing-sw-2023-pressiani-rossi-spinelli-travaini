package model.player;

import enumerations.PlayerState;
import model.library.Library;
import model.library.PersonalObjective;

public class Player {
    private String nickname;
    private boolean firstPlayer;
    private boolean firstToEnd;
    private Library library;
    private PersonalObjective personalObjective;
    private PlayerState playerState;
    private int points;

    public Player(String username){
        this.nickname = username;
    }

    public String getNickname() {
        return nickname;
    }

    public boolean isFirstPlayer() {
        return firstPlayer;
    }

    public boolean isFirstToEnd() {
        return firstToEnd;
    }

    public int getPoints() {
        return points;
    }
}
