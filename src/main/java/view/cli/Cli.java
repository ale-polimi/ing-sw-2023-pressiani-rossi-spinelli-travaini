package view.cli;

import controller.ClientController;
import enumerations.ObjectColour;
import enumerations.TypeSpace;
import model.board.Board;
import model.library.Library;
import model.objects.ObjectCard;
import network.Message;
import observer.ViewObservable;
import view.View;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Command line interface (CLI) for the game.
 */
public class Cli extends ViewObservable implements View {

    private static final String STR_INPUT_CANCELED = "User input canceled.";
    private final PrintStream out;
    private Thread inputThread;

    public Cli() {
        out = System.out;
    }

    /**
     * Reads a line from the standard input.
     *
     * @return the string read from the input.
     * @throws ExecutionException if the input stream thread is interrupted.
     */
    public String readLine() throws ExecutionException {
        FutureTask<String> futureTask = new FutureTask<>(new InputReadTask());
        inputThread = new Thread(futureTask);
        inputThread.start();

        String input = null;

        try {
            input = futureTask.get();
        } catch (InterruptedException e) {
            futureTask.cancel(true);
            Thread.currentThread().interrupt();
        }
        return input;
    }

    public void initCli(){
        out.println(""+
                "8888ba.88ba              .d88888b  dP                dP .8888b oo         \n" +
                "88  `8b  `8b             88.    \"' 88                88 88   \"            \n" +
                "88   88   88 dP    dP    `Y88888b. 88d888b. .d8888b. 88 88aaa  dP .d8888b.\n" +
                "88   88   88 88    88          `8b 88'  `88 88ooood8 88 88     88 88ooood8\n" +
                "88   88   88 88.  .88    d8'   .8P 88    88 88.  ... 88 88     88 88.  ...\n" +
                "dP   dP   dP `8888P88     Y88888P  dP    dP `88888P' dP dP     dP `88888P'\n" +
                "                  .88                                                     \n" +
                "              d8888P                                                      ");
        out.println("Welcome to My Shelfie board game!");

        try{
            askServerInfo();
        } catch (ExecutionException e){
            out.println(STR_INPUT_CANCELED);
        }

    }

    /**
     * This method asks the user the server IP address and port.
     * @throws ExecutionException if {@link Cli#readLine()} fails.
     */
    private void askServerInfo() throws ExecutionException{
        Map<String, String> serverInfo = new HashMap<>();
        String defaultAddress = "localhost";
        String defaultPort = "12345";
        boolean validInput;

        out.println("Please enter the connection settings.");
        do{
            out.print("Enter the server address (default: " + defaultAddress + "):");
            String address = readLine();

            if(address.equals("")){
                serverInfo.put("address", defaultAddress);
                validInput = true;
            } else if (ClientController.isAddressValid(address)) {
                serverInfo.put("address", address);
                validInput = true;
            } else {
                out.println("Invalid input!");
                clearCli();
                validInput = false;
            }
        } while (validInput == false);

        do{
            out.print("Enter the server port (default: " + defaultPort + "):");
            String port = readLine();

            if(port.equals("")){
                serverInfo.put("port", defaultPort);
                validInput = true;
            } else if (ClientController.isPortValid(port)) {
                serverInfo.put("port", port);
                validInput = true;
            } else {
                out.println("Invalid input!");
                clearCli();
                validInput = false;
            }
        } while (validInput == false);

        notifyObserver(viewObserver -> viewObserver.onUpdateServerInfo(serverInfo));
    }

    /**
     * This method asks the user the username that will be used in the game.
     */
    @Override
    public void askNickname() {
        boolean validInput;

        try{
            do {
                out.print("Enter your nickname: ");
                String nickname = readLine();

                if(nickname.equals("Controller")){
                    out.println("The nickname \"" + nickname + "\" is not permitted.");
                    clearCli();
                    validInput = false;
                }  else {
                    validInput = true;
                    notifyObserver(viewObserver -> viewObserver.onUpdateNickname(nickname));
                }
            } while (validInput == false);
        } catch (ExecutionException e){
            out.println(STR_INPUT_CANCELED);
        }
    }

    /**
     * This method asks the user the number of players for the game.
     */
    @Override
    public void askMaxPlayer() {
        int numOfPlayers;

        try {
            out.print("How many players are going to play?\n" +
                    "Minimum: 2\n" +
                    "Maximum: 4\n");
            numOfPlayers = Integer.parseInt(readLine());
            notifyObserver(viewObserver -> viewObserver.onMaxPlayers(numOfPlayers));
        } catch (ExecutionException e){
            out.println(STR_INPUT_CANCELED);
        }
    }

