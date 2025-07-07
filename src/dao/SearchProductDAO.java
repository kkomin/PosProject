package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// 제품 검색 -> NAME, COMPANY
public class SearchProductDAO {
    private final Connection connection;

    public SearchProductDAO(Connection connection) {
        this.connection = connection;
    }

    // 제품명으로 검색
    public void SearchName() {
        String searchNameSql = """
            SELECT * FROM PRODUCTS WHERE PROD_NAME LIKE ?
            """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(searchNameSql)) {
            // 파라미터 바인딩
            preparedStatement.executeQuery();
        } catch (SQLException e) {
            System.out.println("SEARCH NAME SELECT 오류" + e.getMessage());
        }
    }

    // 회사명으로 검색
    public void SearchCompany() {
        String searchCompanySql = """
                SELECT * FROM PRODUCTS WHERE COMPANY LIKE ?
                """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(searchCompanySql)){
            // 파라미터 바인딩
            preparedStatement.executeQuery();
        } catch (SQLException e) {
            System.out.println("SEARCH COMPANY SELECT 오류" + e.getMessage());
        }

    }
}
