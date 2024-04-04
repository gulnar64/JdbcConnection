package aze.coders;

import aze.coders.entity.Customers;
import aze.coders.service.CustomersService;
import aze.coders.service.CustomersServiceImpl;

import java.sql.SQLException;

public class Main {
    private int i = 5;
    private static final CustomersService customersService = new CustomersServiceImpl();

    public static void main(String[] args) {
        System.out.println("findById");
        System.out.println(customersService.findByCustomerId(6));
        System.out.println("************************");
        System.out.println("findAll");
        customersService.customersList().forEach(customers -> System.out.println(customers));
        System.out.println("************************");
        System.out.println("insert");
        Customers customers = new Customers(552213,"sxsdfd", "addd");
        customersService.insert(customers);
        System.out.println("************************");
        System.out.println("update");
        System.out.println(customersService.update(3, "customer_name", "HVUBJAKLDxsd"));
    }
}