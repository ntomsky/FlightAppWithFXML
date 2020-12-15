package DAO;

import Domain.CurrentUser;
import Domain.Customer;
import Domain.User;

import java.sql.*;

import static DAO.DBQueries.*;

public class BookedFlights_dataAccess {
    public static int bookFlight(User user, int flightID) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(URL, getDbUserName(), getDbPassword());
        System.out.println("DB Connected");

        //this method checks if user already has a flight reservation for this flight_id.
        // if so, returns "-2" to GUI and popup message
        try {
            PreparedStatement statement = connection.prepareStatement(CHECK_DOUBLE_BOOKING);
            statement.setInt(1,flightID);
            ResultSet bookedReservation = statement.executeQuery();

            while(bookedReservation.next()){
                //checks returned SSN \with activeCustomer's id
                if(bookedReservation.getString(1).equals(((Customer)user).getCustomer_id()));
                    return -2;

            }
        } catch (SQLException throwables) {
            throwables.getStackTrace();
            connection.close();
            throw new SQLException ("error in checking for existing booking");

        }

        //this section checks for flight capacity and if it is 0, return -1
        try {
            PreparedStatement statement = connection.prepareStatement(CHECK_CAPACITY);
            statement.setInt(1,flightID);
            ResultSet currentCap = statement.executeQuery();

            while(currentCap.next()){
                if(currentCap.getInt(1) == 0 ){
                    return -1;
                }
            }
        } catch (SQLException throwables) {
            throwables.getStackTrace();
            connection.close();
            throw new SQLException ("error in quering Fight Capacity");

        }
        //if the above is false, insert new bookings into the Booked_Flights_Table
        try {
            PreparedStatement bookStatement = connection.prepareStatement(BOOK_FLIGHT);
            bookStatement.setInt(1,((Customer)user).getCustomer_id());
            System.out.println(((Customer)user).getCustomer_id());
            bookStatement.setInt(2,flightID);
            bookStatement.executeUpdate();
        } catch (SQLException throwables) {
            connection.close();
            throwables.getStackTrace();
            throw new SQLException("error in booking query");
        }

        //update capacity of the flight to -= 1;
        try {
            PreparedStatement updateCap = connection.prepareStatement(DEBIT_SEAT);
            updateCap.setInt(1,flightID);
            updateCap.executeUpdate();
        } catch (SQLException throwables) {
            connection.close();
            throw  new SQLException("Error in updating flight Capacity");
        }

        return 0;

    }
}
