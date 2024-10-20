package order;

/**
 * <p>ICS4U</p>
 * <p>AY Jackson SS</p>
 *
 * The {@code OrderStatus} class represents the status of an order.
 * It provides predefined status constants and allows custom statuses.
 * Instances of this class are immutable once created.
 *
 * <p>Predefined status constants:</p>
 * <ul>
 *   <li>{@code NOT_STARTED} - Order has not started.</li>
 *   <li>{@code IN_PROGRESS} - Order is in progress.</li>
 *   <li>{@code COMPLETED} - Order has been completed.</li>
 *   <li>{@code CANCELLED} - Order has been canceled.</li>
 * </ul>
 *
 * <p>Custom statuses can be created using the constructor that takes a status name as a parameter.</p>
 *
 * <p>Methods are provided to get the current status, check if it is a custom status,
 * set the status either by number or name, check if the status is current (either not started or in progress),
 * and compare two instances for equality.</p>
 *
 * <p>Note: The status numbering starts from 0 for predefined statuses.</p>
 *
 * @author Daniel, Meherzad, Stanley
 * @since 2024-01-15
 */
public class OrderStatus {
    /**
     * Class fields
     */
    private static final String[] DEFAULT_STATUS_NAMES = {"Not started", "In progress", "Completed", "Cancelled"};
    public static final OrderStatus NOT_STARTED = new OrderStatus(0);
    public static final OrderStatus IN_PROGRESS = new OrderStatus(1);
    public static final OrderStatus COMPLETED = new OrderStatus(2);
    public static final OrderStatus CANCELLED = new OrderStatus(3);
    private static final OrderStatus[] DEFAULT_STATUSES = {NOT_STARTED, IN_PROGRESS, COMPLETED, CANCELLED};
    /**
     * Instance fields
     * Concept: Encapsulation
     */
    private final int statusNumber; // -1 means custom status
    private final String currentStatus;

    /**
     * Constructs a new {@code OrderStatus} instance based on the predefined status number.
     *
     * @param statusNumber The status number (0 for "Not started", 1 for "In progress", etc.).
     * @throws IllegalArgumentException if the status number is invalid.
     */
    private OrderStatus(int statusNumber) {
        if (statusNumber < 0 || statusNumber > DEFAULT_STATUS_NAMES.length - 1) {
            throw new IllegalArgumentException("Invalid status number");
        }
        this.statusNumber = statusNumber;
        currentStatus = DEFAULT_STATUS_NAMES[statusNumber];
    }

    /**
     * Constructs a new {@code OrderStatus} instance with a custom status name.
     *
     * @param name The custom status name.
     */
    public OrderStatus(String name) {
        statusNumber = -1;
        currentStatus = name;
    }

    /**
     * Gets the {@code OrderStatus} instance based on the predefined status index.
     *
     * @param statusNumber The index (0 for {@code NOT_STARTED}, 1 for {@code IN_PROGRESS}, etc.).
     * @return The {@code OrderStatus} instance corresponding to the statusNumber.
     * @throws IllegalArgumentException if the status number is invalid.
     */
    public static OrderStatus defaultByIndex(int statusNumber) {
        if (statusNumber < 0 || statusNumber > DEFAULT_STATUS_NAMES.length - 1) {
            throw new IllegalArgumentException("Invalid status number");
        }
        return DEFAULT_STATUSES[statusNumber];
    }

    /**
     * Gets the status number of the current {@code OrderStatus}.
     *
     * @return The status number, or -1 if status is not associated with a status number (custom statuses).
     */
    public int getStatusNumber() {
        return statusNumber;
    }

    /**
     * Gets the current status.
     *
     * @return The current status.
     */
    public String getCurrentStatus() {
        return currentStatus;
    }

    /**
     * Checks if the status is custom.
     *
     * @return {@code true} if the status is custom, {@code false} otherwise.
     */
    public boolean isCustom() {
        return statusNumber == -1;
    }

    /**
     * Checks if the status is current (either not started or in progress).
     *
     * @return {@code true} if the status is current, {@code false} otherwise.
     */
    public boolean isCurrent() {
        return equals(NOT_STARTED) || equals(IN_PROGRESS);
    }

    public int compareTo(OrderStatus other) {
        return statusNumber - other.statusNumber;
    }

    /**
     * Checks if this {@code OrderStatus} instance is equal to another instance.
     *
     * @param other The other {@code OrderStatus} instance to compare.
     * @return {@code true} if the instances are equal and other is not {@code null}, {@code false} otherwise.
     */
    public boolean equals(OrderStatus other) {
        return other != null && currentStatus.equals(other.currentStatus);
    }

    /**
     * Returns the string representation of the current status.
     * Concept: Polymorphism
     * @return The string representation of the current status.
     */
    public String toString() {
        return currentStatus;
    }
}
