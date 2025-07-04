package model;

// 로그인 성공 시 사원의 정보를 담는 객체
import java.time.LocalDateTime;
public class LoginUser {
    // 변수 생성
    // emp_id, user_id, user_name, hourly_wage, 로그인한 시간, 날짜, login_log_id
    private int empId;
    private String userId;
    private String userName;
    private int hourlyWage;
    private LocalDateTime loginTime;
    private int loginLogsId;

    // 로그인 성공 시 사용자 정보 받아줌
    public LoginUser(int empId, String userId, String userName, int hourlyWage, LocalDateTime loginTime, int loginLogsId) {
        this.empId = empId;
        this.userId = userId;
        this.userName = userName;
        this.hourlyWage = hourlyWage;
        this.loginTime = loginTime;
        this.loginLogsId = loginLogsId;
    }

    public int getEmpId() {
        return empId;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public int getHourlyWage() {
        return hourlyWage;
    }

    public LocalDateTime getLoginTime() {
        return loginTime;
    }

    public int getLoginLogsId() {
        return loginLogsId;
    }

    // 현재 날짜 및 시간 저장
    public void setLoginTime(LocalDateTime loginTime) {
        this.loginTime = loginTime;
    }

    public void setLoginLogsId(int loginLogsId) {
        this.loginLogsId = loginLogsId;
    }

    // 프로그램 종료 시 일급 계산 - 이후 구현
}
