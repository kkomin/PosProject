package dao;

import db.ConnectionDB;
import model.LoginUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

// DB 쿼리 수행
public class EmployeeService {
    // DB 연결 (db.ConnectionDB 호출)
    private Connection connection;
    public EmployeeService() {
        try {
            connection = ConnectionDB.getConnectionDB();
        } catch (SQLException e) {
            System.out.println("DB 연결 오류" + e.getMessage());
        }
    }

    // 쿼리 작성 employees의 id -> ?, pw -> ?
    // select * from employees where user_id = ? and user_pw = ?;
    private final String sql = """
            SELECT * FROM EMPLOYEES WHERE USER_ID = ? AND USER_PW = ?
            """;

    public LoginUser loginCheck(String userId, String userPw) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            // PreparedStatement로 파라미터 바인딩
            preparedStatement.setString(1, userId);
            preparedStatement.setString(2, userPw);
            // ResultSet의 결과 확인
            final ResultSet resultSet = preparedStatement.executeQuery();
            // 결과로 존재 여부 확인
            if (resultSet.next()) {
                int emp_id = resultSet.getInt("EMP_ID");
                String userName = resultSet.getString("USER_NAME");
                int hourlyWage = resultSet.getInt("HOURLY_WAGE");

                LocalDateTime loginTime = LocalDateTime.now();

                // 추후 로그인할 log_id를 loginuser 객체에 추가해주는 로직 필요
                return new LoginUser(emp_id, userId, userName, hourlyWage, loginTime, 0);
            }
        } catch (SQLException e) {
            System.out.println("쿼리 오류 발생" + e.getMessage());
        }
        return null;
    }
}
