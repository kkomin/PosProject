package dao;

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

        } catch (SQLException e) {
            System.out.println("INVENTORY SQL 구문 오류" + e.getMessage());
        }
    }
}
