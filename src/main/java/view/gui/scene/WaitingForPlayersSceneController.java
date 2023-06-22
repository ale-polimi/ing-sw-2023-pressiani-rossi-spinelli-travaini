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

public class WaitingForPlayersSceneController extends ViewObservable implements GenericSceneController {
    private ArrayList<String> nicknames = new ArrayList<>();


    @FXML
    private Label playersNicknameLabel;
    @FXML
    private Label playerNumbersLabel;


    public void initialize(){
        playersNicknameLabel.setText(String.join(", ", nicknames ));
        playerNumbersLabel.setText(String.valueOf(nicknames.size()));

    }


    public void setNicknames(ArrayList<String> nicknames) {
        this.nicknames =  nicknames;
    }



    public void updateValues(){
        playersNicknameLabel.setText(String.join(", ", this.nicknames));
        playerNumbersLabel.setText(String.valueOf((this.nicknames.size() )));
    }
}


