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
import model.library.Library;
import model.objects.ObjectCard;
import observer.ViewObservable;

import java.util.ArrayList;

public class BoardSceneController extends ViewObservable implements GenericSceneController {

    @FXML
    private GridPane boardGrid;
    @FXML
    private Button boardButton00;
    @FXML
    private GridPane libraryGrid;
    @FXML
    private Button confirmButton;
    private Board gameBoard1;
    private Library playerLibrary1;
    private ArrayList<ObjectCard>playerObjInHand1;
    private ArrayList<Integer> coordinatesToSend = new ArrayList<>();

    public void initialize(){
        boardGrid.addEventHandler(MouseEvent.MOUSE_CLICKED, this::onBoardSpaceClick);
        libraryGrid.addEventHandler(MouseEvent.MOUSE_CLICKED, this::onLibrarySpaceClick);
        confirmButton.addEventHandler(MouseEvent.MOUSE_CLICKED, this::onConfirmButtonClick);
        setBoardGrid(gameBoard1);
        setLibraryGrid(playerLibrary1);
        setPlayerObjInHand(playerObjInHand1);






    }

    public void setPlayerObjInHand(ArrayList<ObjectCard> playerObjInHand){
        playerObjInHand1 = playerObjInHand;
    }

    public void setBoardGrid(Board gameBoard){
        gameBoard1 = gameBoard;

        if(boardGrid != null) {
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {

                for (Node node : boardGrid.getChildren()) {
                    System.out.println("row is"+GridPane.getRowIndex(node));
                    System.out.println("x is"+x);
                    System.out.println("col is"+GridPane.getColumnIndex(node));
                    System.out.println("y is"+y);
                    if ((GridPane.getRowIndex(node)== null && x==0 && GridPane.getColumnIndex(node) == null && y== 0)
                        ||(GridPane.getRowIndex(node)!= null && GridPane.getColumnIndex(node) == null &&(GridPane.getRowIndex(node)==  x  && y== 0))
                          ||  (GridPane.getRowIndex(node)== null && GridPane.getColumnIndex(node) != null && x==0 && GridPane.getColumnIndex(node) == y)||
                            (GridPane.getRowIndex(node)!= null && GridPane.getColumnIndex(node) != null &&(GridPane.getRowIndex(node) == x && GridPane.getColumnIndex(node) == y))) {
                        Button b = (Button) node;
                        if(gameBoard.getSpace(x, y).getObject() != null) {
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
                            if(playerLibrary.getLibrarySpace(x,y) != null) {
                                switch (playerLibrary.getLibrarySpace(x, y).getObject().getObjectColour()) {
                                    case GREEN1 -> {
                                        b.setStyle(null);
                                        BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/GREEN1XS.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                                        Background background = new Background(backgroundImage);
                                        b.setBackground(background);
                                    }
                                    case GREEN2 -> {
                                        b.setStyle(null);
                                        BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/GREEN2XS.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                                        Background background = new Background(backgroundImage);
                                        b.setBackground(background);
                                    }
                                    case GREEN3 -> {
                                        b.setStyle(null);
                                        BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/GREEN3XS.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                                        Background background = new Background(backgroundImage);
                                        b.setBackground(background);
                                    }
                                    case BLUE1 -> {
                                        b.setStyle(null);
                                        BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/BLUE1XS.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                                        Background background = new Background(backgroundImage);
                                        b.setBackground(background);
                                    }
                                    case BLUE2 -> {
                                        b.setStyle(null);
                                        BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/BLUE2XS.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                                        Background background = new Background(backgroundImage);
                                        b.setBackground(background);
                                    }
                                    case BLUE3 -> {
                                        b.setStyle(null);
                                        BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/BLUE3XS.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                                        Background background = new Background(backgroundImage);
                                        b.setBackground(background);
                                    }
                                    case WHITE1 -> {
                                        b.setStyle(null);
                                        BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/WHITE1XS.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                                        Background background = new Background(backgroundImage);
                                        b.setBackground(background);
                                    }
                                    case WHITE2 -> {
                                        b.setStyle(null);
                                        BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/WHITE2XS.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                                        Background background = new Background(backgroundImage);
                                        b.setBackground(background);
                                    }
                                    case WHITE3 -> {
                                        b.setStyle(null);
                                        BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/WHITE3XS.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                                        Background background = new Background(backgroundImage);
                                        b.setBackground(background);
                                    }
                                    case LIGHT_BLUE1 -> {
                                        b.setStyle(null);
                                        BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/LIGHTBLUE1XS.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                                        Background background = new Background(backgroundImage);
                                        b.setBackground(background);
                                    }
                                    case LIGHT_BLUE2 -> {
                                        b.setStyle(null);
                                        BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/LIGHTBLUE2XS.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                                        Background background = new Background(backgroundImage);
                                        b.setBackground(background);
                                    }
                                    case LIGHT_BLUE3 -> {
                                        b.setStyle(null);
                                        BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/LIGHTBLUE3XS.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                                        Background background = new Background(backgroundImage);
                                        b.setBackground(background);
                                    }
                                    case PINK1 -> {
                                        b.setStyle(null);
                                        BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/PINK1XS.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                                        Background background = new Background(backgroundImage);
                                        b.setBackground(background);
                                    }
                                    case PINK2 -> {
                                        b.setStyle(null);
                                        BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/PINK2XS.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                                        Background background = new Background(backgroundImage);
                                        b.setBackground(background);
                                    }
                                    case PINK3 -> {
                                        b.setStyle(null);
                                        BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/PINK3XS.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                                        Background background = new Background(backgroundImage);
                                        b.setBackground(background);
                                    }
                                    case YELLOW1 -> {
                                        b.setStyle(null);
                                        BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/YELLOW1XS.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                                        Background background = new Background(backgroundImage);
                                        b.setBackground(background);
                                    }
                                    case YELLOW2 -> {
                                        b.setStyle(null);
                                        BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/YELLOW2XS.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                                        Background background = new Background(backgroundImage);
                                        b.setBackground(background);
                                    }
                                    case YELLOW3 -> {
                                        b.setStyle(null);
                                        BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/images/YELLOW3XS.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
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
        Node clickedNode = event.getPickResult().getIntersectedNode();
        int row = GridPane.getRowIndex(clickedNode);
        int col = GridPane.getColumnIndex(clickedNode);
        if(gameBoard1.getSpace(row,col).getObject() != null && gameBoard1.getSpace(row,col).getObject().getObjectColour() != ObjectColour.EMPTY) {
            if (playerObjInHand1.size() < 3) {
                playerObjInHand1.add(gameBoard1.getSpace(row, col).getObject());
                coordinatesToSend.add(row);
                coordinatesToSend.add(col);
            }
        }

        System.out.println(playerObjInHand1);
        System.out.println(coordinatesToSend);

    }
    private void onLibrarySpaceClick(MouseEvent event){

    }

    private void onConfirmButtonClick(MouseEvent event){
        if(playerObjInHand1.size() > 0 ){
          new Thread(() ->  notifyObserver(obs -> obs.onUpdateBoardMove(coordinatesToSend))).start();
        }

    }

}
