package view.gui;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import controller.ClientController;
import view.gui.scene.StartSceneController;

import java.io.IOException;

public class JavaFXGUI extends Application {

    public static boolean isSocket;
    @Override
    public void start(Stage stage) throws IOException {


        Gui view = new Gui();
        System.out.println(isSocket);
        ClientController clientController = new ClientController(view, isSocket);
        view.addObserver(clientController);

        FXMLLoader loader = new FXMLLoader();
        //FileInputStream fileInputStream = new FileInputStream(new File("src/resources/fxml/Start.fxml"));
        loader.setLocation(getClass().getResource("/fxml/Start.fxml"));
        Parent root = loader.load();


        StartSceneController controller = loader.getController();
        controller.addObserver(clientController);

        Scene scene = new Scene(root,700,500);
        stage.setScene(scene);
        stage.setFullScreen(true);
        //stage.setFullScreenExitHint("");
        //stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        stage.show();









    }

    public static void main(String[] args) {
        System.out.println(args[1]);
        launch(args);




    }

    public static void setIsSocket(boolean isSocketcontroller){
        isSocket = isSocketcontroller;
    }
}
