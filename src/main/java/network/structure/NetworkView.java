package network.structure;

import model.board.BoardSpace;
import model.commonobjective.CommonObjective;
import model.library.Library;
import model.library.LibrarySpace;
import model.library.PersonalObjective;
import network.Message;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

// This clas hides the network to the controller
public class NetworkView{
    private final Server server;

    /**
     * Custom constructor of NetworkView
     * @param server is the server who is handling the client connection
     */
    public NetworkView(Server server) {this.server = server;}

    /**
     * Gives the client related to the NetworkView
     * @return The server parameter of the NetworkView object
     */
    public Server getServer(){return server;}

    /**
     * Send the update of the model to the clients
     * @param message It is the message containing the update of the model
     */
   public void sendMessageTo(Message message) {
        try{server.sendMessage(message);}
        catch(RemoteException e){System.err.println("Cannot send the message to the view");}
   }
}
