package network.structure;

import controller.Controller;
import java.util.ArrayList;


public class Tuple implements Runnable{
    //Controller of the game related to the clients
    private final Controller controller;
    //Clients which correspond to the players of the game
    private final ArrayList<ClientHandler> clientHandlers;

    /**
     * constructor
     * @param controller is the controller of the game
     * @param clientHandlers is the list of clients
     */
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

    /**
     * connection to the game
     */
    @Override
    public void run() {
        /*TODO implementare la connessione al gioco*/
    }
}
