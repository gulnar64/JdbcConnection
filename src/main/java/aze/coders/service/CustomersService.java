package aze.coders.service;

import aze.coders.entity.Customers;

import java.sql.SQLException;
import java.util.List;

public interface CustomersService {
   Customers findByCustomerId(Integer customerId);
   List<Customers> customersList();
   void insert(Customers customers);
   void insertPrepare(Customers customers);
   Customers update(Integer customerId, String columnName, String columnValue);
   void deleteByCustomerId(Integer customerId);
   String getCustomersName(Integer customerId);
   String getCustomersNameProc(Integer customerId);
   void insertBatch(List<Customers> customersList);

}
