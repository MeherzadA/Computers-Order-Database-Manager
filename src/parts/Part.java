package parts;

/**
 * <p>ICS4U</p>
 * <p>AY Jackson SS</p>
 *
 * <p>The {@code Part} class is an abstract class representing a generic part in a computer system.
 * It includes features such as price, brand, and model, along with getter and setter methods for these attributes.
 * The class also provides methods for equality comparison and a formatted string representation.</p>
 *
 * <p>Subclasses are expected to extend this class and provide specific implementations for their respective part types.</p>
 *
 * @author Meherzad Antia
 * @author Stanley Gu
 * @author Daniel Li
 * @since 2024-01-15
 */
public class Part {

    /**
     * The price of the part.
     */
    protected double price;

    /**
     * The brand of the part.
     */
    protected String brand;

    /**
     * The model of the part.
     */
    protected String model;

    /**
     * Constructs a new {@code Part} object with the specified price, model, and brand.
     *
     * @param price the price of the part
     * @param model the model of the part
     * @param brand the brand of the part
     */
    public Part(double price, String model, String brand) {
        this.price = price;
        this.model = model;
        this.brand = brand;
    }

    /**
     * Gets the price of the part.
     *
     * @return the price of the part
     */
    public double getPrice() {
        return price;
    }

    /**
     * Gets the model of the part.
     *
     * @return the model of the part
     */
    public String getModel() {
        return model;
    }

    /**
     * Gets the brand of the part.
     *
     * @return the brand of the part
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Sets the price of the part.
     *
     * @param price the new price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Sets the model of the part.
     *
     * @param model the new model to set
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Sets the brand of the part.
     *
     * @param brand the new brand to set
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * Checks if the given part is equal to this part.
     * Concept: Polymorphism
     * @param other the part to compare
     * @return {@code true} if the parts are equal, {@code false} otherwise
     */
    public boolean equals(Part other) {
        return other != null && price == other.price && brand.equals(other.brand) && model.equals(other.model);
    }

    /**
     * Returns a formatted string representation of the part, including its price, brand, and model.
     * Concept: Polymorphism
     * @return a string representation of the part
     */
    @Override
    public String toString() {
        return String.format("""
                Price: $%.2f
                Brand: %s
                Model: %s""", this.price, this.brand, this.model);
    }
}
