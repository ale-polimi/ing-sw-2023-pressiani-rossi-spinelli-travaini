package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import enumerations.GameState;
import exceptions.controller.IncompatibleStateException;
import exceptions.controller.NotEnoughSpaceException;
import exceptions.game.TooManyPlayersException;
import exceptions.player.TooManyObjectsInHandException;
import model.Game;
import model.objects.ObjectCard;
import model.player.Player;
import network.GenericErrorMessage;
import network.Message;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import static enumerations.GameState.IN_GAME;
import static enumerations.GameState.LOGIN;
import static enumerations.PlayerState.IN_LIBRARY;
import static enumerations.PlayerState.PICKUP;
import static model.player.Player.MAX_OBJECTS_IN_HAND;
import static network.MessageType.*;

public class Controller {

    private Game game;
    private HashMap<String, Integer> playersPoints;

    private HashMap personalObjectives;
    int firstX, firstY, secondX, secondY, thirdX, thirdY = 0;

    /**
     * Constructor for the controller.
     */
    public Controller() throws IOException {
        this.game = new Game();
        game.setGameState(LOGIN);
        byte[] jsonData = Files.readAllBytes(Paths.get("src/main/java/personalObjectives.json"));
        ObjectMapper objectMapper = new ObjectMapper();
        personalObjectives = objectMapper.readValue(jsonData,HashMap.class);
    }


