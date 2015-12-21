package com.epam.jamp.patterns.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionCreator {

    public static final String url = "jdbc:oracle:thin:@localhost:1521:orcl";
    public static final String name = "test";
    public static final String password = "test";

    private static volatile ConnectionCreator instance;

    private ConnectionCreator() {
    }

    public static ConnectionCreator getInstance() {
        if (instance == null) {
            synchronized (ConnectionCreator.class) {
                if (instance == null) {
                    instance = new ConnectionCreator();
                }
            }
        }
        return instance;
    }

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        return DriverManager.getConnection(url, name, password);
    }
}
