package com.brain.jdbc.test_2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddColumnTableTest {

    private static final String SQL_ADD_COLUMN_QUERY =
            "ALTER TABLE product " +
            "ADD COLUMN update_date timestamp with time zone NOT NULL DEFAULT CURRENT_TIMESTAMP;";

    public static void main(String[] args) {

        String dbHost = "jdbc:postgresql://localhost:5432/jdbc-test";
        String dbUser = "postgres";
        String dbPass = "postgres";
        try (Connection conn = DriverManager.getConnection(dbHost, dbUser, dbPass);
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_ADD_COLUMN_QUERY)) {
            // Выполняем запрос в БД
            preparedStatement.execute();

        } catch (SQLException e) {
            System.err.format("SQL error: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
