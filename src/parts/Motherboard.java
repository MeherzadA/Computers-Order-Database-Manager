package parts;

/**
 * <p>ICS4U</p>
 * <p>AY Jackson SS</p>
 *
 * <p>The {@code Motherboard} class represents a motherboard, which is a type of part in a computer system.
 * It extends the {@code Part} class and includes additional features related to size and Wi-Fi capability.</p>
 *
 * <p>The class includes a constructor to initialize the price, model, brand, size, and Wi-Fi capability of the motherboard.
 * It also provides getter and setter methods for accessing and modifying the size and Wi-Fi capability.</p>
 *
 * <p>The {@code toString} method is overridden to provide a formatted string representation of the motherboard,
 * including its general information, size, and Wi-Fi capability.</p>
 * <p>Concept: Inheritance</p>
 * @author Meherzad Antia
 * @author Stanley Gu
 * @author Daniel Li
 * @since 2024-01-15
 */
public class Motherboard extends Part {

    /**
     * The size of the motherboard. Concept: Encapsulation
     */
    private String size;

    /**
     * Indicates whether the motherboard has Wi-Fi capability. Concept: Encapsulation
     */
    private boolean hasWifi;

    /**
     * Constructs a new {@code Motherboard} object with the specified price, model, brand, size, and Wi-Fi capability.
     *
     * @param price   the price of the motherboard
     * @param model   the model of the motherboard
     * @param brand   the brand of the motherboard
     * @param size    the size of the motherboard
     * @param hasWifi {@code true} if the motherboard has Wi-Fi capability, {@code false} otherwise
     */
    public Motherboard(double price, String model, String brand, String size, boolean hasWifi) {
        super(price, model, brand);
        this.size = size;
        this.hasWifi = hasWifi;
    }

    /**
     * Gets the size of the motherboard.
     *
     * @return the size of the motherboard
     */
    public String getSize() {
        return size;
    }

    /**
     * Checks if the motherboard has Wi-Fi capability.
     *
     * @return {@code true} if the motherboard has Wi-Fi capability, {@code false} otherwise
     */
    public boolean isHasWifi() { // prefixing method with is for standard java boolean getter naming convention (used when object is converted to json)
        return hasWifi;
    }

    /**
     * Sets the size of the motherboard.
     *
     * @param size the new size to set
     */
    public void setSize(String size) {
        this.size = size;
    }

    /**
     * Sets the Wi-Fi capability of the motherboard.
     *
     * @param hasWifi {@code true} if the motherboard has Wi-Fi capability, {@code false} otherwise
     */
    public void setWifi(boolean hasWifi) {
        this.hasWifi = hasWifi;
    }

    /**
     * Returns a formatted string representation of the motherboard, including its general information, size, and Wi-Fi capability.
     * Concept: Polymorphism
     * @return a string representation of the motherboard
     */
    @Override
    public String toString() {
        return String.format("""
                %s
                Size: %s
                Wi-Fi Antenna: %b""", super.toString(), size, hasWifi);
    }
}
