package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import enumerations.GameState;
import enumerations.ObjectColour;
import enumerations.TypeSpace;
import exceptions.controller.IncompatibleStateException;
import exceptions.controller.NotEnoughSpaceException;
import exceptions.game.TooManyPlayersException;
import exceptions.player.TooManyObjectsInHandException;
import model.Game;
import model.commonobjective.*;
import model.objects.ObjectCard;
import model.player.Player;
import network.*;
import network.structure.NetworkView;
import network.structure.StartServerImpl;
import observer.Observer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static enumerations.GameState.IN_GAME;
import static enumerations.GameState.LOGIN;
import static enumerations.PlayerState.IN_LIBRARY;
import static enumerations.PlayerState.PICKUP;

/**
 * This class will control the flow of the game.
 * Broadly speaking, it will receive a message from the network then it will update the model and
 * it will finally send a response back to the client with the updated model.
 * This stays server side.
 */
public class Controller implements Observer {

    private Game game;
    private HashMap<String, Integer> playersPoints;
    private String winner;
    private HashMap personalObjectives;
    private HashMap<Integer, CommonObjective> availableCommonObjectives;
    int firstRow, firstCol, secondRow, secondCol, thirdRow, thirdCol = 0;
    private final NetworkView networkView;

    /**
     * Constructor for the controller.
     */
    public Controller(StartServerImpl server) throws IOException {
        this.game = new Game();
        /* Subscribing the Controller to the Model (Game) */
        game.addObserver(this);
        networkView = new NetworkView(server);
        game.setGameState(LOGIN);
        byte[] jsonData = Files.readAllBytes(Paths.get("src/main/java/personalObjectives.json"));
        ObjectMapper objectMapper = new ObjectMapper();
        personalObjectives = objectMapper.readValue(jsonData,HashMap.class);

        /* Creating all the common objectives; when the game starts we will randomly select only two */
        availableCommonObjectives = new HashMap<>();
        availableCommonObjectives.put(0, new Diagonal());
        availableCommonObjectives.put(1, new EightEquals());
        availableCommonObjectives.put(2, new FiveX());
        availableCommonObjectives.put(3, new FourByFourNew());
        availableCommonObjectives.put(4, new FourCorners());
        availableCommonObjectives.put(5, new FourRowsMaxThreeDifferent());
        availableCommonObjectives.put(6, new SixByTwo());
        availableCommonObjectives.put(7, new Stairs());
        availableCommonObjectives.put(8, new ThreeColumnsMaxThreeDifferent());
        availableCommonObjectives.put(9, new TotalDifferentColumns());
        availableCommonObjectives.put(10, new TotalDifferentRows());
        availableCommonObjectives.put(11, new TwoByFour());
        availableCommonObjectives.put(12, new TwoEqualsInColumn());
    }


