package menu;

import computer.ComputerType;
import hardware.GamingAdditionalHardware;
import hardware.LaptopAdditionalHardware;
import management.OrderManager;
import order.Order;
import order.OrderStatus;
import order.OrderType;
import org.json.JSONException;
import parts.Storage;
import utils.ConsoleUtils;
import static utils.ConsoleUtils.*;

import java.io.IOException;
import java.util.List;

/**
 * <p>ICS4U</p>
 * <p>AY Jackson SS</p>
 * <p>The OrderMenu class inherits from the abstract {@link Menu} class and thus
 * represents a sub menu for managing and extracting specific information related to orders within the database.
 * Encompasses a multitude of functionalities tailored to order management, and facilitates various operations on the orders in the database.</p>
 * <p>Concept: Inheritance</p>
 * @author Meherzad Antia
 * @author Stanley Gu
 * @author Daniel Li
 * @since 2024-1-20
 */
public class OrderMenu extends Menu {

    /**
     * The PrimaryHardwareMenu sub menu instance
     * Concept: Encapsulation
     */
    private final PrimaryHardwareMenu primaryHardwareMenu = new PrimaryHardwareMenu(orderManager);

    /**
     * Constructs an OrderMenu with the given OrderManager.
     *
     * @param orderManager The OrderManager associated with this menu.
     */
    public OrderMenu(OrderManager orderManager) {
        super(orderManager);
    }

    /**
     * Displays the menu options for the Order Menu.
     * The menu includes various functionalities related to managing and retrieving information about orders
     * within the database.
     * Concept: Polymorphism
     */
    @Override
    public void printOptions(){
        System.out.println();
        ConsoleUtils.boxMenu("Order Menu", """
                1. List all orders in the database
                2. Add new order
                3. Permanently delete order
                4. Update an order's primary specifications
                5. Update an order's status
                6. Search for orders by brand name (CPU and GPU)
                7. Search and display order info given order ID
                8. Get the selling price of a given order ID
                9. Get expected revenue from current orders
                10. Average price of all orders except canceled
                11. Filter orders by status
                12. Check the number of orders in the database
                13. Check/print the number of completed orders
                14. Check/print the number of current orders (not started/in progress)
                15. Check/print the number of orders that are to be picked up
                16. Check/print the number of orders that are to be shipped
                17. Check/print the number of canceled orders
                18. Check/print the number of orders that match given Additional Hardware
                    specifications
                -1. Back to Main Menu
                """).render();
    }

    /**
     * Displays the menu options for selecting computer types.
     */
    private void printComputerTypeOptions() {
        System.out.println();
        ConsoleUtils.boxMenu("Computer Type Options", """
                 1. Gaming
                 2. Laptop
                -1. Back to Order Menu""").render();
    }

    /**
     * Displays the menu options for selecting order types.
     */
    private void printOrderTypeOptions() {
        System.out.println();
        ConsoleUtils.boxMenu("Order Type Options", """
                 1. Shipping Order
                 2. Pickup Order
                -1. Back to Order Menu""").render();
    }

    /**
     * Displays the menu options for selecting statuses.
     */
    private void printStatusMenu() {
        System.out.println();
        ConsoleUtils.boxMenu("Status Menu", """
                 STATUSES:
                 1. Not Started
                 2. In Progress
                 3. Completed
                 4. Cancelled
                 5. Custom
                -1. Back to Order Menu""").render();
    }

    private void listAllOrders() {
        orderManager.printAllOrders();
        if (orderManager.isEmptyOrders()) {
            printError("Error: No orders found. Please ensure there are orders before proceeding.");
        }
        consumeInput();
    }

    /**
     * Prompts the user to enter the ID of the order they want to delete and processes the deletion.
     * Handles user input validation and communicates success or failure messages accordingly.
     */
    private void deleteOrder() {
        int id;
        try {
            id = prompt("Enter the id of the order you would like to delete: ").toInt();
        } catch (NumberFormatException e) {
            printError("Invalid input - Please enter a valid Order ID!");
            consumeInput();
            return;
        }

        if (orderManager.deleteOrder(id)) {
            printSuccess("Order successfully deleted.");
        } else {
            printError("Order failed to delete: No order exists with id " + id);
        }
        consumeInput();
    }

