package model;

// 판매 상세 기록용 객체
public class SaleItem {
    private int itemId;
    private int saleId;
    private int prodId;
    private int quantity;
    private int subtotal;

    public SaleItem() {}

    public SaleItem(int itemId, int saleId, int prodId, int quantity) {
        this.itemId = itemId;
        this.saleId = saleId;
        this.prodId = prodId;
        this.quantity = quantity;
    }

    public int getItemId() {
        return itemId;
    }

    public int getSaleId() {
        return saleId;
    }

    public int getProdId() {
        return prodId;
    }

    public int getQuantity() {
        return quantity;
    }
}
