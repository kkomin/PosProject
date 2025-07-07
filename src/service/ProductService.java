package service;

import dao.InventoryDAO;
import dao.ProductDAO;
import db.ConnectionDB;
import model.Product;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// 제품 선택 기능 (유통기한 체크, 재고 감소)
public class ProductService {

    // DB에서 제품 목록 출력
    public List<Product> getAllProducts() {
        try (Connection connection = ConnectionDB.getConnectionDB()){
            InventoryDAO inventoryDAO = new InventoryDAO(connection);
            return inventoryDAO.inventory();
        } catch (SQLException e) {
            System.out.println("제품 전체 목록 조회 중 오류 발생" + e.getMessage());
        }
        return new ArrayList<>();
    }

    // 유통기한 비교
    public boolean isExpired(Product product) {
        Date today = new Date(System.currentTimeMillis());
        return product.getExpiration().before(today);
    }

    // 해당 제품이 19금인지 확인
    public boolean isAdult(Product product) {
        // '1'이면 성인으로 true 반환
        return product.isAdult() == '1';
    }

    // 재고 차감 처리
}
