
package info;

/**
 * <p>ICS4U</p>
 * <p>AY Jackson SS</p>
 *
 * <p>This {@code ShippingInfo} class represents information related to shipping, including billing address,
 * shipping address, and shipping company. It provides methods to access and modify
 * this information.</p>
 *
 * @author Meherzad Antia
 * @author Stanley Gu
 * @author Daniel Li
 * @since 2024-01-19
 */
public class ShippingInfo {
    /**
     * Constant representing the shipping fee.
     */
    public static final double SHIPPING_FEE = 100;

    /**
     * Represents the billing address for shipping. Concept: Encapsulation
     */
    private String billingAddress;

    /**
     * Represents the shipping address for the customer. Concept: Encapsulation
     */
    private String shippingAddress;

    /**
     * Represents the shipping company for delivery. Concept: Encapsulation
     */
    private String shippingCompany;


    /**
     * Constructs a ShippingInfo object with the specified billing, shipping addresses,
     * and shipping company.
     *
     * @param billingAddress   the billing address for shipping
     * @param shippingAddress  the shipping address for the customer
     * @param shippingCompany  the shipping company for delivery
     */
    public ShippingInfo(String billingAddress, String shippingAddress, String shippingCompany){
        this.billingAddress = billingAddress;
        this.shippingAddress = shippingAddress;
        this.shippingCompany = shippingCompany;
    }

    /**
     * Gets the billing address for shipping.
     *
     * @return the billing address for shipping
     */
    public String getBillingAddress() {
        return billingAddress;
    }

    /**
     * Gets the shipping address for the customer.
     *
     * @return the shipping address for the customer
     */
    public String getShippingAddress() {
        return shippingAddress;
    }

    /**
     * Gets the shipping company for delivery.
     *
     * @return the shipping company for delivery
     */
    public String getShippingCompany() {
        return shippingCompany;
    }

    /**
     * Sets the billing address for shipping.
     *
     * @param billingAddress the billing address to set
     */
    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    /**
     * Sets the shipping address for the customer.
     *
     * @param shippingAddress the shipping address to set
     */
    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    /**
     * Sets the shipping company for delivery.
     *
     * @param shippingCompany the shipping company to set
     */
    public void setShippingCompany(String shippingCompany) {
        this.shippingCompany = shippingCompany;
    }

    /**
     * Returns a string representation of the shipping information,
     * including details such as billing address, shipping address, and shipping company.
     * Concept: Polymorphism
     * @return a formatted string representing the shipping information
     */
    public String toString(){
        return String.format("""
                Billing Address: %s
                Shipping Address: %s
                Shipping Company: %s
                Shipping Fee: $%.2f / package""", billingAddress, shippingAddress, shippingCompany, SHIPPING_FEE);
    }
}
