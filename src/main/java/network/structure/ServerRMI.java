package network.structure;

import network.messages.*;
import observer.Observable;


import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class represents an instance for the RMI server.
 */
public class ServerRMI extends Observable implements Server,Runnable{

    private ArrayList<ClientHandler> clientsRMI;
    private ArrayList<Message> messages = new ArrayList<>();
    private final StartServerImpl startServer;
    private HashMap<String, Boolean> pingReceived=new HashMap<>();

    /**
     * Custom constructor for the RMI server.
     * @param startServer is the server's main that starts this instance.
     * @throws RemoteException is thrown when the server is not reachable.
     */
    public ServerRMI(StartServerImpl startServer) throws RemoteException {
        super();
        this.startServer = startServer;
        this.clientsRMI = new ArrayList<>();
    }

    @Override
    public void registry(ClientHandler clientHandler) throws RemoteException {
        if(!clientsRMI.contains(clientHandler)){
            System.out.println("Received");
            clientsRMI.add(clientHandler);
            clientHandler.receivedMessage(new  AskNicknameMessage("Controller"));
        }
    }

    @Override
     public void receiveMessage(Message message){
        if(!message.getType().equals(MessageType.PING))messages.add(message);
        else {pingReceived.replace(message.getSender(), true);}
    }


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


    @Override
    public void disconnect(ClientHandler clientHandler) {
        clientsRMI.remove(clientHandler);
       startServer.disconnect();
    }

    /**
     * This method disconnects all clients from this server.
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

    @Override
    public void ping() throws RemoteException {
        if(pingReceived.isEmpty())return;
        if(pingReceived.containsValue(false)) startServer.disconnect();
        for(String key : pingReceived.keySet()){
            pingReceived.replace(key,true,false);
        }
    }

    @Override
    public void run() {
        while(!Thread.interrupted()){
            if(messages.size()>0)notifyObserver(messages.remove(0));
        }
    }

    /**
     * Getter method for the startServer parameter.
     * @return the start server instance.
     */
    public StartServerImpl getServer() {return startServer; }
}
