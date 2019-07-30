package com.brain.jdbc.test_3;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;

/**
 * Добавляем данные в БД
 */

public class InsertTableTest {
    /**
     * создаем строку с помощьюк которой будем добавлять данные
     */
    private static final String SQL_INSERT_QUERY =
            "INSERT INTO public.product( " +
            "name, cost, count, description, created_date) " +
            "VALUES (?, ?, ?, ?, ?);";

    public static void main(String[] args) {

        String dbHost = "jdbc:postgresql://localhost:5432/jdbc-test";  // прописываем URL адрес нашей БД
        String dbUser = "postgres";         // прописываем наш логин
        String dbPass = "eldorado92";      //прописываем на пароль
        try (Connection conn = DriverManager.getConnection(dbHost, dbUser, dbPass);      //делаем подключение к БД
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_INSERT_QUERY)) {   // Готовим запрос в БД
            /*
            Добавляем данные в столбцы
             */
            preparedStatement.setString(1, "Микроволновка");
            preparedStatement.setBigDecimal(2, new BigDecimal(2511.50));
            preparedStatement.setInt(3, 2);
            preparedStatement.setString(4, "Микроволновка + гриль");
            preparedStatement.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));

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