    /**
     * Main method for the controller that will manage the course of the game.
     * @param receivedMessage is the message received from the "client".
     */
    public void onMessageReceived(Message receivedMessage) {
        /* TODO - Debug print */
        System.out.println("Ricevuto messaggio da: " + receivedMessage.getSender() + " Di tipo: " + receivedMessage.getType().toString());
        switch (receivedMessage.getType()) {
            /* TODO - Controllo se il client che manda (message.getsender) è il giocatore che deve giocare (game.getcurrentplayer) */
            case MAX_PLAYERS_FOR_GAME:
                MaxPlayersMessage maxPlayersMessage = (MaxPlayersMessage) receivedMessage;
                if (game.getGameState().equals(LOGIN)) {
                    if (game.getMaxPlayers() > 1) {
                        this.update(new GenericErrorMessage(maxPlayersMessage.getSender(), "The players for this game are already set."));
                    } else if (!game.setMaxPlayers(maxPlayersMessage.getPlayers())) {
                        this.update(new GenericErrorMessage(maxPlayersMessage.getSender(), "The number is not within the correct bounds. It must be 2 <= players <= " + Game.MAX_PLAYERS));
                        this.update(new AskMaxPlayerMessage(this.getGame().getPlayers().get(0).getNickname()));
                    }
                } else {
                    this.update(new GenericErrorMessage(maxPlayersMessage.getSender(), "This message type: " + receivedMessage.getType().toString() + " is not available for this game state: " + game.getGameState().toString()));
                }
                ArrayList<String> players = new ArrayList<>();
                for (Player p : game.getPlayers()) players.add(p.getNickname());
                this.update(new ShowLobbyMessage(players));
                break;
            case USER_INFO:
                assert receivedMessage instanceof UserInfoForLoginMessage;
                UserInfoForLoginMessage userInfoForLoginMessage = (UserInfoForLoginMessage) receivedMessage;
                if (game.getGameState().equals(LOGIN)) {
                    if (game.isNicknameTaken(userInfoForLoginMessage.getUsername())) {
                        this.update(new GenericErrorMessage(userInfoForLoginMessage.getSender(), "Username is already taken."));
                        this.update(new AskNicknameMessage("Controller"));
                    } else {
                        try {
                            Random rand = new Random();
                            game.addToGame(new Player(userInfoForLoginMessage.getUsername(), (String) personalObjectives.remove(String.valueOf(rand.nextInt(personalObjectives.size())))));
                        } catch (TooManyPlayersException exception) {
                            this.update(new GenericErrorMessage(userInfoForLoginMessage.getSender(), exception.getMessage()));
                        }
                        if (game.getMaxPlayers() != 1 && game.getPlayers().size() == game.getMaxPlayers()) {
                            initGame(game);
                        }
                    }
                } else {
                    this.update(new GenericErrorMessage(userInfoForLoginMessage.getSender(), "This message type: " + receivedMessage.getType().toString() + " is not available for this game state: " + game.getGameState().toString()));
                }
                break;
            case PICK_OBJECT:

                PickObjectMessage pickObjectMessage = (PickObjectMessage) receivedMessage;

                if (game.getGameState().equals(IN_GAME)) {

                    if (!(game.getPlayerInTurn().hasObjectsInHand())) {
                        game.getPlayerInTurn().initObjectsInHand();
                    }

                    switch (pickObjectMessage.getCoordinates().size()) {

                        case 2:
                            firstRow = pickObjectMessage.getCoordinates().get(0);
                            firstCol = pickObjectMessage.getCoordinates().get(1);
                            try {
                                pickObjectFromBoard(firstRow, firstCol);
                                this.update(new GenericModelChangeMessage());
                            } catch (IncompatibleStateException e) {
                                this.update(new GenericErrorMessage(game.getPlayerInTurn().getNickname(), e.getMessage()));
                            }
                            break;
                        case 4:
                            firstRow = pickObjectMessage.getCoordinates().get(0);
                            firstCol = pickObjectMessage.getCoordinates().get(1);
                            secondRow = pickObjectMessage.getCoordinates().get(2);
                            secondCol = pickObjectMessage.getCoordinates().get(3);

                            if (!(firstRow == secondRow && areAdjacentColumns(firstCol, secondCol)) && !(firstCol == secondCol && areAdjacentRows(firstRow, secondRow))) {
                                this.update(new GenericErrorMessage(game.getPlayerInTurn().getNickname(), "You must pick objects from the same row or column!"));
                            } else {

                                try {
                                    pickObjectFromBoard(firstRow, firstCol);
                                    pickObjectFromBoard(secondRow, secondCol);
                                    this.update(new GenericModelChangeMessage());
                                } catch (IncompatibleStateException e) {
                                    this.update(new GenericErrorMessage(game.getPlayerInTurn().getNickname(), e.getMessage()));
                                }

                            }
                            break;
                        case 6:
                            firstRow = pickObjectMessage.getCoordinates().get(0);
                            firstCol = pickObjectMessage.getCoordinates().get(1);
                            secondRow = pickObjectMessage.getCoordinates().get(2);
                            secondCol = pickObjectMessage.getCoordinates().get(3);
                            thirdRow = pickObjectMessage.getCoordinates().get(4);
                            thirdCol = pickObjectMessage.getCoordinates().get(5);


                            if (!(firstRow == secondRow && secondRow == thirdRow && ((areAdjacentColumns(firstCol, secondCol) && areAdjacentColumns(secondCol, thirdCol)) || (areAdjacentColumns(firstCol, secondCol) && areAdjacentColumns(firstCol, thirdCol)) || (areAdjacentColumns(firstCol, thirdCol) && areAdjacentColumns(secondCol, thirdCol)))) && !(firstCol == secondCol && secondCol == thirdCol && ((areAdjacentRows(firstRow, secondRow) && areAdjacentRows(secondRow, thirdRow)) || (areAdjacentRows(firstRow, secondRow) && areAdjacentRows(firstRow, thirdRow)) || (areAdjacentRows(firstRow, thirdRow) && areAdjacentRows(secondRow, thirdRow))))) {
                                this.update(new GenericErrorMessage(game.getPlayerInTurn().getNickname(), "You must pick objects from the same row or column!"));
                            } else {
                                try {
                                    pickObjectFromBoard(firstRow, firstCol);
                                    pickObjectFromBoard(secondRow, secondCol);
                                    pickObjectFromBoard(thirdRow, thirdCol);
                                    this.update(new GenericModelChangeMessage());
                                } catch (IncompatibleStateException e) {
                                    this.update(new GenericErrorMessage(game.getPlayerInTurn().getNickname(), e.getMessage()));
                                }
                            }
                            break;
                        default:
                            this.update(new GenericErrorMessage(game.getPlayerInTurn().getNickname(), "Coordinates must be in pairs."));
                    }

                    /* After picking up the objects, the player will go to his library */
                    game.getPlayerInTurn().setPlayerState(IN_LIBRARY);

                } else {
                    this.update(new GenericErrorMessage(game.getPlayerInTurn().getNickname(), "This message type: " + receivedMessage.getType().toString() + " is not available for this game state: " + game.getGameState().toString()));
                }
                break;
            case PUT_OBJECT:

                PutObjectInLibraryMessage putObjectInLibraryMessage = (PutObjectInLibraryMessage) receivedMessage;

                if (game.getGameState().equals(IN_GAME)) {
                    resetCoordinateValues();

                    if (putObjectInLibraryMessage.getOrderArray().size() - 1 < game.getPlayerInTurn().getObjectsInHandSize()) {
                        this.update(new GenericErrorMessage(game.getPlayerInTurn().getNickname(), "You must put all the objects you have in hand in the library."));
                    } else if (putObjectInLibraryMessage.getOrderArray().size() - 1 > game.getPlayerInTurn().getObjectsInHandSize()) {
                        int value = putObjectInLibraryMessage.getOrderArray().size() - 1;
                        this.update(new GenericErrorMessage(game.getPlayerInTurn().getNickname(), "You do not have " + value + " objects in hand. You have only: " + game.getPlayerInTurn().getObjectsInHandSize()));
                    } else if (putObjectInLibraryMessage.getOrderArray().size() - 1 == game.getPlayerInTurn().getObjectsInHandSize()) {
                        switch (putObjectInLibraryMessage.getOrderArray().size()) {
                            case 2 -> {
                                try {
                                    addObjectToLibrary(putObjectInLibraryMessage.getOrderArray().get(0), putObjectInLibraryMessage.getOrderArray().get(1));

                                    /* Resetting the player's objects in hand, so they'll start from scratch in the next turn */
                                    game.getPlayerInTurn().initObjectsInHand();

                                    /* The player must be in PICKUP state for the next turn, else he won't be able to pick any object */
                                    game.getPlayerInTurn().setPlayerState(PICKUP);

                                    /* Check if the player has completed a common objective */
                                    checkCommonObjectives();

                                    if (checkLibrarySpaces() == 0) {
                                        game.getPlayerInTurn().setFirstToEnd(true);
                                    }

                                    if (isLastTurn()) {
                                        endGame(game);
                                    }

                                    game.setNextPlayer();

                                } catch (NotEnoughSpaceException | IncompatibleStateException e) {
                                    this.update(new GenericErrorMessage(game.getPlayerInTurn().getNickname(), e.getMessage()));
                                }
                            }
                            case 3 -> {
                                try {
                                    addObjectToLibrary(putObjectInLibraryMessage.getOrderArray().get(0), putObjectInLibraryMessage.getOrderArray().get(1), putObjectInLibraryMessage.getOrderArray().get(2));

                                    /* Resetting the player's objects in hand, so they'll start from scratch in the next turn */
                                    game.getPlayerInTurn().initObjectsInHand();

                                    /* The player must be in PICKUP state for the next turn, else he won't be able to pick any object */
                                    game.getPlayerInTurn().setPlayerState(PICKUP);

                                    /* Check if the player has completed a common objective */
                                    checkCommonObjectives();

                                    if (checkLibrarySpaces() == 0) {
                                        game.getPlayerInTurn().setFirstToEnd(true);
                                    }

                                    if (isLastTurn()) {
                                        endGame(game);
                                    }

                                    game.setNextPlayer();

                                } catch (NotEnoughSpaceException | IncompatibleStateException e) {
                                    this.update(new GenericErrorMessage(game.getPlayerInTurn().getNickname(), e.getMessage()));
                                }
                            }
                            case 4 -> {
                                try {
                                    addObjectToLibrary(putObjectInLibraryMessage.getOrderArray().get(0), putObjectInLibraryMessage.getOrderArray().get(1), putObjectInLibraryMessage.getOrderArray().get(2), putObjectInLibraryMessage.getOrderArray().get(3));

                                    /* Resetting the player's objects in hand, so they'll start from scratch in the next turn */
                                    game.getPlayerInTurn().initObjectsInHand();

                                    /* The player must be in PICKUP state for the next turn, else he won't be able to pick any object */
                                    game.getPlayerInTurn().setPlayerState(PICKUP);

                                    /* Check if the player has completed a common objective */
                                    checkCommonObjectives();

                                    if (checkLibrarySpaces() == 0) {
                                        game.getPlayerInTurn().setFirstToEnd(true);
                                    }

                                    if (isLastTurn()) {
                                        endGame(game);
                                    }

                                    game.setNextPlayer();

                                } catch (NotEnoughSpaceException | IncompatibleStateException e) {
                                    this.update(new GenericErrorMessage(game.getPlayerInTurn().getNickname(), e.getMessage()));
                                }
                            }
                            default ->
                                    this.update(new GenericErrorMessage(game.getPlayerInTurn().getNickname(), "Invalid number of objects."));
                        }
                    }
                } else {
                    this.update(new GenericErrorMessage(game.getPlayerInTurn().getNickname(), "This message type: " + receivedMessage.getType().toString() + " is not available for this game state: " + game.getGameState().toString()));
                }
                break;
            default:
                this.update(new GenericErrorMessage(game.getPlayerInTurn().getNickname(), "Message type: " + receivedMessage.getType().toString() + " is not valid."));
        }
    }

