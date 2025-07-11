package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SaleDAO {
    private final Connection connection;

    public SaleDAO(Connection connection) {
        this.connection = connection;
    }

    // SALE 테이블에 insert
    public int insertSale(int empId, int totalPrice, String paymentType, boolean isAdult) {
    String insertSql = """
            INSERT INTO SALES(SALE_ID, SALE_DATE, EMP_ID, TOTAL_PRICE, PAYMENT_tYPE, ADULT_CHECK)
            VALUES(SALE_SEQ.NEXTVAL, SYSDATE, ?, ?, ?, ?)
            """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertSql)){
            // 파라미터 바인딩
            preparedStatement.setInt(1, empId);
            preparedStatement.setInt(2, totalPrice);
            preparedStatement.setString(3, paymentType);
            preparedStatement.setString(4, isAdult? "1" : "0");

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("sale insert 오류 발생" + e.getMessage());
        }

        String currentSql = "SELECT SALE_SEQ.CURRVAL FROM DUAL";
        int saleId = -1;
        try (PreparedStatement preparedStatement = connection.prepareStatement(currentSql)){
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                saleId = resultSet.getInt(1);
            }
            else {
                System.out.println("CURRVAL 조회 실패");
                return -1;
            }
        } catch (SQLException e) {
            System.out.println("CURRVAL 조회 오류 발생" + e.getMessage());
        }
        return saleId;
    }

    // sale_item 테이블에 insert
    public void insertSaleItem() {

    }
}
