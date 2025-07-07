package dao;

import db.ConnectionDB;

import java.sql.*;

// 등록하는 제품 -> DB에 추가 (insert)
public class ProductDAO {
    // 제품 등록 sql
    public final String insertProduct = """
            INSERT INTO PRODUCTS(PROD_ID, PROD_NAME, COMPANY, EXPIRATION, IS_ADULT, PRICE, STOCK)
            VALUES (prod_id_seq.nextval, ?, ?, ?, ?, ?, ?)
            """;

    // 등록한 제품 prod_id 받아오는 sql
//    public final String getProduct = """
//            SELECT PROD_ID_SEQ.CURRVAL FROM DUAL
//            """;

    private Connection connection;

    public ProductDAO() {
        try {
            connection = ConnectionDB.getConnectionDB();
        } catch (SQLException e) {
            System.out.println("상품 등록 INSERT SQL 오류 " + e.getMessage());
        }
    }

    public void registerProduct(String name, String company, Date expiration, Character isAdult, int price, int stock) {
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

//    public int getProductId() {
//        try (PreparedStatement preparedStatement = connection.prepareStatement(getProduct)) {
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            if(resultSet.next()) {
//                return resultSet.getInt(1);
//            }
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return -1;
//    }
}