    /**
     * This method asks the user which cards he wants to pick from the board.
     */
    @Override
    public void askBoardMove(){
        try{
            out.print("Which cards do you want to pick?\n" +
                      "You can pick up to 3 cards as: X1,Y1,X2,Y2,X3,Y3\n" +
                      "(Separate the coordinates with a comma)\n");
            String coordinates = readLine();

            String[] parsedCoordinates = coordinates.split(",");
            ArrayList<Integer> coordinatesToSend = new ArrayList<>();
            for(int i = 0; i < parsedCoordinates.length; i++){
                coordinatesToSend.add(Integer.parseInt(parsedCoordinates[i]));
            }

            notifyObserver(viewObserver -> viewObserver.onUdpateBoardMove());
        } catch (ExecutionException e){
            out.println(STR_INPUT_CANCELED);
        }
    }

    /**
     * This method shows the turn to the player.
     * @param rcvGameBoard is the {@link Board game board}.
     * @param rcvPlayerLibrary
     * @param rcvObjectsInHand
     */
    public void showTurn(Board rcvGameBoard, Library rcvPlayerLibrary, ArrayList<ObjectCard> rcvObjectsInHand){
        clearCli();

        showBoard(rcvGameBoard);
        showObjInHand(rcvObjectsInHand);
        showLibrary(rcvPlayerLibrary);
    }



    /**
     * This method prints the board.
     * @param gameBoard is the board passed by the {@link ClientController}.
     */
    private void showBoard(Board gameBoard){

        out.print(printColumnNumbers(9));
        out.print(Colours.RESET);
        for(int row = 0; row < 9; row++){
            out.print(printRowNumber(row));
            for(int col = 0; col < 9; col++){
                if(gameBoard.getSpace(row,col).getTypeSpace().equals(TypeSpace.UNUSABLE) || gameBoard.getSpace(row,col).getObject().equals(null)){
                    out.print(" " + Colours.BLACK + "■" + Colours.RESET + Colours.UNDERLINED + " |");
                } else {
                    if(gameBoard.getSpace(row,col).getObject().getObjectColour().isEquals(ObjectColour.GREEN1)){
                        out.print(" " + Colours.GREEN + "■" + Colours.RESET + Colours.UNDERLINED + " |");
                    } else if(gameBoard.getSpace(row,col).getObject().getObjectColour().isEquals(ObjectColour.WHITE1)){
                        out.print(" " + Colours.WHITE + "■" + Colours.RESET + Colours.UNDERLINED + " |");
                    } else if(gameBoard.getSpace(row,col).getObject().getObjectColour().isEquals(ObjectColour.YELLOW1)){
                        out.print(" " + Colours.YELLOW + "■" + Colours.RESET + Colours.UNDERLINED + " |");
                    } else if(gameBoard.getSpace(row,col).getObject().getObjectColour().isEquals(ObjectColour.BLUE1)){
                        out.print(" " + Colours.BLUE + "■" + Colours.RESET + Colours.UNDERLINED + " |");
                    } else if(gameBoard.getSpace(row,col).getObject().getObjectColour().isEquals(ObjectColour.LIGHT_BLUE1)){
                        out.print(" " + Colours.LIGHT_BLUE + "■" + Colours.RESET + Colours.UNDERLINED + " |");
                    } else if(gameBoard.getSpace(row,col).getObject().getObjectColour().isEquals(ObjectColour.PINK1)){
                        out.print(" " + Colours.PINK + "■" + Colours.RESET + Colours.UNDERLINED + " |");
                    }
                }
            }
        }

        out.println(Colours.RESET);
    }

