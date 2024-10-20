
package hardware;

import parts.Storage;

/**
 * <p>ICS4U</p>
 * <p>AY Jackson SS</p>
 *
 * <p>This {@code LaptopAdditionalHardware} class represents additional hardware components specific to laptops,
 * extending the AdditionalHardware class. It includes features such as a
 * fingerprint scanner, touchscreen, and a 360-degree hinge.</p>
 * <p>Concept: Inheritance</p>
 * @author Meherzad Antia
 * @author Stanley Gu
 * @author Daniel Li
 * @since 2024-01-19
 */
public class LaptopAdditionalHardware extends AdditionalHardware {
    /**
     * The total count of laptop additional hardware components.
     */
    public static final int HARDWARE_COUNT = AdditionalHardware.HARDWARE_COUNT + 3;

    /**
     * The price of a fingerprint scanner.
     */
    public static final int FINGERPRINT_SCANNER_PRICE = 100;

    /**
     * The price of a touchscreen.
     */
    public static final int TOUCHSCREEN_PRICE = 150;

    /**
     * The price of a 360-degree hinge.
     */
    public static final int HINGE_360_PRICE = 200;

    /** Indicates whether the laptop has a fingerprint scanner. Concept: Encapsulation */
    private boolean hasFingerprintScanner;

    /** Indicates whether the laptop has a touchscreen. Concept: Encapsulation*/
    private boolean hasTouchscreen;

    /** Indicates whether the laptop has a 360-degree hinge. Concept: Encapsulation */
    private boolean has360Hinge;

    /**
     * Constructs a LaptopAdditionalHardware object with the specified additional storage,
     * fingerprint scanner status, touchscreen status, and 360-degree hinge status.
     *
     * @param additional           the additional storage component
     * @param hasFingerprintScanner true if the laptop has a fingerprint scanner, false otherwise
     * @param hasTouchscreen        true if the laptop has a touchscreen, false otherwise
     * @param has360Hinge           true if the laptop has a 360-degree hinge, false otherwise
     */
    public LaptopAdditionalHardware(Storage additional, boolean hasFingerprintScanner,
                                    boolean hasTouchscreen, boolean has360Hinge){
        super(additional);
        this.hasFingerprintScanner = hasFingerprintScanner;
        this.hasTouchscreen = hasTouchscreen;
        this.has360Hinge = has360Hinge;
    }

    /**
     * Checks if the laptop has a fingerprint scanner.
     *
     * @return true if the laptop has a fingerprint scanner, false otherwise
     */
    public boolean isHasFingerprintScanner() {
        return hasFingerprintScanner;
    }

    /**
     * Checks if the laptop has a touchscreen.
     *
     * @return true if the laptop has a touchscreen, false otherwise
     */
    public boolean isHasTouchscreen() {
        return hasTouchscreen;
    }

    /**
     * Checks if the laptop has a 360-degree hinge.
     *
     * @return true if the laptop has a 360-degree hinge, false otherwise
     */
    public boolean isHas360Hinge() {
        return has360Hinge;
    }

    /**
     * Sets whether the laptop has a fingerprint scanner.
     *
     * @param hasFingerprintScanner true if the laptop has a fingerprint scanner, false otherwise
     */
    public void setFingerprintScanner(boolean hasFingerprintScanner) {
        this.hasFingerprintScanner = hasFingerprintScanner;
    }

    /**
     * Sets whether the laptop has a touchscreen.
     *
     * @param hasTouchscreen true if the laptop has a touchscreen, false otherwise
     */
    public void setTouchscreen(boolean hasTouchscreen) {
        this.hasTouchscreen = hasTouchscreen;
    }

    /**
     * Sets whether the laptop has a 360-degree hinge.
     *
     * @param has360Hinge true if the laptop has a 360-degree hinge, false otherwise
     */
    public void set360Hinge(boolean has360Hinge) {
        this.has360Hinge = has360Hinge;
    }

    /**
     * Calculates the total price of the laptop additional hardware, including
     * a fingerprint scanner, touchscreen, and a 360-degree hinge.
     * Concept: Polymorphism
     * @return the total cost of the laptop additional hardware
     */
    @Override
    public double totalPrice() {
        double sum = 0;
        if (hasFingerprintScanner) {
            sum += FINGERPRINT_SCANNER_PRICE;
        }
        if (hasTouchscreen) {
            sum += TOUCHSCREEN_PRICE;
        }
        if (has360Hinge) {
            sum += HINGE_360_PRICE;
        }
        return sum;
    }

    /**
     * Checks if the specifications of two laptop additional hardware components match within a given percentage.
     * The specifications include additional storage specifications, fingerprint scanner status,
     * touchscreen status, and 360-degree hinge status.
     * Concept: Polymorphism
     * @param other   the other laptop additional hardware component to compare
     * @param percent the percentage within which the specifications are considered a match
     * @return true if the specifications match, false otherwise
     */
    @Override
    public boolean matchSpecs(AdditionalHardware other, double percent) {
        if (!(other instanceof LaptopAdditionalHardware)) {
            return false;
        }

        LaptopAdditionalHardware laptopOther = (LaptopAdditionalHardware) other;

        int matches = 0;
        if (additionalStorage.compareToStorageSize(laptopOther.getAdditionalStorage()) >= 0) {
            matches++;
        }
        if (hasFingerprintScanner == laptopOther.hasFingerprintScanner) {
            matches++;
        }
        if (hasTouchscreen == laptopOther.hasTouchscreen) {
            matches++;
        }
        if (has360Hinge == laptopOther.hasTouchscreen) {
            matches++;
        }

        return (double) matches / HARDWARE_COUNT >= percent;
    }

    /**
     * Returns a string representation of the laptop additional hardware,
     * including details of a fingerprint scanner, touchscreen, and a 360-degree hinge.
     * Concept: Polymorphism
     * @return a formatted string representing the laptop additional hardware
     */
    @Override
    public String toString(){
        return String.format("""
                %s
                :
                Has Fingerprint Scanner: %b
                Has Touchscreen: %b
                Has 360 Degree Hinge: %b""", super.toString(), hasFingerprintScanner, hasTouchscreen, has360Hinge);
    }
}
