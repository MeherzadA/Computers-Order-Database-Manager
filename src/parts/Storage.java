package parts;

/**
 * <p>ICS4U</p>
 * <p>AY Jackson SS</p>
 *
 * <p>The {@code Storage} class represents a storage device, which is a type of part in a computer system.
 * It extends the {@code Part} class and includes additional features related to speed in megabytes per second (MBps)</p>
 * and storage size in gigabytes (GB).
 *
 * <p>The class includes a constructor to initialize the price, model, brand, speed, and storage size of the storage device.
 * It also provides getter and setter methods for accessing and modifying these features.
 * Additionally, the class includes methods for comparing storage devices based on speed and storage size,
 * checking equality, and a formatted string representation.</p>
 * <p>Concept: Inheritance</p>
 * @author Meherzad Antia
 * @author Stanley Gu
 * @author Daniel Li
 * @since 2024-01-15
 */
public class Storage extends Part {

    /**
     * The speed of the storage device in megabytes per second (MBps). Concept: Encapsulation
     */
    private double speedMBps;

    /**
     * The storage size of the storage device in gigabytes (GB). Concept: Encapsulation
     */
    private double capacityGB;

    /**
     * Constructs a new {@code Storage} object with the specified price, model, brand, speed, and storage size.
     *
     * @param price       the price of the storage device
     * @param model       the model of the storage device
     * @param brand       the brand of the storage device
     * @param speedMBps   the speed of the storage device in megabytes per second (MBps)
     * @param capacityGB  the storage size of the storage device in gigabytes (GB)
     */
    public Storage(double price, String model, String brand, double speedMBps, double capacityGB) {
        super(price, model, brand);
        this.speedMBps = speedMBps;
        this.capacityGB = capacityGB;
    }

    /**
     * Gets the speed of the storage device.
     *
     * @return the speed of the storage device in megabytes per second (MBps)
     */
    public double getSpeedMBps() {
        return speedMBps;
    }

    /**
     * Gets the storage size of the storage device.
     *
     * @return the storage size of the storage device in gigabytes (GB)
     */
    public double getCapacityGB() {
        return capacityGB;
    }

    /**
     * Sets the speed of the storage device.
     *
     * @param speedMBps the new speed to set in megabytes per second (MBps)
     */
    public void setSpeedMBps(double speedMBps) {
        this.speedMBps = speedMBps;
    }

    /**
     * Sets the storage size of the storage device.
     *
     * @param capacityGB the new storage size to set in gigabytes (GB)
     */
    public void setCapacityGB(double capacityGB) {
        this.capacityGB = capacityGB;
    }

    /**
     * Compares this storage device to another based on speed.
     *
     * @param other the other storage device to compare
     * @return the difference in speed between this storage device and the other
     */
    public double compareToSpeed(Storage other) {
        return speedMBps - other.speedMBps;
    }

    /**
     * Compares this storage device to another based on storage size.
     *
     * @param other the other storage device to compare
     * @return the difference in storage size between this storage device and the other
     */
    public double compareToStorageSize(Storage other) {
        return capacityGB - other.capacityGB;
    }

    /**
     * Checks if this storage device is equal to another storage device.
     *
     * @param other the other storage device to compare
     * @return {@code true} if the storage devices are equal, {@code false} otherwise
     */
    public boolean equals(Storage other) {
        return super.equals(other) && speedMBps == other.speedMBps && capacityGB == other.capacityGB;
    }

    /**
     * Returns a formatted string representation of the storage device,
     * including its general information, speed, and storage size.
     * Concept: Polymorphism
     * @return a string representation of the storage device
     */
    @Override
    public String toString() {
        return String.format("""
                %s
                Speed: %.1f MBps
                Storage Size: %.0f GB""", super.toString(), speedMBps, capacityGB);
    }
}
