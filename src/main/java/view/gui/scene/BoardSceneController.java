package view.gui.scene;

import enumerations.ObjectColour;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import model.board.Board;
import model.commonobjective.*;
import model.library.Library;
import model.library.PersonalObjective;
import model.objects.ObjectCard;
import observer.ViewObservable;
import view.gui.Gui;
import view.gui.SceneController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * This class represents the Board Scene Controller, which controls the board scenes.
 */
public class BoardSceneController extends ViewObservable implements GenericSceneController {

    @FXML
    private GridPane boardGrid;
    @FXML
    private GridPane objInHandGrid;
    @FXML
    private Button sendButton;
    @FXML
    private GridPane libraryGrid;
    @FXML
    private Button confirmButton;
    @FXML
    private Button librariesButton;
    @FXML
    private Button commonObj1Button;
    @FXML
    private Button commonObj2Button;
    @FXML
    private Button personalObjButton;
    @FXML
    private Button availablePoints1;
    @FXML
    private Button availablePoints2;
    @FXML
    private Button first;
    @FXML
    private Button firsttofinish;
    @FXML
    private TextField chatMsg;
    @FXML
    private TextArea chatBox;
    private Board gameBoard1;
    private Library playerLibrary1;
    private ArrayList<ObjectCard>playerObjInHand1;
    private ArrayList<Integer> coordinatesToSend = new ArrayList<>();
    private ArrayList<Integer>orderObjects = new ArrayList<>();
    private int libColumn;
    private CommonObjective commonObjective11;
    private CommonObjective commonObjective21;
    private PersonalObjective personalObjective1;
    private String myPlayer;
    private String chatLog ;
    private String firstToFinish;

    private int objInHand = 0;
    private boolean boardFirstTurn = false;
    private int available_points1 = 0;
    private int available_points2 = 0;
    private int [] available_commonObj_points;

