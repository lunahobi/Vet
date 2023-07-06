package sample.vet;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class DbConnection {
    private static DbConnection instance;
    private Connection connection;

    private DbConnection() throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_2290_vetclinic";
            String username = "std_2290_vetclinic";
            String password = "2036548090";
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static DbConnection getInstance() throws ClassNotFoundException, SQLException {
        if (instance == null) {
            instance = new DbConnection();
        } else if (instance.getConnection().isClosed()) {
            instance = new DbConnection();
        }

        return instance;
    }
    public Connection getConnection() {
        return connection;
    }


    public void registerOwner(Owner owner) throws SQLException {
        String query = "INSERT INTO Users(username, password, role_id) VALUES (?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, owner.getUsername());
        statement.setString(2, owner.getPassword());
        statement.setLong(3, owner.getRole_id());
        statement.executeUpdate();


        String query2 = "INSERT INTO Owners(last_name, first_name, second_name, address, phone_number, user_id) VALUES " +
                "(?, ?, ?, ?, ?, ?)";

        PreparedStatement statement2 = connection.prepareStatement(query2);
        statement2.setString(1, owner.getLast_name());
        statement2.setString(2, owner.getFirst_name());
        statement2.setString(3, owner.getSecond_name());
        statement2.setString(4, owner.getAddress());
        statement2.setString(5, owner.getPhone_number());
        statement2.setInt(6, getUserID(owner.getUsername()));
        statement2.executeUpdate();
    }
    public int getUserID(String username) throws SQLException {
        String query = "SELECT user_id FROM Users WHERE username = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, username);
        ResultSet result = statement.executeQuery();
        int id = 0;
        if (result.next())
            id = result.getInt("user_id");
        return id;
    }
    public boolean isUserNameExists(String username) throws SQLException {
        String query = "SELECT COUNT(*) FROM Users WHERE username = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, username);
        ResultSet resultSet = statement.executeQuery();
        return resultSet.next() && resultSet.getInt(1) > 0;
    }
}
