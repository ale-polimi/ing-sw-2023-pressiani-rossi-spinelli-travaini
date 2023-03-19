package exceptions.game;

import exceptions.MyShelfieRuntimeExceptions;

public class TooManyPlayersException extends MyShelfieRuntimeExceptions {
    public TooManyPlayersException(){
        super("Too many players for this game!");
    }
}
