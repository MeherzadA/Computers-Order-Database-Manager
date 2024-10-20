package order;

import computer.Computer;
import info.CustomerInfo;
import utils.DateUtils;

import java.util.Date; // Date class!

/**
 * <p>ICS4U</p>
 * <p>AY Jackson SS</p>
 *
 * This abstract class represents an order for a computer, containing information such as order type,
 * customer details, computer specifications, and order status.
 * <p>
 * The class provides methods to manage and retrieve order-related information,
 * calculate the total cost, check if the order is overdue, and compare orders based on different criteria.
 * </p>
 * <p>Concept: Abstract Class</p>
 * @author Meherzad Antia
 * @author Stanley Gu
 * @author Daniel Li
 * @since 2024-01-19
 */
public abstract class Order {
    /**
     * The type of the order.
     */
    protected final String type;

    /**
     * Customer information associated with the order.
     */
    protected CustomerInfo customerInfo;

    /**
     * The computer related to the order.
     */
    protected Computer computer;

    /**
     * The unique identifier for the order.
     */
    protected int id;

    /**
     * The status of the order.
     */
    protected OrderStatus orderStatus;

    /**
     * The quantity of the items in the order.
     */
    protected int quantity;

    /**
     * The date when the order was placed.
     */
    protected Date orderDate;

    /**
     * The date by which the order should be finished (1 week after the order date).
     */
    protected Date finishByDate;

    /**
     * Constructs a new order with the given parameters, used during adding a new order where the order/expiry dates are
     * determined automatically based on current time.
     *
     * @param type        The type of the order.
     * @param id          The unique identifier for the order.
     * @param quantity    The quantity of items in the order.
     * @param orderStatus The status of the order.
     * @param computer    The computer associated with the order.
     * @param customerInfo The customer information associated with the order.
     */
    public Order(String type, int id, int quantity, OrderStatus orderStatus, Computer computer, CustomerInfo customerInfo){
        this.type = type;
        this.id = id;
        this.quantity = quantity;
        this.orderStatus = orderStatus;
        this.computer = computer;
        this.customerInfo = customerInfo;

        orderDate = new Date();
        finishByDate = DateUtils.addDays(orderDate, 7);
    }

    /**
     * Constructs a new order with the given parameters, used during loading orders from database.
     *
     * @param type         The type of the order.
     * @param id           The unique identifier for the order.
     * @param quantity     The quantity of items in the order.
     * @param orderStatus  The status of the order.
     * @param computer     The computer associated with the order.
     * @param customerInfo The customer information associated with the order.
     * @param orderDate    The date when the order was placed.
     * @param finishByDate The date by which the order should be finished.
     */
    public Order(String type, int id, int quantity, OrderStatus orderStatus, Computer computer, CustomerInfo customerInfo, Date orderDate, Date finishByDate){
        this.type = type;
        this.id = id;
        this.quantity = quantity;
        this.orderStatus = orderStatus;
        this.computer = computer;
        this.customerInfo = customerInfo;
        this.orderDate = orderDate;
        this.finishByDate = finishByDate;
    }


    /**
     * Retrieves the type of the order.
     *
     * @return The type of the order.
     */
    public String getType() {
        return type;
    }

    /**
     * Retrieves the customer information associated with the order.
     *
     * @return The customer information of the order.
     */
    public CustomerInfo getCustomerInfo() {
        return customerInfo;
    }

    /**
     * Retrieves the computer associated with the order.
     *
     * @return The computer associated with the order.
     */
    public Computer getComputer() {
        return computer;
    }

    /**
     * Retrieves the unique identifier of the order.
     *
     * @return The unique identifier of the order.
     */
    public int getId() {
        return id;
    }

    /**
     * Retrieves the status of the order.
     *
     * @return The status of the order.
     */
    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    /**
     * Retrieves the quantity of items in the order.
     *
     * @return The quantity of items in the order.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Retrieves the date when the order was placed.
     *
     * @return The date when the order was placed.
     */
    public Date getOrderDate() {
        return orderDate;
    }

