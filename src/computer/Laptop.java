
package computer;

import hardware.LaptopAdditionalHardware;
import hardware.PrimaryHardware;

/**
 * <p>ICS4U</p>
 * <p>AY Jackson SS</p>
 *
 * <p>This class represents a laptop, extending the abstract Computer class.
 * It includes additional features specific to laptops such as laptop-specific
 * additional hardware.</p>
 * <p>Concept: Inheritance</p>
 * @author Meherzad Antia
 * @author Stanley Gu
 * @author Daniel Li
 * @since 2024-01-19
 */
public class Laptop extends Computer {
    /**
     * Laptop specific additional hardware
     * Concept: Encapsulation
     */
    private LaptopAdditionalHardware additionalHardware;

    /**
     * Constructs a Laptop object with the specified primary hardware and
     * laptop-specific additional hardware.
     *
     * @param primaryHardware      the primary hardware of the laptop
     * @param additionalHardware   the laptop-specific additional hardware
     */
    public Laptop(PrimaryHardware primaryHardware, LaptopAdditionalHardware additionalHardware) {
        super(ComputerType.LAPTOP, primaryHardware);
        this.additionalHardware = additionalHardware;
    }

    /**
     * Gets the laptop-specific additional hardware.
     *
     * @return the laptop-specific additional hardware
     */
    public LaptopAdditionalHardware getAdditionalHardware() {
        return additionalHardware;
    }

    /**
     * Sets the laptop-specific additional hardware.
     *
     * @param additionalHardware the additional hardware to set
     */
    public void setAdditionalHardware(LaptopAdditionalHardware additionalHardware) {
        this.additionalHardware = additionalHardware;
    }

    /**
     * Calculates the total price of the primary hardware for the laptop.
     * Concept: Polymorphism
     * @return the total cost of the primary hardware for the laptop
     */
    @Override
    public double primaryHardwareTotalPrice() {
        return primaryHardware.totalPrice();
    }

    /**
     * Calculates the total price of laptop-specific additional hardware.
     Concept: Polymorphism
     * @return the total cost of laptop-specific additional hardware
     */
    @Override
    public double additionalHardwareTotalPrice() {
        return additionalHardware.totalPrice();
    }

    /**
     * Returns a string representation of the laptop, including details of the primary
     * hardware and laptop-specific additional hardware.
     * Concept: Polymorphism
     * @return a formatted string representing the laptop
     */
    @Override
    public String toString() {
        return String.format("""
                %s
                Additional Hardware:
                %s""", super.toString(), additionalHardware);
    }
}
