package service;

import dao.SearchProductDAO;
import db.ConnectionDB;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class SearchService {
    public void searchProducts() {
        Scanner sc = new Scanner(System.in);

        try (Connection connection = ConnectionDB.getConnectionDB()){
            SearchProductDAO searchDAO = new SearchProductDAO(connection);
            boolean repeat = true;

            while(repeat) {
                System.out.println("===== 검색 카테고리 =====");
                System.out.println("1. 제품명");
                System.out.println("2. 회사명");
                System.out.print("\n카테고리 선택 : ");
                int input;

                try {
                    input = sc.nextInt();
                    sc.nextLine();
                    switch (input) {
                        case 1:
                            // 제품명으로 검색
                            System.out.print("\n검색할 제품명 : ");
                            String name = sc.nextLine().trim().toUpperCase();
                            searchDAO.SearchName(name);
                            repeat = false;
                            break;
                        case 2:
                            // 회사명으로 검색
                            System.out.print("\n검색할 회사명 : ");
                            String company = sc.nextLine().trim().toUpperCase();
                            searchDAO.SearchCompany(company);
                            repeat = false;
                            break;
                        default:
                            System.out.println("카테고리가 존재하지 않습니다.\n");
                    }
                } catch (Exception e) {
                    System.out.println("숫자만 입력해주세요.\n");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
