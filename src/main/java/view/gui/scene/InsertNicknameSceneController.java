package view.gui.scene;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import observer.ViewObservable;

public class InsertNicknameSceneController extends ViewObservable implements GenericSceneController {

    @FXML
    private TextField nicknameField;

    @FXML
    private Button submitButton;


    /**
     * initializes the scene
     */
    public void initialize(){
        submitButton.addEventHandler(MouseEvent.MOUSE_CLICKED, this :: onSubmitButtonClick);
    }


    /**
     * submit the player's nickname
     * @param event is the click of the user
     */
    private void onSubmitButtonClick(Event event){
        submitButton.setDisable(true);

        String nickname = nicknameField.getText();

        new Thread(() -> notifyObserver(obs -> obs.onUpdateNickname(nickname))).start();
    }
}
