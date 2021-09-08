package com.teradata.app;

import java.sql.*;

public class App {
    static final String DB_URL = "jdbc:teradata://localhost";
    static final String USER = "dbc";
    static final String PASS = "dbc";
    static final String QUERY = "SELECT * FROM dbc.dbcinfo";

    public static void main(String[] args) {
        App app = new App();
        app.query();
    }

    public void query() {
        // Open a connection
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(QUERY);) {

            // Extract data from result set
            while (rs.next()) {
                System.out.println(String.format("setting: %s, value: %s", rs.getString(1), rs.getString(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
