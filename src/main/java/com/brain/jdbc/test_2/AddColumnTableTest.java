package com.brain.jdbc.test_2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Добавление нового столбца в нашц таблицу
 */

public class AddColumnTableTest {
    /**
     * создаем строку,в которой добавляем столбец к существующей таблице
     */
    private static final String SQL_ADD_COLUMN_QUERY =
            "ALTER TABLE product " +
            "ADD COLUMN update_date timestamp with time zone NOT NULL DEFAULT CURRENT_TIMESTAMP;";

    public static void main(String[] args) {

        String dbHost = "jdbc:postgresql://localhost:5432/jdbc-test";   // прописываем URL адрес нашей БД
        String dbUser = "postgres";                // прописываем наш логин
        String dbPass = "eldorado92";         //прописываем на пароль
        try (Connection conn = DriverManager.getConnection(dbHost, dbUser, dbPass);   //делаем подключение к БД
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_ADD_COLUMN_QUERY)) {  // Выполняем запрос в БД
            preparedStatement.execute();             //выполняем SQL-команду.

        } catch (SQLException e) {
            System.err.format("SQL error: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
