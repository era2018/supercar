package com.era.supercar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

class DBConnection implements AutoCloseable
{
    private final Connection connection;

    public DBConnection() throws SQLException
    {
        Properties props = new Properties();
        props.put("User", "ubdb");
        props.put("Password", "123456");
        this.connection = DriverManager.getConnection("jdbc:sybase:Tds:10.171.1.252:2638/ubdatabase", props);
    }

    public void insert(int value, String id, long timestamp) throws SQLException
    {
        String queryString = String.format("INSERT INTO EventLog (logID, eventID, carID, logTime) values (%s, %s, %s, %s)", value, "12345", "2018-07-20");
        PreparedStatement pstatement = this.connection.prepareStatement(queryString);
        pstatement.executeUpdate();
        pstatement.close();
    }

    public void close() throws SQLException
    {
        this.connection.close();
    }
}