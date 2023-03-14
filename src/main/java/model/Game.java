package model;

import enumerations.GameState;
import enumerations.TypeSpace;
import jdk.jshell.spi.ExecutionControl;
import model.board.Board;
import model.objects.ObjectsDeck;
import model.player.Player;


import java.util.ArrayList;

public class Game {
    private Board board;
    private ArrayList<Player> players;
    private ObjectsDeck objectsDeck;
    private Turn turn;
    private GameState gameState;

    public void startGame(){

        gameState = GameState.IN_GAME;
       try { new ExecutionControl.NotImplementedException("not implemented");
    }catch(Exception ex){new ExecutionControl.NotImplementedException("not implemented");
    }}

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

                                board.putObjectIn();
                            }
                        }
                        }
                    }
            }
        }
    }
}
