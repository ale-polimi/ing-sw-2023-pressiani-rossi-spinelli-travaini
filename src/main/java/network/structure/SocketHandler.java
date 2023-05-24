package network.structure;

import network.Message;
import network.MessageType;
import network.PingMessage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketHandler implements Runnable,ClientHandler {

    private final SocketServer socketServer;
    private final Socket socket;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;

    /**
     * Custom controller of the SocketHandler class
     * @param socketServer The corresponding socket server
     * @param socket the socket used for communicating
     */
    public SocketHandler(SocketServer socketServer, Socket socket){
        this.socketServer = socketServer;
        this.socket = socket;
        try {
            this.oos = new ObjectOutputStream(socket.getOutputStream()) ;
            this.ois = new ObjectInputStream(socket.getInputStream());
        }catch(IOException e){System.err.println("Cannot connect to the socket streams");}
    }

    /**
     * Run method of the SocketHandler class
     */
    @Override
    public void run() {
        try{clientCommunication();}
        catch(IOException e){
            System.err.println("Client "+ socket.getInetAddress()+" is unreachable");}
    }

    /**
     * Handle the communication with the server
     * @throws IOException when there is a malfunction with the socket
     */
    private void clientCommunication() throws IOException {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Message message = (Message) ois.readObject();
                System.out.println("Messaggio Ricevuto"+ message.getSender());
                socketServer.receiveMessage(message);
            }
        } catch (ClassCastException | ClassNotFoundException e) {
            System.err.println("Client thread malfunction"+ e);
        }
        //socket.close();
    }
    /**
     * Check that a client is still connected
     * @return true if the client is reachable, false otherwise
     */
    @Override
    public boolean isConnected() {
        try {
                oos.writeObject(new PingMessage("Server",MessageType.PING));
                oos.flush();
                oos.reset();
        }catch (IOException e) {return false;}
        return true;
    }


    /**
     * close the connection
     */
    @Override
    public void disconnect() {
        socketServer.disconnect();
        try{socket.close();}
        catch(IOException e){System.err.println("Cannot close the socket");}
    }

    /**
     * Forward a message to the client
     * @param message The message that has to be forwarded
     */
    @Override
    public void receivedMessage(Message message) {
        try{
                oos.writeObject(message);
                oos.flush();
                oos.reset();
                System.out.print("Message sent");
        } catch (IOException e) {throw new RuntimeException("SocketClient unreachable from server",e);}
    }
}
