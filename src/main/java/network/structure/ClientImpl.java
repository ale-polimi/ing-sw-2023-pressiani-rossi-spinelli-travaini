package network.structure;

import network.Message;
import network.MessageType;
import view.UI;


import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;

/**
 * this class represents a generic client. There are methods for the initialization and the methods for the UI
 */

public class ClientImpl extends UnicastRemoteObject implements Client{

   UI view = new UI();

    /**
     * method for the connection with the server
     * @param server is the server which the client is connecting to
     * @throws RemoteException when the connection is not possible
     */
    public ClientImpl(Server server) throws RemoteException {
        super();
        initialize(server);
    }

    /**
     * is the constructor
     * @param server is the server which the client is connecting to
     * @param port is the port where the connection is
     * @throws RemoteException because needs to
     */
    public ClientImpl(Server server, int port) throws RemoteException {
        super(port);
        initialize(server);
    }

    /**
     * connection with the server for creating connection with the server for custom socket
     * @param server is the server that needs the connection to be initialized
     * @param port is the port
     * @param csf is the client socket factory
     * @param ssf is the server socket factory
     * @throws RemoteException because we extend Unicast
     */
    public ClientImpl(Server server, int port, RMIClientSocketFactory csf, RMIServerSocketFactory ssf) throws RemoteException {
        super(port, csf, ssf);
        initialize(server);
    }


    /**
     * method for the initialization of the connection with the server
     * @param server is the server that needs to initialize the connection
     * @throws RemoteException if the connection is not possible
     */
    private void initialize(Server server) throws RemoteException {
        server.registry(this);
        view.addObserver((o, arg) -> {
            try {
                server.update(this /*String, MessageType*/);
            } catch (RemoteException e) {
                System.err.println("connection unable");
            }
        });

    }

    /**
     * notify the client a change
     * @throws RemoteException when the update isn't possible
     */
    public void update(/*TypeView typeView, Type type*/) throws RemoteException {

    }

    /**
     * method that asks, using the UI, the nickname to the client and send it to the server
     * @throws RemoteException when is not possible to send the message
     */

    public void requestNickname() throws RemoteException {
        String nickname= view.getNickname();
        Message message = new Message(this.toString(), nickname, MessageType.USER_INFO);

        try {
            listener.nicknameInput(message);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * method that asks, using the UI, the number of players in the game
     * @throws RemoteException when is not possible to send the message
     */

    public void requestMaxPlayer() throws RemoteException{
        int numPlayers= view.getNumPlayers();
        Message message = new Message(this.toString(),String.valueOf(numPlayers), MessageType.MAX_PLAYERS_FOR_GAME);

        try {
            listener.setMaxPlayer(message);
        } catch (RemoteException e) {
            throw new RuntimeException(e);

        }
    }

    /**
     * method for asking the client which move will do in his turn on the board
     * @throws RemoteException when is not possible to send the message
     */

    public void requestBoardMove() throws RemoteException {


    }

    public void requestLibraryMove() throws RemoteException {

    }

    public void waitTurn() throws RemoteException {

    }

    public void whoIsWinner() throws RemoteException {

    }
}
