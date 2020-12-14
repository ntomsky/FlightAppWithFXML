package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static DAO.DBQueries.*;

public class BookedFlights_dataAccess {
    public static void bookFlight() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(URL, getDbUserName(), getDbPassword());
        System.out.println("DB Connected");


    }
}
