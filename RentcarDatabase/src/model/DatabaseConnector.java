package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/* DB connection을 싱글턴 패턴으로 구현
 * 이유: connection은 하나만 있으면 되는데 참조하는 곳이 많기 때문 (메모리 절약)
 * 장점: 기존에도 static으로 가져올 수 있었으나, 싱글턴 기법은 접근을 제한함으로써 임의 수정 방지 가능
 * 사용법: 필드 선언부에 Connection con = DatabaseConnector.getConnection()으로 선언하기
 * */
public class DatabaseConnector {

    /* 외부에서 getConnection 메서드가 불려지면,
     * 최초에 내부의 static class가 로딩되면서 메모리에 올라감.
     * 이후로는 생성된 인스턴스만 리턴함.
     * */
    public static Connection getConnection() {
        return ConnectionHolder.instance;
    }

    private static class ConnectionHolder {
        /* static method를 위한 static 변수 선언 */
        static String url = "jdbc:mysql://localhost:3306/madang?&serverTimezone=Asia/Seoul" +
                "&allowPublicKeyRetrieval=true&useSSL=false";
        static String user = "madang";
        static String password = "madang";

        /* private & final: 다른 곳에서 임의 수정 방지 */
        /* static: 프로그램 실행되는 동안 connection 하나만 참조할 수 있도록 */
        private static final Connection instance = getConnectionToDatabase();

        private static Connection getConnectionToDatabase() {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                // System.out.println("드라이버 로드 성공");
            } catch(ClassNotFoundException e1) {
                e1.printStackTrace();
            }
            try {
                // System.out.println("데이터베이스 연결 준비...");
                return DriverManager.getConnection(url, user, password);
                // System.out.println("데이터베이스 연결 성공");
            } catch(SQLException e1) {
                e1.printStackTrace();
            }
            return null;
        }
    }
}
