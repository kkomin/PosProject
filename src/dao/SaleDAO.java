package dao;

import java.sql.Connection;

public class SaleDAO {
    private final Connection connection;

    public SaleDAO(Connection connection) {
        this.connection = connection;
    }

    // SALE 테이블에 insert
    public void insertSale() {
    String insertSql = """
            INSERT INTO SALES(SALE_ID, SALE_DATE, EMP_ID, TOTAL_PRICE, PAYMENT_tYPE, ADULT_CHECK)
            VALUES(SALE_SEQ.NEXTVAL, SYSDATE, ?, ?, ?, ?)
            """;
    }

    // sale_item 테이블에 insert
    public void insertSaleItem() {

    }
}
