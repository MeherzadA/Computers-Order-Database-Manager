package management;
import computer.Computer;
import computer.ComputerType;
import computer.GamingComputer;
import computer.Laptop;
import hardware.GamingAdditionalHardware;
import hardware.LaptopAdditionalHardware;
import hardware.PrimaryHardware;
import info.CustomerInfo;
import info.PickupInfo;
import info.ShippingInfo;
import order.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import parts.*;
import utils.DateUtils;

import java.io.*;
import java.util.*;


/**
 * <p>ICS4U</p>
 * <p>AY Jackson SS</p>
 *
 * <p>
 * The {@code DatabaseManager} class manages the storage and retrieval of orders in a computer store database.
 * It includes methods for adding orders, saving and loading orders to and from a JSON database file,
 * and converting JSON objects to various hardware components and order-related objects.
 * </p>
 * The class uses several constants for defining file paths and includes methods for converting JSON data
 * into various hardware components such as PowerSupply, Case, Cooler, CPU, GPU, RAM, Motherboard, and Storage.
 * Additionally, it handles order-specific details like PickupInfo and determining the input file based on
 * computer and order types.
 * </p>
 * <p>
 * The class also provides utility methods for reading JSON data from files and includes exception handling
 * for potential errors during the conversion process. It is designed to work in conjunction with other
 * classes and components to maintain and manage the computer store's order database.
 * </p>
 *
 * @author Meherzad Antia
 * @author Stanley Gu
 * @author Daniel Li
 * @since 2024-01-19
 */
public class DatabaseManager {
    /**
     * Represents the file path for the input data of laptop pickup orders.
     */
    private static final String LAPTOP_PICKUP_INPUT_FILE = "src/input/laptopPickup.json";

    /**
     * Represents the file path for the input data of laptop shipping orders.
     */
    private static final String LAPTOP_SHIPPING_INPUT_FILE = "src/input/laptopShipping.json";

    /**
     * Represents the file path for the input data of gaming pickup orders.
     */
    private static final String GAMING_PICKUP_INPUT_FILE = "src/input/gamingPickup.json";

    /**
     * Represents the file path for the input data of gaming shipping orders.
     */
    private static final String GAMING_SHIPPING_INPUT_FILE = "src/input/gamingShipping.json";

    /**
     * Represents the file path for the database storing orders.
     */
    private static final String DATABASE_PATH = "src/database/orders.json";

    /**
     * List to store order data. Concepts: Encapsulation, Array of Objects
     */
    private final List<Order> ordersList;


    /**
     * Constructs a {@code DatabaseManager} object, loading order data from the database file.
     *
     * @throws IOException if an I/O error occurs while reading the database file.
     */
    public DatabaseManager() throws IOException {
        ordersList = new ArrayList<>();
        loadOrderListFromDatabase();
    }

