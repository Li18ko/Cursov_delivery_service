package com.example.delivery_service;

import java.sql.*;

public class DatabaseConnection{
    private static DatabaseConnection instance;
    private Connection connection;

    private DatabaseConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        this.connection = DriverManager.getConnection(
                "jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_2280_delivery_service",
                "std_2280_delivery_service", "LizaKorneeva_Liza");
    }

    public Connection getConnection() {
        return connection;
    }

    public static DatabaseConnection getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null) {
            instance = new DatabaseConnection();
        } else if (instance.getConnection().isClosed()) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public void registerUser(User user) throws SQLException {
        String query = "INSERT INTO users(login, password) VALUES (?, ?)";
        String query2 = "INSERT INTO clients(name, number, address, nearest_delivery_centers_name, users_login) VALUES " +
                "(?, ?, ?, NULL, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, user.getLogin());
        statement.setString(2, user.getPassword());
        PreparedStatement statement2 = connection.prepareStatement(query2);
        statement2.setString(1, user.getName());
        statement2.setString(2, user.getNumber());
        statement2.setString(3, user.getAddress());
        statement2.setString(4, user.getLogin());
        statement.executeUpdate();
        statement2.executeUpdate();
    }

    public ResultSet getUser(User user) throws SQLException {
        ResultSet resSet = null;
        String query = "SELECT * FROM users WHERE login = ? AND password = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, user.getName());
        statement.setString(2, user.getPassword());
        ResultSet result = statement.executeQuery();
        return resSet;
    }
}