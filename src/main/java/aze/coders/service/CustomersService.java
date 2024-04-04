package aze.coders.service;

import aze.coders.entity.Customers;

import java.sql.SQLException;
import java.util.List;

public interface CustomersService {
   Customers findByCustomerId(Integer customerId);
   List<Customers> customersList();
   void insert(Customers customers);
   Customers update(Integer customerId, String columnName, String columnValue);

}
