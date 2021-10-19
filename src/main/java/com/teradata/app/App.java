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
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        // Open a connection
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(QUERY);

            // Extract data from result set
            while (rs.next()) {
                System.out.println(String.format("setting: %s, value: %s", rs.getString(1), rs.getString(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) { /* Ignored */}
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) { /* Ignored */}
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) { /* Ignored */}
            }
        }
    }
}
