package service;

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
        String name = sc.nextLine();
        System.out.print("제조사 : ");
        String company = sc.nextLine();
        System.out.print("유통기한 (년,월,일) : ");
        String deadline = sc.nextLine();
        System.out.print("19금 여부 (Y/N) : ");
        String adult = sc.nextLine();
        System.out.print("가격 : ");
        int price = sc.nextInt();
        // 수량은 최소 10개 입력받는 예외처리 필요
        System.out.print("수량 :");
        int stock = sc.nextInt();

        // 입력값을 기반으로 Product 객체 생성
    }
}
