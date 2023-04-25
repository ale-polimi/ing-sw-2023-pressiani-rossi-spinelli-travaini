package model.player;

import model.library.Library;
import model.objects.ObjectCard;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class PlayerView implements PropertyChangeListener {
    private Library playerLibrary;
    private ArrayList<ObjectCard> objectsInHand;
    private boolean isFirstPlayer;

    Library getLibrary(){
        return playerLibrary;
    }

    ArrayList<ObjectCard> getObjectsInHand(){
        return objectsInHand;
    }

    boolean isFirstPlayer(){
        return isFirstPlayer;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch(evt.getPropertyName()) {
            case "SET_AS_FIRST":
                isFirstPlayer = (boolean) evt.getNewValue();
                break;
            case "OBJECTS_IN_HAND":
                objectsInHand = (ArrayList<ObjectCard>) evt.getNewValue();
                break;
            case "LIBRARY_CHANGED":
                playerLibrary = (Library) evt.getNewValue();
            default:
                throw new IllegalStateException("Unexpected value: " + evt.getPropertyName());
        }
    }
}
