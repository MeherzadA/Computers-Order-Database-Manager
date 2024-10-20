
package hardware;

import parts.Storage;

/**
 * <p>ICS4U</p>
 * <p>AY Jackson SS</p>
 *
 * <p>This {@code AdditionalHardware} abstract class represents additional hardware components that can be added to a computer system.
 * It includes methods to get and set additional storage, calculate the total price of the hardware,
 * and check if the specifications of two additional hardware components match within a given percentage.</p>
 * <p>Concept: Abstract Class</p>
 * @author Meherzad Antia
 * @author Stanley Gu
 * @author Daniel Li
 * @since 2024-01-19
 */
public abstract class AdditionalHardware {

    /**
     * The total count of hardware components.
     */
    public static final int HARDWARE_COUNT = 1;

    /**
     * Additional storage component
     */
    protected Storage additionalStorage;

    /**
     * Constructs an {@code AdditionalHardware} object with the specified additional storage.
     *
     * @param additionalStorage  the additional storage component
     */
    public AdditionalHardware(Storage additionalStorage){
        this.additionalStorage = additionalStorage;
    }

    /**
     * Gets the additional storage component.
     *
     * @return the additional storage component
     */
    public Storage getAdditionalStorage(){
        return additionalStorage;
    }

    /**
     * Sets the additional storage component.
     *
     * @param additionalStorage  the additional storage component to set
     */
    public void setAdditionalStorage(Storage additionalStorage) {
        this.additionalStorage = additionalStorage;
    }

    /**
     * Calculates the total price of the additional hardware.
     * Concept: Abstract Method
     * @return the total price of the additional hardware
     */
    public abstract double totalPrice();

    /**
     * Checks if the specifications of two additional hardware components match within a given percentage.
     * Concept: Abstract Method
     * @param other    the other additional hardware component to compare
     * @param percent  the percentage within which the specifications are considered a match
     * @return {@code true} if the specifications match, {@code false} otherwise
     */
    public abstract boolean matchSpecs(AdditionalHardware other, double percent);

    /**
     * Checks if the specifications of two additional hardware components match within 100%.
     *
     * @param other  the other additional hardware component to compare
     * @return {@code true} if the specifications match, {@code false} otherwise
     */
    public boolean matchSpecs(AdditionalHardware other) {
        return matchSpecs(other, 100);
    }

    /**
     * Returns a string representation of the additional hardware, including details of the additional storage.
     * Concept: Polymorphism
     * @return a formatted string representing the additional hardware
     */
    public String toString(){
        if (additionalStorage == null) { // no additional storage
            return "Additional Storage: None";
        }
        return String.format("""
                Additional Storage:
                %s""", additionalStorage);

    }
}
