package exceptions.player;

import exceptions.MyShelfieRuntimeExceptions;

public class EmptyDeckException extends MyShelfieRuntimeExceptions {
    public EmptyDeckException(){
        super("You have already no objects in hand!");
    }
}
