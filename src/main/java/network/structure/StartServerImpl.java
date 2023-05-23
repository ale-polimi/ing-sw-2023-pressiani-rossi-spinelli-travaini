package network.structure;

import controller.Controller;
import exceptions.player.ClientNotRegisteredException;
import model.player.Player;
import network.GameClosedMessage;
import network.Message;
import network.MessageType;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StartServerImpl{
    private ServerRMI serverRMI;
    private SocketServer socketServer;
    //private final ArrayList<Tuple> games = new ArrayList<>();
    private Controller controller;
    private static ExecutorService executor;
    public StartServerImpl() throws IOException {
        startRMI();
        startSocket();
        controller = new Controller(this);
    }

    /**
     * Main method,create the instance of the server
     * @param args arguments given by command line
     */
    public static void main(String[] args){
        executor = Executors.newCachedThreadPool();
        try {new StartServerImpl();}
        catch(IOException e){System.err.println("Cannot instantiate the server structure"); System.exit(-1);}
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
        LocateRegistry.createRegistry(12345);
        Registry registry = LocateRegistry.getRegistry(12345);
        registry.rebind("server", serverRMI);
        System.out.println("RMI server started, waiting for clients...");
    }

    private void startSocket(){
        //Start Socket server instance
        socketServer = new SocketServer(this, 12000);
        getExecutor().submit(socketServer);
        System.out.println("Server socket up, waiting for client");
    }

    /**
     * Add a player to a game, if there aren't free spaces create a new game
     * @return The controller of the game where the player will be assigned
     */
    private Controller addToGame(){return controller;
        // Initialise a new game
            /*try {
                games.add(new Tuple(new Controller(this),new ArrayList<>()));
            } catch (IOException e) {
                System.err.println("ERROR: cannot create a game instance");
            }
            getExecutor().submit(games.get(0));
            return games.get(0).getController();*/
    }

    /**
     * Returns the controller related to the current client
     * @param nickname The nickname related to the client
     * @return The controller of the game where the player is in
     */
    private Controller getController(String nickname){
        /*for(Tuple t : games){*/
            for(Player p : controller.getGame().getPlayers()){if(p.getNickname().equals(nickname))return controller;}
        //}
        /*throw new ClientNotRegisteredException("You are not registered in a current game");*/
        return null;
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
    public void receiveMessage(Message message){
        if(!message.getType().equals(MessageType.USER_INFO))
            getController(message.getSender()).onMessageReceived(message);
        else {
            addToGame().onMessageReceived(message);
        }
    }

    /**
     * Send the message from the model to the views in the clients
     * @param message The message for the clients
     */
    public void sendMessage(Message message){serverRMI.sendMessage(message);socketServer.sendMessage(message);}

    /**
     * Close a game when a player wants to disconnect from the game
     */
    public  void disconnect(){
        serverRMI.disconnect();
        socketServer.disconnect();
    }
}
