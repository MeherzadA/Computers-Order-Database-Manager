package utils;

import computer.Computer;
import computer.GamingComputer;
import hardware.GamingAdditionalHardware;
import hardware.PrimaryHardware;
import info.CustomerInfo;
import info.ShippingInfo;
import order.Order;
import order.PickupOrder;
import order.ShippingOrder;
import table.*;

import java.util.Scanner;

/**
 * <p>ICS4U</p>
 * <p>AY Jackson SS</p>
 *
 * <p>The ConsoleUtils class provides utility methods for interacting with the console,
 * such as prompting user input, displaying messages, and creating/formatting tables for Customer Info,
 * Shipping/Pickup Info, and Order Info.</p>
 * @author Meherzad Antia
 * @author Stanley Gu
 * @author Daniel Li
 * @since 2024-1-20
 */
public class ConsoleUtils {
    /** Constants for formatting table for menus and orders */
    private static final int MENU_WIDTH = 80;
    private static final int INNER_ORDER_WIDTH = 100;
    private static final int OUTER_ORDER_WIDTH = 110;
    private static final int ORDER_INFORMATION_RATIO_KEYS = 1;
    private static final int ORDER_INFORMATION_RATIO_VALUES = 2;
    private static final int HARDWARE_RATIO = 4;
    private static final int HARDWARE_KEY_RATIO = 4;
    private static final int HARDWARE_VALUE_RATIO = 7;

    /** Scanner class for reading input */
    private static final Scanner sc = new Scanner(System.in);

    /** Constants for colours */
    private static final String RED = "\033[31m";
    private static final String GREEN = "\033[92m";
    private static final String RESET_FORMATTING = "\033[0m";
    private static final String ITALICS = "\033[3m";


    /** Private constructor to prevent instantiation **/
    private ConsoleUtils() {}

    /**
     * Prompts the user with a message and returns the user's input as an Input object.
     *
     * @param prompt The message to prompt the user.
     * @return An {@link Input} object containing the user's input.
     */
    public static Input prompt(String prompt) {
        System.out.print(prompt);
        return new Input(sc.nextLine());
    }

    // do we still need this
    public static int promptId() {
        int id;
        try {
            id = prompt("Enter the id of the order you want to edit the parts of: ").toInt();
        } catch (NumberFormatException e) {
            printError("Invalid input - Please enter a valid Order ID!");
            consumeInput();
            return -1;
        }
        return id;
    }

    /**
     * Consumes the user input by prompting them to press enter to continue.
     */
    public static void consumeInput() {
        consumeInput(italics("Press enter to continue... "));
    }

    /**
     * Consumes the user input by displaying a message and prompting them to press enter.
     *
     * @param message The message to display.
     */
    public static void consumeInput(String message) {
        prompt(message);
    }

    /**
     * Applies italics formatting to the provided message.
     *
     * @param msg The message to be italicized.
     * @return The italicized message.
     */
    public static String italics(String msg) {
        return ITALICS + msg + RESET_FORMATTING;
    }

    /**
     * Prints an error message in red color to the console.
     *
     * @param msg The error message to be displayed.
     */
    public static void printError(String msg) {
        System.out.println(RED + msg + RESET_FORMATTING);
    }

    /**
     * Prints a success message in green color to the console.
     *
     * @param msg The success message to be displayed.
     */
    public static void printSuccess(String msg) {
        System.out.println(GREEN + msg + RESET_FORMATTING);
    }

    /**
     * Creates and returns a table formatted with a title and menu options.
     *
     * @param title The title of the menu.
     * @param menu  The menu options.
     * @return A {@link Table} object representing the formatted menu.
     */
    public static Table boxMenu(String title, String menu) {
        Table table = new Table(MENU_WIDTH);
        table.addComponent(new SimpleComponent(title, Component.CENTER_JUSTIFY));
        table.addComponent(new SimpleComponent(menu, Component.LEFT_JUSTIFY));

        return table;
    }

