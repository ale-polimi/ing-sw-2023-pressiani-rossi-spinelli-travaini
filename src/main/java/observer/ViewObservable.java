package observer;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.function.Consumer;

/**
 * Observable class used only for the {@link view.View view}.
 */
public abstract class ViewObservable {
    protected List<ViewObserver> observers = new ArrayList<>();

    /**
     * This method will add an observer to the list of view observers.
     * @param viewObserver is the new view observer.
     */
    public void addObserver(ViewObserver viewObserver){
        observers.add(viewObserver);
    }

    /**
     * This method will remove an observer from the list of view observers.
     * @param viewObserver is the view observer that has to be removed.
     */
    public void removeObserver(ViewObserver viewObserver){
        observers.remove(viewObserver);
    }

    /**
     * This method will add more observers to the list of view observers.
     * @param viewObservers is a list of view observers that will be added.
     */
    public void addAllObservers(List<ViewObserver> viewObservers){
        observers.addAll(viewObservers);
    }

    /**
     * This method will remove a list of observers from the view observers.
     * @param viewObservers is the list of observers to remove.
     */
    public void removeAllObservers(List<ViewObserver> viewObservers){
        observers.removeAll(viewObservers);
    }

    /**
     * This method will notify a specific view observer that will accept the provided function.
     * @param lambda is the lambda function that the view observer has to satisfy.
     */
    protected void notifyObserver(Consumer<ViewObserver> lambda) {
        for (ViewObserver observer : observers) {
            lambda.accept(observer);
        }
    }
}
