
package info;

/**
 * <p>ICS4U</p>
 * <p>AY Jackson SS</p>
 *
 * <p>This {@code CustomerInfo} class represents customer information, including first name, last name,
 * email, phone number, and address. It provides methods to access and modify
 * this information.</p>
 *
 * @author Meherzad Antia
 * @author Stanley Gu
 * @author Daniel Li
 * @since 2024-01-19
 */
public class CustomerInfo {
    /**
     * Represents the first name of the customer. Concept: Encapsulation
     */
    private String firstName;

    /**
     * Represents the last name of the customer. Concept: Encapsulation
     */
    private String lastName;

    /**
     * Represents the email address of the customer. Concept: Encapsulation
     */
    private String email;

    /**
     * Represents the phone number of the customer. Concept: Encapsulation
     */
    private String phoneNumber;

    /**
     * Represents the address of the customer. Concept: Encapsulation
     */
    private String address;


    /**
     * Constructs a CustomerInfo object with the specified customer details.
     *
     * @param firstName   the first name of the customer
     * @param lastName    the last name of the customer
     * @param email       the email address of the customer
     * @param phoneNumber the phone number of the customer
     * @param address     the address of the customer
     */
    public CustomerInfo(String firstName, String lastName, String email, String phoneNumber, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    /**
     * Gets the first name of the customer.
     *
     * @return the first name of the customer
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Gets the last name of the customer.
     *
     * @return the last name of the customer
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Gets the email address of the customer.
     *
     * @return the email address of the customer
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the phone number of the customer.
     *
     * @return the phone number of the customer
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Gets the address of the customer.
     *
     * @return the address of the customer
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the first name of the customer.
     *
     * @param firstName the first name to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Sets the last name of the customer.
     *
     * @param lastName the last name to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Sets the email address of the customer.
     *
     * @param email the email address to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Sets the phone number of the customer.
     *
     * @param phoneNumber the phone number to set
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Sets the address of the customer.
     *
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Returns a string representation of the customer information,
     * including details such as first name, last name, email, phone number, and address.
     * Concept: Polymorphism
     * @return a formatted string representing the customer information
     */
    public String toString() {
        return String.format("""
                First Name: %s
                Last Name: %s
                Email: %s
                Phone Number: %s
                Address: %s""", firstName, lastName, email, phoneNumber, address);
    }
}
