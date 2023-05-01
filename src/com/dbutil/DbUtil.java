package com.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/QuoteDb";
    private static final String USER = "shivam";
    private static final String PASS = "shivam";

    static {
        try {
            Class.forName(DB_DRIVER);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        Connection con = DriverManager.getConnection(URL, USER, PASS);
        return con;
    }
}
