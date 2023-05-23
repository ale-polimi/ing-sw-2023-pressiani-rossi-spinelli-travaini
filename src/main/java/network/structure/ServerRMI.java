package network.structure;

import network.GameClosedMessage;
import network.Message;
import network.MessageType;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class ServerRMI extends UnicastRemoteObject implements Server{

    private ArrayList<ClientHandler> clients;
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
        if(!clients.contains(clientHandler))clients.add(clientHandler);
    }

    @Override
 public void receiveMessage(Message message){startServer.receiveMessage(message);}

    /**
     * Send a message to the clients
     * @param message The message that has to be forwarded
     */
    @Override
    public void sendMessage(Message message){
        for(ClientHandler c : clients){c.receivedMessage(message);}
    }
    /**
     * End a game when a player disconnect from a game
     */
    @Override
    public void disconnect() {
        for(ClientHandler c: clients){
            c.receivedMessage(new GameClosedMessage("Controller",MessageType.GAME_CLOSED));
            clients.remove(c);
        }
    }
}