    /**
     * Retrieves the date by which the order should be finished.
     *
     * @return The date by which the order should be finished.
     */
    public Date getFinishByDate() {
        return finishByDate;
    }

    /**
     * Sets the customer information associated with the order.
     *
     * @param customerInfo The new customer information.
     */
    public void setCustomerInfo(CustomerInfo customerInfo) {
        this.customerInfo = customerInfo;
    }

    /**
     * Sets the computer associated with the order.
     *
     * @param computer The new computer associated with the order.
     */
    public void setComputer(Computer computer) {
        this.computer = computer;
    }

    /**
     * Sets the unique identifier of the order.
     *
     * @param id The new unique identifier for the order.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Sets the quantity of items in the order.
     *
     * @param quantity The new quantity of items in the order.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Sets the date when the order was placed.
     *
     * @param orderDate The new date when the order was placed.
     */
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * Sets the date by which the order should be finished.
     *
     * @param finishByDate The new date by which the order should be finished.
     */
    public void setFinishByDate(Date finishByDate) {
        this.finishByDate = finishByDate;
    }

    /**
     * Calculates the total cost of the order.
     * Concept: Abstract Method
     * @return The total cost of the order.
     */
    public abstract double totalCost();

    /**
     * Retrieves additional information about the order.
     * Concept: Abstract Method
     * @return A string containing detailed information about the order.
     */
    public abstract String orderInfo();

    /**
     * Checks if the order is overdue.
     *
     * @return {@code true} if the order is overdue, {@code false} otherwise.
     */
    public boolean isOverdue() {
        Date currDate = new Date();
        return currDate.compareTo(finishByDate) > 0;
    }

    /**
     * Compares the ID of this order with another order.
     *
     * @param other The other order to compare.
     * @return A negative value if the ID of this order is less than the other order,
     *         zero if they are equal, and a positive value if greater.
     */
    public int compareToId(Order other) {
        return id - other.id;
    }

    /**
     * Compares the total cost of this order with another order.
     *
     * @param other The other order to compare.
     * @return A negative value if the total cost of this order is less than the other order,
     *         zero if they are equal, and a positive value if greater.
     */
    public double compareToTotalCost(Order other){
        return totalCost() - other.totalCost();
    }

    /**
     * Compares the finish-by date of this order with another order.
     *
     * @param other The other order to compare.
     * @return A negative value if the finish-by date of this order is earlier than the other order,
     *         zero if they are equal, and a positive value if later.
     */
    public int compareToFinishByDate(Order other) {
        return finishByDate.compareTo(other.finishByDate);
    }

    /**
     * Compares the order status of this order with another order.
     *
     * @param other The other order to compare.
     * @return A negative value if the order status of this order is less than the other order,
     *         zero if they are equal, and a positive value if greater.
     */
    public int compareToOrderStatus(Order other) {
        return orderStatus.compareTo(other.orderStatus);
    }

    /**
     * Updates the order status based on the provided index.
     *
     * @param index The index representing the new order status.
     */
    public void updateStatus(int index){
        orderStatus = OrderStatus.defaultByIndex(index);
    }

    /**
     * Updates the order status to the specified status.
     *
     * @param orderStatus The new order status.
     */

    public void updateStatus(OrderStatus orderStatus){
        this.orderStatus = orderStatus;
    }

    /**
     * Returns a string representation of the order.
     * Concept: Polymorphism
     * @return A formatted string containing order information.
     */
    public String toString(){
        return String.format("""
                ID: %s
                Current Status: %s
                Quantity: %d
                Date Ordered: %s
                Date to be Finished By: %s
                Customer Information:
                %s
                %s
                Computer:
                %s""", id, orderStatus, quantity, orderDate, finishByDate, customerInfo, orderInfo(), computer);
    }
}
