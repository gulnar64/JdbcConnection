package aze.coders.service;

import aze.coders.connection.DbConnection;
import aze.coders.entity.Customers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomersServiceImpl implements CustomersService {
    Connection connection = DbConnection.getConnection();
    Statement statement;

    {
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Customers findByCustomerId(Integer customerId) {
        Customers customers = new Customers();
        try {
            String query = "select * from customers where customer_id = '" + customerId + "'";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                customers.setId(resultSet.getInt(1));
                customers.setCustomerName(resultSet.getString(2));
                customers.setAddress(resultSet.getString("address"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customers;
    }

    @Override
    public List<Customers> customersList() {
        List<Customers> customersList = new ArrayList<>();
        String query = "select * from customers order by customer_id";
        ResultSet resultSet;
        try {
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Customers customers = new Customers();
                customers.setId(resultSet.getInt(1));
                customers.setCustomerName(resultSet.getString(2));
                customers.setAddress(resultSet.getString("address"));
                customersList.add(customers);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customersList;
    }

    @Override
    public void insert(Customers customers) {
        String query = "insert into customers(customer_id , customer_name, address) values ('"
                + customers.getId() + "', '"
                + customers.getCustomerName() + "','"
                + customers.getAddress() + "')";
        try {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public Customers update(Integer customerId, String columnName, String columnValue) {
        String query = "update customers set " + columnName + "='" + columnValue + "' where customer_id = '" + customerId + "'";
        try {
            statement.executeUpdate(query);
            return findByCustomerId(customerId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
