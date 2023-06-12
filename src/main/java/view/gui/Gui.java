package view.gui;

import model.board.Board;
import model.commonobjective.CommonObjective;
import model.library.Library;
import model.library.PersonalObjective;
import model.objects.ObjectCard;
import observer.ViewObservable;
import view.View;

import java.util.ArrayList;
import java.util.HashMap;

public class Gui extends ViewObservable implements View {
    @Override
    public void askNickname() {

    }

    @Override
    public void askMaxPlayer() {

    }

    @Override
    public void askBoardMove() {

    }

    @Override
    public void askLibraryMove() {

    }

    @Override
    public void showGenericError(String player, String payload) {

    }

    @Override
    public void showTurn(String player, Board gameBoard, Library playerLibrary, ArrayList<ObjectCard> playerObjInHand) {

    }

    @Override
    public void showCommonObjectives(String player, CommonObjective commonObjective1, CommonObjective commonObjective2) {

    }

    @Override
    public void showPersonalObjective(String player, PersonalObjective personalObjective) {

    }

    @Override
    public void showLobby(ArrayList<String> players) {

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
    public void showChat(String sender, String message) {

    }
}
