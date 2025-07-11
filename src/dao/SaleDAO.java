package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SaleDAO {
    private final Connection connection;

    public SaleDAO(Connection connection) {
        this.connection = connection;
    }

    // SALE 테이블에 insert
    public void insertSale(int empId, int totalPrice, String paymentType, Character isAdult) {
    String insertSql = """
            INSERT INTO SALES(SALE_ID, SALE_DATE, EMP_ID, TOTAL_PRICE, PAYMENT_tYPE, ADULT_CHECK)
            VALUES(SALE_SEQ.NEXTVAL, SYSDATE, ?, ?, ?, ?)
            """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertSql)){
            // 파라미터 바인딩
            preparedStatement.setInt(1, empId);
            preparedStatement.setInt(2, totalPrice);
            preparedStatement.setString(3, paymentType);
            preparedStatement.setString(4, String.valueOf(isAdult));

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("sale insert 오류 발생");
        }
    }

    // sale_item 테이블에 insert
    public void insertSaleItem() {

    }
}
