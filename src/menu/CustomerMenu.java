package menu;

import info.CustomerInfo;
import management.OrderManager;
import utils.ConsoleUtils;

import java.util.List;
import static utils.ConsoleUtils.*;

/**
 * <p>ICS4U</p>
 * <p>AY Jackson SS</p>
 *
 * <p>The CustomerMenu class inherits the abstract {@link Menu} class and represents a sub menu of the {@link MainMenu}.
 * It has user input options dealing with viewing Customer Information in each order.</p>
 * <p>Concept: Inheritance</p>
 * @author Meherzad Antia
 * @author Stanley Gu
 * @author Daniel Li
 * @since 2024-1-20
 */
public class CustomerMenu extends Menu {

    /**
     * Constructs a CustomerMenu with the given OrderManager.
     *
     * @param orderManager The {@link OrderManager} associated with this menu.
     */
    public CustomerMenu(OrderManager orderManager){
        super(orderManager);
    }

    /**
     * Prints the available options for the customer menu.
     * Concept: Polymorphism
     */
    public void printOptions() {
        System.out.println();
        ConsoleUtils.boxMenu("Customer Menu", """
                  1. View info of a customer given the order ID
                  2. View info of a customer given first name and last name
                  3. Count the number of unique customers' (unique full name)
                 -1. Back to Main Menu""").render();
    }

    /**
     * Displays customer information based on the provided order ID.
     *
     */
    private void viewCustomerInfoGivenID() {
        int id;
        try {
            id = prompt("Enter the id of the order you want to get the customer information for: ").toInt();
            if (!orderManager.viewCustomerInfo(id)) {
                printError("Invalid input - Please enter a valid Order ID!");
            }
        } catch (NumberFormatException e) {
            printError("Invalid input - Please enter a valid Order ID!");
        }
        consumeInput();
    }

    /**
     * Displays customer information based on the provided first name and last name.
     */
    private void viewCustomerInfoGivenFirstNameAndLastName() {
        String firstName;
        String lastName;

        firstName = prompt("Enter the first name of the customer: ").value;
        lastName = prompt("Enter the last name of the customer: ").value;
        System.out.println();
        boolean success = orderManager.printCustomerInfoByFullName(firstName, lastName);

        if(!success){
            printError("Customer with that full name not found!");
        }
        consumeInput();
    }

    /**
     * Counts the number of unique customers and optionally prints their information.
     */
    private void viewUniqueCustomers() {
        List<CustomerInfo> uniqueCustomerInfos = orderManager.viewAllUniqueCustomerInfo();
        int count = uniqueCustomerInfos.size();

        printSuccess("There are " + count + " unique customers!");

        if (uniqueCustomerInfos.isEmpty()) {
            return;
        }

        String toPrint = prompt("Would you like to print out the information of each of these customers? (Y/N): ").value;

        if(toPrint.equalsIgnoreCase("Y")) {
            OrderManager.printCustomerInfoList(uniqueCustomerInfos);
        }
        consumeInput();
    }

    /**
     * Executes the specified option based on the user's choice.
     * Concept: Polymorphism
     * @param choice The user's choice.
     */
    public void executeOption(int choice) {
        switch (choice) {
            case 1 -> viewCustomerInfoGivenID();
            case 2 -> viewCustomerInfoGivenFirstNameAndLastName();
            case 3 -> viewUniqueCustomers();
            default -> {
                printError("Please enter a valid option from 1 to 3, or -1 to quit.");
                consumeInput();
            }
        }
    }

    /**
     * Runs the CustomerMenu, allowing the user to interact with the available options.
     * Concept: Polymorphism
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
