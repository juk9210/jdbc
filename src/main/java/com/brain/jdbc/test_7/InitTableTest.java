package com.brain.jdbc.test_7;

import com.brain.jdbc.Product;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InitTableTest {

    private static final String SQL_INSERT_QUERY =
            "INSERT INTO public.product( " +
                    "name, cost, count, description) " +
                    "VALUES (?, ?, ?, ?);";

    public static void main(String[] args) {
        String dbHost = "jdbc:postgresql://localhost:5432/jdbc-test";
        String dbUser = "postgres";
        String dbPass = "postgres";
        try (Connection conn = DriverManager.getConnection(dbHost, dbUser, dbPass);
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_INSERT_QUERY)) {

            insertProduct(preparedStatement, new Product(100, "Смартфон",
                    "Андроид, камера 20Мп", BigDecimal.valueOf(8003.23), 10));

            insertProduct(preparedStatement, new Product(101, "Тостер",
                    "...", BigDecimal.valueOf(800), 12));

            insertProduct(preparedStatement, new Product(102, "Телевизор",
                    "55\"", BigDecimal.valueOf(25000), 8));

            insertProduct(preparedStatement, new Product(103, "Ноутбук",
                    "windows 10", BigDecimal.valueOf(17032), 2));

        } catch (SQLException e) {
            System.err.format("SQL error: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void insertProduct(PreparedStatement preparedStatement, Product product) throws SQLException {

        // Готовим запрос в БД
        preparedStatement.setString(1, product.getName());
        preparedStatement.setBigDecimal(2, product.getCost());
        preparedStatement.setInt(3, product.getCount());
        preparedStatement.setString(4, product.getDescription());

        // Выполняем запрос в БД
        int row = preparedStatement.executeUpdate();

        // результат выполнения
        System.out.println(row);
    }

}
