package controller;

import model.commonobjective.CommonObjective;
import model.library.Library;
import model.library.PersonalObjective;
import model.objects.ObjectCard;
import network.messages.*;
import network.structure.Client;
import network.structure.ClientRMI;
import network.structure.ClientSocket;
import observer.Observable;
import observer.Observer;
import observer.ViewObserver;
import view.View;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * Controller for the client.
 * It stays client side.
 */
public class ClientController extends Observable implements ViewObserver, Observer {
    private final View view;
    private Client client;
    private String nickname;
    private boolean inLobby = false;
    private ArrayList<String> takenNicknames = new ArrayList<>();
    private CommonObjective commonObjective1;
    private CommonObjective commonObjective2;
    private PersonalObjective personalObjective;
    private Library playerLibrary = new Library();
    private HashMap<String, Library> otherPlayersLibrary = new HashMap<>();
    private ArrayList<ObjectCard> objInHand = new ArrayList<>(Arrays.asList(null,null,null));
    private boolean inLibrary = false;
    private boolean inPickup = true;
    private final ExecutorService taskQueue;
    private boolean isSocket;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    public ClientController(View view,boolean isSocket){
        this.view = view;
        taskQueue = Executors.newSingleThreadExecutor();
        this.isSocket=isSocket;
    }

    @Override
    public void onUpdateServerInfo(Map<String, String> serverInfo) {
        if(isSocket){
            client = new ClientSocket(serverInfo.get("address"), Integer.parseInt(serverInfo.get("port")));
            ((ClientSocket)client).addObserver(this);
        }
        else {
            try {
                client = new ClientRMI(serverInfo.get("address"), Integer.parseInt(serverInfo.get("port")),this);
                ((ClientRMI) client).addObserver(this);
                try{client.connection();}
                catch(IOException e){System.err.println("Cannot connect to the server, closing");}
                executor.submit((ClientRMI) client);
            } catch (RemoteException e) {
                System.err.println("Cannot create the client, exiting");
                System.exit(1);
            }
        }
        //client.receivedMessage(new UserInfoForLoginMessage(null, this.nickname)); // Starts an asynchronous reading from the server.
        //client.ping();
    }

