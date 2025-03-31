package ui;

import ui.menus.Menu;

public class ConsoleOutput {
    public static void println(String message) {
        System.out.println(message);
    }

    public static void print(String message) {
        System.out.print(message);
    }


    public static void showMenu(Menu menu) {
        menu.showMenu();
    }

    public static void printInvalidInput(String message) {
        System.err.println("Invalid input, please try again " + message);
    }

    public static void printSuccess(String message) {
        System.out.println("Success " + message);
    }
}
