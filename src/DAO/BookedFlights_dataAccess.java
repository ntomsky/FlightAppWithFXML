package DAO;

import Domain.CurrentUser;
import Domain.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static DAO.DBQueries.*;

public class BookedFlights_dataAccess {
//    public static void bookFlight(User user, int flightId) throws ClassNotFoundException, SQLException {
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        Connection connection = DriverManager.getConnection(URL, getDbUserName(), getDbPassword());
//        System.out.println("DB Connected");
//
//        PreparedStatement statement = connection.prepareStatement();
//        statement.setInt(1,flightID);
//
//        statement.executeUpdate();
//
//        connection.close();
//
//    }
}
