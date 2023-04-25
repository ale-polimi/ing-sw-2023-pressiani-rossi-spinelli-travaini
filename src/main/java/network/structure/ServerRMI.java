package network.structure;

import controller.Controller;
import exceptions.player.ClientNotRegisteredException;
import model.player.Player;
import network.Message;


import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;

public class ServerRMI extends UnicastRemoteObject implements Server{

    private final HashMap<Integer, Tuple> games = new HashMap<>();

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


    /*TODO: Handler of messages from clients*/

    /**
     * Add the client to the list of known clients
     * @param client the client that tries to connect to the server
     */
    @Override
    public synchronized void registry(Client client) {
        for(Tuple t: games.values()){
            if (t.getController().getGame().getPlayers().size() < t.getController().getGame().getMaxPlayers()) {
                t.addClient(client);
                /*TODO Procedura di inizializzazione client*/
                return;
            }
        }
        // Initialise a new game
        try{games.put(games.size(),new Tuple(new Controller(), new ArrayList<>()));}
        catch(IOException e){System.err.println("ERROR: cannot create a controller");}
        games.get(games.size()-1).addClient(client);
        /*TODO Chiamata al client per settare il nickname e numero di partecipanti*/
    }

    /**
     * Returns the oontroller related to the current client
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
