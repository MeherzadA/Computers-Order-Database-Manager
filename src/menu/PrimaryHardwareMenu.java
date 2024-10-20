package menu;

import management.OrderManager;
import order.Order;
import utils.ConsoleUtils;

import static utils.ConsoleUtils.*;

/**
 * <p>ICS4U</p>
 * <p>AY Jackson SS</p>
 * <p>The PrimaryHardwareMenu class represents a menu for updating primary hardware components
 * (CPU, GPU, RAM, Motherboard, and Storage) of a specific order in an order management system.
 * It extends the Menu class and provides methods to interact with the user, gather input,
 * and update the specified order's hardware components.</p>
 * <p>Concept: Inheritance</p>
 * @author Meherzad Antia
 * @author Stanley Gu
 * @author Daniel Li
 * @since 2024-1-20
 */
public class PrimaryHardwareMenu extends Menu {

    /**
     * Constructs a new PrimaryHardwareMenu with the given OrderManager.
     *
     * @param orderManager The OrderManager to manage orders.
     */
    public PrimaryHardwareMenu(OrderManager orderManager) {
        super(orderManager);
    }

    /**
     * Prompts the user to enter updated information for the CPU of the specified order and updates the order's CPU accordingly.
     * Handles user input validation and communicates success or failure messages accordingly.
     *
     * @param order The order for which the Storage is to be updated.
     */
    private void updateOrderCPU(Order order) {
        int cores;
        double price;
        int threads;
        double processingSpeed;
        String model;
        String brand;

        try {
            brand = prompt("Enter the updated brand of the CPU: ").value;
            model = prompt("Enter the updated model of the CPU: ").value;
            price = prompt("Enter the updated price of the CPU: ").toPositiveDouble();
            cores = prompt("Enter the updated core count of the CPU: ").toInt();
            threads = prompt("Enter the updated thread count of the CPU: ").toInt();
            processingSpeed = prompt("Enter the updated processing speed of the CPU (GHz): ").toPositiveDouble();

            orderManager.updateOrderCPU(order, brand, model, price, cores, threads, processingSpeed);
            printSuccess("CPU for order " + order.getId() + " successfully updated!");
        } catch (NumberFormatException e) {
            printError("Invalid input! Try again or select another component to update.");
        } catch (IllegalArgumentException e) {
            printError("Please enter a positive, finite number!");
        }
        consumeInput();
    }

    /**
     * Prompts the user to enter updated information for the GPU of the specified order and updates the order's GPU accordingly.
     * Handles user input validation and communicates success or failure messages accordingly.
     *
     * @param order The order for which the Storage is to be updated.
     */
    private void updateOrderGPU(Order order) {
        boolean rayTracing = false;
        int vram;
        double clockSpeed;
        double price;
        String model;
        String brand;

        try {
            brand = prompt("Enter the updated brand of the GPU: ").value;
            model = prompt("Enter the updated model of the GPU: ").value;
            price = prompt("Enter the updated price of the GPU: ").toPositiveDouble();
            String hasRayTracing = prompt("Is there ray tracing? (Y/N) ").value;
            vram = prompt("Enter the updated VRAM amount of the GPU: ").toInt();
            clockSpeed = prompt("Enter the updated clock speed of the GPU (GHz): ").toPositiveDouble();

            if (hasRayTracing.equalsIgnoreCase("Y")) {
                rayTracing = true;
            }

            orderManager.updateOrderGPU(order, brand, model, price, rayTracing, clockSpeed, vram);
            printSuccess("GPU for order " + order.getId() + " successfully updated!");
        } catch (NumberFormatException e) {
            printError("Invalid input! Try again or select another component to update.");
        } catch (IllegalArgumentException e) {
            printError("Please enter a positive, finite number!");
        }
        consumeInput();
    }

    /**
     * Prompts the user to enter updated information for the RAM of the specified order and updates the order's RAM accordingly.
     * Handles user input validation and communicates success or failure messages accordingly.
     *
     * @param order The order for which the Storage is to be updated.
     */
    private void updateOrderRAM(Order order) {
        int capacity;
        int speedMHz;
        double price;
        String type;
        String model;
        String brand;

        try {
            brand = prompt("Enter the updated brand of the RAM: ").value;
            model = prompt("Enter the updated model of the RAM: ").value;
            price = prompt("Enter the updated price of the RAM: ").toPositiveDouble();
            type = prompt("Enter the updated RAM type (Ex: DDR4, DDR5, etc.): ").value;
            capacity = prompt("Enter the updated RAM capacity (GB): ").toInt();
            speedMHz = prompt("Enter the updated speed of the RAM (MHz): ").toInt();

            orderManager.updateOrderRAM(order, brand, model, price, type, capacity, speedMHz);
            printSuccess("RAM for order " + order.getId() + " successfully updated!");
        } catch (NumberFormatException e) {
            printError("Invalid input! Try again or select another component to update.");
        } catch (IllegalArgumentException e) {
            printError("Please enter a positive, finite number!");
        }
        consumeInput();
    }

