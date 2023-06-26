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


    /**
     * @return the current scene
     */
    public static Scene getActiveScene(){
        return activeScene;
    }

    /**
     * @return the current generic scene controller
     */
    public static GenericSceneController getActiveController(){
        return activeController;
    }


    /**
     * when called change the scene
     * @param observerList is the list of the observers
     * @param scene is the scene that replace the current scene
     * @param fxml
     * @return the controller
     * @param <P>
     */
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

    /**
     * when called change the scene
     * @param observerList is the list of the observers
     * @param event is the event that caused the scene change
     * @param fxml
     * @return
     * @param <P>
     */
    public static <P> P changeRootPane(List<ViewObserver> observerList, Event event, String fxml){
        Scene scene = ((Node) event.getSource()).getScene();
        return changeRootPane(observerList,scene,fxml);
    }

    /**
     * when called change the scene
     * @param observerList is the list of the observers
     * @param
     * @return
     * @param <P>
     */
    public static <P> P changeRootPane(List<ViewObserver> observerList, String fxml){
        return changeRootPane(observerList,activeScene,fxml);
    }

    /**
     * when called change the scene
     * @param controller is the gui controller
     * @param scene is the scene that replace the current scene
     * @param fxml
     */

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



    /**
     * when called change the scene
     * @param controller is the gui controller
     * @param fxml
     */

    public static void changeRootPane(GenericSceneController controller, String fxml){
        changeRootPane(controller, activeScene,fxml);
    }

    /**
     * when called gives out a popup window
     * @param scene is the scene that is showed by the popup window
     */

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

    /**
     * when there are 2 players, this shows the others players library
     * @param scene is the scene that has to be displayed
     * @param librariesOfPlayers are the libraries of the other players
     */
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

    /**
     * when there are 3 players, this shows the others players library
     * @param scene is the scene that has to be displayed
     * @param librariesOfPlayers are the libraries of the other players
     */
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

    /**
     * when there are 4 players, this shows the others players library
     * @param scene is the scene that has to be displayed
     * @param librariesOfPlayers are the libraries of the other players
     */

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
