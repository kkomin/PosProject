package main;

import dao.EmployeeDAO;
import dao.LoginLogDAO;
import db.ConnectionDB;
import model.LoginUser;
import service.EmployeeService;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Scanner;

// 사용자 로그인 (메인)
public class LoginManager {
    public static void main(String[] args) {
        try(Connection connection = ConnectionDB.getConnectionDB()) {
            // Scanner로 id, pw 입력 받기
            Scanner sc = new Scanner(System.in);
            while(true) {
                System.out.print("ID : ");
                String id = sc.nextLine().trim();
                System.out.print("PW : ");
                String pw = sc.nextLine().trim();

                // 로그인 여부 확인하는 dao.EmployeeService 객체 생성
                EmployeeDAO employeeDao = new EmployeeDAO(connection);
                LoginUser loginUser = employeeDao.loginCheck(id, pw);

                // 로그인 성공 여부에 따른 메세지 출력
                if(loginUser != null) {
                    System.out.println("로그인 성공!");
                    System.out.printf("사원 %s 님, 안녕하세요\n", loginUser.getUserName());

                    // DB에 출근 기록 저장 + log_id 변환 -> loginUser 저장
                    LoginLogDAO loginLogDao = new LoginLogDAO(connection);

                    // 시간 저장 시 나노 단위 제거
                    int logId = loginLogDao.SaveLoginLog(loginUser.getEmpId(), loginUser.getLoginTime());
                    // 로그인한 기록 id를 loginUser에 저장 -> 퇴근 시 활용
                    loginUser.setLoginLogsId(logId);

                    // 시간은 초까지만 저장
                    loginUser.setLoginTime(LocalDateTime.now().withNano(0));
                    System.out.printf("출근 시간 : %s\n\n", loginUser.getLoginTime());

                    // 로그인 반복문 종료
                    EmployeeService.showMenu(loginUser);
                    break;
                }
                else {
                    System.out.println("정보가 일치하지 않습니다.\n");
                }
            }
        } catch (SQLException e) {
            System.out.println("SQL Connection 불러오기 실패" + e.getMessage());
        }
    }
}
