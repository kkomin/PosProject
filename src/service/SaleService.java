package service;

import dao.SaleDAO;
import db.ConnectionDB;

import java.sql.Connection;
import java.sql.SQLException;

// 매출 기록 저장, 판매 내역 처리
public class SaleService {
    public void processSale(int empId, int prodId, int quantity, int totalPrice, String paymentType, boolean isAdultCheck) {
        Connection connection = null;
        // db 연결 생성
        try {
            connection = ConnectionDB.getConnectionDB();
            // 자동 커밋 제거 -> sale + sales_item 동시 처리
            connection.setAutoCommit(false);

            // dao 생성
            SaleDAO saleDAO = new SaleDAO(connection);

            // sales 테이블에 판매 기록 저장
            int saleId = saleDAO.insertSale(empId, totalPrice, paymentType, isAdultCheck);

            // sales_item 테이블에 판매 항목 상세 저장

            // 트랜잭션 커밋 (sale, sales_item 모든 쿼리 성공 시에만)
            connection.commit();
            System.out.println("판매가 정상 처리되었습니다.\n");

        } catch (SQLException e) {
            System.out.println("SALE 연결 오류" + e.getMessage());

            // 오류 발생 시 롤백
            if(connection != null) {
                try {
                    connection.rollback();
                    System.out.println("트랜젝션 롤백 완료!");
                } catch (SQLException ex) {
                    System.out.println("롤백 실패 : " + e.getMessage());
                }
            }
        } finally {
            // 연결 종료
            if(connection != null) {
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("DB 연결 종료 중 오류" + e.getMessage());
                }
            }
        }
    }
}
