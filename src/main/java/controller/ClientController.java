package controller;

import network.*;
import network.client.SocketClient;
import network.client.*
import observer.Observer;
import observer.ViewObserver;
import view.View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Controller for the client.
 * It stays client side.
 */
public class ClientController implements ViewObserver, Observer {
    private final View view;
    private Client client;
    private String nickname;
    private boolean inLibrary = false;
    private boolean inPickup = true;
    private final ExecutorService taskQueue;

    public ClientController(View view){
        this.view = view;
        taskQueue = Executors.newSingleThreadExecutor();
    }

    public void onUpdateServerInfo(Map<String, String> serverInfo) {
        try {
            client = new SocketClient(serverInfo.get("address"), Integer.parseInt(serverInfo.get("port")));
            client.addObserver(this);
            client.readMessage(); // Starts an asynchronous reading from the server.
            client.enablePinger(true);
            taskQueue.execute(view::askMaxPlayer);
        } catch (IOException e) {
            taskQueue.execute(() -> view.showLoginResult(false, false, this.nickname));
        }
    }

    /**
     * This method will send the username on the network.
     * @param nickname is the nickname passed by the {@link View}.
     */
    @Override
    public void onUpdateNickname(String nickname){
        this.nickname = nickname;
        client.sendMessage(new UserInfoForLoginMessage(this.nickname, this.nickname));
    }

    @Override
    public void onMaxPlayers(int maxPlayers){
        client.sendMessage(new MaxPlayersMessage(this.nickname, maxPlayers));
    }

    @Override
    public void onUdpateBoardMove(ArrayList<Integer> coordinatesToSend) {
        inPickup = false;
        inLibrary = true;
        client.sendMessage(new PickObjectMessage(this.nickname, coordinatesToSend));
    }

    @Override
    public void onUdpateLibraryMove(ArrayList<Integer> orderAndColumnToSend) {
        inPickup = true;
        inLibrary = false;
        client.sendMessage(new PutObjectInLibraryMessage(this.nickname, orderAndColumnToSend));
    }


    /**
     * This method checks if the ip is valid as in it follows the <a href="https://en.wikipedia.org/wiki/Dot-decimal_notation">dot-decimal notation</a>.
     * @param ip is the ip to verify.
     * @return {@code true} if the ip is valid, {@code false} otherwise.
     */
    public static boolean isAddressValid(String ip){
        /* Regex pattern to check if the ip follows the dot-decimal notation standard */
        /* Sempre sia lodato Stackoverflow */
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
            case SHOW_TURN:
                if(message.getSender().equals(nickname)) {
                    if (inLibrary == true && inPickup == false) {
                        ShowTurnMessage turnMessage = (ShowTurnMessage) message;
                        view.showTurn(turnMessage.getSender(), turnMessage.getGameBoard(), turnMessage.getPlayerLibrary(), turnMessage.getPlayerObjInHand());
                        view.askLibraryMove();
                    } else if (inLibrary == false && inPickup == true) {
                        ShowTurnMessage turnMessage = (ShowTurnMessage) message;
                        view.showTurn(turnMessage.getSender(), turnMessage.getGameBoard(), turnMessage.getPlayerLibrary(), turnMessage.getPlayerObjInHand());
                        view.askBoardMove();
                    }
                }
                break;
            case SHOW_COMMON_OBJECTIVE:
                if(message.getSender().equals(nickname)) {
                    ShowCommonObjectiveMessage commonObjectiveMessage = (ShowCommonObjectiveMessage) message;
                    view.showCommonObjectives(commonObjectiveMessage.getCommonObjective1(), commonObjectiveMessage.getCommonObjective2());
                }
                break;
            case SHOW_PERSONAL_OBJECTIVE:
                if(message.getSender().equals(nickname)) {
                    ShowPersonalObjectiveMessage personalObjectiveMessage = (ShowPersonalObjectiveMessage) message;
                    view.showPersonalObjective(personalObjectiveMessage.getPersonalObjective());
                }
                break;
            case GENERIC_ERROR:
                if(message.getSender().equals(nickname)) {
                    GenericErrorMessage genericErrorMessage = (GenericErrorMessage) message;
                    view.showGenericError(genericErrorMessage.getPayload());
                }
                break;
            default:
                break;
        }
    }
}
