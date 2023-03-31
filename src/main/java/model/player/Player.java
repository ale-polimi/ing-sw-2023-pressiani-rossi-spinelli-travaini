package model.player;

import enumerations.PlayerState;
import exceptions.player.EmptyDeckException;
import exceptions.player.TooManyObjectsInHandException;
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
    }

    /**
     *
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
     *
     * @return true if the player is the first player of the game.
     */
    public boolean isFirstPlayer() {
        return firstPlayer;
    }

    /**
     *
     * @return true if the player is the first to have ended a game.
     */
    public boolean isFirstToEnd() {
        return firstToEnd;
    }

    /**
     * Setter
     * @param firstToEnd
     */
    public void setFirstToEnd(boolean firstToEnd) {
        this.firstToEnd = firstToEnd;
    }

    /**
     *
     * @return the points scored by the player.
     */
    public int getPoints() {
        return points;
    }

    public Library getLibrary(){
        return this.library;
    }


    /**
     *
     */
    public void initObjectsInHand(){
        this.objectsInHand = new ArrayList<>();
    }

    /**
     *
     */
    public void resetObjectsInHand(){
        this.objectsInHand = null;
    }

    /**
     *
     * @return
     */
    public boolean hasObjectsInHand(){
       if(objectsInHand != null){
           return true;
       } else {
           return false;
       }
    }

    /**
     *
     * @param objectCard
     * @throws TooManyObjectsInHandException
     */
    public void addToObjectsInHand(ObjectCard objectCard) throws TooManyObjectsInHandException{
        if(objectsInHand.size() <= MAX_OBJECTS_IN_HAND){
            objectsInHand.add(objectCard);
        } else {
            throw new TooManyObjectsInHandException(MAX_OBJECTS_IN_HAND);
        }
    }

    /**
     *
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
     *
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
     *
     * @return the state of the player.
     */
    public PlayerState getPlayerState(){
        return this.playerState;
    }
}
