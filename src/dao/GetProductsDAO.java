package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetProductsDAO {
    private final Connection connection;

    public final String getProductsSql = """
            SELECT PROD_ID, PROD_NAME, STOCK FROM PRODUCTS ORDER BY PROD_ID
            """;

    public GetProductsDAO(Connection conn) {
        this.connection = conn;
    }

    // 재고 확인
    public void getProducts() {
        try (PreparedStatement preparedStatement = connection.prepareStatement(getProductsSql)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int prodId = resultSet.getInt("PROD_ID");
                String prodName = resultSet.getString("PROD_NAME");
                int prodStock = resultSet.getInt("STOCK");

                StringBuilder stars = new StringBuilder();
                for (int i = 0; i < prodStock; i++) {
                    stars.append("*");
                }

                System.out.printf("%s : %s\t(%d개)\n", prodName, stars ,prodStock);
                System.out.println("\n");
            }

        } catch (SQLException e) {
            System.out.println("PRODUCTS SQL 오류" + e.getMessage());
        }
    }
}
