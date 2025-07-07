package dao;

import model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InventoryDAO {
    private Connection connection;

    // 모든 제품 조회
    private final String selectSql = """
            SELECT * FROM PRODUCTS
            """;

    public InventoryDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Product> inventory() {
        List<Product> productList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectSql)){
            ResultSet resultSet = preparedStatement.executeQuery();
            // 전체 목록 조회
            while(resultSet.next()) {
                // PRODUCTS -> prod_id, prod_name, company, expiration, is_adult, price, stock
                int id = resultSet.getInt("prod_id");
                String name = resultSet.getString("prod_name");
                String company = resultSet.getString("company");
                Date exp = resultSet.getDate("expiration");
                char adult = resultSet.getString("is_adult").charAt(0);
                int price = resultSet.getInt("price");
                int stock = resultSet.getInt("stock");

                // 제품 객체 생성
                Product product = new Product(id, name, company, exp, adult, price, stock);
                // 리스트에 추가
                productList.add(product);
            }
            return productList;

        } catch (SQLException e) {
            System.out.println("INVENTORY SQL 구문 오류" + e.getMessage());
        }
        return productList;
    }
}
