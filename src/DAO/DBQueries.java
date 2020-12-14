package DAO;

import java.sql.*;

public class DBQueries {
   //Credentails
    private static final String dbUserName = "root";
    private static final String dbPassword = "pioneer1";
    public static final String URL = "jdbc:mysql://localhost:3306/flight_reservation_app";

    //usename and password validation queries
    public static final String USERNAME_VALIDATION = "SELECT Username from Users where Username = ?;";
    public static final String PASSWORD_VALIDATION = "SELECT password from Users where Username = ?;";

    //new customer and new fllight registration
    public static final String REGISTER_NEW_CUSTOMER = "INSERT into Users values (?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
    public static final String ADD_NEW_FLIGHT = "INSERT INTO Flight_Schedule VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);";

    //get list of customers and flights
    public static final String LIST_OF_CUSTOMERS = "SELECT ssn, username, password, first_name, last_name " +
            "FROM Users WHERE isAdmin = false;";
    public static final String QUERY_FLIGHTS ="SELECT F.Flight_ID, A.Airline_Name, F.Departure_City, F.Arrival_City," +
                                            "F.Departure_Date, F.Departure_Time,\n" +
                                            "F.Arrival_Time, F.Ticket_Price, F.Flight_Cap\n" +
                                            "FROM FLIGHT_SCHEDULE F, Airlines A" +
            "                                   WHERE A.Airline_ID = F.Airline_ID;";
    //validation queries
    public static final String CHECK_IF_USER_ADMIN_QUERY = "SELECT isAdmin FROM Users WHERE Username = ?;";
    public static final String PULL_AIRLINE_ID = "SELECT airline_id FROM Airlines WHERE Airline_Name = ?;";
    public static final String PULL_AIRLINE_NAME = "SELECT airline_name FROM Airlines WHERE Airline_ID = ?;";
    public static final String FLIGHT_ID_CHECKER = "SELECT flight_id FROM Flight_Schedule WHERE flight_id = ?;";

    //removal of customers or flights
    public static final String DELETE_FLIGHT = "DELETE FROM Flight_Schedule WHERE flight_id = ?;";
    public static final String DELETE_CUSTOMER = "DELETE FROM Users WHERE SSN = ?;";


    public static String getDbUserName() {
      return dbUserName;
   }

   public static String getDbPassword() {
      return dbPassword;
   }

//   public static PreparedStatement connectToDB(String Query) throws SQLException, ClassNotFoundException {
//       Class.forName("com.mysql.cj.jdbc.Driver");
//       Connection connection = DriverManager.getConnection(URL, getDbUserName(), getDbPassword());
//       System.out.println("DB Connected");
//
//       return connection.prepareStatement(Query);
//
//   }
}
