package network.structure;

import controller.Controller;
import exceptions.player.ClientNotRegisteredException;
import model.player.Player;
import network.Message;


import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;

public class ServerRMI extends UnicastRemoteObject implements Server{

    private static ServerRMI serverRMI;
    private static ServerSocket serverSocket;
    private final HashMap<Integer, Tuple> games = new HashMap<>();
    private static final ArrayList<GameHandler> gameHandlers = new ArrayList<>();

    /**
     * Custom constructor of ServerRMI class
     * @throws RemoteException Threw when the server is unreachable
     */
    public ServerRMI() throws RemoteException {
        super();
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
     * Main method,create the instance of the server
     * @param args arguments given by command line
     */
    public static void main(String[] args) {
        //Start RMI server instance
        Thread rmiThread = new Thread() {
            @Override
            public void run() {
                try {startRMI();}
                catch (RemoteException e) {System.err.println("Cannot start RMI instance, disabling protocol");}
            }
        };
        rmiThread.start();

        //Start Socket server instance
        Thread socketThread = new Thread() {
            @Override
            public void run(){
                try{startSocket(serverRMI);}
                catch(RemoteException e) {System.err.println("Cannot connect to socket server, disabling protocol");}
            }
        };
        socketThread.start();
    }

    /**
     * Return the instance of the server
     * @return the serverRMI
     * @throws RemoteException Threw when the creation of a server fails
     */
    private static ServerRMI getServerRMI() throws RemoteException {
        if(serverRMI == null) serverRMI = new ServerRMI(1234);
        return serverRMI;
    }

    /**
     * Start the server RMI thread
     * @throws RemoteException Threw when the server cannot be reached
     */
    private static void startRMI() throws RemoteException {
        ServerRMI serverRMI = getServerRMI();
        LocateRegistry.createRegistry(1234);
        Registry registry = LocateRegistry.getRegistry(1234);
        registry.rebind("server", serverRMI);
        System.out.println("RMI server started, waiting for clients...");
    }

    private static void startSocket(ServerRMI serverRMI) throws RemoteException {
        serverSocket = new ServerSocket(serverRMI,, 1234);
    }
    /*TODO: Handler of messages from clients*/

    /**
     * Add the client to the list of known clients
     * @param client the client that tries to connect to the server
     */
    @Override
    public void registry(Client client) throws RemoteException {
        for(Tuple t: games.values()){
            if (t.getController().getGame().getPlayers().size() < t.getController().getGame().getMaxPlayers()) {
                t.addClient(client);
                client.login();
                if (t.getController().getGame().getMaxPlayers()==t.getController().getGame().getMaxPlayers()){
                    gameHandlers.add(new GameHandler(t));
                    gameHandlers.get(gameHandlers.size()-1).start();
                }
                return;
            }
        }
        // Initialise a new game
        try{games.put(games.size(),new Tuple(new Controller(), new ArrayList<>()));}
        catch(IOException e){System.err.println("ERROR: cannot create a controller");}
        games.get(games.size() - 1).addClient(client);
        /*TODO Chiamata al client per settare il nickname e numero di partecipanti*/
        client.login();
        client.setMaxPlayer();
    }

    /**
     * Returns the controller related to the current client
     * @param nickname The nickname related to the client
     * @return The controller of the game where the player is in
     */
    private Controller getController(String nickname){
        for(Tuple t: games.values()){
            for(Player p : t.getController().getGame().getPlayers()){if(p.getNickname().equals(nickname))return t.getController();}
        }
        throw new ClientNotRegisteredException("You are not registered in a current game");
    }

    /**
     * Listener that execute the actions related to the input of a new nickname
     * @param message the message containing the player's nickname
     * @throws RemoteException Threw when the server is unreachable
     */
    @Override
    public void nicknameInput(Message message) throws RemoteException {
        for(Tuple t : games.values()){
            t.getController().onMessageReceived(message);
        }
    }

    /**
     * Listener that execute the actions related to the setting of the maximum number of players in a game
     * @param message the message containing the maximum number of players
     * @throws RemoteException Threw when the server is unreachable
     */
    @Override
    public void setMaxPlayer(Message message) throws RemoteException {
        games.get(games.size()-1).getController().onMessageReceived(message);
    }

    /**
     * Listener that execute the actions related to a player's move on the board
     * @param message the message containing the coordinates of the cards chosen by the player
     * @throws RemoteException Threw when the server is unreachable
     */
    @Override
    public void playerBoardMove(Message message) throws RemoteException {
        getController(message.getSender()).onMessageReceived(message);
    }

    /**
     * Listener that execute the actions related to a player's move on the library
     * @param message is the message containing the column chosen by the player and the order of the cards to insert in the libraty
     * @throws RemoteException Threw when the server is unreachable
     */
    @Override
    public void playerLibraryMove(Message message) throws RemoteException {
        getController(message.getSender()).onMessageReceived(message);
    }
}
