package com.nucleus.util;

import java.sql.Connection;
import java.sql.DriverManager;

// Gives us a connection to MySQL. Change USER and PASS to yours.
public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/nucleus";
    private static final String USER = "root";
    private static final String PASS = "root";

    public static Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASS);
    }

    // Stops bad HTML from being shown on the page
    public static String esc(String s) {
        if (s == null) return "";
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;");
    }
}
