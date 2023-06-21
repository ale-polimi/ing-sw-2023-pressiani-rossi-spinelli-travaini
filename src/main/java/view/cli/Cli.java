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
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * Command line interface (CLI) for the game.
 */
public class Cli extends ViewObservable implements View {
    private static final String STR_INPUT_CANCELED = "User input canceled.";
    private final boolean isSocket;
    private final PrintStream out;
    private Thread inputThread;
    private boolean myTurn = true;

    private boolean chatAbilitator = true;

    /**
     * Constructor for the CLI.
     */
    public Cli(boolean isSocket) {
        out = System.out;
        this.isSocket = isSocket;
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
        out.println("" + Colours.BOLD + Colours.GOLD + "                                                                                                                        \n" +
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
        out.print(Colours.RESET);
        out.println(Colours.BOLD + "                                        Welcome to My Shelfie board game!" + Colours.RESET);

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
        String defaultSocketPort = "12345";
        String defaultRMIPort = "1099";
        boolean validInput;

        out.println("Please enter the connection settings.");
        do{
            out.print("Enter the server address (default: " + defaultAddress + "):");
            Scanner addrScanner = new Scanner(System.in);  // Create a Scanner object
            String address = addrScanner.nextLine();
            //String address = readLine();

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
            if(isSocket){
                out.print("Enter the server port (default: " + defaultSocketPort + "):");
            } else {
                out.print("Enter the server port (default: " + defaultRMIPort + "):");
            }

            Scanner portScanner = new Scanner(System.in);  // Create a Scanner object
            String port = portScanner.nextLine();

            if(port.equals("")){
                if(isSocket) {
                    serverInfo.put("port", defaultSocketPort);
                } else {
                    serverInfo.put("port", defaultRMIPort);
                }
                validInput = true;
            } else if (ClientController.isPortValid(port)) {
                serverInfo.put("port", port);
                validInput = true;
            } else {
                out.println("Invalid input!");
                clearCli();
                validInput = false;
            }
        } while (!validInput);

        notifyObserver(viewObserver -> viewObserver.onUpdateServerInfo(serverInfo));
    }


    /**
     * This method asks the user the username that will be used in the game.
     */
    @Override
    public void askNickname() {
        boolean validInput;

        do {
            out.print("Enter your nickname: ");
            Scanner nameScanner = new Scanner(System.in);  // Create a Scanner object
            String nickname = nameScanner.nextLine();

            if(nickname.equals("Controller") || nickname.equals("Model") || nickname.equals("Client") || nickname.equals("Server")){
                out.println("The nickname \"" + nickname + "\" is not permitted.");
                clearCli();
                validInput = false;
            }  else {
                validInput = true;
                notifyObserver(viewObserver -> viewObserver.onUpdateNickname(nickname));
            }
        } while (!validInput);
    }

    /**
     * This method asks the user the number of players for the game.
     */
    @Override
    public void askMaxPlayer() {
        String num;
        int numOfPlayers;
        boolean validInput;

        do {
            out.print("How many players are going to play?\n" +
                    "Minimum: 2\n" +
                    "Maximum: 4\n");

            Scanner numScanner = new Scanner(System.in);  // Create a Scanner object
            num = numScanner.nextLine();

            try{
                numOfPlayers = Integer.parseInt(num);
                if(numOfPlayers >= 2 && numOfPlayers <= 4){
                    validInput = true;
                    int finalNumOfPlayers = numOfPlayers;
                    notifyObserver(viewObserver -> viewObserver.onMaxPlayers(finalNumOfPlayers));
                } else {
                    out.println("" + Colours.RED + Colours.BOLD + "The number is not within the correct bounds. It must be 2 <= players <= 4" + Colours.RESET);
                    validInput = false;
                }
            } catch (NumberFormatException e){
                out.println("" + Colours.RED + Colours.BOLD + e.getMessage() + Colours.RESET);
                validInput = false;
            }
        } while (!validInput);
    }

    /**
     * This method asks the user which cards he wants to pick from the board.
     */
    @Override
    public void askBoardMove(){
        out.print(Colours.SHOW_CURSOR);
        boolean validInput;

        do{
            out.println("" + Colours.BOLD + Colours.GREEN + "YOUR TURN!" + Colours.RESET);
            out.print("Which cards do you want to pick?\n" +
                    "You can pick up to 3 cards as: Row1,Column1,Row2,Column2,Row3,Column3\n" +
                    "(Separate the coordinates with a comma)\n"+
                    "The objects must be in line (same row or column), adjacent and with ad least one side free.\n"+
                    "Type -showcommon to show the common objectives\n"+
                    "Type -showpersonal to show your personal objective\n"+
                    "Type -showothers to show the opponents' library\n"+
                    "Type -c_message to send a public chat message\n"+
                    "Type -c_-p_Receiver_message to send a private message\n"
            );

            Scanner coordScanner = new Scanner(System.in);  // Create a Scanner object
            String coordinates = coordScanner.nextLine();

            if(coordinates.equals("-showcommon")){
                notifyObserver(viewObserver -> viewObserver.onRequestCommonObjectives());
                validInput = true;
            } else if (coordinates.equals("-showpersonal")){
                notifyObserver(viewObserver -> viewObserver.onRequestPersonalObjective());
                validInput = true;
            } else if (coordinates.equals("-showothers")){
                notifyObserver(viewObserver -> viewObserver.onRequestOthersLibrary());
                validInput = true;
            }else if(coordinates.split("_")[0].equals("-c")){
              String[] ncm = coordinates.split("_");
              if(ncm[1].equals("-p")){
                  notifyObserver(viewObserver -> viewObserver.onChatMessage(null,ncm[2],ncm[3]));
              }
              else{
                  notifyObserver(viewObserver -> viewObserver.onChatMessage(null,"all",ncm[1]));
              }
              validInput = true;;
            } else if(ClientController.isInputValid(coordinates)){

                String[] parsedCoordinates = coordinates.split(",");
                ArrayList<Integer> coordinatesToSend = new ArrayList<>();
                for (int i = 0; i < parsedCoordinates.length; i++) {
                    coordinatesToSend.add(Integer.parseInt(parsedCoordinates[i]));
                }

                notifyObserver(viewObserver -> viewObserver.onUpdateBoardMove(coordinatesToSend));
                validInput = true;
            } else {
                out.println("" + Colours.RED + Colours.BOLD + "Invalid input!");
                out.print(Colours.RESET);
                validInput = false;
            }
        } while (!validInput);
    }

    /**
     * This method asks the user the order and column in which to put the cards.
     */
    @Override
    public void askLibraryMove() {
        chatAbilitator = true;
        myTurn = true;
        out.print(Colours.SHOW_CURSOR);
        boolean validInput;

        do{
            out.print("Please put the order followed by the column in which you wish to add the cards to your library\n" +
                    "You must put all the objects you have in hand as: First_To_Be_Added,Second_To_Be_Added,Third_To_Be_Added,Column\n"+
                    "Type -showcommon to show the common objectives\n"+
                    "Type -showpersonal to show your personal objective\n"+
                    "Type -showothers to show the opponents' library\n"+
                    "Type -c_message to send a public chat message\n"+
                    "Type -c_-p_Receiver_message to send a private message\n"
            );

            Scanner orderAndColumnScanner = new Scanner(System.in);  // Create a Scanner object
            String orderAndColumn = orderAndColumnScanner.nextLine();

            if(orderAndColumn.equals("-showcommon")){
                notifyObserver(viewObserver -> viewObserver.onRequestCommonObjectives());
                validInput = true;
            } else if (orderAndColumn.equals("-showpersonal")){
                notifyObserver(viewObserver -> viewObserver.onRequestPersonalObjective());
                validInput = true;
            } else if (orderAndColumn.equals("-showothers")){
                notifyObserver(viewObserver -> viewObserver.onRequestOthersLibrary());
                validInput = true;
            } else if(orderAndColumn.split("_")[0].equals("-c")){
                String[] ncm = orderAndColumn.split("_");
                if(ncm[1].equals("-p")){
                    notifyObserver(viewObserver -> viewObserver.onChatMessage(null,ncm[2],ncm[3]));
                }
                else {
                    notifyObserver(viewObserver -> viewObserver.onChatMessage(null,"all",ncm[1]));
                }
                validInput = true;;
            } else if(ClientController.isInputValid(orderAndColumn)){

                String[] parsedCoordinates = orderAndColumn.split(",");
                ArrayList<Integer> orderAndColumnToSend = new ArrayList<>();
                for(int i = 0; i < parsedCoordinates.length; i++){
                    orderAndColumnToSend.add(Integer.parseInt(parsedCoordinates[i]));
                }

                notifyObserver(viewObserver -> viewObserver.onUpdateLibraryMove(orderAndColumnToSend));
                validInput = true;
            } else {
                out.println("" + Colours.RED + Colours.BOLD + "Invalid input!");
                out.print(Colours.RESET);
                validInput = false;
            }
        } while (!validInput);
    }

    /**
     * This method shows the turn to the player.
     *
     * @param player                    is the {@link model.player.Player player's nickname}, used in {@link ClientController} to check whether the message is for this user.
     * @param rcvGameBoard              is the {@link Board game board}.
     * @param rcvPlayerLibrary          is the {@link Library player's library}.
     * @param rcvObjectsInHand          is the {@link ArrayList} of objects that a player currently has in hand.
     * @param completedCommonObjectives is the array containing the points the player has received from each common objective.
     */
    @Override
    public void showTurn(String player, Board rcvGameBoard, Library rcvPlayerLibrary, ArrayList<ObjectCard> rcvObjectsInHand, int[] completedCommonObjectives){
        clearCli();
        out.print(Colours.HIDE_CURSOR);
        if(myTurn)chatAbilitator = true;

        out.println(Colours.BOLD + player + "'s turn" + Colours.RESET);
        showBoard(rcvGameBoard);
        showObjInHand(rcvObjectsInHand);
        showLibrary(rcvPlayerLibrary);

        if(!myTurn){askChat();}
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
        int columnsToPrint = 9;
        out.print(printColumnNumbers(columnsToPrint) + "\n");
        out.print(printSeparator(columnsToPrint) + "\n");
        out.print(Colours.RESET);
        for(int row = 0; row < 9; row++){
            out.print(printRowNumber(row));
            for(int col = 0; col < 9; col++){
                if(gameBoard.getSpace(row,col).getTypeSpace().equals(TypeSpace.UNUSABLE) || gameBoard.getSpace(row,col).getObject() == null){
                    out.print("   |");
                } else {
                    if(gameBoard.getSpace(row,col).getObject().getObjectColour().isEquals(ObjectColour.GREEN1)){
                        out.print(" " + Colours.GREEN + "■" + Colours.RESET+ " |");
                    } else if(gameBoard.getSpace(row,col).getObject().getObjectColour().isEquals(ObjectColour.WHITE1)){
                        out.print(" " + Colours.WHITE + "■" + Colours.RESET + " |");
                    } else if(gameBoard.getSpace(row,col).getObject().getObjectColour().isEquals(ObjectColour.YELLOW1)){
                        out.print(" " + Colours.YELLOW + "■" + Colours.RESET + " |");
                    } else if(gameBoard.getSpace(row,col).getObject().getObjectColour().isEquals(ObjectColour.BLUE1)){
                        out.print(" " + Colours.BLUE + "■" + Colours.RESET + " |");
                    } else if(gameBoard.getSpace(row,col).getObject().getObjectColour().isEquals(ObjectColour.LIGHT_BLUE1)){
                        out.print(" " + Colours.LIGHT_BLUE + "■" + Colours.RESET + " |");
                    } else if(gameBoard.getSpace(row,col).getObject().getObjectColour().isEquals(ObjectColour.PINK1)){
                        out.print(" " + Colours.PINK + "■" + Colours.RESET + " |");
                    }
                }
            }
            out.print(Colours.RESET + "\n");
            out.print(printSeparator(columnsToPrint) + "\n");
        }

        out.println(Colours.RESET);
    }

    /**
     * This method prints the objects in hand for a player.
     * @param rcvObjectsInHand is the array of objects that a player currently has in hand.
     */
    private void showObjInHand(ArrayList<ObjectCard> rcvObjectsInHand) {
        int columnsToPrint = 3;
        out.print(printColumnNumbers(columnsToPrint) + "\n");
        out.print(printSeparator(columnsToPrint) + "\n");
        out.print(Colours.RESET);
        out.print(printRowNumber(0));
        for(int i = 0; i < 3; i++){
            if((rcvObjectsInHand.get(i) == null) || (rcvObjectsInHand.get(i).getObjectColour().equals(ObjectColour.EMPTY))){
                out.print("   |");
            } else {
                if(rcvObjectsInHand.get(i).getObjectColour().isEquals(ObjectColour.GREEN1)){
                    out.print(" " + Colours.GREEN + "■" + Colours.RESET + " |");
                } else if(rcvObjectsInHand.get(i).getObjectColour().isEquals(ObjectColour.WHITE1)){
                    out.print(" " + Colours.WHITE + "■" + Colours.RESET + " |");
                } else if(rcvObjectsInHand.get(i).getObjectColour().isEquals(ObjectColour.YELLOW1)){
                    out.print(" " + Colours.YELLOW + "■" + Colours.RESET + " |");
                } else if(rcvObjectsInHand.get(i).getObjectColour().isEquals(ObjectColour.BLUE1)){
                    out.print(" " + Colours.BLUE + "■" + Colours.RESET + " |");
                } else if(rcvObjectsInHand.get(i).getObjectColour().isEquals(ObjectColour.LIGHT_BLUE1)){
                    out.print(" " + Colours.LIGHT_BLUE + "■" + Colours.RESET + " |");
                } else if(rcvObjectsInHand.get(i).getObjectColour().isEquals(ObjectColour.PINK1)){
                    out.print(" " + Colours.PINK + "■" + Colours.RESET + " |");
                }
            }
        }
        out.print("\n");
        out.print(printSeparator(columnsToPrint) + "\n");

        out.println(Colours.RESET);
    }

    /**
     * This method prints the player's library.
     * @param playerLibrary is the library passed by the {@link ClientController}.
     */
    private void showLibrary(Library playerLibrary){
        int columnsToPrint = 5;
        out.print(printColumnNumbers(columnsToPrint) + "\n");
        out.print(printSeparator(columnsToPrint) + "\n");
        out.print(Colours.RESET);
        for(int row = 0; row < 6; row++){
            out.print(printRowNumber(row));
            for(int col = 0; col < 5; col++){
                if(playerLibrary.getLibrarySpace(row, col).getObject().getObjectColour().equals(ObjectColour.EMPTY)){
                    out.print("   |");
                } else {
                    if(playerLibrary.getLibrarySpace(row,col).getObject().getObjectColour().isEquals(ObjectColour.GREEN1)){
                        out.print(" " + Colours.GREEN + "■" + Colours.RESET + " |");
                    } else if(playerLibrary.getLibrarySpace(row,col).getObject().getObjectColour().isEquals(ObjectColour.WHITE1)){
                        out.print(" " + Colours.WHITE + "■" + Colours.RESET + " |");
                    } else if(playerLibrary.getLibrarySpace(row,col).getObject().getObjectColour().isEquals(ObjectColour.YELLOW1)){
                        out.print(" " + Colours.YELLOW + "■" + Colours.RESET + " |");
                    } else if(playerLibrary.getLibrarySpace(row,col).getObject().getObjectColour().isEquals(ObjectColour.BLUE1)){
                        out.print(" " + Colours.BLUE + "■" + Colours.RESET + " |");
                    } else if(playerLibrary.getLibrarySpace(row,col).getObject().getObjectColour().isEquals(ObjectColour.LIGHT_BLUE1)){
                        out.print(" " + Colours.LIGHT_BLUE + "■" + Colours.RESET + " |");
                    } else if(playerLibrary.getLibrarySpace(row,col).getObject().getObjectColour().isEquals(ObjectColour.PINK1)){
                        out.print(" " + Colours.PINK + "■" + Colours.RESET + " |");
                    }
                }
            }
            out.print("\n");
            out.print(printSeparator(columnsToPrint) + "\n");
        }

        out.println(Colours.RESET);
    }

    /**
     * This method prints the current {@link CommonObjective common objectives}.
     *
     * @param commonObjective1          is the first common objective of the game.
     * @param commonObjective2          is the second common objective of the game.
     * @param completedCommonObjectives is the array of points for each completed common objective.
     */
    @Override
    public void showCommonObjectives(String player, CommonObjective commonObjective1, CommonObjective commonObjective2, int[] completedCommonObjectives) {

        out.println("FIRST CO VALUE: " + completedCommonObjectives[0] + " SECOND CO VALUE: " + completedCommonObjectives[1]);

        out.println("The common objectives are:");
        out.print(commonObjective1.getDescription());
        out.print("\n");
        if(completedCommonObjectives[0] > 0){
            out.println("" + Colours.BOLD + Colours.GREEN + "COMPLETED WITH " + completedCommonObjectives[0] + " POINTS!" + Colours.RESET);
        } else {
            out.println("" + Colours.BOLD + Colours.RED + "NOT DONE YET!" + Colours.RESET);
        }
        out.print(commonObjective2.getDescription());
        out.print("\n");
        if(completedCommonObjectives[1] > 0){
            out.println("" + Colours.BOLD + Colours.GREEN + "COMPLETED WITH " + completedCommonObjectives[1] + " POINTS!" + Colours.RESET);
        } else {
            out.println("" + Colours.BOLD + Colours.RED + "NOT DONE YET!" + Colours.RESET);
        }
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
     * This method shows the other players' library.
     * @param sender is the sender of the message.
     * @param librariesOfPlayers is the {@link HashMap} containing the other players' usernames as keys and libraries as values.
     */
    @Override
    public void showOthersLibrary(String sender, HashMap<String, Library> librariesOfPlayers) {
        for(String username : librariesOfPlayers.keySet()){
            out.println(Colours.BOLD + username + "'s library:" + Colours.RESET);
            showLibrary(librariesOfPlayers.get(username));
        }
    }


    @Override
    public void showChat(String sender,boolean isPrivate, String message) {
        String senderOut;
        if(isPrivate){senderOut = "PRIVATE|"+ sender;}
        else{senderOut = sender;}
        out.println("" + Colours.BOLD + Colours.GREEN + senderOut + Colours.RESET+": "+message);
        if(!chatAbilitator){
            System.out.println(""+Colours.BOLD + Colours.YELLOW +"Abilitating the chat service only for a message as you closed the service\n"+Colours.RESET);
            chatAbilitator = true;
            askChat();
            chatAbilitator = false;
        }
    }

    /**
     * This method prints an error.
     * @param errorString is the explanation of the error as shown in most {@link exceptions.MyShelfieRuntimeExceptions runtime exceptions}.
     */
    @Override
    public void showGenericError(String player, String errorString){
        clearCli();

        out.print("" + Colours.BOLD + Colours.RED + errorString + Colours.RESET);
        out.print("\n\n");
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
        int columnsToPrint = 5;
        out.print(printColumnNumbers(columnsToPrint) + "\n");
        out.print(printSeparator(columnsToPrint) + "\n");
        out.print(Colours.RESET);
        for(int row = 0; row < 6; row++) {
            out.print(printRowNumber(row));
            for (int col = 0; col < 5; col++) {
                if (personalObjective.getLibraryGrid()[row][col].getObject().getObjectColour().equals(ObjectColour.EMPTY)) {
                    out.print("   |");
                } else {
                    if (personalObjective.getLibraryGrid()[row][col].getObject().getObjectColour().isEquals(ObjectColour.GREEN1)) {
                        out.print(" " + Colours.GREEN + "■" + Colours.RESET + " |");
                    } else if (personalObjective.getLibraryGrid()[row][col].getObject().getObjectColour().isEquals(ObjectColour.WHITE1)) {
                        out.print(" " + Colours.WHITE + "■" + Colours.RESET+ " |");
                    } else if (personalObjective.getLibraryGrid()[row][col].getObject().getObjectColour().isEquals(ObjectColour.YELLOW1)) {
                        out.print(" " + Colours.YELLOW + "■" + Colours.RESET + " |");
                    } else if (personalObjective.getLibraryGrid()[row][col].getObject().getObjectColour().isEquals(ObjectColour.BLUE1)) {
                        out.print(" " + Colours.BLUE + "■" + Colours.RESET + " |");
                    } else if (personalObjective.getLibraryGrid()[row][col].getObject().getObjectColour().isEquals(ObjectColour.LIGHT_BLUE1)) {
                        out.print(" " + Colours.LIGHT_BLUE + "■" + Colours.RESET + " |");
                    } else if (personalObjective.getLibraryGrid()[row][col].getObject().getObjectColour().isEquals(ObjectColour.PINK1)) {
                        out.print(" " + Colours.PINK + "■" + Colours.RESET + " |");
                    }
                }
            }
            out.print("\n");
            out.print(printSeparator(columnsToPrint) + "\n");
        }
        out.println(Colours.RESET);

        out.print(" ✅ " + Colours.UNDERLINED + " 1 | 2 | 3 | 4 | 5 | 6 \n" + Colours.RESET +
                  " \uD83C\uDFC6 " + Colours.UNDERLINED + " 1 | 2 | 4 | 6 | 9 | 12\n" + Colours.RESET);
    }

    /**
     * This method preapres the row numbers for both the board and the library.
     * @param row is the row to be printed.
     * @return the formatted string.
     */
    private String printRowNumber(int row) {
        StringBuilder strRowBld = new StringBuilder(" ");
        strRowBld.append(row).append(" |");

        return strRowBld.toString();
    }

    /**
     * This method prepares the row separator.
     * @param maxColumns is the number of columns of the object to print.
     * @return the formatted string.
     */
    private String printSeparator(int maxColumns){
        StringBuilder strSeparatorBld = new StringBuilder("   +");
        for(int i = 0; i < maxColumns; i++){
            strSeparatorBld.append("---+");
        }

        return strSeparatorBld.toString();
    }

    /**
     * This method prints the column numbers for both the board and the library.
     * @param maxColumns is the number of columns of the object to print.
     * @return the formatted string.
     */
    private String printColumnNumbers(int maxColumns) {
        StringBuilder strIndexBld = new StringBuilder("   ");
        for (int i = 0; i < maxColumns; i++) {
            strIndexBld.append("  ").append(i).append(" ");
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

    /**
     * Getter for myTurn parameter
     * @return myTurn value
     */
    public boolean getMyTurn(){return myTurn;}

    @Override
    public void setMyTurn(boolean turn) {this.myTurn=turn;}

    public void askChat() {
        if(!chatAbilitator || myTurn)return;
        boolean validInput;
        do{
            System.out.println(
                 "Chat service out of your turn:\n" +
                         "Type -c_message to send a public chat message\n" +
                         "Type -c_-p_Receiver_message to send a private message\n" +
                         "Type SEE to see if there are incoming messages\n"+
                         "Type EXIT to exit chat");
            try {
                String[] text = readLine().split("_");
                switch (text[0]) {
                    case "EXIT" -> {
                        out.println("Exiting chat service");
                        chatAbilitator = false;
                        return;
                    }
                    case "-c" -> {
                        validInput = true;
                        if (text[1].equals("-p"))
                            notifyObserver(viewObserver -> viewObserver.onChatMessage(null, text[2], text[3]));
                        else notifyObserver(viewObserver -> viewObserver.onChatMessage(null, "all", text[1]));
                    }
                    case "SEE" -> {
                        validInput = true;
                        notifyObserver(viewObserver -> viewObserver.onChatMessage(null, "SEE", null));
                    }
                    default -> {
                        out.println("" + Colours.RED + Colours.BOLD + "Invalid input!");
                        out.print(Colours.RESET);
                        validInput = false;
                    }
                }
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }while(!validInput);
    }


}