    /**
     * Guides the user through the process of adding a new order, prompting for computer type and order type.
     * Ensures valid input and communicates success or failure messages accordingly.
     */
    private void addOrder() {
        String computerType = "";
        String orderType = "";

        boolean doneInput = false;

        System.out.println("What type of computer is the order?");
        printComputerTypeOptions();
        int computerTypeChoice = promptChoice();

        while (!doneInput) {
            switch (computerTypeChoice) {
                case 1 -> {
                    computerType = ComputerType.GAMING;
                    doneInput = true;
                }
                case 2 -> {
                    computerType = ComputerType.LAPTOP;
                    doneInput = true;
                }
                case -1 -> { return; }
                default -> {
                    printError("Enter a valid number between 1 and 2!");
                    printComputerTypeOptions();
                    computerTypeChoice = promptChoice();

                }
            }
        }
        doneInput = false;

        System.out.println("Is it a shipping or pickup order?");
        printOrderTypeOptions();
        int orderTypeChoice = promptChoice();

        while (!doneInput) {
            switch (orderTypeChoice) {
                case 1 -> {
                    orderType = OrderType.SHIPPING;
                    doneInput = true;
                }
                case 2 -> {
                    orderType = OrderType.PICKUP;
                    doneInput = true;
                }
                case -1 -> { return; }
                default -> {
                    printError("Enter a valid number between 1 and 2!");
                    printOrderTypeOptions();
                    orderTypeChoice = promptChoice();
                }
            }
        }
        printSuccess("Please check your designated input file to make sure all data is correct and entered, and don't forget to save.");

        consumeInput();

        try {
            orderManager.addOrder(computerType, orderType);
            printSuccess("Order added successfully!");
        } catch(IOException e) {
            printError(e.getMessage());
        } catch (JSONException e) {
            printError("Error parsing JSON file. Make sure all formatting is correct and try again: " + e.getMessage());
        }
        consumeInput();

    }

    /**
     * Prompts the user to enter the ID of the order for which they want to change the status.
     * Handles user input validation and provides options to choose from predefined status options
     * or enter a custom status. Communicates success or failure messages accordingly.
     */
    private void updateOrderStatus() {
        int id;
        try {
            id = prompt("Enter the id of the order which you want to change the status of: ").toInt();
        } catch (NumberFormatException e) {
            printError("Invalid input - Please enter a valid Order ID!");
            consumeInput();
            return;
        }

        Order order = orderManager.findOrderById(id);
        if (order == null) {
            printError("Invalid input - Please enter a valid Order ID!");
            return;
        }
        printSuccess("Order with ID " + id + " found!");
        printStatusMenu();

        int choice = promptChoice();
        switch (choice) {
            case 1, 2, 3, 4 -> {
                orderManager.updateOrderStatus(order, choice - 1);
                printSuccess("Order " + id + "'s status changed!");
                consumeInput();

            }
            case 5 -> {
                orderManager.updateOrderStatus(order, prompt("Enter a custom status: ").value);
                printSuccess("Order " + id + "'s status changed!");
                consumeInput();
            }
            case -1 -> {}
            default -> {
                printError("Please enter a valid option from 1 to 5, or -1 to quit.");
                consumeInput();
            }
        }
    }

    /**
     * Searches for orders based on the brand names of the CPU and GPU specified by the user.
     * Retrieves and displays matching orders, if any, and communicates success or failure messages accordingly.
     */
    private void searchOrdersByCPUAndGPUBrand() {
        String CPUBrand = prompt("Enter the brand of the CPU you want to search for: ").value;
        String GPUBrand = prompt("Enter the brand of the GPU you want to search for: ").value;
        List<Order> orderMatches = orderManager.findOrdersByCPUAndGPUBrand(CPUBrand, GPUBrand);

        if (!orderMatches.isEmpty()) {
            printSuccess("Successfully fetched all orders with the given CPU and GPU Brands.");
            OrderManager.printOrderList(orderMatches);
        } else {
            printError("No orders that match those given brand names!");
        }
        consumeInput();
    }

    /**
     * Prompts the user to enter the ID of the order they want to print and displays the details of the order, if found.
     * Handles user input validation and communicates success or failure messages accordingly.
     */
    private void printOrderById() {
        int id;
        try {
            id = prompt("Enter the id of the order you want to print: ").toInt();
        } catch (NumberFormatException e) {
            printError("Invalid input - Please enter a valid Order ID!");
            consumeInput();
            return;
        }

        Order order = orderManager.findOrderById(id);
        if (order != null) {
            printSuccess("Successfully found order:");
            ConsoleUtils.boxOrder(order).render();
        } else {
            printError("Could not find order with a specified id.");
        }
        consumeInput();
    }