    /**
     * Saves the current list of orders to the database file. Concept: File Writing
     */
    public void saveOrdersListToDatabase() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(DATABASE_PATH));
            JSONArray jsonData = new JSONArray(ordersList);
            jsonData.write(bw, 2, 0);
            bw.close();
        } catch (IOException e) {
            System.err.println("An error occurred when writing to database file.");
        }
    }

    /**
     * Loads order data from the database file and populates the order list.
     *
     * @throws IOException    if an I/O error occurs while reading the database file.
     * @throws JSONException  if there is an issue parsing JSON data from the file.
     */
    public void loadOrderListFromDatabase() throws IOException, JSONException {
        JSONArray fileData = readFileAsJSONArray(DATABASE_PATH);
        ordersList.clear();

        for (int i = 0; i < fileData.length(); i++) {
            ordersList.add(loadExistingOrder(fileData.getJSONObject(i)));
        }
    }

    /**
     * Retrieves the list of all orders in the system.
     *
     * @return a list of Order objects representing all orders.
     */
    public List<Order> getAllOrders() {
        return ordersList;
    }

    /**
     * Adds a new order to the system based on the specified computer type and order type. This method
     * will load the specified file into the list of orders and re-save the orders list back into the database
     * to make sure the database and the order list is synced.
     *
     * @param computerType the type of computer for the order, specified by {@link ComputerType}.
     * @param orderType    the type of order (pickup or shipping), specified by {@link OrderType}.
     * @throws IOException if an I/O error occurs during the order creation or database update.
     */
    public void addOrder(String computerType, String orderType) throws IOException {
        String inputFile = determineOrderInputFile(computerType, orderType);
        Order order = createNewOrder(readFileAsJSONObject(inputFile)); // calls the wrapper method!

        IdManager.incrementIdValue();
        ordersList.add(order);
        saveOrdersListToDatabase();
    }

    /**
     * Creates an Order object from a JSON representation, taking into account whether it is an input or database order.
     *
     * @param json        the JSON object representing the order.
     * @param orderInput  a flag indicating whether the order is from input or database.
     * @return an {@code Order} object created from the JSON data.
     * @throws IOException           if an I/O error occurs during the order creation.
     * @throws NumberFormatException if there is an issue parsing numeric data from the JSON.
     * @throws JSONException         if there is an issue parsing JSON data.
     */
    private Order createOrderFromJson(JSONObject json, boolean orderInput) throws IOException, NumberFormatException, JSONException{
        OrderStatus orderStatus = jsonToOrderStatus(json.getJSONObject(Keys.STATUS));
        int quantity = json.getInt(Keys.QUANTITY);
        String type = json.getString(Keys.TYPE);
        CustomerInfo customerInfo = jsonToCustomerInfo(json.getJSONObject(Keys.CUSTOMER_INFO));
        Computer computer = jsonToComputer(json.getJSONObject(Keys.COMPUTER));

        int id;

        if (orderInput) { // if order is a new order from input, generate a id based on current id
            id = IdManager.getCurrentIdValue();

            // determines the type of order and calls the constructor without order dates (generated automatically based on current date)
            if (type.equals(OrderType.SHIPPING)) {
                ShippingInfo shippingInfo = jsonToShippingInfo(json.getJSONObject(Keys.SHIPPING_INFO));
                return new ShippingOrder(id, quantity, orderStatus, computer, customerInfo, shippingInfo);
            } else if (type.equals(OrderType.PICKUP)) {
                PickupInfo pickupInfo = jsonToPickupInfo(json.getJSONObject(Keys.PICKUP_INFO));
                return new PickupOrder(id, quantity, orderStatus, computer, customerInfo, pickupInfo);
            }
        } else { // if order is from database, use id, orderDate and finishByDate from the order json itself
            id = json.getInt(Keys.ID);
            Date orderDate = DateUtils.stringToDate(json.getString(Keys.ORDER_DATE));
            Date finishByDate = DateUtils.stringToDate(json.getString(Keys.FINISH_BY_DATE));

            // determines the type of order and calls the constructor with the specific order dates loaded in from the orders.json
            if (type.equals(OrderType.SHIPPING)) {
                ShippingInfo shippingInfo = jsonToShippingInfo(json.getJSONObject(Keys.SHIPPING_INFO));
                return new ShippingOrder(id, quantity, orderStatus, computer, customerInfo, orderDate, finishByDate, shippingInfo);
            } else if (type.equals(OrderType.PICKUP)) {
                PickupInfo pickupInfo = jsonToPickupInfo(json.getJSONObject(Keys.PICKUP_INFO));
                return new PickupOrder(id, quantity, orderStatus, computer, customerInfo, orderDate, finishByDate, pickupInfo);
            }
        }

        return null;
    }

    /**
     * Creates a new order from a JSON representation for input orders.
     *
     * @param json the JSON object representing the order.
     * @return an {@code Order} object created from the JSON data.
     * @throws IOException           if an I/O error occurs during the order creation.
     * @throws NumberFormatException if there is an issue parsing numeric data from the JSON.
     */
    private Order createNewOrder(JSONObject json) throws IOException, NumberFormatException {
        return createOrderFromJson(json, true);
    }

    /**
     * Loads an existing order from a JSON representation for database orders.
     *
     * @param json the JSON object representing the order.
     * @return an {@code Order} object created from the JSON data.
     * @throws IOException           if an I/O error occurs during the order creation.
     * @throws NumberFormatException if there is an issue parsing numeric data from the JSON.
     * @throws JSONException         if there is an issue parsing JSON data.
     */
    private Order loadExistingOrder(JSONObject json) throws IOException, NumberFormatException, JSONException {
        return createOrderFromJson(json, false);
    }

    /**
     * Converts a JSON object to an {@code OrderStatus} object.
     *
     * @param json the JSON object representing the order status.
     * @return an {@code OrderStatus} object created from the JSON data.
     * @throws JSONException if there is an issue parsing JSON data.
     */
    private OrderStatus jsonToOrderStatus(JSONObject json){
        String currentStatus = json.getString(Keys.CURRENT_STATUS);
        int statusNumber = json.getInt(Keys.STATUS_NUMBER);

        if (statusNumber == -1) {
            return new OrderStatus(currentStatus);
        } else {
            try{
                return OrderStatus.defaultByIndex(statusNumber);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid default status index.", e);
            }
        }
    }

    /**
     * Converts a JSON object to a {@code CustomerInfo} object.
     *
     * @param json the JSON object representing the customer information.
     * @return a {@code CustomerInfo} object created from the JSON data.
     * @throws JSONException if there is an issue parsing JSON data.
     */
    private CustomerInfo jsonToCustomerInfo(JSONObject json) throws JSONException{
        try {
            String firstName = json.getString(Keys.CUSTOMER_FIRST_NAME);
            String lastName = json.getString(Keys.CUSTOMER_LAST_NAME);
            String email = json.getString(Keys.CUSTOMER_EMAIL);
            String phoneNumber = json.getString(Keys.CUSTOMER_PHONE_NUMBER);
            String address = json.getString(Keys.CUSTOMER_ADDRESS);
            return new CustomerInfo(firstName, lastName, email, phoneNumber, address);
        } catch (JSONException e) {
            throw new JSONException("Error converting JSON to Customer Info. Check the formatting and ensure all required fields are present with the correct type.", e);
        }
    }

    /**
     * Converts a JSON object to a {@code ShippingInfo} object.
     *
     * @param json the JSON object representing the shipping information.
     * @return a {@code ShippingInfo} object created from the JSON data.
     * @throws JSONException if there is an issue parsing JSON data.
     */
    private ShippingInfo jsonToShippingInfo(JSONObject json) throws JSONException {
        try {
            String billingAddress = json.getString(Keys.BILLING_ADDRESS);
            String shippingAddress = json.getString(Keys.SHIPPING_ADDRESS);
            String shippingCompany = json.getString(Keys.SHIPPING_COMPANY);
            return new ShippingInfo(billingAddress, shippingAddress, shippingCompany);
        } catch (JSONException e) {
            throw new JSONException("Error converting JSON to Shipping Info. Check the formatting and ensure all required fields are present with the correct type.", e);
        }
    }

    /**
     * Converts a JSON object to a {@code PickupInfo} object.
     *
     * @param json the JSON object representing the pickup information.
     * @return a {@code PickupInfo} object created from the JSON data.
     * @throws JSONException if there is an issue parsing JSON data.
     */
    private PickupInfo jsonToPickupInfo(JSONObject json) throws JSONException {
        try {
            String billingAddress = json.getString(Keys.BILLING_ADDRESS);
            String pickupAddress = json.getString(Keys.PICKUP_ADDRESS);
            return new PickupInfo(billingAddress, pickupAddress);
        } catch (JSONException e) {
            throw new JSONException("Error converting JSON to Pickup Info. Check the formatting and ensure all required fields are present with the correct type.", e);
        }
    }

    /**
     * Converts a JSON object to a {@code Computer} object, handling both gaming and laptop types.
     *
     * @param json the JSON object representing the computer information.
     * @return a {@code Computer} object created from the JSON data.
     * @throws JSONException          if there is an issue parsing JSON data.
     * @throws IllegalArgumentException if the computer type is invalid.
     */
    private Computer jsonToComputer(JSONObject json) throws JSONException, IllegalArgumentException {
        String computerType;
        try {
            computerType = json.getString(Keys.TYPE);
        } catch (JSONException e) {
            throw new JSONException("Invalid computer type. Make sure it is either gaming or laptop.", e);
        }
        try {
            if (computerType.equalsIgnoreCase(ComputerType.GAMING)) {
                return jsonToGamingComputer(json);
            } else if (computerType.equalsIgnoreCase(ComputerType.LAPTOP)) {
                return jsonToLaptop(json);
            } else {
                throw new IllegalArgumentException("Invalid computer type. Make sure it is either gaming or laptop.");
            }
        } catch (JSONException e) {
            throw new JSONException("Error converting JSON to Computer: \n\t" + e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON object to a {@code GamingComputer} object.
     *
     * @param json the JSON object representing the gaming computer information.
     * @return a {@code GamingComputer} object created from the JSON data.
     * @throws JSONException if there is an issue parsing JSON data.
     */
    private GamingComputer jsonToGamingComputer(JSONObject json) throws JSONException {
        PowerSupply powerSupply = jsonToPowerSupply(json.getJSONObject(Keys.POWER_SUPPLY));
        Case computerCase = jsonToCase(json.getJSONObject(Keys.CASE));
        Cooler cooler = jsonToCooler(json.getJSONObject(Keys.COOLER));

        PrimaryHardware primaryHardware = jsonToPrimaryHardware(json.getJSONObject(Keys.PRIMARY_HARDWARE));
        GamingAdditionalHardware gamingAdditionalHardware = jsonToGamingAdditionalHardware(json.getJSONObject((Keys.ADDITIONAL_HARDWARE)));

        return new GamingComputer(primaryHardware, cooler, powerSupply,
                computerCase, gamingAdditionalHardware);
    }

    /**
     * Converts a JSON object to a {@code Laptop} object.
     *
     * @param json the JSON object representing the laptop information.
     * @return a {@code Laptop} object created from the JSON data.
     * @throws JSONException if there is an issue parsing JSON data.
     */
    private Laptop jsonToLaptop(JSONObject json) throws JSONException {
        PrimaryHardware primaryHardware = jsonToPrimaryHardware(json.getJSONObject(Keys.PRIMARY_HARDWARE));
        LaptopAdditionalHardware laptopAdditionalHardware = jsonToLaptopAdditionalHardware(json.getJSONObject((Keys.ADDITIONAL_HARDWARE)));
        return new Laptop(primaryHardware, laptopAdditionalHardware);
    }

    /**
     * Converts a JSON object to {@code GamingAdditionalHardware} object.
     *
     * @param json the JSON object representing the gaming additional hardware information.
     * @return a {@code GamingAdditionalHardware} object created from the JSON data.
     * @throws JSONException if there is an issue parsing JSON data.
     */
    private GamingAdditionalHardware jsonToGamingAdditionalHardware(JSONObject json) throws JSONException {
        Storage additionalStorage = null; // null means no additional storage

        try {
            if (!json.isNull(Keys.ADDITIONAL_STORAGE)) {
                additionalStorage = jsonToStorage(json.getJSONObject(Keys.ADDITIONAL_STORAGE));
            }

            boolean hasRgbStrips = json.getBoolean(Keys.RGB_STRIPS);
            int numExtraFans = json.getInt(Keys.EXTRA_FANS_COUNT);
            boolean areNoiseCancellingFans = json.getBoolean(Keys.NOISE_CANCELLING_FANS);

            return new GamingAdditionalHardware(additionalStorage, hasRgbStrips, areNoiseCancellingFans, numExtraFans);
        } catch (JSONException e) {
            throw new JSONException("Error converting JSON to Gaming Additional Hardware. Check the formatting and ensure all required fields are present with the correct type.", e);
        }
    }

    /**
     * Converts a JSON object to {@code LaptopAdditionalHardware} object.
     *
     * @param json the JSON object representing the laptop additional hardware information.
     * @return a {@code LaptopAdditionalHardware} object created from the JSON data.
     * @throws JSONException if there is an issue parsing JSON data.
     */
    private LaptopAdditionalHardware jsonToLaptopAdditionalHardware(JSONObject json) throws JSONException {
        Storage additionalStorage = null; // null means no additional storage

        try {
            if (!json.isNull(Keys.ADDITIONAL_STORAGE)) {
                additionalStorage = jsonToStorage(json.getJSONObject(Keys.ADDITIONAL_STORAGE));
            }
            boolean hasFingerprintScanner = json.getBoolean(Keys.HAS_FINGERPRINT_SCANNER);
            boolean hasTouchscreen = json.getBoolean(Keys.HAS_TOUCHSCREEN);
            boolean has360Hinge = json.getBoolean(Keys.HAS_360_HINGE);
            return new LaptopAdditionalHardware(additionalStorage, hasFingerprintScanner, hasTouchscreen, has360Hinge);
        } catch (JSONException e) {
            throw new JSONException("Error converting JSON to Laptop Additional Hardware. Check the formatting and ensure all required fields are present with the correct type.", e);
        }
    }

    /**
     * Converts a JSON object to {@code PrimaryHardware} object.
     *
     * @param json the JSON object representing the primary hardware information.
     * @return a {@code PrimaryHardware} object created from the JSON data.
     * @throws JSONException if there is an issue parsing JSON data.
     */
    private PrimaryHardware jsonToPrimaryHardware(JSONObject json) throws JSONException {
        try {
            CPU cpu = jsonToCPU(json.getJSONObject(Keys.CPU));
            GPU gpu = jsonToGPU(json.getJSONObject(Keys.GPU));
            RAM ram = jsonToRAM(json.getJSONObject(Keys.RAM));
            Motherboard motherboard = jsonToMotherboard(json.getJSONObject(Keys.MOTHERBOARD));
            Storage storage = jsonToStorage(json.getJSONObject(Keys.STORAGE));

            return new PrimaryHardware(storage, motherboard,ram, gpu,cpu);
        } catch (JSONException e) {
            throw new JSONException("Error converting JSON to Primary Hardware: \n\t\t" + e.getMessage(), e);
        }

    }

    /**
     * Converts a JSON object to a {@code PowerSupply} object.
     *
     * @param json the JSON object representing the power supply information.
     * @return a {@code PowerSupply} object created from the JSON data.
     * @throws JSONException if there is an issue parsing JSON data.
     */
    private PowerSupply jsonToPowerSupply(JSONObject json) throws JSONException {
        try {
            String brand = json.getString(Keys.BRAND);
            String model = json.getString(Keys.MODEL);
            double price = json.getDouble(Keys.PRICE);
            double wattage = json.getDouble(Keys.PSU_WATTAGE);

            return new PowerSupply(price, model, brand, wattage);
        } catch (JSONException e) {
            throw new JSONException("Error converting JSON to Power Supply. Check the formatting and ensure all required fields are present with the correct type.", e);
        }
    }

    /**
     * Converts a JSON object to a {@code Case} object.
     *
     * @param json the JSON object representing the computer case information.
     * @return a {@code Case} object created from the JSON data.
     * @throws JSONException if there is an issue parsing JSON data.
     */
    private Case jsonToCase(JSONObject json) throws JSONException {
        try {
            String brand = json.getString(Keys.BRAND);
            String model = json.getString(Keys.MODEL);
            double price = json.getDouble(Keys.PRICE);
            String colour = json.getString(Keys.COLOUR);
            String size = json.getString(Keys.CASE_SIZE);
            String material = json.getString(Keys.CASE_MATERIAL);

            return new Case(price, model, brand, colour, size, material);
        } catch (JSONException e) {
            throw new JSONException("Error converting JSON to Case. Check the formatting and ensure all required fields are present with the correct type.", e);
        }
    }

    /**
     * Converts a JSON object to a {@code Cooler} object.
     *
     * @param json the JSON object representing the cooler information.
     * @return a {@code Cooler} object created from the JSON data.
     * @throws JSONException if there is an issue parsing JSON data.
     */
    private Cooler jsonToCooler(JSONObject json) throws JSONException {
        try {
            String brand = json.getString(Keys.BRAND);
            String model = json.getString(Keys.MODEL);
            double price = json.getDouble(Keys.PRICE);
            String color = json.getString(Keys.COLOUR);
            String type = json.getString(Keys.TYPE);
            int rpm = json.getInt(Keys.COOLER_RPM);
            int fanCount = json.getInt(Keys.COOLER_FAN_COUNT);
            return new Cooler(price, model, brand, color, type, fanCount, rpm);
        } catch (JSONException e) {
            throw new JSONException("Error converting JSON to Cooler. Check the formatting and ensure all required fields are present with the correct type.", e);
        }
    }

    // The following parts are all part of primary hardware (meaning they are found in both gaming PCs and laptops)
    /**
     * Converts a JSON object to a {@code CPU} object.
     *
     * @param json the JSON object representing the CPU information.
     * @return a {@code CPU} object created from the JSON data.
     * @throws JSONException if there is an issue parsing JSON data.
     */
    private CPU jsonToCPU(JSONObject json) throws JSONException {
        try{
            String brand = json.getString(Keys.BRAND);
            String model = json.getString(Keys.MODEL);
            int cores = json.getInt(Keys.CPU_CORES);
            int threads = json.getInt(Keys.CPU_THREADS);
            double price = json.getDouble(Keys.PRICE);
            double processingSpeed = json.getDouble(Keys.CPU_PROCESSING_SPEED);
            return new CPU(price, model, brand, cores, threads, processingSpeed);
        } catch (JSONException e) {
            throw new JSONException("Error converting JSON to CPU. Check the formatting and ensure all required fields are present with the correct type.", e);
        }
    }

    /**
     * Converts a JSON object to a {@code GPU} object.
     *
     * @param json the JSON object representing the GPU information.
     * @return a {@code GPU} object created from the JSON data.
     * @throws JSONException if there is an issue parsing JSON data.
     */
    private GPU jsonToGPU(JSONObject json) throws JSONException {

        try{
            String brand = json.getString(Keys.BRAND);
            String model = json.getString(Keys.MODEL);
            double price = json.getDouble(Keys.PRICE);
            boolean rayTracing = json.getBoolean(Keys.GPU_RAY_TRACING);
            double clockSpeed = json.getDouble(Keys.GPU_CLOCK_SPEED);
            int vRam = json.getInt(Keys.GPU_VRAM);
            return new GPU(price, model, brand, rayTracing, clockSpeed, vRam);
        } catch (JSONException e) {
            throw new JSONException("Error converting JSON to GPU. Check the formatting and ensure all required fields are present with the correct type.", e);
        }
    }

    /**
     * Converts a JSON object to a {@code RAM} object.
     *
     * @param json the JSON object representing the RAM information.
     * @return a {@code RAM} object created from the JSON data.
     * @throws JSONException if there is an issue parsing JSON data.
     */
    private RAM jsonToRAM(JSONObject json) throws JSONException {
        try{
            String brand = json.getString(Keys.BRAND);
            String model = json.getString(Keys.MODEL);
            double price = json.getDouble(Keys.PRICE);
            int memory = json.getInt(Keys.RAM_MEMORY);
            String type = json.getString(Keys.TYPE);
            double speed = json.getDouble(Keys.RAM_SPEED);
            return new RAM(price, model, brand, type, memory, speed);
        } catch (JSONException e) {
            throw new JSONException("Error converting JSON to RAM. Check the formatting and ensure all required fields are present with the correct type.", e);
        }
    }

    /**
     * Converts a JSON object to a {@code Motherboard} object.
     *
     * @param json the JSON object representing the motherboard information.
     * @return a {@code Motherboard} object created from the JSON data.
     * @throws JSONException if there is an issue parsing JSON data.
     */
    private Motherboard jsonToMotherboard(JSONObject json) throws JSONException {
        try {
            String brand = json.getString(Keys.BRAND);
            String model = json.getString(Keys.MODEL);
            double price = json.getDouble(Keys.PRICE);
            String size = json.getString(Keys.MOTHERBOARD_SIZE);
            boolean hasWifi = json.getBoolean(Keys.MOTHERBOARD_WIFI);
            return new Motherboard(price, model, brand, size, hasWifi);
        }
        catch (JSONException e) {
            throw new JSONException("Error converting JSON to Motherboard. Check the formatting and ensure all required fields are present with the correct type.", e);
        }
    }

    /**
     * Converts a JSON object to a {@code Storage} object.
     *
     * @param json the JSON object representing the storage information.
     * @return a {@code Storage} object created from the JSON data.
     * @throws JSONException if there is an issue parsing JSON data.
     */
    private Storage jsonToStorage(JSONObject json) throws JSONException {
        try{
            String brand = json.getString(Keys.BRAND);
            String model = json.getString(Keys.MODEL);
            double price = json.getDouble(Keys.PRICE);
            double storageSize = json.getDouble(Keys.STORAGE_SIZE);
            double speedMBps = json.getDouble(Keys.STORAGE_SPEED);
            return new Storage(price, model, brand, speedMBps, storageSize);
        }

        catch (JSONException e) {
            throw new JSONException("Error converting JSON to Storage. Check the formatting and ensure all required fields are present with the correct type.", e);
        }
    }

    /**
     * Determines the input file based on the computer type and order type.
     *
     * @param computerType the type of computer (gaming or laptop), specified by {@link ComputerType}.
     * @param orderType    the type of order (pickup or shipping), specified by {@link OrderType}.
     * @return the input file path determined by the computer and order types.
     */
    private String determineOrderInputFile(String computerType, String orderType) {
        if (computerType.equals(ComputerType.GAMING) && orderType.equals(OrderType.SHIPPING))
            return GAMING_SHIPPING_INPUT_FILE;
        if (computerType.equals(ComputerType.GAMING) && orderType.equals(OrderType.PICKUP))
            return GAMING_PICKUP_INPUT_FILE;
        if (computerType.equals(ComputerType.LAPTOP) && orderType.equals(OrderType.SHIPPING))
            return LAPTOP_SHIPPING_INPUT_FILE;
        if (computerType.equals(ComputerType.LAPTOP) && orderType.equals(OrderType.PICKUP))
            return LAPTOP_PICKUP_INPUT_FILE;
        return "";
    }

    /**
     * Reads the content of a file and returns it as a JSON object.
     *
     * @param file the path of the file to be read.
     * @return a JSON object representing the file content.
     * @throws IOException if an I/O error occurs while reading the file.
     */
    private JSONObject readFileAsJSONObject(String file) throws IOException {
        return new JSONObject(readAllLines(file));
    }

    /**
     * Reads the content of a file and returns it as a JSON array.
     *
     * @param file the path of the file to be read.
     * @return a JSON array representing the file content.
     * @throws IOException if an I/O error occurs while reading the file.
     */
    private JSONArray readFileAsJSONArray(String file) throws IOException {
        String result = readAllLines(file);
        return new JSONArray(result);
    }

    /**
     * Reads all lines from a file and returns them as a single string.
     * Concept: File Reading
     * @param file the path of the file to be read.
     * @return a string containing all lines of the file.
     * @throws IOException if an I/O error occurs while reading the file.
     */
    private String readAllLines(String file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        StringBuilder data = new StringBuilder();

        String line = br.readLine();
        while (line != null) {
            data.append(line);
            data.append('\n');
            line = br.readLine();
        }

        return data.toString();
    }
}
