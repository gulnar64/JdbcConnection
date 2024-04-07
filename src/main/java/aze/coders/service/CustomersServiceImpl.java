package aze.coders.service;

import aze.coders.connection.DbConnection;
import aze.coders.entity.Customers;

import java.sql.*;
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
            ResultSetMetaData metaData = resultSet.getMetaData();
            for(int i=1; i <=metaData.getColumnCount();i++) {
                System.out.println("column: " + metaData.getColumnName(i) + ", type: " + metaData.getColumnTypeName(i));
            }
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

    public void insertPrepare(Customers customers) {
        String query = "insert into customers(customer_id , customer_name, address) values (?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, customers.getId());
            preparedStatement.setString(2, customers.getCustomerName());
            preparedStatement.setString(3, customers.getAddress());
            preparedStatement.executeUpdate();
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

    @Override
    public void deleteByCustomerId(Integer customerId) {
        String query = "delete from customers where customer_id = " + customerId;
        try {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public String getCustomersName(Integer customerId) {
        String name = "";
        String query = "{ ?= call get_customers(?)}";
        try {
            CallableStatement callableStatement = connection.prepareCall(query);
            callableStatement.registerOutParameter(1, Types.VARCHAR);
            callableStatement.setInt(2, customerId);
            callableStatement.execute();
            name = (String) callableStatement.getObject(1);
            return name;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
//        finally {
//            try {
//                connection.close();
//            } catch (SQLException e) {
//                System.out.println("connection close fail");;
//            }
//        }
    }

    @Override
    public String getCustomersNameProc(Integer customerId) {
        String name = "";
        String query = "call get_customers_proc(?, ?)";
        try {
            CallableStatement callableStatement = connection.prepareCall(query);
            callableStatement.setInt(1, customerId);
            callableStatement.registerOutParameter(2, Types.VARCHAR);
            callableStatement.execute();
            name = (String) callableStatement.getObject(2);
            return name;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insertBatch(List<Customers> customersList) {
        try {
            String query = "insert into customers(customer_id , customer_name, address) values (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            for (Customers customers : customersList) {
                preparedStatement.setInt(1, customers.getId());
                preparedStatement.setString(2, customers.getCustomerName());
                preparedStatement.setString(3, customers.getAddress());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
