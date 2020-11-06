package model;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CustomerModel {
	static Connection con;
	Statement stmt, stmt2, stmt3;
	ResultSet rs;
	String Driver = "";
	String url = "jdbc:mysql://localhost:3306/madang?&serverTimezone=Asia/Seoul&useSSL=false";
	String userid = "madang";
	String pwd = "madang";

	public CustomerModel() {
		// 생성자에서 DB Connection을 시켜줌.
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

	public ResultSet return_CampingCar_List() {
		// 맨 처음 뷰를 띄웠을때, 대여 가능한 캠핑카 리스트 전체를 불러오는 기능
		try {
			stmt = con.createStatement();
			String Query = "SELECT * FROM rentcar_list";
			rs = stmt.executeQuery(Query);
		} catch (Exception e1) {
			System.out.println("대여 가능 캠핑카 리스트 SQL 에러 in CustomerModel" + e1);
		}

		return rs;
	}

	public ResultSet return_CampingCar_List_By_CampingCarID(String Search_CampingCarID) {
		// 조건 검색 1, 뷰에서 캠핑카ID 라디오 버튼이 체크되었을 때, 이 메소드를 호출.
		try {
			stmt = con.createStatement();
			String Query = "SELECT * FROM rentcar_list WHERE rent_id='" + Search_CampingCarID + "'";
			rs = stmt.executeQuery(Query);
		} catch (Exception e1) {
			System.out.println("대여 가능 캠핑카 리스트 By 캠핑카ID SQL 에러 in CustomerModel" + e1);
		}

		return rs;
	}

	public ResultSet return_CampingCar_List_By_CampingCarName(String Search_CampingCarName) {
		// 조건 검색 2, 뷰에서 차명 라디오 버튼이 체크되었을 때, 이 메소드를 호출.
		try {
			stmt = con.createStatement();
			String Query = "SELECT * FROM rentcar_list WHERE cc_name='" + Search_CampingCarName + "'";
			rs = stmt.executeQuery(Query);
		} catch (Exception e1) {
			System.out.println("대여 가능 캠핑카 리스트 By 캠핑카 이름 SQL 에러 in CustomerModel" + e1);
		}

		return rs;
	}

	public ResultSet return_CampingCar_List_By_CampingCarSeats(String Search_CampingCarSeats) {
		// 조건 검색 3, 뷰에서 최소승차인원 라디오 버튼이 체크되었을 때, 이 메소드를 호출.
		try {
			stmt = con.createStatement();
			String Query = "SELECT * FROM rentcar_list WHERE cc_sits>='" + Search_CampingCarSeats + "'";
			rs = stmt.executeQuery(Query);
		} catch (Exception e1) {
			System.out.println("대여 가능 캠핑카 리스트 By 캠핑카 좌석 SQL 에러 in CustomerModel" + e1);
		}

		return rs;
	}

	public ResultSet return_CampingCar_List_By_CampingCarManufacture(String Search_CampingCarManufacture) {
		// 조건 검색 4, 뷰에서 제조회사 라디오 버튼이 체크되었을 때, 이 메소드를 호출.
		try {
			stmt = con.createStatement();
			String Query = "SELECT * FROM rentcar_list WHERE cc_manufacture='" + Search_CampingCarManufacture + "'";
			rs = stmt.executeQuery(Query);
		} catch (Exception e1) {
			System.out.println("대여 가능 캠핑카 리스트 By 캠핑카 제조회사 SQL 에러 in CustomerModel" + e1);
		}

		return rs;
	}

	public ResultSet return_CampingCar_List_By_CampingCarMileage(String Search_CampingCarMaileage) {
		// 조건 검색 5, 뷰에서 최대주행거리 라디오 버튼이 체크되었을 때, 이 메소드를 호출.
		try {
			stmt = con.createStatement();
			String Query = "SELECT * FROM rentcar_list WHERE cc_mileage<='" + Search_CampingCarMaileage + "'";
			rs = stmt.executeQuery(Query);
		} catch (Exception e1) {
			System.out.println("대여 가능 캠핑카 리스트 By 캠핑카 최대주행거리 SQL 에러 in CustomerModel" + e1);
		}

		return rs;
	}

	public ResultSet return_CampingCar_List_By_CampingCarPrice(String Search_CampingCarPrice) {
		// 조건 검색 6, 뷰에서 최대대여비용 라디오 버튼이 체크되었을 때, 이 메소드를 호출.
		try {
			stmt = con.createStatement();
			String Query = "SELECT * FROM rentcar_list WHERE cc_rent_price<='" + Search_CampingCarPrice + "'";
			rs = stmt.executeQuery(Query);
		} catch (Exception e1) {
			System.out.println("대여 가능 캠핑카 리스트 By 캠핑카 최대대여비용 SQL 에러 in CustomerModel" + e1);
		}

		return rs;
	}

	public ResultSet return_Rented_CampingCar_List() {
		// 대여 현황을 출력한다. 뷰에서 대여 현황은 ( 대여 번호 | 대여중인 캠핑카 ID | 대여중인 캠핑카 가격 ) 으로 되어있다.
		// Query를 실행한 결과를 받아오는 ResultSet을 리턴하는 것으로 일단 해놨으나, ResultSet을 가공해서 스트링으로 만들어
		// 리턴하는 방향으로 바꿀수도 있다.
		try {
			stmt = con.createStatement();
			String Query = "SELECT campingcar_id,cc_price FROM customer_rent_list";
			rs = stmt.executeQuery(Query);
		} catch (Exception e1) {
			System.out.println("대여 현황 리스트 SQL 에러 in CustomerModel" + e1);
		}

		return rs;
	}

	public void Rent_CampingCar(String CampingCar_ID, String Customer_License, String Rent_Start_Date,
			String Rent_Period, String Rent_End_Date, String Extra_Goods, String Extra_Goods_Price) {
		// 캠핑카를 대여하는 기능 -> 원하는 캠핑카를 대여했다 가정하고, 캠핑카 대여 현황 리스트에 Insert한다.
		// 인자로 7개의 String을 받는데, 이는 Insert Query에 삽입할 Values로 쓰인다.
		try {
			stmt = con.createStatement();
			String Select_Query = "SELECT cc_rent_price,camping_rent_company_id FROM rentcar_list where rent_id='"
					+ CampingCar_ID + "'";
			rs = stmt.executeQuery(Select_Query);

			// 단, 여기서 CampingCar_CompanyID와 CampingCar_Price는 함수 인자로 넘겨받지 않고 (뷰에서 입력받지 않고)
			// 캠핑카 테이블에서 캠핑카 아이디를 키로 하여 받아온다.
			String CampingCar_Price = "";
			String CampingCar_CompanyID = "";
			if (rs.next()) {
				CampingCar_Price = rs.getString(1);
				CampingCar_CompanyID = rs.getString(2);
			}

			// 대여 기능 수행 -> 대여 현황 리스트에 해당 캠핑카의 정보를 Insert.
			stmt2 = con.createStatement();
			String Insert_Query = "INSERT INTO customer_rent_list(start_date,rent_time,due_date,otherthing,others_price,campingcar_company_id,c_license_id,campingcar_id,cc_price)"
					+ " VALUES('" + Rent_Start_Date + "','" + Rent_Period + "','" + Rent_End_Date + "','" + Extra_Goods
					+ "','" + Extra_Goods_Price + "','" + CampingCar_CompanyID + "','" + Customer_License + "','"
					+ CampingCar_ID + "','" + CampingCar_Price + "')";
			int Check_Insert = stmt.executeUpdate(Insert_Query);
			// 대여 현황 리스트에 Insert가 제대로 되었다면, 대여 가능 리스트에서 해당 차의 행을 지워준다.
			if (Check_Insert == 1) {
				try {
					stmt3 = con.createStatement();
					String Delete_Query = "DELETE FROM customer_rent_list WHERE rent_id = '" + CampingCar_CompanyID
							+ "'";
					stmt.executeUpdate(Delete_Query);
				} catch (Exception e2) {
					System.out.println(e2);
				}
			}

		} catch (Exception e1) {
			System.out.println("캠핑카 대여 SQL 에러 in CustomerModel" + e1);
		}
	}

	// -----------------테스트 영역 (후에 삭제예정)---------------------
	public static void main(String[] args) {
		CustomerModel cm = new CustomerModel();
		ResultSet rs;

		// return_CapmingCar_List 테스트
		rs = cm.return_CampingCar_List();

		try {
			while (rs.next()) {
				String str = rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4)
						+ "\t" + rs.getString(5) + "\t" + rs.getString(6) + "\t" + rs.getString(7) + "\t"
						+ rs.getString(8) + "만원\t" + rs.getString(9) + "\t" + rs.getString(10) + "\n";

				// System.out.print(str);
			}
		} catch (Exception e1) {
			System.out.println(e1);
		}

		// return_Rented_CampingCar_List 테스트
		rs = cm.return_Rented_CampingCar_List();

		try {
			while (rs.next()) {
				String str = rs.getString(1) + "\t" + rs.getString(2) + "\n";

				// System.out.print(str);
			}
		} catch (Exception e1) {
			System.out.println(e1);
		}
		
		// Rent_CampingCar 테스트 -> 되는것으로 확인됨.
		//cm.Rent_CampingCar("1", "1023454", "2020-11-06", "3", "2020-11-09", "tissue", "1000");
		
		// 조건 검색 1~6 테스트.
		

	}

}
