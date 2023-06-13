package network.structure;

import model.board.Board;
import model.commonobjective.CommonObjective;
import model.library.Library;
import model.library.PersonalObjective;
import model.objects.ObjectCard;
import network.messages.*;
import view.View;

import java.util.ArrayList;
import java.util.HashMap;

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

   @Override
    public void showLobby(ArrayList<String> players) {
        server.sendMessage(new ShowLobbyMessage(players));
    }

    @Override
    public void askNickname() {
        server.sendMessage(new AskNicknameMessage("Controller"));
    }

    @Override
    public void askMaxPlayer() {
        server.sendMessage(new AskMaxPlayerMessage("Controller") );
    }

    @Override
    public void askBoardMove() {
        server.sendMessage(new AskBoardMoveMessage("Controller"));
    }

    @Override
    public void askLibraryMove() {
        server.sendMessage(new AskLibraryMoveMessage("Controller"));
    }

    @Override
    public void showTurn(String player, Board gameBoard, Library playerLibrary, ArrayList<ObjectCard> playerObjInHand, boolean[] completedCommonObjectives) {
        server.sendMessage(new ShowTurnMessage(player, gameBoard, playerLibrary, playerObjInHand, completedCommonObjectives));
    }

    @Override
    public void showWinner(String winner, HashMap<String, Integer> leaderboard) {
        server.sendMessage(new EndGameMessage(winner, leaderboard));
    }

    @Override
    public void showNotMyTurn(Board gameBoard) {}

    @Override
    public void showCommonObjectives(String player, CommonObjective commonObjective1, CommonObjective commonObjective2, boolean[] completedCommonObjectives) {
        server.sendMessage(new ShowCommonObjectiveMessage(player,commonObjective1,commonObjective2));
    }

    @Override
    public void showPersonalObjective(String player,PersonalObjective personalObjective) {
        server.sendMessage(new ShowPersonalObjectiveMessage(player,personalObjective));
    }

    @Override
    public void showGenericError(String player, String payload) {
        server.sendMessage(new GenericErrorMessage(player, payload));
    }

    @Override
    public void showOthersLibrary(String sender, HashMap<String, Library> librariesOfPlayers) {
        server.sendMessage(new ShowLibrariesMessage(sender, librariesOfPlayers));
    }

    @Override
    public void showChat(String sender, String message) {server.sendMessage(new ChatMessage(sender,message));}
}