    @Override
    public void onUpdateNickname(String nickname) {
        this.nickname = nickname;
        try {
            if(isSocket)client.sendMessage(new UserInfoForLoginMessage(this.nickname, this.nickname));
            else notifyObserver(new UserInfoForLoginMessage(this.nickname, this.nickname));
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onMaxPlayers(int maxPlayers) {
        try {
            client.sendMessage(new MaxPlayersMessage(this.nickname, maxPlayers));
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpdateBoardMove(ArrayList<Integer> coordinatesToSend){
        inLibrary = true;
        inPickup = false;
        try {
            client.sendMessage(new PickObjectMessage(this.nickname, coordinatesToSend));
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpdateLibraryMove(ArrayList<Integer> orderAndColumnToSend){
        inLibrary = false;
        inPickup = true;
        try {
            client.sendMessage(new PutObjectInLibraryMessage(this.nickname, orderAndColumnToSend));
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onRequestCommonObjectives() {
        view.showCommonObjectives(this.nickname, this.commonObjective1, this.commonObjective2);
        if(inLibrary == false && inPickup == true){
            view.askBoardMove();
        } else {
            view.askLibraryMove();
        }
    }

    @Override
    public void onRequestPersonalObjective() {
        view.showPersonalObjective(this.nickname, this.personalObjective);
        if(inLibrary == false && inPickup == true){
            view.askBoardMove();
        } else {
            view.askLibraryMove();
        }
    }

    @Override
    public void onRequestOthersLibrary() {
        view.showOthersLibrary(this.nickname, this.otherPlayersLibrary);
        if(inLibrary == false && inPickup == true){
            view.askBoardMove();
        } else {
            view.askLibraryMove();
        }
    }

    /**
     * This method checks if the ip is valid as in it follows the <a href="https://en.wikipedia.org/wiki/Dot-decimal_notation">dot-decimal notation</a>.
     * @param ip is the ip to verify.
     * @return {@code true} if the ip is valid, {@code false} otherwise.
     */
    public static boolean isAddressValid(String ip){
        /* Regex pattern to check if the ip follows the dot-decimal notation standard */
        /* Sempre sia eulogized Stackoverflow */
        String PATTERN = "^((0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)\\.){3}(0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)$";

        return ip.matches(PATTERN);
    }

    /**
     * This method checks if the port is valid.
     * @param portString is the port to verify.
     * @return {@code true} if the port is valid, {@code false} otherwise.
     */
    public static boolean isPortValid(String portString){
        try{
            int port = Integer.parseInt(portString);
            if(port >= 1 && port <= 65535) {
                return true;
            } else {
                return false;
            }
        } catch (NumberFormatException e){
            return false;
        }
    }

    /**
     * This method checks if the input is in the correct format (%d,%d etc.).
     * @param coordinatesToSend is the string to verify.
     * @return {@code true} if the string is valid, {@code false} otherwise.
     */
    public static boolean isInputValid(String coordinatesToSend) {
        String PATTERN = "^([0-8](,[0-8])+)$";

        return coordinatesToSend.matches(PATTERN);
    }

    @Override
    public void update(Message message) {
        switch (message.getType()){
            case SHOW_LOBBY:
                inLobby = true;
                ShowLobbyMessage lobbyMessage = (ShowLobbyMessage) message;
                this.takenNicknames.add(lobbyMessage.getSender());
                view.showLobby(lobbyMessage.getLobbyPlayers());
                break;
            case SHOW_TURN:
                String sender;
                String type = "TEST";
                if(message.getSender().contains(":")){
                    type = parseType(message.getSender());
                    sender = parseSender((message.getSender()));
                } else {
                    sender = message.getSender();
                }

                if(sender.equals(nickname)) {
                    ShowTurnMessage turnMessage = (ShowTurnMessage) message;
                    switch (type){
                        case "END_TURN":
                            this.playerLibrary = turnMessage.getPlayerLibrary();
                            view.showTurn(sender, turnMessage.getGameBoard(), playerLibrary, this.objInHand);
                            break;
                        default:
                            if (inLibrary == true && inPickup == false) {
                                view.showTurn(sender, turnMessage.getGameBoard(), turnMessage.getPlayerLibrary(), turnMessage.getPlayerObjInHand());
                                view.askLibraryMove();
                            } else if (inLibrary == false && inPickup == true) {
                                view.showTurn(sender, turnMessage.getGameBoard(), turnMessage.getPlayerLibrary(), turnMessage.getPlayerObjInHand());
                                view.askBoardMove();
                            }
                            break;
                    }
                } else {
                    ShowTurnMessage turnMessage = (ShowTurnMessage) message;
                    view.showTurn(sender, turnMessage.getGameBoard(), this.playerLibrary, this.objInHand);
                }
                break;
            case SHOW_COMMON_OBJECTIVE:
                if(message.getSender().equals(nickname)) {
                    ShowCommonObjectiveMessage commonObjectiveMessage = (ShowCommonObjectiveMessage) message;
                    this.commonObjective1 = commonObjectiveMessage.getCommonObjective1();
                    this.commonObjective2 = commonObjectiveMessage.getCommonObjective2();

                    // view.showCommonObjectives(commonObjectiveMessage.getSender(), commonObjectiveMessage.getCommonObjective1(), commonObjectiveMessage.getCommonObjective2());

                } else {
                    System.out.println("Ignoring message to: " + message.getSender() + " of type: " + message.getType().toString());
                }
                break;
            case SHOW_PERSONAL_OBJECTIVE:
                if(message.getSender().equals(nickname)) {
                    ShowPersonalObjectiveMessage personalObjectiveMessage = (ShowPersonalObjectiveMessage) message;
                    this.personalObjective = personalObjectiveMessage.getPersonalObjective();

                    // view.showPersonalObjective(personalObjectiveMessage.getSender(), personalObjectiveMessage.getPersonalObjective());

                } else {
                    System.out.println("Ignoring message to: " + message.getSender() + " of type: " + message.getType().toString());
                }
                break;
            case SHOW_OTHERS_LIBRARY:
                ShowLibrariesMessage showLibrariesMessage = (ShowLibrariesMessage) message;
                HashMap<String, Library> notWithMe = new HashMap<>();
                for(String username : showLibrariesMessage.getLibrariesOfPlayers().keySet()){
                    if(!username.equals(nickname)){
                        notWithMe.put(username, showLibrariesMessage.getLibrariesOfPlayers().get(username));
                    }
                }

                this.otherPlayersLibrary = notWithMe;
                break;
            case END_GAME:
                EndGameMessage endGameMessage = (EndGameMessage) message;
                HashMap<String, Integer> sortedLeaderboard = sortLeaderboard(endGameMessage.getPlayersPoints(), false); /* False = ordine decrescente */
                                                                                                                              /* True = ordine crescente */
                view.showWinner(endGameMessage.getWinner(), sortedLeaderboard);
                break;
            case GENERIC_ERROR:
                System.out.println(this.getClass().toString() + " I've received an error!");
                String typeErr = parseType(message.getSender());
                String senderErr = parseSender((message.getSender()));
                if(senderErr.equals(nickname)) {
                    GenericErrorMessage genericErrorMessage = (GenericErrorMessage) message;
                    switch (typeErr){
                        case "BOARD":
                            view.showGenericError(senderErr, genericErrorMessage.getPayload());
                            System.out.println(this.getClass().toString() + " The error is for the board!");
                            inLibrary = false;
                            inPickup = true;
                            view.askBoardMove();
                            break;
                        case "LIBRARY":
                            view.showGenericError(senderErr, genericErrorMessage.getPayload());
                            System.out.println(this.getClass().toString() + " The error is for the library!");
                            inLibrary = true;
                            inPickup = false;
                            view.askLibraryMove();
                            break;
                        case "NICKNAME":
                            if(inLobby == false) {
                                view.showGenericError(senderErr, genericErrorMessage.getPayload());
                                view.askNickname();
                            }
                            break;
                        case "GENERIC":
                            System.out.println(this.getClass().toString() + " The error is generic :(");
                            default:
                            ;
                    }
                }
                break;
            case ASK_NICKNAME:
                view.askNickname();
                break;
            case ASK_MAX_PLAYER:
                view.askMaxPlayer();
                break;
            case MAX_PLAYERS_FOR_GAME:
                try {
                    client.sendMessage(message);
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
                break;
            default:
                break;
        }
    }

    /**
     * This method parses the type of error. Due to limitations of the structure of the game, this is the best way to do it.
     * @param sender is the sender in format {@code <name_of_the_player>:<error_type>}.
     * @return the type of error as a {@link String}.
     */
    private String parseType(String sender) {
        return sender.substring(sender.lastIndexOf(':') + 1);
    }

    /**
     * This method parses the name in case of error. Due to limitations of the structure of the game, this is the best way to do it.
     * @param sender is the sender in format {@code <name_of_the_player>:<error_type>}.
     * @return the name of the player {@link String}.
     */
    private String parseSender(String sender) {
        return sender.substring(0,sender.lastIndexOf(':'));
    }

    /**
     * This method will sort the leaderboard points in descending order.
     * @param playersPoints is the {@link HashMap} containing this game's leaderboard.
     * @param order is set to {@code true} for ascending order, {@code false} for descending order.
     * @return the new sorted leaderboard.
     */
    private HashMap<String, Integer> sortLeaderboard(HashMap<String, Integer> playersPoints, boolean order) {
        List<Map.Entry<String, Integer>> list = new LinkedList<>(playersPoints.entrySet());

        // Sorting the list based on values
        list.sort((o1, o2) -> order ? o1.getValue().compareTo(o2.getValue()) == 0
                ? o1.getKey().compareTo(o2.getKey())
                : o1.getValue().compareTo(o2.getValue()) : o2.getValue().compareTo(o1.getValue()) == 0
                ? o2.getKey().compareTo(o1.getKey())
                : o2.getValue().compareTo(o1.getValue()));
        return list.stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> b, LinkedHashMap::new));
    }
}
