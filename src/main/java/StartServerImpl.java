import network.structure.Server;
import network.structure.ServerRMI;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StartServerImpl extends UnicastRemoteObject implements StartServer {

    private static StartServerImpl instance;

    private final ExecutorService executorService = Executors.newCachedThreadPool();

    /**
     * Custom constructor of StartServerImplementation class
     * @throws RemoteException Threw when the server cannot be reached
     */
    public StartServerImpl() throws RemoteException {super();}

    /**
     * Custom constructor of StartServerImplementation class
     * @param port is the port of the server
     * @throws RemoteException Threw when the server cannot be reached
     */
    public StartServerImpl(int port) throws RemoteException {super(port);}

    /**
     * Custom constructor of StartServerImplementation class
     * @param port is the port of the server
     * @param csf
     * @param ssf
     * @throws RemoteException Threw when the server cannot be reached
     */
    public StartServerImpl(int port, RMIClientSocketFactory csf, RMIServerSocketFactory ssf) throws RemoteException {super(port, csf, ssf);}

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
           public void run(){
               try{/*TODO startSocket();*/}
               catch(Exception e) {System.err.println("Cannot connect to socket server, disabling protocol");}
           }
        };
        socketThread.start();
    }

    /**
     * Return the server instance
     * @return the instance parameter of the StartServerImpl object
     * @throws RemoteException Threw when the server cannot be reached
     */
    private static StartServerImpl getInstance() throws RemoteException {
        return(instance == null)? new StartServerImpl() : instance;
    }

    /**
     * Create a new ServerRMI instance
     * @return a new ServerRMI object
     * @throws RemoteException Threw when the server is unreachable
     */
    @Override
    public Server connect() throws RemoteException {return new ServerRMI();}

    /**
     * Initialise the server RMI instance
     * @throws RemoteException Threw when the server is unreachable
     */
    private static void startRMI() throws RemoteException {
        StartServerImpl server = getInstance();
        Registry registry = LocateRegistry.getRegistry(1234);
        registry.rebind("server", server);
        System.out.println("Waiting for clients...");
    }
}



