
package computer;

import hardware.PrimaryHardware;

/**
 * <p>ICS4U</p>
 * <p>AY Jackson SS</p>
 * <p>The {@code Computer} class represents an abstract computer with primary hardware
 * and provides methods to calculate total cost and display information.</p>
 *
 * <p>This class is designed to be extended by specific computer implementations,
 * and it requires a {@link PrimaryHardware} object for primary hardware details.</p>
 * <p>Concept: Abstract Class</p>
 * @author Meherzad Antia
 * @author Stanley Gu
 * @author Daniel Li
 * @since 2024-01-19
 */

public abstract class Computer {

    /**
     * The type of the computer.
     */
    private final String type;

    /**
     * The primary hardware associated with the computer.
     */
    protected PrimaryHardware primaryHardware;

    /**
     * Constructs a new computer with the specified type and primary hardware.
     *
     * @param type            the type of the computer.
     * @param primaryHardware the primary hardware associated with the computer.
     */
    public Computer(String type, PrimaryHardware primaryHardware) {
        this.type = type;
        this.primaryHardware = primaryHardware;
    }

    /**
     * Calculates the total price of the primary hardware.
     * Concept: Abstract Method
     * @return the total price of the primary hardware.
     */
    public abstract double primaryHardwareTotalPrice();

    /**
     * Calculates the total price of additional hardware.
     * Concept: Abstract Method
     * @return the total price of additional hardware.
     */
    public abstract double additionalHardwareTotalPrice();

    /**
     * Gets the type of the computer.
     *
     * @return the type of the computer.
     */
    public String getType() {
        return type;
    }

    /**
     * Gets the primary hardware associated with the computer.
     *
     * @return the primary hardware.
     */
    public PrimaryHardware getPrimaryHardware() {
        return primaryHardware;
    }

    /**
     * Sets the primary hardware associated with the computer.
     *
     * @param primaryHardware the primary hardware to set.
     */
    public void setPrimaryHardware(PrimaryHardware primaryHardware) {
        this.primaryHardware = primaryHardware;
    }

    /**
     * Calculates the total cost of the computer, including primary and additional hardware.
     *
     * @return the total cost of the computer.
     */
    public double totalCost() {
        return primaryHardwareTotalPrice() + additionalHardwareTotalPrice();
    }

    /**
     * Returns a string representation of the computer, including primary hardware details.
     * Concept: Polymorphism
     * @return a string representation of the computer.
     */
    @Override
    public String toString() { //
        return String.format("""
                Primary Hardware:
                %s""", primaryHardware);
    }
}
