package DAO;

public class DBQueries {
    private static final String dbUserName = "root";
    private static final String dbPassword = "pioneer1";
    public static final String URL = "jdbc:mysql://localhost:3306/flight_reservation_app";
    public static final String USERNAME_VALIDATION = "SELECT Username from Users where Username = ?";
    public static final String PASSWORD_VALIDATION = "SELECT password from Users where password = ?";
    public static final String REGISTER_NEW_CUSTOMER = "insert into Users values (?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

   public static String getDbUserName() {
      return dbUserName;
   }

   public static String getDbPassword() {
      return dbPassword;
   }
}
