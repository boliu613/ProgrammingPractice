package banks;

/**
 * A class that represents a single bank customer
 * Every customer of the bank has a unique ID that is auto generated.
 * The bank keeps a record of the customer's name, address and phone.
 * @author Arvind
 *
 */
public class Customer {
    
    private static int counter = 1729;
    private String name;
    private String address;
    private String phone;
    private int customerID;
    
    /**
     * create customer with a specific name.
     * customerID is automatically assigned.
     * @param name
     */
    public Customer(String name) {
        this.name = name;
        customerID = counter;
        counter += 1;
    }

    /**
     * get the customer's name
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * set customer's name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * get customer's address
     * @return
     */
    public String getAddress() {
        return address;
    }

    /**
     * set customer's address
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * get phone number
     * @return
     */
    public String getPhone() {
        return phone;
    }

    /**
     * set the phone number 
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * get the customer id for this customer
     * @return
     */
    public int getCustomerID() {
        return customerID;
    }
    
    

}
