package DAO;

public class DBQueries {
    private static final String dbUserName = "root";
    private static final String dbPassword = "pioneer1";
    public static final String URL = "jdbc:mysql://localhost:3306/flight_reservation_app";
    public static final String USERNAME_VALIDATION = "SELECT Username from Users where Username = ?;";
    public static final String PASSWORD_VALIDATION = "SELECT password from Users where Username = ?;";
    public static final String REGISTER_NEW_CUSTOMER = "INSERT into Users values (?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
    public static final String QUERY_FLIGHTS ="SELECT F.Flight_ID, A.Airline_Name, F.Departure_City, F.Arrival_City," +
                                            "F.Departure_Date, F.Departure_Time,\n" +
                                            "F.Arrival_Time, F.Ticket_Price\n" +
                                            "FROM FLIGHT_SCHEDULE F, Airlines A\n" +
                                            "    where A.Airline_ID = F.Airline_ID;";
    public static final String CHECK_IF_USER_ADMIN_QUERY = "SELECT isAdmin FROM Users WHERE Username = ?;";

   public static String getDbUserName() {
      return dbUserName;
   }

   public static String getDbPassword() {
      return dbPassword;
   }
}