    /**
     * Creates and returns a table formatted with customer information.
     *
     * @param customerInfo The customer information to display.
     * @return A {@link Table} object representing the formatted customer information.
     */
    public static Table boxCustomerInfo(CustomerInfo customerInfo) {
        Table table = new Table(INNER_ORDER_WIDTH);
        table.addComponent(new SimpleComponent("Customer Information", Component.CENTER_JUSTIFY));
        table.addComponent(new KeyValueComponent(customerInfo.toString(),
                Component.LEFT_JUSTIFY, ORDER_INFORMATION_RATIO_KEYS, Component.LEFT_JUSTIFY, ORDER_INFORMATION_RATIO_VALUES));

        return table;
    }

    /**
     * Creates and returns a table formatted with order details.
     *
     * @param order The order to display.
     * @return A {@link Table} object representing the formatted order details.
     */
    public static Table boxOrder(Order order) {
        Table outerTable = new Table(OUTER_ORDER_WIDTH);
        Table table = new Table(INNER_ORDER_WIDTH);

        outerTable.addComponent(new SimpleComponent("Order #" + order.getId(), Component.CENTER_JUSTIFY));

        // Add general order information
        table.addComponent(new SimpleComponent("General Information", Component.CENTER_JUSTIFY));
        table.addComponent(new KeyValueComponent(String.format("""
                        Order Status: %s
                        Quantity: %d
                        Date Ordered: %s
                        Finish By Date: %s""",
                order.getOrderStatus(), order.getQuantity(), order.getOrderDate(), order.getFinishByDate()),
                Component.LEFT_JUSTIFY, ORDER_INFORMATION_RATIO_KEYS, Component.LEFT_JUSTIFY, ORDER_INFORMATION_RATIO_VALUES));

        // Add customer information
        CustomerInfo customerInfo = order.getCustomerInfo();
        table.addComponents(boxCustomerInfo(customerInfo));

        // Add shipping or pickup information based on order type
        if (order instanceof ShippingOrder) {
            ShippingOrder shippingOrder = (ShippingOrder) order;
            table.addComponent(new SimpleComponent("Shipping Information", Component.CENTER_JUSTIFY));
            table.addComponent(new KeyValueComponent(shippingOrder.getShippingInfo().toString(),
                    Component.LEFT_JUSTIFY, ORDER_INFORMATION_RATIO_KEYS, Component.LEFT_JUSTIFY, ORDER_INFORMATION_RATIO_VALUES));
        } else if (order instanceof PickupOrder) {
            PickupOrder pickupOrder = (PickupOrder) order;
            table.addComponent(new SimpleComponent("Pickup Information", Component.CENTER_JUSTIFY));
            table.addComponent(new KeyValueComponent(pickupOrder.getPickupInfo().toString(),
                    Component.LEFT_JUSTIFY, ORDER_INFORMATION_RATIO_KEYS, Component.LEFT_JUSTIFY, ORDER_INFORMATION_RATIO_VALUES));
        }

        // Add computer configuration details
        table.addComponent(new SimpleComponent("Computer Configuration", Component.CENTER_JUSTIFY));

        Computer computer = order.getComputer();
        PrimaryHardware primaryHardware = computer.getPrimaryHardware();

        // Add primary hardware details
        table.addComponent(new SimpleComponent("Primary Hardware", Component.LEFT_JUSTIFY));
        table.addComponent(new HeaderedKeyValueComponent("CPU", Component.LEFT_JUSTIFY, HARDWARE_RATIO,
                primaryHardware.getCPU().toString(), Component.LEFT_JUSTIFY, HARDWARE_KEY_RATIO, Component.LEFT_JUSTIFY, HARDWARE_VALUE_RATIO));
        table.addComponent(new HeaderedKeyValueComponent("GPU", Component.LEFT_JUSTIFY, HARDWARE_RATIO,
                primaryHardware.getGPU().toString(), Component.LEFT_JUSTIFY, HARDWARE_KEY_RATIO, Component.LEFT_JUSTIFY, HARDWARE_VALUE_RATIO));
        table.addComponent(new HeaderedKeyValueComponent("RAM", Component.LEFT_JUSTIFY, HARDWARE_RATIO,
                primaryHardware.getRAM().toString(), Component.LEFT_JUSTIFY, HARDWARE_KEY_RATIO, Component.LEFT_JUSTIFY, HARDWARE_VALUE_RATIO));
        table.addComponent(new HeaderedKeyValueComponent("Motherboard", Component.LEFT_JUSTIFY, HARDWARE_RATIO,
                primaryHardware.getMotherboard().toString(), Component.LEFT_JUSTIFY, HARDWARE_KEY_RATIO, Component.LEFT_JUSTIFY, HARDWARE_VALUE_RATIO));
        table.addComponent(new HeaderedKeyValueComponent("Storage", Component.LEFT_JUSTIFY, HARDWARE_RATIO,
                primaryHardware.getStorage().toString(), Component.LEFT_JUSTIFY, HARDWARE_KEY_RATIO, Component.LEFT_JUSTIFY, HARDWARE_VALUE_RATIO));

        // Add additional gaming hardware details for GamingComputer
        if (computer instanceof GamingComputer) {
            GamingComputer gamingComputer = (GamingComputer) computer;

            table.addComponent(new HeaderedKeyValueComponent("Cooler", Component.LEFT_JUSTIFY, HARDWARE_RATIO,
                    gamingComputer.getCooler().toString(), Component.LEFT_JUSTIFY, HARDWARE_KEY_RATIO, Component.LEFT_JUSTIFY, HARDWARE_VALUE_RATIO));
            table.addComponent(new HeaderedKeyValueComponent("Power Supply", Component.LEFT_JUSTIFY, HARDWARE_RATIO,
                    gamingComputer.getPowerSupply().toString(), Component.LEFT_JUSTIFY, HARDWARE_KEY_RATIO, Component.LEFT_JUSTIFY, HARDWARE_VALUE_RATIO));
            table.addComponent(new HeaderedKeyValueComponent("Case", Component.LEFT_JUSTIFY, HARDWARE_RATIO,
                    gamingComputer.getComputerCase().toString(), Component.LEFT_JUSTIFY, HARDWARE_KEY_RATIO, Component.LEFT_JUSTIFY, HARDWARE_VALUE_RATIO));

            GamingAdditionalHardware gamingAdditionalHardware = gamingComputer.getAdditionalHardware();
            table.addComponent(new SimpleComponent("Other", Component.LEFT_JUSTIFY));
            table.addComponent(new HeaderedKeyValueComponent("Additional Hardware", Component.LEFT_JUSTIFY, HARDWARE_RATIO,
                    gamingAdditionalHardware.toString(), Component.LEFT_JUSTIFY, HARDWARE_KEY_RATIO, Component.LEFT_JUSTIFY, HARDWARE_VALUE_RATIO));
        }

        // Add total cost details
        table.addComponent(new SimpleComponent("Total", Component.CENTER_JUSTIFY));
        String total = String.format("""
                        Cost Per Computer: + $%.2f
                        Computer Quantity: x %d
                        """, computer.totalCost(), order.getQuantity());

        // Add shipping fee details for ShippingOrder
        if (order instanceof ShippingOrder) {
            total += String.format("""
                        Shipping Fee Per Package: + $%.2f
                        Package Quantity: x %d
                        """, ShippingInfo.SHIPPING_FEE, order.getQuantity());
        }
        total += String.format("""
                        Grand Total : $%.2f""", order.totalCost());

        table.addComponent(new KeyValueComponent(String.format(total, order.getQuantity()),
                Component.LEFT_JUSTIFY, ORDER_INFORMATION_RATIO_KEYS, Component.RIGHT_JUSTIFY, ORDER_INFORMATION_RATIO_VALUES));

        // Combine inner table and add to outer table
        String innerTable = table.toString();
        outerTable.addComponent(new SimpleComponent(innerTable, Component.CENTER_JUSTIFY));

        return outerTable;
    }
}
