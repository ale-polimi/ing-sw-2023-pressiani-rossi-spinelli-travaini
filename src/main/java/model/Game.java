package model;

import controller.Controller;
import enumerations.GameState;
import enumerations.TypeSpace;
import exceptions.game.TooManyPlayersException;
import model.board.Board;
import model.commonobjective.CommonObjective;
import model.objects.ObjectsDeck;
import model.player.Player;
import network.messages.AddedPlayerMessage;
import network.messages.Message;
import observer.Observable;
import observer.Observer;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Game extends Observable implements Observer {
    private Board board;
    private GameState gameState;
    private ArrayList<Player> players;
    private Player playerInTurn;
    public static int MAX_PLAYERS = 4;
    private int chosenPlayersNumber = 1;
    private ObjectsDeck objectsDeck;
    private HashMap<CommonObjective, ArrayList<Integer>> commonObjectivesPoints;


    /**
     * Custom constructor.
     */
    public Game() {
        this.board = new Board();
        board.addObserver(this);
        this.players = new ArrayList<>();
        this.objectsDeck = new ObjectsDeck();
        this.commonObjectivesPoints = new HashMap<>(2);
    }

    /**
     * This method adds a common objective and its points to the game.
     * @param commonObjective is a common objective created by the {@link Controller controller} in the {@link Controller#setupCommonObjectives() appropriate method}.
     */
    public void addCommonObjective(CommonObjective commonObjective){
        switch(players.size()){
            case 2:
                commonObjectivesPoints.put(commonObjective, new ArrayList<>(Arrays.asList(8,4)));
                break;
            case 3:
                commonObjectivesPoints.put(commonObjective, new ArrayList<>(Arrays.asList(8,6,4)));
                break;
            case 4:
                commonObjectivesPoints.put(commonObjective, new ArrayList<>(Arrays.asList(8,6,4,2)));
                break;
            default:
                /* TODO - ECCEZIONE */
        }
    }

    /**
     * Getter method to return the common objectives for this game.
     * @return the map of common objectives and relative available points.
     */
    public HashMap<CommonObjective, ArrayList<Integer>> getCommonObjectives() {
        return commonObjectivesPoints;
    }

    /**
     * This method checks if the number of players is within the permitted bounds.
     * @param chosenMaxPlayers is the number of players chose by the first player.
     * @return {@code true} if the number is within the bounds, {@code false} otherwise.
     */
    public boolean isInBounds(int chosenMaxPlayers){
        if (chosenMaxPlayers >= 2 && chosenMaxPlayers <= 4){
            return true;
        } else {
            return false;
        }
    }


    /**
     * This method will set the maximum number of players in this game.
     * @param chosenMaxPlayers is the number of players allowed in this game.
     */
    public void setMaxPlayers(int chosenMaxPlayers) {
        this.chosenPlayersNumber = chosenMaxPlayers;
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
     * This method returns the first player of the game.
     * @return the first {@link Player player} of the game.
     */
    public Player getFirstPlayer() {
        Player firstPlayer = null;
        for (Player player: players){
            if(player.isFirstPlayer()){
                firstPlayer = player;
                break;
            }
        }
        return firstPlayer;
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
     *Returns the player who has to play next
     * @return the next player in the game
     */
    public Player getNextPlayer(){
        int currPlayerIndex = getPlayers().indexOf(playerInTurn);
        return getPlayers().get((currPlayerIndex + 1) % getMaxPlayers());
    }

    /**
     *Setter of the playerInTurn with the player who has to play next
     */
    public void setNextPlayer(){
        this.playerInTurn = getNextPlayer();
        //notifyObserver(new NextTurnMessage());
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
        if(players.size() >= chosenPlayersNumber){
            throw new TooManyPlayersException();
        } else if (player.getNickname() == null) {
            throw new NullPointerException();
        } else {
            players.add(player);
            notifyObserver(new AddedPlayerMessage());
        }
    }

    /**
     * This method will check if a player nickname has already been used.
     * @param playerNickname is the username of the player.
     * @return true if the username has already been used, false otherwise.
     */
    public boolean isNicknameTaken(String playerNickname){

        for (Player player : players) {
            if (playerNickname.equals(player.getNickname())) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method will restore the board, filling it with object cards.
     * @param board is the board of this game.
     */
    public void restoreBoard(Board board) {
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                if(objectsDeck.getAvailableObjects()==0)return;
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
                            if (players.size() >= 3) {
                                if (board.getSpace(x, y).getObject() == null) {
                                    board.putObjectIn(board.getSpace(x, y), objectsDeck.removeFromDeck());
                                }
                            }

                        }
                        case FOR_TWO_PLAYERS -> {
                            if (players.size() >= 2) {
                                if (board.getSpace(x, y).getObject() == null) {
                                    board.putObjectIn(board.getSpace(x, y), objectsDeck.removeFromDeck());
                                }
                            }
                        }
                    }
                }
            }
        }
        //notifyObserver(new GenericModelChangeMessage());
    }

    @Override
    public void update(Message message) {
        System.out.println(this.getClass().toString() + ": I have been notified!");
        notifyObserver(message);
    }

    /**
     * Ends the game.
     */
    public void endGame(){
        gameState = GameState.END;
    }
}

