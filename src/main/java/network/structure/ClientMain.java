package network.structure;

import controller.ClientController;
//import javafx.application.Application;
//import javafx.stage.Stage;
//import view.Gui;
import view.cli.*;



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
          //Application.launch(Gui.class);
        }

    }

}

