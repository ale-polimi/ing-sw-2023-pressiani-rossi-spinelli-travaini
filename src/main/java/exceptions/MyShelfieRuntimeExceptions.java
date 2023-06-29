package exceptions;

/**
 * Abstract class representing custom exceptions of the game.
 */
public abstract class MyShelfieRuntimeExceptions extends RuntimeException{
    public MyShelfieRuntimeExceptions(String exceptionMessage){
        super(exceptionMessage);
    }
}
