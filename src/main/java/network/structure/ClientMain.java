package network.structure;

//import javafx.*;
import controller.ClientController;
import view.cli.*;
import java.util.logging.Level;
import network.structure.*;



public class ClientMain {
    public static void main(String[] args){

        boolean isCli=true;


        //check if CLI or GUI and if is CLI set isCli to true
       // for (String arg: args){
         //   if (arg.equals("CLI"))
          //      isCli=true;
        //    break;
        //}

        //if is Cli, create a cli

        if (isCli){
            Cli view = new Cli();
            ClientController clientController = new ClientController(view);
            view.addObserver(clientController);
            view.initCli();
        }

        //launch a GUI
        if (!isCli){
            //Application.launch(gui.class);
        }

    }
}

