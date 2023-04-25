package exceptions.player;

import exceptions.MyShelfieRuntimeExceptions;

public class ClientNotRegisteredException extends MyShelfieRuntimeExceptions {
    public ClientNotRegisteredException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
