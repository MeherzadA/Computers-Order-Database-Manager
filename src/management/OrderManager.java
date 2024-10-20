package management;

import computer.ComputerType;
import computer.GamingComputer;
import computer.Laptop;
import hardware.AdditionalHardware;
import hardware.GamingAdditionalHardware;
import hardware.LaptopAdditionalHardware;
import info.CustomerInfo;
import order.Order;
import order.OrderStatus;
import order.ShippingOrder;
import order.PickupOrder;
import org.json.JSONException;
import parts.*;
import utils.ConsoleUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>ICS4U</p>
 * <p>AY Jackson SS</p>
 *
 * The OrderManager class is responsible for managing orders in the system.
 * It interacts with the DatabaseManager to retrieve and persist order data.
 * This class provides various methods for manipulating orders, such as adding,
 * updating, deleting, and retrieving orders based on different criteria.
 *
 * <p>
 * The order list is initialized by loading data from the database during
 * the construction of the OrderManager object. Users can perform operations
 * on orders, including viewing customer information, updating order status,
 * updating hardware components, sorting orders, and more.
 * </p>
 *
 * <p>
 * Example usage:
 * <pre>
 * OrderManager orderManager = new OrderManager();
 * orderManager.addOrder(ComputerType.GAMING, OrderType.SHIPPING);
 * orderManager.updateOrderStatus(10000, 1); // In Progress
 * orderManager.printAllOrders();
 * </pre>
 * </p>
 *
 * @author Meherzad Antia
 * @author Stanley Gu
 * @author Daniel Li
 * @since 2024-01-19
 */
public class OrderManager {
    // Constants for different order types
    private static final int ALL_CURRENT_ORDERS = 0;
    private static final int ALL_CUSTOM_ORDERS = 1;
    private static final int ALL_COMPLETED_ORDERS = 2;
    private static final int ALL_CANCELLED_ORDERS = 3;
    private static final int ALL_PICKUP_ORDERS = 4;
    private static final int ALL_SHIPPING_ORDERS = 5;
    private static final int ALL_GAMING_COMPUTER_ORDERS = 6;
    private static final int ALL_LAPTOP_ORDERS = 7;


    /**
     * DatabaseManager instance field to handle reading and writing to database (orders.json)
     * Concept: Encapsulation
     */
    private DatabaseManager databaseManager;
    /**
     * List of orders that is synced with the ordersList in the DatabaseManager instance (same memory address)
     * for ease of saving.
     * Concepts: Encapsulation, Array of Objects
     */
    private final List<Order> ordersList;

    /**
     * Constructs an OrderManager and initializes the order list by loading data from the database.
     *
     * @throws IOException If an I/O error occurs during database initialization.
     * @apiNote The {@code ordersList} is synced up to the database manager through the line
     * {@code ordersList = databaseManager.getAllOrders(); }, meaning any changes performed on {@code ordersList}
     * will automatically reflect in {@code DatabaseManager}'s ordersList
     */
    public OrderManager() throws IOException {
        databaseManager = new DatabaseManager();
        ordersList = databaseManager.getAllOrders();
        idificateIt();
    }

    /**
     * Retrieves the associated {@code DatabaseManager}.
     *
     * @return The {@code DatabaseManager} instance.
     */
    public DatabaseManager getDatabaseManager() {
        return databaseManager;
    }

    /**
     * Retrieves the list of orders.
     *
     * @return The list of orders.
     */
    public List<Order> getOrdersList() {
        return ordersList;
    }

