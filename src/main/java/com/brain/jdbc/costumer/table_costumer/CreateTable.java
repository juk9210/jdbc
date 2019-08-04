package com.brain.jdbc.costumer.table_costumer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Создание таблицы в базе данных
 *
 * @author Shakhov Yevhen.
 */

public class CreateTable {
    /**
     * Создаем строку в которой прописываем создание таблицы со столбцами
     */
    private static final String SQL_CREATE_QUERY =
            "CREATE TABLE customer"
                    + "("
                    + "id serial,"
                    + "firstName varchar (20) NOT NULL,"
                    + "surName varchar (20) NOT NULL,"
                    + "phone numeric NOT NULL,"
                    + "email text,"
                    + "city varchar (30) NOT NULL,"
                    + "discount numeric NOT NULL,"
                    + "PRIMARY KEY (id)"
                    + ")";


    public static void main(String[] args) {
        String dbHost = "jdbc:postgresql://localhost:5432/jdbc-test";      // прописываем URL адрес нашей БД
        String dbUser = "postgres"; // прописываем наш логин
        String dbPass = "eldorado92";  //прописываем на пароль
        try (Connection conn = DriverManager.getConnection( dbHost, dbUser, dbPass );    //делаем подключение к БД
             PreparedStatement preparedStatement = conn.prepareStatement( SQL_CREATE_QUERY )) {   // Выполняем запрос в БД
            preparedStatement.execute();       //выполняем SQL-команду.
        } catch (SQLException e) {
            System.err.format( "SQL error: %s\n%s", e.getSQLState(), e.getMessage() );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
