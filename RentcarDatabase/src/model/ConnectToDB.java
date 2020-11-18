package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectToDB {
	public static Connection con;
	String Driver="";
	static String url="jdbc:mysql://localhost:3306/madang?&serverTimezone=Asia/Seoul&useSSL=false"; 
	static String userid="madang";
	static String pwd="madang";
	
	public static void conDB() { 
	  try {
	    Class.forName("com.mysql.cj.jdbc.Driver");   
	    System.out.println("드라이버 로드 성공");
	  } catch(ClassNotFoundException e1) {
	      e1.printStackTrace();
	  }
	try {
	    System.out.println("데이터베이스 연결 준비...");
	    con=DriverManager.getConnection(url, userid, pwd); 
	    System.out.println("데이터베이스 연결 성공");
	  } catch(SQLException e1) {
	      e1.printStackTrace();
	    }
	}
}