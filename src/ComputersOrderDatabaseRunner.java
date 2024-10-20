import management.OrderManager;
import menu.MainMenu;
import org.json.JSONException;
import static utils.ConsoleUtils.*;

import java.io.IOException;

/**
 * <p>ICS4U</p>
 * <p>AY Jackson SS</p>
 *
 * The {@code ComputersOrderDatabaseRunner} class is the entry point for the Computers Order Database application.
 * It initializes the {@link OrderManager}, loads orders from the 'orders.json' file, and starts the main menu.
 * Handles exceptions related to JSON formatting issues, reading from files, and out-of-bounds status numbers.
 * Displays appropriate error messages and quits the program in case of issues during initialization.
 *
 * @author Meherzad Antia
 * @author Stanley Gu
 * @author Daniel Li
 * @since 2024-01-19
 */
public class ComputersOrderDatabaseRunner {

    /**
     * The order manager instance for managing orders.
     */
    private static OrderManager orderManager = null;

    public static void main(String[] args) {

        // loading the orders.json to ordersList (quit program instantly if an issue on this part)
        try {
            orderManager = new OrderManager();
        } catch (JSONException e) { // json formatting issue
            printError(e.getMessage());
        } catch (IOException e) { // reading from file issue
            printError(e.getMessage());
        } catch (IllegalArgumentException e) { // out of bounds status number
            printError(e.getMessage());
        }

        // after orders.json has been loaded into memory, all subsequent errors will be handled accordingly
        if (orderManager != null) {
            new MainMenu(orderManager).run();
        }
    }
}