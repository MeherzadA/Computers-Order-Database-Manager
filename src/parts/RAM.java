package parts;

/**
 * <p>ICS4U</p>
 * <p>AY Jackson SS</p>
 *
 * <p>The {@code RAM} class represents a random-access memory (RAM) module, which is a type of part in a computer system.
 * It extends the {@code Part} class and includes additional features related to type, memory capacity, and speed.</p>
 *
 * <p>The class includes a constructor to initialize the price, model, brand, type, memory capacity, and speed of the RAM module.
 * It also provides getter and setter methods for accessing and modifying these features.</p>
 *
 * <p>The {@code toString} method is overridden to provide a formatted string representation of the RAM module,
 * including its general information, type, memory capacity, and speed.</p>
 * <p>Concept: Inheritance</p>
 * @author Meherzad Antia
 * @author Stanley Gu
 * @author Daniel Li
 * @since 2024-01-15
 */
public class RAM extends Part {

    /**
     * The type of the RAM module. Concept: Encapsulation
     */
    private String type;

    /**
     * The memory capacity of the RAM module in gigabytes (GB). Concept: Encapsulation
     */
    private int capacity;

    /**
     * The speed of the RAM module in megahertz (MHz). Concept: Encapsulation
     */
    private double speedMHz;

    /**
     * Constructs a new {@code RAM} object with the specified price, model, brand, type, memory capacity, and speed.
     *
     * @param price   the price of the RAM module
     * @param model   the model of the RAM module
     * @param brand   the brand of the RAM module
     * @param type    the type of the RAM module
     * @param capacity  the memory capacity of the RAM module in gigabytes (GB)
     * @param speedMHz   the speed of the RAM module in gigahertz (GHz)
     */
    public RAM(double price, String model, String brand, String type, int capacity, double speedMHz) {
        super(price, model, brand);
        this.type = type;
        this.capacity = capacity;
        this.speedMHz = speedMHz;
    }

    /**
     * Gets the type of the RAM module.
     *
     * @return the type of the RAM module
     */
    public String getType() {
        return type;
    }

    /**
     * Gets the memory capacity of the RAM module.
     *
     * @return the memory capacity of the RAM module in gigabytes (GB)
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Gets the speed of the RAM module.
     *
     * @return the speed of the RAM module in megahertz (MHz)
     */
    public double getSpeedMHz() {
        return speedMHz;
    }

    /**
     * Sets the type of the RAM module.
     *
     * @param type the new type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Sets the memory capacity of the RAM module.
     *
     * @param capacity the new memory capacity to set in gigabytes (GB)
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Sets the speed of the RAM module.
     *
     * @param speedMHz the new speed to set in gigahertz (GHz)
     */
    public void setSpeedMHz(double speedMHz) {
        this.speedMHz = speedMHz;
    }

    /**
     * Returns a formatted string representation of the RAM module, including its general information, type, memory capacity, and speed.
     * Concept: Polymorphism
     * @return a string representation of the RAM module
     */
    @Override
    public String toString() {
        return String.format("""
                %s
                Type: %s
                Memory: %d GB
                Speed: %.1f GHz""", super.toString(), type, capacity, speedMHz);
    }
}
