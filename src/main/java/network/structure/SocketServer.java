package network.structure;

import network.Message;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.RemoteException;

public class SocketServer implements Runnable, Server{
    private final ServerRMI server;
    private final int port;
    ServerSocket serverSocket;

    public SocketServer(ServerRMI server, int port){
        this.server = server;
        this.port = port;
    }


    @Override
    public void run() {
        try {serverSocket = new ServerSocket(port);}
        catch ( IOException e) {System.err.println("Server could not start!");}
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Socket socket = serverSocket.accept();
                socket.setSoTimeout(5000);
                SocketHandler clientHandler = new SocketHandler(this, socket);
                Thread thread = new Thread(clientHandler, "ss_handler" + socket.getInetAddress());
                thread.start();
            } catch (IOException e) {
                System.err.println("Server socket unreachable ");
            }
        }
    }

    @Override
    public void nicknameInput(Message message) throws RemoteException {
        server.nicknameInput(message);
    }

    @Override
    public void setMaxPlayer(Message message) throws RemoteException {
        server.setMaxPlayer(message);
    }

    @Override
    public void playerBoardMove(Message message) throws RemoteException {
        server.playerBoardMove(message);
    }

    @Override
    public void playerLibraryMove(Message message) throws RemoteException {
        server.playerLibraryMove(message);
    }

    @Override
    public void registry(ClientHandler clientHandler) throws RemoteException {
        server.registry(clientHandler);
    }
}
