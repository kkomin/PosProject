package db;

import security.DBdata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// DB 연결
public class ConnectionDB {
    // DB 연결 정보 (url, name, pw) 선언
    private static final String url = DBdata.URL;
    private static final String name = DBdata.NAME;
    private static final String password = DBdata.PASSWORD;

    // connection으로 연결
    public static Connection getConnectionDB() throws SQLException {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
        } catch (ClassNotFoundException e) {
            System.out.println("jdbc11.jar 없습니다.");
        }
        return DriverManager.getConnection(url, name, password);
    }
}
