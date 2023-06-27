package view.gui.scene;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import observer.ViewObservable;

/**
 * This class represents the Scoreboard Scene Controller
 */
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
     * This method initialises the scene.
     */
    public void initialize(){
        closeButton.addEventHandler(MouseEvent.MOUSE_CLICKED, this :: onCloseButtonClick);
        setWinner(winner);
        setSecond(second);
        setThird(third);
        setFourth(fourth);
    }

    /**
     * This method sets the winner's label.
     * @param winnerL is the winner's label.
     */
    public void setWinner(String winnerL){
        winner = winnerL;
        if (winnerLabel != null) {
            winnerLabel.setText(winner);
        }

    }

    /**
     * This method sets the second player in the chart.
     * @param secondL is the second player's label.
     */
    public void setSecond(String secondL){
        second = secondL;
        if(secondLabel != null) {
            secondLabel.setText(second);
        }
    }

    /**
     * This method sets the third player in the chart.
     * @param thirdL is the third player's label.
     */
    public void setThird(String thirdL){
        third = thirdL;
        if(thirdLabel!= null) {
            thirdLabel.setText(third);
        }
    }

    /**
     *This method sets the fourth player in the chart.
     * @param fourthL is the fourth player's label.
     */
    public void setFourth(String fourthL){
        fourth = fourthL;
        if(fourthLabel != null) {
            fourthLabel.setText(fourth);
        }
    }

    /**
     *This method closes the window.
     * @param event is the click of the user.
     */
    private void onCloseButtonClick(MouseEvent event){
        System.exit(0);
    }
}
