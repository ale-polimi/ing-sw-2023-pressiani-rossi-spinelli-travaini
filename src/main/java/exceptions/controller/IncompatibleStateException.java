package exceptions.controller;

import enumerations.PlayerState;
import exceptions.MyShelfieRuntimeExceptions;

public class IncompatibleStateException extends MyShelfieRuntimeExceptions {
    /**
     * This exception is launched when an object's state does not match the needed state for an operation.
     * @param expectedState is the state the object should be in.
     * @param providedState is the state the object is actually in.
     */
    public IncompatibleStateException(PlayerState expectedState, PlayerState providedState){
        super("You are not in the correct state. State needed: " + expectedState + " State provided: " + providedState);
    }
}
