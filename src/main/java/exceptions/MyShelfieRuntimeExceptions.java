package exceptions;

public abstract class MyShelfieRuntimeExceptions extends RuntimeException{
    public MyShelfieRuntimeExceptions(String exceptionMessage){
        super(exceptionMessage);
    }
}
