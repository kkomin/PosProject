package service;

import dao.ProductDAO;
import model.Product;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

// 제품 등록
public class ProductRegister {
    public static void registerProduct() {
        // 제품 입력 -> 제품명, 제조사, 유통기한, 19금 여부, 가격
        Scanner sc = new Scanner(System.in);
        System.out.println("=========================");
        System.out.println("제품 입력");
        System.out.println("=========================");
        System.out.print("제품명 : ");
        String name = sc.nextLine().trim();
        System.out.print("제조사 : ");
        String company = sc.nextLine().trim();

        // 유통기한 입력 시 형식 오류 예외처리
        System.out.print("유통기한 (년,월,일) : ");
        String deadline = sc.nextLine().trim();

        // String 형태를 Date 형태로 형변환
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        Date expiration = null;
        try {
            LocalDate localDate = LocalDate.parse(deadline, formatter);
            expiration = Date.valueOf(localDate);

        } catch (Exception e) {
            System.out.println("입력 형식이 올바르지 않습니다. (ex) 20250710)");
        }


        System.out.print("19금 여부 (Y/N) : ");
        String adult = sc.nextLine().trim();

        // 성인 여부 입력 시 true, false로 변경
        boolean isAdult;
        if(adult.equals("y") || adult.equals("Y")) {
            isAdult = true;
        }
        else if (adult.equals("n") || adult.equals("N")) {
            isAdult = false;
        }
        else {
            System.out.println("잘못된 입력입니다.\n");
            return;
        }

        System.out.print("가격 : ");
        int price = sc.nextInt();

        // 수량은 최소 10개 입력받는 예외처리
        System.out.print("수량 :");
        int stock = sc.nextInt();

        if(stock < 10) {
            System.out.println("수량은 최소 10개 이상 입력해주세요.\n");
            return;
        }

        // 입력값을 기반으로 Product 객체 생성
        // prod_id 값은 임시로 설정
        Product product = new Product(name, company, expiration, isAdult, price, stock);
        ProductDAO productDAO = new ProductDAO();
        productDAO.registerProduct(
                product.getProdName(),
                product.getCompany(),
                product.getExpiration(),
                product.isAdult(),
                product.getPrice(),
                product.getStock());
    }
}
