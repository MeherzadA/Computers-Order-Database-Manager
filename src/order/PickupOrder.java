package order;

import computer.Computer;
import info.CustomerInfo;
import info.PickupInfo;

import java.util.Date;


/**
 * <p>ICS4U</p>
 * <p>AY Jackson SS</p>
 *
 * The {@code PickupOrder} class represents an order type for picking up a computer.
 * It extends the {@link Order} class and includes additional information specific to pickup orders.
 * <p>Concept: Inheritance</p>
 * @author Meherzad Antia
 * @author Stanley Gu
 * @author Daniel Li
 * @since 2024-01-19
 */
public class PickupOrder extends Order{
    /**
     * PickupInfo instance field for the PickupOrder instance
     * Concept: Encapsulation
     */
    private PickupInfo pickupInfo;

    /**
     * Constructs a new {@code PickupOrder} with specified parameters.
     *
     * @param id            The unique identifier of the order.
     * @param quantity      The quantity of computers in the order.
     * @param status        The status of the order.
     * @param computer      The computer associated with the order.
     * @param customerInfo  The customer information associated with the order.
     * @param pickupInfo    The pickup information associated with the order.
     */
    public PickupOrder(int id, int quantity, OrderStatus status, Computer computer, CustomerInfo customerInfo, PickupInfo pickupInfo) {
        super(OrderType.PICKUP, id, quantity, status, computer, customerInfo);
        this.pickupInfo = pickupInfo;

    }

    /**
     * Constructs a new {@code PickupOrder} with specified parameters, including order and finish by dates.
     *
     * @param id            The unique identifier of the order.
     * @param quantity      The quantity of computers in the order.
     * @param status        The status of the order.
     * @param computer      The computer associated with the order.
     * @param customerInfo  The customer information associated with the order.
     * @param orderDate     The date when the order was placed.
     * @param finishByDate  The date by which the order should be finished.
     * @param pickupInfo    The pickup information associated with the order.
     */
    public PickupOrder(int id, int quantity, OrderStatus status, Computer computer, CustomerInfo customerInfo, Date orderDate, Date finishByDate, PickupInfo pickupInfo) {
        super(OrderType.PICKUP, id, quantity, status, computer, customerInfo, orderDate, finishByDate);
        this.pickupInfo = pickupInfo;

    }

    /**
     * Gets the pickup information associated with the order.
     *
     * @return The pickup information.
     */
    public PickupInfo getPickupInfo() {
        return pickupInfo;
    }

    /**
     * Sets the pickup information associated with the order.
     *
     * @param pickupInfo The pickup information to set.
     */
    public void setPickupInfo(PickupInfo pickupInfo){
        this.pickupInfo = pickupInfo;
    }

    /**
     * Calculates and returns the total cost of the pickup order.
     * Concept: Polymorphism
     * @return The total cost of the order.
     */
    public double totalCost() {
        return quantity * computer.totalCost();
    }

    /**
     * Generates a string representation of the order information, including pickup details.
     * Concept: Polymorphism
     * @return The order information as a formatted string.
     */
    @Override
    public String orderInfo() {
        return String.format("""
                Pickup Info:
                %s""", pickupInfo);
    }
}
