package service;

import dao.SaleDAO;
import db.ConnectionDB;

import java.sql.Connection;
import java.sql.SQLException;

// 매출 기록 저장, 판매 내역 처리
public class SaleService {
    public void processSale(int empId, int prodId, int quantity, int totalPrice, String paymentType, boolean isAdultCheck) {
        // db 연결 생성
        try (Connection connection = ConnectionDB.getConnectionDB()){
            SaleDAO saleDAO = new SaleDAO(connection);

            int saleId = saleDAO.insertSale(empId, totalPrice, paymentType, isAdultCheck);
        } catch (SQLException e) {
            System.out.println("SALE 연결 오류" + e.getMessage());
        }
    }

}
