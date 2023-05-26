package exceptions.controller;
import exceptions.MyShelfieRuntimeExceptions;

public class SpaceSurroundedException extends MyShelfieRuntimeExceptions {
    /**
     * This exception is launched when an object on the board is surrounded by others (does not have at least one free side).
     */
    public SpaceSurroundedException(){
        super("The object you selected is surrounded!");
    }
}