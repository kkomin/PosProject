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

    // INVENTORY_LOGS에 기록 추가
    // id -> sequence로 순차적으로 생성
    private final String inventoryLogSql = """
            INSERT INTO INVENTORY_LOGS(LOG_ID, PROD_ID, QUANTITY, ARRIVAL_DATE)
            VALUES (INVENTORY_LOGS_ID_SEQ.NEXTVAL, ?, ?, SYSDATE)
            """;

    public InventoryDAO(Connection connection) {
        this.connection = connection;
    }

    // PRODUCT 재고 목록 조회 및 리스트에 담기
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

    // 입고 기록 추가
    public void addInventoryLog(int prodId, int quantity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(inventoryLogSql)) {
            // 파라미터 바인딩
            preparedStatement.setInt(1, prodId);
            preparedStatement.setInt(2, quantity);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("INVENTORY SQL 오류" + e.getMessage());
        }
    }


    // 수량만큼 products의 재고 수량 증가

}
