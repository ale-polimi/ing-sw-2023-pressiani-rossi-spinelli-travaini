package model.player;

import enumerations.ObjectColour;
import enumerations.PlayerState;
import exceptions.player.EmptyDeckException;
import exceptions.player.TooManyObjectsInHandException;
import model.commonobjective.CommonObjective;
import model.library.Library;
import model.library.LibrarySpace;
import model.library.PersonalObjective;
import model.objects.ObjectCard;
import network.messages.GenericModelChangeMessage;
import observer.Observable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Player extends Observable {
    private final String nickname;
    private boolean firstPlayer;
    private boolean firstToEnd;
    public static int MAX_OBJECTS_IN_HAND = 3;
    private ArrayList<ObjectCard> objectsInHand = new ArrayList<>(Arrays.asList(null, null, null));
    private final Library library;
    private final PersonalObjective personalObjective;
    int[] completedCommonObjectives = {0,0};
    private PlayerState playerState;
    private int points;

    /**
     * Constructor of the Player class
     * @param username is the username of the player, given to the server by the player.
     * @param json is the string containing the parameters of the personal objective, given to the player by the server
     */
    public Player(String username, String json) {
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
        notifyObserver(new GenericModelChangeMessage());
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
        return points;
    }

    /**
     * This method will add points to the player.
     * @param points are the points to add.
     */
    public void addPoints(int points) {
        this.points += points;
    }

    /**
     * This method will return the points made by a group of adjacent tiles.
     * @param objectColour is the type of cards to check.
     * @return the points made from this type of object cards.
     */
    public int getBoardPoints(ObjectColour objectColour) {
        int groupCount = 0;
        int boardPoints = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                if(library.getLibrarySpace(i,j).getObject().getObjectColour()!= null &&
                        library.getLibrarySpace(i,j).getObject().getObjectColour().isEquals(objectColour)) {


                        List<int[]> group = getAdjacentCells(i, j, objectColour);

                            if (isUniqueGroup(group)) {

                                groupCount = countGroup(group);

                                switch (group.size()){
                                    case  1,2 ->{

                                    }
                                    case 3 -> {
                                        boardPoints += 2;
                                    }
                                    case 4 -> {
                                        boardPoints += 3;
                                    }
                                    case 5 -> {
                                        boardPoints += 5;
                                    }
                                    case 6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30 -> {
                                        boardPoints += 8;
                                    }
                                }

                            }


                }
            }
        }

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                library.getLibrarySpace(i,j).setGroup(0);
                library.getLibrarySpace(i,j).setVisited(false);
            }

        }

        return boardPoints;

    }

    /**
     * this method will return a group of adjacent tiles
     * @param i represents the X coordinate
     * @param j represents the Y coordinate
     * @param objectColour is the colour of the first tile of the group
     * @return a group of adjacent tiles
     */
    private List<int[]> getAdjacentCells(int i, int j, ObjectColour objectColour) {
        List<int[]> group = new ArrayList<>();
        if (i >= 0 && i < 6 && j >= 0 && j < 5 && !library.getLibrarySpace(i, j).isVisited()) {
            ObjectColour currentColour = library.getLibrarySpace(i,j).getObject().getObjectColour();
            if (currentColour != null && currentColour.isEquals(objectColour)) {
                library.getLibrarySpace(i,j).setVisited(true);
                group.add(new int[] {i, j});
                if (i!=0) {group.addAll(getAdjacentCells(i - 1, j, objectColour));}
                if (i!=5) {group.addAll(getAdjacentCells(i + 1, j, objectColour));}
                if (j!=0){group.addAll(getAdjacentCells(i, j - 1, objectColour));}
                if (j!=4){group.addAll(getAdjacentCells(i, j + 1, objectColour));}

            }
        }
        return group;
    }

    /**
     * this method will check if the group has already been counted or not
     * @param group is the list containing adjacent cells
     * @return if the group has already been counted or not
     */
    private boolean isUniqueGroup(List<int[]> group) {
        int row = group.get(0)[0];
        int col = group.get(0)[1];
        return library.getLibrarySpace(row,col).getGroup() == 0;
    }

    /**
     * this method will set the group number to all adjacent tiles of the group
     * @param group is the list containing adjacent cells
     * @return the number of groups + 1
     */
    private int countGroup(List<int[]> group) {
        int count = 1;
        for (int i = 0; i < group.size(); i++) {
            int[] cell = group.get(i);
            int row = cell[0];
            int col = cell[1];
            library.getLibrarySpace(row, col).setGroup(count);
        }
        return ++count; //
    }

    /**
     * This method will tell which common objectives the player has already done.
     * @param commonObjective is the completed common objective.
     * @param points are the points gained by the player associated to the common objective.
     */
    public void setCompletedCommonObjectiveType(CommonObjective commonObjective, int points){
        completedCommonObjectives[commonObjective.getObjectiveNumeral()] = points;
    }

    /**
     * This method returns the array of completed common objective points of this player.
     * @return the array of completed common objective points of this player.
     */
    public int[] getCompletedCommonObjectives() {
        return completedCommonObjectives;
    }

    /**
     * Getter method for the personal objective of this player.
     * @return the player's personal objective.
     */
    public PersonalObjective getPersonalObjective() {
        return personalObjective;
    }

    /**
     * Returns the player's library
     * @return the player's library
     */
    public Library getLibrary(){
        return this.library;
    }

    /**
     * Dummy method to add the object to the library.
     * @param objectCard is the object that will be added to the library.
     * @param librarySpace is the space the {@code objectCard} will occupy.
     */
    public void addObjectToLibrary(ObjectCard objectCard, LibrarySpace librarySpace){
        this.library.addObject(objectCard, librarySpace);
        //notifyObserver(new GenericModelChangeMessage());
    }


    /**
     * Initialize the player's hand
     */
    public void initObjectsInHand(){
        this.objectsInHand = new ArrayList<>(Arrays.asList(null, null, null));
        //notifyObserver(new GenericModelChangeMessage());
    }

    /**
     * DEPRECATED - DO NOT USE, MAY BE REMOVED
     * Reset the player's hand
     */
    @Deprecated
    public void resetObjectsInHand(){
        this.objectsInHand = null;
        notifyObserver(new GenericModelChangeMessage());
    }

    /**
     *Returns if the player has a hand
     * @return true if objectsInHand isn't null, false otherwise
     */
    public boolean hasObjectsInHand(){
        return objectsInHand != null;
    }

    /**
     *Insert an objectCard in the player's hand
     * @param objectCard is the card that has to be added to the hand
     * @throws TooManyObjectsInHandException if the hand is already full
     */
    public void addToObjectsInHand(ObjectCard objectCard) throws TooManyObjectsInHandException{
        /* TODO - Debug print */
        System.out.println(this.getClass().toString() + ": INSIDE addToObjectsInHand");
        if(objectsInHand.size() <= MAX_OBJECTS_IN_HAND){
            for(int i = 0; i < MAX_OBJECTS_IN_HAND; i++){
                if(objectsInHand.get(i) == null){
                    objectsInHand.set(i, objectCard);
                    break;
                }
            }
            /* TODO - Debug print */
            System.out.print("The current objects in hand are: ");
            String toPrint;
            for(int i = 0; i < MAX_OBJECTS_IN_HAND; i++){
                if(objectsInHand.get(i) == null){
                    toPrint = "null";
                } else {
                    toPrint = objectsInHand.get(i).getObjectColour().toString();
                }
                System.out.print(toPrint + ", ");
            }
            System.out.print("\n");
            //notifyObserver(new GenericModelChangeMessage());
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
       int num=0;
       for(int i = 0;  i < getObjectsInHand().size();i++){
           if(getObjectsInHand().get(i)!= null){num++;}
       }
       return num;
    }

    /**
     * This method returns the {@link ArrayList} of objects in hand;
     * @return the objects in hand as an {@link ArrayList}.
     */
    public ArrayList<ObjectCard> getObjectsInHand() {
        return objectsInHand;
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
