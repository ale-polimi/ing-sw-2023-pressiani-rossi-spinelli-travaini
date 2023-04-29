package network.structure;

import network.*;
import view.UI;


import java.net.Socket;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * this class represents a generic client. There are methods for the initialization and the methods for the UI
 */

public class ClientRMI extends UnicastRemoteObject implements Client{

   UI view = new UI();

   private Server server;

   //default RMI port
   private int port= 1099;


    /**
     * constructor for the connection with the server
     * @param server is the server which the client is connecting to
     * @throws RemoteException when the connection is not possible
     */

    public ClientRMI(Server server) throws RemoteException {
        this.server=server;
        initialize(server);
    }

    /**
     * is the constructor with the connection with the server
     * @param server is the server which the client is connecting to
     * @param port is the port where the connection is
     * @throws RemoteException because needs to
     */
    public ClientRMI(Server server, int port) throws RemoteException {
        this.server=server;
        this.port=port;
        initialize(server);
    }



    /**
     * method for register a client to a game
     * @param server is the server which the client connects to for starting the game
     * @throws RemoteException if the connection is not possible
     */
    private void initialize(Server server) throws RemoteException {
            try {
                server.registry(this);
            } catch (RemoteException e) {
                System.err.println("connection unable");
            }
        }


    /**
     * method that returns the client port
     * @return the port
     */
    public int getPort() {
        return port;
    }


    /**
     * method for the actual connection to the server
     * @throws RemoteException when the connection is not possible
     * @throws NotBoundException when there is not a server named like in the lookup function
     */

    public void connectionRMI() throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(getPort());
        this.server = (Server) registry.lookup("server");
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
        Message message = new UserInfoForLoginMessage(this.toString(), nickname);

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
        Message message = new MaxPlayersMessage(this.toString(),numPlayers);

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
        ArrayList boardMove = new ArrayList<int[][]>(view.getBoardMove());
        Message message= new PickObjectMessage(this.toString(), boardMove);

        try {
            listener.playerBoardMove(message);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * method for asking the user which move will do in his turn on his library and sends message to the server
     * @throws RemoteException when is not possible to send the message
     */

    public void requestLibraryMove() throws RemoteException {
        ArrayList libraryMove = new ArrayList<int[][]>(view.getLibraryMove());
        Message message= new PutObjectInLibraryMessage(this.toString(), libraryMove);

        try {
            listener.playerLibraryMove(message);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * tells the user that he has to wait his turn and sends message to the server
     * @throws RemoteException when is not possible to send the message
     */

    public void waitTurn() throws RemoteException {


    }

    /**
     * tells the user who is the winner and sends the message to the server
     * @throws RemoteException when is not possible to send the message
     */
    public void whoIsWinner() throws RemoteException {

    }

    /**
     * method for the disconnection from the server
     * @throws RemoteException when there are problems with the connection
     */
    public void closeConnectionRMI(Server server) throws RemoteException{

        /*TODO disconnection method in the server*/

        server=null;
    }


}
