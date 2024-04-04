package aze.coders.entity;

public class Customers {
    private int id;
    private String customerName;
    private String address;

    public Customers(int id, String customerName, String address) {
        this.id = id;
        this.customerName = customerName;
        this.address = address;
    }

    public Customers() {

    }

    @Override
    public String toString() {
        return "Customers{" +
                "id=" + id +
                ", customerName='" + customerName + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
