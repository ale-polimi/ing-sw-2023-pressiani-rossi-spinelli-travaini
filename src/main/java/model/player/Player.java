package model.player;

import enumerations.PlayerState;
import exceptions.player.EmptyDeckException;
import exceptions.player.TooManyObjectsInHandException;
import model.commonobjective.CommonObjective;
import model.library.Library;
import model.library.PersonalObjective;
import model.objects.ObjectCard;

import java.util.ArrayList;
import java.util.EmptyStackException;

public class Player {
    private String nickname;
    private boolean firstPlayer;
    private boolean firstToEnd;
    public static int MAX_OBJECTS_IN_HAND = 3;
    private ArrayList<ObjectCard> objectsInHand = null;
    private Library library;
    private PersonalObjective personalObjective;
    boolean[] completedCommonObjectives = {false, false};
    private PlayerState playerState;
    private int points;

    /**
     * Constructor of the Player class
     * @param username is the username of the player, given to the server by the player.
     * @param json is the string containing the parameters of the personal objective, given to the player by the server
     */
    public Player(String username, String json){
        this.nickname = username;
        this.library = new Library();
        this.personalObjective = new PersonalObjective(json);
        this.firstPlayer = false;
    }

    /**
     *Returns the player's nickname
     * @return the nickname of the player.
     */
    public String getNickname() {
        return nickname;
    }

    /**
     *Set the player as first player
     */
    public void setAsFirst(){
        this.firstPlayer = true;
    }

    /**
     *Returns if the player is the first player of the game
     * @return true if the player is the first player of the game.
     */
    public boolean isFirstPlayer() {
        return firstPlayer;
    }

    /**
     *Returns if the player is the first to complete is library
     * @return true if the player is the first to have ended a game.
     */
    public boolean isFirstToEnd() {
        return firstToEnd;
    }

    /**
     * Setter of the first player to end the game
     * @param firstToEnd is the player who finished the game first
     */
    public void setFirstToEnd(boolean firstToEnd) {
        this.firstToEnd = firstToEnd;
    }

    /**
     *Returns the total points scored by the player
     * @return the points scored by the player.
     */
    public int getPoints() {
        return points + personalObjective.compareTo(library);
    }

    /**
     * This method will add points to the player.
     * @param points is calculated by the {@link controller.Controller controller} in the appropriate methods.
     */
    public void addPoints(int points) {
        this.points += points;
    }

    /**
     * This method will tell which common objectives the player has already done.
     * @param commonObjective is the completed common objective.
     */
    public void setCompletedCommonObjectiveType(CommonObjective commonObjective){
        completedCommonObjectives[commonObjective.getObjectiveNumeral()] = true;
    }

    public boolean[] getCompletedCommonObjectives() {
        return completedCommonObjectives;
    }

    /**
     * Returns the player's library
     * @return the player's library
     */
    public Library getLibrary(){
        return this.library;
    }


    /**
     *Initialize the player's hand
     */
    public void initObjectsInHand(){
        this.objectsInHand = new ArrayList<>();
    }

    /**
     *Reset the player's hand
     */
    public void resetObjectsInHand(){
        this.objectsInHand = null;
    }

    /**
     *Returns if the player has a hand
     * @return true if objectsInHand isn't null, false otherwise
     */
    public boolean hasObjectsInHand(){
       if(objectsInHand != null){
           return true;
       } else {
           return false;
       }
    }

    /**
     *Insert an objectCard in the player's hand
     * @param objectCard is the card that has to be added to the hand
     * @throws TooManyObjectsInHandException if the hand is already full
     */
    public void addToObjectsInHand(ObjectCard objectCard) throws TooManyObjectsInHandException{
        if(objectsInHand.size() < MAX_OBJECTS_IN_HAND){
            objectsInHand.add(objectCard);
        } else {
            throw new TooManyObjectsInHandException(MAX_OBJECTS_IN_HAND);
        }
    }

    /**
     *Returns the object in the specified position of the player's hand
     * @param positionInDeck is the position of the card in the player's turn deck.
     * @return the object in the specified position.
     * @throws EmptyDeckException if the hand deck does not contain any card.
     */
    public ObjectCard getObjectInHand(int positionInDeck) throws EmptyDeckException {
        if(!objectsInHand.isEmpty()){
            return objectsInHand.get(positionInDeck);
        } else {
            throw new EmptyDeckException();
        }
    }

    /**
     *Return the number of objects in the player's hand
     * @return the number of object cards this player currently has in hand.
     */
    public int getObjectsInHandSize(){
        return objectsInHand.size();
    }

    /**
     * Setter method to change the player's state.
     * @param playerState is the new state for this player.
     */
    public void setPlayerState(PlayerState playerState){
        this.playerState = playerState;
    }

    /**
     *Returns the player's state
     * @return the state of the player.
     */
    public PlayerState getPlayerState(){
        return this.playerState;
    }
}