    /**
     *
     * @param receivedMessage
     */
    public void onMessageReceived(Message receivedMessage) {
        switch (receivedMessage.getType()) {
            case MAX_PLAYERS_FOR_GAME:

                if (game.getGameState().equals(LOGIN)) {
                    if (game.getMaxPlayers() > 0) {
                        new GenericErrorMessage("The players for this game are already set.");
                    } else if (game.setMaxPlayers(Integer.parseInt(receivedMessage.getPayload())) == false) {
                        new GenericErrorMessage("The number is not within the correct bounds. It must be 2 <= players <= " + Game.MAX_PLAYERS);
                    }
                } else {
                    /* Eccezione o messaggio d'errore? */
                }

            case USER_INFO:

                if (game.getGameState().equals(LOGIN)) {
                    if (game.isNicknameTaken(receivedMessage.getPayload()) == true) {
                        new GenericErrorMessage("Username is already taken.");
                    } else {

                        try {
                            Random rand = new Random();
                            game.addToGame(new Player(receivedMessage.getPayload(), (String) personalObjectives.remove(String.valueOf(rand.nextInt(personalObjectives.size())))));
                        } catch (TooManyPlayersException exception) {
                            new GenericErrorMessage(exception.getMessage());
                        }

                        if (game.getPlayers().size() == game.getMaxPlayers()) {
                            initGame(game);
                        }
                    }
                } else {
                    /* TODO - Invalid game state */
                }

            case PICK_OBJECT:

                if (game.getGameState().equals(IN_GAME)) {

                    if (!(game.getPlayerInTurn().hasObjectsInHand())) {
                        game.getPlayerInTurn().initObjectsInHand();
                    }

                    switch (receivedMessage.getPayload().length()) {

                        case 2:
                            firstX = Character.getNumericValue(receivedMessage.getPayload().charAt(0));
                            firstY = Character.getNumericValue(receivedMessage.getPayload().charAt(1));
                            try {
                                pickObjectFromBoard(firstX, firstY);
                            } catch (IncompatibleStateException e) {
                                new GenericErrorMessage(e.getMessage());
                            }
                            break;
                        case 4:
                            firstX = Character.getNumericValue(receivedMessage.getPayload().charAt(0));
                            firstY = Character.getNumericValue(receivedMessage.getPayload().charAt(1));
                            secondX = Character.getNumericValue(receivedMessage.getPayload().charAt(2));
                            secondY = Character.getNumericValue(receivedMessage.getPayload().charAt(3));

                            if (secondX != firstX || secondY != firstY) {
                                new GenericErrorMessage("You must pick objects from the same row or column!");
                            } else {

                                try {
                                    pickObjectFromBoard(firstX, firstY);
                                    pickObjectFromBoard(secondX, secondY);
                                } catch (IncompatibleStateException e) {
                                    new GenericErrorMessage(e.getMessage());
                                }

                            }
                            break;
                        case 6:
                            firstX = Character.getNumericValue(receivedMessage.getPayload().charAt(0));
                            firstY = Character.getNumericValue(receivedMessage.getPayload().charAt(1));
                            secondX = Character.getNumericValue(receivedMessage.getPayload().charAt(2));
                            secondY = Character.getNumericValue(receivedMessage.getPayload().charAt(3));
                            thirdX = Character.getNumericValue(receivedMessage.getPayload().charAt(4));
                            thirdY = Character.getNumericValue(receivedMessage.getPayload().charAt(5));


                            if (!((firstX == secondX && firstX == thirdX && secondX == thirdX) || (firstY == secondY && firstY == thirdY && secondY == thirdY))) {
                                new GenericErrorMessage("You must pick objects from the same row or column!");
                            } else {
                                try {
                                    pickObjectFromBoard(firstX, firstY);
                                    pickObjectFromBoard(secondX, secondY);
                                    pickObjectFromBoard(thirdX, thirdY);
                                } catch (IncompatibleStateException e) {
                                    new GenericErrorMessage(e.getMessage());
                                }
                            }
                            break;
                        default:
                            new GenericErrorMessage("Coordinates must be in pairs.");
                    }

                    /* After picking up the objects, the player will go to his library */
                    game.getPlayerInTurn().setPlayerState(IN_LIBRARY);

                } else {
                    /* TODO - Invalid game state */
                }

            case PUT_OBJECT:

                if (game.getGameState().equals(IN_GAME)) {
                    resetCoordinateValues();

                    if (receivedMessage.getPayload().length() - 1 < game.getPlayerInTurn().getObjectsInHandSize()) {
                        new GenericErrorMessage("You must put all the objects you have in hand in the library.");
                    } else if (receivedMessage.getPayload().length() - 1 > game.getPlayerInTurn().getObjectsInHandSize()) {
                        int value = receivedMessage.getPayload().length() - 1;
                        new GenericErrorMessage("You do not have " + value + " objects in hand. You have only: " + game.getPlayerInTurn().getObjectsInHandSize());
                    } else if (receivedMessage.getPayload().length() - 1 == game.getPlayerInTurn().getObjectsInHandSize()) {
                        switch (receivedMessage.getPayload().length()) {
                            case 2 -> {
                                try {
                                    addObjectToLibrary(Character.getNumericValue(receivedMessage.getPayload().charAt(0)), Character.getNumericValue(receivedMessage.getPayload().charAt(1)));

                                    /* Resetting the player's objects in hand, so they'll start from scratch in the next turn */
                                    game.getPlayerInTurn().resetObjectsInHand();

                                    /* The player must be in PICKUP state for the next turn, else he won't be able to pick any object */
                                    game.getPlayerInTurn().setPlayerState(PICKUP);

                                    /* Check if the player has completed a common objective */


                                    if (checkLibrarySpaces() == 0) {
                                        game.getPlayerInTurn().setFirstToEnd(true);
                                    }

                                    if (isLastTurn()) {
                                        endGame(game);
                                    }

                                    game.setNextPlayer();

                                } catch (NotEnoughSpaceException | IncompatibleStateException e) {
                                    new GenericErrorMessage(e.getMessage());
                                }
                            }
                            case 3 -> {
                                try {
                                    addObjectToLibrary(Character.getNumericValue(receivedMessage.getPayload().charAt(0)), Character.getNumericValue(receivedMessage.getPayload().charAt(1)), Character.getNumericValue(receivedMessage.getPayload().charAt(2)));

                                    /* Resetting the player's objects in hand, so they'll start from scratch in the next turn */
                                    game.getPlayerInTurn().resetObjectsInHand();

                                    /* The player must be in PICKUP state for the next turn, else he won't be able to pick any object */
                                    game.getPlayerInTurn().setPlayerState(PICKUP);

                                    if (checkLibrarySpaces() == 0) {
                                        game.getPlayerInTurn().setFirstToEnd(true);
                                    }

                                    if (isLastTurn()) {
                                        endGame(game);
                                    }

                                    game.setNextPlayer();

                                } catch (NotEnoughSpaceException | IncompatibleStateException e) {
                                    new GenericErrorMessage(e.getMessage());
                                }
                            }
                            case 4 -> {
                                try {
                                    addObjectToLibrary(Character.getNumericValue(receivedMessage.getPayload().charAt(0)), Character.getNumericValue(receivedMessage.getPayload().charAt(1)), Character.getNumericValue(receivedMessage.getPayload().charAt(2)), Character.getNumericValue(receivedMessage.getPayload().charAt(3)));

                                    /* Resetting the player's objects in hand, so they'll start from scratch in the next turn */
                                    game.getPlayerInTurn().resetObjectsInHand();

                                    /* The player must be in PICKUP state for the next turn, else he won't be able to pick any object */
                                    game.getPlayerInTurn().setPlayerState(PICKUP);

                                    if (checkLibrarySpaces() == 0) {
                                        game.getPlayerInTurn().setFirstToEnd(true);
                                    }

                                    if (isLastTurn()) {
                                        endGame(game);
                                    }

                                    game.setNextPlayer();

                                } catch (NotEnoughSpaceException | IncompatibleStateException e) {
                                    new GenericErrorMessage(e.getMessage());
                                }
                            }
                            default -> new GenericErrorMessage("Invalid number of objects.");
                        }
                    }
                } else {
                    /* TODO - Invalid game state */
                }

            default:
                /* TODO - INVALID MESSAGE TYPE */
        }
    }

