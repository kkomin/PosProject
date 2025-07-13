package service;

import dao.InventoryDAO;
import db.ConnectionDB;
import model.LoginUser;
import model.Product;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

// 사용자 주문 처리
public class ProductSelectService {
    private final LoginUser loginUser;
    private final ProductService productService;
    private final CalculateService calculateService;
    private final PaymentService paymentService;

    public ProductSelectService(LoginUser loginUser, ProductService productService, CalculateService calculateService, PaymentService paymentService) {
        this.loginUser = loginUser;
        this.productService = productService;
        this.calculateService = calculateService;
        this.paymentService = paymentService;
    }

    public void selectProduct() {
        Scanner sc = new Scanner(System.in);

        // 제품 목록 출력
        productService.getAllProducts();

        // 사용자로부터 제품 선택 받기
        System.out.print("구매할 상품(ID) : ");
        int prod = sc.nextInt();
        sc.nextLine();

        // 선택한 제품 정보 가져오기
        Product selectedProd = getSelectedProductId(prod);
        if (selectedProd == null) {
            System.out.println("해당 상품은 존재하지 않습니다.\n");
            return;
        }

        // 유통기한 확인
        if(productService.isExpired(selectedProd)) {
            System.out.println("이 제품은 유통기한이 지났습니다.\n");
            return;
        }

        // 성인 인증 체크
        if(productService.isAdult(selectedProd)) {
            System.out.println("성인 제품입니다.");
            System.out.print("성인입니까? (Y / N) : ");
            String answer = sc.nextLine().trim().toUpperCase();

            if(answer.equals("N")) {
                System.out.println("성인 제품은 성인만 구매가 가능합니다.\n");
                return;
            }
        }

        // 수량 입력
        System.out.print("구매 수량 : ");
        int inputAmount = sc.nextInt();
        sc.nextLine();

        // 가격 계산
        int total = calculateService.totalPrice(selectedProd, inputAmount);
        System.out.printf("총액 : %,d 원\n", total);

        // 재고 차감
        if(productService.reduceStock(selectedProd, inputAmount)) {
            paymentService.proccessPay(total);

            // 판매 기록 저장
            SaleService saleService = new SaleService();
            int empId = loginUser.getEmpId();
            String paymentType = paymentService.proccessPay(total);
            boolean isAdultCheck = selectedProd.getIsAdult() == '1';
            // 결제 후 잔고 출력
            int currentBalance = paymentService.getCurrent();
            System.out.printf("결제 후 잔고 : %,d 원\n", currentBalance);

            saleService.processSale(
                    empId,
                    selectedProd.getProdId(),
                    inputAmount,
                    total,
                    paymentType,
                    isAdultCheck
            );
        } else {
            System.out.println("구매가 취소되었습니다.\n");
        }
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
