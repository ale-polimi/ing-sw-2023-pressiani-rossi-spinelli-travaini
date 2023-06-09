package network.structure;

import controller.Controller;
import enumerations.GameState;
import network.messages.Message;
import network.messages.MessageType;
import observer.Observer;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StartServerImpl implements Observer, Runnable {
    private ServerRMI serverRMI;
    private SocketServer socketServer;
    //private final ArrayList<Tuple> games = new ArrayList<>();
    private Controller controller;
    private static ExecutorService executor;
    public StartServerImpl() throws IOException {
        startRMI();
        startSocket();
        controller = new Controller(this);
        executor.submit(this);
    }

    /**
     * Main method,create the instance of the server
     * @param args arguments given by command line
     */
    public static void main(String[] args){
        executor = Executors.newCachedThreadPool();
        try {new StartServerImpl();}
        catch(IOException e){e.printStackTrace();System.err.println("Cannot instantiate the server structure"); System.exit(-1);}
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
        Server stubRMI = (Server) UnicastRemoteObject.exportObject(serverRMI,1099);
        LocateRegistry.createRegistry(1099);
        Registry registry = LocateRegistry.getRegistry(1099);
        registry.rebind("server", stubRMI);
        System.out.println("RMI server started, waiting for clients...");
        serverRMI.addObserver(this);
        getExecutor().submit(serverRMI);
    }


    /**
     * Start the server socket thread
     */
    private void startSocket(){
        //Start Socket server instance
        socketServer = new SocketServer(this, 12345);
        getExecutor().submit(socketServer);
        System.out.println("Socket server up, waiting for clients...");
    }
    /**
     * Return the thread pool executor
     * @return The Executor service related to the server
     */
    public ExecutorService getExecutor(){return executor;}

    /**
     * Send a message received from the clients to the controller
     * @param message The message to forward
     */
    public void receiveMessage(Message message){
        if(!(!(message.getType().equals(MessageType.USER_INFO)||message.getType().equals(MessageType.MAX_PLAYERS_FOR_GAME))&&(controller.getGame().getGameState().equals(GameState.LOGIN))))
            controller.onMessageReceived(message);
    }

    /**
     * Send the message from the model to the views in the clients
     * @param message The message for the clients
     */
    public void sendMessage(Message message){serverRMI.sendMessage(message);socketServer.sendMessage(message);}

    /**
     * Close a game when one or more players disconnect from the game
     */
    public  void disconnect(){
        serverRMI.disconnect();
        socketServer.disconnect();
        try {
            controller = new Controller(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void update(Message message) { receiveMessage(message);}

    @Override
    public void run() {
        try {
            getServerRMI().ping();
        } catch (RemoteException e) {
            disconnect();
        }
        try {
            getSocketServer().ping();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
        try {
            wait(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