    /**
     * This method initializes the game controlled by this controller. It can be called only after all the required players have been added to the game.
     * @param game is the game of this controller.
     */
    private void initGame(Game game){
        game.restoreBoard(game.getBoard());
        game.setFirstPlayer();
        /* TODO - Init common objectives */
        game.setGameState(IN_GAME);
    }

    /**
     * This method ends the game and checks the points of the players, declaring the winner.
     * @param game is the game of this controller.
     */
    private void endGame(Game game){
        if(game.getNextPlayer().equals(game.getPlayers().get(0))){
            game.setGameState(GameState.END);
            checkPoints();
            declareWinner();
        }
    }

    /**
     * This method checks the points of this game.
     */
    private void checkPoints(){
        for(int i = 0; i < game.getMaxPlayers(); i++){
            playersPoints.put(game.getPlayers().get(i).getNickname(), game.getPlayers().get(i).getPoints());
        }
    }

    /**
     * This method will declare the winner of the game.
     * @return the username of the winner.
     */
    private String declareWinner(){
        int maxPoints = Collections.max(playersPoints.values());
        List<String> usernames = playersPoints.entrySet().stream()
                                                         .filter(entry -> entry.getValue() == maxPoints)
                                                         .map(entry -> entry.getKey())
                                                         .toList();

        if(usernames.size() > 1){
            /* This means that there are players with the same number of points */
            int furthestPlayerIndex = 0;
            for(int i = 0; i < game.getMaxPlayers(); i++){
                if(usernames.contains(game.getPlayers().get(i).getNickname())){
                    if(i > furthestPlayerIndex){
                        furthestPlayerIndex = i;
                    }
                }
            }

            return usernames.get(furthestPlayerIndex);
        } else {
            return usernames.get(0);
        }

    }

    /**
     * This method will check if the game is in its final turn.
     * @return {@code true} if the game is in its final turn, {@code false} otherwise.
     */
    private boolean isLastTurn(){
        for(int i = 0; i < game.getMaxPlayers(); i++){
            if(game.getPlayers().get(i).isFirstToEnd()){
                return true;
            }
        }
        return false;
    }

