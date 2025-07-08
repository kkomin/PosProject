package dao;

import db.ConnectionDB;

import java.sql.*;

// 등록하는 제품 -> DB에 추가 (insert)
public class ProductDAO {
    private Connection connection;

    public ProductDAO() {
        try {
            connection = ConnectionDB.getConnectionDB();
        } catch (SQLException e) {
            System.out.println("상품 등록 INSERT SQL 오류 " + e.getMessage());
        }
    }

    public void registerProduct(String name, String company, Date expiration, Character isAdult, int price, int stock) {
        // 제품 등록 sql
        String insertProduct = """
            INSERT INTO PRODUCTS(PROD_ID, PROD_NAME, COMPANY, EXPIRATION, IS_ADULT, PRICE, STOCK)
            VALUES (prod_id_seq.nextval, ?, ?, ?, ?, ?, ?)
            """;

        // PreparedStatement 자동으로 닫기
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertProduct)) {
            // 파라미터 바인딩
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, company);
            preparedStatement.setDate(3, expiration);
            preparedStatement.setString(4, String.valueOf(isAdult));
            preparedStatement.setInt(5, price);
            preparedStatement.setInt(6, stock);

            int result = preparedStatement.executeUpdate();

            if(result > 0) {
                System.out.println("제품 등록 완료!\n");
            }
            else {
                System.out.println("제품 등록 실패...\n");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // 주문 갯수만큼 재고 수량 감소
    public boolean reduceProduct(int prodId, int quantity) {
        String reduceSql = """
                UPDATE PRODUCTS
                SET STOCK = STOCK - ?
                WHERE PROD_ID = ? AND STOCK >= ?
                """;

        try ( PreparedStatement preparedStatement = connection.prepareStatement(reduceSql) ) {
            // 파라미터 바인딩
            // 차감할 수량
            preparedStatement.setInt(1, quantity);
            // 제품 id
            preparedStatement.setInt(2, prodId);
            // 차감 가능한 최대 수량
            preparedStatement.setInt(3, quantity);
            int update = preparedStatement.executeUpdate();
            if(update == 0) {
                return false;
            }
            return true;
        } catch (SQLException e) {
            System.out.println("재고 수량 감소 UPDATE 오류" + e.getMessage());
            return false;
        }
    }
}