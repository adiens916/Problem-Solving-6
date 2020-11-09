package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
	// SQL 연결
    /* 다른 클래스에서 DB 연결하는 방법:
    * 1. 필드 선언부에
    * 2. Connection con = DatabaseConnector.connection; 한 줄 추가
    * 3. 다른 DB 연결에 필요한 필드나 메서드는 지워도 됨 */

    /* static 선언: 프로그램 실행되는 동안 connection 하나만 참조할 수 있도록 */
    static Connection connection;

    /* static method를 위한 static 변수 선언 */
    static String url = "jdbc:mysql://localhost:3306/madang?&serverTimezone=Asia/Seoul&allowPublicKeyRetrieval=true&useSSL=false";
    static String user = "madang";
    static String password = "madang";

    /* static block: 클래스 로딩 시 맨 처음에만 딱 한 번 실행됨 -> 매번 연결할 필요 없음 */
    static {
        connectToDatabase();
    }

    static public void connectToDatabase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //System.out.println("드라이버 로드 성공");
        } catch(ClassNotFoundException e1) {
            e1.printStackTrace();
        }
        try {
            //System.out.println("데이터베이스 연결 준비...");
            connection = DriverManager.getConnection(url, user, password);
            // System.out.println("데이터베이스 연결 성공");
        } catch(SQLException e1) {
            e1.printStackTrace();
        }
    }
}
