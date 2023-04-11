package network;

import controller.Controller;

public class MaxPlayersMessage extends Message{
    public MaxPlayersMessage(int maxPlayers){
        super("Controller", String.valueOf(maxPlayers), MessageType.MAX_PLAYERS_FOR_GAME);
    }

    @Override
    public void executeOnController(Controller controller) {
        ;
    }
}
