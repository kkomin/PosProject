package dao;

import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InventoryDAO {
    private Connection connection;

    // 모든 제품 조회
    private final String selectSql = """
            SELECT * FROM PRODUCTS
            """;

    public InventoryDAO(Connection connection) {
        this.connection = connection;
    }

    public void inventory() {
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectSql)){
            ResultSet resultSet = preparedStatement.executeQuery();
            // 전체 목록 조회
            while(resultSet.next()) {
                // 제품 객체 생성
            }

        } catch (SQLException e) {
            System.out.println("INVENTORY SQL 구문 오류" + e.getMessage());
        }
    }
}
