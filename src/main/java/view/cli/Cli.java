package view.cli;

import controller.ClientController;
import enumerations.ObjectColour;
import enumerations.TypeSpace;
import model.board.Board;
import model.library.Library;
import view.View;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Command line interface (CLI) for the game.
 */
public class Cli implements View {

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

        /* TODO - Update server info with observers */
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
                    /* TODO - Notify observers */
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
    public void askPlayersNumber() {
        int numOfPlayers;

        try {
            out.print("How many players are going to play?" +
                    "Minimum: 2" +
                    "Maximum: 4");
            numOfPlayers = Integer.parseInt(readLine());
        } catch (ExecutionException e){
            out.println(STR_INPUT_CANCELED);
        }
    }

    /**
     * This method prints the board.
     * @param gameBoard is the board passed by the {@link ClientController}.
     */
    public void showBoard(Board gameBoard){
        clearCli();

        out.print(printColumnNumbers());
        out.print(Colours.RESET);
        for(int row = 0; row < 9; row++){
            out.print(printRowNumber(row));
            for(int col = 0; col < 9; col++){
                if(gameBoard.getSpace(row,col).getTypeSpace().equals(TypeSpace.UNUSABLE) || gameBoard.getSpace(row,col).getObject().equals(null)){
                    out.print(" " + Colours.BLACK + "■" + Colours.RESET + Colours.UNDERLINED + " |");
                } else {
                    if(gameBoard.getSpace(row,col).getObject().getObjectColour().equals(ObjectColour.GREEN1) || gameBoard.getSpace(row,col).getObject().getObjectColour().equals(ObjectColour.GREEN2) || gameBoard.getSpace(row,col).getObject().getObjectColour().equals(ObjectColour.GREEN3)){
                        out.print(" " + Colours.GREEN + "■" + Colours.RESET + Colours.UNDERLINED + " |");
                    } else if(gameBoard.getSpace(row,col).getObject().getObjectColour().equals(ObjectColour.WHITE1) || gameBoard.getSpace(row,col).getObject().getObjectColour().equals(ObjectColour.WHITE2) || gameBoard.getSpace(row,col).getObject().getObjectColour().equals(ObjectColour.WHITE3)){
                        out.print(" " + Colours.WHITE + "■" + Colours.RESET + Colours.UNDERLINED + " |");
                    } else if(gameBoard.getSpace(row,col).getObject().getObjectColour().equals(ObjectColour.YELLOW1) || gameBoard.getSpace(row,col).getObject().getObjectColour().equals(ObjectColour.YELLOW2) || gameBoard.getSpace(row,col).getObject().getObjectColour().equals(ObjectColour.YELLOW3)){
                        out.print(" " + Colours.YELLOW + "■" + Colours.RESET + Colours.UNDERLINED + " |");
                    } else if(gameBoard.getSpace(row,col).getObject().getObjectColour().equals(ObjectColour.BLUE1) || gameBoard.getSpace(row,col).getObject().getObjectColour().equals(ObjectColour.BLUE2) || gameBoard.getSpace(row,col).getObject().getObjectColour().equals(ObjectColour.BLUE3)){
                        out.print(" " + Colours.BLUE + "■" + Colours.RESET + Colours.UNDERLINED + " |");
                    } else if(gameBoard.getSpace(row,col).getObject().getObjectColour().equals(ObjectColour.LIGHT_BLUE1) || gameBoard.getSpace(row,col).getObject().getObjectColour().equals(ObjectColour.LIGHT_BLUE2) || gameBoard.getSpace(row,col).getObject().getObjectColour().equals(ObjectColour.LIGHT_BLUE3)){
                        out.print(" " + Colours.LIGHT_BLUE + "■" + Colours.RESET + Colours.UNDERLINED + " |");
                    } else if(gameBoard.getSpace(row,col).getObject().getObjectColour().equals(ObjectColour.PINK1) || gameBoard.getSpace(row,col).getObject().getObjectColour().equals(ObjectColour.PINK2) || gameBoard.getSpace(row,col).getObject().getObjectColour().equals(ObjectColour.PINK3)){
                        out.print(" " + Colours.PINK + "■" + Colours.RESET + Colours.UNDERLINED + " |");
                    }
                }
            }
        }

        out.println(Colours.RESET);
    }

