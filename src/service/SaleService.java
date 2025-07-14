package service;

import dao.SaleDAO;
import db.ConnectionDB;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

// 매출 기록 저장, 판매 내역 처리
public class SaleService {
    public void saleService() {
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyyMMdd");

        while(true) {
            System.out.println("조회하고 싶은 매출 날짜 (예) 20250713)");
            System.out.print("입력 : ");
            String inputDate = sc.nextLine().trim();
            try {
                LocalDate.parse(inputDate, dateFormat);
                getSalesByDate(inputDate);
                break;
            } catch (Exception e) {
                System.out.println("올바르지 않는 형식입니다.\n");
            }
        }
    }

    public void getSalesByDate(String date) {
        Connection connection;

        try {
            connection = ConnectionDB.getConnectionDB();
            SaleDAO saleDAO = new SaleDAO(connection);
            saleDAO.getSalesByDate(date);
        } catch(SQLException e) {
            System.out.println("매출 조회 오류 발생" + e.getMessage());
        }
    }

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
            saleDAO.insertSaleItem(saleId, prodId, quantity, totalPrice);

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
