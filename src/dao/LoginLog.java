package dao;

import db.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;

// login_logs 테이블 db 작업용
// 로그인 시간 기록, 로그아웃(종료) 시간 업데이트, 근무시간 계산
public class LoginLog {
    // DB 연결 - ConnectionDB
    private Connection connection;

    public LoginLog() {
        try {
            connection = ConnectionDB.getConnectionDB();
        } catch (SQLException e) {
            System.out.println("DB 연결 오류" + e.getMessage());
        }
    }

    // update

}
