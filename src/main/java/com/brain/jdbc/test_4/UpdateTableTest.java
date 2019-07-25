package com.brain.jdbc.test_4;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;

public class UpdateTableTest {

    private static final String SQL_UPDATE_QUERY =
            "UPDATE public.product SET cost=?, count=?, update_date=? WHERE id = 1;";

    public static void main(String[] args) {

        String dbHost = "jdbc:postgresql://localhost:5432/jdbc-test";
        String dbUser = "postgres";
        String dbPass = "postgres";
        try (Connection conn = DriverManager.getConnection(dbHost, dbUser, dbPass);
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_UPDATE_QUERY)) {
            // Готовим запрос в БД
            preparedStatement.setBigDecimal(1, new BigDecimal(3510.55));
            preparedStatement.setInt(2, 0);
            preparedStatement.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));

            // Выполняем запрос в БД
            int row = preparedStatement.executeUpdate();

            // результат выполнения
            System.out.println(row);

        } catch (SQLException e) {
            System.err.format("SQL error: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
