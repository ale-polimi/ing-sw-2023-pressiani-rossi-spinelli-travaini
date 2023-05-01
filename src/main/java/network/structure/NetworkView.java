package network.structure;

import model.board.BoardSpace;
import model.library.LibrarySpace;
import view.View;

import java.rmi.RemoteException;
import java.util.List;

// This clas hides the network to the controller
public class NetworkView implements View {
    private final Client client;

    /**
     * Custom constructor of NetworkView
     * @param client is the client related to the NetworkView
     */
    public NetworkView(Client client) {
        this.client = client;
    }

    /**
     * Gives the client related to the NetworkView
     * @return The client parameter of the NetworkView object
     */
    public Client getClient(){return client;}

    /**
     * Invokes the client listener related to the request of a player nickname
     */
    @Override
    public void askNickname(){
        try{client.requestNickname();}
        catch(RemoteException e){System.err.println("Player cannot insert his/her nickname");}
    }

    /**
     * Invokes the client listener related to the request of the maximum number of players in a match
     */
    @Override
    public void askMaxPlayer() {
        try{client.requestMaxPlayer();}
        catch(RemoteException e){System.err.println("Player cannot insert the max number of player");}
        }

    /**
     * Invokes the client listener related to the request of a player moves on the board
     * @param board It gives the player a view of the current board in the game
     */
    @Override
    public void askBoardMove(BoardSpace[][] board) {
        try{client.requestBoardMove(board);}
        catch (RemoteException e ){System.err.println("Player cannot insert his/her board move");}
    }

    /**
     * Invokes the client listener related to the request of a player moves on the library
     * @param library  It gives the player a view of his/her current library in the game
     */
    @Override
    public void askLibraryMove(LibrarySpace[][] library) {
        try{client.requestLibraryMove(library);}
        catch(RemoteException e){System.err.println("Player cannot insert his/her library move");}
    }

    /**
     * Invokes the client listener related to the transmission of general messages from the game
     * @param message It represents the message that has to be transmitted
     */
    @Override
    public void generalMessage(String message) {
       try{ client.generalMessage(message);}
       catch(RemoteException e){System.err.println("Player cannot receive the message");}
    }

    /**
     * Invokes the client listener related to the showing the winner of a game
     * @param winner It represents the winner's nickname
     */
    @Override
    public void endGame(String winner) {
        try{client.whoIsWinner(winner);}
        catch(RemoteException e){System.err.println("Player cannot know who is the winner");}
    }

    /**
     * Invokes the client listener to show the current game status
     * @param players It is a list of the players' nicknames
     * @param board It is a representation of the current board
     * @param playersLibrary It is a list of the representation of the current players' library
     */
    @Override
    public void showMatchInfo(List<String> players, BoardSpace[][] board, List<LibrarySpace[][]> playersLibrary) {
        try{client.showMatchInfo(players, board, playersLibrary);}
        catch(RemoteException e){System.err.println("Player cannot receive general info about the game");}
    }

    /**
     * Invokes the client listener to put the player in wait of his/her turn
     */
    @Override
    public void waitTurn() {
        try{client.waitTurn();}
        catch(RemoteException e){System.err.println("Player is not caring about the game");}
    }
}
