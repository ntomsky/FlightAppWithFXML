package DAO;

import Domain.*;

import java.sql.*;
import java.util.ArrayList;

import static DAO.DBQueries.*;

public class BookedFlights_dataAccess {
    //method is the db request for checking capacity, doublebooking, placing a reservation and
    // controls number of available seats
    public static int bookFlight(User user, int flightID) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(URL, getDbUserName(), getDbPassword());
        System.out.println("DB Connected");

        //this method checks if user already has a flight reservation for this flight_id.
        // if so, returns "-2" to GUI and popup message
        try {
            PreparedStatement statement = connection.prepareStatement(CHECK_DOUBLE_BOOKING);
            statement.setInt(1, flightID);
            ResultSet bookedReservation = statement.executeQuery();

            while (bookedReservation.next()) {
                //checks returned SSN \with activeCustomer's id
                if (bookedReservation.getString(1).equals(((Customer) user).getCustomer_id())) ;
                return -2;

            }
        } catch (SQLException throwables) {
            throwables.getStackTrace();
            connection.close();
            throw new SQLException("error in checking for existing booking");

        }

        //this section checks for flight capacity and if it is 0, return -1
        try {
            PreparedStatement statement = connection.prepareStatement(CHECK_CAPACITY);
            statement.setInt(1, flightID);
            ResultSet currentCap = statement.executeQuery();

            while (currentCap.next()) {
                if (currentCap.getInt(1) == 0) {
                    return -1;
                }
            }
        } catch (SQLException throwables) {
            throwables.getStackTrace();
            connection.close();
            throw new SQLException("error in quering Fight Capacity");

        }
        //if the above is false, insert new bookings into the Booked_Flights_Table
        try {
            PreparedStatement bookStatement = connection.prepareStatement(BOOK_FLIGHT);
            bookStatement.setInt(1, ((Customer) user).getCustomer_id());
            System.out.println(((Customer) user).getCustomer_id());
            bookStatement.setInt(2, flightID);
            bookStatement.executeUpdate();
        } catch (SQLException throwables) {
            connection.close();
            throwables.getStackTrace();
            throw new SQLException("error in booking query");
        }

        //update capacity of the flight to -= 1;
        try {
            PreparedStatement updateCap = connection.prepareStatement(DEBIT_SEAT);
            updateCap.setInt(1, flightID);
            updateCap.executeUpdate();
        } catch (SQLException throwables) {
            connection.close();
            throw new SQLException("Error in updating flight Capacity");
        }

        return 0;

    }

    //this method returns the list of confirmed booked flights based on customers id
    public static ArrayList<Flight> getBookings(User user) throws ClassNotFoundException, SQLException {

        ArrayList<Flight> listOfBooked = new ArrayList<>();
        ArrayList<Integer> listOfFlighID = new ArrayList<>();


        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(URL, getDbUserName(), getDbPassword());
        System.out.println("DB Connected");


        //this section retrieves booked Flight_IDs
        PreparedStatement statement = connection.prepareStatement(QUERY_BOOKED_FLIGHT_IDs);

        statement.setInt(1,((Customer)user).getCustomer_id());

        ResultSet rs = statement.executeQuery();

        while(rs.next()){
            listOfFlighID.add(rs.getInt(1));
        }

        //this section queries of the schedules flights and pull them based previous method

        PreparedStatement statement2 = connection.prepareStatement(BOOKED_FLIGHTS);

        try {

            for (Integer i : listOfFlighID) {
                statement2.setInt(1, i);

                ResultSet resultSet = statement2.executeQuery();

                while (resultSet.next()) {
                    //fetching data from DB, creating anonymous Flight obj using FlightBuilder
                    //then adding flights to ArrayList <Flights>
                    listOfBooked.add(new FlightBuilder()
                            .setFlightID(resultSet.getInt(1))
                            .setAirlineName(resultSet.getString(2))
                            .setDepartureCity(resultSet.getString(3))
                            .setDestinationCity(resultSet.getString(4))
                            .setDepartureDate((resultSet.getDate(5)).toLocalDate())
                            .setDepartureTime((resultSet.getTime(6)).toLocalTime())
                            .setArrivalTime((resultSet.getTime(7)).toLocalTime())
                            .setTicketPrice(resultSet.getDouble(8))
                            .setFlightCapacity(resultSet.getInt(9))
                            .createFlight()
                    );
                }
            }
        }
        finally {
                connection.close();
            }
        
        return listOfBooked;
    }
}