    /**
     * This method will count the number of free spaces in a player's library. It is used to see whether a player has finished as first.
     * @return the number of free spaces in the player's library.
     */
    private int checkLibrarySpaces(){
        int freeSpaces = 0;

        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 5; j++){
                if(game.getPlayerInTurn().getLibrary().getObject(game.getPlayerInTurn().getLibrary().getLibrarySpace(i, j)) == null){
                    freeSpaces++;
                }
            }
        }

        return freeSpaces;
    }

    /**
     * Resets the coordinates values for the following turn.
     */
    private void resetCoordinateValues(){
        firstX = 0;
        firstY = 0;
        secondX = 0;
        secondY = 0;
        thirdX = 0;
        thirdY = 0;
    }


    /**
     * The player will select the card to pick from the board one by one
     * @param coordX is the X coordinate of the card on the board.
     * @param coordY is the Y coordinate of the card on the board.
     * @throws IncompatibleStateException if the player is not in PICKUP state.
     */
    public boolean pickObjectFromBoard(int coordX, int coordY) throws IncompatibleStateException{
        if(game.getPlayerInTurn().getPlayerState().equals(PICKUP)) {
            if (game.getBoard().getSpace(coordX, coordY).getObject() != null) {
                if(game.getBoard().isSpaceSurrounded(coordX, coordY)){
                    return false;
                } else {

                    try {
                        game.getPlayerInTurn().addToObjectsInHand(game.getBoard().getSpace(coordX, coordY).getObject());
                        game.getBoard().getSpace(coordX, coordY).removeObject();
                        return true;
                    } catch (TooManyObjectsInHandException e) {
                        return false;
                    }

                }
            } else {
                return false;
            }
        } else {
            throw new IncompatibleStateException(PICKUP, game.getPlayerInTurn().getPlayerState());
        }
    }

    /**
     * The player can select in which order the object cards have to be put in the specified column
     * @param cardID1 is the first card to be put in the specified column.
     * @param column is the player specified column.
     * @return true if the cards are correctly put in the library.
     * @throws IncompatibleStateException if the player is not in IN_LIBRARY state.
     * @throws NotEnoughSpaceException if the chosen column does not have enough space for the desired cards.
     */
    private boolean addObjectToLibrary(int cardID1, int column) throws IncompatibleStateException, NotEnoughSpaceException{
        if(game.getPlayerInTurn().getPlayerState().equals(IN_LIBRARY)) {
            ObjectCard objectCard1 = game.getPlayerInTurn().getObjectInHand(cardID1);
            int firstEmpty = 0;

            for (int i = 5; i >= 0; i--) {
                if (game.getPlayerInTurn().getLibrary().getObject(game.getPlayerInTurn().getLibrary().getLibrarySpace(i, column)) == null) {
                    firstEmpty = i;
                    break;
                }
            }
            if (firstEmpty != 0) {
                game.getPlayerInTurn().getLibrary().addObject(objectCard1, game.getPlayerInTurn().getLibrary().getLibrarySpace(firstEmpty, column));
                return true;
            } else {
                throw new NotEnoughSpaceException(column);
            }
        } else {
            throw new IncompatibleStateException(IN_LIBRARY, game.getPlayerInTurn().getPlayerState());
        }
    }

    /**
     * The player can select in which order the object cards have to be put in the specified column
     * @param cardID1 is the first card to be put in the specified column.
     * @param cardID2 is the second card to be put in the specified column.
     * @param column is the player specified column.
     * @return true if the cards are correctly put in the library.
     * @throws IncompatibleStateException if the player is not in IN_LIBRARY state.
     * @throws NotEnoughSpaceException if the chosen column does not have enough space for the desired cards.
     */
    private boolean addObjectToLibrary(int cardID1, int cardID2, int column) throws IncompatibleStateException, NotEnoughSpaceException{
        if(game.getPlayerInTurn().getPlayerState().equals(IN_LIBRARY)) {
            ObjectCard objectCard1 = game.getPlayerInTurn().getObjectInHand(cardID1);
            ObjectCard objectCard2 = game.getPlayerInTurn().getObjectInHand(cardID2);
            int firstEmpty = 0;

            for (int i = 5; i >= 0; i--) {
                if (game.getPlayerInTurn().getLibrary().getObject(game.getPlayerInTurn().getLibrary().getLibrarySpace(i, column)) == null) {
                    firstEmpty = i;
                    break;
                }
            }
            if (!((firstEmpty - 1 < 0) || (firstEmpty - 2 < 0))) {
                game.getPlayerInTurn().getLibrary().addObject(objectCard1, game.getPlayerInTurn().getLibrary().getLibrarySpace(firstEmpty, column));
                game.getPlayerInTurn().getLibrary().addObject(objectCard2, game.getPlayerInTurn().getLibrary().getLibrarySpace(firstEmpty - 1, column));
                return true;
            } else {
                throw new NotEnoughSpaceException(column);
            }
        } else {
            throw new IncompatibleStateException(IN_LIBRARY, game.getPlayerInTurn().getPlayerState());
        }
    }

    /**
     * The player can select in which order the object cards have to be put in the specified column
     * @param cardID1 is the first card to be put in the specified column.
     * @param cardID2 is the second card to be put in the specified column.
     * @param cardID3 is the third card to be put in the specified column.
     * @param column is the player specified column.
     * @return true if the cards are correctly put in the library.
     * @throws IncompatibleStateException if the player is not in IN_LIBRARY state.
     * @throws NotEnoughSpaceException if the chosen column does not have enough space for the desired cards.
     */
    private boolean addObjectToLibrary(int cardID1, int cardID2, int cardID3, int column) throws IncompatibleStateException, NotEnoughSpaceException{
        if(game.getPlayerInTurn().getPlayerState().equals(IN_LIBRARY)) {
            ObjectCard objectCard1 = game.getPlayerInTurn().getObjectInHand(cardID1);
            ObjectCard objectCard2 = game.getPlayerInTurn().getObjectInHand(cardID2);
            ObjectCard objectCard3 = game.getPlayerInTurn().getObjectInHand(cardID3);
            int firstEmpty = 0;

            for (int i = 5; i >= 0; i--) {
                if (game.getPlayerInTurn().getLibrary().getObject(game.getPlayerInTurn().getLibrary().getLibrarySpace(i, column)) == null) {
                    firstEmpty = i;
                    break;
                }
            }
            if (!((firstEmpty - 1 < 0) || (firstEmpty - 2 < 0))) {
                game.getPlayerInTurn().getLibrary().addObject(objectCard1, game.getPlayerInTurn().getLibrary().getLibrarySpace(firstEmpty, column));
                game.getPlayerInTurn().getLibrary().addObject(objectCard2, game.getPlayerInTurn().getLibrary().getLibrarySpace(firstEmpty - 1, column));
                game.getPlayerInTurn().getLibrary().addObject(objectCard3, game.getPlayerInTurn().getLibrary().getLibrarySpace(firstEmpty - 2 , column));
                return true;
            } else {
                throw new NotEnoughSpaceException(column);
            }
        } else {
            throw new IncompatibleStateException(IN_LIBRARY, game.getPlayerInTurn().getPlayerState());
        }
    }
}
