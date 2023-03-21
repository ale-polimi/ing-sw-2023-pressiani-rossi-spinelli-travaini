package model;

import enumerations.GameState;
import enumerations.TypeSpace;
import exceptions.game.TooManyPlayersException;
import jdk.jshell.spi.ExecutionControl;
import model.board.Board;
import model.board.BoardSpace;
import model.objects.ObjectsDeck;
import model.player.Player;


import java.util.ArrayList;

public class Game {
    private Board board;
    private ArrayList<Player> players;
    public static int MAX_PLAYERS = 4;
    private int chosenPlayersNumber;
    private ObjectsDeck objectsDeck;
    private Turn turn;
    private GameState gameState;

    public Game(){
        this.board = new Board();
        this. objectsDeck = new ObjectsDeck();
        this.gameState = GameState.LOGIN;
    }

    public boolean setMaxPlayers(int chosenMaxPlayers) {
        if ((chosenMaxPlayers > 1 && chosenMaxPlayers <= MAX_PLAYERS) && gameState.equals(GameState.LOGIN)) {
            this.chosenPlayersNumber = chosenMaxPlayers;
            return true;
        }
        return false;
    }

    public int getChosenPlayers(){
        return this.chosenPlayersNumber;
    }

    public void addToGame(Player player) throws TooManyPlayersException {
        if(players.size() > chosenPlayersNumber){
            throw new TooManyPlayersException();
        } else if(player.getNickname().equals(null)) {
            throw new NullPointerException();
        } else {
            players.add(player);
        }
    }

    public int numOfPlayers(){
        return chosenPlayersNumber;
    }

    public void startGame() {

        gameState = GameState.IN_GAME;
        try {
            new ExecutionControl.NotImplementedException("not implemented");
        } catch (Exception ex) {
            new ExecutionControl.NotImplementedException("not implemented");
        }
    }

    public boolean isNicknameTaken(String playerNickname){

        for (int i=0; i < players.size(); i++){
            if(playerNickname.equals(players.get(i).getNickname())){
                return true;
            }
        }return false;
    }

    public void restoreBoard(Board board){
        for(int x= 0; x <9; x++){
            for(int y = 0; y < 9; y++){
                if(!board.getSpace(x,y).getTypeSpace().equals(TypeSpace.UNUSABLE)){
                    switch (board.getSpace(x,y).getTypeSpace()){
                        case FOR_FOUR_PLAYERS -> {
                            if(players.size()==4){

                               if(board.getSpace(x,y).getObject()== null){
                                board.putObjectIn(board.getSpace(x,y),objectsDeck.removeFromDeck());}
                            }
                        }
                         case FOR_THREE_PLAYERS -> {
                             if(players.size()==3){
                                 if(board.getSpace(x,y).getObject()== null){
                                 board.putObjectIn(board.getSpace(x,y),objectsDeck.removeFromDeck());}
                             }

                         }
                        case FOR_TWO_PLAYERS -> {
                            if(players.size()==2){
                                if(board.getSpace(x,y).getObject()== null){
                                board.putObjectIn(board.getSpace(x,y),objectsDeck.removeFromDeck());}
                            }
                        }
                    }
            }
        }
    }
}

    public Turn getTurn() {
        return turn;
    }
    public void endGame(){
        gameState = GameState.END;
    }

}

