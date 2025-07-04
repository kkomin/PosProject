package dao;

import db.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// 등록하는 제품 -> DB에 추가 (insert)
public class ProductDAO {
    private Connection connection;

    // 제품 등록 sql
    private final String insertProduct = """
            INSERT INTO PRODUCTS(PROD_ID, PROD_NAME, COMPANY, EXPIRATION, IS_ADULT, PRICE, STOCK)
            VALUES (prod_id_seq.nextval, ?, ?, ?, ?, ?, ?, ?)
            """;

    // 등록한 제품 prod_id 받아오는 sql


    public ProductDAO() {
        try {
            connection = ConnectionDB.getConnectionDB();
        } catch (SQLException e) {
            System.out.println("상품 등록 INSERT SQL 오류 " + e.getMessage());
        }
    }

    public void RegisterProduct(String name, String company, String expiration, String isAdult, int price, int stock) {
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(insertProduct);
            // 파라미터 바인딩

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
