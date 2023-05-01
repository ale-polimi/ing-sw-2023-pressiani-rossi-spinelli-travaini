package network.structure;

import controller.Controller;
import java.util.ArrayList;


public class Tuple {
    //Controller of the game related to the clients
    private final Controller controller;
    //Clients which correspond to the players of the game
    private ArrayList<ClientHandler> clientHandlers;

    public Tuple(Controller controller, ArrayList<ClientHandler> clientHandlers){
        this.controller = controller;
        this.clientHandlers = clientHandlers;
    }

    /**
     * Getter of controller parameter
     * @return the controller parameter of the Tuple object
     */
    public Controller getController() {return controller;}

    /**
     * Getter of clients parameter
     * @return the clients parameter of the Tuple object
     */
    public ArrayList<ClientHandler> getClientHandlers() {return clientHandlers;}

    /**
     * Add a client to the clients list
     * @param clientHandler The client handler that has to be added to the clientHandlers parameter
     */
    public void addClientHandler(ClientHandler clientHandler){clientHandlers.add(clientHandler);}
}
