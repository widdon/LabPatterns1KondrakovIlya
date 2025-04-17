package ControlPanel.factories;

import ControlPanel.elements.Lamp;
import ControlPanel.elements.LampColor;
import ControlPanel.elements.PanelElement;
import java.util.Random;

// Фабрика ламп
public class LampFactory implements ElementFactory {
    private static final Random random = new Random();

    @Override
    public PanelElement createElement(int x, int y) {
        LampColor color = LampColor.values()[random.nextInt(LampColor.values().length)];
        return new Lamp(x, y, color);
    }
}
