package service;

import java.util.Scanner;

// 결제 방식 처리 (카드 or. 현금 구분, 잔고 갱신)
public class PaymentService {
    private final int INITIAL = 1234000;
    private int current = INITIAL;

    // 결제 처리
    public void proccessPay(int totalPrice) {
        Scanner sc = new Scanner(System.in);
        System.out.printf("현재 잔고 : %,d 원\n", INITIAL);
        System.out.println("결제 방법 (카드 : 1 / 현금 : 2)\n");
        System.out.print("선택 : ");
        int method = sc.nextInt();
        sc.nextLine();

        // 결제 방법 선택
        switch(method) {
            case 1 :
                // 카드 선택
                cardPayment(totalPrice);
                break;
            case 2:
                // 현금 선택
                cashPayment(totalPrice);
                break;
            default:
                System.out.println("잘못된 결제 방법 입니다.\n");
        }
    }

    // 카드 결제 처리
    public void cardPayment(int totalPrice) {
        Scanner sc = new Scanner(System.in);

        System.out.print("카드 번호 : ");
        String card = sc.nextLine();

        // 16자리만 결제 가능
        if(card.length() != 16) {
            System.out.println("카드 번호가 유효하지 않습니다.\n");
        }

        // 카드 결제
        current += totalPrice;
        System.out.printf("결제 금액 : %,d 원\n", totalPrice);
        System.out.println("결제가 완료되었습니다.\n");

        System.out.printf("현재 잔고 : %,d 원\n\n", current);
    }

    // 현금 결제 처리
    public void cashPayment(int totalPrice) {
        Scanner sc = new Scanner(System.in);

        // 현금 금액 받기
        System.out.print("현금 금액 : ");
        int cash = sc.nextInt();

        if(cash >= totalPrice) {
            int charge = cash - totalPrice;
            current -= charge;
            System.out.println("현금 결제 완료\n");
            System.out.printf("거스름돈 : %,d 원\n", charge);
            System.out.printf("현재 잔고 : %,d 원\n", current);
        }
        else {
            System.out.println("금액이 부족합니다.\n");
        }
    }

    // 결제 후 잔액 반환
    public int getCurrent() {
        return current;
    }
}
