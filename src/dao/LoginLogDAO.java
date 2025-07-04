package dao;

import java.sql.*;
import java.time.LocalDateTime;

// login_logs 테이블 db 작업용
// 로그인 시간 기록, 로그아웃(종료) 시간 업데이트, 근무시간 계산
public class LoginLogDAO {
    // 로그인한 유저 정보 추가 해주는 sql
    public final String insertSql = """
            INSERT INTO LOGIN_LOGS(LOG_ID, EMP_ID, LOGIN_TIME)
            VALUES (login_logs_seq.NEXTVAL, ?, ?)
            """;

    // id 값 받아오는 sql
    public final String getIdSql = """
            SELECT LOGIN_LOGS_SEQ.CURRVAL FROM DUAL
            """;

    // DB 연결 - ConnectionDB
    private final Connection connection;

    public LoginLogDAO(Connection connection) {
        this.connection = connection;
    }

    // 로그인 시 loginmanager에서 받아온 값 insert -> empid, localdatetime
    // 값 insert
    public int SaveLoginLog(int empId, LocalDateTime time) {
        // PreparedStatement 자동으로 닫기
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertSql)) {
            // 파라미터 바인딩
            preparedStatement.setInt(1, empId);
            preparedStatement.setTimestamp(2, Timestamp.valueOf(time));     // timestamp로 변환 후 바인딩

            preparedStatement.executeUpdate();

            // id 값 조회
            PreparedStatement pres = connection.prepareStatement(getIdSql);
            ResultSet resultSet = pres.executeQuery();
            // id 값이 존재하면
            if(resultSet.next()) {
                // 첫번째 컬럼 값 추출
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("오류" + e.getMessage());
        }
        return -1;
    }
}
