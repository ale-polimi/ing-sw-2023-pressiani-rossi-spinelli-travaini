package network.structure;

import controller.Controller;
import exceptions.player.ClientNotRegisteredException;
import model.player.Player;
import network.Message;

import java.io.IOException;
import java.net.ServerSocket;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StartServerImpl {
    private ServerRMI serverRMI;
    private SocketServer socketServer;

    private static StartServerImpl startServer;
    private final HashMap<Integer, Tuple> games = new HashMap<>();
    private static ServerSocket serverSocket;
    private static ExecutorService executor;
    public StartServerImpl() throws IOException {
        startRMI();
        serverSocket = new ServerSocket(8080);
        startSocket();
    }

    /**
     * Main method,create the instance of the server
     * @param args arguments given by command line
     */
    public static void main(String[] args) throws IOException {
        executor = Executors.newCachedThreadPool();
        startServer = new StartServerImpl();
        while(true) try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.err.println("Server thread has been interrupted");
        }
    }

    /**
     * Return the instance of the RMI server
     * @return the serverRMI
     */
    private ServerRMI getServerRMI() {return serverRMI; }

    /**
     * Return the instance of the socket server
     * @return The socket server
     */
    private SocketServer getSocketServer() {return socketServer; }

    /**
     * Start the server RMI thread
     * @throws RemoteException Threw when the server cannot be reached
     */
    private void startRMI() throws RemoteException {
        //Start RMI server instance
        serverRMI = new ServerRMI(this);
        LocateRegistry.createRegistry(1234);
        Registry registry = LocateRegistry.getRegistry(1234);
        registry.rebind("server", serverRMI);
        System.out.println("RMI server started, waiting for clients...");
    }

    private void startSocket(){
        //Start Socket server instance
        SocketServer socketServer = new SocketServer(startServer, 12000);
        getExecutor().submit(socketServer);
    }


/*TODO eliminare ed aggiungere la connessione alla partita*/
    public void registry(ClientHandler clientHandler) throws RemoteException {
        for(Tuple t: games.values()){
            if (t.getClientHandlers().size() !=  t.getController().getGame().getPlayers().size() && t.getController().getGame().getPlayers().size() < t.getController().getGame().getMaxPlayers()) {
                t.addClientHandler(clientHandler);
                return;
            }
        }
        // Initialise a new game
        if(games.size() != 0) {
            try {
                games.put((Integer)games.keySet().toArray()[games.size()-1]+1, new Tuple(new Controller(this), new ArrayList<>()));
            } catch (IOException e) {
                System.err.println("ERROR: cannot create a controller");
            }
        }else{
            try {
                Tuple tuple = new Tuple(new Controller(this), new ArrayList<>());
                games.put(0, tuple);
                getExecutor().submit(tuple);
            } catch (IOException e) {
                System.err.println("ERROR: cannot create a controller");
            }
        }
        games.get(games.size() - 1).addClientHandler(clientHandler);
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
     * Return the thread pool executor
     * @return The Executor service related to the server
     */
    public static ExecutorService getExecutor(){return executor;}

    /**
     * Send a message received from the clients to the controller
     * @param message The message to forward
     */
    public void receiveMessage(Message message){getController(message.getSender()).onMessageReceived(message);}

    /**
     * Send the message from the model to the views in the clients
     * @param message The message for the clients
     */
    public void sendMessage(Message message){serverRMI.sendMessage(message);socketServer.sendMessage(message);}
}
