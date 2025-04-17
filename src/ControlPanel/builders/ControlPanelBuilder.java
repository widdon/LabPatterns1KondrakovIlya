package ControlPanel.builders;

import ControlPanel.elements.*;
import ControlPanel.factories.*;
import java.util.Random;

public class ControlPanelBuilder {
    private int width;
    private int height;
    private final Random random = new Random();
    private final ElementFactory buttonFactory = new ButtonFactory();
    private final ElementFactory lampFactory = new LampFactory();

    public ControlPanelBuilder setSize(int width, int height) {
        this.width = width;
        this.height = height;
        return this;
    }

    public PanelElement[][] build() {
        PanelElement[][] panel = new PanelElement[height][width];

        // 1. Создаем элементы
        createElements(panel);

        // 2. Настраиваем связи
        setupConnections(panel);

        return panel;
    }

    private void createElements(PanelElement[][] panel) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (random.nextBoolean()) {
                    // Создаем кнопку
                    panel[y][x] = buttonFactory.createElement(x, y);
                } else {
                    // Создаем лампу через прокси
                    Lamp lamp = (Lamp) lampFactory.createElement(x, y);
                    panel[y][x] = new LampProxy(lamp);
                }
            }
        }
    }

    private void setupConnections(PanelElement[][] panel) {
        PanelIterator iterator = new PanelIterator(panel);
        while (iterator.hasNext()) {
            PanelElement element = iterator.next();
            if (element instanceof Button) {
                setupButtonObservers((Button) element, panel);
            }
        }
    }

    private void setupButtonObservers(Button button, PanelElement[][] panel) {
        int lampsToConnect = random.nextInt(3) + 1;

        for (int i = 0; i < lampsToConnect; i++) {
            int x, y;
            do {
                x = random.nextInt(width);
                y = random.nextInt(height);
            } while (!(panel[y][x] instanceof LampProxy));

            button.addObserver((LampProxy) panel[y][x]);
        }
    }
}