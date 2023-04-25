package model.board;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class BoardView implements PropertyChangeListener {
    private BoardSpace[][] board;
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("BOARD_CHANGED")) {
            this.board = (BoardSpace[][]) evt.getNewValue();
        } else {
            throw new IllegalStateException("Unexpected value: " + evt.getPropertyName());
        }
    }
}
