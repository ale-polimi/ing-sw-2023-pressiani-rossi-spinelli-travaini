package exceptions.controller;

import exceptions.MyShelfieRuntimeExceptions;

public class NotEnoughSpaceException extends MyShelfieRuntimeExceptions {

    /**
     * This exception is launched when a player's library has no more space in the selected column.
     * @param column is the player's selected column.
     */
    public NotEnoughSpaceException(int column){
        super("There is not enough space in the column: " + column);
    }
}
