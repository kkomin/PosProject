package service;

import java.util.Currency;
import java.util.Scanner;

// 결제 방식 처리 (카드 or. 현금 구분, 잔고 갱신)
public class PaymentService {
    private final int INITIAL = 1234000;
    private int current = INITIAL;

    // 결제 처리
    public void proccessPay(int totalPrice) {
        Scanner sc = new Scanner(System.in);
        System.out.println("결제 방법 (카드 : 1 / 현금 : 2)");
        System.out.print("선택 : ");
        int method = sc.nextInt();
        sc.nextLine();

        // 결제 방법 선택
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


    // 카드 결제 처리
    public void cardPayment(int totalPrice) {
        Scanner sc = new Scanner(System.in);

        System.out.println("카드 번호 : ");
        String card = sc.nextLine();

        // 16자리만 결제 가능
        if(card.length() != 16) {
            System.out.println("카드 번호가 유효하지 않습니다.\n");
        }

        // 카드 결제
        if(current >= totalPrice) {
            current -= totalPrice;
            System.out.printf("결제 금액 : %,d 원", totalPrice);
            System.out.println("결제가 완료되었습니다.\n");
            System.out.printf("현재 잔액 : %,d 원", current);
        } else {
            System.out.println("잔액이 부족합니다.\n");
        }
    }

    // 현금 결제 처리
    public void cashPayment(int totalPrice) {

    }
}
