package network.structure;


import network.messages.GenericErrorMessage;
import network.messages.Message;
import network.messages.MessageType;
import network.messages.PingMessage;
import observer.Observable;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.net.Socket;
import java.util.concurrent.*;
import java.util.logging.Logger;


/**
 * this class represents a socket client
 */

public class ClientSocket extends Observable implements Client {

    private ObjectOutputStream oos ;
    private ObjectInputStream ois;
    private Socket socket;
    private final int port;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private String nickname;
    Server server;
    final String address;
    boolean getConnected =false;
    ScheduledExecutorService timer;

    private final Logger LOGGER = Logger.getLogger(getClass().getName());


    /**
     * constructor for a socket client
     * @param address is the address of the client
     * @param port is he connection port
     */
    public ClientSocket(String address, int port){
        System.out.println("Entrato in client");
        this.address=address;
        this.port = port;
        try{connection();}
        catch(IOException e){System.err.println("Error connecting to the socket"); System.exit(1);}
        receivedMessage();
    }

    /**
     * method for the connection socket with the server
     * @throws IOException when the server is unreachable
     */

    public void connection() throws IOException {

       try {
           socket= new Socket(address, port);
           this.oos = new ObjectOutputStream(socket.getOutputStream());
           System.out.println(socket.getInputStream());
           this.ois= new ObjectInputStream(socket.getInputStream());
           this.timer = Executors.newSingleThreadScheduledExecutor();
           getConnected=true;
       }
       catch (IOException e){
           System.err.println("couldn't get IO for the connection");
           closeConnection();
           notifyObserver(new GenericErrorMessage(null,"Connection unable"));
       }

    }

    /**
     * Forwards the message in input
     * @param message is the message to send
     */
    @Override
    public void sendMessage(Message message) {
        try {
            if(message.getType().equals(MessageType.USER_INFO))nickname = message.getSender();
            oos.writeObject(message);
            oos.flush();
            oos.reset();
        } catch (IOException e) {
            try {
                closeConnection();
            } catch (IOException exception) {
                System.exit(1);
            }
            getConnected = false;
            notifyObserver(new GenericErrorMessage("client", "Impossible send message"));
        }
    }

    /**
     * when called, it reads the message
     */
    public void receivedMessage() {
        executor.execute(()->{
        while(!executor.isShutdown()){
            Message message;
        try {
            message = (Message) ois.readObject();
            LOGGER.info("Received: "+ message);

            /* TODO - Debug print */
            System.out.println(message.getSender()+" "+ message.getType());
            notifyObserver(message);
        }
        catch (IOException e) {
            executor.shutdownNow();
            disconnect();

        }
        catch (ClassNotFoundException e) {
            //it doesn't read the message (?)
        }}});
    }


    /**
     * close the connection between the client and the server
     * @throws IOException if the connection is already close
     */
    public void closeConnection() throws IOException {
        if (!socket.isClosed()){
            socket.close();
            getConnected=false;
        }
        ois=null;
        oos=null;
        System.out.println("Connection closed, terminating the application...");
        System.exit(1);
    }


    /**
     * is a boolean method that checks the connection
     * @return the state of the connection
     */
    public boolean isConnected() {
        return getConnected;
    }


    /**
     * Disconnect the client
     */
    public void disconnect() {
        try{
            closeConnection();;
        }
        catch (IOException e){
            Logger.getLogger("client").severe(e.getMessage());
        }

    }

    @Override
    public void receivedMessage(Message message) {
        //Voluntarily leaved empty
    }

    /**
     * check the presence of problems in the connection between client and server
     */
    @Override
    public void ping() {timer.scheduleAtFixedRate(() -> sendMessage(new PingMessage(nickname, MessageType.PING)), 0, 5000, TimeUnit.MILLISECONDS);}
}