    /**
     * Sets the associated {@code DatabaseManager}
     *
     * @param databaseManager The new {@code DatabaseManager} to set.
     */
    public void setDatabaseManager(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    /**
     * Sets the associated {@code orderList}
     *
     * @param ordersList The new {@code orderList} to set.
     * @apiNote This method does not directly set the {@code this.orderList} but instead clears the
     * {@code this.orderList} and adds all the items from the new {@code orderList} to prevent
     * the sync between {@code DatabaseManager} and {@code OrderManager} from breaking
     */
    public void setOrdersList(List<Order> ordersList) {
        this.ordersList.clear();
        this.ordersList.addAll(ordersList);
        saveOrdersToDatabase();
    }

    /**
     * Retrieves the number of orders in the system.
     *
     * @return The number of orders.
     */
    public int ordersCount() {
        return ordersList.size();
    }

    /**
     * Views the customer information for the specified order.
     *
     * @param id The identifier of the order.
     */
    public boolean viewCustomerInfo(int id){
        Order order = findOrderById(id);

        if (order != null) {
            ConsoleUtils.boxCustomerInfo(order.getCustomerInfo()).render();
            return true;
        }
        return false;
    }


    /**
     * Finds and returns a list of orders that match the specified additional hardware and percentage criteria.
     *
     * @param otherAdditionalHardware The additional hardware to match against.
     * @param percent The percentage match threshold.
     * @return List of orders matching the given criteria.
     */
    public List<Order> findMatchingOrdersByAdditionalHardwareAndPercent(AdditionalHardware otherAdditionalHardware, double percent) {

        List<Order> matchedOrders = new ArrayList<>();

        boolean isGaming = otherAdditionalHardware instanceof GamingAdditionalHardware;

        for (Order currOrder : ordersList) {
            if (isGaming && currOrder.getComputer().getType().equalsIgnoreCase(ComputerType.GAMING)) {
                GamingComputer currGamingComputer = (GamingComputer) currOrder.getComputer();

                if (currGamingComputer.getAdditionalHardware().getAdditionalStorage() != null) {
                    GamingAdditionalHardware currGamingAdditionalHardware = currGamingComputer.getAdditionalHardware();
                    if (currGamingAdditionalHardware.matchSpecs(otherAdditionalHardware, percent)) {
                        matchedOrders.add(currOrder);
                    }
                }
            } else if (!isGaming && currOrder.getComputer().getType().equalsIgnoreCase(ComputerType.LAPTOP)) {
                Laptop currLaptop = (Laptop) currOrder.getComputer();

                if (currLaptop.getAdditionalHardware().getAdditionalStorage() != null) {
                    LaptopAdditionalHardware currLaptopAdditionalHardware = currLaptop.getAdditionalHardware();
                    if (currLaptopAdditionalHardware.matchSpecs(otherAdditionalHardware, percent)) {
                        matchedOrders.add(currOrder);
                    }
                }
            }
        }
        return matchedOrders;
    }

    /**
     * Retrieves a list of unique customer information from all orders.
     *
     * @return List of unique customer information.
     */
    public List<CustomerInfo> viewAllUniqueCustomerInfo() {

        List<String> fullCustomerNames = new ArrayList<>();
        List<CustomerInfo> customerInfoList = new ArrayList<>();

        for (Order currOrder : ordersList) {
            CustomerInfo customerInfo = currOrder.getCustomerInfo();
            String firstName = customerInfo.getFirstName();
            String lastname = customerInfo.getLastName();
            String fullName = firstName + lastname;

            if (!fullCustomerNames.contains(fullName)) {
                fullCustomerNames.add(fullName);
                customerInfoList.add(customerInfo);
            }
        }
        return customerInfoList;
    }

    /**
     * Prints customer information based on the provided first name and last name.
     *
     * @param firstName The first name of the customer.
     * @param lastName  The last name of the customer.
     * @return {@code true} if customer information is found and printed, {@code false} otherwise.
     */
    public boolean printCustomerInfoByFullName(String firstName, String lastName){
        for (Order currOrder : ordersList) {
            CustomerInfo customerInfo = currOrder.getCustomerInfo();

            if (customerInfo.getFirstName().equalsIgnoreCase(firstName) && customerInfo.getLastName().equalsIgnoreCase(lastName)) {
                ConsoleUtils.boxCustomerInfo(customerInfo).render();
                return true;
            }
        }
        return false;
    }

    /**
     * Sorts the orders in the list by order status and finish by date in ascending order.
     * Uses the selection sort algorithm.
     */
    public void sortOrdersByStatusAndMostUrgent() {
        // uses selection sort
        for (int i = 0; i < ordersList.size(); i++) {
            int minIndex = i;

            for (int j = i + 1; j < ordersList.size(); j++) {
                Order currOrder = ordersList.get(j);
                Order minOrder = ordersList.get(minIndex);

                if(currOrder.compareToOrderStatus(minOrder) < 0){
                    minIndex = j;
                } else if (currOrder.compareToOrderStatus(minOrder) == 0 && currOrder.compareToFinishByDate(minOrder) < 0){
                    minIndex = j;
                }
            }
            Order temp = ordersList.get(i);
            ordersList.set(i, ordersList.get(minIndex));
            ordersList.set(minIndex, temp);
        }
    }

    /**
     * Sorts the orders in the list by order ID in ascending order. Uses the insertion sort algorithm.
     */
    public void sortOrdersByID() {

        for (int i = 0; i < ordersList.size(); i++) {

            int j = i;
            Order order = ordersList.get(i);
            while (j > 0 && order.compareToId(ordersList.get(j - 1)) < 0) {
                ordersList.set(j, ordersList.get(j - 1));
                j--;
            }

            ordersList.set(j, order);
        }
    }

    /**
     * Adds a new order to the system.
     *
     * @param computerType The type of computer for the order, specified by {@link computer.ComputerType}.
     * @param orderType    The type of order, specified by {@link order.OrderType}.
     * @throws IOException If an I/O error occurs while adding the order.
     */
    public void addOrder(String computerType, String orderType) throws IOException {
        databaseManager.addOrder(computerType, orderType);
    }

    /**
     * Prints details of all orders in the list to the console.
     */
    public void printAllOrders(){
        printOrderList(ordersList);
    }

    /**
     * Updates the status of an order with the provided status number.
     *
     * @param order        The order with its status to be updated.
     * @param statusNumber The new status number.
     * @return {@code true} if the order is found and updated, {@code false} otherwise.
     */
    public boolean updateOrderStatus(Order order, int statusNumber){
        if (order != null) {
            order.updateStatus(statusNumber);
            saveOrdersToDatabase();
            return true;
        }
        return false;
    }

    /**
     * Updates the status of an order with the provided custom status.
     *
     * @param order        The order with its status to be updated.
     * @param customStatus The new custom status.
     * @return {@code true} if the order is found and updated, {@code false} otherwise.
     */
    public boolean updateOrderStatus(Order order, String customStatus){
        if (order != null) {
            order.updateStatus(new OrderStatus(customStatus));
            saveOrdersToDatabase();
            return true;
        }
        return false;
    }

    /**
     * Updates the CPU details of the order identified by its ID.
     *
     * @param order            The order with the CPU to be updated.
     * @param brand            The brand of the CPU.
     * @param model            The model of the CPU.
     * @param price            The price of the CPU.
     * @param cores            The number of CPU cores.
     * @param threads          The number of CPU threads.
     * @param processingSpeed The processing speed of the CPU.
     * @return {@code true} if the order is found and CPU details are updated, {@code false} otherwise.
     */
    public boolean updateOrderCPU(Order order, String brand, String model, double price, int cores, int threads, double processingSpeed) {

        if (order != null) {
            order.getComputer().getPrimaryHardware().setCPU(new CPU(price, model, brand, cores, threads, processingSpeed));
            saveOrdersToDatabase();
            return true;
        }
        return false;
    }

    /**
     * Updates the GPU details of the order identified by its ID.
     *
     * @param order        The order with the GPU to be updated.
     * @param brand        The brand of the GPU.
     * @param model        The model of the GPU.
     * @param price        The price of the GPU.
     * @param rayTracing   Indicates whether the GPU supports ray tracing.
     * @param clockSpeed   The clock speed of the GPU.
     * @param VRAM         The VRAM (Video RAM) capacity of the GPU.
     * @return {@code true} if the order is found and GPU details are updated, {@code false} otherwise.
     */
    public boolean updateOrderGPU(Order order, String brand, String model, double price, boolean rayTracing, double clockSpeed, int VRAM) {

        if (order != null) {
            order.getComputer().getPrimaryHardware().setGPU(new GPU(price, model, brand, rayTracing, clockSpeed, VRAM));
            saveOrdersToDatabase();
            return true;
        }
        return false;
    }

    /**
     * Updates the RAM details of the order identified by its ID.
     *
     * @param order    The order with the RAM to be updated.
     * @param brand    The brand of the RAM.
     * @param model    The model of the RAM.
     * @param price    The price of the RAM.
     * @param type     The type of RAM.
     * @param capacity The capacity of the RAM.
     * @param speedMHz The speed of the RAM in megahertz.
     * @return {@code true} if the order is found and RAM details are updated, {@code false} otherwise.
     */
    public boolean updateOrderRAM(Order order, String brand, String model, double price, String type, int capacity, int speedMHz) {

        if (order != null) {
            order.getComputer().getPrimaryHardware().setRAM(new RAM(price, model, brand, type, capacity, speedMHz));
            saveOrdersToDatabase();
            return true;
        }
        return false;
    }

    /**
     * Updates the motherboard details of the order identified by its ID.
     *
     * @param order   The order with the Motherboard to be updated.
     * @param brand   The brand of the motherboard.
     * @param model   The model of the motherboard.
     * @param price   The price of the motherboard.
     * @param size    The size of the motherboard.
     * @param hasWifi Indicates whether the motherboard has Wi-Fi capability.
     * @return {@code true} if the order is found and motherboard details are updated, {@code false} otherwise.
     */
    public boolean updateOrderMotherboard(Order order, String brand, String model, double price, String size, boolean hasWifi){

        if (order != null) {
            order.getComputer().getPrimaryHardware().setMotherboard(new Motherboard(price, model, brand, size, hasWifi));
            saveOrdersToDatabase();
            return true;
        }
        return false;
    }

    /**
     * Updates the storage details of the order identified by its ID.
     *
     * @param order       The order with the Storage to be updated.
     * @param brand       The brand of the storage device.
     * @param model       The model of the storage device.
     * @param price       The price of the storage device.
     * @param capacityGB  The capacity of the storage device in gigabytes.
     * @param speedMBps   The speed of the storage device in megabytes per second.
     * @return {@code true} if the order is found and storage details are updated, {@code false} otherwise.
     */
    public boolean updateOrderStorage(Order order, String brand, String model, double price, int capacityGB, int speedMBps){

        if (order != null) {
            order.getComputer().getPrimaryHardware().setStorage(new Storage(price, model, brand, speedMBps, capacityGB));
            saveOrdersToDatabase();
            return true;
        }
        return false;
    }

    /**
     * Deletes the order identified by its ID from the system.
     *
     * @param id The ID of the order to be deleted.
     * @return {@code true} if the order is found and deleted, {@code false} otherwise.
     */
    public boolean deleteOrder(int id) {

        int orderToDeleteIndex = binarySearchIndexById(id);

        if(orderToDeleteIndex != -1){
            ordersList.remove(orderToDeleteIndex);
            sortOrdersByID();
            saveOrdersToDatabase();
            return true;
        }

        return false;
    }

    /**
     * Calculates the total cost of an order identified by its ID.
     *
     * @param id The ID of the order.
     * @return The total cost of the order, or -1 if the order is not found.
     */
    public double orderTotalCost(int id) {
        Order order = findOrderById(id);
        if(order != null){
            return order.totalCost();
        }
        return -1;

    }

    /**
     * Calculates the expected revenue based on the total cost of all not started and in progress orders.
     *
     * @return The expected revenue.
     */
    public double calculateExpectedRevenue() {
        double expectedRevenue = 0;

        List<Order> currentOrders = allCurrentOrders();
        int size = currentOrders.size();

        for (Order currentOrder : currentOrders) {
            expectedRevenue += currentOrder.totalCost();
        }

        return expectedRevenue;
    }


    /**
     * Calculates the average price of all orders (current, completed, and custom).
     *
     * @return The average price of all orders.
     */
    public double calculateAveragePriceOfAllOrders() {
        double priceSum = 0;
        int count = 0;

        List<Order> currentOrders = allCurrentOrders();
        List<Order> completedOrders = allCompletedOrders();
        List<Order> customOrders = allCustomOrders();

        for (Order currentOrder : currentOrders) {
            priceSum += currentOrder.totalCost();
            count++;
        }
        for (Order completedOrder : completedOrders) {
            priceSum += completedOrder.totalCost();
            count++;
        }
        for (Order customOrder : customOrders) {
            priceSum += customOrder.totalCost();
            count++;
        }

        return priceSum / count;
    }

    /**
     * Filters orders based on their order status.
     *
     * @param status The order status to filter by.
     * @return A list of orders with the specified status.
     */
    public List<Order> filterOrdersByStatus(OrderStatus status) {
        List<Order> filteredList = new ArrayList<>();

        for (Order order : ordersList) {
            if (order.getOrderStatus().equals(status)) {
                filteredList.add(order);
            }
        }

        return filteredList;
    }

    /**
     * Prints a list of orders based on the given order type.
     *
     * @param orderType The type of orders to print.
     */
    public void printOrdersBy(int orderType) {
        List<Order> toPrint = switch (orderType) {
            case ALL_CURRENT_ORDERS -> allCurrentOrders();
            case ALL_CUSTOM_ORDERS -> allCustomOrders();
            case ALL_COMPLETED_ORDERS -> allCompletedOrders();
            case ALL_CANCELLED_ORDERS -> allCancelledOrders();
            case ALL_PICKUP_ORDERS -> allPickupOrders();
            case ALL_SHIPPING_ORDERS -> allShippingOrders();
            case ALL_GAMING_COMPUTER_ORDERS -> allGamingComputerOrders();
            case ALL_LAPTOP_ORDERS -> allLaptopOrders();
            default -> new ArrayList<>();
        };

        printOrderList(toPrint);
    }

    /**
     * Retrieves a list of all current orders.
     *
     * @return A list of all current orders.
     */
    public List<Order> allCurrentOrders() {
        List<Order> currentOrders = new ArrayList<>();

        for (Order order : ordersList) {
            if (order.getOrderStatus().isCurrent()) {
                currentOrders.add(order);
            }
        }

        return currentOrders;
    }

    /**
     * Retrieves a list of all custom orders.
     *
     * @return A list of all custom orders.
     */
    public List<Order> allCustomOrders() {
        List<Order> customOrders = new ArrayList<>();
        for (Order order : ordersList) {
            if (order.getOrderStatus().isCustom()) customOrders.add(order);
        }
        return customOrders;
    }

    /**
     * Retrieves a list of all completed orders.
     *
     * @return A list of all completed orders.
     */
    public List<Order> allCompletedOrders() {
        List<Order> completedOrders = new ArrayList<>();

        for (Order currOrder : ordersList) {
            if (currOrder.getOrderStatus().equals(OrderStatus.COMPLETED)) {
                completedOrders.add(currOrder);
            }
        }

        return completedOrders;
    }

    /**
     * Retrieves a list of all cancelled orders.
     *
     * @return A list of all cancelled orders.
     */
    public List<Order> allCancelledOrders() {

        List<Order> cancelledOrders = new ArrayList<>();

        for (Order currOrder : ordersList) {
            if (currOrder.getOrderStatus().equals(OrderStatus.CANCELLED)) {
                cancelledOrders.add(currOrder);
            }
        }
        return cancelledOrders;
    }

    /**
     * Retrieves a list of all pickup orders.
     *
     * @return A list of all pickup orders.
     */
    public List<Order> allPickupOrders() {
        List<Order> pickupOrders = new ArrayList<>();

        for (Order currOrder : ordersList) {
            if (currOrder instanceof PickupOrder) {
                pickupOrders.add(currOrder);
            }
        }

        return pickupOrders;
    }

    /**
     * Retrieves a list of all shipping orders.
     *
     * @return A list of all shipping orders.
     */
    public List<Order> allShippingOrders() {
        List<Order> shippingOrders = new ArrayList<>();

        for (Order currOrder : ordersList) {
            if (currOrder instanceof ShippingOrder) {
                shippingOrders.add(currOrder);
            }
        }

        return shippingOrders;
    }

    /**
     * Retrieves a list of all gaming computer orders.
     *
     * @return A list of all gaming computer orders.
     */
    public List<Order> allGamingComputerOrders() {
        List<Order> gamingComputerOrders = new ArrayList<>();

        for (Order currOrder : ordersList) {
            if (currOrder.getComputer() instanceof GamingComputer) {
                gamingComputerOrders.add(currOrder);
            }
        }

        return gamingComputerOrders;
    }

    /**
     * Retrieves a list of all laptop orders.
     *
     * @return A list of all laptop orders.
     */
    public List<Order> allLaptopOrders() {
        List<Order> laptopOrders = new ArrayList<>();

        for (Order currOrder : ordersList) {
            if (currOrder.getComputer() instanceof Laptop) {
                laptopOrders.add(currOrder);
            }
        }

        return laptopOrders;
    }

    /**
     * Performs a binary search to find the index of an order in the list based on its ID.
     * Concepts: Binary Search, Recursion
     * @param id     The ID of the order to search for.
     * @param bottom The starting index for the search.
     * @param top    The ending index for the search.
     * @return The index of the order if found, otherwise -1.
     */
    private int binarySearchIndexById(int id, int bottom, int top) {
        int middle = bottom + (top - bottom) / 2;

        if (bottom <= top) {
            if (id == ordersList.get(middle).getId()) {
                return middle;
            } else if (id < ordersList.get(middle).getId()) {
                return binarySearchIndexById(id, bottom, middle - 1);
            } else {
                return binarySearchIndexById(id, middle + 1, top);
            }
        }

        return -1;
    }

    /**
     * Wrapper method for the binarySearchIndexById method. Sorts the orders by ID before initiating the search.
     *
     * @param id The ID of the order to search for.
     * @return The index of the order if found, otherwise -1.
     */
    private int binarySearchIndexById(int id) { // wrapper method
        sortOrdersByID();
        return binarySearchIndexById(id, 0, ordersList.size() - 1);
    }

    /**
     * Finds and retrieves an order based on its ID using binary search.
     *
     * @param id The ID of the order to find.
     * @return The order with the specified ID, or null if not found.
     */
    public Order findOrderById(int id){
        int index = binarySearchIndexById(id);

        if(index != -1){
            return ordersList.get(index);
        }
        return null;
    }

    /**
     * Finds orders based on the CPU and GPU brands of their primary hardware.
     * Concept: Linear Search
     * @param cpuBrand The brand of the CPU to match.
     * @param gpuBrand The brand of the GPU to match.
     * @return A list of orders matching the specified CPU and GPU brands.
     */
    public List<Order> findOrdersByCPUAndGPUBrand(String cpuBrand, String gpuBrand){

        List<Order> orders = new ArrayList<>();

        for (Order order : ordersList) {
            if (order.getComputer().getPrimaryHardware().matchCpuBrand(cpuBrand) && order.getComputer().getPrimaryHardware().matchGpuBrand(gpuBrand)) {
                orders.add(order);
            }
        }
        return orders;
    }


    /**
     * Prints a list of customer information to the console.
     *
     * @param customerInfos The list of customer information to print.
     */
    public static void printCustomerInfoList(List<CustomerInfo> customerInfos){
        for (CustomerInfo customerInfo : customerInfos) {
            ConsoleUtils.boxCustomerInfo(customerInfo).render();
        }
    }

    /**
     * Prints a list of orders to the console.
     *
     * @param orders The list of orders to print.
     */
    public static void printOrderList(List<Order> orders) {
        for (int i = 0; i < orders.size(); i++) {
            Order order = orders.get(i);
            ConsoleUtils.boxOrder(order).render();
        }
    }

    /**
     * Saves the list of orders to the database.
     */
    public void saveOrdersToDatabase() {
        databaseManager.saveOrdersListToDatabase();
    }


    /**
     * Used to maintain unique IDs to orders in the system based on the current state of the orders list.
     * If the orders list is empty, it resets the current ID value to the starting ID value.
     * Otherwise, it sets the current ID value to the next available ID based on the last order's ID.
     *
     * @throws IOException If an I/O error occurs while updating the ID value.
     */
    public void idificateIt() throws IOException {
        if (ordersList.isEmpty()) {
            IdManager.resetCurrentIDValue();
        } else {

//            IdManager.setCurrentIDValue(ordersList.getLast().getId() + 1);
        }
    }

    /**
     * Loads the list of orders from the database. This method will automatically sort
     * the newly loaded list by ID and update {@link IdManager}'s current id value (as well as
     * the file associated with it) to the highest ID value in the newly loaded list. This prevents
     * any ID duplicates and makes sure the ID value is synced to the new lists of orders
     *
     * @throws IOException   If an I/O error occurs during loading.
     * @throws JSONException If there is an issue with JSON data format during loading.
     */
    public void loadOrdersFromDatabase() throws IOException, JSONException {
        databaseManager.loadOrderListFromDatabase();
        sortOrdersByID();
        idificateIt();
    }

    /**
     * Checks if there are no orders in the orders list.
     * @return {@code true} if and only if {@code orders.length == 0}, otherwise false
     */
    public boolean isEmptyOrders() {
        return ordersCount() == 0;
    }
}
