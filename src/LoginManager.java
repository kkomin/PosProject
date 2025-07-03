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

            // 로그인 여부 확인하는 EmployeeService 객체 생성
            EmployeeService employeeService = new EmployeeService();
            // login(id, pw) 호출 및 결과 확인
            boolean isLogin = employeeService.loginCheck(id, pw);
            // 로그인 성공 여부에 따른 메세지 출력
            if(isLogin) {
                System.out.println("로그인 성공!");
                break;
            }
            else {
                System.out.println("정보가 일치하지 않습니다.\n");
            }
        }
    }
}
