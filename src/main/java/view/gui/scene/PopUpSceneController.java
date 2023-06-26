package view.gui.scene;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import view.gui.SceneController;

public class PopUpSceneController implements GenericSceneController{

    private final Stage stage;
    private double xOffset;
    private double yOffset;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private Button confirmButton;

    public PopUpSceneController(){
        stage = new Stage();
        stage.initOwner(SceneController.getActiveScene().getWindow());
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setAlwaysOnTop(true);
        xOffset = 0;
        yOffset = 0;
    }

    /**
     * initialises the scene
     */
    @FXML
    public void initialize(){
        rootPane.addEventHandler(MouseEvent.MOUSE_PRESSED, this::onRootPaneMousePressed);
        rootPane.addEventHandler(MouseEvent.MOUSE_DRAGGED, this::onRootPaneMouseDragged);
        confirmButton.addEventHandler(MouseEvent.MOUSE_CLICKED, this:: onConfirmButtonClick);
    }

    /**
     * sets coordinates and allows to move the window in the screen
     * @param event is the click of the user
     */
    private void onRootPaneMousePressed(MouseEvent event) {
        xOffset = stage.getX() - event.getScreenX();
        yOffset = stage.getY() - event.getScreenY();
    }

    /**
     * moves the windows
     * @param event
     */
    private void onRootPaneMouseDragged(MouseEvent event) {
        stage.setX(event.getScreenX() + xOffset);
        stage.setY(event.getScreenY() + yOffset);
    }

    /**
     * close the stage
     * @param event is the click of the user
     */
    private void onConfirmButtonClick(MouseEvent event){
        stage.close();
    }

    /**
     * shows the popup window
     */
    public void displayPopUp(){
        stage.showAndWait();
    }

    /**
     * sets the scene
     * @param scene
     */

    public void setScene(Scene scene){
        stage.setScene(scene);
    }
}
