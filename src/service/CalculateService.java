package service;

import model.Product;

public class CalculateService {
    // 제품 선택 -> 유통기한 체크 -> 19금 확인 -> 결제 -> 판매 기록
    // 가격 계산(총액)
    public int totalPrice(Product product, int quantity) {
        // 총합 계산
        return product.getPrice() * quantity;
    }
}
