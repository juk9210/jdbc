package com.brain.jdbc.costumer.table_costumer;

import com.brain.jdbc.costumer.Customer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Добавляем данные в БД
 *
 * @author Shakhov Yevhen.
 */
public class InsertTable {
    /**
     * Создаем строку с помощьюк которой будем добавлять данные
     */
    private static final String SQL_INSERT_QUERY =
            "INSERT INTO public.customer( " +
                    "firstName,surName,phone,email,city,discount) " +
                    "VALUES (?, ?, ?, ?, ?, ?);";

    public static void main(String[] args) {
        String dbHost = "jdbc:postgresql://localhost:5432/jdbc-test";      // прописываем URL адрес нашей БД
        String dbUser = "postgres"; // прописываем наш логин
        String dbPass = "eldorado92";  //прописываем на пароль
        try (Connection conn = DriverManager.getConnection( dbHost, dbUser, dbPass );    //делаем подключение к БД
             PreparedStatement preparedStatement = conn.prepareStatement( SQL_INSERT_QUERY )) {   // Выполняем запрос в БД
             /*
            Добавляем данные в таблицу с помощью метода,который делает запросы к БД
             */
            insertCustomer( preparedStatement, new Customer( 1, "Ivan", "Rikalov", 809948647,
                    "rik@gamil.com", "Dnepr", 584124 ) );
            insertCustomer( preparedStatement, new Customer( 2, "Oleg", "Samohin", 805054827,
                    "sam@ukr.net", "Lviv", 181674 ) );
            insertCustomer( preparedStatement, new Customer( 3, "Tatyana", "Mironova", 806658415,
                    "mirtat@gamil.com", "Kiev", 584157 ) );
            insertCustomer( preparedStatement, new Customer( 4, "Ivan", "Fedorov", 805054841,
                    "fedor@gamil.com", "Donetsk", 852746 ) );
        } catch (SQLException e) {
            System.err.format( "SQL error: %s\n%s", e.getSQLState(), e.getMessage() );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Создаем метод для запроса в БД
     *
     * @param preparedStatement
     * @param customer
     * @throws SQLException
     */
    private static void insertCustomer(PreparedStatement preparedStatement, Customer customer) throws SQLException {
        /*
        Готовим запрос в БД
         */
        preparedStatement.setString( 1, customer.getFirstName() );
        preparedStatement.setString( 2, customer.getSurName() );
        preparedStatement.setLong( 3, customer.getPhone() );
        preparedStatement.setString( 4, customer.getEmail() );
        preparedStatement.setString( 5, customer.getCity() );
        preparedStatement.setLong( 6, customer.getDiscount() );
        int row = preparedStatement.executeUpdate();     // Выполняем запрос в БД
        System.out.println( row );        // результат выполнения
    }
}
