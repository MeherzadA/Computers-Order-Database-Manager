package menu;

import management.OrderManager;
import org.json.JSONException;
import utils.ConsoleUtils;
import static utils.ConsoleUtils.*;

import java.io.IOException;

/**
 * <p>ICS4U</p>
 * <p>AY Jackson SS</p>
 * <p>The SavingAndLoadingMenu class inherits from the abstract {@link Menu} class and thus
 * represents a sub menu for saving and loading orders from a database.
 * It provides options to save all orders to a file and load all orders from a file.</p>
 * <p>Concept: Inheritance</p>
 * @author Meherzad Antia
 * @author Stanley Gu
 * @author Daniel Li
 * @since 2024-1-20
 */
public class SavingAndLoadingMenu extends Menu {

    /**
     * Constructs a SavingAndLoadingMenu with the given OrderManager.
     *
     * @param orderManager The OrderManager associated with this menu.
     */
    public SavingAndLoadingMenu(OrderManager orderManager){
        super(orderManager);
    }

    /**
     * Prints the available options for saving and loading orders. Concept: Polymorphism
     */
    public void printOptions() {
        System.out.println();
        ConsoleUtils.boxMenu("Saving & Loading Menu", """
                 1. Save all orders to file (orders.json)
                 2. Load all orders from file (orders.json)
                -1. Back to Main Menu""").render();
    }

    /**
     * Executes the specified option based on the user's choice.
     * Concept: Polymorphism
     * @param choice The user's choice.
     */
    public void executeOption(int choice) {
        switch (choice) {
            case 1 -> {
                orderManager.saveOrdersToDatabase();
                printSuccess("Successfully saved orders to database!");
            }
            case 2 -> {
                try{
                    orderManager.loadOrdersFromDatabase();
                    printSuccess("Successfully loaded orders from database!");
                } catch (IOException | JSONException e){
                    printError(e.getMessage());
                }
            }
            default -> printError("Please enter a valid option from 1 to 2, or -1 to quit.");
        }
        consumeInput();
    }

    /**
     * Runs the SavingAndLoadingMenu, allowing the user to interact with the available options. Concept: Polymorphism
     */
    public void run() {
        printOptions();
        int choice = promptChoice();

        while(choice != QUIT_PROGRAM) {
            executeOption(choice);
            printOptions();
            choice = promptChoice();
        }
    }
}
