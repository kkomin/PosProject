package service;

import dao.InventoryDAO;
import db.ConnectionDB;
import model.Product;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

// 무작위로 제품 선택 후 입고
public class InventoryService {
    public void randomInventory() {
        try(Connection connection = ConnectionDB.getConnectionDB()) {
            InventoryDAO inventoryDAO = new InventoryDAO(connection);
            // inventory 리스트 가져오기
            List<Product> products = inventoryDAO.inventory();
        }
        catch (SQLException e) {
            System.out.println("Inventory 연결 오류" + e.getMessage());
        }
    }
}