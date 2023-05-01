package controller;

import network.*;
import observer.Observer;
import view.View;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Controller for the client.
 * It stays client side.
 */
public class ClientController implements Observer {
    private final View view;
    private String nickname;
    private final ExecutorService taskQueue;

    public ClientController(View view){
        this.view = view;
        taskQueue = Executors.newSingleThreadExecutor();
    }

    public void onUpdateNickname(String nickname){
        this.nickname = nickname;
        Client.sendMessage(new UserInfoForLoginMessage(this.nickname,this.nickname));
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
            case SHOW_BOARD:
                ShowBoardMessage boardMessage = (ShowBoardMessage) message;
                view.showBoard(boardMessage.getGameBoard());
                break;
            case SHOW_LIBRARY:
                ShowLibraryMessage libraryMessage = (ShowLibraryMessage) message;
                view.showLibrary(libraryMessage.getPlayerLibrary());
                break;
            case SHOW_OBJECTS_IN_HAND:
                break;
            case SHOW_COMMON_OBJECTIVE:
                ShowCommonObjectiveMessage commonObjectiveMessage = (ShowCommonObjectiveMessage) message;
                view.showCommonObjectives(commonObjectiveMessage.getCommonObjective1(), commonObjectiveMessage.getCommonObjective2());
                break;
            case SHOW_PERSONAL_OBJECTIVE:
                ShowPersonalObjectiveMessage personalObjectiveMessage = (ShowPersonalObjectiveMessage) message;
                view.showPersonalObjective(personalObjectiveMessage.getPersonalObjective());
                break;
            case GENERIC_ERROR:
                GenericErrorMessage genericErrorMessage = (GenericErrorMessage) message;
                view.showGenericError(genericErrorMessage.getPayload());
                break;
            default:
                break;
        }
    }
}
