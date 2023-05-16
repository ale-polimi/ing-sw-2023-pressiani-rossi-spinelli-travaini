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

// This clas hides the network to the controller
public class NetworkView implements View {
    private final Server server;

    /**
     * Custom constructor of NetworkView
     * @param server is the server who is handling the client connection
     */
    public NetworkView(Server server) {this.server = server;}

    /**
     * Gives the client related to the NetworkView
     * @return The server parameter of the NetworkView object
     */
    public Server getServer(){return server;}

    /**
     * Send the update of the model to the clients
     * @param message It is the message containing the update of the model
     */
   public void sendMessageTo(Message message) {
        try{server.sendMessage(message);}
        catch(RemoteException e){System.err.println("Cannot send the message to the view");}
   }

    @Override
    public void showLobby(ArrayList<String> players) {
        try{
            server.sendMessage(new ShowLobbyMessage(players));
        } catch (RemoteException e){
            System.err.println("Cannot send the message to the view");
        }
    }

    @Override
    public void askNickname() {
       try{server.sendMessage(new AskNicknameMessage("Controller"));}
       catch(RemoteException e){System.err.println("Cannot reach the player for nickname");}
    }

    @Override
    public void askMaxPlayer() {
       try{server.sendMessage(new AskMaxPlayerMessage("Controller") );}
       catch(RemoteException e){System.err.println("Cannot reach the view for max players action");}
    }

    @Override
    public void askBoardMove() {
       try{server.sendMessage(new AskBoardMoveMessage("Controller"));}
       catch(RemoteException e){System.err.println("Cannot reach the view for board move");}
    }

    @Override
    public void askLibraryMove() {
       try{server.sendMessage(new AskLibraryMoveMessage("Controller"));}
       catch(RemoteException e){System.err.println("Cannot reach the view for library move");}
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
        try{
            server.sendMessage(new ShowTurnMessage(player, gameBoard, playerLibrary, playerObjInHand));
        } catch (RemoteException e){
            System.err.println("Cannot send the message to the view");
        }
    }

    /**
     * This method shows the winner at the end of the game.
     * @param winner is the username of the winner.
     * @param leaderboard is the leaderboard of the game.
     */
    @Override
    public void showWinner(String winner, HashMap<String, Integer> leaderboard) {
        try {
            server.sendMessage(new EndGameMessage(winner, leaderboard));
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Show the common objective of the game
     * @param commonObjective1 The first common objective
     * @param commonObjective2 The second common objective
     */
    @Override
    public void showCommonObjectives(CommonObjective commonObjective1, CommonObjective commonObjective2) {
        try{ server.sendMessage(new ShowCommonObjectiveMessage("Game",commonObjective1,commonObjective2));}
        catch(RemoteException e){System.err.println("Cannot reach the view for the common objectives");}
    }

    /**
     * Send a message containing the personal objective of the player
     * @param personalObjective It is the personal objective of the player
     */
    @Override
    public void showPersonalObjective(PersonalObjective personalObjective) {
        try{server.sendMessage(new ShowPersonalObjectiveMessage("Game",personalObjective));}
        catch(RemoteException e){System.err.println("Cannot send the personal objective to the view");}
    }

    @Override
    public void showGenericError(String player, String payload) {
        try {
            server.sendMessage(new GenericErrorMessage(player, payload));
        } catch (RemoteException e) {
            System.err.println("Cannot send the message to the view");
        }
    }
}
