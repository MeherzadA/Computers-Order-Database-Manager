
package computer;

import hardware.GamingAdditionalHardware;
import hardware.PrimaryHardware;
import parts.Case;
import parts.Cooler;
import parts.PowerSupply;

/**
 * <p>ICS4U</p>
 * <p>AY Jackson SS</p>
 *
 * <p>This class represents a gaming computer, extending the abstract Computer class.
 * It includes additional features specific to gaming computers such as a cooler,
 * power supply, computer case, and gaming-specific additional hardware.</p>
 * <p>Concept: Inheritance</p>
 * @author Meherzad Antia
 * @author Stanley Gu
 * @author Daniel Li
 * @since 2024-01-19
 */
public class GamingComputer extends Computer {
    /**
     * Cooler of the gaming computer
     * Concept: Encapsulation
     */
    private Cooler cooler;

    /**
     * Power supply of the gaming computer
     * Concept: Encapsulation
     */
    private PowerSupply powerSupply;

    /**
     * Computer case of the gaming computer
     * Concept: Encapsulation
     */
    private Case computerCase;

    /**
     * Gaming specific additional hardware
     * Concept: Encapsulation
     */
    private GamingAdditionalHardware additionalHardware;

    /**
     * Constructs a GamingComputer object with the specified primary hardware, cooler,
     * power supply, computer case, and gaming-specific additional hardware.
     *
     * @param primaryHardware      the primary hardware of the gaming computer
     * @param cooler               the cooler of the gaming computer
     * @param powerSupply          the power supply of the gaming computer
     * @param computerCase         the computer case of the gaming computer
     * @param additionalHardware   the gaming-specific additional hardware
     */
    public GamingComputer(PrimaryHardware primaryHardware, Cooler cooler, PowerSupply powerSupply,
                          Case computerCase, GamingAdditionalHardware additionalHardware) {
        super(ComputerType.GAMING, primaryHardware);
        this.cooler = cooler;
        this.powerSupply = powerSupply;
        this.computerCase = computerCase;
        this.additionalHardware = additionalHardware;
    }

    /**
     * Gets the cooler of the gaming computer.
     *
     * @return the cooler of the gaming computer
     */
    public Cooler getCooler() {
        return cooler;
    }

    /**
     * Gets the power supply of the gaming computer.
     *
     * @return the power supply of the gaming computer
     */
    public PowerSupply getPowerSupply() {
        return powerSupply;
    }

    /**
     * Gets the computer case of the gaming computer.
     *
     * @return the computer case of the gaming computer
     */
    public Case getComputerCase() {
        return computerCase;
    }

    /**
     * Gets the gaming-specific additional hardware.
     *
     * @return the gaming-specific additional hardware
     */
    public GamingAdditionalHardware getAdditionalHardware() {
        return additionalHardware;
    }

    /**
     * Sets the cooler of the gaming computer.
     *
     * @param cooler the cooler to set
     */
    public void setCooler(Cooler cooler) {
        this.cooler = cooler;
    }

    /**
     * Sets the power supply of the gaming computer.
     *
     * @param powerSupply the power supply to set
     */
    public void setPowerSupply(PowerSupply powerSupply) {
        this.powerSupply = powerSupply;
    }

    /**
     * Sets the computer case of the gaming computer.
     *
     * @param computerCase the computer case to set
     */
    public void setComputerCase(Case computerCase) {
        this.computerCase = computerCase;
    }

    /**
     * Sets the gaming-specific additional hardware.
     *
     * @param additionalHardware the additional hardware to set
     */
    public void setAdditionalHardware(GamingAdditionalHardware additionalHardware) {
        this.additionalHardware = additionalHardware;
    }

    /**
     * Calculates the total price of the gaming computer, including primary and additional hardware.
     * Concept: Polymorphism
     * @return the total cost of the gaming computer
     */
    @Override
    public double primaryHardwareTotalPrice() {
        return primaryHardware.totalPrice() + cooler.getPrice() + powerSupply.getPrice() + computerCase.getPrice();
    }

    /**
     * Calculates the total price of gaming-specific additional hardware.
     * Concept: Polymorphism
     * @return the total cost of gaming-specific additional hardware
     */
    @Override
    public double additionalHardwareTotalPrice() {
        return additionalHardware.totalPrice();
    }

    /**
     * Returns a string representation of the gaming computer, including details of the components.
     * Concept: Polymorphism
     * @return a formatted string representing the gaming computer
     */
    @Override
    public String toString() {
        return String.format("""
                %s
                Cooler: %s
                Power Supply: %s
                Case: %s
                Additional Hardware:
                %s""", super.toString(), cooler, powerSupply, computerCase, additionalHardware);
    }
}
