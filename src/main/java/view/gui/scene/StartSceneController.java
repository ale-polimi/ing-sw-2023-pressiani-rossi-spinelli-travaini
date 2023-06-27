package view.gui.scene;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;
import observer.ViewObservable;
import view.gui.SceneController;

/**
 * This class represents the Start Scene Controller.
 */
public class StartSceneController extends ViewObservable implements GenericSceneController {

    @FXML
    private VBox size;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private Button playButton;
    @FXML
    private Button exitButton;

    @FXML
    private ImageView playImg;

    @FXML
    private ImageView exitImg;


    /**
     * This method initialises the scene.
     */
    @FXML
    public void initialize() {
      /*  Platform.runLater(() ->{
            Stage stage = (Stage) rootPane.getScene().getWindow();

            addResizeListeners(stage);
            resizeScene(stage);

        });

       */






        playButton.addEventHandler(MouseEvent.MOUSE_CLICKED,  this :: onPlayButtonClick);
        exitButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> System.exit(0));
    }

   /* public void addResizeListeners(Stage stage){
        stage.widthProperty().addListener((obs, oldVal, newVal) -> resizeScene(stage));
        stage.heightProperty().addListener((obs, oldVal, newVal) -> resizeScene(stage));


    }

    */




    /*private void resizeScene(Stage stage) {
        double screenWidth = stage.getWidth();
        double screenHeight = stage.getHeight();


        double newWidth, newHeight;
        newWidth = screenWidth;
        newHeight = screenHeight;



        rootPane.setPrefWidth(newWidth);
        rootPane.setPrefHeight(newHeight);
        playButton.setPrefHeight(newHeight/6.02);
        playButton.setPrefWidth(newWidth/2.45);
        exitButton.setPrefHeight(newHeight/6.02);
        exitButton.setPrefWidth(newWidth/2.45);
        playImg.setPreserveRatio(true);
        playImg.fitWidthProperty().bind(playButton.prefWidthProperty());
        playImg.fitHeightProperty().bind(playButton.prefHeightProperty());
        exitImg.setPreserveRatio(true);
        exitImg.fitWidthProperty().bind(exitButton.prefWidthProperty());
        exitImg.fitHeightProperty().bind(exitButton.prefHeightProperty());
    }



     */


    /**
     * This method sets the game scene.
     * @param event is the click of the user.
     */
    private void onPlayButtonClick(Event event) {
        SceneController.changeRootPane(observers, event, "ServerInfo.fxml");
    }
}
