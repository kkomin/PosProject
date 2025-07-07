package dao;

import model.LoginUser;

import java.sql.*;
import java.time.LocalDateTime;

public class WorkLogDAO {
    private Connection connection;

    public WorkLogDAO(Connection connection) {
        this.connection = connection;
    }

    // 해당 EMPLOYEE가 가장 마지막에 로그인한 기록 가져오기
    public LoginUser LastLoginLog(int empId) {
        String lastLogSql = """
                SELECT l.log_id, l.login_time, l.logout_time,e.user_name, e.user_id, e.hourly_wage
                   FROM login_logs l
                   JOIN employees e ON l.emp_id = e.emp_id
                   WHERE l.emp_id = ?
                   ORDER BY l.log_id DESC
                   FETCH FIRST 1 ROWS ONLY;
            """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(lastLogSql)){
            preparedStatement.setInt(1, empId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                int logId = resultSet.getInt("log_id");
                int employeeId = resultSet.getInt("emp_id");
                String userId = resultSet.getString("user_id");
                String userName = resultSet.getString("user_name");
                int hourlyWage = resultSet.getInt("hourly_wage");
                LocalDateTime loginTime = resultSet.getTimestamp("login_time").toLocalDateTime();

                return new LoginUser(employeeId, userId, userName, hourlyWage, loginTime, logId);
            }
        } catch (SQLException e) {
            System.out.println("LOGIN_LOGS SELECT 오류" + e.getMessage());
        }
        return null;
    }

    // 로그아웃 시간 업데이트
    public void updateLogout(int logId, LocalDateTime logoutTime, long workMinutes, int dailyWage) {
        String logoutLog = """
                UPDATE LOGIN_LOGS
                SET LOGOUT_TIME = ?, WORK_MINUTES = ?, DAILY_WAGE = ?
                WHERE LOG_ID = ?
                """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(logoutLog)){
            preparedStatement.setInt(1, logId);
            preparedStatement.setDate(2, Date.valueOf(logoutTime.toLocalDate()));
            preparedStatement.setLong(3, workMinutes);
            preparedStatement.setInt(4, dailyWage);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("LOGOUT UPDATE 오류" + e.getMessage());
        }
    }

}
