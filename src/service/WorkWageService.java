package service;

import dao.WorkLogDAO;
import db.ConnectionDB;
import model.LoginUser;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class WorkWageService {
    public void logout(LoginUser user) {
        // 현재 시간
        try (Connection connection = ConnectionDB.getConnectionDB()) {
            WorkLogDAO workLogDAO = new WorkLogDAO(connection);

            // 해당 사원의 가장 최근 login_log 조회
            LocalDateTime currentLogout = LocalDateTime.now();

            // 로그인 시간 가져오기


            // 근무 시간 분 단위로 계산

            // 시급 조회

            // 일급 계산 = 시급 / 60 * 근무시간(분)

            // worklogdao에 업데이트

            // 콘솔에 일급 출력
        } catch (SQLException e) {
            System.out.println("시급 계산 connection 오류" + e.getMessage());
        }
    }
}
