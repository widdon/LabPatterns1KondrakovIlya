package ControlPanel;

import ControlPanel.builders.ControlPanelBuilder;
import ControlPanel.elements.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите размеры панели (ширина высота):");
        int width = scanner.nextInt();
        int height = scanner.nextInt();

        // Создание панели
        PanelElement[][] panel = new ControlPanelBuilder()
                .setSize(width, height)
                .build();

        // Вывод первоначального состояния
        System.out.println("\nСгенерирована панель управления:");
        printPanelGrid(panel);

        // Основной цикл управления
        while (true) {
            System.out.println("\nВведите координаты кнопки (x y) или -1 для выхода:");
            int x = scanner.nextInt();
            if (x == -1) break;

            int y = scanner.nextInt();
            handleButtonPress(panel, x, y);
            printPanelGrid(panel);
        }

        scanner.close();
    }


    private static void printPanelGrid(PanelElement[][] panel) {
        // Верхняя граница
        printHorizontalBorder(panel[0].length);

        for (PanelElement[] row : panel) {
            System.out.print("|");
            for (PanelElement element : row) {
                System.out.printf(" %2s |", element.getDisplay());
            }
            System.out.println();
            printHorizontalBorder(row.length);
        }
    }

    private static void printHorizontalBorder(int length) {
        System.out.print("+");
        for (int i = 0; i < length; i++) {
            System.out.print("----+");
        }
        System.out.println();
    }

    private static void handleButtonPress(PanelElement[][] panel, int x, int y) {
        if (!isValidCoordinate(panel, x, y)) {
            System.out.println("Неверные координаты!");
            return;
        }

        PanelElement element = panel[y][x];
        if (element instanceof Button) {
            ((Button) element).press();
        } else {
            System.out.println("Здесь нет кнопки!");
        }
    }

    private static boolean isValidCoordinate(PanelElement[][] panel, int x, int y) {
        return x >= 0 && x < panel[0].length && y >= 0 && y < panel.length;
    }
}