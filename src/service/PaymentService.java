package service;

import java.util.Scanner;

// 결제 방식 처리 (카드 or. 현금 구분, 잔고 갱신)
public class PaymentService {
    private final int INITIAL = 1234000;

    // 결제 처리
    public void proccessPay(int INITIAL) {
        Scanner sc = new Scanner(System.in);
        System.out.println("결제 방법 (카드 : 1 / 현금 : 2)");
        System.out.print("선택 : ");
        int method = sc.nextInt();
        sc.nextLine();

        switch(method) {
            case 1 :
                // 카드 선택
                break;
            case 2:
                // 현금 선택
                break;
            default:
                System.out.println("잘못된 결제 방법 입니다.\n");
        }
    }
    
    // 결제 방법 선택
    
    // 카드 결제 처리
    
    // 현금 결제 처리
}
