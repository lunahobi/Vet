package sample.vet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DbConnection {
    private static DbConnection instance;
    private static Connection connection;

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

    public int getRoleIdByUsername(String username) throws SQLException {
        String query = "SELECT role_id FROM Users  WHERE user_id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setLong(1, getUserID(username));
        ResultSet result = statement.executeQuery();
        int role = 0;
        if (result.next())
            role = result.getInt("role_id");
        return role;
    }

    public String getFirstNameByUsername(String username) throws SQLException {
        String query = "SELECT first_name FROM Owners WHERE user_id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, getUserID(username));
        ResultSet result = statement.executeQuery();
        String first_name = "";
        if (result.next())
            first_name = result.getString("first_name");
        return first_name;
    }

    public void updateOwner(Owner owner) throws SQLException {
        int user_id = getUserID(LogInController.owner.getUsername());

        String query1 = "UPDATE Users SET password = ?, role_id = ? WHERE user_id = ?";
        PreparedStatement statement1 = connection.prepareStatement(query1);
        statement1.setString(1, owner.getPassword());
        statement1.setLong(2, getRoleIdByUsername(LogInController.owner.getUsername()));
        statement1.setInt(3, user_id);
        statement1.executeUpdate();

        String query2 = "UPDATE Owners SET last_name = ?, first_name = ?, second_name = ?, address = ?, phone_number = ? WHERE user_id = ?";
        PreparedStatement statement2 = connection.prepareStatement(query2);
        statement2.setString(1, owner.getLast_name());
        statement2.setString(2, owner.getFirst_name());
        statement2.setString(3, owner.getSecond_name());
        statement2.setString(4, owner.getAddress());
        statement2.setString(5, owner.getPhone_number());
        statement2.setInt(6, user_id);
        statement2.executeUpdate();
    }

    public static Owner getOwnerByUsername(String username) throws SQLException {
        String query = "SELECT owner_id, last_name, first_name, second_name, address, phone_number, username, password FROM Owners JOIN Users USING (user_id) WHERE username = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, username);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            int id = resultSet.getInt("owner_id");
            String last_name = resultSet.getString("last_name");
            String first_name = resultSet.getString("first_name");
            String second_name = resultSet.getString("second_name");
            String address = resultSet.getString("address");
            String phone_number = resultSet.getString("phone_number");
            String password = resultSet.getString("password");

            return new Owner(id, last_name, first_name, second_name, address, phone_number, username, password);
        }
        return null;
    }
    public static Breed getBreedById(int breed_id) {
        String query = "SELECT breed_id, name FROM Breeds WHERE breed_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, breed_id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("breed_id");
                String name = rs.getString("name");

                return new Breed(id, name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Animal> getInfoAboutPet() {
        List<Animal> petList = new ArrayList<>();

        try {
            String query = "SELECT animal_id, name, breed_id FROM Animals WHERE owner_id = ?";
            Long owner_id = (long) getOwnerByUsername(LogInController.owner.getUsername()).getOwner_id();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1,owner_id);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                long id = rs.getLong("animal_id");
                String name = rs.getString("name");
                int breedId = rs.getInt("breed_id");

                Breed breed = getBreedById(breedId);
                Animal pet = new Animal(id, owner_id, breed.getName(), name);
                petList.add(pet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return petList;
    }
    public Long getBreedIdByName(String name) throws SQLException {
        String query = "SELECT breed_id FROM Breeds WHERE name = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, name);
        ResultSet result = statement.executeQuery();
        long breed_id = 0;
        if (result.next())
            breed_id = result.getInt("breed_id");
        return breed_id;
    }
    public void addAnimal(String name, String breed) throws SQLException {
        Long breed_id = getBreedIdByName(breed);
        if (breed_id == 0){
            String query2 = "INSERT INTO Breeds(name) VALUES(?)" ;
            PreparedStatement statement2 = connection.prepareStatement(query2);
            statement2.setString(1, breed);
            statement2.executeUpdate();
            String query3 = "SELECT breed_id FROM Breeds WHERE name = ?";
            PreparedStatement statement3 = connection.prepareStatement(query3);
            statement3.setString(1, breed);
            ResultSet result1 = statement3.executeQuery();
            if (result1.next())
                breed_id = result1.getLong("breed_id");
        }
        Long owner_id = (long) getOwnerByUsername(LogInController.owner.getUsername()).getOwner_id();
        String query1 = "INSERT INTO Animals(owner_id, breed_id, name) VALUES(?, ?, ?)" ;
        PreparedStatement statement1 = connection.prepareStatement(query1);
        statement1.setLong(1, owner_id);
        statement1.setLong(2, breed_id);
        statement1.setString(3, name);
        statement1.executeUpdate();
    }

    public void updateAnimal(Long id, String name, String breed) throws SQLException {
        Long breed_id = getBreedIdByName(breed);
        if (breed_id == 0){
            String query2 = "INSERT INTO Breeds(name) VALUES(?)" ;
            PreparedStatement statement2 = connection.prepareStatement(query2);
            statement2.setString(1, breed);
            statement2.executeUpdate();
            String query3 = "SELECT breed_id FROM Breeds WHERE name = ?";
            PreparedStatement statement3 = connection.prepareStatement(query3);
            statement3.setString(1, breed);
            ResultSet result1 = statement3.executeQuery();
            if (result1.next())
                breed_id = result1.getLong("breed_id");
        }
        String query2 = "UPDATE Animals SET name = ?, breed_id = ? WHERE animal_id = ?";
        PreparedStatement statement2 = connection.prepareStatement(query2);
        statement2.setString(1, name);
        statement2.setLong(2, breed_id);
        statement2.setLong(3, id);
        statement2.executeUpdate();
    }
    public void deleteAnimal(Long id) throws SQLException {
        String query = "DELETE FROM Animals WHERE animal_id = ?";
        PreparedStatement statement2 = connection.prepareStatement(query);
        statement2.setLong(1, id);
        statement2.executeUpdate();
    }
}