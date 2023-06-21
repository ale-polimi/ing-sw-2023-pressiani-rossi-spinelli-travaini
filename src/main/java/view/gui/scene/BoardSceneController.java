package view.gui.scene;

import enumerations.ObjectColour;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import model.board.Board;
import model.commonobjective.*;
import model.library.Library;
import model.objects.ObjectCard;
import observer.ViewObservable;

import java.util.ArrayList;

public class BoardSceneController extends ViewObservable implements GenericSceneController {

    @FXML
    private GridPane boardGrid;
    @FXML
    private GridPane objInHandGrid;
    @FXML
    private Button boardButton00;
    @FXML
    private GridPane libraryGrid;
    @FXML
    private Button confirmButton;
    @FXML
    private Button commonObj1Button;
    @FXML
    private Button commonObj2Button;
    private Board gameBoard1;
    private Library playerLibrary1;
    private ArrayList<ObjectCard>playerObjInHand1;
    private ArrayList<Integer> coordinatesToSend = new ArrayList<>();
    private ArrayList<Integer>orderObjects = new ArrayList<>();
    private int libColumn;
    private CommonObjective commonObjective11;
    private CommonObjective commonObjective21;

    private int objInHand = 0;

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
        commonObj1Button.addEventHandler(MouseEvent.MOUSE_CLICKED, this::onCommonObjClick);
        commonObj2Button.addEventHandler(MouseEvent.MOUSE_CLICKED, this::onCommonObjClick);
        setBoardGrid(gameBoard1);
        setLibraryGrid(playerLibrary1);
        setPlayerObjInHand(playerObjInHand1);
        setCommonObj1Button(commonObjective11);
        setCommonObj2Button(commonObjective21);






    }

    public void setCommonObj1Button(CommonObjective commonObjective1){
       if( commonObjective1 != null){
        commonObjective11 = commonObjective1;
       }
        if(commonObj1Button != null && commonObjective1 != null){
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
            }
        }
    }
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
            }
        }
    }



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
                        }

                    }
                }
            }

            }

        }

    }

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
        if(gameBoard1.getSpace(row,col).getObject() != null && gameBoard1.getSpace(row,col).getObject().getObjectColour() != ObjectColour.EMPTY) {
            objInHand ++;
            if (objInHand <= 3) {
                playerObjInHand1.add(gameBoard1.getSpace(row, col).getObject());
                coordinatesToSend.add(row);
                coordinatesToSend.add(col);
            }
        }

        System.out.println(playerObjInHand1);
        System.out.println(coordinatesToSend);

    }
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
        }
        System.out.println(orderObjects);

    }

    private void onCommonObjClick(MouseEvent event){
        new Thread(() -> notifyObserver(obs -> obs.onRequestCommonObjectives()));
        setCommonObj1Button(commonObjective11);
        setCommonObj2Button(commonObjective21);
    }

    private void onConfirmButtonClick(MouseEvent event){
        if(playerObjInHand1.size() > 0 && objInHand > 0 ){
            objInHand = 0;
            libColumn = 0;
            orderObjects = new ArrayList<>();
          new Thread(() ->  notifyObserver(obs -> obs.onUpdateBoardMove(coordinatesToSend))).start();
        }else{
            coordinatesToSend = new ArrayList<>();
            orderObjects.add(libColumn);
            System.out.println(orderObjects);
            for(Node node : objInHandGrid.getChildren()){
                Button b = (Button) node;
                b.setStyle("-fx-background-color: transparent");
            }



            new Thread(() -> notifyObserver(obs -> obs.onUpdateLibraryMove(orderObjects))).start();


        }

    }

}
