package menu;

import management.OrderManager;
import utils.ConsoleUtils;
import static utils.ConsoleUtils.*;

/**
 * <p>ICS4U</p>
 * <p>AY Jackson SS</p>
 * <p>The MainMenu class inherits the abstract {@link Menu} class. It represents the main menu of the system,
 *  providing options for viewing customer information, sorting, managing orders,
 *  and saving/loading data.</p>
 *  <p>Concept: Inheritance</p>
 * @author Meherzad Antia
 * @author Stanley Gu
 * @author Daniel Li
 * @since 2024-1-20
 */
public class MainMenu extends Menu {

    /**
     * The {@link OrderMenu} sub menu instance.
     * Concept: Encapsulation
     */
    private final OrderMenu orderMenu = new OrderMenu(orderManager);
    /**
     * The {@link SortingMenu} sub menu instance. Concept: Encapsulation
     */
    private final SortingMenu sortingMenu = new SortingMenu(orderManager);
    /**
     * The {@link CustomerMenu} sub menu instance. Concept: Encapsulation
     */
    private final CustomerMenu customerMenu = new CustomerMenu(orderManager);
    /**
     * The {@link SavingAndLoadingMenu} sub menu instance. Concept: Encapsulation
     */
    private final SavingAndLoadingMenu savingAndLoadingMenu = new SavingAndLoadingMenu(orderManager);

    /**
     * Constructs a MainMenu with the given OrderManager.
     *
     * @param orderManager The OrderManager associated with this menu.
     */
    public MainMenu(OrderManager orderManager) {
        super(orderManager);
    }

    /**
     * Prints the available options for the main menu.
     * Concept: Polymorphism
     */
    @Override
    protected void printOptions() {
        System.out.println();
        ConsoleUtils.boxMenu("Main Menu", """
                1. View Customer
                2. Sorting
                3. Orders
                4. Save / Load
               -1. Quit Program""").render();
    }

    /**
     * Executes the specified option based on the user's choice.
     * Concept: Polymorphism
     * @param choice The user's choice.
     */
    @Override
    public void executeOption(int choice) {
        switch (choice) {
            case 1 -> customerMenu.run();
            case 2 -> sortingMenu.run();
            case 3 -> orderMenu.run();
            case 4 -> savingAndLoadingMenu.run();
            default -> {
                printError("Please enter a valid number from 1 to 4.");
                consumeInput();
            }
        }
    }

    /**
     * Runs the MainMenu, allowing the user to interact with the available options. Concept: Polymorphism
     */
    @Override
    public void run() {
        printOptions();
        int choice = promptChoice();

        while (choice != QUIT_PROGRAM) {
            executeOption(choice);
            printOptions();
            choice = promptChoice();
        }

        System.out.println("Thank you for coming! We hope you run this database again sometime soon! :D");
    }
}
