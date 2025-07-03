package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// DB 연결
public class ConnectionDB {
    // DB 연결 정보 (url, name, pw) 선언
    private static final String URL = "jdbc:oracle:thin:@10.10.108.142:1521/xe";
    private static final String NAME = "c##posuser";
    private static final String PASSWORD = "pos12345";

    // connection으로 연결
    public static Connection getConnectionDB() throws SQLException {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
        } catch (ClassNotFoundException e) {
            System.out.println("jdbc11.jar 없습니다.");
        }
        return DriverManager.getConnection(URL, NAME, PASSWORD);
    }
}
