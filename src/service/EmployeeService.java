package service;

import java.util.Scanner;

public class EmployeeService {
    public static void showMenu() {
        Scanner sc = new Scanner(System.in);

        while(true) {
            System.out.println("1. 제품 등록");
            System.out.println("2. 재고 확인");
            System.out.println("3. 제품 검색");
            System.out.println("4. 입고 처리");
            System.out.println("5. 결제");
            System.out.println("6. 매출 조회");
            System.out.println("0. 퇴근");
            System.out.print("선택 : ");

            int input = 0;
            try {
                input = sc.nextInt();
            } catch (Exception e) {
                System.out.println("숫자를 입력하세요\n");
            }
            System.out.println(input);
        }
    }
}
