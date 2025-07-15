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

        String name;
        while(true) {
            System.out.print("제품명 : ");
            name = sc.nextLine().trim();

            if(!name.isEmpty()) {
                break;
            } else {
                System.out.println("제품명은 공백일 수 없습니다. 다시 입력해주세요.\n");
            }
        }
        String company;
        while(true) {
            System.out.print("제조사 : ");
            company = sc.nextLine().trim();

            if(!company.isEmpty()) {
                break;
            } else {
                System.out.println("제조사는 공백일 수 없습니다. 다시 입력해주세요.\n");
            }
        }

        // 유통기한 입력 시 형식 오류 예외처리
        Date expiration;
        while(true) {
            System.out.print("유통기한 (년,월,일) : ");
            String deadline = sc.nextLine().trim();

            // String 형태를 Date 형태로 형변환
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            try {
                LocalDate localDate = LocalDate.parse(deadline, formatter);
                expiration = Date.valueOf(localDate);
                break;
            } catch (Exception e) {
                System.out.println("입력 형식이 올바르지 않습니다. (ex) 20250710)");
            }
        }


        char isAdult;
        while(true) {
            System.out.print("19금 여부 (Y/N) : ");
            String adult = sc.nextLine().trim().toUpperCase();

            // 성인 여부 입력 시 true, false로 변경
            if(adult.equals("Y")) {
                isAdult = '1';
                break;
            }
            else if (adult.equals("N")) {
                isAdult = '0';
                break;
            }
            else {
                System.out.println("잘못된 입력입니다.\n");
            }
        }

        System.out.print("가격 : ");
        int price = sc.nextInt();

        int stock;
        while(true) {
            // 수량은 최소 10개 입력받는 예외처리
            System.out.print("수량 :");
            stock = sc.nextInt();

            if(stock >= 10) {
                break;
            }
            else {
                System.out.println("수량은 최소 10개 이상 입력해주세요.\n");
            }
        }

        // 입력값을 기반으로 Product 객체 생성
        Product product = new Product(name, company, expiration, isAdult, price, stock);
        ProductDAO productDAO = new ProductDAO();
        productDAO.registerProduct(
                product.getProdName(),
                product.getCompany(),
                product.getExpiration(),
                product.getIsAdult(),
                product.getPrice(),
                product.getStock());
    }
}
