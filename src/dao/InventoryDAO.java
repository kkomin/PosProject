package dao;

import java.sql.Connection;

public class InventoryDAO {
    private final Connection connection;

    // 모든 제품 조회
    private final String selectSql = """
            SELECT * FROM PRODUCTS
            """;

    public InventoryDAO(Connection connection) {
        this.connection = connection;
    }
}
