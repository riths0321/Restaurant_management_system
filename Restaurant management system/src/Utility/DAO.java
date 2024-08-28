package Utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAO
{
    // singleton class -- essi class jiska ek obj banega
    String url  = "jdbc:mysql://localhost:3306/restaurant_management_system";
    String dbname  = "root" ;
    String dbpass  = "6264@#Mysql" ;

    // isi class ka cont private ---
    private DAO()
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    private static DAO connectionFactory = null  ;

    public static DAO getConnectionFactory()
    {
        if ( connectionFactory  ==  null)
        {
            connectionFactory   = new DAO() ;
        }
        return connectionFactory ;
    }


    public Connection getConnection() throws SQLException
    {

        Connection con = DriverManager.getConnection(url, dbname, dbpass) ;
        return con  ;

    }

}