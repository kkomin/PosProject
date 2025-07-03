package dao;

import db.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

// login_logs 테이블 db 작업용
// 로그인 시간 기록, 로그아웃(종료) 시간 업데이트, 근무시간 계산
public class LoginLog {
    // DB 연결 - ConnectionDB
    private Connection connection;
    // sql 생성
    private final String insertSql = """
            INSERT INTO LOGIN_LOGS(LOG_ID, EMP_ID, LOGIN_TIME)
            VALUES (login_logs_seq.NEXTVAL, ?, ?)
            """;

    public LoginLog() {
        try {
            connection = ConnectionDB.getConnectionDB();
        } catch (SQLException e) {
            System.out.println("DB 연결 오류" + e.getMessage());
        }
    }

    // 로그인 시 loginmanager에서 받아온 값 insert -> empid, localdatetime
    public int SaveLoginLog(int empId, LocalDateTime time) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertSql);
            // 파라미터 바인딩
            preparedStatement.setInt(1, empId);
            preparedStatement.setTimestamp(2, Timestamp.valueOf(time));     // timestamp로 변환 후 바인딩

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("SQL 문구 오류" + e.getMessage());
        }
        return empId;
    }
}