    /**
     * Prompts the user to enter the ID of the order for which they want to get the selling price of.
     * Retrieves and displays the selling price of the specified order, if it exists, and communicates
     * success or failure messages accordingly.
     */
    private void sellingPriceOfOrder() {
        int id;
        try {
            id = prompt("Enter the id of the order you want to get the selling price of: ").toInt();
        } catch (NumberFormatException nfe) {
            printError("Invalid input - Please enter a valid Order ID!");
            return;
        }

        Order order = orderManager.findOrderById(id);
        if (order != null) {
            System.out.print("The selling price of the order is: ");
            printSuccess(String.format("$%.2f", order.totalCost()));
        } else {
            printError("Invalid input - Please enter a valid Order ID!");
        }
        consumeInput();
    }

    /**
     * Filters orders based on the user-selected status, displaying the filtered results.
     * Provides options for choosing from predefined status options or entering a custom status to filter by.
     * Communicates success or failure messages accordingly.
     */
    private void filterOrdersByStatus() {
        printStatusMenu();
        int choice = promptChoice();
        OrderStatus orderStatus;

        switch (choice) {
            case 1, 2, 3, 4 -> orderStatus = OrderStatus.defaultByIndex(choice - 1);
            case 5 -> orderStatus = new OrderStatus(prompt("Enter a custom status: ").value);
            case -1 -> { return; }
            default -> {
                printError("Please enter a valid option from 1 to 5, or -1 to quit.");
                consumeInput();
                return;
            }
        }

        List<Order> filteredOrders = orderManager.filterOrdersByStatus(orderStatus);

        if (filteredOrders.isEmpty()) {
            printError("No orders found with the given status.");
        } else {
            printSuccess("Successfully filtered all orders by status: ");
            OrderManager.printOrderList(filteredOrders);
        }
        consumeInput();
    }

    /**
     * Retrieves and displays all completed orders, providing an option to print the details of each completed order.
     * Communicates the total number of completed orders and prompts the user for further action.
     */
    private void allCompletedOrders() {
        List<Order> completedOrders = orderManager.allCompletedOrders();
        printSuccess("Currently " + completedOrders.size() + " completed orders."); // number of completed order

        if (completedOrders.isEmpty()) {
            return;
        }

        String toPrint = prompt("Would you like to print out all the completed orders? (Y/N) ").value;
        if(toPrint.equalsIgnoreCase("y")) {
            OrderManager.printOrderList(completedOrders);
        }
        consumeInput();
    }

    /**
     * Retrieves and displays all current orders (not started or in progress), providing an option to print the details of each order.
     * Communicates the total number of current orders and prompts the user for further action.
     */
    private void allCurrentOrders() {
        List<Order> currentOrders = orderManager.allCurrentOrders();
        printSuccess("Currently " + currentOrders.size() + " not started or in progress orders");

        if (currentOrders.isEmpty()) {
            return;
        }

        String toPrint = prompt("Would you like to print out all the current orders? (Y/N) ").value;

        if (toPrint.equalsIgnoreCase("y")) {
            OrderManager.printOrderList(currentOrders);
        }
        consumeInput();
    }

    /**
     * Retrieves and displays all orders that are to be shipped out, providing an option to print the details of each shipping order.
     * Communicates the total number of orders to be shipped and prompts the user for further action.
     */
    private void allShippingOrders() {
        List<Order> shippingOrders = orderManager.allShippingOrders();
        printSuccess("Currently " + shippingOrders.size() + " orders to be shipped out. ");

        if (shippingOrders.isEmpty()) {
            return;
        }

        String toPrint = prompt("Would you like to print out all the shipping orders? (Y/N) ").value;
        if (toPrint.equalsIgnoreCase("y")) {
            OrderManager.printOrderList(shippingOrders);
        }
        consumeInput();
    }

    /**
     * Retrieves and displays all orders that are to be picked up, providing an option to print the details of each pickup order.
     * Communicates the total number of pickup orders and prompts the user for further action.
     */
    private void allPickupOrders() {
        List<Order> pickupOrders = orderManager.allPickupOrders();
        printSuccess("Currently " + pickupOrders.size() + " orders to be picked up.");

        if (pickupOrders.isEmpty()) {
            return;
        }

        String toPrint = prompt("Would you like to print out all the pickup orders? (Y/N) ").value;
        if (toPrint.equalsIgnoreCase("y")) {
            OrderManager.printOrderList(pickupOrders);
        }
        consumeInput();
    }

    /**
     * Retrieves and displays all cancelled orders, providing an option to print the details of each cancelled order.
     * Communicates the total number of cancelled orders and prompts the user for further action.
     */
    private void allCancelledOrders(){
        List<Order> cancelledOrders = orderManager.allCancelledOrders();
        printSuccess("Currently " + cancelledOrders.size() + " cancelled orders.");

        if (cancelledOrders.isEmpty()) {
            return;
        }

        String toPrint = prompt("Would you like to print out all the cancelled orders? (Y/N) ").value;
        if (toPrint.equalsIgnoreCase("y")) {
            OrderManager.printOrderList(cancelledOrders);
        }
        consumeInput();
    }



