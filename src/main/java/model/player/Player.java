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

    /**
     *
     * @param username
     */
    public Player(String username){
        this.nickname = username;
        this.library = new Library();
        this.personalObjective = new PersonalObjective();
    }

    /**
     *
     * @return
     */
    public String getNickname() {
        return nickname;
    }

    /**
     *
     */
    public void setAsFirst(){
        this.firstPlayer = true;
    }

    /**
     *
     * @return
     */
    public boolean isFirstPlayer() {
        return firstPlayer;
    }

    /**
     *
     * @return
     */
    public boolean isFirstToEnd() {
        return firstToEnd;
    }

    /**
     *
     * @return
     */
    public int getPoints() {
        return points;
    }

    public Library getLibrary(){
        return this.library;
    }
}
