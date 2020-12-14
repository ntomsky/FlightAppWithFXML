package DAO;

import Domain.*;

import static DAO.DBQueries.*;
import java.sql.*;
import java.util.ArrayList;

public class Customer_dataAccess {
    //Validating username
    public static boolean isUsernameValid(String credential) throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(URL, getDbUserName(),getDbPassword());
        System.out.println("DB Connected");

        PreparedStatement statement = connection.prepareStatement(USERNAME_VALIDATION);
        statement.setString(1,credential);

            ResultSet resultSet = statement.executeQuery();
           try {
               if (resultSet.next())
                   return true;
               else
                   return false;
           }
           finally {
            connection.close();
           }
    }

    //validating password
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

    //adding new customer to DB
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

    //getting a list of customers for tableview population
    public static ArrayList<Customer> getListOfCustomers() throws ClassNotFoundException, SQLException {
       ArrayList<Customer> listOfCustomers = new ArrayList<>();

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(URL, getDbUserName(), getDbPassword());
        System.out.println("DB Connected");

        //created a new entry in the Users DB
        PreparedStatement statement = connection.prepareStatement(LIST_OF_CUSTOMERS);

        //execute Query
        ResultSet rs = statement.executeQuery();

        //create list of customers
        while(rs.next()) {
            //fetching data from DB, creating anonymous Flight obj using FlightBuilder
            //then adding flights to ArrayList <Flights>
            listOfCustomers.add(new CustomerBuilder().setId(rs.getInt(1)).setUsername(rs.getString(2)).setPwd(rs.getString(3)).setFirstName(rs.getString(4)).setLastName(rs.getString(5)).createCustomer());

        }

        connection.close();

        return listOfCustomers;
    }

    //Remove custome from the DB
    public static void deleteCustomer(int customer_id) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(URL, getDbUserName(), getDbPassword());
        System.out.println("DB Connected");

        PreparedStatement statement = connection.prepareStatement(DELETE_CUSTOMER);
        statement.setInt(1,customer_id);

        statement.executeUpdate();
        connection.close();
    }
}
