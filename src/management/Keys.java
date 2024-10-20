package management;

/**
 * <p>ICS4U</p>
 * <p>AY Jackson SS</p>
 *
 * <p>The Keys class defines constant strings used as keys for various properties
 * in the application. These keys are utilized for accessing and storing values
 * in data structures like maps or JSON objects.</p>
 *
 * <p>
 * The class is organized into sections, each representing a specific domain or
 * category of properties, such as order details, customer information,
 * hardware components, and additional features.
 * </p>
 *
 * <p>
 * Example usage:
 * <pre>
 * String orderIdKey = Keys.ID;
 * String customerFirstNameKey = Keys.CUSTOMER_FIRST_NAME;
 * </pre>
 * </p>
 *
 * @author Meherzad Antia
 * @author Stanley Gu
 * @author Daniel Li
 * @since 2024-01-19
 */
public class Keys {
    // Order Details
    public static final String ID = "id";
    public static final String STATUS = "orderStatus";
    public static final String CURRENT_STATUS = "currentStatus";
    public static final String STATUS_NUMBER = "statusNumber";
    public static final String ORDER_DATE = "orderDate";
    public static final String FINISH_BY_DATE = "finishByDate";
    public static final String QUANTITY = "quantity";
    public static final String TYPE = "type";

    // Customer Information
    public static final String COMPUTER = "computer";
    public static final String CUSTOMER_INFO = "customerInfo";
    public static final String CUSTOMER_FIRST_NAME = "firstName";
    public static final String CUSTOMER_LAST_NAME = "lastName";
    public static final String CUSTOMER_EMAIL = "email";
    public static final String CUSTOMER_PHONE_NUMBER = "phoneNumber";
    public static final String CUSTOMER_ADDRESS = "address";

    // Shipping Information
    public static final String SHIPPING_INFO = "shippingInfo";
    public static final String BILLING_ADDRESS = "billingAddress";
    public static final String SHIPPING_ADDRESS = "shippingAddress";
    public static final String SHIPPING_COMPANY = "shippingCompany";

    // Hardware Components
    public static final String POWER_SUPPLY = "powerSupply";
    public static final String BRAND = "brand";
    public static final String MODEL = "model";
    public static final String PRICE = "price";
    public static final String PSU_WATTAGE = "wattage";

    public static final String CASE = "computerCase";
    public static final String CASE_SIZE = "size";
    public static final String CASE_MATERIAL = "material";
    public static final String COLOUR = "colour";

    public static final String COOLER = "cooler";
    public static final String COOLER_FAN_COUNT = "fanCount";
    public static final String COOLER_RPM = "rpm";

    public static final String PRIMARY_HARDWARE = "primaryHardware";
    public static final String CPU = "CPU";
    public static final String CPU_CORES = "cores";
    public static final String CPU_THREADS = "threads";
    public static final String DOOOTZ = "doootz";
    public static final String CPU_PROCESSING_SPEED = "processingSpeed";

    public static final String GPU = "GPU";
    public static final String GPU_RAY_TRACING = "rayTracing";
    public static final String GPU_CLOCK_SPEED = "clockSpeed";
    public static final String GPU_VRAM = "VRAM";

    public static final String RAM = "RAM";
    public static final String RAM_SPEED = "speedMHz";
    public static final String RAM_MEMORY = "capacity";

    public static final String MOTHERBOARD = "motherboard";
    public static final String MOTHERBOARD_SIZE = "size";
    public static final String MOTHERBOARD_WIFI = "hasWifi";

    public static final String STORAGE = "storage";
    public static final String STORAGE_SIZE = "capacityGB";
    public static final String STORAGE_SPEED = "speedMBps";

    // Additional Features
    public static final String ADDITIONAL_HARDWARE = "additionalHardware";
    public static final String RGB_STRIPS = "hasRgbStrips";
    public static final String ADDITIONAL_STORAGE = "additionalStorage";
    public static final String EXTRA_FANS_COUNT = "extraFansCount";
    public static final String NOISE_CANCELLING_FANS = "useNoiseCancellingFans";

    public static final String PICKUP_INFO = "pickupInfo";
    public static final String PICKUP_ADDRESS = "pickupAddress";
    public static final String HAS_FINGERPRINT_SCANNER = "hasFingerprintScanner";
    public static final String HAS_TOUCHSCREEN = "hasTouchscreen";
    public static final String HAS_360_HINGE = "has360Hinge";


}
