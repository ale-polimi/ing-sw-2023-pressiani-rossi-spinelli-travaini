package exceptions.controller;

import exceptions.MyShelfieRuntimeExceptions;

public class NotEnoughSpaceException extends MyShelfieRuntimeExceptions {

    public NotEnoughSpaceException(int column){
        super("There is not enough space in the column: " + column);
    }
}
