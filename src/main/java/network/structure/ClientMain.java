package network.structure;

import controller.ClientController;
//import javafx.application.Application;
//import javafx.stage.Stage;
//import view.Gui;
import javafx.application.Application;
import view.cli.*;
import view.gui.Gui;
import view.gui.JavaFXGUI;


/**
 * Main class of the client application. The user can decide what type of client to launch with the following command line parameters:
 * <ul>
 *     <li>First parameter:</li>
 *     <ul>
 *         <li>"{@code -cli}" or "{@code -c}" will start the CLI game;</li>
 *         <li>"{@code -gui}" or "{@code -g}" will start the GUI game;</li>
 *     </ul>
 *     <li>Second parameter:</li>
 *     <ul>
 *         <li>"{@code -socket}" or "{@code -s}" will start the game based on a socket connection;</li>
 *         <li>"{@code -rmi}" or "{@code -r}" will start the game based on a RMI connection;</li>
 *     </ul>
 * </ul>
 */
public class ClientMain {
    public static void main(String[] args) {

        /* TODO - REMOVE COMMENT HERE FOR FINAL DEPLOYMENT */
        boolean isCli = true;
        boolean isSocket = true;
        boolean failedArgs = false;

        if (args.length != 2) {
            System.out.println("" + Colours.RED + Colours.BOLD + "Invalid number of parameters. Required: 2; Provided: " + args.length + Colours.RESET);
            failedArgs = true;
        } else {

            /* Check for the first argument */
            if (args[0].equals("-cli") || args[0].equals("-c")) {
                isCli = true;
            } else if (args[0].equals("-gui") || args[0].equals("-g")) {
                isCli = false;
            } else {
                System.out.println("" + Colours.RED + Colours.BOLD + "Invalid parameter: " + args[0] + Colours.RESET);
                failedArgs = true;
            }

            /* Check for the second argument */
            if (args[1].equals("-socket") || args[1].equals("-s")) {
                isSocket = true;
            } else if (args[1].equals("-rmi") || args[1].equals("-r")) {
                isSocket = false;
            } else {
                System.out.println("" + Colours.RED + Colours.BOLD + "Invalid parameter: " + args[1] + Colours.RESET);
                failedArgs = true;
            }
        }

        if (!failedArgs) {
            if (isCli) {
                Cli view = new Cli(isSocket);
                ClientController clientController = new ClientController(view, isSocket);
                view.addObserver(clientController);
                view.initCli();
            } else {
                JavaFXGUI.setIsSocket(isSocket);
                Application.launch(JavaFXGUI.class);
            }
        } else {
            System.exit(2);
        }

        /*
        *//* TODO - REMOVE THIS! ONLY FOR DEBUG *//*
        boolean isCli = false;
        boolean isSocket = false;

        if (isCli) {
            Cli view = new Cli(isSocket);
            ClientController clientController = new ClientController(view, isSocket);
            view.addObserver(clientController);
            view.initCli();
        } else {
            *//* TODO - GUI application launch *//*
            JavaFXGUI.setIsSocket(isSocket);
            Application.launch(JavaFXGUI.class);
        }
        */
    }

}

