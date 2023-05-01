package network.structure;

import network.Message;
import network.MessageType;
import network.listeners.GameListener;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketHandler implements Runnable {

    private final SocketServer socketServer;
    private final Socket socket;
    private final  Object inputReader;
    private final Object outputWriter;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;

    public SocketHandler(SocketServer socketServer, Socket socket){
        this.socketServer = socketServer;
        this.socket = socket;
        this.inputReader = new Object();
        this.outputWriter = new Object();
    }
    @Override
    public void run() {
        try{clientCommunication();}
        catch(IOException e){
            System.err.println("Client "+ socket.getInetAddress()+" is unreachable");}
    }

    private void clientCommunication() throws IOException {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                synchronized (inputReader) {
                    Message message = (Message) ois.readObject();
                    switch(message.getType()){
                        case USER_INFO -> socketServer.nicknameInput(message);
                        case MAX_PLAYERS_FOR_GAME -> socketServer.setMaxPlayer(message);
                        case PICK_OBJECT -> socketServer.playerBoardMove(message);
                        case PUT_OBJECT -> socketServer.playerLibraryMove(message);
                        /*TODO Implementare altri casi per i messaggi ricevuti*/
                    }
                    }
                }
            } catch (ClassCastException | ClassNotFoundException e) {
            System.err.println("Client thread malfunction");
        }
        socket.close();
    }

    public void sendMessage(Message message) {
        try {
            synchronized (outputWriter) {
                oos.writeObject(message);
                oos.flush();
                oos.reset();
            }
        } catch (IOException e) {
            System.err.println("Cannot reach the server");
        }
    }

}
