package view.gui.scene;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import observer.ViewObservable;
import view.gui.SceneController;

public class StartSceneController extends ViewObservable implements GenericSceneController {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private Button playButton;
    @FXML
    private Button exitButton;


    @FXML
    public void initialize() {
        playButton.addEventHandler(MouseEvent.MOUSE_CLICKED, this::onPlayBtnClick);
        exitButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> System.exit(0));
    }




    private void onPlayBtnClick(Event event) {
        SceneController.changeRootPane(observers, event, "connect_scene.fxml");
    }
}
