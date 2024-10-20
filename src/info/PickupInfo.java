
package info;

/**
 * <p>ICS4U</p>
 * <p>AY Jackson SS</p>
 *
 * <p>This {@code PickupInfo} class represents information related to the pickup, including billing address
 * and pickup address. It provides methods to access and modify this information.</p>
 *
 * @author Meherzad Antia
 * @author Stanley Gu
 * @author Daniel Li
 * @since 2024-01-19
 */
public class PickupInfo {
    /**
     * Represents the billing address for pickup. Concept: Encapsulation
     */
    private String billingAddress;

    /**
     * Represents the pickup address for the customer. Concept: Encapsulation
     */
    private String pickupAddress;


    /**
     * Constructs a PickupInfo object with the specified billing and pickup addresses.
     *
     * @param billingAddress the billing address for pickup
     * @param pickupAddress  the pickup address for the customer
     */
    public PickupInfo(String billingAddress, String pickupAddress){
        this.billingAddress = billingAddress;
        this.pickupAddress = pickupAddress;
    }

    /**
     * Gets the billing address for pickup.
     *
     * @return the billing address for pickup
     */
    public String getBillingAddress() {
        return billingAddress;
    }

    /**
     * Gets the pickup address for the customer.
     *
     * @return the pickup address for the customer
     */
    public String getPickupAddress() {
        return pickupAddress;
    }

    /**
     * Sets the billing address for pickup.
     *
     * @param billingAddress the billing address to set
     */
    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    /**
     * Sets the pickup address for the customer.
     *
     * @param pickupAddress the pickup address to set
     */
    public void setPickupAddress(String pickupAddress) {
        this.pickupAddress = pickupAddress;
    }

    /**
     * Returns a string representation of the pickup information,
     * including details such as billing address and pickup address.
     * Concept: Polymorphism
     * @return a formatted string representing the pickup information
     */
    public String toString(){
        return String.format("""
                Billing Address: %s
                Pickup Address: %s""", billingAddress, pickupAddress);
    }
}
