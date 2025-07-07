package service;

import dao.InventoryDAO;
import db.ConnectionDB;
import model.Product;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

// 제품 선택 기능 (유통기한 체크, 재고 감소)
public class ProductService {
    // DB에서 제품 목록 출력
    public void getAllProducts() {
        try (Connection connection = ConnectionDB.getConnectionDB()){
            InventoryDAO inventoryDAO = new InventoryDAO(connection);
            List<Product> productList = inventoryDAO.inventory();
            if(productList.isEmpty()) {
                System.out.println("등록된 제품이 없습니다. 제품 준비 중...\n");
            }
            else {
                System.out.println("현재 등록된 제품 목록을 출력합니다.");
                for(Product product : productList) {
                    System.out.printf("ID : %d | 이름 : %s | 회사 : %s | 가격 : %,d | 재고 : %d",
                            product.getProdId(), product.getProdName(), product.getCompany(), product.getPrice(), product.getStock());
                }
            }
        } catch (SQLException e) {
            System.out.println("제품 전체 목록 조회 중 오류 발생" + e.getMessage());
        }
    }

    // 유통기한 비교
    public boolean isExpired(Product product) {
        // DB에서 제품 정보 조회 후 유통기한 비교
        Date today = new Date(System.currentTimeMillis());
        return product.getExpiration().before(today);
    }

    // 해당 제품이 19금인지 확인
    public boolean isAdult(Product product) {
        // '1'이면 성인으로 true 반환
        return product.getIsAdult() == '1';
    }

    // 재고 차감 처리 -> 제품 재고 수량 줄이기 (재고가 부족하면 판매 막기)
    public boolean reduceStock(int selectedId) {
        // DB에서 재고 현황 (stock) 조회

        return false;
    }
}
