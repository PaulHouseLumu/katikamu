package DButils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/*
 ** Handles the database connection
 */
public class DButil {

    String className, URL, user, password;
    Connection connection;

    public DButil() {
        this.className = "com.mysql.cj.jdbc.Driver";
        this.URL = "jdbc:mysql://localhost:3306/katikamudb";
        this.user = "root";
        this.password ="";
        this.connection = null;
    }

    public Connection getConnection() {
        //Load the driver class
        try {
            Class.forName(className);
        } catch (ClassNotFoundException ex) {
            System.out.println("Unable to load the class. Terminating the program");
            System.exit(-1);
        }
        //get the connection
        try {
            connection = DriverManager.getConnection(URL, user, password);
        } catch (SQLException ex) {
            System.out.println("Error getting connection: " + ex.getMessage());
            System.exit(-1);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            System.exit(-1);
        }

        return connection;
    }


}