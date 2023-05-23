package network.structure;

import model.board.Board;
import model.board.BoardSpace;
import model.commonobjective.CommonObjective;
import model.library.Library;
import model.library.LibrarySpace;
import model.library.PersonalObjective;
import model.objects.ObjectCard;
import network.*;
import view.View;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This class hides the network to the controller.
 */
public class NetworkView implements View {
    private final StartServerImpl server;

    /**
     * Custom constructor of NetworkView
     * @param server is the server who is handling the client connection
     */
    public NetworkView(StartServerImpl server) {this.server = server;}

    /**
     * Gives the client related to the NetworkView
     * @return The server parameter of the NetworkView object
     */
    public StartServerImpl getServer(){return server;}

    /**
     * Send the update of the model to the clients
     * @param message It is the message containing the update of the model
     */
   public void sendMessageTo(Message message) {
       server.sendMessage(message);
   }

    /**
     * This method
      * @param players
     */
   @Override
    public void showLobby(ArrayList<String> players) {
        server.sendMessage(new ShowLobbyMessage(players));
    }

    /**
     * Send a message to the client to ask his/her nickname
     */
    @Override
    public void askNickname() {
        server.sendMessage(new AskNicknameMessage("Controller"));
    }

    /**
     * Send a message to the player asking the maximum number of player in the match
     */
    @Override
    public void askMaxPlayer() {
        server.sendMessage(new AskMaxPlayerMessage("Controller") );
    }

    /**
     * Send a message to the client asking what object cards wants to pick from the board
     */
    @Override
    public void askBoardMove() {
        server.sendMessage(new AskBoardMoveMessage("Controller"));
    }

    /**
     * Send a message to the client asking in which column the objects in hand have to be inserted
     */
    @Override
    public void askLibraryMove() {
        server.sendMessage(new AskLibraryMoveMessage("Controller"));
    }

    /**
     * Show the current turn of the game
     * @param player It is the player in turn
     * @param gameBoard It is the board of the game
     * @param playerLibrary It is player's library
     * @param playerObjInHand They are the cards chosen by the player
     */
    @Override
    public void showTurn(String player, Board gameBoard, Library playerLibrary, ArrayList<ObjectCard> playerObjInHand) {
        server.sendMessage(new ShowTurnMessage(player, gameBoard, playerLibrary, playerObjInHand));
    }

    /**
     * This method shows the winner at the end of the game.
     * @param winner is the username of the winner.
     * @param leaderboard is the leaderboard of the game.
     */
    @Override
    public void showWinner(String winner, HashMap<String, Integer> leaderboard) {
        server.sendMessage(new EndGameMessage(winner, leaderboard));
    }


    @Override
    public void showNotMyTurn(Board gameBoard) {}

    /**
     * Show the common objective of the game
     * @param commonObjective1 The first common objective
     * @param commonObjective2 The second common objective
     */
    @Override
    public void showCommonObjectives(String player,CommonObjective commonObjective1, CommonObjective commonObjective2) {
        server.sendMessage(new ShowCommonObjectiveMessage(player,commonObjective1,commonObjective2));
    }

    /**
     * Send a message containing the personal objective of the player
     * @param personalObjective It is the personal objective of the player
     */
    @Override
    public void showPersonalObjective(String player,PersonalObjective personalObjective) {
        server.sendMessage(new ShowPersonalObjectiveMessage(player,personalObjective));
    }

    /**
     * Send a message to a player to indicate a generic error
     * @param player The player to contact
     * @param payload The description of the error
     */
    @Override
    public void showGenericError(String player, String payload) {
        server.sendMessage(new GenericErrorMessage(player, payload));
    }

    /**
     * Call the disconnection to the game
     */
    public void disconnect(){
        server.disconnect();
    }
}
