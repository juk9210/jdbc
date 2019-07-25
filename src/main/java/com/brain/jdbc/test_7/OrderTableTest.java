package com.brain.jdbc.test_7;

import com.brain.jdbc.Product;

import java.math.BigDecimal;
import java.sql.*;

public class OrderTableTest {

    private static final String SQL_SELECT_ALL_SORT_QUERY = "SELECT * FROM public.product ORDER BY cost DESC;";

    public static void main(String[] args) {

        String dbHost = "jdbc:postgresql://localhost:5432/jdbc-test";
        String dbUser = "postgres";
        String dbPass = "postgres";
        try (Connection conn = DriverManager.getConnection(dbHost, dbUser, dbPass);
             PreparedStatement preparedStatement1 = conn.prepareStatement(SQL_SELECT_ALL_SORT_QUERY)
        ) {
            processData(preparedStatement1);

        } catch (SQLException e) {
            System.err.format("SQL error: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void processData(PreparedStatement preparedStatement) throws SQLException {
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Product product = mapToProduct(resultSet);
            System.out.println(product);
        }
    }

    private static Product mapToProduct(ResultSet result) throws SQLException {
        long id = result.getLong("id");
        String name = result.getString("name");
        BigDecimal cost = result.getBigDecimal("cost");
        int count = result.getInt("count");
        String description = result.getString("description");
        Timestamp createdDate = result.getTimestamp("created_date");
        Timestamp updateDate = result.getTimestamp("update_date");

        Product product = new Product(id, name, description, cost, count);
        product.setCreationDate(createdDate.toLocalDateTime());
        product.setUpdateDate(updateDate.toLocalDateTime());
        return product;
    }
}
