package view.gui.scene;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import observer.ViewObservable;

public class ScoreboardSceneController extends ViewObservable implements GenericSceneController {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private Button closeButton;
    @FXML
    private Label winnerLabel;
    @FXML
    private Label secondLabel;
    @FXML
    private Label thirdLabel;
    @FXML
    private Label fourthLabel;

    private String winner;
    private String second;
    private String third;
    private String fourth;

    /**
     * initilises the scene
     */
    public void initialize(){
        closeButton.addEventHandler(MouseEvent.MOUSE_CLICKED, this :: onCloseButtonClick);
        setWinner(winner);
        setSecond(second);
        setThird(third);
        setFourth(fourth);
    }

    /**
     * sets the winner
     * @param winnerL is the winner
     */
    public void setWinner(String winnerL){
        winner = winnerL;
        if (winnerLabel != null) {
            winnerLabel.setText(winner);
        }

    }

    /**
     * sets the second in the chart
     * @param secondL
     */
    public void setSecond(String secondL){
        second = secondL;
        if(secondLabel != null) {
            secondLabel.setText(second);
        }
    }

    /**
     * sets the third in the chart
     * @param thirdL
     */
    public void setThird(String thirdL){
        third = thirdL;
        if(thirdLabel!= null) {
            thirdLabel.setText(third);
        }
    }

    /**
     * sets the fourth in the chart
     * @param fourthL
     */
    public void setFourth(String fourthL){
        fourth = fourthL;
        if(fourthLabel != null) {
            fourthLabel.setText(fourth);
        }
    }

    /**
     * close the window
     * @param event is the click of the user
     */
    private void onCloseButtonClick(MouseEvent event){
        System.exit(0);
    }
}