    /**
     * This method initializes the game controlled by this controller. It can be called only after all the required players have been added to the game.
     * @param game is the game of this controller.
     */
    private void initGame(Game game){
        game.setFirstPlayer();
        game.setPlayerInTurn(game.getFirstPlayer());
        setupCommonObjectives();
        /* Subscribe to the players' updates */
        for (Player player:
             game.getPlayers()) {
            player.addObserver(this);
        }
        /* Sends the common objectives and the personal objectives to all the connected players. */
        for(Player player: game.getPlayers()){
            this.update(new ShowCommonObjectiveMessage(player.getNickname(), (CommonObjective) game.getCommonObjectives().keySet().toArray()[0], (CommonObjective) game.getCommonObjectives().keySet().toArray()[1]));
            this.update(new ShowPersonalObjectiveMessage(player.getNickname(), player.getPersonalObjective()));
        }

        for(Player player: game.getPlayers()) {
            if (player.equals(game.getPlayerInTurn())) {
                player.setPlayerState(PICKUP);
            } else {
                player.setPlayerState(IN_LIBRARY);
            }
        }
        /* TODO - Debug print */
        System.out.println("Player in turn is: " + game.getPlayerInTurn().getNickname());
        game.restoreBoard(game.getBoard());

        game.setGameState(IN_GAME);
    }

