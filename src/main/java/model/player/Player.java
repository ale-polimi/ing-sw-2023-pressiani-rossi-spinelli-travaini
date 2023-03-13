package model.player;

import enumerations.PlayerState;
import model.library.Library;

public class Player {
    private String nickname;
    private boolean firstPlayer;
    private boolean firstToEnd;
    private Library library;
    private PersonalObjective personalObjective;
    private PlayerState playerState;
    private int points;

}
