package view.gui.scene;

import controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.Game;
import model.player.Player;
import network.messages.ShowLobbyMessage;
import observer.ViewObservable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the scene controller of the lobby.
 */
public class WaitingForPlayersSceneController extends ViewObservable implements GenericSceneController {
    private ArrayList<String> nicknames = new ArrayList<>();


    @FXML
    private Label playersNicknameLabel;
    @FXML
    private Label playerNumbersLabel;


    /**
     * This method initialises the scene.
     */
    public void initialize(){
        playersNicknameLabel.setText(String.join(", ", nicknames ));
        playerNumbersLabel.setText(String.valueOf(nicknames.size()));

    }


    /**
     * This method sets the nicknames of the players.
     * @param nicknames is the list of the players' nickname.
     */
    public void setNicknames(ArrayList<String> nicknames) {
        this.nicknames =  nicknames;
    }


    /**
     *This method replaces the number and the name.
     */
    public void updateValues(){
        playersNicknameLabel.setText(String.join(", ", this.nicknames));
        playerNumbersLabel.setText(String.valueOf((this.nicknames.size() )));
    }
}