    /**
     * This method will create the common objectives for the game.
     */
    private void setupCommonObjectives(){
        Random rand1 = new Random();
        Random rand2;
        do{
            rand2 = new Random();
        } while(rand1 == rand2);

        /* First I get the objectives from the hashmap */
        CommonObjective objective1 = availableCommonObjectives.remove(rand1.nextInt(availableCommonObjectives.size()));
        CommonObjective objective2 = availableCommonObjectives.remove(rand2.nextInt(availableCommonObjectives.size()));

        /* Then I set their numeral */
        objective1.setObjectiveNumeral(ObjectiveNumeral.ONE);
        objective2.setObjectiveNumeral(ObjectiveNumeral.TWO);

        /* Finally I add the objectives to the game */
        game.addCommonObjective(objective1);
        game.addCommonObjective(objective2);
    }


    /**
     * This method will add the common objective points to the player's points.
     */
    private void checkCommonObjectives() {
        for (CommonObjective commonObjective : game.getCommonObjectives().keySet()) {
            /* We apply the common objective only if it still has some points left */
            if(game.getCommonObjectives().get(commonObjective).size() > 0) {
                /* If the player hasn't already completed the objective, it will get the points */
                if(game.getPlayerInTurn().getCompletedCommonObjectives()[commonObjective.getObjectiveNumeral()] == false) {
                    if (commonObjective.applyObjectiveRules(game.getPlayerInTurn().getLibrary(), 0, 0) == true) {
                        int points = game.getCommonObjectives().get(commonObjective).remove(0);
                        game.getPlayerInTurn().addPoints(points);
                        game.getPlayerInTurn().setCompletedCommonObjectiveType(commonObjective);
                    }
                }
            }
        }
    }

