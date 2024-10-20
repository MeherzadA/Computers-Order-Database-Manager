package order;

import computer.Computer;
import info.CustomerInfo;
import info.PickupInfo;
import info.ShippingInfo;

import java.util.Date;

/**
 * <p>ICS4U</p>
 * <p>AY Jackson SS</p>
 *
 * The {@code ShippingOrder} class represents an order type for shipping a computer.
 * It extends the {@link Order} class and includes additional information specific to shipping orders.
 * <p>Concept: Inheritance</p>
 * @author Meherzad Antia
 * @author Stanley Gu
 * @author Daniel Li
 *  * @since 2024-01-19
 */
public class ShippingOrder extends Order {
    /**
     * ShippingInfo instance field required for the ShippingOrder instance.
     * Concept: Encapsulation
     */
    private ShippingInfo shippingInfo;

    /**
     * Constructs a new {@code ShippingOrder} with specified parameters, including order and finish by dates.
     *
     * @param id            The unique identifier of the order.
     * @param quantity      The quantity of computers in the order.
     * @param status        The status of the order.
     * @param computer      The computer associated with the order.
     * @param customerInfo  The customer information associated with the order.
     * @param orderDate     The date when the order was placed.
     * @param finishByDate  The date by which the order should be finished.
     * @param shippingInfo  The shipping information associated with the order.
     */
    public ShippingOrder(int id, int quantity, OrderStatus status, Computer computer,
                         CustomerInfo customerInfo, Date orderDate, Date finishByDate,
                         ShippingInfo shippingInfo) {
        super(OrderType.SHIPPING, id, quantity, status, computer, customerInfo, orderDate, finishByDate);
        this.shippingInfo = shippingInfo;
    }

    /**
     * Constructs a new {@code ShippingOrder} with specified parameters.
     *
     * @param id            The unique identifier of the order.
     * @param quantity      The quantity of computers in the order.
     * @param status        The status of the order.
     * @param computer      The computer associated with the order.
     * @param customerInfo  The customer information associated with the order.
     * @param shippingInfo  The shipping information associated with the order.
     */
    public ShippingOrder(int id, int quantity, OrderStatus status, Computer computer,
                         CustomerInfo customerInfo, ShippingInfo shippingInfo) {
        super(OrderType.SHIPPING, id, quantity, status, computer, customerInfo);
        this.shippingInfo = shippingInfo;
    }

    /**
     * Gets the shipping information associated with the order.
     *
     * @return The shipping information.
     */
    public ShippingInfo getShippingInfo() {
        return shippingInfo;
    }

    /**
     * Sets the shipping information associated with the order.
     *
     * @param shippingInfo The shipping information to set.
     */
    public void setShippingInfo(ShippingInfo shippingInfo) {
        this.shippingInfo = shippingInfo;
    }

    /**
     * Calculates and returns the total cost of the shipping order, including shipping fees.
     * Concept: Polymorphism
     * @return The total cost of the order.
     */
    @Override
    public double totalCost() {
        return quantity * (computer.totalCost() + ShippingInfo.SHIPPING_FEE);
    }

    /**
     * Generates a string representation of the order information, including shipping details.
     * Concept: Polymorphism
     * @return The order information as a formatted string.
     */
    public String orderInfo() {
        return String.format("""
                Shipping Info:
                %s""", shippingInfo);
    }
}
