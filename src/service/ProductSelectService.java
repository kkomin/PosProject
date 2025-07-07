package service;

import dao.InventoryDAO;
import dao.ProductDAO;
import db.ConnectionDB;
import model.Product;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

// 사용자 주문 처리
public class ProductSelectService {
    public void selectProduct(Product product) {
        Scanner sc = new Scanner(System.in);
        ProductService productService = new ProductService();

        // 제품 목록 출력
        productService.getAllProducts();

        // 사용자로부터 제품 선택 받기
        System.out.print("구매할 상품(ID) : ");
        int prod = sc.nextInt();
        sc.nextLine();

        // 선택한 제품 정보 가져오기
        Product selectedProd = getSelectedProductId(prod);

        // 유통기한 확인
        if(productService.isExpired(product)) {
            System.out.println("이 제품은 유통기한이 지났습니다.\n");
            return;
        }

        // 성인 인증 체크

        // 수량 입력

        // 가격 계산

        // 재고 차감
    }

    // 선택한 제품 값 반환하는 메서드
    private Product getSelectedProductId(int prodId) {
        try( Connection connection = ConnectionDB.getConnectionDB() ){
            InventoryDAO inventoryDAO = new InventoryDAO(connection);
            List<Product> productList = inventoryDAO.inventory();

            for(Product product : productList) {
                // 해당 id를 가진 제품이 존재한다면
                if(product.getProdId() == prodId) {
                    // 해당 제품 반환
                    return product;
                }
            }
        } catch (SQLException e) {
            System.out.println("Product ID 조회 오류" + e.getMessage());
        }
        return null;
    }
}
