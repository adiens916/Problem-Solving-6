package model;

import controller.dataClass.CompanyInfo;
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
	
	public String Company_List(){
		// 뷰에서 캠핑카 회사 리스트를 반환해주는 기능
		String Company_Info = "";
		
		try {
			stmt = con.createStatement();
			String Query = "SELECT * FROM campingcar_rent_company";
			rs = stmt.executeQuery(Query);

			while(rs.next()) {
				
				Company_Info += rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4)
				+ "\t" + rs.getString(5) + "\t" + rs.getString(6) + "\n";
			}
		} catch (Exception e1){
			System.out.println("캠핑카 회사 목록 불러오기 에러 in DataModel_Company"+e1);
		}
		// 캠핑카 회사 리스트 정보를 담은 ArrayList를 반환
		return Company_Info;
	}
	
	
	public String search_Company_By_CompanyID(String Company_ID){
		
		String Company_Info = "";
		
		try {
			stmt = con.createStatement();
			String Query = "SELECT * FROM campingcar_rent_company WHERE camping_rent_company_id='"+ Company_ID + "'";
			rs = stmt.executeQuery(Query);
			
			if(rs.next()) {
				Company_Info += rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t"
						+ rs.getString(4) + "\t" + rs.getString(5) + "\t" + rs.getString(6) + "\n";  
			}
			
		} catch(Exception e1) {
			System.out.println("캠핑카 회사 정보 검색 에러 in DataModel_Company"+e1);
		}
		
		return Company_Info;
	}
    
    public int insert_Company_Data(CompanyInfo Company){
    	// 뷰의 텍스트 필드에서 받은 데이터 ArrayList를 받은 후 캠핑카 회사 테이블에 insert
    	int Insert_Check = 0;
    	
    	if(Company.companyName.length()==0||Company.companyAddress.length()==0||Company.companyPhoneNumber.length()==0||Company.companyOfficerEmail.length()==0||Company.companyOfficerName.length()==0) {
    		System.out.println("모든 텍스트 필드를 채워주세요.");
    		Insert_Check = 2; 
    	}
    	else {
        try {
        	stmt = con.createStatement();
        	String Query = "INSERT INTO campingcar_rent_company(cp_name,cp_address,cp_number,cp_mng_email,cp_mng_name) VALUES('"
					+ Company.companyName + "','" + Company.companyAddress + "','" + Company.companyPhoneNumber + "','" + Company.companyOfficerEmail + "','"
					+ Company.companyOfficerName + "')";
        	Insert_Check = stmt.executeUpdate(Query);
        } catch (Exception e1) {
        	System.out.println("캠핑카 회사 데이터 입력 에러 in DataModel_Company"+e1);
        }
        }
        
        return Insert_Check;
    }
    
    public int update_Company_Data(CompanyInfo Company) {
    	// 뷰의 텍스트 필드에서 받은 데이터 ArrayList를 받은 후 캠핑카 회사 테이블 update
    	int Update_Check = 0;
    	
    	if(Company.companyName.length()==0||Company.companyAddress.length()==0||Company.companyPhoneNumber.length()==0||Company.companyOfficerEmail.length()==0||Company.companyOfficerName.length()==0) {
    		System.out.println("모든 텍스트 필드를 채워주세요.");
    		Update_Check = 2; 
    	}
    	else {
    	try {
    		stmt = con.createStatement();
    		String Query = "update campingcar_rent_company set cp_name='" + Company.companyName + "'" + ",cp_address='" + Company.companyAddress
					+ "',cp_number='" + Company.companyPhoneNumber + "'" + ",cp_mng_name='" + Company.companyOfficerName + "',cp_mng_email='" + Company.companyOfficerEmail + "'"
					+ " where camping_rent_company_id='" + Company.companyID + "'";
    		Update_Check = stmt.executeUpdate(Query);
    	} catch (Exception e1) {
    		if(Company.isNull()){
        		System.out.println("모든 텍스트 필드를 채워주세요.");
        		Update_Check = 2;
        	}
    		System.out.println("캠핑카 회사 데이터 수정 에러 in DataModel_Company");
    	}
    	}
    	
    	return Update_Check;
    }
    
    public int delete_Company_Data(String Company_ID) {
    	// 캠핑카 회사 ID로 delete
    	int Delete_Check = 0;
    	
    	try {
    		stmt = con.createStatement();
    		String Query = "DELETE FROM campingcar_rent_company WHERE camping_rent_company_id = '" + Company_ID + "'";
    		Delete_Check = stmt.executeUpdate(Query);
    	} catch (Exception e1) {
    		if(Company_ID.length() == 0) {
    			System.out.println("삭제할 캠핑카 회사의 ID를 입력해주세요.");
    		}
    		System.out.println("캠핑카 회사 데이터 삭제 에러 in DataModel_Company");
    	}
    	
    	return Delete_Check;
    }

    /*
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
		/*ArrayList<String> Company_Data = new ArrayList<>();
		Company_Data.add("");
		Company_Data.add("AA");
		Company_Data.add("강북구 인수동");
		Company_Data.add("12313123");
		Company_Data.add("emial");
		Company_Data.add("김오키");
		Check = dc.insert_Company_Data(Company_Data);
		if(Check == 1) System.out.println("Insert 성공");
		if(Check == 0) System.out.println("Insert 실패");*/
		
		
		/*Check = dc.insert_Company_Data("AA", "강북구 인수동", "12313123", "emial", "김오키");
		if(Check == 1) System.out.println("Insert 성공");
		if(Check == 0) System.out.println("Insert 실패");*/
		
		// 캠핑카 데이터 업데이트 테스트
		/*ArrayList<String> Company_Data1 = new ArrayList<>();
		Company_Data1.add("16");
		Company_Data1.add("BB");
		Company_Data1.add("강북구 삼양동");
		Company_Data1.add("2348579");
		Company_Data1.add("bb@bb.com");
		Company_Data1.add("김태규");
		Check = dc.update_Company_Data(Company_Data1);
		if(Check == 1) System.out.println("Update 성공");
		if(Check == 0) System.out.println("Update 실패");*/
		/*Check = dc.update_Company_Data(16, "BB", "강북구 삼양동", "2348579", "bb@bb.com", "김태규");
		if(Check == 1) System.out.println("Update 성공");
		if(Check == 0) System.out.println("Update 실패");*/
		
		// 캠핑카 데이터 삭제 테스트
		/*Check = dc.delete_Company_Data("16");
		if(Check == 1) System.out.println("Delete 성공");
		if(Check == 0) System.out.println("Delete 실패");*/
		
	//}

}
