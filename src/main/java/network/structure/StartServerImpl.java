package network.structure;

import controller.Controller;
import enumerations.GameState;
import network.messages.Message;
import network.messages.MessageType;
import observer.Observer;
import view.cli.Colours;

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

    /**
     * Custom constructor for StartServerImpl class
     * @throws IOException When there is an error during the creation of the RMI or socket connection
     */
    public StartServerImpl(int socketPort, int RMIPort) throws IOException {
        startRMI(RMIPort);
        startSocket(socketPort);
        controller = new Controller(this);
        executor.submit(this);
    }

    /**
     * Main method, used to create the instance of the server.
     * The user can decide on which ports the servers (socket and RMI) will start:
     * <ul>
     *     <li>First parameter: will define the port for the socket connection.</li>
     *     <li>Second parameter: will define the port for the RMI connection.</li>
     * </ul>
     */
    public static void main(String[] args){
        boolean failedArgs = false;
        final int defaultSocketPort = 12345;
        final int defaultRMIPort = 1099;
        int socketPort = 0;
        int RMIPort = 0;
        executor = Executors.newCachedThreadPool();



        if (args.length == 0) {
            socketPort = defaultSocketPort;
            RMIPort = defaultRMIPort;
        } else {
            if (args.length != 2) {
                System.out.println("" + Colours.RED + Colours.BOLD + "Invalid number of parameters. Required: 2; Provided: " + args.length + Colours.RESET);
                failedArgs = true;
            } else {

                /* Clean the command line parameters */
                try {
                    socketPort = Integer.parseInt(args[0]);
                } catch (NumberFormatException e) {
                    System.out.println("" + Colours.RED + Colours.BOLD + "Invalid input: " + args[0] + Colours.RESET);
                    failedArgs = true;
                }

                try {
                    RMIPort = Integer.parseInt(args[1]);
                } catch (NumberFormatException e) {
                    System.out.println("" + Colours.RED + Colours.BOLD + "Invalid input: " + args[1] + Colours.RESET);
                    failedArgs = true;
                }

                /* Check for the first argument */
                if (!isPortValid(socketPort)) {
                    System.out.println("" + Colours.RED + Colours.BOLD + "Invalid input: " + socketPort + Colours.RESET);
                    failedArgs = true;
                } else if (!isPortValid(RMIPort)) {
                    System.out.println("" + Colours.RED + Colours.BOLD + "Invalid input: " + RMIPort + Colours.RESET);
                    failedArgs = true;
                }
            }
        }

        if (!failedArgs) {
            try {
                new StartServerImpl(socketPort, RMIPort);
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Cannot instantiate the server structure");
                System.exit(-1);
            }
        } else {
            System.exit(2);
        }
    }

    /**
     * This method checks if the port is valid.
     * @param portToCheck is the port to verify.
     * @return {@code true} if the port is valid, {@code false} otherwise.
     */
    public static boolean isPortValid(int portToCheck){
        int port = portToCheck;
        if(port >= 1 && port <= 65535) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Return the instance of the RMI server
     * @return the serverRMI
     */
    public ServerRMI getServerRMI() {return serverRMI; }

    /**
     * Return the instance of the socket server
     * @return The socket server
     */
    public SocketServer getSocketServer() {return socketServer; }

    /**
     * Start the server RMI
     * @param port is the port for the RMI server.
     * @throws RemoteException Threw when the server cannot be reached
     */
    private void startRMI(int port) throws RemoteException {
        //Start RMI server instance
        serverRMI = new ServerRMI(this);
        Server stubRMI = (Server) UnicastRemoteObject.exportObject(serverRMI,port);
        LocateRegistry.createRegistry(port);
        Registry registry = LocateRegistry.getRegistry(port);
        registry.rebind("server", stubRMI);
        System.out.println("RMI server started on port: " + port + ", waiting for clients...");
        serverRMI.addObserver(this);
        getExecutor().submit(serverRMI);
    }


    /**
     * Start the server socket thread
     * @param port is the port for the Socket server
     */
    private void startSocket(int port){
        //Start Socket server instance
        socketServer = new SocketServer(this, port);
        getExecutor().submit(socketServer);
        System.out.println("Socket server started on port: " + port + ", waiting for clients...");
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

    /**
     * Handle the messages coming from RMI
     * @param message is the message used to share information.
     */
    @Override
    public void update(Message message) { receiveMessage(message);}

    /**
     * Run method of the server
     */
    @Override
    public void run() {
        while (!Thread.interrupted()) {
           try {
                serverRMI.ping();
            } catch (RemoteException e) {
                disconnect();
            }
            try {
                socketServer.ping();
            } catch (RemoteException e) {
                disconnect();
            }
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
