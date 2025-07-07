package service;

import java.util.Scanner;

public class SearchService {
    public static void searchProducts() {
        Scanner sc = new Scanner(System.in);

        while(true) {
            System.out.println("===== 검색 카테고리 =====");
            System.out.println("1. 제품명");
            System.out.println("2. 회사명");
            System.out.print("\n검색할 제품명 : ");

            int input;
            try {
                input = sc.nextInt();
                switch (input) {
                    case 1:
                        // 제품명으로 검색
                        break;
                    case 2:
                        // 회사명으로 검색
                        break;
                    default:
                        System.out.println("카테고리가 존재하지 않습니다.\n");
                        return;
                }
            } catch (Exception e) {
                System.out.println("숫자만 입력해주세요.\n");
            }
        }
    }
}
