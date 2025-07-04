package service;

import java.util.Objects;
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
        System.out.print("유통기한 (년,월,일) : ");
        String deadline = sc.nextLine().trim();
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
        // 수량은 최소 10개 입력받는 예외처리 필요
        System.out.print("수량 :");
        int stock = sc.nextInt();

        // 입력값을 기반으로 Product 객체 생성
    }
}
