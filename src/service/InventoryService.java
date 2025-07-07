package service;

import dao.InventoryDAO;
import db.ConnectionDB;
import model.Product;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

// 무작위로 제품 선택 후 입고
public class InventoryService {
    public void randomInventory() {
        try(Connection connection = ConnectionDB.getConnectionDB()) {
            InventoryDAO inventoryDAO = new InventoryDAO(connection);
            // inventory 리스트 가져오기
            List<Product> products = inventoryDAO.inventory();

            // Random으로 products 하나 뽑기
            // products -> ArrayList 형태 -> 인덱스 필요
            Random random = new Random();
            int index = random.nextInt(products.size());
            Product selected = products.get(index);

            // 입고 처리 (재고 증가 + inventory_logs 추가)
            // 입고 수량 1 ~ 10 사이 랜덤하게
            int quantity = random.nextInt(10) + 1;

            // 현재 날짜도 포함해서 입고 기록 추가
            inventoryDAO.addInventoryLog(selected.getProdId(), quantity);

            // PRODUCT의 STOCK도 수량만큼 증가
            inventoryDAO.addProducts(selected.getProdId(), quantity);

            // 입고한 정보 출력
            System.out.printf("\n[%s]가 [%d개] 입고 되었습니다.\n", selected.getProdName(), quantity);
            System.out.printf("==== [%s]의 현재 재고 : [%d개] ====\n\n", selected.getProdName(), selected.getStock() + quantity);
        }
        catch (SQLException e) {
            System.out.println("Inventory 연결 오류" + e.getMessage());
        }
    }
}