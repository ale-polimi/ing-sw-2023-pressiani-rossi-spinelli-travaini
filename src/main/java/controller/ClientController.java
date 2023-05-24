package controller;

import model.commonobjective.CommonObjective;
import model.library.PersonalObjective;
import network.*;
import network.structure.Client;
import network.structure.ClientSocket;
import observer.Observer;
import observer.ViewObserver;
import view.View;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * Controller for the client.
 * It stays client side.
 */
public class ClientController implements ViewObserver, Observer {
    private final View view;
    private Client client;
    private String nickname;
    private CommonObjective commonObjective1;
    private CommonObjective commonObjective2;
    private PersonalObjective personalObjective;
    private boolean inLibrary = false;
    private boolean inPickup = true;
    private final ExecutorService taskQueue;

    public ClientController(View view){
        this.view = view;
        taskQueue = Executors.newSingleThreadExecutor();
    }

    @Override
    public void onUpdateServerInfo(Map<String, String> serverInfo) {
        System.out.println("Entrato");
        client = new ClientSocket(serverInfo.get("address"), Integer.parseInt(serverInfo.get("port")));
        System.out.println("Socket creato");
        ((ClientSocket)client).addObserver(this);
        //client.receivedMessage(new UserInfoForLoginMessage(null, this.nickname)); // Starts an asynchronous reading from the server.
        //client.ping();
        //taskQueue.execute(view::askNickname);
        //taskQueue.execute(view::askMaxPlayer);
        System.out.println("Fine Server Update");
    }

    @Override
    public void onUpdateNickname(String nickname) {
        this.nickname = nickname;
        client.sendMessage(new UserInfoForLoginMessage(this.nickname, this.nickname));
        System.out.println("Numero letto");
    }

    @Override
    public void onMaxPlayers(int maxPlayers) {
        client.sendMessage(new MaxPlayersMessage(this.nickname, maxPlayers));
    }

    @Override
    public void onUpdateBoardMove(ArrayList<Integer> coordinatesToSend){
        inPickup = false;
        inLibrary = true;
        client.sendMessage(new PickObjectMessage(this.nickname, coordinatesToSend));
    }

    @Override
    public void onUpdateLibraryMove(ArrayList<Integer> orderAndColumnToSend){
        inPickup = true;
        inLibrary = false;
        client.sendMessage(new PutObjectInLibraryMessage(this.nickname, orderAndColumnToSend));
    }

    @Override
    public void onRequestCommonObjectives() {
        view.showCommonObjectives(this.nickname, this.commonObjective1, this.commonObjective2);
    }

    @Override
    public void onRequestPersonalObjective() {
        view.showPersonalObjective(this.nickname, this.personalObjective);
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

    @Override
    public void update(Message message) {
        switch (message.getType()){
            case SHOW_LOBBY:
                ShowLobbyMessage lobbyMessage = (ShowLobbyMessage) message;
                view.showLobby(lobbyMessage.getLobbyPlayers());
                break;
            case SHOW_TURN:
                if(message.getSender().equals(nickname)) {
                    if (inLibrary == true && inPickup == false) {
                        assert message instanceof ShowTurnMessage;
                        ShowTurnMessage turnMessage = (ShowTurnMessage) message;
                        view.showTurn(turnMessage.getSender(), turnMessage.getGameBoard(), turnMessage.getPlayerLibrary(), turnMessage.getPlayerObjInHand());
                        view.askLibraryMove();
                    } else if (inLibrary == false && inPickup == true) {
                        assert message instanceof ShowTurnMessage;
                        ShowTurnMessage turnMessage = (ShowTurnMessage) message;
                        view.showTurn(turnMessage.getSender(), turnMessage.getGameBoard(), turnMessage.getPlayerLibrary(), turnMessage.getPlayerObjInHand());
                        view.askBoardMove();
                    }
                } else {
                    assert message instanceof ShowTurnMessage;
                    ShowTurnMessage turnMessage = (ShowTurnMessage) message;
                    view.showNotMyTurn(turnMessage.getGameBoard());
                }
                break;
            case SHOW_COMMON_OBJECTIVE:
                if(message.getSender().equals(nickname)) {
                    ShowCommonObjectiveMessage commonObjectiveMessage = (ShowCommonObjectiveMessage) message;
                    this.commonObjective1 = commonObjectiveMessage.getCommonObjective1();
                    this.commonObjective2 = commonObjectiveMessage.getCommonObjective2();
                    /*
                    view.showCommonObjectives(commonObjectiveMessage.getSender(), commonObjectiveMessage.getCommonObjective1(), commonObjectiveMessage.getCommonObjective2());
                     */
                }
                break;
            case SHOW_PERSONAL_OBJECTIVE:
                if(message.getSender().equals(nickname)) {
                    ShowPersonalObjectiveMessage personalObjectiveMessage = (ShowPersonalObjectiveMessage) message;
                    this.personalObjective = personalObjectiveMessage.getPersonalObjective();
                    /*
                    view.showPersonalObjective(personalObjectiveMessage.getSender(), personalObjectiveMessage.getPersonalObjective());
                     */
                }
                break;
            case END_GAME:
                EndGameMessage endGameMessage = (EndGameMessage) message;
                HashMap<String, Integer> sortedLeaderboard = sortLeaderboard(endGameMessage.getPlayersPoints(), false); /* False = ordine decrescente */
                                                                                                                              /* True = ordine crescente */
                view.showWinner(endGameMessage.getWinner(), sortedLeaderboard);
                break;
            case GENERIC_ERROR:
                if(message.getSender().equals(nickname)) {
                    assert message instanceof GenericErrorMessage;
                    GenericErrorMessage genericErrorMessage = (GenericErrorMessage) message;
                    view.showGenericError(genericErrorMessage.getSender(), genericErrorMessage.getPayload());
                }
                break;
            case ASK_NICKNAME:
                view.askNickname();
                break;
            case ASK_MAX_PLAYER:
                view.askMaxPlayer();
                break;
            case MAX_PLAYERS_FOR_GAME:
                client.sendMessage(message);
                break;
            default:
                break;
        }
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
