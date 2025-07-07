package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// 제품 검색 -> NAME, COMPANY
public class SearchProductDAO {
    private final Connection connection;

    public SearchProductDAO(Connection connection) {
        this.connection = connection;
    }

    // 제품명으로 검색
    public void SearchName(String name) {
        String searchNameSql = """
            SELECT * FROM PRODUCTS WHERE PROD_NAME LIKE ?
            """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(searchNameSql)) {
            // 파라미터 바인딩
            preparedStatement.setString(1, "%" + name + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean found = false;

            while(resultSet.next()) {
                found = true;
                String prodName = resultSet.getString("PROD_NAME");
                String prodCompany = resultSet.getString("COMPANY");
                int price = resultSet.getInt("PRICE");
                int stock = resultSet.getInt("STOCK");
                System.out.printf("제품명 : %s | 회사 %s | 가격 : %,d | 수량 : %d\n", prodName, prodCompany, price, stock);
            }
            if(!found) {
                System.out.println("검색 결과가 존재하지 않습니다.\n");
            }

        } catch (SQLException e) {
            System.out.println("SEARCH NAME SELECT 오류\n" + e.getMessage());
        }
    }

    // 회사명으로 검색
    public void SearchCompany(String company) {
        String searchCompanySql = """
                SELECT * FROM PRODUCTS WHERE COMPANY LIKE ?
                """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(searchCompanySql)){
            // 파라미터 바인딩
            preparedStatement.setString(1, "%" + company + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean found = false;

            while(resultSet.next()) {
                found = true;
                String prodName = resultSet.getString("PROD_NAME");
                String prodCompany = resultSet.getString("COMPANY");
                int price = resultSet.getInt("PRICE");
                int stock = resultSet.getInt("STOCK");
                System.out.printf("제품명 : %s | 회사 %s | 가격 : %,d | 수량 : %d\n", prodName, prodCompany, price, stock);
            }
            if(!found) {
                System.out.println("검색 결과가 존재하지 않습니다.\n");
            }
        } catch (SQLException e) {
            System.out.println("SEARCH COMPANY SELECT 오류\n" + e.getMessage());
        }

    }
}
