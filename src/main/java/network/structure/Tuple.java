package network.structure;

import controller.Controller;
import java.util.ArrayList;


public class Tuple {
    //Controller of the game related to the clients
    private final Controller controller;
    //Clients which correspond to the players of the game
    private ArrayList<Client> clients;

    public Tuple(Controller controller, ArrayList<Client> clients){
        this.controller = controller;
        this.clients = clients;
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
    public ArrayList<Client> getClients() {return clients;}

    /**
     * Add a client to the clients list
     * @param client The client that has to be added to the clients parameter
     */
    public void addClient(Client client){clients.add(client);}
}
