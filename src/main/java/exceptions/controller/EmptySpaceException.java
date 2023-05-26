package exceptions.controller;

import exceptions.MyShelfieRuntimeExceptions;

public class EmptySpaceException extends MyShelfieRuntimeExceptions {
    public EmptySpaceException() {
        super("You can't pick up objects from there!");
    }
}
