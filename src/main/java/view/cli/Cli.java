package view.cli;

import controller.ClientController;
import enumerations.ObjectColour;
import enumerations.TypeSpace;
import model.board.Board;
import model.commonobjective.CommonObjective;
import model.library.Library;
import model.library.PersonalObjective;
import model.objects.ObjectCard;
import observer.ViewObservable;
import view.View;

import java.io.PrintStream;
import java.util.ArrayList;
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

    /**
     * Constructor for the CLI.
     */
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

    /**
     * This method will start the CLI.
     */
    public void initCli(){
        out.println("                                                                                                                        \n" +
                "                                                                                                                        \n" +
                "                                                     ..                                                                 \n" +
                "                                                  .-:-=-...                                                             \n" +
                "          :::.-:    :...-=                        =.==:::.-.  ..                    ..:   ..---.                        \n" +
                "           -..+.     ..:*.                       :-.* ::..+:  .:.=+                 ::* .::*-. ..-                      \n" +
                "           -..-.    ...-:                        .-.-  .--.    -:+                  ::+ -.* ..:-.+.                     \n" +
                "           -...-   ..:.-:     ....:.  ::.:-       ::..         -.+                  ::+.=:= .-==+=                      \n" +
                "           -:-=:- .:==.-:    .-:..+  :-.-+.        .:....      -.=..::.             ::+ =.=     :.                      \n" +
                "           -:---::::*-.=:       -::.:-.=+            ::....    -.:.::.:-:           -:+ .:-     -:==                    \n" +
                "           --:=.-::*.-:=:        --::.==               ..:::.  =::+=:.::+  --+=-::. --+.::::--=..--  .-=+-:::.          \n" +
                "           --:= -:=: =:=:         +::+-          .:::    ::-=  =:+:   -:+ =:-:..:-=.--+ --::=.. -:+: =:=:.:::+          \n" +
                "           --:+ ==== =:=. :--::: .=:*-          :-::-=:  ::-+: =:+   .==- =:*---==- --+   :-=.  =-+ :==+---==-          \n" +
                "           --:+     .=:=. +----+---*:           :--===+ :--+#  =-*   :=+. =-+  .---.==+   .-=-  =-* .=--  -::-          \n" +
                "          :=---:   .=---: :=------*.             -=------=+*. :=-=: .=---  ---:--=*:=-=   --=- .=-+. .--::--=*          \n" +
                "          .-----    -----. .-==+==                .-======-   .----  ---=   :-===-  ---   ----  ---.   :-===:           \n" +
                "                                                                                                                        \n" +
                "                                                                                                                        \n");
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
        out.println("Inviato indirizzo e porte");
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

                if(nickname.equals("Controller") || nickname.equals("Model")){
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
        String num;
        int numOfPlayers;

        try {
            out.print("How many players are going to play?\n" +
                    "Minimum: 2\n" +
                    "Maximum: 4\n");
            num = readLine();
            numOfPlayers = Integer.parseInt(num);
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
                      "You can pick up to 3 cards as: Row1,Column1,Row2,Column2,Row3,Column3\n" +
                      "(Separate the coordinates with a comma)\n"+
                      "The objects must be in line (same row or column), adjacent and with ad least one side free.\n");
            String coordinates = readLine();

            String[] parsedCoordinates = coordinates.split(",");
            ArrayList<Integer> coordinatesToSend = new ArrayList<>();
            for(int i = 0; i < parsedCoordinates.length; i++){
                coordinatesToSend.add(Integer.parseInt(parsedCoordinates[i]));
            }

            notifyObserver(viewObserver -> viewObserver.onUpdateBoardMove(coordinatesToSend));
        } catch (ExecutionException e){
            out.println(STR_INPUT_CANCELED);
        }
    }

    /**
     * This method asks the user the order and column in which to put the cards.
     */
    @Override
    public void askLibraryMove() {
        try{
            out.print("Please put the order followed by the column in which you wish to add the cards to your library\n" +
                      "You must put all the objects you have in hand as: First_To_Be_Added,Second_To_Be_Added,Third_To_Be_Added,Column\n"+
                      "Type -showcommon to show the common objectives\n"+
                      "Type -showpersonal to show the personal objective\n");
            String orderAndColumn = readLine();

            /*
            Only when the player is in the library, he can ask to see its personal objective and the game's common objectives.
             */
            if(orderAndColumn.equals("-showcommon")){
                notifyObserver(viewObserver -> viewObserver.onRequestCommonObjectives());
            } else if (orderAndColumn.equals("-showpersonal")){
                notifyObserver(viewObserver -> viewObserver.onRequestPersonalObjective());
            } else {
                String[] parsedCoordinates = orderAndColumn.split(",");
                ArrayList<Integer> orderAndColumnToSend = new ArrayList<>();
                for(int i = 0; i < parsedCoordinates.length; i++){
                    orderAndColumnToSend.add(Integer.parseInt(parsedCoordinates[i]));
                }

                notifyObserver(viewObserver -> viewObserver.onUpdateLibraryMove(orderAndColumnToSend));
            }
        } catch (ExecutionException e){
            out.println(STR_INPUT_CANCELED);
        }
    }

    /**
     * This method shows the turn to the player.
     * @param player is the {@link model.player.Player player's nickname}, used in {@link ClientController} to check whether the message is for this user.
     * @param rcvGameBoard is the {@link Board game board}.
     * @param rcvPlayerLibrary is the {@link Library player's library}.
     * @param rcvObjectsInHand is the {@link ArrayList} of objects that a player currently has in hand.
     */
    @Override
    public void showTurn(String player, Board rcvGameBoard, Library rcvPlayerLibrary, ArrayList<ObjectCard> rcvObjectsInHand){
        clearCli();

        showBoard(rcvGameBoard);
        showObjInHand(rcvObjectsInHand);
        showLibrary(rcvPlayerLibrary);
    }

    /**
     * This method shows the board when it's not my turn, so that the current user does not see an outdated game board.
     * @param rcvGameBoard is the {@link Board game board}.
     */
    public void showNotMyTurn(Board rcvGameBoard){
        clearCli();
        showBoard(rcvGameBoard);
    }

    /**
     * This method show the lobby to the user.
     * @param players are the usernames of the players currently connected to the game.
     */
    @Override
    public void showLobby(ArrayList<String> players) {
        clearCli();

        out.println("Connected players:");
        for (String playerName:
             players) {
            out.println(playerName);
        }
    }

    /**
     * This method prints the board.
     * @param gameBoard is the board passed by the {@link ClientController}.
     */
    private void showBoard(Board gameBoard){

        out.print(printColumnNumbers(9));
        out.print("\n");
        out.print(Colours.RESET);
        for(int row = 0; row < 9; row++){
            out.print(printRowNumber(row));
            for(int col = 0; col < 9; col++){
                if(gameBoard.getSpace(row,col).getTypeSpace().equals(TypeSpace.UNUSABLE) || gameBoard.getSpace(row,col).getObject() == null){
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
            out.print(Colours.RESET + "\n");
        }

        out.println(Colours.RESET);
    }

    /**
     * This method prints the objects in hand for a player.
     * @param rcvObjectsInHand is the array of objects that a player currently has in hand.
     */
    private void showObjInHand(ArrayList<ObjectCard> rcvObjectsInHand) {

        out.print(printColumnNumbers(3));
        out.print(Colours.RESET);
        out.print("\n");
        out.print(printRowNumber(0));
        for(int i = 0; i < 3; i++){
            if((rcvObjectsInHand.get(i) == null) || (rcvObjectsInHand.get(i).getObjectColour().equals(ObjectColour.EMPTY))){
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
        out.print("\n");

        out.println(Colours.RESET);
    }

    /**
     * This method prints the player's library.
     * @param playerLibrary is the library passed by the {@link ClientController}.
     */
    private void showLibrary(Library playerLibrary){

        out.print(printColumnNumbers(5));
        out.print(Colours.RESET);
        out.print("\n");
        for(int row = 0; row < 6; row++){
            out.print(printRowNumber(row));
            for(int col = 0; col < 5; col++){
                if(playerLibrary.getLibrarySpace(row, col).getObject().getObjectColour().equals(ObjectColour.EMPTY)){
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
            out.print(Colours.RESET + "\n");
        }

        out.println(Colours.RESET);
    }

    /**
     * This method prints the current {@link CommonObjective common objectives}.
     * @param commonObjective1 is the first common objective of the game.
     * @param commonObjective2 is the second common objective of the game.
     */
    @Override
    public void showCommonObjectives(String player, CommonObjective commonObjective1, CommonObjective commonObjective2) {
        out.println("The common objectives are:");
        out.println(commonObjective1.getDescription());
        out.print("\n");
        out.println(commonObjective2.getDescription());
        out.print("\n");
    }

    /**
     * This method shows the current {@link PersonalObjective personal objective} of the player.
     * @param personalObjective is the personal objective of the player.
     */
    @Override
    public void showPersonalObjective(String player, PersonalObjective personalObjective) {
        out.println("Your personal objective is:");
        printPersonalObjective(personalObjective);
    }

    /**
     * This method prints an error.
     * @param errorString is the explanation of the error as shown in most {@link exceptions.MyShelfieRuntimeExceptions runtime exceptions}.
     */
    @Override
    public void showGenericError(String player, String errorString){
        clearCli();

        out.println(errorString);
    }

    /**
     * This method shows the winner at the end of the game.
     * @param winner is the username of the winner.
     * @param leaderboard is the leaderboard of the game (Username: Points).
     */
    @Override
    public void showWinner(String winner, HashMap<String, Integer> leaderboard) {
        clearCli();

        out.println("Player: \"" + winner + "\" has won!");
        out.println("Leaderboard:\n");
        leaderboard.forEach((username, points) -> out.println("Player: " + username + " || Points: " + points));
    }

    /**
     * This method prints the current {@link PersonalObjective personal objective} of the player.
     * @param personalObjective is the personal objective of the player.
     */
    private void printPersonalObjective(PersonalObjective personalObjective) {

        out.print(printColumnNumbers(5));
        out.print("\n");
        out.print(Colours.RESET);
        for(int row = 0; row < 6; row++) {
            out.print(printRowNumber(row));
            for (int col = 0; col < 5; col++) {
                if (personalObjective.getLibraryGrid()[row][col].getObject().getObjectColour().equals(ObjectColour.EMPTY)) {
                    out.print(" " + Colours.BLACK + "■" + Colours.RESET + Colours.UNDERLINED + " |");
                } else {
                    if (personalObjective.getLibraryGrid()[row][col].getObject().getObjectColour().isEquals(ObjectColour.GREEN1)) {
                        out.print(" " + Colours.GREEN + "■" + Colours.RESET + Colours.UNDERLINED + " |");
                    } else if (personalObjective.getLibraryGrid()[row][col].getObject().getObjectColour().isEquals(ObjectColour.WHITE1)) {
                        out.print(" " + Colours.WHITE + "■" + Colours.RESET + Colours.UNDERLINED + " |");
                    } else if (personalObjective.getLibraryGrid()[row][col].getObject().getObjectColour().isEquals(ObjectColour.YELLOW1)) {
                        out.print(" " + Colours.YELLOW + "■" + Colours.RESET + Colours.UNDERLINED + " |");
                    } else if (personalObjective.getLibraryGrid()[row][col].getObject().getObjectColour().isEquals(ObjectColour.BLUE1)) {
                        out.print(" " + Colours.BLUE + "■" + Colours.RESET + Colours.UNDERLINED + " |");
                    } else if (personalObjective.getLibraryGrid()[row][col].getObject().getObjectColour().isEquals(ObjectColour.LIGHT_BLUE1)) {
                        out.print(" " + Colours.LIGHT_BLUE + "■" + Colours.RESET + Colours.UNDERLINED + " |");
                    } else if (personalObjective.getLibraryGrid()[row][col].getObject().getObjectColour().isEquals(ObjectColour.PINK1)) {
                        out.print(" " + Colours.PINK + "■" + Colours.RESET + Colours.UNDERLINED + " |");
                    }
                }
            }
            out.print("\n");
        }
        out.println(Colours.RESET);

        out.print("  ☑\t" + Colours.UNDERLINED + "1 | 2 | 3 | 4 | 5 | 6 \n" + Colours.RESET +
                  " Pts.\t" + Colours.UNDERLINED + "1 | 2 | 4 | 6 | 9 | 12\n" + Colours.RESET);
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
        StringBuilder strIndexBld = new StringBuilder("   " + Colours.UNDERLINED + " ");
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
