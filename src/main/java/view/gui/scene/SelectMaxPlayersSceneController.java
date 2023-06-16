package view.gui.scene;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import observer.ViewObservable;
import view.gui.Gui;

public class SelectMaxPlayersSceneController extends ViewObservable implements GenericSceneController {

    @FXML
    private Button twoPlayersButton;
    @FXML
    private Button threePlayersButton;
    @FXML
    private Button fourPlayersButton;



    public void initialize(){
        twoPlayersButton.addEventHandler(MouseEvent.MOUSE_CLICKED, this :: onTwoPlayersButtonClick);
        threePlayersButton.addEventHandler(MouseEvent.MOUSE_CLICKED, this :: onThreePlayersButtonClick);
        fourPlayersButton.addEventHandler(MouseEvent.MOUSE_CLICKED, this :: onFourPlayersButtonClick);

    }

    private void onTwoPlayersButtonClick(Event event){
        twoPlayersButton.setDisable(true);
        threePlayersButton.setDisable(true);
        fourPlayersButton.setDisable(true);


        int players = 2;
        Gui.setPlayerNum(players);

        new Thread(() -> notifyObserver(obs -> obs.onMaxPlayers(players))).start();
    }

    private void onThreePlayersButtonClick(Event event){
        twoPlayersButton.setDisable(true);
        threePlayersButton.setDisable(true);
        fourPlayersButton.setDisable(true);


        int players = 3;
        Gui.setPlayerNum(players);


        new Thread(() -> notifyObserver(obs -> obs.onMaxPlayers(players))).start();
    }

    private void onFourPlayersButtonClick(Event event){
        twoPlayersButton.setDisable(true);
        threePlayersButton.setDisable(true);
        fourPlayersButton.setDisable(true);


        int players = 4;
        Gui.setPlayerNum(players);

        new Thread(() -> notifyObserver(obs -> obs.onMaxPlayers(players))).start();
    }

}