    /**
     * Prompts the user to input specifications for creating a new additional storage instance
     * based on capacity (GB) and speed (MBps). Validates user input to ensure it is numeric.
     *
     * @return A new Storage instance with the specified capacity and speed.
     */
    private Storage createAdditionalStorageFromUserForMatching() {
        String brand = "default";
        String model = "default";
        double price = 0;
        int capacityGB = 0;
        int speedMBps = 0;

        boolean validInput = false;

        while(!validInput){
            try {
                capacityGB = prompt("Enter the capacity of the Additional Storage (GB): ").toPositiveInt();
                speedMBps = prompt("Enter the speed of the Additional Storage (MBps): ").toPositiveInt();
                validInput = true;
            }
            catch(IllegalArgumentException e){
                printError("Invalid input! Try entering your Additional Storage specifications again.");
            }
        }
        return new Storage(price, model, brand, speedMBps, capacityGB);
    }


    /**
     * Prompts the user to enter a percentage for filtering orders based on match criteria.
     * Ensures the input is a valid decimal number between 0 and 1, inclusive.
     *
     * @return The entered match percentage as a decimal number. (between 0 and 1, inclusive)
     */
    private double promptForMatchPercentage() {
        double percentage = 0;
        boolean validPercentageInput = false;

        while (!validPercentageInput) {
            try {
                percentage = prompt("What percentage of match would you like to filter orders for? (Enter as decimal number): ").toPositiveDouble();
                if (percentage > 1) {
                    printError("Invalid input! Please enter as a decimal number between 0 and 1, inclusive.");
                } else {
                    validPercentageInput = true;
                }
            } catch (NumberFormatException nfe) {
                printError("Invalid input! Please enter as a decimal number between 0 and 1, inclusive.");
            } catch(IllegalArgumentException e){
                printError("Please enter a positive, finite number!");
            }
        }
        return percentage;
    }

    /**
     * Guides the user through entering specifications for Gaming Additional Hardware specifications and a matching percentage.
     * Finds and displays orders that match the specified Gaming Additional Hardware at the specified match rate.
     * Provides option to view the matching orders and communicates success or failure messages accordingly.
     */
    private void findMatchingOrdersByGamingAdditionalHardwareAndPercent() {

        System.out.println("Enter the Gaming Additional Hardware Specs to match for in the orders ");
        boolean validInput = false;

        while (!validInput) {
            try{
                boolean noiseCancelFans = false;
                boolean rgbStrips = false;

                int extraFansCount = prompt("Extra fans count: ").toPositiveInt();
                String useNoiseCancellingFans = prompt("Noise cancelling fans? (Y/N): ").value;
                String useRGBStrips = prompt("RBG Strips? (Y/N): ").value;

                if (useNoiseCancellingFans.equalsIgnoreCase("Y")) {
                    noiseCancelFans = true;
                }
                if(useRGBStrips.equalsIgnoreCase("Y")) {
                    rgbStrips = true;
                }

                Storage additionalStorage = createAdditionalStorageFromUserForMatching();
                GamingAdditionalHardware gamingAdditionalHardware = new GamingAdditionalHardware(additionalStorage, rgbStrips, noiseCancelFans, extraFansCount);

                double percentage = promptForMatchPercentage();

                List<Order> orderMatches = orderManager.findMatchingOrdersByAdditionalHardwareAndPercent(gamingAdditionalHardware, percentage);

                printSuccess("There are " + orderMatches.size() + " orders that match that Gaming Additional Hardware at " + percentage*100 + "% match rate. ");
                validInput = true;

                if(!orderMatches.isEmpty()){
                    String toPrint = prompt("Would you like to view those orders? (Y/N): ").value;

                    if (toPrint.equalsIgnoreCase("Y")) {
                        OrderManager.printOrderList(orderMatches);
                    }
                }
            } catch (NumberFormatException e) {
                printError("Invalid input! Try entering your specifications again.");
            } catch (IllegalArgumentException e) {
                printError("Please enter a positive, finite number!");
            }
        }

    }

