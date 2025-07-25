package service;

import dao.WorkLogDAO;
import db.ConnectionDB;
import model.LoginUser;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDateTime;

public class WorkWageService {
    public void logout(LoginUser user) {
        try (Connection connection = ConnectionDB.getConnectionDB()) {
            WorkLogDAO workLogDAO = new WorkLogDAO(connection);

            // 해당 사원의 가장 최근 login_log 조회
            int id = user.getEmpId();       // emp_id(PK) 가져오기
            LoginUser lastLog = workLogDAO.LastLoginLog(id);

            // 계산에 필요한 정보 가져오기
            LocalDateTime loginTime = lastLog.getLoginTime();  // 로그인한 시간
            LocalDateTime currentLogout = LocalDateTime.now();  // 현재 시간
            int wage = lastLog.getHourlyWage();    // 시급 조회

            // 근무 시간 분 단위로 계산
            // 일급 계산 = 시급 / 60 * 근무시간(분)
            long workMinutes = Duration.between(loginTime, currentLogout).toMinutes();   // 일한 시간
            long dailyWage = Math.round((float) wage / 60) * workMinutes;

            // worklogdao에 업데이트
            int logId = lastLog.getLoginLogsId();
            workLogDAO.updateLogout(logId, currentLogout, workMinutes, (int) dailyWage);

            // 콘솔에 일급 출력
            System.out.printf("총 근무 시간 : %d 분\n", workMinutes);
//            System.out.printf("오늘의 일급 : %,d 원\n\n", dailyWage);

            System.out.printf("%s 빠이~~ 오늘 일당은 %,d 원 입니다.\n", lastLog.getUserName() ,dailyWage);
        } catch (SQLException e) {
            System.out.println("시급 계산 connection 오류" + e.getMessage());
        }
    }
}