    /**
     * This method prints the player's library.
     * @param playerLibrary is the library passed by the {@link ClientController}.
     */
    public void showLibrary(Library playerLibrary){

        out.print(printColumnNumbers());
        out.print(Colours.RESET);
        for(int row = 0; row < 6; row++){
            out.print(printRowNumber(row));
            for(int col = 0; col < 5; col++){
                if(playerLibrary.getLibrarySpace(row, col).getObject().equals(null)){
                    out.print(" " + Colours.BLACK + "■" + Colours.RESET + Colours.UNDERLINED + " |");
                } else {
                    if(playerLibrary.getLibrarySpace(row,col).getObject().getObjectColour().equals(ObjectColour.GREEN1) || playerLibrary.getLibrarySpace(row,col).getObject().getObjectColour().equals(ObjectColour.GREEN2) || playerLibrary.getLibrarySpace(row,col).getObject().getObjectColour().equals(ObjectColour.GREEN3)){
                        out.print(" " + Colours.GREEN + "■" + Colours.RESET + Colours.UNDERLINED + " |");
                    } else if(playerLibrary.getLibrarySpace(row,col).getObject().getObjectColour().equals(ObjectColour.WHITE1) || playerLibrary.getLibrarySpace(row,col).getObject().getObjectColour().equals(ObjectColour.WHITE2) || playerLibrary.getLibrarySpace(row,col).getObject().getObjectColour().equals(ObjectColour.WHITE3)){
                        out.print(" " + Colours.WHITE + "■" + Colours.RESET + Colours.UNDERLINED + " |");
                    } else if(playerLibrary.getLibrarySpace(row,col).getObject().getObjectColour().equals(ObjectColour.YELLOW1) || playerLibrary.getLibrarySpace(row,col).getObject().getObjectColour().equals(ObjectColour.YELLOW2) || playerLibrary.getLibrarySpace(row,col).getObject().getObjectColour().equals(ObjectColour.YELLOW3)){
                        out.print(" " + Colours.YELLOW + "■" + Colours.RESET + Colours.UNDERLINED + " |");
                    } else if(playerLibrary.getLibrarySpace(row,col).getObject().getObjectColour().equals(ObjectColour.BLUE1) || playerLibrary.getLibrarySpace(row,col).getObject().getObjectColour().equals(ObjectColour.BLUE2) || playerLibrary.getLibrarySpace(row,col).getObject().getObjectColour().equals(ObjectColour.BLUE3)){
                        out.print(" " + Colours.BLUE + "■" + Colours.RESET + Colours.UNDERLINED + " |");
                    } else if(playerLibrary.getLibrarySpace(row,col).getObject().getObjectColour().equals(ObjectColour.LIGHT_BLUE1) || playerLibrary.getLibrarySpace(row,col).getObject().getObjectColour().equals(ObjectColour.LIGHT_BLUE2) || playerLibrary.getLibrarySpace(row,col).getObject().getObjectColour().equals(ObjectColour.LIGHT_BLUE3)){
                        out.print(" " + Colours.LIGHT_BLUE + "■" + Colours.RESET + Colours.UNDERLINED + " |");
                    } else if(playerLibrary.getLibrarySpace(row,col).getObject().getObjectColour().equals(ObjectColour.PINK1) || playerLibrary.getLibrarySpace(row,col).getObject().getObjectColour().equals(ObjectColour.PINK2) || playerLibrary.getLibrarySpace(row,col).getObject().getObjectColour().equals(ObjectColour.PINK3)){
                        out.print(" " + Colours.PINK + "■" + Colours.RESET + Colours.UNDERLINED + " |");
                    }
                }
            }
        }

        out.println(Colours.RESET);
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
    private String printColumnNumbers() {
        StringBuilder strIndexBld = new StringBuilder("   " + Colours.UNDERLINED);
        for (int i = 0; i < 9; i++) {
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
