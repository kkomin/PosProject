package service;

import dao.GetProductsDAO;
import db.ConnectionDB;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class EmployeeService {
    public static void showMenu() {
        Scanner sc = new Scanner(System.in);
        try (Connection connection = ConnectionDB.getConnectionDB()){
            while(true) {
                System.out.println("1. 제품 등록");
                System.out.println("2. 재고 확인");
                System.out.println("3. 제품 검색");
                System.out.println("4. 입고 처리");
                System.out.println("5. 결제");
                System.out.println("6. 매출 조회");
                System.out.println("0. 퇴근");
                System.out.print("선택 : ");

                int input;
                try {
                    input = sc.nextInt();
                    switch (input) {
                        case 1 :
                            // 제품 등록 메서드 호출
                            ProductRegister.registerProduct();
                            break;
                        case 2:
                            // 재고 확인 메서드 호출
                            GetProductsDAO getProductsDAO = new GetProductsDAO(connection);
                            getProductsDAO.getProducts();
                            break;
                        case 3:
                            // 제품 검색 메서드 호출
                            break;
                        case 4:
                            // 입고 처리 메서드 호출
                            InventoryService inventoryService = new InventoryService();
                            inventoryService.randomInventory();
                            break;
                        case 5:
                            // 결제 메서드 호출
                            break;
                        case 6 :
                            // 매출 조회 메서드 호출
                            break;
                        case 0:
                            // 퇴근 (일급 계산) 메서드 호출
                            return;
                        default:
                            System.out.println("잘못 입력하셨습니다.\n");
                    }
                } catch (Exception e) {
                    System.out.println("숫자를 입력하세요\n");
                }
            }
        } catch (SQLException e) {
            System.out.println("재고 확인 connection 오류");
        }
    }
}
