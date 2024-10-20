package parts;

/**
 * <p>ICS4U</p>
 * <p>AY Jackson SS</p>
 *
 * <p>The {@code Case} class represents a computer case, which is a type of part in a computer system.
 * It extends the {@code Part} class and includes additional features related to color, size and material.</p>
 *
 * <p>The class includes a constructor to initialize the price, model, brand, color, size and material of the case.
 * It also provides getter and setter methods for accessing and modifying the color, size and material of the case.</p>
 *
 * <p>The {@code toString} method is overridden to provide a formatted string representation of the case,
 * including its general information, color, size and material.</p>
 * <p>Concept: Inheritance</p>
 * @author Meherzad Antia
 * @author Stanley Gu
 * @author Daniel Li
 * @since 2024-01-15
 */
public class Case extends Part {

    /**
     * The color of the case. Concept: Encapsulation
     */
    private String colour;
    /**
     * The size of the case. Concept: Encapsulation
     */
    private String size;
    /**
     * The material of the case. Concept: Polymorphism
     */
    private String material;

    /**
     * Constructs a new {@code Case} object with the specified price, model, brand, and color.
     *
     * @param price  the price of the case
     * @param model  the model of the case
     * @param brand  the brand of the case
     * @param colour the color of the case
     * @param size the size of the case
     * @param material the material of the case
     */
    public Case(double price, String model, String brand, String colour, String size, String material) {
        super(price, model, brand);
        this.colour = colour;
        this.size = size;
        this.material = material;
    }

    /**
     * Gets the color of the case.
     *
     * @return the color of the case
     */
    public String getColour() {
        return colour;
    }

    /**
     * Gets the size of the case.
     *
     * @return the size of the case
     */
    public String getSize() {
        return size;
    }

    /**
     * Gets the material of the case.
     *
     * @return the material of the case
     */
    public String getMaterial() {
        return material;
    }

    /**
     * Sets the color of the case.
     *
     * @param colour the new color to set
     */
    public void setColour(String colour) {
        this.colour = colour;
    }

    /**
     * Sets the size of the case.
     *
     * @param size the new color to set
     */
    public void setSize(String size) {
        this.size = size;
    }

    /**
     * Sets the material of the case.
     *
     * @param material the new color to set
     */
    public void setMaterial(String material) {
        this.material = material;
    }

    /**
     * Returns a formatted string representation of the case, including its general information, color, size and material.
     * Concept: Polymorphism
     * @return a string representation of the case
     */
    @Override
    public String toString(){
        return String.format("""
                %s
                Color: %s
                Size: %s
                Material: %s""", super.toString(), colour, size, material);
    }
}
