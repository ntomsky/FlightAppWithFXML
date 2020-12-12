package DAO;

import java.sql.*;

import static DAO.DBQueries.*;

public class Admin_dataAccess {
    public static boolean isAdmin(String username) throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(URL, getDbUserName(), getDbPassword());
        System.out.println("DB Connected");

        PreparedStatement statement = connection.prepareStatement(CHECK_IF_USER_ADMIN_QUERY);
        statement.setString(1, username);

        try {
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next())
            return resultSet.getBoolean(1);
        } finally {
            connection.close();
        }
        return false;
    }
}
