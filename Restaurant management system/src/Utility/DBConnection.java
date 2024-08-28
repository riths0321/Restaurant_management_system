package Utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection
{
    private static final String url = "jdbc:mysql://localhost:3306/restaurant_management_system";
    private static final String user = "root";
    private static final String password = "6264@#Mysql";
    private static Connection connection = null;

    private DBConnection() {}

    public static Connection getConnection()
    {
        if (connection == null)
        {
            try
            {
                connection = DriverManager.getConnection(url, user, password);
                System.out.println("Your Database is connected successfully.");
            }
            catch (SQLException e)
            {
                e.printStackTrace();
                throw new RuntimeException("404 Error,Unable to connect to the database.", e);
            }
        }
        return connection;
    }

    public static void closeConnection()
    {
        if (connection == null)
        {
            try
            {
                connection.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
                throw new RuntimeException("Unable to close database connection");
            }
            System.out.println("Connection is closed");
        }
    }
}