    /**
     * This method ends the game and checks the points of the players, declaring the winner.
     * @param game is the game of this controller.
     */
    private void endGame(Game game){
        if(game.getNextPlayer().equals(game.getPlayers().get(0))){
            game.setGameState(GameState.END);
            calcPoints();
            getPointsAndUsernames();
            this.winner = declareWinner();
            this.update(new EndGameMessage(winner, playersPoints));
        }
    }

    /**
     * This method will calculate the points each player has made in the game.
     */
    private void calcPoints(){
        int personalObjectivePoints = 0;
        int boardPoints = 0;

        for (Player player : game.getPlayers()) {

            /* Points for the personal objective */
            personalObjectivePoints = player.getPersonalObjective().compareTo(player.getLibrary());


            for(int i = 0; i < ObjectColour.values().length; i += 3){
                /* Points for the adjacent objects cards */
                boardPoints += player.getBoardPoints(ObjectColour.values()[i]);
            }

            player.addPoints(personalObjectivePoints + boardPoints);
        }
    }

    /**
     * This method checks the points of this game.
     */
    private void getPointsAndUsernames(){
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
        firstRow = 0;
        firstCol = 0;
        secondRow = 0;
        secondCol = 0;
        thirdRow = 0;
        thirdCol = 0;
    }

