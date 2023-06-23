package view.gui;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import model.library.Library;
import network.structure.ClientSocket;
import observer.ViewObservable;
import observer.ViewObserver;
import view.gui.scene.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public static void showPopUP(String scene) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(SceneController.class.getResource(scene));

        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            System.out.println("exception");
            throw new RuntimeException(e);

        }

        PopUpSceneController popUpSceneController = loader.getController();
        Scene PopUpScene = new Scene(root);
        popUpSceneController.setScene(PopUpScene);
        popUpSceneController.displayPopUp();
    }

    public static void showTwoLibraries(String scene, HashMap<String, Library> librariesOfPlayers) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(SceneController.class.getResource(scene));
        String name = null;
        Library library = null;

        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            System.out.println("exception");
            throw new RuntimeException(e);

        }

        ShowLibraryTwoPSceneController showLibraryTwoPSceneController = loader.getController();
        for(Map.Entry<String,Library> entry : librariesOfPlayers.entrySet()) {
            name = entry.getKey();
            library = entry.getValue();
        }
        showLibraryTwoPSceneController.SetLibraryGrid(library);
        showLibraryTwoPSceneController.setNicknames(name);
        Scene LibraryScene = new Scene(root);
        showLibraryTwoPSceneController.setScene(LibraryScene);
        showLibraryTwoPSceneController.displayPopUp();
    }

    public static void showThreeLibraries(String scene, HashMap<String, Library> librariesOfPlayers) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(SceneController.class.getResource(scene));
        String name = null;
        Library library = null;
        int count = 0;

        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            System.out.println("exception");
            throw new RuntimeException(e);

        }

        ShowLibraryThreePSceneController showLibraryThreePSceneController = loader.getController();
        for(Map.Entry<String,Library> entry : librariesOfPlayers.entrySet()) {
            count++;
            name = entry.getKey();
            library = entry.getValue();
            if(count==1){
                showLibraryThreePSceneController.setNickname1(name);
                showLibraryThreePSceneController.SetLibraryGrid1(library);
            } else if (count==2) {
                showLibraryThreePSceneController.setNickname2(name);
                showLibraryThreePSceneController.SetLibraryGrid2(library);
            }
        }


        Scene LibraryScene = new Scene(root);
        showLibraryThreePSceneController.setScene(LibraryScene);
        showLibraryThreePSceneController.displayPopUp();
    }


    public static void showFourLibraries(String scene, HashMap<String, Library> librariesOfPlayers) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(SceneController.class.getResource(scene));
        String name = null;
        Library library = null;
        int count = 0;

        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            System.out.println("exception");
            throw new RuntimeException(e);

        }

        ShowLibraryFourPSceneController showLibraryFourPSceneController = loader.getController();
        for(Map.Entry<String,Library> entry : librariesOfPlayers.entrySet()) {
            count++;
            name = entry.getKey();
            library = entry.getValue();
            if(count==1){
                showLibraryFourPSceneController.setNickname1(name);
                showLibraryFourPSceneController.SetLibraryGrid1(library);
            } else if (count==2) {
                showLibraryFourPSceneController.setNickname2(name);
                showLibraryFourPSceneController.SetLibraryGrid2(library);
            }else if (count==3) {
                showLibraryFourPSceneController.setNickname3(name);
                showLibraryFourPSceneController.SetLibraryGrid3(library);
            }
        }


        Scene LibraryScene = new Scene(root);
        showLibraryFourPSceneController.setScene(LibraryScene);
        showLibraryFourPSceneController.displayPopUp();
    }

}