    /**
     * Prompts the user to enter updated information for the Motherboard of the specified order and updates the order's Motherboard accordingly.
     * Handles user input validation and communicates success or failure messages accordingly.
     *
     * @param order The order for which the Storage is to be updated.
     */
    private void updateOrderMotherboard(Order order) {
        boolean wifi = false;
        double price;
        String size;
        String model;
        String brand;

        try {
            brand = prompt("Enter the updated brand of the Motherboard: ").value;
            model = prompt("Enter the updated model of the Motherboard: ").value;
            price = prompt("Enter the updated price of the Motherboard: ").toPositiveDouble();
            String hasWifi = prompt("Is there wifi capabilities? (Y/N) ").value;
            size = prompt("Enter the updated size of the Motherboard (Ex: ATX, Micro-ATX, etc.): ").value;

            if (hasWifi.equalsIgnoreCase("Y")) {
                wifi = true;
            }

            orderManager.updateOrderMotherboard(order, brand, model, price, size, wifi);
            printSuccess("Motherboard for order " + order.getId() + " successfully updated!");
        } catch (NumberFormatException e) {
            printError("Invalid input! Try again or select another component to update.");
        } catch (IllegalArgumentException e) {
            printError("Please enter a positive, finite number!");
        }
        consumeInput();
    }

    /**
     * Prompts the user to enter updated information for the Storage of the specified order and updates the order's Storage accordingly.
     * Handles user input validation and communicates success or failure messages accordingly.
     *
     * @param order The order for which the Storage is to be updated.
     */
    private void updateOrderStorage(Order order) {
        int capacityGB;
        int speedMBps;
        double price;
        String model;
        String brand;

        try {
            brand = prompt("Enter the updated brand of the Storage: ").value;
            model = prompt("Enter the updated model of the Storage: ").value;
            price = prompt("Enter the updated price of the Storage: ").toPositiveDouble();
            capacityGB = prompt("Enter the updated Storage capacity (GB): ").toInt();
            speedMBps = prompt("Enter the updated speed of the Storage (MBps): ").toInt();

            orderManager.updateOrderStorage(order, brand, model, price, capacityGB, speedMBps);
            printSuccess("Storage for order " + order.getId() + " successfully updated!");
        } catch (NumberFormatException e) {
            printError("Invalid input! Try again or select another component to update.");
        } catch (IllegalArgumentException e) {
            printError("Please enter a positive, finite number!");
        }
        consumeInput();
    }

    /**
     * Displays the menu options for selecting Primary Hardware components.
     * Concept: Polymorphism
     */
    @Override
    public void printOptions() {
        System.out.println();
        ConsoleUtils.boxMenu("Primary Hardware Options", """
                 1. Update CPU
                 2. Update GPU
                 3. Update RAM
                 4. Update Motherboard
                 5. Update Primary Storage
                -1. Back to Order Menu""").render();
    }

    /**
     * Executes the option corresponding to the user's choice.
     * Concept: Polymorphism
     * @param choice The user's choice.
     */
    @Override
    public void executeOption(int choice) {
        Order order;
        int id;
        switch (choice) {
            case 1, 2, 3, 4, 5 -> {
                try {
                    id = prompt("Enter the id of the order you want to edit the parts of: ").toInt();
                } catch (NumberFormatException e) {
                    printError("Invalid input - Please enter a valid Order ID!");
                    consumeInput();
                    return;
                }

            }
            default -> {
                printError("Please enter a valid option between 1 and 5! Or, -1 to return back to the Order Menu.");
                consumeInput();
                return;
            }
        }

        order = orderManager.findOrderById(id);

        if (order == null) {
            printError("Order with ID of " + id + " not found!");
            consumeInput();
            return;
        }

        switch (choice) {
            case 1 -> updateOrderCPU(order);
            case 2 -> updateOrderGPU(order);
            case 3 -> updateOrderRAM(order);
            case 4 -> updateOrderMotherboard(order);
            case 5 -> updateOrderStorage(order);
            default -> {
                printError("Please enter a valid option from 1 to 5! Or, -1 to return back to Order Menu.");
                consumeInput();
            }
        }
    }

    /**
     * Runs the PrimaryHardwareMenu, displaying options to the user and executing their choices until they choose to quit.
     * Concept: Polymorphism
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
