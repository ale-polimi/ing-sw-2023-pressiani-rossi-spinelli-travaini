import controller.Controller;
import enumerations.GameState;
import enumerations.ObjectColour;
import enumerations.PlayerState;
import model.library.Library;
import model.objects.ObjectCard;
import network.MaxPlayersMessage;
import network.PickObjectMessage;
import network.PutObjectInLibraryMessage;
import network.UserInfoForLoginMessage;
import network.structure.Client;
import network.structure.ClientRMI;
import network.structure.StartServerImpl;
import org.junit.*;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class ControllerTest {
    private Controller controller;
    private Client dummyClient;

    @Before
    public void setUp(){
        this.controller = new Controller(new StartServerImpl());
        this.dummyClient = new ClientRMI("localhost", 8080);
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
    public void onPickupMove(){
        controller.onMessageReceived(new MaxPlayersMessage("Client1", 2));
        controller.onMessageReceived(new UserInfoForLoginMessage("Client1", "Alice"));
        controller.onMessageReceived(new UserInfoForLoginMessage("Client2", "Bob"));
        ObjectCard pickedObject = controller.getGame().getBoard().getSpace(4,2).getObject();
        controller.onMessageReceived(new PickObjectMessage("Alice", new ArrayList<>(Arrays.asList(4,2))));
        assertEquals(1, controller.getGame().getPlayerInTurn().getObjectsInHandSize());
        assertEquals(pickedObject, controller.getGame().getPlayerInTurn().getObjectInHand(0));
        assertEquals(PlayerState.IN_LIBRARY, controller.getGame().getPlayerInTurn().getPlayerState());
    }

    /**
     * Test to check if the controller correctly handles a player's library move.
     * After the move the object(s) contained in the hand deck of the player will be placed in the assigned column.
     * After the move the player's objectsInHand will be empty after the move.
     * After the move the player must be in the PICKUP state, so that he can no longer put more objects in teh library.
     */
    @Test
    public void onPutInLibraryMove(){
        controller.onMessageReceived(new MaxPlayersMessage("Client1", 2));
        controller.onMessageReceived(new UserInfoForLoginMessage("Client1", "Alice"));
        controller.onMessageReceived(new UserInfoForLoginMessage("Client2", "Bob"));
        ObjectCard pickedObject = controller.getGame().getBoard().getSpace(4,2).getObject();
        controller.onMessageReceived(new PickObjectMessage("Alice", new ArrayList<>(Arrays.asList(4,2))));
        controller.onMessageReceived(new PutObjectInLibraryMessage("Alice", new ArrayList<>(Arrays.asList(0,0))));
        assertEquals(ObjectColour.EMPTY, controller.getGame().getPlayerInTurn().getObjectInHand(0).getObjectColour());
        assertEquals(pickedObject, controller.getGame().getPlayerInTurn().getLibrary().getLibrarySpace(0,5).getObject());
        assertEquals(PlayerState.PICKUP, controller.getGame().getPlayerInTurn().getPlayerState());
        assertEquals("Bob", controller.getGame().getPlayerInTurn().getNickname());
    }

    /**
     * Test to check if the controller correctly handles a player's incorrect move.
     * After the move, the player's objectsInHand must not have changed.
     * After the move, the player's library must not have changed.
     * After the move, the player must not have changed.
     */
    @Test
    public void onPutInLibraryMoveNotOK(){
        controller.onMessageReceived(new MaxPlayersMessage("Client1", 2));
        controller.onMessageReceived(new UserInfoForLoginMessage("Client1", "Alice"));
        controller.onMessageReceived(new UserInfoForLoginMessage("Client2", "Bob"));
        ObjectCard pickedObject = controller.getGame().getBoard().getSpace(4,2).getObject();
        controller.onMessageReceived(new PickObjectMessage("Alice", new ArrayList<>(Arrays.asList(4,2))));
        ArrayList<ObjectCard> oldObjectsInHand = controller.getGame().getPlayerInTurn().getObjectsInHand();
        Library oldPlayerLibrary = controller.getGame().getPlayerInTurn().getLibrary();
        controller.onMessageReceived(new PutObjectInLibraryMessage("Alice", new ArrayList<>(Arrays.asList(0,1,0))));
        assertEquals(oldObjectsInHand, controller.getGame().getPlayerInTurn().getObjectsInHand());
        assertEquals(oldPlayerLibrary, controller.getGame().getPlayerInTurn().getLibrary());
        assertEquals("Alice", controller.getGame().getPlayerInTurn().getNickname());
        assertEquals("Bob", controller.getGame().getNextPlayer().getNickname());
    }

}
