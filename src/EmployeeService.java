import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// DB 쿼리 수행
public class EmployeeService {
    // DB 연결 (ConnectionDB 호출)
    Connection connection = ConnectionDB.getConnectionDB();

    // 쿼리 작성 employees의 id -> ?, pw -> ?
    // select * from employees where user_id = ? and user_pw = ?;
    private final String sql = """
            SELECT * FROM EMPLOYEES WHERE USER_ID = ? AND USER_PW = ?
            """;
    public EmployeeService() throws SQLException {
        System.out.println("DB 연결 오류 발생");
    }

    public boolean loginCheck(String userId, String userPw) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            // PreparedStatement로 파라미터 바인딩
            preparedStatement.setString(1, userId);
            preparedStatement.setString(2, userPw);
            // ResultSet의 결과 확인
            final ResultSet resultSet = preparedStatement.executeQuery();
            // 결과로 존재 여부 확인
            return resultSet.next();

        } catch (SQLException e) {
            System.out.println("쿼리 오류 발생" + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
