package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Connection connection = Util.getConnection();
    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try (Statement stmt = connection.createStatement()) {
            String SQLCommand = "CREATE TABLE IF NOT EXISTS users_db (" +
                    "id SERIAL PRIMARY KEY NOT NULL, " +
                    "name VARCHAR(255) NOT NULL, " +
                    "lastName VARCHAR(255) NOT NULL, " +
                    "age INT NOT NULL )";
            stmt.execute(SQLCommand);
            System.out.println("Table created");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Statement stmt = connection.createStatement()) {
            String SQLCommand = "DROP TABLE IF EXISTS users_db";
            stmt.executeUpdate(SQLCommand);
            System.out.println("Table dropped");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Statement stmt = connection.createStatement()) {
            String SQLCommand = "INSERT INTO users_db (name, lastName, age) VALUES ('" + name + "', '" + lastName + "', " + age + ")";
            stmt.executeUpdate(SQLCommand);
            System.out.println("User с именем - " + name + " добавлен в базу данных");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (Statement stmt = connection.createStatement()) {
            String SQLCommand = "DELETE FROM users_db WHERE id = " + id;
            stmt.executeUpdate(SQLCommand);
            System.out.println("User с id " + id + "удален из базы данных");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList<>();
        try (Statement stmt = connection.createStatement()) {
            String SQLCommand = "SELECT * FROM users_db";
            ResultSet rs = stmt.executeQuery(SQLCommand);
            while (rs.next()) {
                User us = new User();
                us.setId(rs.getLong(1));
                us.setName(rs.getString(2));
                us.setLastName(rs.getString(3));
                us.setAge(rs.getByte(4));
                allUsers.add(us);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return allUsers;
    }

    public void cleanUsersTable() {
        try (Statement stmt = connection.createStatement()) {
            String SQLCommand = "DELETE FROM users_db";
            stmt.executeUpdate(SQLCommand);
            System.out.println("Table cleared");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
