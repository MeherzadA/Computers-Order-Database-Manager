package menu;

import management.OrderManager;
import static utils.ConsoleUtils.*;

/**
 * <p>ICS4U</p>
 * <p>AY Jackson SS</p>
 *
 *
 * <p>This abstract class represents a console menu that the user can interact with by inputting different
 * numbers as options. It has three abstract methods which define the functionality of whatever specific menu
 * is inheriting this class, which pertain to printing the options, executing the option that the user selected,
 * and running the menu. </p>
 * <p>Concept: Abstract Class</p>
 * @author Meherzad Antia
 * @author Stanley Gu
 * @author Daniel Li
 * @since 2024-1-20
 */
public abstract class Menu {
    protected static final int INPUT_MISMATCH = 0;
    protected static final int QUIT_PROGRAM = -1;
    protected final OrderManager orderManager;

    /**
     * Constructs a menu with the specified order manager.
     *
     * @param orderManager The {@link OrderManager} instance associated with the menu.
     */
    public Menu(OrderManager orderManager) {
        this.orderManager = orderManager;
    }

    /**
     * Prints the available options of the menu. Concept: Abstract Method
     */
    protected abstract void printOptions();

    /**
     * Executes the selected option based on the provided choice. Concept: Abstract Method
     *
     * @param choice The user's choice to execute.
     */
    protected abstract void executeOption(int choice);

    /**
     * Initiates the execution of the menu, displaying options and handling user input. Concept: Abstract Method
     */
    public abstract void run();

    /**
     * Prompts the user to enter a choice and returns the integer representation of the input.
     *
     * @return The user's choice or INPUT_MISMATCH if the input is not a valid integer.
     */
    protected int promptChoice() {
        try {
            return prompt("Enter a choice: ").toInt();
        } catch (NumberFormatException e) {
            return INPUT_MISMATCH;
        }
    }
}
