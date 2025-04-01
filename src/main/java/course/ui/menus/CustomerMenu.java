package course.ui.menus;

import course.ui.ConsoleOutput;

public class CustomerMenu extends Menu {

    private static final String CUSTOMER_MENU_CONTEXT = "Browse available spaces(1)/ Make reservation(2)/ Cancel my reservations(3)/ View my reservation(4)/ Update booking(5)/ Exit(6): ";

    @Override
    public void showMenu() {
        ConsoleOutput.print(CUSTOMER_MENU_CONTEXT);
    }
}
