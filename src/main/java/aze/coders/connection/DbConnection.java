package aze.coders.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static final String url ="jdbc:postgresql://localhost:5432/DB";
    private static final String username ="postgres";
    private static final String password ="12345";
    private static Connection connection;
    public static Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            connection =  DriverManager.getConnection(url, username, password);
            return connection;
        } catch (SQLException e) {
            System.out.println("Xeta");
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            System.out.println("Xeta driver");
            throw new RuntimeException(e);
        }
    }
    public static void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("Xeta close");
            throw new RuntimeException(e);
        }
    }
}
