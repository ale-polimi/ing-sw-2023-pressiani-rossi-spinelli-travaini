package network.structure;

import network.messages.*;
import observer.Observable;


import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

public class ServerRMI extends Observable implements Server,Runnable{

    private ArrayList<ClientHandler> clientsRMI;
    private ArrayList<Message> messages = new ArrayList<>();
    private final StartServerImpl startServer;
    private HashMap<String, Boolean> pingReceived=new HashMap<>();
    /**
     * Custom constructor of ServerRMI class
     * @throws RemoteException Threw when the server is unreachable
     */
    public ServerRMI(StartServerImpl startServer) throws RemoteException {
        super();
        this.startServer = startServer;
        this.clientsRMI = new ArrayList<>();
    }

    /**
     * Add the client to the list of known clients
     * @param clientHandler  the client handler that tries to connect to the server
     */
    @Override
    public void registry(ClientHandler clientHandler) throws RemoteException {
        if(!clientsRMI.contains(clientHandler)){
            System.out.println("Received");
            clientsRMI.add(clientHandler);
            clientHandler.receivedMessage(new  AskNicknameMessage("Controller"));
        }
    }

    /**
     * when called, reads the message
     * @param message is the message that has to be read
     */
    @Override
     public void receiveMessage(Message message){
        if(!message.getType().equals(MessageType.PING))messages.add(message);
        else {pingReceived.replace(message.getSender(), true);}
    }
    /**
     * Send a message to the clients
     * @param message The message that has to be forwarded
     */
    @Override
    public void sendMessage(Message message){
        if(message.getType().equals(MessageType.SHOW_LOBBY)){
            ShowLobbyMessage lm = (ShowLobbyMessage) message;
            pingReceived.put(lm.getLobbyPlayers().get(lm.getNumOfPlayers() - 1), true);}
        for(ClientHandler c : clientsRMI){
            try {
                System.out.println("Message sent:");
                c.receivedMessage(message);
            } catch (RemoteException e) {
               disconnect(c);
            }
        }
    }
    /**
     * End a game when a player disconnect from a game
     */
    @Override
    public void disconnect(ClientHandler clientHandler) {
        clientsRMI.remove(clientHandler);
       startServer.disconnect();
    }

    /**
     * Disconnect all the players from the game
     */
    public void disconnect(){
        for(ClientHandler c: clientsRMI){
            try {
                c.receivedMessage(new GameClosedMessage("Controller",MessageType.GAME_CLOSED));
            } catch (RemoteException e) {}
        }
        clientsRMI = new ArrayList<>();
        pingReceived = new HashMap<>();
    }

    /**
     * Check if a player is not connected anymore
     * @throws RemoteException
     */
    @Override
    public void ping() throws RemoteException {
        if(pingReceived.isEmpty())return;
        System.out.println(pingReceived);
        if(pingReceived.containsValue(false)) startServer.disconnect();
        for(String key : pingReceived.keySet()){
            pingReceived.replace(key,true,false);
        }
    }

    /**
     * Run method
     */
    @Override
    public void run() {
        while(!Thread.interrupted()){
            if(messages.size()>0)notifyObserver(messages.remove(0));
        }
    }

    /**
     * Getter method for the startServer parameter
     * @return the start server instance
     */
    public StartServerImpl getServer() {return startServer; }
}
