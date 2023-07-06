package sample.vet;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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

    public static void main(String[] args) throws ClassNotFoundException {
        DbConnection connection1 = new DbConnection();
    }
    public String hashPass(String pass) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        assert md5 != null;
        byte[] bytes = md5.digest(pass.getBytes());
        StringBuilder hashPass = new StringBuilder();
        for (byte b : bytes) {
            hashPass.append(String.format("%02X", b));
        }
        return hashPass.toString();
    }
}