package network.structure;

import network.messages.AskNicknameMessage;
import network.messages.GameClosedMessage;
import network.messages.Message;
import network.messages.MessageType;
import observer.Observable;


import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class ServerRMI extends Observable implements Server,Runnable{

    private final ArrayList<ClientHandler> clients;
    private ArrayList<Message> messages = new ArrayList<>();
    private final StartServerImpl startServer;
    /**
     * Custom constructor of ServerRMI class
     * @throws RemoteException Threw when the server is unreachable
     */
    public ServerRMI(StartServerImpl startServer) throws RemoteException {
        super();
        this.startServer = startServer;
        this.clients = new ArrayList<>();
    }

    /**
     * Add the client to the list of known clients
     * @param clientHandler  the client handler that tries to connect to the server
     */
    @Override
    public void registry(ClientHandler clientHandler) throws RemoteException {
        if(!clients.contains(clientHandler)){
            System.out.println("Received");
            clients.add(clientHandler);
            clientHandler.receivedMessage(new  AskNicknameMessage("Controller"));
        }
    }

    /**
     * when called, reads the message
     * @param message is the message that has to be read
     */
    @Override
     public void receiveMessage(Message message){
        System.out.println("Message Received");
        messages.add(message);
    }

    /**
     * Send a message to the clients
     * @param message The message that has to be forwarded
     */
    @Override
    public void sendMessage(Message message){
        for(ClientHandler c : clients){
            try {
                System.out.println("Message sent");
                c.receivedMessage(message);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }
    }
    /**
     * End a game when a player disconnect from a game
     */
    @Override
    public void disconnect() {
        for(ClientHandler c: clients){
            try {
                c.receivedMessage(new GameClosedMessage("Controller",MessageType.GAME_CLOSED));
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
            clients.remove(c);
        }
    }

    public List<ClientHandler> getClients() {return clients;}

    @Override
    public void run() {
        while(!Thread.interrupted()){
            if(messages.size()>0)notifyObserver(messages.remove(0));
        }
    }
}
