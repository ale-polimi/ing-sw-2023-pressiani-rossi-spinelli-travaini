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
import view.gui.scene.ScoreboardSceneController;
import view.gui.scene.SelectMaxPlayersSceneController;
import view.gui.scene.WaitingForPlayersSceneController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Gui extends ViewObservable implements View {

    private  boolean myTurn= true;

   private boolean boardUsed;


    /**
     * message to open insert nickname page
     */

    @Override
    public void askNickname() {
        Platform.runLater(() -> SceneController.changeRootPane(observers, "Insert_Nickname.fxml" ));

    }

    /**
     * message to open the ask max player page
     */

    @Override
    public void askMaxPlayer() {
        SelectMaxPlayersSceneController smpsc = new SelectMaxPlayersSceneController();
        smpsc.addAllObservers(observers);
        Platform.runLater(() -> SceneController.changeRootPane(smpsc,  "Select_Max_Players.fxml"));

    }

    /**
     * message to start a turn
     */

    @Override
    public void askBoardMove() {
        System.out.println("BOARDMOVE");
        setBoardUsed(false);

       // setMyTurn(true);
        BoardSceneController bsc = null;
        try {
            bsc = getBoardSceneController();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        bsc.resetBoardTiles();


    }

    /**
     * message to insert tiles in the library
     */
    @Override
    public void askLibraryMove() {
        System.out.println("LIBRARYMOVE");
        setBoardUsed(true);
        BoardSceneController bsc = null;
        try {
            bsc = getBoardSceneController();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        bsc.resetObjInHandTiles();
        bsc.resetLibraryTiles();


    }

    /**
     * called when there is an error
     * @param player is the recipient of the error.
     * @param payload is the error's description.
     */

    @Override
    public void showGenericError(String player, String payload) {
        BoardSceneController bsc = null;
        try {
            bsc = getBoardSceneController();
        } catch (InterruptedException e) {
           System.exit(0);

        }
        bsc.resetErrorValues();
    }

    /**
     * resets turn
     *
     * @param player                         is the player currently playing.
     * @param gameBoard                      is the board of the game.
     * @param playerLibrary                  is the player's library.
     * @param playerObjInHand                are the objects the player currently has in hand.
     * @param completedCommonObjectives      is the array of points for each completed common objective.
     * @param availableCommonObjectivePoints
     */

    @Override
    public void showTurn(String player, Board gameBoard, Library playerLibrary, ArrayList<ObjectCard> playerObjInHand, int[] completedCommonObjectives, int[] availableCommonObjectivePoints) {
        System.out.println("TURNCALL");
        BoardSceneController bsc = getBoardSceneController(gameBoard, playerLibrary,playerObjInHand);
        bsc.blockBoardTiles();
        bsc.savePlayer(player);




        // bsc.setBoardGrid(gameBoard);
        //bsc.setLibraryGrid(playerLibrary);
    }

    /**
     * shows common objectives
     *
     * @param player                         is the player currently playing.
     * @param commonObjective1               is the first common objective.
     * @param commonObjective2               is the second common objective.
     * @param completedCommonObjectives
     * @param availableCommonObjectivePoints
     */
    @Override
    public void showCommonObjectives(String player, CommonObjective commonObjective1, CommonObjective commonObjective2, int[] completedCommonObjectives, int[] availableCommonObjectivePoints) {
        System.out.println(commonObjective1);
        System.out.println(commonObjective2);
        BoardSceneController bsc = getBoardSceneController(commonObjective1, commonObjective2);
        if(getBoardUsed()){
            bsc.blockBoardTiles();
        }

    }


    /**
     * shows personal objective
     * @param player is the player currently playing.
     * @param personalObjective is the player's personal objective.
     */

    @Override
    public void showPersonalObjective(String player, PersonalObjective personalObjective) {
        System.out.println(personalObjective);
        System.out.println(personalObjective.getPersonalOBJ());
        BoardSceneController bsc = getBoardSceneController(personalObjective);
    }

    /**
     * shows lobby when waiting for players
     * @param players is an {@link ArrayList} containing the usernames of the currently connected players.
     */

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

    /**
     * shows the winner
     * @param winner is the username of the winner.
     * @param leaderboard is a {@link HashMap} containing the pairs (username, points).
     */

    @Override
    public void showWinner(String winner, HashMap<String, Integer> leaderboard) {
        int count = 0;
        ScoreboardSceneController sbsc = new ScoreboardSceneController();
        sbsc.addAllObservers(observers);
        switch (leaderboard.size()){
            case 2 ->{
                for(Map.Entry<String,Integer> entry : leaderboard.entrySet()) {
                    count++;
                    String string = entry.getKey();
                    int points = entry.getValue();
                    String score = string +":  " +points;
                    if(count == 1){
                        sbsc.setWinner(score);
                    } else if (count==2) {
                        sbsc.setSecond(score);
                    }
                }
            }
            case 3 -> {
                for(Map.Entry<String,Integer> entry : leaderboard.entrySet()) {
                    count++;
                    String string = entry.getKey();
                    int points = entry.getValue();
                    String score = string +":  " +points;
                    if(count == 1){
                        sbsc.setWinner(score);
                    } else if (count==2) {
                        sbsc.setSecond(score);
                    } else if (count == 3) {
                        sbsc.setThird(score);
                    }
                }
            }
            case 4 -> {
                for(Map.Entry<String,Integer> entry : leaderboard.entrySet()) {
                    count++;
                    String string = entry.getKey();
                    int points = entry.getValue();
                    String score = string +":  " +points;
                    if(count == 1){
                        sbsc.setWinner(score);
                    } else if (count==2) {
                        sbsc.setSecond(score);
                    } else if (count == 3) {
                        sbsc.setThird(score);
                    } else if (count == 4) {
                        sbsc.setFourth(score);
                    }
                }
            }
        }

        Platform.runLater(() -> SceneController.changeRootPane(sbsc,  "ScoreBoard.fxml"));
    }

    /**
     * shows only the board when is not the players turn
     * @param gameBoard is the board of the game.
     */
    @Override
    public void showNotMyTurn(Board gameBoard) {
        setMyTurn(false);
        System.out.println("notmyturn");
    }

    /**
     * shows the others library
     * @param sender is the sender of the message.
     * @param librariesOfPlayers is the {@link HashMap} containing the other players' usernames as keys and libraries as values.
     */
    @Override
    public void showOthersLibrary(String sender, HashMap<String, Library> librariesOfPlayers) {
        BoardSceneController bsc = null;
        try {
            bsc = getBoardSceneController();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        bsc.showLibraries(librariesOfPlayers);
    }

    /**
     * shows the chat
     * @param sender the sender of the message
     * @param isPrivate indicate if the message is private
     * @param message is the message text
     */

    @Override
    public void showChat(String sender, boolean isPrivate, String message) {
        BoardSceneController bsc = null;
        try {
           bsc = getBoardSceneController();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        bsc.updateChat(sender,message);

    }

    /**
     * @return the turn
     */
    @Override
    public boolean getMyTurn() {
        return myTurn;
    }

    /**
     *
     * @param turn is the new value of the myTurn parameter
     */
    @Override
    public void setMyTurn(boolean turn) {
        myTurn = turn;

    }

    /**
     * indicates if board is used
     * @param boardUsed
     */
    public void setBoardUsed(boolean boardUsed) {
        this.boardUsed = boardUsed;
    }

    /**
     *
     * @return boolean boardUsed
     */
    public boolean getBoardUsed(){
        return boardUsed;
    }

    @Override
    public void askChat() {

    }

    /**
     *
     * @return false
     */
    @Override
    public boolean getChatAbilitator() {
        return false;
    }


    @Override
    public void setChatAbilitator(boolean value) {

    }


    public void showChat(String sender, String receiver,String message) {
        //Empty because is used in the network view simulation
    }

    /**
     * @param gameBoard is the current board of the game
     * @param playerLibrary is the library of the player
     * @param playerObjInHand are the tiles in object in hand
     * @return bsc board scene controller
     */

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

    /**
     * @param commonObjective1 is the first common objective
     * @param commonObjective2 is the second common objective
     * @return bsc board scene controller
     */
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

    /**
     * @param personalObjective is the personal objective of the player
     * @return bsc board scene controler
     */
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

    /**
     * @return bsc board scene controller
     * @throws InterruptedException if is not available
     */
    private BoardSceneController getBoardSceneController() throws InterruptedException {
        BoardSceneController bsc;

        TimeUnit.MILLISECONDS.sleep(10);

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