    private void showObjInHand(ArrayList<ObjectCard> rcvObjectsInHand) {

        printColumnNumbers(3);
        out.println(Colours.RESET);
        printRowNumber(0);
        for(int i = 0; i < 3; i++){
            if(rcvObjectsInHand.get(i).equals(null)){
                out.print(" " + Colours.BLACK + "■" + Colours.RESET + Colours.UNDERLINED + " |");
            } else {
                if(rcvObjectsInHand.get(i).getObjectColour().isEquals(ObjectColour.GREEN1)){
                    out.print(" " + Colours.GREEN + "■" + Colours.RESET + Colours.UNDERLINED + " |");
                } else if(rcvObjectsInHand.get(i).getObjectColour().isEquals(ObjectColour.WHITE1)){
                    out.print(" " + Colours.WHITE + "■" + Colours.RESET + Colours.UNDERLINED + " |");
                } else if(rcvObjectsInHand.get(i).getObjectColour().isEquals(ObjectColour.YELLOW1)){
                    out.print(" " + Colours.YELLOW + "■" + Colours.RESET + Colours.UNDERLINED + " |");
                } else if(rcvObjectsInHand.get(i).getObjectColour().isEquals(ObjectColour.BLUE1)){
                    out.print(" " + Colours.BLUE + "■" + Colours.RESET + Colours.UNDERLINED + " |");
                } else if(rcvObjectsInHand.get(i).getObjectColour().isEquals(ObjectColour.LIGHT_BLUE1)){
                    out.print(" " + Colours.LIGHT_BLUE + "■" + Colours.RESET + Colours.UNDERLINED + " |");
                } else if(rcvObjectsInHand.get(i).getObjectColour().isEquals(ObjectColour.PINK1)){
                    out.print(" " + Colours.PINK + "■" + Colours.RESET + Colours.UNDERLINED + " |");
                }
            }
        }

        out.println(Colours.RESET);
    }

    /**
     * This method prints the player's library.
     * @param playerLibrary is the library passed by the {@link ClientController}.
     */
    private void showLibrary(Library playerLibrary){

        out.print(printColumnNumbers(5));
        out.print(Colours.RESET);
        for(int row = 0; row < 6; row++){
            out.print(printRowNumber(row));
            for(int col = 0; col < 5; col++){
                if(playerLibrary.getLibrarySpace(row, col).getObject().equals(null)){
                    out.print(" " + Colours.BLACK + "■" + Colours.RESET + Colours.UNDERLINED + " |");
                } else {
                    if(playerLibrary.getLibrarySpace(row,col).getObject().getObjectColour().isEquals(ObjectColour.GREEN1)){
                        out.print(" " + Colours.GREEN + "■" + Colours.RESET + Colours.UNDERLINED + " |");
                    } else if(playerLibrary.getLibrarySpace(row,col).getObject().getObjectColour().isEquals(ObjectColour.WHITE1)){
                        out.print(" " + Colours.WHITE + "■" + Colours.RESET + Colours.UNDERLINED + " |");
                    } else if(playerLibrary.getLibrarySpace(row,col).getObject().getObjectColour().isEquals(ObjectColour.YELLOW1)){
                        out.print(" " + Colours.YELLOW + "■" + Colours.RESET + Colours.UNDERLINED + " |");
                    } else if(playerLibrary.getLibrarySpace(row,col).getObject().getObjectColour().isEquals(ObjectColour.BLUE1)){
                        out.print(" " + Colours.BLUE + "■" + Colours.RESET + Colours.UNDERLINED + " |");
                    } else if(playerLibrary.getLibrarySpace(row,col).getObject().getObjectColour().isEquals(ObjectColour.LIGHT_BLUE1)){
                        out.print(" " + Colours.LIGHT_BLUE + "■" + Colours.RESET + Colours.UNDERLINED + " |");
                    } else if(playerLibrary.getLibrarySpace(row,col).getObject().getObjectColour().isEquals(ObjectColour.PINK1)){
                        out.print(" " + Colours.PINK + "■" + Colours.RESET + Colours.UNDERLINED + " |");
                    }
                }
            }
        }

        out.println(Colours.RESET);
    }

    /**
     * This method prints an error.
     * @param errorString is the explanation of the error as shown in most {@link exceptions.MyShelfieRuntimeExceptions runtime exceptions}.
     */
    public void showGenericError(String errorString){
        clearCli();

        out.println(errorString);
    }

    /**
     * This method prints the row numbers for both the board and the library.
     * @param row is the row to be printed.
     * @return the formatted string.
     */
    private String printRowNumber(int row) {
        StringBuilder strRowBld = new StringBuilder(" ");
        strRowBld.append(row).append(" ").append(Colours.UNDERLINED).append("|");

        return strRowBld.toString();
    }

    /**
     * This method prints the column numbers for both the board and the library.
     * @return the formatted string.
     */
    private String printColumnNumbers(int maxColumns) {
        StringBuilder strIndexBld = new StringBuilder("   " + Colours.UNDERLINED);
        for (int i = 0; i < maxColumns; i++) {
            strIndexBld.append(" ").append(i).append(" |");
        }
        return strIndexBld.toString();
    }

    /**
     * Clears the CLI terminal.
     */
    private void clearCli() {
        out.println(Colours.CLEAR);
        out.flush();
    }
}
