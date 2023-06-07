package view.gui;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import observer.ViewObservable;
import observer.ViewObserver;
import view.gui.scene.GenericSceneController;

import java.io.IOException;
import java.util.List;

public class SceneController extends ViewObservable {

    private static Scene activeScene;
    private static GenericSceneController activeController;


    public static Scene getActiveScene(){
        return activeScene;
    }

    public static GenericSceneController getActiveController(){
        return activeController;
    }


    public static <P> P changeRootPane(List<ViewObserver> observerList, Scene scene, String fxml){
      P controller = null;

      try{
          FXMLLoader loader = new FXMLLoader(SceneController.class.getResource("/fxml/" + fxml));
          Parent root = loader.load();
          controller = loader.getController();
          ((ViewObservable) controller).addAllObservers(observerList);

          activeController = (GenericSceneController) controller;
          activeScene = scene;
          activeScene.setRoot(root);
      } catch (IOException e) {
          throw new RuntimeException(e);
      }
      return controller;
    }


    public static <P> P changeRootPane(List<ViewObserver> observerList, Event event, String fxml){
        Scene scene = ((Node) event.getSource()).getScene();
        return changeRootPane(observerList,scene,fxml);
    }

    public static <P> P changeRootPane(List<ViewObserver> observerList, String fxml){
        return changeRootPane(observerList,activeScene,fxml);
    }


    public static void changeRootPane(GenericSceneController controller, Scene scene, String fxml){
        try{
            FXMLLoader loader = new FXMLLoader(SceneController.class.getResource("/fxml/" + fxml));

            loader.setController(controller);
            activeController = controller;
            Parent root = loader.load();

            activeScene = scene;
            activeScene.setRoot(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void changeRootPane(GenericSceneController controller, Event event, String fxml){
        Scene scene = ((Node) event.getSource()).getScene();
        changeRootPane(controller,scene,fxml);
    }


    public static void changeRootPane(GenericSceneController controller, String fxml){
        changeRootPane(controller, activeScene,fxml);
    }

}
