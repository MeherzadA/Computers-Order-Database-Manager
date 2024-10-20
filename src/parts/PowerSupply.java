package parts;

/**
 * <p>ICS4U</p>
 * <p>AY Jackson SS</p>
 *
 * <p>The {@code PowerSupply} class represents a power supply unit (PSU), which is a type of part in a computer system.
 * It extends the {@code Part} class and includes additional features related to wattage.</p>
 *
 * <p>The class includes a constructor to initialize the price, model, brand, and wattage of the power supply unit.
 * It also provides getter and setter methods for accessing and modifying the wattage of the power supply unit.</p>
 *
 * <p>The {@code toString} method is overridden to provide a formatted string representation of the power supply unit,
 * including its general information and wattage.</p>
 * <p>Concept: Inheritance</p>
 * @author Meherzad Antia
 * @author Stanley Gu
 * @author Daniel Li
 * @since 2024-01-15
 */
public class PowerSupply extends Part {

    /**
     * The wattage of the power supply unit. Concept: Encapsulation
     */
    private double wattage;

    /**
     * Constructs a new {@code PowerSupply} object with the specified price, model, brand, and wattage.
     *
     * @param price   the price of the power supply unit
     * @param model   the model of the power supply unit
     * @param brand   the brand of the power supply unit
     * @param wattage the wattage of the power supply unit
     */
    public PowerSupply(double price, String model, String brand, double wattage) {
        super(price, model, brand);
        this.wattage = wattage;
    }

    /**
     * Gets the wattage of the power supply unit.
     *
     * @return the wattage of the power supply unit
     */
    public double getWattage() {
        return wattage;
    }

    /**
     * Sets the wattage of the power supply unit.
     *
     * @param wattage the new wattage to set
     */
    public void setWattage(double wattage) {
        this.wattage = wattage;
    }

    /**
     * Returns a formatted string representation of the power supply unit, including its general information and wattage.
     * Concept: Polymorphism
     * @return a string representation of the power supply unit
     */
    @Override
    public String toString() {
        return String.format("""
                %s
                Wattage: %.2f W""", super.toString(), wattage);
    }
}
