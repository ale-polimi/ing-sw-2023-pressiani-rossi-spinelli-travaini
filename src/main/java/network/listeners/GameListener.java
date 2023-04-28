package network.listeners;

import java.rmi.RemoteException;

public interface GameListener {

    /**
     * method that asks, using the UI, the nickname to the user and sends it to the server
     * @throws RemoteException when the message is null
     */
    public void requestNickname() throws RemoteException;

    /**
     * method that asks, using the UI, the number of players in the game
     * @throws RemoteException when is not possible to send the message
     */
    public void requestMaxPlayer() throws RemoteException;

    /**
     * method for asking the user which move will do in his turn on the board
     * @throws RemoteException when is not possible to send the message
     */
    public void requestBoardMove() throws RemoteException;

    /**
     * method for asking the user which move will do in his turn on his library and sends message to the server
     * @throws RemoteException when is not possible to send the message
     */
    public void requestLibraryMove() throws RemoteException;

    /**
     * tells the user that he has to wait his turn and sends message to the server
     * @throws RemoteException when is not possible to send the message
     */

    public void waitTurn() throws RemoteException;

    /**
     * tells the user who is the winner and sends the message to the server
     * @throws RemoteException when is not possible to send the message
     */

    public void whoIsWinner() throws RemoteException;
    
}
