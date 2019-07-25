package com.brain.jdbc.test_1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateTableTest {

    private static final String SQL_CREATE_QUERY =
            "CREATE TABLE product"
                    + "("
                    + " id serial,"
                    + " name varchar(100) NOT NULL,"
                    + " cost decimal(15, 2) NOT NULL,"
                    + " count numeric NOT NULL,"
                    + " description text,"
                    + " created_date timestamp with time zone NOT NULL DEFAULT CURRENT_TIMESTAMP,"
                    + " PRIMARY KEY (id)"
                    + ")";

    public static void main(String[] args) {

        String dbHost = "jdbc:postgresql://localhost:5432/jdbc-test";
        String dbUser = "postgres";
        String dbPass = "postgres";
        try (Connection conn = DriverManager.getConnection(dbHost, dbUser, dbPass);
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_CREATE_QUERY)) {
            // Выполняем запрос в БД
            preparedStatement.execute();

        } catch (SQLException e) {
            System.err.format("SQL error: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
