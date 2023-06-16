package view.gui.scene;

import controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.Game;
import model.player.Player;
import network.messages.ShowLobbyMessage;
import observer.ViewObservable;

import java.util.List;

public class WaitingForPlayersSceneController extends ViewObservable implements GenericSceneController {


    @FXML
    private Label playersNicknameLabel;
    @FXML
    private Label playerNumbersLabel;

    public void initialize(){
        playersNicknameLabel.setText(String.join(", ", ));
        playerNumbersLabel.setText(nicknames.size()+"/" + maxPlayers );

    }


    public void setNicknames(List<String> nicknames) {
        this.nicknames = nicknames;
    }
    public void setMaxPlayers(int maxPlayers){
        this.maxPlayers = maxPlayers;
    }


    public void updateValues(){
        playersNicknameLabel.setText(String.join(", ", this.nicknames));
        playerNumbersLabel.setText((this.nicknames.size() + "/" + Game.MAX_PLAYERS));
    }
}


