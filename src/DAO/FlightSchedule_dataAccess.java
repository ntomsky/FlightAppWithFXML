package DAO;

import Domain.Flight;
import Domain.FlightBuilder;

import java.sql.*;
import java.util.ArrayList;

import static DAO.DBQueries.*;
import static DAO.DBQueries.getDbUserName;

public class FlightSchedule_dataAccess {
        public static ArrayList<Flight> getListOfFlights() throws ClassNotFoundException, SQLException {

        ArrayList<Flight> listOfFlights = new ArrayList<>();


        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(URL, getDbUserName(), getDbPassword());
        System.out.println("DB Connected");

        PreparedStatement statement = connection.prepareStatement(QUERY_FLIGHTS);

            ResultSet rs = statement.executeQuery();

            try {
                while (rs.next()) {
                    //fetching data from DB, creating anonymous Flight obj using FlightBuilder
                    //then adding flights to ArrayList <Flights>
                    listOfFlights.add(new FlightBuilder()
                            .setFlightID(rs.getInt(1))
                            .setAirlineName(rs.getString(2))
                            .setDepartureCity(rs.getString(3))
                            .setDestinationCity(rs.getString(4))
                            .setDepartureDate((rs.getDate(5)).toLocalDate())
                            .setDepartureTime((rs.getTime(6)).toLocalTime())
                            .setArrivalTime((rs.getTime(7)).toLocalTime())
                            .setTicketPrice(rs.getDouble(8))
                            .setFlightCapacity(rs.getInt(9))
                            .createFlight()
                    );
                }
            }
            finally {
                connection.close();
            }

            //return an array of flights from FlightsSchedule table
        return listOfFlights;
        }
        public static int retrieveAirlineID(String AirlineName) throws SQLException, ClassNotFoundException {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(URL, getDbUserName(), getDbPassword());
            System.out.println("DB Connected");

            PreparedStatement statement = connection.prepareStatement(PULL_AIRLINE_ID);
            statement.setString(1,AirlineName);

            //process the query
            try {
                ResultSet resultSet = statement.executeQuery();
                if(resultSet.next())
                    return (resultSet.getInt(1));
            }
            finally {
                connection.close();
            }
            return -1;

        }
        public static String retrieveAirlineName(int airlineID) throws SQLException, ClassNotFoundException {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(URL, getDbUserName(), getDbPassword());
            System.out.println("DB Connected");

            PreparedStatement statement = connection.prepareStatement(PULL_AIRLINE_NAME);
            statement.setInt(1,airlineID);

            //process the query
            try {
                ResultSet resultSet = statement.executeQuery();
                if(resultSet.next())
                    return (resultSet.getString(1));
            }
            finally {
                connection.close();
            }
            return "NOT FOUND";
        }
        public static void addNewFlight(Flight flight) throws SQLException, ClassNotFoundException {
            int airlineID = retrieveAirlineID(flight.getAirlineName());

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(URL, getDbUserName(), getDbPassword());
            System.out.println("DB Connected");

            PreparedStatement statement = connection.prepareStatement(ADD_NEW_FLIGHT);
            statement.setInt(1,flight.getFlightID());
            statement.setInt(2,airlineID);
            statement.setString(3,flight.getDepartureCity());
            statement.setTime(4,Time.valueOf(flight.getDepartureTime()));
            statement.setDate(5,Date.valueOf(flight.getDepartureDate()));
            statement.setString(6,flight.getArrivalCity());
            statement.setTime(7,Time.valueOf(flight.getArrivalTime()));
            statement.setDouble(8,flight.getTicketPrice());
            statement.setInt(9,flight.getFlightCapacity());

            statement.executeUpdate();

            System.out.println("New Flight Added to DB");

            connection.close();
        }
//        public static void flightID
    }

