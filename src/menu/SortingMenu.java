package menu;
import management.OrderManager;
import utils.ConsoleUtils;
import static utils.ConsoleUtils.*;


/**
 * <p>ICS4U</p>
 * <p>AY Jackson SS</p>
 * <p>The SortingMenu class inherits from the abstract {@link Menu} class and thus
 * represents a sub menu for sorting the orders in the database.
 * It provides options to sort orders by ascending ID number, and sort them by status and soonest completion date.</p>
 * <p>Concept: Inheritance</p>
 * @author Meherzad Antia
 * @author Stanley Gu
 * @author Daniel Li
 * @since 2024-1-20
 */

public class SortingMenu extends Menu {

    /**
     * Constructs a SortingMenu with the given OrderManager.
     *
     * @param orderManager The OrderManager associated with this menu.
     */
    public SortingMenu(OrderManager orderManager) {
        super(orderManager);
    }

    /**
     * Prints the available options for sorting orders. Concept: Polymorphism
     */
    protected void printOptions() {
        System.out.println();
        ConsoleUtils.boxMenu("Sorting Menu", """
                 1. Sort orders by status and soonest date to be completed
                 2. Sort orders by ID in ascending order
                -1. Back to Main Menu""").render();
    }

    /**
     * Executes the specified option based on the user's choice.
     * Concept: Polymorphism
     * @param choice The user's choice.
     */
    protected void executeOption(int choice){
        switch (choice) {
            case 1 -> {
                orderManager.sortOrdersByStatusAndMostUrgent();
                System.out.println("Successfully sorted.");
                consumeInput();
            }
            case 2 -> {
                orderManager.sortOrdersByID();
                System.out.println("Successfully sorted.");
                consumeInput();
            }
            default -> {
                printError("Please enter a valid option from 1 to 2, or -1 to quit.");
                consumeInput();
            }
        }
    }

    /**
     * Runs the SortingMenu, allowing the user to interact with the available options. Concept: Polymorphism
     */
    public void run() {
        printOptions();
        int choice = promptChoice();

        while (choice != QUIT_PROGRAM) {
            executeOption(choice);
            printOptions();
            choice = promptChoice();
        }
    }
}