    /**
     * This method checks if two columns are next to each other.
     * @param firstCol is the first column.
     * @param secondCol is the second column.
     * @return {@code true} if the two columns are next to each other, {@code false} otherwise.
     */
    private boolean areAdjacentColumns(int firstCol, int secondCol) {
        if (firstCol < secondCol) {
            if(secondCol == firstCol + 1){
                return true;
            } else {
                return false;
            }
        } else if (firstCol > secondCol) {
            if(firstCol == secondCol + 1){
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }

    /**
     * This method checks if two rows are next to each other.
     * @param firstRow is the first row.
     * @param secondRow is the second row.
     * @return {@code true} if the two rows are next to each other, {@code false} otherwise.
     */
    private boolean areAdjacentRows(int firstRow, int secondRow) {
        if (firstRow < secondRow) {
            if(secondRow == firstRow + 1){
                return true;
            } else {
                return false;
            }
        } else if (firstRow > secondRow) {
            if(firstRow == secondRow + 1){
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }


    /**
     * The player will select the card to pick from the board one by one
     * @param coordX is the X coordinate of the card on the board.
     * @param coordY is the Y coordinate of the card on the board.
     * @throws IncompatibleStateException if the player is not in PICKUP state.
     */
    private void pickObjectFromBoard(int coordX, int coordY) throws IncompatibleStateException{
        if(game.getPlayerInTurn().getPlayerState().equals(PICKUP)) {
            /* TODO - CORRECT THIS !!!!! */
            System.out.println("Object to pick up from row: " + coordX + ", column: " + coordY + " is of type: " + game.getBoard().getSpace(coordX, coordY).getObject().getObjectColour().toString());
            if (!(game.getBoard().getSpace(coordX, coordY).getObject().getObjectColour().equals(ObjectColour.EMPTY)) || !(game.getBoard().getSpace(coordX, coordY).getTypeSpace().equals(TypeSpace.UNUSABLE))) {
                if(game.getBoard().isSpaceSurrounded(coordX, coordY)){
                    this.update(new GenericErrorMessage(game.getPlayerInTurn().getNickname(), "The object you selected is surrounded!"));
                } else {

                    try {
                        game.getPlayerInTurn().addToObjectsInHand(game.getBoard().getSpace(coordX, coordY).getObject());
                        System.out.println("Added to objects in hand the object of type: " + game.getBoard().getSpace(coordX, coordY).getObject().getObjectColour().toString());
                        game.getBoard().getSpace(coordX, coordY).removeObject();
                    } catch (TooManyObjectsInHandException e) {
                        this.update(new GenericErrorMessage(game.getPlayerInTurn().getNickname(), e.getMessage()));
                    }

                }
            } else {
                this.update(new GenericErrorMessage(game.getPlayerInTurn().getNickname(), "You can't pick up objects from there!"));
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
    private void addObjectToLibrary(int cardID1, int column) throws IncompatibleStateException, NotEnoughSpaceException{
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
                game.getPlayerInTurn().addObjectToLibrary(objectCard1, game.getPlayerInTurn().getLibrary().getLibrarySpace(firstEmpty, column));
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
    private void addObjectToLibrary(int cardID1, int cardID2, int column) throws IncompatibleStateException, NotEnoughSpaceException{
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
                game.getPlayerInTurn().addObjectToLibrary(objectCard1, game.getPlayerInTurn().getLibrary().getLibrarySpace(firstEmpty, column));
                game.getPlayerInTurn().addObjectToLibrary(objectCard2, game.getPlayerInTurn().getLibrary().getLibrarySpace(firstEmpty - 1, column));
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
    private void addObjectToLibrary(int cardID1, int cardID2, int cardID3, int column) throws IncompatibleStateException, NotEnoughSpaceException{
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
                game.getPlayerInTurn().addObjectToLibrary(objectCard1, game.getPlayerInTurn().getLibrary().getLibrarySpace(firstEmpty, column));
                game.getPlayerInTurn().addObjectToLibrary(objectCard2, game.getPlayerInTurn().getLibrary().getLibrarySpace(firstEmpty - 1, column));
                game.getPlayerInTurn().addObjectToLibrary(objectCard3, game.getPlayerInTurn().getLibrary().getLibrarySpace(firstEmpty - 2, column));
            } else {
                throw new NotEnoughSpaceException(column);
            }
        } else {
            throw new IncompatibleStateException(IN_LIBRARY, game.getPlayerInTurn().getPlayerState());
        }
    }

    @Override
    public void update(Message message){
        switch(message.getType()){
            case ADDED_PLAYER:
                if(game.getPlayers().size() == 1) {
                    networkView.askMaxPlayer();
                }

                ArrayList<String> players = new ArrayList<>();
                for (Player player :
                        game.getPlayers()) {
                    players.add(player.getNickname());
                }

                networkView.showLobby(players);
                break;
            case NEXT_TURN:
                networkView.showTurn(game.getPlayerInTurn().getNickname(), game.getBoard(), game.getPlayerInTurn().getLibrary(), game.getPlayerInTurn().getObjectsInHand());
                /* TODO - Send the view update to the game.getPlayerInTurn() user */
                break;
            case GENERIC_MODEL_CHANGE:
                networkView.showTurn(game.getPlayerInTurn().getNickname(), game.getBoard(), game.getPlayerInTurn().getLibrary(), game.getPlayerInTurn().getObjectsInHand());


                /* TODO - Send the view update to the game.getPlayerInTurn() user */
                break;
            case SHOW_COMMON_OBJECTIVE:
                ShowCommonObjectiveMessage showCommonObjectiveMessage = (ShowCommonObjectiveMessage) message;
                networkView.showCommonObjectives(showCommonObjectiveMessage.getSender(), showCommonObjectiveMessage.getCommonObjective1(), showCommonObjectiveMessage.getCommonObjective2());
                break;
            case SHOW_PERSONAL_OBJECTIVE:
                ShowPersonalObjectiveMessage showPersonalObjectiveMessage = (ShowPersonalObjectiveMessage) message;
                networkView.showPersonalObjective(showPersonalObjectiveMessage.getSender(), showPersonalObjectiveMessage.getPersonalObjective());
                break;
            case END_GAME:
                EndGameMessage endGameMessage = (EndGameMessage) message;
                networkView.showWinner(endGameMessage.getWinner(), endGameMessage.getPlayersPoints());
                break;
            case GENERIC_ERROR:
                GenericErrorMessage errorMessage = (GenericErrorMessage) message;
                networkView.showGenericError(errorMessage.getSender(), errorMessage.getPayload());
                break;
            default:
                ;
        }
    }

    /**
     * Return the game which is controlled by the controller
     * @return the game controlled by the controller
     */
    public Game getGame(){return game;}
}

