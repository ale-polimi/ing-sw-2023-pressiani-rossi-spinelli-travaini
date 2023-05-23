package network.structure;

import controller.Controller;
import exceptions.player.ClientNotRegisteredException;
import model.player.Player;
import network.GameClosedMessage;
import network.MaxPlayersMessage;
import network.Message;
import network.MessageType;


import java.io.IOException;
import java.net.ServerSocket;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerRMI extends UnicastRemoteObject implements Server{

    private ArrayList<ClientHandler> clients;
    private StartServerImpl startServer;
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
     * Custom constructor of ServerRMI class
     * @param port is the port of the server
     * @throws RemoteException Threw when the server is unreachable
     */
    public ServerRMI(int port) throws RemoteException {
        super(port);
    }

    /**
     * Custom constructor of ServerRMI class
     * @param port is the port of the server
     * @param csf
     * @param ssf
     * @throws RemoteException Threw when the server is unreachable
     */
    public ServerRMI(int port, RMIClientSocketFactory csf, RMIServerSocketFactory ssf) throws RemoteException {
        super(port, csf, ssf);
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
