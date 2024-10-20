
package hardware;

import parts.Storage;

/**
 * <p>ICS4U</p>
 * <p>AY Jackson SS</p>
 *
 * <p>The {@code GamingAdditionalHardware} class represents additional hardware components specific to gaming computers,
 * extending the AdditionalHardware class. It includes features such as RGB strips,
 * noise-canceling fans, and additional cooling fans.</p>
 * <p>Concept: Inheritance</p>
 * @author Meherzad Antia
 * @author Stanley Gu
 * @author Daniel Li
 * @since 2024-01-19
 */

public class GamingAdditionalHardware extends AdditionalHardware {
    /**
     * The total count of gaming additional hardware components.
     */
    public static final int HARDWARE_COUNT = AdditionalHardware.HARDWARE_COUNT + 3;

    /**
     * The price of RGB strips.
     */
    public static final int RGB_STRIP_PRICE = 30;

    /**
     * The price of a single fan.
     */
    public static final int SINGLE_FAN_PRICE = 50;

    /**
     * The price of noise-canceling technology.
     */
    public static final int NOISE_CANCEL_PRICE = 25;

    /**
     * Indicates whether the additional hardware has RGB strips.
     * Concept: Encapsulation
     */
    private boolean hasRgbStrips;

    /**
     * Indicates whether noise-canceling fans are used.
     * Concept: Encapsulation
     */
    private boolean useNoiseCancellingFans;

    /** The count of extra cooling fans. Concept: Encapsulation */
    private int extraFansCount;

    /**
     * Constructs a GamingAdditionalHardware object with the specified additional storage,
     * RGB strip status, noise-canceling fan usage, and count of extra fans.
     *
     * @param additionalStorage       the additional storage component
     * @param hasRgbStrips            {@code true} if the hardware has RGB strips, {@code false} otherwise
     * @param useNoiseCancellingFans  {@code true} if noise-canceling fans are used, {@code false} otherwise
     * @param extraFansCount          the count of extra cooling fans
     */
    public GamingAdditionalHardware(Storage additionalStorage, boolean hasRgbStrips,
                                    boolean useNoiseCancellingFans, int extraFansCount) {
        super(additionalStorage);
        this.hasRgbStrips = hasRgbStrips;
        this.useNoiseCancellingFans = useNoiseCancellingFans;
        this.extraFansCount = extraFansCount;
    }

    /**
     * Checks if the additional hardware has RGB strips.
     *
     * @return true if the hardware has RGB strips, false otherwise
     */
    public boolean isHasRgbStrips() {
        return hasRgbStrips;
    }

    /**
     * Checks if noise-canceling fans are used.
     *
     * @return true if noise-canceling fans are used, false otherwise
     */
    public boolean isUseNoiseCancellingFans() {
        return useNoiseCancellingFans;
    }

    /**
     * Gets the count of extra cooling fans.
     *
     * @return the count of extra cooling fans
     */
    public int getExtraFansCount() {
        return extraFansCount;
    }

    /**
     * Sets whether the additional hardware has RGB strips.
     *
     * @param hasRgbStrips true if the hardware has RGB strips, false otherwise
     */
    public void setHasRgbStrips(boolean hasRgbStrips) {
        this.hasRgbStrips = hasRgbStrips;
    }

    /**
     * Sets whether noise-canceling fans are used.
     *
     * @param useNoiseCancellingFans true if noise-canceling fans are used, false otherwise
     */
    public void setUseNoiseCancellingFans(boolean useNoiseCancellingFans) {
        this.useNoiseCancellingFans = useNoiseCancellingFans;
    }

    /**
     * Sets the count of extra cooling fans.
     *
     * @param extraFansCount the count of extra cooling fans
     */
    public void setExtraFansCount(int extraFansCount) {
        this.extraFansCount = extraFansCount;
    }

    /**
     * Calculates the total price of the gaming additional hardware, including RGB strips,
     * noise-canceling technology, and additional cooling fans.
     * Concept: Polymorphism
     * @return the total cost of the gaming additional hardware
     */
    public double totalPrice(){
        double sum = 0;
        if (hasRgbStrips) {
            sum += RGB_STRIP_PRICE;
        }
        if (useNoiseCancellingFans) {
            sum += NOISE_CANCEL_PRICE;
        }
        sum += (SINGLE_FAN_PRICE * extraFansCount);

        return sum;
    }

    /**
     * Checks if the specifications of two gaming additional hardware components match within a given percentage.
     * The specifications include RGB strip status, noise-canceling fan usage, extra fan count,
     * and additional storage specifications.
     * Concept: Polymorphism
     * @param other   the other gaming additional hardware component to compare
     * @param percent the percentage within which the specifications are considered a match
     * @return true if the specifications match, false otherwise
     */
    @Override
    public boolean matchSpecs(AdditionalHardware other, double percent) {
        if (!(other instanceof GamingAdditionalHardware)) {
            return false;
        }

        GamingAdditionalHardware gamingOther = (GamingAdditionalHardware) other;

        int matches = 0;
        if (hasRgbStrips == gamingOther.hasRgbStrips) {
            matches++;
        }
        if (useNoiseCancellingFans == gamingOther.useNoiseCancellingFans) {
            matches++;
        }
        if (extraFansCount >= gamingOther.extraFansCount) {
            matches++;
        }
        if (additionalStorage.compareToSpeed(gamingOther.additionalStorage) >= 0 &&
                additionalStorage.compareToStorageSize(gamingOther.additionalStorage) >= 0) {
            matches++;
        }

        return (double) matches / HARDWARE_COUNT >= percent;
    }

    /**
     * Returns a string representation of the gaming additional hardware, including details of RGB strips,
     * noise-canceling fan usage, and extra fan count.
     * Concept: Polymorphism
     * @return a formatted string representing the gaming additional hardware
     */
    @Override
    public String toString(){
        return String.format("""
                %s
                :
                Has RGB Strips: %b
                Noise-Cancelling Fans: %b
                Number of Extra Fans: %d""", super.toString(), hasRgbStrips, useNoiseCancellingFans, extraFansCount);
    }
}
