package network.structure;

import network.Message;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServerSocket extends UnicastRemoteObject implements Server{
    private final ServerRMI server;
    private final int port;
    private Socket socket;
    private ObjectOutputStream oos;

    private ObjectInputStream ois;

    public ServerSocket(ServerRMI serverRMI, int port) throws RemoteException{
        this.server = serverRMI;
        this.port=port;
    }

    /**
     * Create a connection between the server and the client
     * @param client is the client that has to be registered
     * @throws RemoteException  the server isn't reachable, it wasn't possible to create an output/input stream
     */
    @Override
    public synchronized void registry(Client client) throws RemoteException {
        try{this.socket=new Socket("server",  port);
            try{this.oos = new ObjectOutputStream(socket.getOutputStream());}
            catch(IOException e){throw new RemoteException("Cannot create an output stream",e);}
            try{this.ois = new ObjectInputStream(socket.getInputStream());}
            catch(IOException e){throw new RemoteException("Cannot create an input stream",e);}
        }  catch (IOException e) {
            throw new RemoteException("Cannot reach the server", e);
        }
        server.registry(client);
    }

    /**
     * Close the server socket
     * @throws RemoteException the socket cannot be closed
     */
    public void close() throws RemoteException {
        try{socket.close();}
        catch (IOException e){throw new RemoteException("Cannot close socket",e);}
    }

    @Override
    public void nicknameInput(Message message) throws RemoteException {

    }

    @Override
    public void setMaxPlayer(Message message) throws RemoteException {

    }

    @Override
    public void playerBoardMove(Message message) throws RemoteException {

    }

    @Override
    public void playerLibraryMove(Message message) throws RemoteException {

    }
}