    /**
     * This method initializes first game window.
     */
    public void initialize(){

        for(Node node : boardGrid.getChildren()){
            Button b = (Button) node;
            b.addEventHandler(MouseEvent.MOUSE_CLICKED, this::onBoardSpaceClick);
        }

        for(Node node : libraryGrid.getChildren()){
            Button b = (Button) node;
            b.addEventHandler(MouseEvent.MOUSE_CLICKED, this::onLibrarySpaceClick);
        }

        for(Node node :objInHandGrid.getChildren()){
            Button b = (Button) node;
            b.addEventHandler(MouseEvent.MOUSE_CLICKED, this::onObjInHandClick);
        }
        confirmButton.addEventHandler(MouseEvent.MOUSE_CLICKED, this::onConfirmButtonClick);
        sendButton.addEventHandler(MouseEvent.MOUSE_CLICKED, this::onSendButtonClick);
        librariesButton.addEventHandler(MouseEvent.MOUSE_CLICKED, this :: onShowLibrariesClick);
        commonObj1Button.addEventHandler(MouseEvent.MOUSE_CLICKED, this::onCommonObjClick);
        commonObj2Button.addEventHandler(MouseEvent.MOUSE_CLICKED, this::onCommonObjClick);
        personalObjButton.addEventHandler(MouseEvent.MOUSE_CLICKED, this :: onPersonalObjClick);
        setBoardGrid(gameBoard1);
        setLibraryGrid(playerLibrary1);
        setPlayerObjInHand(playerObjInHand1);
        setCommonObj1Button(commonObjective11);
        setCommonObj2Button(commonObjective21);
        setPersonalObjButton(personalObjective1);
        setAvailablePoints(available_commonObj_points);
        setFirstButton(firstToFinish);
        BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/common_Obj1_Button.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background background = new Background(backgroundImage);
        commonObj1Button.setBackground(background);
        BackgroundImage backgroundImage2 = new BackgroundImage(new Image(getClass().getResource("/images/common_Obj2_Button.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background background2 = new Background(backgroundImage2);
        commonObj2Button.setBackground(background2);
        BackgroundImage backgroundImageP = new BackgroundImage(new Image(getClass().getResource("/images/personal_Obj_Button.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background backgroundP = new Background(backgroundImageP);
        personalObjButton.setBackground(backgroundP);
        blockBoardTiles();
        if(boardFirstTurn){
            resetBoardTiles();
        }
    }

    /**
     * This method sets the first player tile based on if it has been taken or not
     * @param firstPlayerToEnd this parameter contains the string nickname of the first player to finish.
     */
    public void setFirstButton(String firstPlayerToEnd){
        if(firstPlayerToEnd != null && first != null){
            first.setStyle("-fx-background-color: transparent");
            if(firstPlayerToEnd.equals(myPlayer)  && firsttofinish != null) {
                System.out.println("ARRIVATO");
                firsttofinish.setStyle(null);
                BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/end_game_tile.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                Background background = new Background(backgroundImage);
                firsttofinish.setBackground(background);
            }
        }else{
            if(first != null) {
                BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/end_game_tile.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                Background background = new Background(backgroundImage);
                first.setBackground(background);
            }
            if(firsttofinish != null){
                firsttofinish.setStyle("-fx-background-color: transparent");
            }
        }
        firstToFinish = firstPlayerToEnd;


    }

    /**
     * this method sets the image showing how many points are still available for both common objectives
     * @param availableCommonObjectivePoints is an int array containing the remaining points for both common objectives
     */
    public void setAvailablePoints(int[] availableCommonObjectivePoints) {
       if(availableCommonObjectivePoints != null){
           available_points1 = availableCommonObjectivePoints[0];
           available_points2 = availableCommonObjectivePoints[1];
           available_commonObj_points = availableCommonObjectivePoints;
       }

       if(availablePoints1 != null && availableCommonObjectivePoints != null){
           switch (availableCommonObjectivePoints[0]){
               case 8 ->{
                   BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/scoring_8.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                   Background background = new Background(backgroundImage);
                   availablePoints1.setBackground(background);
               }
               case 6 ->{
                   BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/scoring_6.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                   Background background = new Background(backgroundImage);
                   availablePoints1.setBackground(background);
               }
               case 4 ->{
                   BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/scoring_4.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                   Background background = new Background(backgroundImage);
                   availablePoints1.setBackground(background);
               }
               case 2 ->{
                   BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/scoring_2.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                   Background background = new Background(backgroundImage);
                   availablePoints1.setBackground(background);
               }
               case 0 ->{
                   BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/scoring_back_EMPTY.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                   Background background = new Background(backgroundImage);
                   availablePoints1.setBackground(background);
               }
           }
       }

       if(availablePoints2 != null && availableCommonObjectivePoints != null){
           switch (availableCommonObjectivePoints[1]){
               case 8 ->{
                   BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/scoring_8.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                   Background background = new Background(backgroundImage);
                   availablePoints2.setBackground(background);
               }
               case 6 ->{
                   BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/scoring_6.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                   Background background = new Background(backgroundImage);
                   availablePoints2.setBackground(background);
               }
               case 4 ->{
                   BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/scoring_4.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                   Background background = new Background(backgroundImage);
                   availablePoints2.setBackground(background);
               }
               case 2 ->{
                   BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/scoring_2.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                   Background background = new Background(backgroundImage);
                   availablePoints2.setBackground(background);
               }
               case 0 ->{
                   BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/scoring_back_EMPTY.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                   Background background = new Background(backgroundImage);
                   availablePoints2.setBackground(background);
               }
           }

       }
    }

    /**
     * This method sets the image of the personal objectives.
     * @param personalObjective is the personal objective of the player.
     */
    public void setPersonalObjButton(PersonalObjective personalObjective){
        if(personalObjective != null){
            personalObjective1 = personalObjective;
        }

        if(personalObjButton != null && personalObjective != null){
            if (("0,0,PINK1,0,2,BLUE1,1,4,GREEN1,2,3,WHITE1,3,1,YELLOW1,5,2,LIGHT_BLUE1").compareTo(personalObjective.getPersonalOBJ()) == 0){
                BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/Personal_Goals.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                Background background = new Background(backgroundImage);
                personalObjButton.setBackground(background);
            }else if (("1,1,PINK1,2,0,GREEN1,2,2,YELLOW1,3,4,WHITE1,4,3,LIGHT_BLUE1,5,4,BLUE1").compareTo(personalObjective.getPersonalOBJ()) == 0){
                BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/Personal_Goals2.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                Background background = new Background(backgroundImage);
                personalObjButton.setBackground(background);
            }else if (("1,0,BLUE1,1,3,YELLOW1,2,2,PINK1,3,1,GREEN1,3,4,LIGHT_BLUE1,5,0,WHITE1").compareTo(personalObjective.getPersonalOBJ()) == 0){
                BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/Personal_Goals3.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                Background background = new Background(backgroundImage);
                personalObjButton.setBackground(background);
            }else if (("0,4,YELLOW1,2,0,LIGHT_BLUE1,2,2,BLUE1,3,3,PINK1,4,1,WHITE1,4,2,GREEN1").compareTo(personalObjective.getPersonalOBJ()) == 0){
                BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/Personal_Goals4.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                Background background = new Background(backgroundImage);
                personalObjButton.setBackground(background);
            }else if (("1,1,LIGHT_BLUE1,3,1,BLUE1,3,2,WHITE1,4,4,PINK1,5,0,YELLOW1,5,3,GREEN1").compareTo(personalObjective.getPersonalOBJ()) == 0){
                BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/Personal_Goals5.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                Background background = new Background(backgroundImage);
                personalObjButton.setBackground(background);
            }else if (("0,2,LIGHT_BLUE1,0,4,GREEN1,2,3,WHITE1,4,1,YELLOW1,4,3,BLUE1,5,0,PINK1").compareTo(personalObjective.getPersonalOBJ()) == 0){
                BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/Personal_Goals6.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                Background background = new Background(backgroundImage);
                personalObjButton.setBackground(background);
            }else if (("0,0,GREEN1,1,3,BLUE1,2,1,PINK1,3,0,LIGHT_BLUE1,4,4,YELLOW1,5,2,WHITE1").compareTo(personalObjective.getPersonalOBJ()) == 0){
                BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/Personal_Goals7.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                Background background = new Background(backgroundImage);
                personalObjButton.setBackground(background);
            }else if (("0,4,BLUE1,1,1,GREEN1,2,2,LIGHT_BLUE1,3,0,PINK1,4,3,WHITE1,5,3,YELLOW1").compareTo(personalObjective.getPersonalOBJ()) == 0){
                BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/Personal_Goals8.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                Background background = new Background(backgroundImage);
                personalObjButton.setBackground(background);
            }else if (("0,2,YELLOW1,2,2,GREEN1,3,4,WHITE1,4,1,LIGHT_BLUE1,4,4,PINK1,5,0,BLUE1").compareTo(personalObjective.getPersonalOBJ()) == 0){
                BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/Personal_Goals9.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                Background background = new Background(backgroundImage);
                personalObjButton.setBackground(background);
            }else if (("0,4,LIGHT_BLUE1,1,1,YELLOW1,2,0,WHITE1,3,3,GREEN1,4,1,BLUE1,5,3,PINK1").compareTo(personalObjective.getPersonalOBJ()) == 0){
                BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/Personal_Goals10.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                Background background = new Background(backgroundImage);
                personalObjButton.setBackground(background);
            }else if (("0,2,PINK1,1,1,WHITE1,2,0,YELLOW1,3,2,BLUE1,4,4,GREEN1,5,3,LIGHT_BLUE1").compareTo(personalObjective.getPersonalOBJ()) == 0){
                BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/Personal_Goals11.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                Background background = new Background(backgroundImage);
                personalObjButton.setBackground(background);
            }else if (("0,2,WHITE1,1,1,PINK1,2,2,BLUE1,3,3,LIGHT_BLUE1,4,4,YELLOW1,5,0,GREEN1").compareTo(personalObjective.getPersonalOBJ()) == 0){
                BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/Personal_Goals12.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                Background background = new Background(backgroundImage);
                personalObjButton.setBackground(background);
            }

        }
    }

    /**
     * This method sets the image of the first common objective.
     * @param commonObjective1 is the common objective.
     */
    public void setCommonObj1Button(CommonObjective commonObjective1){
       if( commonObjective1 != null){
        commonObjective11 = commonObjective1;
       }
        if(commonObj1Button != null && commonObjective1 != null) {
            if (commonObjective1.getClass().equals(FiveX.class)) {
                BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/fiveX.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                Background background = new Background(backgroundImage);
                commonObj1Button.setBackground(background);
            } else if (commonObjective1.getClass().equals(FourByFourNew.class)) {
                BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/four_by_four.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                Background background = new Background(backgroundImage);
                commonObj1Button.setBackground(background);
            } else if (commonObjective1.getClass().equals(FourCorners.class)) {
                BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/four_corners.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                Background background = new Background(backgroundImage);
                commonObj1Button.setBackground(background);
            } else if (commonObjective1.getClass().equals(Diagonal.class)) {
                BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/diagonal.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                Background background = new Background(backgroundImage);
                commonObj1Button.setBackground(background);
            } else if (commonObjective1.getClass().equals(EightEquals.class)) {
                BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/eight_equals.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                Background background = new Background(backgroundImage);
                commonObj1Button.setBackground(background);
            } else if (commonObjective1.getClass().equals(FourRowsMaxThreeDifferent.class)) {
                BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/four_rows_max_three_different.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                Background background = new Background(backgroundImage);
                commonObj1Button.setBackground(background);
            } else if (commonObjective1.getClass().equals(SixByTwoNew.class)) {
                BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/six_by_two.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                Background background = new Background(backgroundImage);
                commonObj1Button.setBackground(background);
            } else if (commonObjective1.getClass().equals(ThreeColumnsMaxThreeDifferent.class)) {
                BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/three_columns_max_three_different.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                Background background = new Background(backgroundImage);
                commonObj1Button.setBackground(background);
            } else if (commonObjective1.getClass().equals(TotalDifferentColumns.class)) {
                BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/total_different_columns.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                Background background = new Background(backgroundImage);
                commonObj1Button.setBackground(background);
            } else if (commonObjective1.getClass().equals(TotalDifferentRows.class)) {
                BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/total_different_rows.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                Background background = new Background(backgroundImage);
                commonObj1Button.setBackground(background);
            } else if (commonObjective1.getClass().equals(TwoByFour.class)) {
                BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/two_by_four.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                Background background = new Background(backgroundImage);
                commonObj1Button.setBackground(background);
            } else if (commonObjective1.getClass().equals(Stairs.class)) {
                BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/stairs.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                Background background = new Background(backgroundImage);
                commonObj1Button.setBackground(background);
            }
        }

    }

    /**
     * This method sets the image of the second common objective.
     * @param commonObjective2 is the common objective.
     */
    public void setCommonObj2Button(CommonObjective commonObjective2){
        commonObjective21 = commonObjective2;
        if(commonObj2Button != null && commonObjective2 != null){
            if (commonObjective2.getClass().equals(FiveX.class)) {
                BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/fiveX.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                Background background = new Background(backgroundImage);
                commonObj2Button.setBackground(background);
            } else if (commonObjective2.getClass().equals(FourByFourNew.class)) {
                BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/four_by_four.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                Background background = new Background(backgroundImage);
                commonObj2Button.setBackground(background);
            } else if (commonObjective2.getClass().equals(FourCorners.class)) {
                BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/four_corners.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                Background background = new Background(backgroundImage);
                commonObj2Button.setBackground(background);
            } else if (commonObjective2.getClass().equals(Diagonal.class)) {
                BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/diagonal.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                Background background = new Background(backgroundImage);
                commonObj2Button.setBackground(background);
            } else if (commonObjective2.getClass().equals(EightEquals.class)) {
                BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/eight_equals.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                Background background = new Background(backgroundImage);
                commonObj2Button.setBackground(background);
            } else if (commonObjective2.getClass().equals(FourRowsMaxThreeDifferent.class)) {
                BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/four_rows_max_three_different.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                Background background = new Background(backgroundImage);
                commonObj2Button.setBackground(background);
            } else if (commonObjective2.getClass().equals(SixByTwoNew.class)) {
                BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/six_by_two.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                Background background = new Background(backgroundImage);
                commonObj2Button.setBackground(background);
            } else if (commonObjective2.getClass().equals(ThreeColumnsMaxThreeDifferent.class)) {
                BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/three_columns_max_three_different.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                Background background = new Background(backgroundImage);
                commonObj2Button.setBackground(background);
            } else if (commonObjective2.getClass().equals(TotalDifferentColumns.class)) {
                BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/total_different_columns.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                Background background = new Background(backgroundImage);
                commonObj2Button.setBackground(background);
            } else if (commonObjective2.getClass().equals(TotalDifferentRows.class)) {
                BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/total_different_rows.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                Background background = new Background(backgroundImage);
                commonObj2Button.setBackground(background);
            } else if (commonObjective2.getClass().equals(TwoByFour.class)) {
                BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/two_by_four.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                Background background = new Background(backgroundImage);
                commonObj2Button.setBackground(background);
            } else if (commonObjective2.getClass().equals(Stairs.class)) {
                BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/stairs.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                Background background = new Background(backgroundImage);
                commonObj2Button.setBackground(background);
            }
        }
    }

    /**
     * This method sets the image of the current object in hand of the player.
     * @param playerObjInHand are the objects currently in hand of the player.
     */

    public void setPlayerObjInHand(ArrayList<ObjectCard> playerObjInHand){

        if(objInHandGrid != null && playerObjInHand != null) {
            int x = 0;

                for (int y = 0; y < playerObjInHand.size(); y++) {
                    for (Node node : objInHandGrid.getChildren()) {
                        if ((GridPane.getRowIndex(node) == null && GridPane.getColumnIndex(node) == null && y == 0) ||
                                (GridPane.getRowIndex(node) == null && GridPane.getColumnIndex(node) != null && GridPane.getColumnIndex(node) == y)) {
                            Button b = (Button) node;
                            if (playerObjInHand.get(y) != null) {
                                b.setStyle(null);
                                switch (playerObjInHand.get(y).getObjectColour()) {
                                    case GREEN1 -> {
                                        BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/GREEN1XS.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                                        Background background = new Background(backgroundImage);
                                        b.setBackground(background);


                                    }
                                    case GREEN2 -> {
                                        BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/GREEN2XS.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                                        Background background = new Background(backgroundImage);
                                        b.setBackground(background);


                                    }
                                    case GREEN3 -> {
                                        BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/GREEN3XS.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                                        Background background = new Background(backgroundImage);
                                        b.setBackground(background);


                                    }
                                    case BLUE1 -> {
                                        BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/BLUE1XS.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                                        Background background = new Background(backgroundImage);
                                        b.setBackground(background);


                                    }
                                    case BLUE2 -> {
                                        BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/BLUE2XS.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                                        Background background = new Background(backgroundImage);
                                        b.setBackground(background);


                                    }
                                    case BLUE3 -> {
                                        BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/BLUE3XS.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                                        Background background = new Background(backgroundImage);
                                        b.setBackground(background);


                                    }
                                    case WHITE1 -> {
                                        BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/WHITE1XS.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                                        Background background = new Background(backgroundImage);
                                        b.setBackground(background);


                                    }
                                    case WHITE2 -> {
                                        BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/WHITE2XS.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                                        Background background = new Background(backgroundImage);
                                        b.setBackground(background);


                                    }
                                    case WHITE3 -> {
                                        BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/WHITE3XS.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                                        Background background = new Background(backgroundImage);
                                        b.setBackground(background);


                                    }
                                    case LIGHT_BLUE1 -> {
                                        BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/LIGHTBLUE1XS.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                                        Background background = new Background(backgroundImage);
                                        b.setBackground(background);


                                    }
                                    case LIGHT_BLUE2 -> {
                                        BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/LIGHTBLUE2XS.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                                        Background background = new Background(backgroundImage);
                                        b.setBackground(background);


                                    }
                                    case LIGHT_BLUE3 -> {
                                        BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/LIGHTBLUE3XS.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                                        Background background = new Background(backgroundImage);
                                        b.setBackground(background);


                                    }
                                    case PINK1 -> {
                                        BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/PINK1XS.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                                        Background background = new Background(backgroundImage);
                                        b.setBackground(background);


                                    }
                                    case PINK2 -> {
                                        BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/PINK2XS.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                                        Background background = new Background(backgroundImage);
                                        b.setBackground(background);


                                    }
                                    case PINK3 -> {
                                        BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/PINK3XS.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                                        Background background = new Background(backgroundImage);
                                        b.setBackground(background);


                                    }
                                    case YELLOW1 -> {
                                        BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/YELLOW1XS.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                                        Background background = new Background(backgroundImage);
                                        b.setBackground(background);


                                    }
                                    case YELLOW2 -> {
                                        BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/YELLOW2XS.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                                        Background background = new Background(backgroundImage);
                                        b.setBackground(background);


                                    }
                                    case YELLOW3 -> {
                                        BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/YELLOW3XS.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                                        Background background = new Background(backgroundImage);
                                        b.setBackground(background);


                                    }
                                }

                            } else {
                                b.setStyle("-fx-background-color: transparent");
                                //playerObjInHand.remove(y);
                            }
                        }

                    }
                }

                if(playerObjInHand != null) {
                    for (int i = 0; i < playerObjInHand.size(); i++) {
                        if (playerObjInHand.get(i) == null) {
                            playerObjInHand.remove(i);
                        }

                    }
                }


        }
        playerObjInHand1 = playerObjInHand;
    }

    /**
     * This method sets the image of the board tiles.
     * @param gameBoard is the board of the game.
     */
    public void setBoardGrid(Board gameBoard){
        gameBoard1 = gameBoard;

        if(boardGrid != null) {
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {

                for (Node node : boardGrid.getChildren()) {

                    if ((GridPane.getRowIndex(node)== null && x==0 && GridPane.getColumnIndex(node) == null && y== 0)
                        ||(GridPane.getRowIndex(node)!= null && GridPane.getColumnIndex(node) == null &&(GridPane.getRowIndex(node)==  x  && y== 0))
                          ||  (GridPane.getRowIndex(node)== null && GridPane.getColumnIndex(node) != null && x==0 && GridPane.getColumnIndex(node) == y)||
                            (GridPane.getRowIndex(node)!= null && GridPane.getColumnIndex(node) != null &&(GridPane.getRowIndex(node) == x && GridPane.getColumnIndex(node) == y))) {
                        Button b = (Button) node;
                        if(gameBoard!= null && gameBoard.getSpace(x, y).getObject() != null) {
                            b.setStyle(null);
                            b.setVisible(true);
                            switch (gameBoard.getSpace(x, y).getObject().getObjectColour()) {
                                case GREEN1 -> {
                                    BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/GREEN1XS.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                                    Background background = new Background(backgroundImage);
                                    b.setBackground(background);



                                }
                                case GREEN2 -> {
                                    BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/GREEN2XS.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                                    Background background = new Background(backgroundImage);
                                    b.setBackground(background);


                                }
                                case GREEN3 -> {
                                    BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/GREEN3XS.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                                    Background background = new Background(backgroundImage);
                                    b.setBackground(background);


                                }
                                case BLUE1 -> {
                                    BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/BLUE1XS.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                                    Background background = new Background(backgroundImage);
                                    b.setBackground(background);


                                }
                                case BLUE2 -> {
                                    BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/BLUE2XS.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                                    Background background = new Background(backgroundImage);
                                    b.setBackground(background);


                                }
                                case BLUE3 -> {
                                    BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/BLUE3XS.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                                    Background background = new Background(backgroundImage);
                                    b.setBackground(background);


                                }
                                case WHITE1 -> {
                                    BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/WHITE1XS.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                                    Background background = new Background(backgroundImage);
                                    b.setBackground(background);


                                }
                                case WHITE2 -> {
                                    BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/WHITE2XS.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                                    Background background = new Background(backgroundImage);
                                    b.setBackground(background);


                                }
                                case WHITE3 -> {
                                    BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/WHITE3XS.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                                    Background background = new Background(backgroundImage);
                                    b.setBackground(background);


                                }
                                case LIGHT_BLUE1 -> {
                                    BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/LIGHTBLUE1XS.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                                    Background background = new Background(backgroundImage);
                                    b.setBackground(background);


                                }
                                case LIGHT_BLUE2 -> {
                                    BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/LIGHTBLUE2XS.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                                    Background background = new Background(backgroundImage);
                                    b.setBackground(background);


                                }
                                case LIGHT_BLUE3 -> {
                                    BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/LIGHTBLUE3XS.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                                    Background background = new Background(backgroundImage);
                                    b.setBackground(background);


                                }
                                case PINK1 -> {
                                    BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/PINK1XS.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                                    Background background = new Background(backgroundImage);
                                    b.setBackground(background);


                                }
                                case PINK2 -> {
                                    BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/PINK2XS.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                                    Background background = new Background(backgroundImage);
                                    b.setBackground(background);


                                }
                                case PINK3 -> {
                                    BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/PINK3XS.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                                    Background background = new Background(backgroundImage);
                                    b.setBackground(background);


                                }
                                case YELLOW1 -> {
                                    BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/YELLOW1XS.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                                    Background background = new Background(backgroundImage);
                                    b.setBackground(background);


                                }
                                case YELLOW2 -> {
                                    BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/YELLOW2XS.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                                    Background background = new Background(backgroundImage);
                                    b.setBackground(background);


                                }
                                case YELLOW3 -> {
                                    BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/YELLOW3XS.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                                    Background background = new Background(backgroundImage);
                                    b.setBackground(background);


                                }
                            }
                        }else{
                            b.setStyle("-fx-background-color: transparent");
                            b.setVisible(false);
                        }

                    }
                }
            }

            }

        }

    }

    /**
     * This method sets the image of the tiles in the library.
     * @param playerLibrary is the player's library.
     */
    public void setLibraryGrid(Library playerLibrary){
        playerLibrary1 = playerLibrary;
        for (int x = 0; x < 6; x++) {
            for (int y = 0; y < 5; y++) {
                if(libraryGrid != null) {
                    for (Node node : libraryGrid.getChildren()) {
                        if ((GridPane.getRowIndex(node)== null && x==0 && GridPane.getColumnIndex(node) == null && y== 0)
                                ||(GridPane.getRowIndex(node)!= null && GridPane.getColumnIndex(node) == null &&(GridPane.getRowIndex(node)==  x  && y== 0))
                                ||  (GridPane.getRowIndex(node)== null && GridPane.getColumnIndex(node) != null && x==0 && GridPane.getColumnIndex(node) == y)||
                                (GridPane.getRowIndex(node)!= null && GridPane.getColumnIndex(node) != null &&(GridPane.getRowIndex(node) == x && GridPane.getColumnIndex(node) == y)))  {
                            Button b = (Button) node;
                            if(playerLibrary!= null && playerLibrary.getLibrarySpace(x,y) != null) {
                                switch (playerLibrary.getLibrarySpace(x, y).getObject().getObjectColour()) {
                                    case GREEN1 -> {
                                        b.setStyle(null);
                                        BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/GREEN1XS.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                                        Background background = new Background(backgroundImage);
                                        b.setBackground(background);
                                    }
                                    case GREEN2 -> {
                                        b.setStyle(null);
                                        BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/GREEN2XS.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                                        Background background = new Background(backgroundImage);
                                        b.setBackground(background);
                                    }
                                    case GREEN3 -> {
                                        b.setStyle(null);
                                        BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/GREEN3XS.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                                        Background background = new Background(backgroundImage);
                                        b.setBackground(background);
                                    }
                                    case BLUE1 -> {
                                        b.setStyle(null);
                                        BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/BLUE1XS.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                                        Background background = new Background(backgroundImage);
                                        b.setBackground(background);
                                    }
                                    case BLUE2 -> {
                                        b.setStyle(null);
                                        BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/BLUE2XS.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                                        Background background = new Background(backgroundImage);
                                        b.setBackground(background);
                                    }
                                    case BLUE3 -> {
                                        b.setStyle(null);
                                        BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/BLUE3XS.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                                        Background background = new Background(backgroundImage);
                                        b.setBackground(background);
                                    }
                                    case WHITE1 -> {
                                        b.setStyle(null);
                                        BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/WHITE1XS.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                                        Background background = new Background(backgroundImage);
                                        b.setBackground(background);
                                    }
                                    case WHITE2 -> {
                                        b.setStyle(null);
                                        BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/WHITE2XS.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                                        Background background = new Background(backgroundImage);
                                        b.setBackground(background);
                                    }
                                    case WHITE3 -> {
                                        b.setStyle(null);
                                        BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/WHITE3XS.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                                        Background background = new Background(backgroundImage);
                                        b.setBackground(background);
                                    }
                                    case LIGHT_BLUE1 -> {
                                        b.setStyle(null);
                                        BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/LIGHTBLUE1XS.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                                        Background background = new Background(backgroundImage);
                                        b.setBackground(background);
                                    }
                                    case LIGHT_BLUE2 -> {
                                        b.setStyle(null);
                                        BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/LIGHTBLUE2XS.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                                        Background background = new Background(backgroundImage);
                                        b.setBackground(background);
                                    }
                                    case LIGHT_BLUE3 -> {
                                        b.setStyle(null);
                                        BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/LIGHTBLUE3XS.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                                        Background background = new Background(backgroundImage);
                                        b.setBackground(background);
                                    }
                                    case PINK1 -> {
                                        b.setStyle(null);
                                        BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/PINK1XS.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                                        Background background = new Background(backgroundImage);
                                        b.setBackground(background);
                                    }
                                    case PINK2 -> {
                                        b.setStyle(null);
                                        BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/PINK2XS.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                                        Background background = new Background(backgroundImage);
                                        b.setBackground(background);
                                    }
                                    case PINK3 -> {
                                        b.setStyle(null);
                                        BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/PINK3XS.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                                        Background background = new Background(backgroundImage);
                                        b.setBackground(background);
                                    }
                                    case YELLOW1 -> {
                                        b.setStyle(null);
                                        BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/YELLOW1XS.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                                        Background background = new Background(backgroundImage);
                                        b.setBackground(background);
                                    }
                                    case YELLOW2 -> {
                                        b.setStyle(null);
                                        BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/YELLOW2XS.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                                        Background background = new Background(backgroundImage);
                                        b.setBackground(background);
                                    }
                                    case YELLOW3 -> {
                                        b.setStyle(null);
                                        BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/YELLOW3XS.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                                        Background background = new Background(backgroundImage);
                                        b.setBackground(background);
                                    }
                                    case EMPTY -> {
                                        b.setStyle("-fx-background-color: transparent");

                                    }
                                }
                            }else{
                                b.setStyle("-fx-background-color: transparent");

                            }

                        }
                    }
                }

            }

        }

    }

    /**
     * This method responds to a click on the board, checking that there are no more than three picked tiles.
     * @param event when the player picks up a tile from the board.
     */
    private void onBoardSpaceClick(MouseEvent event){
        Button clickedButton = (Button) event.getPickResult().getIntersectedNode();

        System.out.println(GridPane.getRowIndex(clickedButton));
        System.out.println(GridPane.getColumnIndex(clickedButton));
        int row;
        int col;


        if(GridPane.getRowIndex(clickedButton) == null){
             row = 0;
        }else{
             row = GridPane.getRowIndex(clickedButton);
        }

        if(GridPane.getColumnIndex(clickedButton) == null){
             col = 0;
        }else {
             col = GridPane.getColumnIndex(clickedButton);
        }
        if(gameBoard1!= null && gameBoard1.getSpace(row,col).getObject() != null && gameBoard1.getSpace(row,col).getObject().getObjectColour() != ObjectColour.EMPTY) {
            objInHand ++;
            if (objInHand <= 3) {
                playerObjInHand1.add(gameBoard1.getSpace(row, col).getObject());
                clickedButton.setDisable(true);
                coordinatesToSend.add(row);
                coordinatesToSend.add(col);
            }

            if(objInHand == 3){
                blockBoardTiles();
            }
        }




        System.out.println(playerObjInHand1);
        System.out.println(coordinatesToSend);

    }

    /**
     * This method responds to a click on the library.
     * @param event is the user click
     */
    private void onLibrarySpaceClick(MouseEvent event){
        Button clickedButton = (Button) event.getPickResult().getIntersectedNode();
        System.out.println(GridPane.getColumnIndex(clickedButton));
        int col;
        if(GridPane.getColumnIndex(clickedButton) == null){
            col = 0;
        }else{
            col = GridPane.getColumnIndex(clickedButton);
        }

        libColumn = col;
        System.out.println(libColumn);


    }

    /**
     * This method responds to a click on the objects in hand.
     * @param event is the user click
     */
    private void onObjInHandClick(MouseEvent event){
        Button clickedButton = (Button) event.getPickResult().getIntersectedNode();
        System.out.println(GridPane.getColumnIndex(clickedButton));
        int col;
        if(GridPane.getColumnIndex(clickedButton) == null){
            col = 0;
        }else{
            col = GridPane.getColumnIndex(clickedButton);
        }
        if(playerObjInHand1.get(col) != null && playerObjInHand1.get(col).getObjectColour() != ObjectColour.EMPTY){
            orderObjects.add(col);
            clickedButton.setDisable(true);
        }
        System.out.println(orderObjects);

    }


    private void onCommonObjClick(MouseEvent event){
        new Thread(() -> notifyObserver(obs -> obs.onRequestCommonObjectives())).start();
    }
    private void onPersonalObjClick(MouseEvent event){
        new Thread(() -> notifyObserver(obs -> obs.onRequestPersonalObjective())).start();

    }
    private void onShowLibrariesClick(MouseEvent event){
        new Thread(() -> notifyObserver(obs -> obs.onRequestOthersLibrary())).start();
    }

    /**
     * This method responds to a click on the "Confirm" button.
     * The first click confirms the choice of the board tiles.
     * The second time confirms the choice of the order and the column for the library insertion.
     * @param event is the user click
     */
    private void onConfirmButtonClick(MouseEvent event){
        if(playerObjInHand1.size() > 0 && objInHand > 0 ){
            objInHand = 0;
            libColumn = 0;
            orderObjects = new ArrayList<>();
            resetLibraryTiles();
            resetObjInHandTiles();
            blockBoardTiles();
          new Thread(() ->  notifyObserver(obs -> obs.onUpdateBoardMove(coordinatesToSend))).start();
        }else{
            coordinatesToSend = new ArrayList<>();
            orderObjects.add(libColumn);
            System.out.println(orderObjects);
            blockLibraryTiles();
            for(Node node : objInHandGrid.getChildren()){
                Button b = (Button) node;
                b.setStyle("-fx-background-color: transparent");
            }



            new Thread(() -> notifyObserver(obs -> obs.onUpdateLibraryMove(orderObjects))).start();


        }

    }

    /**
     * This method sends a chat message.
     * @param event is the click of the user.
     */
    private void onSendButtonClick(MouseEvent event){
        String message = chatMsg.getText();
        String messageToSend = null;
        if( message.split("_").length > 1) {
            messageToSend = message.split("_")[1];

        }
        String messageToSend1 = messageToSend;
        if(message.split("_")[0].equals("all")|| message.split("_").length ==1){
            if( message.split("_").length == 1){
               String messageToSend2 = message.split("_")[0];
               chatMsg.setText(null);
                new Thread(() -> notifyObserver(obs -> obs.onChatMessage(myPlayer, "all", messageToSend2))).start();
            }else {
                chatMsg.setText(null);
                new Thread(() -> notifyObserver(obs -> obs.onChatMessage(myPlayer, "all", messageToSend1))).start();
            }
        }else {
            String receiver = message.split("_")[0];
            System.out.println(receiver);
            chatMsg.setText(null);
            new Thread(() -> notifyObserver(obs -> obs.onChatMessage(myPlayer,receiver,messageToSend1))).start();
        }
    }

    /**
     * This method updates the chat.
     * @param sender is the sender of the message.
     * @param message is the chat message.
     */
   public void updateChat(String sender, String message){
        if(sender.equals(myPlayer)){
            if(chatLog == null){
                String  temp = "\n"+ "                                                              "+message;
                chatLog = temp;
            }else {
                String temp = chatLog + "\n" + "                                                              " + message;
                chatLog = temp;
            }
        }else{
            if(chatLog == null){
                String temp = "\n"+sender+":  "+message;
                chatLog = temp;
            }else {
                String temp = chatLog + "\n" + sender + ":  " + message;
                chatLog = temp;
            }
        }
        chatBox.setEditable(true);
        chatBox.setText(chatLog);
        chatBox.setEditable(false);
    }

    /**
     * This method saves the nickname of the player.
     * @param player is the {@link model.player.Player player}.
     */
    public void savePlayer(String player){
        myPlayer = player;
    }

    /**
     * This method shows the various errors throughout the game.
     */
    public void resetErrorValues(){
        if(coordinatesToSend.size()>0){
            coordinatesToSend = new ArrayList<>();
            for(Node node : objInHandGrid.getChildren()){
                Button b = (Button) node;
                b.setStyle("-fx-background-color: transparent");
            }
            String scene ="/fxml/Board_Error_Scene.fxml";
            Platform.runLater(() -> SceneController.showPopUP(scene));
        }else{
            objInHand = 0;
            libColumn = 0;
            orderObjects = new ArrayList<>();
            setPlayerObjInHand(playerObjInHand1);
            String scene ="/fxml/Library_Error_Scene.fxml";
            Platform.runLater(() -> SceneController.showPopUP(scene));
        }
    }
    /**
     * This method reset the board tiles when called.
     */
    public void resetBoardTiles(){
        boardFirstTurn = true;
        if(boardGrid != null) {
            for (Node node : boardGrid.getChildren()) {
                Button b = (Button) node;
                if(b.isVisible()) {
                    b.setDisable(false);
                }

            }
        }
    }

    /**
     * This method reset the library tiles when called.
     */
    public void resetLibraryTiles(){
        if(libraryGrid != null) {
            for (Node node : libraryGrid.getChildren()) {
                Button b = (Button) node;
                    b.setDisable(false);

            }
        }
    }

    /**
     * This method reset the current object in hand when called.
     */
    public void resetObjInHandTiles(){

            for (Node node : objInHandGrid.getChildren()) {
                Button b = (Button) node;
                b.setDisable(false);

            }

    }

    /**
     * This method stops from picking up a tile from the board when is not allowed.
     */
    public void blockBoardTiles(){
        if(boardGrid != null) {
            for (Node node : boardGrid.getChildren()) {
                Button b = (Button) node;
                b.setDisable(true);

            }
        }
    }

    /**
     * This method stops from put a tile in the library when is not allowed.
     */
    public void blockLibraryTiles(){
        if(libraryGrid != null) {
            for (Node node : libraryGrid.getChildren()) {
                Button b = (Button) node;
                b.setDisable(true);

            }
        }
    }

    /**
     * This method shows the players' libraries.
     * @param librariesOfPlayers is the {@link HashMap} containing the other players' usernames as keys and libraries as values.
     */

    public void showLibraries(HashMap<String, Library> librariesOfPlayers){
        if(librariesOfPlayers.size() == 1){
            String scene ="/fxml/libraries_Two_Players_Scene.fxml";
            Platform.runLater(() -> SceneController.showTwoLibraries(scene,librariesOfPlayers));
        } else if (librariesOfPlayers.size()==2) {
            String scene ="/fxml/libraries_Three_Players_Scene.fxml";
            Platform.runLater(() -> SceneController.showThreeLibraries(scene,librariesOfPlayers));
        } else if (librariesOfPlayers.size() == 3) {
            String scene ="/fxml/libraries_Four_Players_Scene.fxml";
            Platform.runLater(() -> SceneController.showFourLibraries(scene,librariesOfPlayers));
        }

    }

}
