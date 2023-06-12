import controller.Controller;
import controller.OfflineControllerForTest;
import enumerations.GameState;
import enumerations.ObjectColour;
import enumerations.PlayerState;
import model.library.Library;
import model.objects.ObjectCard;
import network.messages.MaxPlayersMessage;
import network.messages.PickObjectMessage;
import network.messages.PutObjectInLibraryMessage;
import network.messages.UserInfoForLoginMessage;
import network.structure.Client;
import network.structure.ClientRMI;
import network.structure.ClientSocket;
import network.structure.StartServerImpl;
import org.junit.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class ControllerTest {
    private OfflineControllerForTest controller;

    @Before
    public void setUp() throws IOException {
        this.controller = new OfflineControllerForTest();
    }

    /**
     * Test on a correct number of players (within bounds).
     */
    @Test
    public void onMessageMaxNumbersOK(){
        controller.onMessageReceived(new MaxPlayersMessage("Test", 2));
        assertEquals(2, controller.getGame().getMaxPlayers());
    }

    /**
     * Test on an incorrect number of players (out of bounds).
     */
    @Test
    public void onMessageNumbersNotOK(){
        controller.onMessageReceived(new MaxPlayersMessage("Test", 5));
        assertNotEquals(5, controller.getGame().numOfPlayers());
    }

    /**
     * Test on a new user connecting to the game.
     */
    @Test
    public void onMessageUpdateInfoNewUser(){
        controller.onMessageReceived(new UserInfoForLoginMessage("Test", "Alice"));
        assertEquals(1, controller.getGame().numOfPlayers());
        assertEquals("Alice", controller.getGame().getPlayers().get(0).getNickname());
    }

    /**
     * Test on an username connecting to the game with a duplicate username.
     */
    @Test
    public void onMessageUpdateInfoAlreadySetUser(){
        controller.onMessageReceived(new UserInfoForLoginMessage("Client1", "Alice"));
        controller.onMessageReceived(new UserInfoForLoginMessage("Client2", "Alice"));
        assertEquals(1, controller.getGame().numOfPlayers());
    }

    /**
     * Test to check if the game correctly starts when all the players have connected to the game.
     */
    @Test
    public void onStartGame(){
        controller.onMessageReceived(new MaxPlayersMessage("Client1", 2));
        controller.onMessageReceived(new UserInfoForLoginMessage("Client1", "Alice"));
        controller.onMessageReceived(new UserInfoForLoginMessage("Client2", "Bob"));
        assertTrue(controller.getGame().getPlayers().get(0).isFirstPlayer());
        assertEquals(GameState.IN_GAME, controller.getGame().getGameState());
        assertEquals("Alice", controller.getGame().getPlayerInTurn().getNickname());
    }

    /**
     * Test to check if the controller correctly handles a player's pickup of an object from the board.
     * After the pickup of one (1) card, the player must have that card in his objectsInHand deck.
     * After the pickup the player must be in the IN_LIBRARY state, so that he can no longer pick more objects from the board.
     */
    @Test
    public void onPickupMoveOK(){
        controller.onMessageReceived(new MaxPlayersMessage("Client1", 2));
        controller.onMessageReceived(new UserInfoForLoginMessage("Client1", "Alice"));
        controller.onMessageReceived(new UserInfoForLoginMessage("Client2", "Bob"));
        ObjectCard pickedObject = controller.getGame().getBoard().getSpace(4,1).getObject();
        controller.onMessageReceived(new PickObjectMessage("Alice", new ArrayList<>(Arrays.asList(4,1))));
        assertEquals(3, controller.getGame().getPlayerInTurn().getObjectsInHand().size());
        assertEquals(pickedObject, controller.getGame().getPlayerInTurn().getObjectInHand(0));
        assertNull(controller.getGame().getPlayerInTurn().getObjectInHand(1));
        assertNull(controller.getGame().getPlayerInTurn().getObjectInHand(2));
        assertEquals(PlayerState.IN_LIBRARY, controller.getGame().getPlayerInTurn().getPlayerState());
    }

    /**
     * Test to check if the controller correctly handles a wrong pickup from the player.
     * In this case the player tries to pick up from a tile marked as unusable.
     * After this, the player's objects in hand must not have changed and the unusable empty tile must not be in the
     * objects in hand.
     */
    @Test
    public void onPickupMoveUnusableSpace(){
        controller.onMessageReceived(new MaxPlayersMessage("Client1", 2));
        controller.onMessageReceived(new UserInfoForLoginMessage("Client1", "Alice"));
        controller.onMessageReceived(new UserInfoForLoginMessage("Client2", "Bob"));
        ArrayList<ObjectCard> oldObjectsInHand = controller.getGame().getPlayerInTurn().getObjectsInHand();
        controller.onMessageReceived(new PickObjectMessage("Alice", new ArrayList<>(Arrays.asList(0,0))));
        assertNull(controller.getGame().getPlayerInTurn().getObjectInHand(0));
        assertEquals(oldObjectsInHand, controller.getGame().getPlayerInTurn().getObjectsInHand());
    }

    /**
     * Test to check if the controller correctly handles a wrong pickup from the player.
     * In this case the player tries to pick up from a surrounded tile.
     * After this, the player's objects in hand must not have changed and the surrounded empty tile must not be in the
     * objects in hand.
     * The player must be in PICKUP state to retry the pickup.
     */
    @Test
    public void onPickupMoveSurroundedSpace(){
        controller.onMessageReceived(new MaxPlayersMessage("Client1", 2));
        controller.onMessageReceived(new UserInfoForLoginMessage("Client1", "Alice"));
        controller.onMessageReceived(new UserInfoForLoginMessage("Client2", "Bob"));
        ArrayList<ObjectCard> oldObjectsInHand = controller.getGame().getPlayerInTurn().getObjectsInHand();
        controller.onMessageReceived(new PickObjectMessage("Alice", new ArrayList<>(Arrays.asList(4,2))));
        assertEquals(oldObjectsInHand, controller.getGame().getPlayerInTurn().getObjectsInHand());
        assertEquals(PlayerState.PICKUP, controller.getGame().getPlayerInTurn().getPlayerState());
    }

    /**
     * Test to check if the controller correctly handles a player's pickup of two objects from the board.
     * After the pickup of two (2) cards, the player must have those cards his objectsInHand deck.
     * After the pickup the player must be in the IN_LIBRARY state, so that he can no longer pick more objects from the board.
     */
    @Test
    public void onPickupMove2TilesOK(){
        controller.onMessageReceived(new MaxPlayersMessage("Client1", 2));
        controller.onMessageReceived(new UserInfoForLoginMessage("Client1", "Alice"));
        controller.onMessageReceived(new UserInfoForLoginMessage("Client2", "Bob"));
        ObjectCard pickedObject1 = controller.getGame().getBoard().getSpace(1,3).getObject();
        ObjectCard pickedObject2 = controller.getGame().getBoard().getSpace(1,4).getObject();
        controller.onMessageReceived(new PickObjectMessage("Alice", new ArrayList<>(Arrays.asList(1,3,1,4))));
        assertEquals(3, controller.getGame().getPlayerInTurn().getObjectsInHand().size());
        assertEquals(pickedObject1, controller.getGame().getPlayerInTurn().getObjectInHand(0));
        assertEquals(pickedObject2, controller.getGame().getPlayerInTurn().getObjectInHand(1));
        assertNull(controller.getGame().getPlayerInTurn().getObjectInHand(2));
        assertEquals(PlayerState.IN_LIBRARY, controller.getGame().getPlayerInTurn().getPlayerState());
    }

    /**
     * Test to check if the controller correctly handles a wrong pickup from the player.
     * In this case the player tries to pick up 2 not aligned objects.
     * After this, the player's objects in hand must not have changed and tiles must not be in the objects in hand.
     * The player must be in PICKUP state to retry the pickup.
     */
    @Test
    public void onPickupMove2TilesNotInLine(){
        controller.onMessageReceived(new MaxPlayersMessage("Client1", 2));
        controller.onMessageReceived(new UserInfoForLoginMessage("Client1", "Alice"));
        controller.onMessageReceived(new UserInfoForLoginMessage("Client2", "Bob"));
        ArrayList<ObjectCard> oldObjectsInHand = controller.getGame().getPlayerInTurn().getObjectsInHand();
        controller.onMessageReceived(new PickObjectMessage("Alice", new ArrayList<>(Arrays.asList(2,3,3,2))));
        assertEquals(oldObjectsInHand, controller.getGame().getPlayerInTurn().getObjectsInHand());
        assertEquals(PlayerState.PICKUP, controller.getGame().getPlayerInTurn().getPlayerState());
    }

    /**
     * Test to check if the controller correctly handles a wrong pickup from the player.
     * In this case the player tries to pick up 2 objects, one of which is surrounded.
     * After this, the player's objects in hand must not have changed and tiles must not be in the objects in hand.
     * The player must be in PICKUP state to retry the pickup.
     */
    @Test
    public void onPickupMove2TilesOneSurrounded(){
        controller.onMessageReceived(new MaxPlayersMessage("Client1", 2));
        controller.onMessageReceived(new UserInfoForLoginMessage("Client1", "Alice"));
        controller.onMessageReceived(new UserInfoForLoginMessage("Client2", "Bob"));
        ArrayList<ObjectCard> oldObjectsInHand = controller.getGame().getPlayerInTurn().getObjectsInHand();
        controller.onMessageReceived(new PickObjectMessage("Alice", new ArrayList<>(Arrays.asList(2,3,3,3))));
        assertEquals(oldObjectsInHand, controller.getGame().getPlayerInTurn().getObjectsInHand());
        assertEquals(PlayerState.PICKUP, controller.getGame().getPlayerInTurn().getPlayerState());
    }

    /**
     * Test to check if the controller correctly handles a wrong pickup from the player.
     * In this case the player tries to pick up 2 objects, one of which is empty.
     * After this, the player's objects in hand must not have changed and tiles must not be in the objects in hand.
     * The player must be in PICKUP state to retry the pickup.
     */
    @Test
    public void onPickupMove2TilesOneEmpty(){
        controller.onMessageReceived(new MaxPlayersMessage("Client1", 2));
        controller.onMessageReceived(new UserInfoForLoginMessage("Client1", "Alice"));
        controller.onMessageReceived(new UserInfoForLoginMessage("Client2", "Bob"));
        ArrayList<ObjectCard> oldObjectsInHand = controller.getGame().getPlayerInTurn().getObjectsInHand();
        controller.onMessageReceived(new PickObjectMessage("Alice", new ArrayList<>(Arrays.asList(0,3,1,3))));
        assertEquals(oldObjectsInHand, controller.getGame().getPlayerInTurn().getObjectsInHand());
        assertEquals(PlayerState.PICKUP, controller.getGame().getPlayerInTurn().getPlayerState());
    }

    /**
     * Test to check if the controller correctly handles a player's pickup of three objects from the board.
     * After the pickup of three (3) cards, the player must have those cards his objectsInHand deck.
     * After the pickup the player must be in the IN_LIBRARY state, so that he can no longer pick more objects from the board.
     */
    @Test
    public void onPickupMove3TilesOK(){
        controller.onMessageReceived(new MaxPlayersMessage("Client1", 2));
        controller.onMessageReceived(new UserInfoForLoginMessage("Client1", "Alice"));
        controller.onMessageReceived(new UserInfoForLoginMessage("Client2", "Bob"));
        controller.getGame().getBoard().getSpace(1,3).removeObject();
        controller.getGame().getBoard().getSpace(1,4).removeObject();
        ObjectCard pickedObject1 = controller.getGame().getBoard().getSpace(2,3).getObject();
        ObjectCard pickedObject2 = controller.getGame().getBoard().getSpace(2,4).getObject();
        ObjectCard pickedObject3 = controller.getGame().getBoard().getSpace(2,5).getObject();
        controller.onMessageReceived(new PickObjectMessage("Alice", new ArrayList<>(Arrays.asList(2,3,2,4,2,5))));
        assertEquals(3, controller.getGame().getPlayerInTurn().getObjectsInHand().size());
        assertEquals(pickedObject1, controller.getGame().getPlayerInTurn().getObjectInHand(0));
        assertEquals(pickedObject2, controller.getGame().getPlayerInTurn().getObjectInHand(1));
        assertEquals(pickedObject3, controller.getGame().getPlayerInTurn().getObjectInHand(2));
        assertEquals(PlayerState.IN_LIBRARY, controller.getGame().getPlayerInTurn().getPlayerState());
    }

    /**
     * Test to check if the controller correctly handles a wrong pickup from the player.
     * In this case the player tries to pick up 3 objects, one (or more) of which is surrounded.
     * After this, the player's objects in hand must not have changed and tiles must not be in the objects in hand.
     * The player must be in PICKUP state to retry the pickup.
     */
    @Test
    public void onPickupMove3TilesOneSurrounded(){
        controller.onMessageReceived(new MaxPlayersMessage("Client1", 2));
        controller.onMessageReceived(new UserInfoForLoginMessage("Client1", "Alice"));
        controller.onMessageReceived(new UserInfoForLoginMessage("Client2", "Bob"));
        ArrayList<ObjectCard> oldObjectsInHand = controller.getGame().getPlayerInTurn().getObjectsInHand();
        controller.onMessageReceived(new PickObjectMessage("Alice", new ArrayList<>(Arrays.asList(2,3,2,4,2,5))));
        assertEquals(3, controller.getGame().getPlayerInTurn().getObjectsInHand().size());
        assertEquals(oldObjectsInHand, controller.getGame().getPlayerInTurn().getObjectsInHand());
        assertEquals(PlayerState.PICKUP, controller.getGame().getPlayerInTurn().getPlayerState());
    }

    /**
     * Test to check if the controller correctly handles a wrong pickup from the player.
     * In this case the player tries to pick up 3 objects, one (or more) of which is empty.
     * After this, the player's objects in hand must not have changed and tiles must not be in the objects in hand.
     * The player must be in PICKUP state to retry the pickup.
     */
    @Test
    public void onPickupMove3TilesOneEmpty(){
        controller.onMessageReceived(new MaxPlayersMessage("Client1", 2));
        controller.onMessageReceived(new UserInfoForLoginMessage("Client1", "Alice"));
        controller.onMessageReceived(new UserInfoForLoginMessage("Client2", "Bob"));
        ArrayList<ObjectCard> oldObjectsInHand = controller.getGame().getPlayerInTurn().getObjectsInHand();
        controller.onMessageReceived(new PickObjectMessage("Alice", new ArrayList<>(Arrays.asList(1,2,1,3,1,4))));
        assertEquals(oldObjectsInHand, controller.getGame().getPlayerInTurn().getObjectsInHand());
        assertEquals(PlayerState.PICKUP, controller.getGame().getPlayerInTurn().getPlayerState());
    }

    /**
     * Test to check if the controller correctly handles a player's library move.
     * After the move the object(s) contained in the hand deck of the player will be placed in the assigned column.
     * After the move the player's objectsInHand will be empty after the move.
     * After the move the player must be in the PICKUP state, so that he can no longer put more objects in teh library.
     */
    @Test
    public void onPutOneInLibraryMove() {
        controller.onMessageReceived(new MaxPlayersMessage("Client1", 2));
        controller.onMessageReceived(new UserInfoForLoginMessage("Client1", "Alice"));
        controller.onMessageReceived(new UserInfoForLoginMessage("Client2", "Bob"));
        ObjectCard pickedObject1 = controller.getGame().getBoard().getSpace(1,3).getObject();
        controller.onMessageReceived(new PickObjectMessage("Alice", new ArrayList<>(Arrays.asList(1,3))));
        controller.onMessageReceived(new PutObjectInLibraryMessage("Alice", new ArrayList<>(Arrays.asList(0,0))));
        assertNull(controller.getGame().getNextPlayer().getObjectsInHand().get(0));
        assertEquals(pickedObject1, controller.getGame().getNextPlayer().getLibrary().getLibrarySpace(5,0).getObject());
        assertEquals(PlayerState.PICKUP, controller.getGame().getPlayerInTurn().getPlayerState());
        assertEquals("Bob", controller.getGame().getPlayerInTurn().getNickname());
    }

    /**
     * Test to check if the controller correctly handles a player's library move.
     * After the move the object(s) contained in the hand deck of the player will be placed in the assigned column.
     * After the move the player's objectsInHand will be empty after the move.
     * After the move the player must be in the PICKUP state, so that he can no longer put more objects in teh library.
     */
    @Test
    public void onPutTwoInLibraryMove() {
        controller.onMessageReceived(new MaxPlayersMessage("Client1", 2));
        controller.onMessageReceived(new UserInfoForLoginMessage("Client1", "Alice"));
        controller.onMessageReceived(new UserInfoForLoginMessage("Client2", "Bob"));
        ObjectCard pickedObject1 = controller.getGame().getBoard().getSpace(1,3).getObject();
        ObjectCard pickedObject2 = controller.getGame().getBoard().getSpace(1,4).getObject();
        controller.onMessageReceived(new PickObjectMessage("Alice", new ArrayList<>(Arrays.asList(1,3,1,4))));
        controller.onMessageReceived(new PutObjectInLibraryMessage("Alice", new ArrayList<>(Arrays.asList(0,1,0))));
        assertNull(controller.getGame().getNextPlayer().getObjectsInHand().get(0));
        assertNull(controller.getGame().getNextPlayer().getObjectsInHand().get(1));
        assertEquals(pickedObject1, controller.getGame().getNextPlayer().getLibrary().getLibrarySpace(5,0).getObject());
        assertEquals(pickedObject2, controller.getGame().getNextPlayer().getLibrary().getLibrarySpace(4,0).getObject());
        assertEquals(PlayerState.PICKUP, controller.getGame().getPlayerInTurn().getPlayerState());
        assertEquals("Bob", controller.getGame().getPlayerInTurn().getNickname());
    }

    /**
     * Test to check if the controller correctly handles a player's library move.
     * After the move the object(s) contained in the hand deck of the player will be placed in the assigned column.
     * After the move the player's objectsInHand will be empty after the move.
     * After the move the player must be in the PICKUP state, so that he can no longer put more objects in teh library.
     */
    @Test
    public void onPutThreeInLibraryMove() {
        controller.onMessageReceived(new MaxPlayersMessage("Client1", 2));
        controller.onMessageReceived(new UserInfoForLoginMessage("Client1", "Alice"));
        controller.onMessageReceived(new UserInfoForLoginMessage("Client2", "Bob"));
        controller.getGame().getBoard().getSpace(1,3).removeObject();
        controller.getGame().getBoard().getSpace(1,4).removeObject();
        ObjectCard pickedObject1 = controller.getGame().getBoard().getSpace(2,3).getObject();
        ObjectCard pickedObject2 = controller.getGame().getBoard().getSpace(2,4).getObject();
        ObjectCard pickedObject3 = controller.getGame().getBoard().getSpace(2,5).getObject();
        controller.onMessageReceived(new PickObjectMessage("Alice", new ArrayList<>(Arrays.asList(2,3,2,4,2,5))));
        controller.onMessageReceived(new PutObjectInLibraryMessage("Alice", new ArrayList<>(Arrays.asList(0,1,2,0))));
        assertNull(controller.getGame().getNextPlayer().getObjectsInHand().get(0));
        assertNull(controller.getGame().getNextPlayer().getObjectsInHand().get(1));
        assertNull(controller.getGame().getNextPlayer().getObjectsInHand().get(2));
        assertEquals(pickedObject1, controller.getGame().getNextPlayer().getLibrary().getLibrarySpace(5,0).getObject());
        assertEquals(pickedObject2, controller.getGame().getNextPlayer().getLibrary().getLibrarySpace(4,0).getObject());
        assertEquals(pickedObject3, controller.getGame().getNextPlayer().getLibrary().getLibrarySpace(3,0).getObject());
        assertEquals(PlayerState.PICKUP, controller.getGame().getPlayerInTurn().getPlayerState());
        assertEquals("Bob", controller.getGame().getPlayerInTurn().getNickname());
    }

    /**
     * Test to check if the controller correctly handles a player's incorrect move. In this case the player does not
     * have enough objects in his hand.
     * After the move, the player's objectsInHand must not have changed.
     * After the move, the player's library must not have changed.
     * After the move, the player in turn must not have changed.
     */
    @Test
    public void onPutInLibraryMoveNotEnoughObjectsInhand(){
        controller.onMessageReceived(new MaxPlayersMessage("Client1", 2));
        controller.onMessageReceived(new UserInfoForLoginMessage("Client1", "Alice"));
        controller.onMessageReceived(new UserInfoForLoginMessage("Client2", "Bob"));
        ObjectCard pickedObject = controller.getGame().getBoard().getSpace(1,3).getObject();
        controller.onMessageReceived(new PickObjectMessage("Alice", new ArrayList<>(Arrays.asList(1,3))));
        ArrayList<ObjectCard> oldObjectsInHand = controller.getGame().getPlayerInTurn().getObjectsInHand();
        Library oldPlayerLibrary = controller.getGame().getPlayerInTurn().getLibrary();
        controller.onMessageReceived(new PutObjectInLibraryMessage("Alice", new ArrayList<>(Arrays.asList(0,1,0))));
        assertEquals(oldObjectsInHand, controller.getGame().getPlayerInTurn().getObjectsInHand());
        assertEquals(oldPlayerLibrary, controller.getGame().getPlayerInTurn().getLibrary());
        assertEquals("Alice", controller.getGame().getPlayerInTurn().getNickname());
        assertEquals("Bob", controller.getGame().getNextPlayer().getNickname());
    }

    /**
     * Test to check if the controller correctly handles a player's incorrect move. In this case the column chosen by
     * the player does not have enough space for the additional object.
     * After the move, the player's objectsInHand must not have changed.
     * After the move, the player's library must not have changed.
     * After the move, the player in turn must not have changed.
     */
    @Test
    public void onPutInLibraryMoveNotEnoughSpaceForOne(){
        controller.onMessageReceived(new MaxPlayersMessage("Client1", 2));
        controller.onMessageReceived(new UserInfoForLoginMessage("Client1", "Alice"));
        controller.onMessageReceived(new UserInfoForLoginMessage("Client2", "Bob"));

        Library aliceLibrary = controller.getGame().getPlayerInTurn().getLibrary();
        /* The first column in the player's library is full */
        for(int row = 0; row < 6; row++){
            aliceLibrary.addObject(new ObjectCard(ObjectColour.PINK1), aliceLibrary.getLibrarySpace(row, 0));
        }

        ObjectCard pickedObject = controller.getGame().getBoard().getSpace(1,3).getObject();
        controller.onMessageReceived(new PickObjectMessage("Alice", new ArrayList<>(Arrays.asList(1,3))));
        ArrayList<ObjectCard> oldObjectsInHand = controller.getGame().getPlayerInTurn().getObjectsInHand();
        Library oldPlayerLibrary = controller.getGame().getPlayerInTurn().getLibrary();
        controller.onMessageReceived(new PutObjectInLibraryMessage("Alice", new ArrayList<>(Arrays.asList(0,0))));
        assertEquals(oldObjectsInHand, controller.getGame().getPlayerInTurn().getObjectsInHand());
        assertEquals(oldPlayerLibrary, controller.getGame().getPlayerInTurn().getLibrary());
        assertEquals("Alice", controller.getGame().getPlayerInTurn().getNickname());
        assertEquals("Bob", controller.getGame().getNextPlayer().getNickname());
    }

    /**
     * Test to check if the controller correctly handles a player's incorrect move. In this case the column chosen by
     * the player does not have enough space for the additional objects.
     * After the move, the player's objectsInHand must not have changed.
     * After the move, the player's library must not have changed.
     * After the move, the player in turn must not have changed.
     */
    @Test
    public void onPutInLibraryMoveNotEnoughSpaceForTwo(){
        controller.onMessageReceived(new MaxPlayersMessage("Client1", 2));
        controller.onMessageReceived(new UserInfoForLoginMessage("Client1", "Alice"));
        controller.onMessageReceived(new UserInfoForLoginMessage("Client2", "Bob"));

        Library aliceLibrary = controller.getGame().getPlayerInTurn().getLibrary();
        /* The first column in the player's library is full */
        for(int row = 0; row < 6; row++){
            aliceLibrary.addObject(new ObjectCard(ObjectColour.PINK1), aliceLibrary.getLibrarySpace(row, 0));
        }

        ObjectCard pickedObject1 = controller.getGame().getBoard().getSpace(1,3).getObject();
        ObjectCard pickedObject2 = controller.getGame().getBoard().getSpace(1,4).getObject();
        controller.onMessageReceived(new PickObjectMessage("Alice", new ArrayList<>(Arrays.asList(1,3,1,4))));
        ArrayList<ObjectCard> oldObjectsInHand = controller.getGame().getPlayerInTurn().getObjectsInHand();
        Library oldPlayerLibrary = controller.getGame().getPlayerInTurn().getLibrary();
        controller.onMessageReceived(new PutObjectInLibraryMessage("Alice", new ArrayList<>(Arrays.asList(0,1,0))));
        assertEquals(oldObjectsInHand, controller.getGame().getPlayerInTurn().getObjectsInHand());
        assertEquals(oldPlayerLibrary, controller.getGame().getPlayerInTurn().getLibrary());
        assertEquals("Alice", controller.getGame().getPlayerInTurn().getNickname());
        assertEquals("Bob", controller.getGame().getNextPlayer().getNickname());
    }

    /**
     * Test to check if the controller correctly handles a player's incorrect move. In this case the column chosen by
     * the player does not have enough space for the additional objects.
     * After the move, the player's objectsInHand must not have changed.
     * After the move, the player's library must not have changed.
     * After the move, the player in turn must not have changed.
     */
    @Test
    public void onPutInLibraryMoveNotEnoughSpaceForThree(){
        controller.onMessageReceived(new MaxPlayersMessage("Client1", 2));
        controller.onMessageReceived(new UserInfoForLoginMessage("Client1", "Alice"));
        controller.onMessageReceived(new UserInfoForLoginMessage("Client2", "Bob"));

        Library aliceLibrary = controller.getGame().getPlayerInTurn().getLibrary();
        /* The first column in the player's library is full */
        for(int row = 0; row < 6; row++){
            aliceLibrary.addObject(new ObjectCard(ObjectColour.PINK1), aliceLibrary.getLibrarySpace(row, 0));
        }

        controller.getGame().getBoard().getSpace(1,3).removeObject();
        controller.getGame().getBoard().getSpace(1,4).removeObject();

        ObjectCard pickedObject1 = controller.getGame().getBoard().getSpace(2,3).getObject();
        ObjectCard pickedObject2 = controller.getGame().getBoard().getSpace(2,4).getObject();
        ObjectCard pickedObject3 = controller.getGame().getBoard().getSpace(2,5).getObject();
        controller.onMessageReceived(new PickObjectMessage("Alice", new ArrayList<>(Arrays.asList(2,3,2,4,2,5))));
        ArrayList<ObjectCard> oldObjectsInHand = controller.getGame().getPlayerInTurn().getObjectsInHand();
        Library oldPlayerLibrary = controller.getGame().getPlayerInTurn().getLibrary();
        controller.onMessageReceived(new PutObjectInLibraryMessage("Alice", new ArrayList<>(Arrays.asList(0,1,2,0))));
        assertEquals(oldObjectsInHand, controller.getGame().getPlayerInTurn().getObjectsInHand());
        assertEquals(oldPlayerLibrary, controller.getGame().getPlayerInTurn().getLibrary());
        assertEquals("Alice", controller.getGame().getPlayerInTurn().getNickname());
        assertEquals("Bob", controller.getGame().getNextPlayer().getNickname());
    }

    /**
     * Test to check the end of the game conditions.
     */
    @Test
    public void endGameTest(){
        controller.onMessageReceived(new MaxPlayersMessage("Client1", 2));
        controller.onMessageReceived(new UserInfoForLoginMessage("Client1", "Alice"));
        controller.onMessageReceived(new UserInfoForLoginMessage("Client2", "Bob"));

        Library aliceLibrary = controller.getGame().getPlayerInTurn().getLibrary();
        /* The library of the first player has only one space left */
        for(int col = 0; col < 5; col++){
            for(int row = 5; row >= 0; row--){
                if(row == 0 && col == 4){
                    break;
                }
                aliceLibrary.addObject(new ObjectCard(ObjectColour.PINK1), aliceLibrary.getLibrarySpace(row, col));
            }
        }

        Library bobLibrary = controller.getGame().getNextPlayer().getLibrary();
        /* The library of the second player has two spaces left */
        for(int col = 0; col < 5; col++){
            for(int row = 5; row >= 0; row--){
                if(row == 0 && col == 4){
                    break;
                }
                bobLibrary.addObject(new ObjectCard(ObjectColour.GREEN1), bobLibrary.getLibrarySpace(row, col));
            }
        }

        /* Setting the board to something known */
        controller.getGame().getBoard().pickupObjectFrom(controller.getGame().getBoard().getSpace(1,3));
        controller.getGame().getBoard().pickupObjectFrom(controller.getGame().getBoard().getSpace(1,4));
        controller.getGame().getBoard().putObjectIn(controller.getGame().getBoard().getSpace(1,3), new ObjectCard(ObjectColour.PINK1));
        controller.getGame().getBoard().putObjectIn(controller.getGame().getBoard().getSpace(1,4), new ObjectCard(ObjectColour.GREEN1));

        controller.onMessageReceived(new PickObjectMessage("Alice", new ArrayList<>(Arrays.asList(1,3))));
        controller.onMessageReceived(new PutObjectInLibraryMessage("Alice", new ArrayList<>(Arrays.asList(0,4))));
        assertTrue(controller.getGame().getNextPlayer().isFirstToEnd());
        assertNotEquals(GameState.END, controller.getGame().getGameState());

        controller.onMessageReceived(new PickObjectMessage("Bob", new ArrayList<>(Arrays.asList(1,4))));
        controller.onMessageReceived(new PutObjectInLibraryMessage("Bob", new ArrayList<>(Arrays.asList(0,4))));
        assertNotNull(controller.winner);
        assertTrue(controller.playersPoints.containsKey("Alice") && controller.playersPoints.containsKey("Bob"));
        System.out.println("Winner is: " + controller.winner);
        controller.playersPoints.forEach((username, points) -> System.out.println(username + " : " + points));
    }

    /**
     * Test to check the end of the game conditions.
     */
    @Test
    public void endGameTestFirstPlayerIsFirstToEnd(){
        controller.onMessageReceived(new MaxPlayersMessage("Client1", 2));
        controller.onMessageReceived(new UserInfoForLoginMessage("Client1", "Alice"));
        controller.onMessageReceived(new UserInfoForLoginMessage("Client2", "Bob"));

        Library aliceLibrary = controller.getGame().getPlayerInTurn().getLibrary();
        /* The library of the first player has only one space left */
        for(int col = 0; col < 5; col++){
            for(int row = 5; row >= 0; row--){
                if(row == 0 && col == 4){
                    break;
                }
                aliceLibrary.addObject(new ObjectCard(ObjectColour.PINK1), aliceLibrary.getLibrarySpace(row, col));
            }
        }

        Library bobLibrary = controller.getGame().getNextPlayer().getLibrary();
        /* The library of the second player has the last column empty */
        for(int col = 0; col < 4; col++){
            for(int row = 5; row >= 0; row--){
                bobLibrary.addObject(new ObjectCard(ObjectColour.GREEN1), bobLibrary.getLibrarySpace(row, col));
            }
        }

        /* Setting the board to something known */
        controller.getGame().getBoard().pickupObjectFrom(controller.getGame().getBoard().getSpace(1,3));
        controller.getGame().getBoard().pickupObjectFrom(controller.getGame().getBoard().getSpace(1,4));
        controller.getGame().getBoard().putObjectIn(controller.getGame().getBoard().getSpace(1,3), new ObjectCard(ObjectColour.PINK1));
        controller.getGame().getBoard().putObjectIn(controller.getGame().getBoard().getSpace(1,4), new ObjectCard(ObjectColour.GREEN1));

        controller.onMessageReceived(new PickObjectMessage("Alice", new ArrayList<>(Arrays.asList(1,3))));
        controller.onMessageReceived(new PutObjectInLibraryMessage("Alice", new ArrayList<>(Arrays.asList(0,4))));
        assertTrue(controller.getGame().getNextPlayer().isFirstToEnd());
        assertNotEquals(GameState.END, controller.getGame().getGameState());

        controller.onMessageReceived(new PickObjectMessage("Bob", new ArrayList<>(Arrays.asList(1,4))));
        controller.onMessageReceived(new PutObjectInLibraryMessage("Bob", new ArrayList<>(Arrays.asList(0,4))));
        assertEquals(GameState.END, controller.getGame().getGameState());
        assertNotNull(controller.winner);
        assertTrue(controller.playersPoints.containsKey("Alice") && controller.playersPoints.containsKey("Bob"));
        System.out.println("Winner is: " + controller.winner);
        controller.playersPoints.forEach((username, points) -> System.out.println(username + " : " + points));
    }

    /**
     * Test to check the end of the game conditions.
     */
    @Test
    public void endGameTestNonFullLibrary(){
        controller.onMessageReceived(new MaxPlayersMessage("Client1", 2));
        controller.onMessageReceived(new UserInfoForLoginMessage("Client1", "Alice"));
        controller.onMessageReceived(new UserInfoForLoginMessage("Client2", "Bob"));

        Library aliceLibrary = controller.getGame().getPlayerInTurn().getLibrary();
        /* The library of the first player has only one space left */
        for(int col = 0; col < 5; col++){
            for(int row = 5; row >= 0; row--){
                if(row == 0 && col == 4){
                    break;
                }
                aliceLibrary.addObject(new ObjectCard(ObjectColour.PINK1), aliceLibrary.getLibrarySpace(row, col));
            }
        }

        Library bobLibrary = controller.getGame().getNextPlayer().getLibrary();
        /* The library of the second player has two spaces left */
        for(int col = 0; col < 5; col++){
            for(int row = 5; row >= 0; row--){
                if(row == 0 && col == 4){
                    break;
                } else if(row == 1 && col == 4){
                    break;
                }
                bobLibrary.addObject(new ObjectCard(ObjectColour.GREEN1), bobLibrary.getLibrarySpace(row, col));
            }
        }

        /* Setting the board to something known */
        controller.getGame().getBoard().pickupObjectFrom(controller.getGame().getBoard().getSpace(1,3));
        controller.getGame().getBoard().pickupObjectFrom(controller.getGame().getBoard().getSpace(1,4));
        controller.getGame().getBoard().putObjectIn(controller.getGame().getBoard().getSpace(1,3), new ObjectCard(ObjectColour.PINK1));
        controller.getGame().getBoard().putObjectIn(controller.getGame().getBoard().getSpace(1,4), new ObjectCard(ObjectColour.GREEN1));

        controller.onMessageReceived(new PickObjectMessage("Alice", new ArrayList<>(Arrays.asList(1,3))));
        controller.onMessageReceived(new PutObjectInLibraryMessage("Alice", new ArrayList<>(Arrays.asList(0,4))));
        assertTrue(controller.getGame().getNextPlayer().isFirstToEnd());
        assertNotEquals(GameState.END, controller.getGame().getGameState());

        controller.onMessageReceived(new PickObjectMessage("Bob", new ArrayList<>(Arrays.asList(1,4))));
        controller.onMessageReceived(new PutObjectInLibraryMessage("Bob", new ArrayList<>(Arrays.asList(0,4))));
        assertEquals(GameState.END, controller.getGame().getGameState());
        assertNotNull(controller.winner);
        assertTrue(controller.playersPoints.containsKey("Alice") && controller.playersPoints.containsKey("Bob"));
        System.out.println("Winner is: " + controller.winner);
        controller.playersPoints.forEach((username, points) -> System.out.println(username + " : " + points));
    }
}
