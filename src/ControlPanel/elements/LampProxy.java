package ControlPanel.elements;

public class LampProxy extends PanelElement implements LampObserver {
    private final Lamp realLamp;

    public LampProxy(Lamp realLamp) {
        super(realLamp.getX(), realLamp.getY());
        this.realLamp = realLamp;
    }

    @Override
    public void update(boolean isActive) {
        System.out.printf("[Прокси] Доступ к лампе (%d,%d)%n", getX(), getY());
        realLamp.update(isActive);
    }

    @Override
    public String getDisplay() {
        return realLamp.getDisplay();
    }

    public Lamp getRealLamp() {
        return realLamp;
    }
}