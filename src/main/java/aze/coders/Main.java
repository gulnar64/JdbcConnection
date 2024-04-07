package aze.coders;

import aze.coders.entity.Customers;
import aze.coders.service.CustomersService;
import aze.coders.service.CustomersServiceImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
        Customers customers = new Customers(552214, "sxsdfdvgdfd", "addd");
//        customersService.insertPrepare(customers);
        System.out.println("************************");
        System.out.println("update");
        System.out.println(customersService.update(3, "customer_name", "HVUBJAKLDxsd"));
        customersService.deleteByCustomerId(552213);
        System.out.println("************************");
        System.out.println("call function");
        System.out.println(customersService.getCustomersName(552214));
        System.out.println("************************");
        System.out.println("call procedure");
        System.out.println(customersService.getCustomersNameProc(552214));
        System.out.println("************************");
        System.out.println("insert batch");
        List<Customers> customersList = new ArrayList<>();
        for (int i = 1; i <= 10; i++)
            customersList.add(new Customers(552214 + i, "sxsdfdvgdfd" + i, "addd" + i));
        customersService.insertBatch(customersList);


    }
}