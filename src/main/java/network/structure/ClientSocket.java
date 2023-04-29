package network.structure;

import network.*;
import view.UI;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.ArrayList;


/**
 * this class represents an RMI client and the method for the connection
 */

public class ClientSocket implements Client {

    private ObjectOutputStream oos ;
    private ObjectInputStream ois;

    private Socket socket;

    private UI view = new UI();


    private final int port;
    private Server server;



    /**
     * constructor for a RNI client
     * @param server is the server which the client is connecting to
     * @param port is he connection port
     */
    public ClientSocket(Server server, int port){
        this.server = server;
        this.port = port;
    }

    /**
     * method for the connection socket with the server
     * @throws IOException when there are problems with the communication with the sever
     * @throws RemoteException when there are problems with the connection
     */
    public void connectionSocket() throws IOException, RemoteException {

       try {
           Socket echoSocket= new Socket(server.toString(), port);
           oos = new ObjectOutputStream(echoSocket.getOutputStream());
           ois= new ObjectInputStream(echoSocket.getInputStream());

       }
       catch (IOException e){
           System.err.println("couldn't get IO for the connection");
       }
    }


    /**
     * notify the client a change
     * throws RemoteException
     */
    @Override
    public void update(/*TypeView typeViews, Type types*/) throws RemoteException {
        /*TODO*/
    }

    /**
     * receive a message from this server
     * @param server is the server that sends the message to the client
     * @throws RemoteException when the message is null
     */
    public void receive(Server server) throws RemoteException{

    }


    /**
     * method that asks, using the UI, the nickname to the client and send it to the server
     * @throws RemoteException when is not possible to send the message
     */

    @Override
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

    @Override
    public void requestMaxPlayer() throws RemoteException {
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

    @Override
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

    @Override
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

    @Override
    public void waitTurn() throws RemoteException {


    }

    /**
     * tells the user who is the winner and sends the message to the server
     * @throws RemoteException when is not possible to send the message
     */

    @Override
    public void whoIsWinner() throws RemoteException {


    }

    /**
     * method for sending a serialized message to the server
     * @param message is the message to send
     * @throws IOException if there are communication problems
     */
    public void sendMessageToServer(Message message) throws IOException {

        oos.writeObject(message);
        oos.flush();
        oos.reset();

    }


    /**
     * close the connection between the client and the server
     * @param server is the server which the client is disconnecting to
     * @throws IOException if the connection is not open
     */
    public void closeConnectionSocket(Server server) throws IOException {
        /*
        TODO add a method "disconnection" in the server
         */
        server = null;
    }

}
