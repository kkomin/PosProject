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
    }
}
