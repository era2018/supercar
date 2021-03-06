package com.era.supercar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

// Probably not thread safe. Each thread should have their own connection.
class DBConnection implements AutoCloseable
{
    private final Connection connection;
    private final PreparedStatement preparedStatement;

    public DBConnection() throws SQLException
    {
        Properties props = new Properties();
        props.put("User", "ubdb");
        props.put("Password", "123456");
        this.connection = DriverManager.getConnection("jdbc:sybase:Tds:10.171.1.252:2638/ubdatabase", props);
        String queryString = "INSERT INTO EventLog (eventID, carID, logTime) values (?, ?, ?)";
        this.preparedStatement = this.connection.prepareStatement(queryString);
    }

    public void insert(int value, String id, String timestamp) throws SQLException //long timestamp) throws SQLException
    {
        this.preparedStatement.setInt(1, value);
        this.preparedStatement.setString(2, id);
        this.preparedStatement.setString(3, timestamp);
        this.preparedStatement.executeUpdate();
    }

    public void close() throws SQLException
    {
        this.preparedStatement.close();
        this.connection.close();
    }
}
