/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Carlos
 */
public class SqlConnect {
    private static final String DATABASE_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/DSS";
    private static final String USERNAME = "DSS";
    private static final String PASSWORD = "dss1516";

    // init connection object
    private  static Connection connection;
    
    public static Connection connect() throws ClassNotFoundException {
        if (connection == null) {
        try {
            Class.forName(DATABASE_DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD); 
        }   catch (SQLException e) {
             throw new IllegalStateException("Cannot connect the database!", e);
            }
        }
        return connection;
    }
}
