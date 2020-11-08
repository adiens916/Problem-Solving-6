package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class DataModel_Company {
	static Connection con;
	Statement stmt, stmt2, stmt3;
	ResultSet rs;
	String Driver = "";
	String url = "jdbc:mysql://localhost:3306/madang?&serverTimezone=Asia/Seoul&useSSL=false";
	String userid = "madang";
	String pwd = "madang";
	
	public DataModel_Company() {
		// 생성자에서 DB Connection을 시켜줌.
		con_DB();
	}
	
	public void con_DB() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			// System.out.println("드라이버 로드 성공");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			// System.out.println("데이터베이스 연결 준비...");
			con = DriverManager.getConnection(url, userid, pwd);
			// System.out.println("데이터베이스 연결 성공");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public ArrayList<String> Company_List(){
		// 뷰에서 캠핑카 회사 리스트를 반환해주는 기능
		ArrayList<String> Company_List = new ArrayList<>();		
		
		try {
			stmt = con.createStatement();
			String Query = "SELECT * FROM campingcar_rent_company";
			rs = stmt.executeQuery(Query);
			// 캠핑카 회사의 리스트 ResultSet으로 받아온 이후에 ArrayList<String>으로 가공
			while(rs.next()) {
				String Company_Info = "";
				Company_Info = rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4)
				+ "\t" + rs.getString(5) + "\t" + rs.getString(6) + "\n";
				Company_List.add(Company_Info);
			}
		} catch (Exception e1){
			System.out.println("캠핑카 회사 목록 불러오기 에러 in DataModel_Company");
		}
		// 캠핑카 회사 리스트 정보를 담은 ArrayList를 반환
		return Company_List;
	}
	
	public ArrayList<String> refresh_Company_List() {
		// 뷰에서 새로고침 기능
		// Company_List를 다시 한번 실행해주기 때문에 마찬가지로 ArrayList를 반환
		ArrayList<String> Company_List = new ArrayList<>();
        Company_List = Company_List();
        
        return Company_List;
    }
    
    public int insert_Company_Data(String Company_Name, String Company_Address, String Company_PhoneNumber, String Company_Officer_Email, String Company_Officer_Name){
    	// 뷰의 텍스트 필드에서 받은 데이터로 캠핑카 회사 테이블에 insert
    	int Insert_Check = 0;
    	
        try {
        	stmt = con.createStatement();
        	String Query = "INSERT INTO campingcar_rent_company(cp_name,cp_address,cp_number,cp_mng_email,cp_mng_name) VALUES('"
					+ Company_Name + "','" + Company_Address + "','" + Company_PhoneNumber + "','" + Company_Officer_Email + "','"
					+ Company_Officer_Name + "')";
        	Insert_Check = stmt.executeUpdate(Query);
        } catch (Exception e1) {
        	System.out.println("캠핑카 회사 데이터 입력 에러 in DataModel_Company");
        }
        
        return Insert_Check;
    }
    
    public int update_Company_Data(int Company_ID, String Company_Name, String Company_Address, String Company_PhoneNumber, String Company_Officer_Email, String Company_Officer_Name) {
    	// 뷰의 텍스트 필드에서 받은 데이터로 캠핑카 회사 테이블 update
    	int Update_Check = 0;
    	
    	try {
    		stmt = con.createStatement();
    		String Query = "update campingcar_rent_company set cp_name='" + Company_Name + "'" + ",cp_address='" + Company_Address
					+ "',cp_number='" + Company_PhoneNumber + "'" + ",cp_mng_name='" + Company_Officer_Email + "',cp_mng_email='" + Company_Officer_Name + "'"
					+ " where camping_rent_company_id='" + Company_ID + "'";
    		Update_Check = stmt.executeUpdate(Query);
    	} catch (Exception e1) {
    		System.out.println("캠핑카 회사 데이터 수정 에러 in DataModel_Company");
    	}
    	
    	return Update_Check;
    }
    
    public int delete_Company_Data(int Company_ID) {
    	// 캠핑카 회사 ID로 delete
    	int Delete_Check = 0;
    	
    	try {
    		stmt = con.createStatement();
    		String Query = "DELETE FROM campingcar_rent_company WHERE camping_rent_company_id = '" + Company_ID + "'";
    		Delete_Check = stmt.executeUpdate(Query);
    	} catch (Exception e1) {
    		System.out.println("캠핑카 회사 데이터 삭제 에러 in DataModel_Company");
    	}
    	
    	return Delete_Check;
    }
	
    //-----------테스트 영역(후에 삭제)-------------
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DataModel_Company dc = new DataModel_Company();
		ArrayList<String> rs;
		int Check;
		
		// 캠핑카 회사 리스트 출력 테스트
		/*rs = dc.Company_List();
		for(int i=0;i<rs.size();i++) {
			System.out.println(rs.get(i));
		}*/
		
		// 캠핑카 회사 리스트 새로고침 테스트
		/*rs = dc.refresh_Company_List();
		for(int i=0;i<rs.size();i++) {
			System.out.println(rs.get(i));
		}*/
		
		// 캠핑카 데이터 입력 테스트
		/*Check = dc.insert_Company_Data("AA", "강북구 인수동", "12313123", "emial", "김오키");
		if(Check == 1) System.out.println("Insert 성공");
		if(Check == 0) System.out.println("Insert 실패");*/
		
		// 캠핑카 데이터 업데이트 테스트
		/*Check = dc.update_Company_Data(16, "BB", "강북구 삼양동", "2348579", "bb@bb.com", "김태규");
		if(Check == 1) System.out.println("Update 성공");
		if(Check == 0) System.out.println("Update 실패");*/
		
		// 캠핑카 데이터 삭제 테스트
		/*Check = dc.delete_Company_Data(16);
		if(Check == 1) System.out.println("Delete 성공");
		if(Check == 0) System.out.println("Delete 실패");*/
		
	}

}
