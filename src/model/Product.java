package model;

import java.sql.Date;

// 제품 정보 객체
public class Product {
    private int prodId;
    private String prodName;
    private String company;
    private Date expiration;
    private boolean isAdult;
    private int price;
    private int stock;


    // 제품 정보 등록 생성자
    public Product(String prodName, String company, Date expiration, boolean isAdult, int price, int stock) {
        this.prodName = prodName;
        this.company = company;
        this.expiration = expiration;
        this.isAdult = isAdult;
        this.price = price;
        this.stock = stock;
    }

    public String getProdName() {
        return prodName;
    }

    public String getCompany() {
        return company;
    }

    public Date getExpiration() {
        return expiration;
    }

    public boolean isAdult() {
        return isAdult;
    }

    public int getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

}
