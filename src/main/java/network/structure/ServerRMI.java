package network.structure;

import network.messages.*;
import observer.Observable;


import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ServerRMI extends Observable implements Server,Runnable{

    private ArrayList<ClientHandler> clients;
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
        if(!message.getType().equals(MessageType.PING))messages.add(message);
        else {pingReceived.replace(message.getSender(),true);
            System.out.println("message = " + message);}
    }
    /**
     * Send a message to the clients
     * @param message The message that has to be forwarded
     */
    @Override
    public void sendMessage(Message message){
        if(message.getType().equals(MessageType.SHOW_LOBBY)){System.out.println("Clientn to agga");pingReceived.put(message.getSender(), true);}
        for(ClientHandler c : clients){
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
        clients.remove(clientHandler);
       startServer.disconnect();
    }

    public void disconnect(){
        for(ClientHandler c: clients){
            try {
                c.receivedMessage(new GameClosedMessage("Controller",MessageType.GAME_CLOSED));
            } catch (RemoteException e) {}
        }
        clients = new ArrayList<>();
        pingReceived = new HashMap<>();
    }
    @Override
    public void ping() throws RemoteException {
        System.out.println("Alrltr: "+ pingReceived);
        if(pingReceived.containsValue(false))startServer.disconnect();
       else  for(String key : pingReceived.keySet()){pingReceived.replace(key,true,false);
            System.out.println(pingReceived);}
    }


    @Override
    public void run() {
        while(!Thread.interrupted()){
            if(messages.size()>0)notifyObserver(messages.remove(0));
        }
    }

    public StartServerImpl getServer() {return startServer; }
}
