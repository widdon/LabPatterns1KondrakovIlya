package ControlPanel.elements;

import java.util.ArrayList;
import java.util.List;

public class Button extends PanelElement {
    private boolean pressed;
    private List<LampObserver> observers = new ArrayList<>();

    public Button(int x, int y) {
        super(x, y);
    }

    public void addObserver(LampObserver observer) {
        observers.add(observer);
    }

    public void press() {
        pressed = true;
        notifyObservers(true);
    }

    private void notifyObservers(boolean state) {
        for (LampObserver observer : observers) {
            observer.update(state);
        }
    }

    @Override
    public String getDisplay() {
        return pressed ? "o" : "O";
    }
}