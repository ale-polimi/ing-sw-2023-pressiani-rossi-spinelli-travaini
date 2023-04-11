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
    private GameState gameState;
    private ArrayList<Player> players;
    private Player playerInTurn;
    public static int MAX_PLAYERS = 4;
    private int chosenPlayersNumber = 0;
    private ObjectsDeck objectsDeck;
    /*private Turn turn;*/


    /**
     * Custom constructor.
     */
    public Game(){
        this.board = new Board();
        this.players = new ArrayList<>();
        this.objectsDeck = new ObjectsDeck();
    }

    /**
     * This method will make it possible to set the maximum number of players in this game.
     * @param chosenMaxPlayers is the number of players allowed in this game.
     * @return will show if the method has worked correctly or not.
     */
    public boolean setMaxPlayers(int chosenMaxPlayers) {
        if ((chosenMaxPlayers > 1 && chosenMaxPlayers <= MAX_PLAYERS)) {
            this.chosenPlayersNumber = chosenMaxPlayers;
            return true;
        }
        return false;
    }

    /**
     * Getter method that returns this game's max players.
     * @return the number of players allowed in this game.
     */
    public int getMaxPlayers(){
        return this.chosenPlayersNumber;
    }

    /**
     * This method sets the first player of the game.
     */
    public void setFirstPlayer(){
        players.get(0).setAsFirst();
    }

    /**
     * Setter method for the state of the game.
     * @param gameState is the new state of the game.
     */
    public void setGameState(GameState gameState){
        this.gameState = gameState;
    }

    /**
     * Getter method that returns the state of the game.
     * @return the current state of this game.
     */
    public GameState getGameState(){
        return this.gameState;
    }

    public Player getPlayerInTurn() {
        return playerInTurn;
    }

    public void setPlayerInTurn(Player playerInTurn) {
        this.playerInTurn = playerInTurn;
    }

    /**
     * Getter method that will return the maximum number of player for this game.
     * @return will return the maximum number of players for this game.
     */
    public int numOfPlayers(){
        return chosenPlayersNumber;
    }

    /**
     * Getter method that returns this board.
     * @return will return the board of this game.
     */
    public Board getBoard() {
        return this.board;
    }

    /**
     * Getter method that returns the players in this game.
     * @return the players in this game.
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     *
     * @return
     */
    public Player getNextPlayer(){
        int currPlayerIndex = getPlayers().indexOf(playerInTurn);
        return getPlayers().get((currPlayerIndex + 1) % getMaxPlayers());
    }

    /**
     *
     */
    public void setNextPlayer(){
        this.playerInTurn = getNextPlayer();
    }

    /**
     * Getter method that returns the deck of this game.
     * @return the deck of this game.
     */
    public ObjectsDeck getObjectsDeck() {
        return objectsDeck;
    }

    /**
     * This method will add a player to the game, if there's enough space.
     * @param player is the player that will be added to this game.
     * @throws TooManyPlayersException if the game has already enough players. The maximum number of players for this game has been set in {@link model.Game#setMaxPlayers(int) setMaxPlayers method}.
     */
    public void addToGame(Player player) throws TooManyPlayersException {
        if(players.size() > chosenPlayersNumber){
            throw new TooManyPlayersException();
        } else if (player.getNickname() == null) {
            throw new NullPointerException();
        } else {
            players.add(player);
        }
    }

    /**
     * This method will check if a player nickname has already been used.
     * @param playerNickname is the username of the player.
     * @return true if the username has already been used, false otherwise.
     */
    public boolean isNicknameTaken(String playerNickname){

        for (int i=0; i < players.size(); i++){
            if(playerNickname.equals(players.get(i).getNickname())){
                return true;
            }
        }return false;
    }

    /**
     * This method will restore the board, filling it with object cards.
     * @param board is the board of this game.
     */
    public void restoreBoard(Board board) {
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                if (!board.getSpace(x, y).getTypeSpace().equals(TypeSpace.UNUSABLE)) {
                    switch (board.getSpace(x, y).getTypeSpace()) {
                        case FOR_FOUR_PLAYERS -> {
                            if (players.size() == 4) {

                                if (board.getSpace(x, y).getObject() == null) {
                                    board.putObjectIn(board.getSpace(x, y), objectsDeck.removeFromDeck());
                                }
                            }
                        }
                        case FOR_THREE_PLAYERS -> {
                            if (players.size() == 3) {
                                if (board.getSpace(x, y).getObject() == null) {
                                    board.putObjectIn(board.getSpace(x, y), objectsDeck.removeFromDeck());
                                }
                            }

                        }
                        case FOR_TWO_PLAYERS -> {
                            if (players.size() == 2) {
                                if (board.getSpace(x, y).getObject() == null) {
                                    board.putObjectIn(board.getSpace(x, y), objectsDeck.removeFromDeck());
                                }
                            }
                        }
                    }
                }
            }
        }
    }


    /**
     * Getter method to return the turn in the game.
     * @return the turn that is currently playing in the game.
     */
    /*
    public Turn getTurn() {
        return turn;
    }
    */

    /**
     * Ends the game.
     */
    public void endGame(){
        gameState = GameState.END;
    }

}

