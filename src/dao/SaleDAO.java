package dao;

import javax.xml.transform.Result;
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
    public void insertSaleItem(int saleId, int prodId, int quantity, int subtotal) {
        String insertSaleItemSql = """
                INSERT INTO SALES_ITEM(ITEM_ID, SALE_ID, PROD_ID, QUANTITY, SUBTOTAL)
                VALUES(SALE_ITEM_SEQ.NEXTVAL, ?, ?, ?, ?)
                """;

        try(PreparedStatement preparedStatement = connection.prepareStatement(insertSaleItemSql)) {
            // 어떤 sale에 포함된 항목인지
            preparedStatement.setInt(1, saleId);
            // 어떤 상품?
            preparedStatement.setInt(2, prodId);
            // 몇 개 샀는지
            preparedStatement.setInt(3, quantity);
            // 개당 가격 * 수량
            preparedStatement.setInt(4, subtotal);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("SALES_ITEM INSERT 오류 발생" + e.getMessage());
        }
    }
    
    // 매출 조회하기
    public void getSalesByDate(String date) {
        String getSaleSql = """
                SELECT s.sale_id, s.sale_date, e.user_name, s.total_price, s.payment_type
                FROM SALES s
                JOIN EMPLOYEES e ON s.emp_id = e.emp_id
                WHERE TO_CHAR(s.sale_date, 'YYYYMMDD') = ?
                ORDER BY s.sale_id
                """;
        try(PreparedStatement preparedStatement = connection.prepareStatement(getSaleSql)) {
            preparedStatement.setString(1, date);

            ResultSet resultSet = preparedStatement.executeQuery();
            boolean found = false;
            int totalSum = 0;

            while(resultSet.next()) {
                found = true;
                int totalPrice = resultSet.getInt("total_price");
                totalSum += totalPrice;

                System.out.printf("[판매번호] %d | [날짜정보] %s | [직원] %s | [결제] %s | [총액] %,d 원\n",
                        resultSet.getInt("sale_id"),
                        resultSet.getString("sale_date"),
                        resultSet.getString("user_name"),
                        resultSet.getString("payment_type"),
                        totalPrice
                );
            }
            // 판매 정보 객체들 출력 후 총합 출력
            System.out.printf("\n총 매출 : %,d 원", totalSum);
            // 해당 날짜의 매출을 찾을 수 없는 경우
            if(!found) {
                System.out.println("해당 날짜에 매출 기록이 존재하지 않습니다.");
            }
        } catch (SQLException e) {
            System.out.println("매출 조회 SQL 오류 발생" + e.getMessage());
        }
    }
}
