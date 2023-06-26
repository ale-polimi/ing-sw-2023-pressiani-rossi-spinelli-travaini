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


    /**
     * initializes the scene
     */

    public void initialize(){
        twoPlayersButton.addEventHandler(MouseEvent.MOUSE_CLICKED, this :: onTwoPlayersButtonClick);
        threePlayersButton.addEventHandler(MouseEvent.MOUSE_CLICKED, this :: onThreePlayersButtonClick);
        fourPlayersButton.addEventHandler(MouseEvent.MOUSE_CLICKED, this :: onFourPlayersButtonClick);

    }

    /**
     * sets the number of players, in this case 2
     * @param event is the click of the user
     */
    private void onTwoPlayersButtonClick(Event event){
        twoPlayersButton.setDisable(true);
        threePlayersButton.setDisable(true);
        fourPlayersButton.setDisable(true);


        int players = 2;


        new Thread(() -> notifyObserver(obs -> obs.onMaxPlayers(players))).start();
    }

    /**
     * sets the number of players, in this case 3
     * @param event is the click of the user
     */
    private void onThreePlayersButtonClick(Event event){
        twoPlayersButton.setDisable(true);
        threePlayersButton.setDisable(true);
        fourPlayersButton.setDisable(true);


        int players = 3;



        new Thread(() -> notifyObserver(obs -> obs.onMaxPlayers(players))).start();
    }

    /**
     * sets the number of players, in this case 4
     * @param event is the click of the user
     */
    private void onFourPlayersButtonClick(Event event){
        twoPlayersButton.setDisable(true);
        threePlayersButton.setDisable(true);
        fourPlayersButton.setDisable(true);


        int players = 4;


        new Thread(() -> notifyObserver(obs -> obs.onMaxPlayers(players))).start();
    }

}
