package com.brain.jdbc.customer.table_customer;
import com.brain.jdbc.customer.Customer;
import java.sql.*;

/**
 * Выборка данных из БД
 *
 * @author Shakhov Yevhen.
 */

public class SelectTable {
    /**
     * В строках прописываем какую выборку будем делать.
     */
    private static final String SQL_SELECT_ALL_QUERY = "SELECT * FROM customer;";
    private static final String SQL_SELECT_BY_CITY_QUERY = "SELECT * FROM customer ORDER BY city;";
    private static final String SQL_SELECT_BY_SURNAME_QUERY = "SELECT * FROM customer ORDER BY SURNAME;";

    public static void main(String[] args) {
        String dbHost = "jdbc:postgresql://localhost:5432/jdbc-test";    // прописываем URL адрес нашей БД
        String dbUser = "postgres";                // прописываем наш логин
        String dbPass = "eldorado92";                 //прописываем наш пароль
        try (Connection conn = DriverManager.getConnection( dbHost, dbUser, dbPass );  //делаем подключение к БД
             PreparedStatement preparedStatement1 = conn.prepareStatement( SQL_SELECT_ALL_QUERY ); //выполняем запрос к БД
             PreparedStatement preparedStatement2 = conn.prepareStatement( SQL_SELECT_BY_CITY_QUERY ); //выполняем запрос к БД
             PreparedStatement preparedStatement3 = conn.prepareStatement( SQL_SELECT_BY_SURNAME_QUERY )//выполняем запрос к БД
        ) {

            processData( preparedStatement1 ); //обращаемся к методу,который выводит данные из БД с первой выборкой
            processData( preparedStatement2 );  //обращаемся к методу,который выводит данные из БД с второй выборкой
            processData( preparedStatement3 );  //обращаемся к методу,который выводит данные из БД с третьей выборкой

        } catch (SQLException e) {
            System.err.format( "SQL error: %s\n%s", e.getSQLState(), e.getMessage() );
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Создаём метод который выводит наши данные из БД
     *
     * @param preparedStatement
     * @throws SQLException
     */
    private static void processData(PreparedStatement preparedStatement) throws SQLException {
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Customer customer = mapToCustomer( resultSet );
            System.out.println( customer );
        }
    }

    /**
     * Создаём метод,который будет выводить наши данные из таблицы в виде строки
     *
     * @param result
     * @return
     * @throws SQLException
     */
    private static Customer mapToCustomer(ResultSet result) throws SQLException {
        long id = result.getLong( "id" );
        String firstName = result.getString( "firstName" );
        String surName = result.getString( "surName" );
        long phone = result.getLong( "phone" );
        String email = result.getString( "email" );
        String city = result.getString( "city" );
        long discount = result.getLong( "discount" );

        Customer customer = new Customer(id,firstName,surName,phone,email,city,discount);
        return customer;
    }
}