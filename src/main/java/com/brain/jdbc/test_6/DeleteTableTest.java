package com.brain.jdbc.test_6;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Выполнение удаление в БД.
 */

public class DeleteTableTest {
    /**
     * В строке прописываем какое именно хотим сделать удаление
     */
    private static final String SQL_DELETE_QUERY = "DELETE FROM public.product WHERE count=0;";

    public static void main(String[] args) {

        String dbHost = "jdbc:postgresql://localhost:5432/jdbc-test";    // прописываем URL адрес нашей БД
        String dbUser = "postgres";    // прописываем наш логин
        String dbPass = "eldorado92";      //прописываем наш пароль
        try (Connection conn = DriverManager.getConnection( dbHost, dbUser, dbPass );  //делаем подключение к БД
             PreparedStatement preparedStatement = conn.prepareStatement( SQL_DELETE_QUERY )) {   //выполняем запрос к БД
            int row = preparedStatement.executeUpdate();

            // результат выполнения
            System.out.println( row );

        } catch (SQLException e) {
            System.err.format( "SQL error: %s\n%s", e.getSQLState(), e.getMessage() );
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
