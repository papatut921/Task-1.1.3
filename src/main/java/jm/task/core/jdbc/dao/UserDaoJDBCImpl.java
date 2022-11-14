package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Connection connection = Util.getConnection();
    private static final String INSERT_USERS = "INSERT INTO users_db (name, lastName, age) VALUES (?, ?, ?);";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS users_db";
    private static final String SELECT_ALL_USERS = "SELECT * FROM users_db";
    private static final String DELETE_USERS = "DELETE FROM users_db WHERE id = ?";
    private static final String DELETE_ALL_USERS = "DELETE FROM users_db";
    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS users_db (" +
                                                "id SERIAL PRIMARY KEY NOT NULL, " +
                                                "name VARCHAR(255) NOT NULL, " +
                                                "lastName VARCHAR(255) NOT NULL, " +
                                                "age INT NOT NULL )";

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(CREATE_TABLE);
            System.out.println("Table created");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(DROP_TABLE);
            System.out.println("Table dropped");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement stmt = connection.prepareStatement(INSERT_USERS)) {
            stmt.setString(1, name);
            stmt.setString(2, lastName);
            stmt.setByte(3, age);
            stmt.executeUpdate();
            System.out.println("User с именем - " + name + " добавлен в базу данных");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (PreparedStatement stmt = connection.prepareStatement(DELETE_USERS)) {
            stmt.setLong(1, id);
            stmt.executeUpdate(DELETE_USERS);
            System.out.println("User с id " + id + "удален из базы данных");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList<>();
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(SELECT_ALL_USERS);
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
            stmt.executeUpdate(DELETE_ALL_USERS);
            System.out.println("Table cleared");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
