package parts;

/**
 * <p>ICS4U</p>
 * <p>AY Jackson SS</p>
 * <p> The {@code Cooler} class represents a cooling system, which is a type of part in a computer system.
 * It extends the {@code Part} class and includes additional features related to color, type, number of fans, and speed of the fans. </p>
 *
 * <p>The class includes a constructor to initialize the price, model, brand, color, type, number of fans and speed of the fans of the cooler.
 * It also provides getter and setter methods for accessing and modifying the color, type, number of fans and speed of the fans of the cooler.</p>
 *
 * <p>The {@code toString} method is overridden to provide a formatted string representation of the cooler,
 * including its general information, type, and the number of fans.</p>
 * <p>Concept: Inheritance</p>
 * @author Meherzad Antia
 * @author Stanley Gu
 * @author Daniel Li
 * @since 2024-01-15
 */
public class Cooler extends Part {

    /**
     * The colour of the cooler. Concept: Encapsulation
     */
    private String colour;
    /**
     * The type of the cooler. Concept: Encapsulation
     */
    private String type;
    /**
     * The number of fans in the cooler. Concept: Encapsulation
     */
    private int fanCount;
    /**
     * The speed of the fans in rotations per minute (rpm) Concept: Encapsulation
     */
    private int rpm;

    /**
     * Constructs a new {@code Cooler} object with the specified price, model, brand, color, type, number of fans and speed in rpm.
     *
     * @param price    the price of the cooler
     * @param model    the model of the cooler
     * @param brand    the brand of the cooler
     * @param colour   the color of the cooler
     * @param type     the type of the cooler
     * @param fanCount the number of fans in the cooler
     * @param rpm      the speed of the fans in rotations per minute
     */
    public Cooler(double price, String model, String brand, String colour, String type, int fanCount, int rpm) {
        super(price, model, brand);
        this.colour = colour;
        this.type = type;
        this.fanCount = fanCount;
        this.rpm = rpm;
    }

    /**
     * Gets the colour of the cooler.
     *
     * @return the colour of the cooler
     */
    public String getColour() {
        return colour;
    }

    /**
     * Gets the type of the cooler.
     *
     * @return the type of the cooler
     */
    public String getType() {
        return type;
    }

    /**
     * Gets the number of fans in the cooler.
     *
     * @return the number of fans in the cooler
     */
    public int getFanCount() {
        return fanCount;
    }

    /**
     * Gets the speed of the fans in rotations per minute.
     *
     * @return the number of fans in the cooler
     */
    public int getRpm() {
        return rpm;
    }

    /**
     * Sets the colour of the cooler.
     *
     * @param colour the new colour to set
     */
    public void setColour(String colour) {
        this.colour = colour;
    }

    /**
     * Sets the type of the cooler.
     *
     * @param type the new type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Sets the number of fans in the cooler.
     *
     * @param fanCount the new number of fans to set
     */
    public void setFanCount(int fanCount) {
        this.fanCount = fanCount;
    }

    /**
     * Sets the speed of the fans in the cooler.
     *
     * @param rpm the new number of fans to set
     */
    public void setRpm(int rpm) {
        this.rpm = rpm;
    }

    /**
     * Returns a formatted string representation of the cooler, including its general information, type, speed in rpm and the number of fans.
     * Concept: Polymorphism
     * @return a string representation of the cooler
     */
    @Override
    public String toString() {
        return String.format("""
                %s
                Type: %s
                Number of Fans: %d
                Fan Speed: %d rpm""", super.toString(), type, fanCount, rpm);
    }
}

