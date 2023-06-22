package view.gui;

import javafx.application.Platform;
import model.board.Board;
import model.commonobjective.CommonObjective;
import model.library.Library;
import model.library.PersonalObjective;
import model.objects.ObjectCard;
import observer.ViewObservable;
import view.View;
import view.gui.scene.BoardSceneController;
import view.gui.scene.SelectMaxPlayersSceneController;
import view.gui.scene.WaitingForPlayersSceneController;

import java.util.ArrayList;
import java.util.HashMap;

public class Gui extends ViewObservable implements View {



    public static void setPlayerNum(int playerNum){
        playerNum = playerNum;
    }
    @Override
    public void askNickname() {
        Platform.runLater(() -> SceneController.changeRootPane(observers, "Insert_Nickname.fxml" ));

    }

    @Override
    public void askMaxPlayer() {
        SelectMaxPlayersSceneController smpsc = new SelectMaxPlayersSceneController();
        smpsc.addAllObservers(observers);
        Platform.runLater(() -> SceneController.changeRootPane(smpsc,  "Select_Max_Players.fxml"));

    }

    @Override
    public void askBoardMove() {

    }

    @Override
    public void askLibraryMove() {

    }

    @Override
    public void showGenericError(String player, String payload) {
        BoardSceneController bsc = getBoardSceneController();
        bsc.resetErrorValues();
    }

    @Override
    public void showTurn(String player, Board gameBoard, Library playerLibrary, ArrayList<ObjectCard> playerObjInHand, int[] completedCommonObjectives) {

        BoardSceneController bsc = getBoardSceneController(gameBoard, playerLibrary,playerObjInHand);
       // bsc.setBoardGrid(gameBoard);
        //bsc.setLibraryGrid(playerLibrary);
    }

    @Override
    public void showCommonObjectives(String player, CommonObjective commonObjective1, CommonObjective commonObjective2, int[] completedCommonObjectives) {
        System.out.println(commonObjective1);
        System.out.println(commonObjective2);
        BoardSceneController bsc = getBoardSceneController(commonObjective1, commonObjective2);

    }




    @Override
    public void showPersonalObjective(String player, PersonalObjective personalObjective) {
        System.out.println(personalObjective);
        System.out.println(personalObjective.getPersonalOBJ());
        BoardSceneController bsc = getBoardSceneController(personalObjective);
    }

    @Override
    public void showLobby(ArrayList<String> players) {
        WaitingForPlayersSceneController wfp;

        try{
            wfp = (WaitingForPlayersSceneController) SceneController.getActiveController();
            wfp.setNicknames(players);
            Platform.runLater(wfp :: updateValues);
        } catch (ClassCastException e) {
            wfp = new WaitingForPlayersSceneController();
            wfp.addAllObservers(observers);
            wfp.setNicknames(players);
            WaitingForPlayersSceneController finalWfp = wfp;
            Platform.runLater(() -> SceneController.changeRootPane(finalWfp, "Waiting_For_Players.fxml" ));

        }
    }



    @Override
    public void showWinner(String winner, HashMap<String, Integer> leaderboard) {

    }

    @Override
    public void showNotMyTurn(Board gameBoard) {

    }

    @Override
    public void showOthersLibrary(String sender, HashMap<String, Library> librariesOfPlayers) {

    }

    @Override
    public void showChat(String sender, boolean isPrivate, String message) {

    }

    @Override
    public boolean getMyTurn() {
        return false;
    }

    @Override
    public void setMyTurn(boolean turn) {

    }

    @Override
    public void askChat() {

    }


    public void showChat(String sender, String receiver,String message) {
        //Empty because is used in the network view simulation
    }

    private BoardSceneController getBoardSceneController(Board gameBoard, Library playerLibrary, ArrayList<ObjectCard> playerObjInHand) {
        BoardSceneController bsc;
        try {
            bsc = (BoardSceneController) SceneController.getActiveController();
            bsc.setBoardGrid(gameBoard);
            bsc.setLibraryGrid(playerLibrary);
            bsc.setPlayerObjInHand(playerObjInHand);
        } catch (ClassCastException e) {
            bsc = new BoardSceneController();
            bsc.addAllObservers(observers);
            bsc.setBoardGrid(gameBoard);
            bsc.setLibraryGrid(playerLibrary);
            bsc.setPlayerObjInHand(playerObjInHand);
            BoardSceneController finalBsc = bsc;
            Platform.runLater(() -> SceneController.changeRootPane(finalBsc, "Board.fxml"));
        }
        return bsc;
    }
    private BoardSceneController getBoardSceneController(CommonObjective commonObjective1, CommonObjective commonObjective2) {
        BoardSceneController bsc;
        try {
            bsc = (BoardSceneController) SceneController.getActiveController();
            bsc.setCommonObj1Button(commonObjective1);
            bsc.setCommonObj2Button(commonObjective2);
        } catch (ClassCastException e) {
            bsc = new BoardSceneController();
            bsc.addAllObservers(observers);
            bsc.setCommonObj1Button(commonObjective1);
            bsc.setCommonObj2Button(commonObjective2);
            BoardSceneController finalBsc = bsc;
            Platform.runLater(() -> SceneController.changeRootPane(finalBsc, "Board.fxml"));
        }
        return bsc;
    }

    private BoardSceneController getBoardSceneController(PersonalObjective personalObjective) {
        BoardSceneController bsc;
        try {
            bsc = (BoardSceneController) SceneController.getActiveController();
            bsc.setPersonalObjButton(personalObjective);

        } catch (ClassCastException e) {
            bsc = new BoardSceneController();
            bsc.addAllObservers(observers);
            bsc.setPersonalObjButton(personalObjective);
            BoardSceneController finalBsc = bsc;
            Platform.runLater(() -> SceneController.changeRootPane(finalBsc, "Board.fxml"));
        }
        return bsc;
    }

    private BoardSceneController getBoardSceneController() {
        BoardSceneController bsc;
        try {
            bsc = (BoardSceneController) SceneController.getActiveController();


        } catch (ClassCastException e) {
            bsc = new BoardSceneController();
            bsc.addAllObservers(observers);


            BoardSceneController finalBsc = bsc;
            Platform.runLater(() -> SceneController.changeRootPane(finalBsc, "Board.fxml"));
        }
        return bsc;
    }
}
