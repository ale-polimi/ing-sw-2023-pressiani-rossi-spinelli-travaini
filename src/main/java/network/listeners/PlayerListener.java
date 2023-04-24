package network.listeners;

import network.Message;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PlayerListener extends Remote {
    /**
     * Listener that notify the server when a client wants to register to a game with a new nickname
     * @param message the message containing the player's nickname
     * @throws RemoteException if the server is unreachable
     */
    void nicknameInput(Message message) throws RemoteException;

    /**
     * Listener that notify the server when a player has chosen the maximum number of players in a game
     * @param message the message containing the maximum number of players
     * @throws RemoteException if the server is unreachable
     */
    void setMaxPlayer(Message message) throws RemoteException;

    /**
     * Listener that notify the server when a player has made a move regarding the board
     * @param message the message containing the coordinates of the cards chosen by the player
     * @throws RemoteException if the server is unreachable
     */
    void playerBoardMove(Message message) throws RemoteException;

    /**
     * Listener that notify the server when a player has made a move regarding his/her library
     * @param message is the message containing the column chosen by the player and the order of the cards to insert in the libraty
     * @throws RemoteException if the server is unreachable
     */
    void playerLibraryMove(Message message) throws RemoteException;
}