    /**
     * Guides the user through entering specifications for Laptop Additional Hardware specifications and a matching percentage.
     * Finds and displays orders that match the specified Laptop Additional Hardware at the specified match rate.
     * Provides option to view the matching orders and communicates success or failure messages accordingly.
     */
    private void findMatchingOrdersByLaptopAdditionalHardwareAndPercent(){
        System.out.println("Enter the Laptop Additional Hardware Specs to match for in the orders: ");
        boolean validInput = false;

        while(!validInput){
            try{
                boolean fingerprintScanner = false;
                boolean touchscreen = false;
                boolean hinge360 = false;

                String useFingerprintScanner = prompt("Fingerprint scanner? (Y/N): ").value;
                String useTouchscreen = prompt("Touchscreen? (Y/N): ").value;
                String useHinge360 = prompt("360 Hinge? (Y/N): ").value;

                if(useFingerprintScanner.equalsIgnoreCase("Y")) {
                    fingerprintScanner = true;
                }
                if(useTouchscreen.equalsIgnoreCase("Y")) {
                    touchscreen = true;
                }
                if(useHinge360.equalsIgnoreCase("Y")) {
                    hinge360 = true;
                }

                Storage additionalStorage = createAdditionalStorageFromUserForMatching();

                LaptopAdditionalHardware laptopAdditionalHardware = new LaptopAdditionalHardware(additionalStorage, fingerprintScanner, touchscreen, hinge360);
                double percentage = promptForMatchPercentage();

                List<Order> orderMatches = orderManager.findMatchingOrdersByAdditionalHardwareAndPercent(laptopAdditionalHardware, percentage);

                printSuccess("There are " + orderMatches.size() + " orders that match that Laptop Additional Hardware at " + percentage*100 + "% match rate. ");
                validInput = true;

                if(!orderMatches.isEmpty()){
                    String toPrint = prompt("Would you like to view those orders? (Y/N): ").value;

                    if (toPrint.equalsIgnoreCase("Y")) {
                        OrderManager.printOrderList(orderMatches);
                    }
                }
            }
            catch(NumberFormatException e){
                printError("Invalid input! Try entering your specifications again.");
            }
            catch(IllegalArgumentException e){
                printError("Please enter a positive, finite number!");
            }
        }

    }

    /**
     * Guides the user through selecting a computer type and finding orders that match the appropriate additional hardware specifications and a specified match percentage.
     * Provides options for gaming computers and laptops, and communicates success or failure messages accordingly.
     */
    private void findMatchingOrdersByAdditionalHardwareAndPercent(){
        System.out.println("What type of computer should we match for Additional Hardware specs?");
        printComputerTypeOptions();

        int computerTypeChoice = promptChoice();

        boolean doneInput = false;

        while(!doneInput) {
            switch (computerTypeChoice) {
                case -1 -> { return; }
                case 1 -> {
                    findMatchingOrdersByGamingAdditionalHardwareAndPercent();
                    doneInput = true;
                    }
                case 2 -> {
                    findMatchingOrdersByLaptopAdditionalHardwareAndPercent();
                    doneInput = true;
                }
                default -> {
                    printError("Enter a valid number between 1 and 2! -1 to return back to the Main Menu.");
                    printOrderTypeOptions();
                    computerTypeChoice = promptChoice();
                }
            }
        }
        consumeInput();

    }

    /**
     * Executes the specified option based on the user's choice.
     * Concept: Polymorphism
     * @param choice The user's choice.
     */
    @Override
    protected void executeOption(int choice) {

        switch (choice) {
            case 1 -> listAllOrders();
            case 2 -> addOrder();
            case 3 -> deleteOrder();
            case 4 -> primaryHardwareMenu.run();
            case 5 -> updateOrderStatus();
            case 6 -> searchOrdersByCPUAndGPUBrand();
            case 7 -> printOrderById();
            case 8 -> sellingPriceOfOrder();
            case 9 -> {
                printSuccess(String.format("Expected revenue from all current orders: $%.2f", orderManager.calculateExpectedRevenue()));
                consumeInput();
            }
            case 10 -> {
                printSuccess(String.format("Average Price of all non-canceled orders: $%.2f", orderManager.calculateAveragePriceOfAllOrders()));
                consumeInput();
            }
            case 11 -> filterOrdersByStatus();
            case 12 -> {
                printSuccess("Currently " + orderManager.ordersCount() + " total orders (including cancelled)");
                consumeInput();
            }
            case 13 -> allCompletedOrders();
            case 14 -> allCurrentOrders();
            case 15 -> allPickupOrders();
            case 16 -> allShippingOrders();
            case 17 -> allCancelledOrders();
            case 18 -> findMatchingOrdersByAdditionalHardwareAndPercent();
            default -> {
                printError("Please enter a valid option from 1 to 18, or -1 to quit.");
                consumeInput();
            }
        }
    }

    /**
     * Runs the SavingAndLoadingMenu, allowing the user to interact with the available options. Concept: Polymorphism
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
    }
}
