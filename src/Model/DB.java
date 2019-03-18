package Model;

import java.sql.*;
import java.util.Properties;


public class DB {
    
    // JDBC Connection
    public Connection getConnection() throws SQLException {

        Connection dbConnection = null;

        try {
            String url = "jdbc:mysql://localhost:3306/travelexperts";
            Properties info = new Properties();
            info.put("user", "test");
            info.put("password", "test");

            // load and register JDBC driver for MySQL

            Class.forName("com.mysql.jdbc.Driver");

            dbConnection = DriverManager.getConnection(url, info);

            if (dbConnection != null) {
                System.out.println(dbConnection);
                //  System.out.println("SQL Connection created");
            }

        } catch (SQLException ex) {
            //System.out.println("An error occurred while connecting MySQL database");
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            // System.out.println("An error occurred while connecting MySQL Classes");
            ex.printStackTrace();

        }
        return dbConnection;
    }

}
