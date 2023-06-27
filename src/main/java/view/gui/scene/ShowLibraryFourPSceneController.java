package view.gui.scene;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.library.Library;
import view.gui.SceneController;

/**
 * This class represents the Show Library Scene Controller for four players.
 */
public class ShowLibraryFourPSceneController implements GenericSceneController{

    private final Stage stage;
    private double xOffset;
    private double yOffset;
    private Library playerLibrary1;
    private Library playerLibrary2;
    private Library playerLibrary3;
    private String nickname1;
    private String nickname2;
    private String nickname3;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private Button closeButton;
    @FXML
    private GridPane libraryGrid1;
    @FXML
    private GridPane libraryGrid2;
    @FXML
    private GridPane libraryGrid3;

    @FXML
    private Label nickname1Label;
    @FXML
    private Label nickname2Label;
    @FXML
    private Label nickname3Label;


    /**
     *This is the constructor
     */
    public ShowLibraryFourPSceneController(){
        stage = new Stage();
        stage.initOwner(SceneController.getActiveScene().getWindow());
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setAlwaysOnTop(true);
        xOffset = 0;
        yOffset = 0;
    }

    /**
     * This method initialises the scene.
     */
    @FXML
    public void initialize(){
        rootPane.addEventHandler(MouseEvent.MOUSE_PRESSED, this::onRootPaneMousePressed);
        rootPane.addEventHandler(MouseEvent.MOUSE_DRAGGED, this::onRootPaneMouseDragged);
        closeButton.addEventHandler(MouseEvent.MOUSE_CLICKED, this:: onCloseButtonClick);
        SetLibraryGrid1(playerLibrary1);
        SetLibraryGrid2(playerLibrary2);
        System.out.println("init");
    }


    /**
     * This method sets coordinates of the screen.
     * @param event is the click of the user.
     */
    private void onRootPaneMousePressed(MouseEvent event) {
        xOffset = stage.getX() - event.getScreenX();
        yOffset = stage.getY() - event.getScreenY();
    }

    /**
     * This method allows to move the windows on the screen.
     * @param event is the click of the user.
     */
    private void onRootPaneMouseDragged(MouseEvent event) {
        stage.setX(event.getScreenX() + xOffset);
        stage.setY(event.getScreenY() + yOffset);
    }

    /**
     * This method closes the stage.
     * @param event is the click of the user.
     */

    private void onCloseButtonClick(MouseEvent event){
        stage.close();
    }

    /**
     * This method displays the pop-up window.
     */

    public void displayPopUp(){
        stage.showAndWait();
    }

    /**
     * This method sets the scene.
     * @param scene is the scene that  has to be set.
     */
    public void setScene(Scene scene){
        stage.setScene(scene);
    }

    /**
     * This method sets the first other player's nickname.
     * @param nicknames is the player's nickname.
     */
    public void setNickname1(String nicknames){
        nickname1 = nicknames;
        System.out.println("setter");
        nickname1Label.setText(nickname1);
    }

    /**
     * This method sets the second other player's nickname.
     * @param nicknames is the player's nickname.
     */
    public void setNickname2(String nicknames){
        nickname2 = nicknames;
        System.out.println("setter");
        nickname2Label.setText(nickname2);
    }

    /**
     * This method sets the third other player's nickname.
     * @param nicknames is the player's nickname.
     */
    public void setNickname3(String nicknames){
        nickname3 = nicknames;
        System.out.println("setter");
        nickname3Label.setText(nickname3);
    }

    /**
     * This method sets the other first player's library.
     * @param playerLibrary is the player's library.
     */
    public void SetLibraryGrid1(Library playerLibrary){
        playerLibrary1 = playerLibrary;
        for (int x = 0; x < 6; x++) {
            for (int y = 0; y < 5; y++) {
                if(libraryGrid1 != null) {
                    for (Node node : libraryGrid1.getChildren()) {
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
     *This method sets the other second player's library.
     * @param playerLibrary is the player's library.
     */
    public void SetLibraryGrid2(Library playerLibrary){
        playerLibrary2 = playerLibrary;
        for (int x = 0; x < 6; x++) {
            for (int y = 0; y < 5; y++) {
                if(libraryGrid2 != null) {
                    for (Node node : libraryGrid2.getChildren()) {
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
     * This method sets the other third player's library.
     * @param playerLibrary is the player's library.
     */

    public void SetLibraryGrid3(Library playerLibrary){
        playerLibrary3 = playerLibrary;
        for (int x = 0; x < 6; x++) {
            for (int y = 0; y < 5; y++) {
                if(libraryGrid3 != null) {
                    for (Node node : libraryGrid3.getChildren()) {
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
}
