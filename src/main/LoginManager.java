package main;

import dao.EmployeeService;
import dao.LoginLog;
import model.LoginUser;

import java.time.LocalDateTime;
import java.util.Scanner;

// 사용자 로그인 (메인)
public class LoginManager {
    public static void main(String[] args) {
        // Scanner로 id, pw 입력 받기
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.print("ID : ");
            String id = sc.nextLine().trim();
            System.out.print("PW : ");
            String pw = sc.nextLine().trim();

            // 로그인 여부 확인하는 dao.EmployeeService 객체 생성
            EmployeeService employeeService = new EmployeeService();
            LoginUser loginUser = employeeService.loginCheck(id, pw);

            // 로그인 성공 여부에 따른 메세지 출력
            if(loginUser != null) {
                System.out.println("로그인 성공!");
                System.out.printf("사원 %s 님, 안녕하세요", loginUser.getUserName());
                // 로그인 성공 시 사원 정보 (id, name, 로그인 date, time 전달)
                // 1. loginuser 객체 생성 <- 로그인 정보 + loginTime + emp_id 저장


                LocalDateTime currentDateTime = LocalDateTime.now();
                LoginLog loginLog = new LoginLog();
//                loginLog.SaveLoginLog(, currentDateTime);
                // 2. 호출 -> DB에 출근 기록 저장 + log_id 변환 -> loginUser 저장
                break;
            }
            else {
                System.out.println("정보가 일치하지 않습니다.\n");
            }
        }
    }
}
