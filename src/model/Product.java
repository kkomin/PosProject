package model;

// 제품 정보 객체
public class Product {
    private int prodId;
    private String prodName;
    private String company;
    private String expiration;
    private boolean isAdult;
    private int price;
    private int stock;


    // 제품 정보 등록 생성자
    public Product(String prodName, String company, String expiration, boolean isAdult, int price, int stock) {
        this.prodName = prodName;
        this.company = company;
        this.expiration = expiration;
        this.isAdult = isAdult;
        this.price = price;
        this.stock = stock;
    }
}
