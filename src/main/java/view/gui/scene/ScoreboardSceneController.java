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

    public void initialize(){
        closeButton.addEventHandler(MouseEvent.MOUSE_CLICKED, this :: onCloseButtonClick);
        setWinner(winner);
        setSecond(second);
        setThird(third);
        setFourth(fourth);
    }

    public void setWinner(String winnerL){
        winner = winnerL;
        if (winnerLabel != null) {
            winnerLabel.setText(winner);
        }

    }
    public void setSecond(String secondL){
        second = secondL;
        if(secondLabel != null) {
            secondLabel.setText(second);
        }
    }
    public void setThird(String thirdL){
        third = thirdL;
        if(thirdLabel!= null) {
            thirdLabel.setText(third);
        }
    }
    public void setFourth(String fourthL){
        fourth = fourthL;
        if(fourthLabel != null) {
            fourthLabel.setText(fourth);
        }
    }

    private void onCloseButtonClick(MouseEvent event){
        System.exit(0);
    }
}
