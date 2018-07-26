package com.era.supercar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

// Probably not thread safe. Each thread should have their own connection.
class RideShareDBConnection implements AutoCloseable
{
    private final Connection connection;
    private final PreparedStatement preparedStatement;

    public RideShareDBConnection() throws SQLException
    {
        Properties props = new Properties();
        props.put("User", "ubdb");
        props.put("Password", "123456");
        this.connection = DriverManager.getConnection("jdbc:sybase:Tds:10.171.1.252:2638/ubdatabase", props);
        String queryString = "INSERT INTO RideSharing (startTime, startState, startCity, name, endState, endCity, ageRange) values (?, ?, ?, ?, ?, ?, ?)";
        this.preparedStatement = this.connection.prepareStatement(queryString);
    }

    public void insert(String startTime, String startState, String startCity, String name, String endState, String endCity, String ageRange) throws SQLException //long timestamp) throws SQLException
    {
        this.preparedStatement.setString(1, startTime);
        this.preparedStatement.setString(2, startState);
        this.preparedStatement.setString(3, startCity);
        this.preparedStatement.setString(4, name);
        this.preparedStatement.setString(5, endState);
        this.preparedStatement.setString(6, endCity);
        this.preparedStatement.setString(7, ageRange);
        this.preparedStatement.executeUpdate();
    }

    public void close() throws SQLException
    {
        this.preparedStatement.close();
        this.connection.close();
    }
}
