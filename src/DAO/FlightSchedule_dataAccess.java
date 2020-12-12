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
                            .setArrivalCity(rs.getString(4))
                            .setDepartureDate((rs.getDate(5)).toLocalDate())
                            .setDepartureTime((rs.getTime(6)).toLocalTime())
                            .setArrivalTime((rs.getTime(7)).toLocalTime())
                            .setTicketPrice(rs.getDouble(8))
                            .createFlight()
                    );
                }
            }
            finally {
                connection.close();
            }

        return listOfFlights;
        }

    }

