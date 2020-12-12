package DAO;

import Domain.*;

import static DAO.DBQueries.*;
import java.sql.*;
import java.util.regex.Pattern;

public class Customer_dataAccess {
    public static boolean isUsernameValid(String credential) throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(URL, getDbUserName(),getDbPassword());
        System.out.println("DB Connected");

        PreparedStatement statement = connection.prepareStatement(USERNAME_VALIDATION);
        statement.setString(1,credential);

        try {
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        }
        finally {
            connection.close();
        }
    }
    public static boolean isPasswordValid(String username, String password) throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(URL, getDbUserName(),getDbPassword());
        System.out.println("DB Connected");

        PreparedStatement statement = connection.prepareStatement(PASSWORD_VALIDATION);
        statement.setString(1,username);

        try {
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next())
                return password.equals(resultSet.getString(1));
        }
        finally {
            connection.close();
        }
        return false;
    }
    public static void registerNewCustomer(Customer customer) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(URL, getDbUserName(), getDbPassword());
        System.out.println("DB Connected");

        //created a new entry in the Users DB
        PreparedStatement statement = connection.prepareStatement(REGISTER_NEW_CUSTOMER);

        statement.setInt(1, customer.getCustomer_id());
        statement.setString(2, customer.getUserName());
        statement.setString(3, customer.getUserPassword());
        statement.setString(4, customer.getFirstName());
        statement.setString(5, customer.getLastName());
        statement.setString(6, customer.getStateAddress());
        statement.setString(7, customer.getCityAddress());
        statement.setString(8, customer.getStateAddress());
        statement.setString(9, customer.getZipAddress());
        statement.setString(10, customer.getUserPhoneNumber());
        statement.setString(11, customer.getUserEmail());
        statement.setString(12, customer.getSecurityQuestion());
        statement.setString(13, customer.getSecurityAnswer());
        statement.setBoolean(14,false);

        //execute Query
        statement.executeUpdate();
        System.out.println("new customer added to DB");

        connection.close();

    }
}
