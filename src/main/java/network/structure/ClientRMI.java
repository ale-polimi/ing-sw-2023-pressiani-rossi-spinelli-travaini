package network.structure;

import network.MessageType;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;
import java.util.logging.Logger;
import java.util.logging.Level;

/*public void enablePing(boolean enabled){
*       if(enabled)
*           ping.scheduleAtFixedRate(()-> sendMessage (new PingMessage()), 0, 1000, TimeUnit.MILLISECOND);
*       else
*            ping.shutDownNow();
* }*/

/**
 * this class represents an RMI client and the method for the connection
 */

public class ClientRMI implements Client {

    private ObjectOutputStream oos ;
    private ObjectInputStream ois;


    private final int port;
    private final String nickname;

    private final Logger LOGGER = Logger.getLogger(getClass().getName());

    /**
     * constructor for a RNI client
     * @param nickname is the user nickname
     * @param port is he connection port
     */
    public ClientRMI(String nickname, int port){
        this.nickname = nickname;
        this.port = port;
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
     * Sends the given message to the server
     * @param message is the client message to send
     */
    public void sendMessage (MessageType message){
        try {
            oos.writeObject(message);
            oos.flush();
            oos.reset();

            /*
            TODO add message in MessageType for the login
             */

            LOGGER.log(Level.FINE, "Message sent: {0}", message);

        } catch (IOException e) {
            LOGGER.log(Level.INFO, "Could not send message: {0}. Exception : {1}", new Object[]{message, e});

            throw new RuntimeException(e);
        }
    }

    /**
     * close the connection between the client and the server
     * @param server is the server which the client is disconnecting to
     * @throws IOException if the connection is not open
     */
    public void closeConnection(Server server) throws IOException {
        /*
        TODO add a method "disconnection"
         */
        server = null;
    }

    @Override
    public void requestNickname() throws RemoteException {

    }

    @Override
    public void requestMaxPlayer() throws RemoteException {

    }

    @Override
    public void requestBoardMove() throws RemoteException {

    }

    @Override
    public void requestLibraryMove() throws RemoteException {

    }

    @Override
    public void waitTurn() throws RemoteException {

    }

    @Override
    public void whoIsWinner() throws RemoteException {

    }
}
