package controller;

import enumerations.GameState;
import model.Game;

public class GameController {
    private Game game;

    public GameController(){
        initGameController();
    }
    public void initGameController(){
        this.game = new Game();
        game.setGameState(GameState.LOGIN);
    }

    public void onMessageReceived(){
        switch (game.getGameState()){
            case LOGIN:
                loginState();
                break;
            case START:
                startState();
                break;
            case IN_GAME:
                //inGameState();
                break;
            case END:
                //endState();
                break;
            default:
                break;
        }
    }

    private void loginState(){
        game.setMaxPlayers(3);
        game.setFirstPlayer();
    }

    private void startState(){

    }


}
