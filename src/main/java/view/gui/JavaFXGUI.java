package view.gui;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import controller.ClientController;
import view.gui.scene.StartSceneController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class JavaFXGUI extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        Gui view = new Gui();
        ClientController clientController = new ClientController(view, true);
        view.addObserver(clientController);

        FXMLLoader loader = new FXMLLoader();
        FileInputStream fileInputStream = new FileInputStream(new File("src/resources/fxml/Start.fxml"));
        //loader.setLocation(getClass().getResource("src/resources/fxml/Start.fxml"));
        Parent root = loader.load(fileInputStream);


        StartSceneController controller = loader.getController();
        controller.addObserver(clientController);

        Scene scene = new Scene(root,500,500);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
