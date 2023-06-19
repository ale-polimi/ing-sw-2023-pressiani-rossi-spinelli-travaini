package view.gui.scene;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import model.board.Board;
import model.library.Library;
import observer.ViewObservable;

public class BoardSceneController extends ViewObservable implements GenericSceneController {

    @FXML
    private GridPane boardGrid;
    @FXML
    private Button boardButton00;
    @FXML
    private GridPane libraryGrid;
    @FXML
    private Button confirmButton;

    public void initialize(){
        boardGrid.addEventHandler(MouseEvent.MOUSE_CLICKED, this::onBoardSpaceClick);
        libraryGrid.addEventHandler(MouseEvent.MOUSE_CLICKED, this::onLibrarySpaceClick);
        confirmButton.addEventHandler(MouseEvent.MOUSE_CLICKED, this::onConfirmButtonClick);




    }

    public void setBoardGrid(Board gameBoard){
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                for(Node node : boardGrid.getChildren()){
                   if(GridPane.getRowIndex(node)==x && GridPane.getColumnIndex(node)==y){
                       Button b = (Button) node;
                       switch (gameBoard.getSpace(x,y).getObject().getObjectColour()){
                           case GREEN1 -> {
                               BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/GREEN1.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                               Background background = new Background(backgroundImage);
                               b.setBackground(background);
                           }
                           case GREEN2 -> {
                               BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/GREEN2.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                               Background background = new Background(backgroundImage);
                               b.setBackground(background);
                           }
                           case GREEN3 -> {
                               BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/GREEN3.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                               Background background = new Background(backgroundImage);
                               b.setBackground(background);
                           }
                           case BLUE1 -> {
                               BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/BLUE1.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                               Background background = new Background(backgroundImage);
                               b.setBackground(background);
                           }
                           case BLUE2 -> {
                               BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/BLUE2.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                               Background background = new Background(backgroundImage);
                               b.setBackground(background);
                           }
                           case BLUE3 -> {
                               BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/BLUE3.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                               Background background = new Background(backgroundImage);
                               b.setBackground(background);
                           }
                           case WHITE1 -> {
                               BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/WHITE1.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                               Background background = new Background(backgroundImage);
                               b.setBackground(background);
                           }
                           case WHITE2 -> {
                               BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/WHITE2.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                               Background background = new Background(backgroundImage);
                               b.setBackground(background);
                           }
                           case WHITE3 -> {
                               BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/WHITE3.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                               Background background = new Background(backgroundImage);
                               b.setBackground(background);
                           }
                           case LIGHT_BLUE1 -> {
                               BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/LIGHTBLUE1.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                               Background background = new Background(backgroundImage);
                               b.setBackground(background);
                           }
                           case LIGHT_BLUE2 -> {
                               BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/LIGHTBLUE2.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                               Background background = new Background(backgroundImage);
                               b.setBackground(background);
                           }
                           case LIGHT_BLUE3 -> {
                               BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/LIGHTBLUE3.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                               Background background = new Background(backgroundImage);
                               b.setBackground(background);
                           }
                           case PINK1 -> {
                               BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/PINK1.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                               Background background = new Background(backgroundImage);
                               b.setBackground(background);
                           }
                           case PINK2 -> {
                               BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/PINK2.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                               Background background = new Background(backgroundImage);
                               b.setBackground(background);
                           }
                           case PINK3 -> {
                               BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/PINK3.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                               Background background = new Background(backgroundImage);
                               b.setBackground(background);
                           }
                           case YELLOW1 -> {
                               BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/YELLOW1.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                               Background background = new Background(backgroundImage);
                               b.setBackground(background);
                           }
                           case YELLOW2 -> {
                               BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/YELLOW2.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                               Background background = new Background(backgroundImage);
                               b.setBackground(background);
                           }
                           case YELLOW3 -> {
                               BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/YELLOW3.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                               Background background = new Background(backgroundImage);
                               b.setBackground(background);
                           }
                       }

                   }
                }

            }

        }

    }

    public void setLibraryGrid(Library playerLibrary){
        for (int x = 0; x < 6; x++) {
            for (int y = 0; y < 5; y++) {
                for(Node node : libraryGrid.getChildren()){
                    if(libraryGrid.getRowIndex(node)==x && libraryGrid.getColumnIndex(node)==y){
                        Button b = (Button) node;
                        switch (playerLibrary.getLibrarySpace(x,y).getObject().getObjectColour()){
                            case GREEN1 -> {
                                BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/GREEN1.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                                Background background = new Background(backgroundImage);
                                b.setBackground(background);
                            }
                            case GREEN2 -> {
                                BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/GREEN2.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                                Background background = new Background(backgroundImage);
                                b.setBackground(background);
                            }
                            case GREEN3 -> {
                                BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/GREEN3.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                                Background background = new Background(backgroundImage);
                                b.setBackground(background);
                            }
                            case BLUE1 -> {
                                BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/BLUE1.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                                Background background = new Background(backgroundImage);
                                b.setBackground(background);
                            }
                            case BLUE2 -> {
                                BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/BLUE2.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                                Background background = new Background(backgroundImage);
                                b.setBackground(background);
                            }
                            case BLUE3 -> {
                                BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/BLUE3.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                                Background background = new Background(backgroundImage);
                                b.setBackground(background);
                            }
                            case WHITE1 -> {
                                BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/WHITE1.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                                Background background = new Background(backgroundImage);
                                b.setBackground(background);
                            }
                            case WHITE2 -> {
                                BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/WHITE2.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                                Background background = new Background(backgroundImage);
                                b.setBackground(background);
                            }
                            case WHITE3 -> {
                                BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/WHITE3.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                                Background background = new Background(backgroundImage);
                                b.setBackground(background);
                            }
                            case LIGHT_BLUE1 -> {
                                BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/LIGHTBLUE1.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                                Background background = new Background(backgroundImage);
                                b.setBackground(background);
                            }
                            case LIGHT_BLUE2 -> {
                                BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/LIGHTBLUE2.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                                Background background = new Background(backgroundImage);
                                b.setBackground(background);
                            }
                            case LIGHT_BLUE3 -> {
                                BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/LIGHTBLUE3.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                                Background background = new Background(backgroundImage);
                                b.setBackground(background);
                            }
                            case PINK1 -> {
                                BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/PINK1.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                                Background background = new Background(backgroundImage);
                                b.setBackground(background);
                            }
                            case PINK2 -> {
                                BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/PINK2.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                                Background background = new Background(backgroundImage);
                                b.setBackground(background);
                            }
                            case PINK3 -> {
                                BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/PINK3.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                                Background background = new Background(backgroundImage);
                                b.setBackground(background);
                            }
                            case YELLOW1 -> {
                                BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/YELLOW1.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                                Background background = new Background(backgroundImage);
                                b.setBackground(background);
                            }
                            case YELLOW2 -> {
                                BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/YELLOW2.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                                Background background = new Background(backgroundImage);
                                b.setBackground(background);
                            }
                            case YELLOW3 -> {
                                BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/YELLOW3.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                                Background background = new Background(backgroundImage);
                                b.setBackground(background);
                            }
                        }

                    }
                }

            }

        }

    }
    
    private void onBoardSpaceClick(MouseEvent event){
        Node clickedNode = event.getPickResult().getIntersectedNode();
        int row = GridPane.getRowIndex(clickedNode);
        int column = GridPane.getColumnIndex(clickedNode);

    }
    private void onLibrarySpaceClick(MouseEvent event){

    }

    private void onConfirmButtonClick(MouseEvent event){

    }

}
