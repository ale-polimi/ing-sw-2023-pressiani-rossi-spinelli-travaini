package observer;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.function.Consumer;

/**
 * Observable class used only for the {@link view.View view}.
 */
public abstract class ViewObservable {
    private List<ViewObserver> observers = new ArrayList<>();

    public void addObserver(ViewObserver viewObserver){
        observers.add(viewObserver);
    }

    public void removeObserver(ViewObserver viewObserver){
        observers.remove(viewObserver);
    }

    public void addAllObservers(List<ViewObserver> viewObservers){
        observers.addAll(viewObservers);
    }

    public void removeAllObservers(List<ViewObserver> viewObservers){
        observers.removeAll(viewObservers);
    }

    protected void notifyObserver(Consumer<ViewObserver> lambda) {
        for (ViewObserver observer : observers) {
            lambda.accept(observer);
        }
    }
}
