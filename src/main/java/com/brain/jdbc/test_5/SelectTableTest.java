package com.brain.jdbc.test_5;

import com.brain.jdbc.Product;

import java.math.BigDecimal;
import java.sql.*;

/**
 * Выборка данных из БД
 */

public class SelectTableTest {
    /**
     * В строках прописываем какую выборку будем делать.
     */
    private static final String SQL_SELECT_ALL_QUERY = "SELECT * FROM product;";
    private static final String SQL_SELECT_BY_ID_QUERY = "SELECT * FROM product where id=1;";
    private static final String SQL_SELECT_BY_NAME_QUERY = "SELECT * FROM product where name='Микроволновка';";

    public static void main(String[] args) {

        String dbHost = "jdbc:postgresql://localhost:5432/jdbc-test";    // прописываем URL адрес нашей БД
        String dbUser = "postgres";                // прописываем наш логин
        String dbPass = "eldorado92";                 //прописываем наш пароль
        try (Connection conn = DriverManager.getConnection( dbHost, dbUser, dbPass );  //делаем подключение к БД
             PreparedStatement preparedStatement1 = conn.prepareStatement( SQL_SELECT_ALL_QUERY ); //выполняем запрос к БД
             PreparedStatement preparedStatement2 = conn.prepareStatement( SQL_SELECT_BY_ID_QUERY ); //выполняем запрос к БД
             PreparedStatement preparedStatement3 = conn.prepareStatement( SQL_SELECT_BY_NAME_QUERY )//выполняем запрос к БД
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
            Product product = mapToProduct( resultSet );
            System.out.println( product );
        }
    }

    private static Product mapToProduct(ResultSet result) throws SQLException {
        long id = result.getLong( "id" );
        String name = result.getString( "name" );
        BigDecimal cost = result.getBigDecimal( "cost" );
        int count = result.getInt( "count" );
        String description = result.getString( "description" );
        Timestamp createdDate = result.getTimestamp( "created_date" );
        Timestamp updateDate = result.getTimestamp( "update_date" );

        Product product = new Product( id, name, description, cost, count );
        product.setCreationDate( createdDate.toLocalDateTime() );
        product.setUpdateDate( updateDate.toLocalDateTime() );
        return product;
    }
}
