package exceptions.player;

import exceptions.MyShelfieRuntimeExceptions;

public class TooManyObjectsInHandException extends MyShelfieRuntimeExceptions {

    public TooManyObjectsInHandException(int maxObjects){
        super("You have already " + maxObjects + " in hand!");
    }
}
