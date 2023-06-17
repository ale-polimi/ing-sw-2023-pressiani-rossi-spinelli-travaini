package view.gui.scene;

import controller.ClientController;
import javafx.css.PseudoClass;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import observer.ViewObservable;
import view.gui.SceneController;



import java.util.Map;

public class ServerInfoSceneController extends ViewObservable implements GenericSceneController {

    private final PseudoClass ERROR_PSEUDO_CLASS = PseudoClass.getPseudoClass("error");

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TextField serverAddressField;

    @FXML
    private TextField serverPortField;

    @FXML
    private Button submitButton;
    @FXML
    private Button returnButton;


    public void initialize(){
        submitButton.addEventHandler(MouseEvent.MOUSE_CLICKED,  this :: onSubmitButtonClick);
        returnButton.addEventHandler(MouseEvent.MOUSE_CLICKED, this :: onReturnButtonClick);
    }



    private void onSubmitButtonClick(Event event){
        String address = serverAddressField.getText();
        String port = serverPortField.getText();

        boolean isAddressValid = ClientController.isAddressValid(address);
        boolean isPortValid = ClientController.isPortValid(port);

        serverAddressField.pseudoClassStateChanged(ERROR_PSEUDO_CLASS, !isAddressValid);
        serverPortField.pseudoClassStateChanged(ERROR_PSEUDO_CLASS, !isPortValid);

        if(isAddressValid && isPortValid) {
            returnButton.setDisable(true);
            submitButton.setDisable(true);

            Map<String, String>  updateServerInfo = Map.of("address",address,"port", port);
            new Thread(() -> notifyObserver(obs -> obs.onUpdateServerInfo(updateServerInfo))).start();
        }

    }

    private void onReturnButtonClick(Event event){
        returnButton.setDisable(true);
        submitButton.setDisable(true);

        SceneController.changeRootPane(observers, event, "Start.fxml");

    }
}
