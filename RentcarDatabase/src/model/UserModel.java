package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import model.dataClass.CampingCarDataClass;
import model.dataClass.RentDataClass;
import model.dataClass.ResultStateDataClass;

public class UserModel {
	// DB 연결 -> DatabaseConnector를 통해 연결
	private final Connection connection = DatabaseConnector.getConnection();
	private Statement statement;
	private ResultSet resultSet;
	
	// 읽기 쿼리 실행
	private ResultSet executeReadQuery(String Query) throws Exception {			
		statement = connection.createStatement();
		resultSet = statement.executeQuery(Query);
		
		return resultSet;
	}
	
	// 업데이트 쿼리 실행
	private int executeUpdateQuery(String Query) throws Exception {
		statement = connection.createStatement();
		
		return statement.executeUpdate(Query);
	}
	
	// 메소드 1 -> 대여 가능한 캠핑카 리스트 전체 출력
	public ArrayList<CampingCarDataClass> readRentableCampingCarList() {
		// 맨 처음 뷰를 띄웠을때, 대여 가능한 캠핑카 리스트 전체를 불러오는 기능		
		ArrayList<CampingCarDataClass> rentableCampingCarList = new ArrayList<>();
		
		try {
			resultSet = executeReadQuery("SELECT * FROM rentcar_list");
			rentableCampingCarList = toCampingCarFromResultSet(resultSet);
		} catch(Exception e) {
			System.out.println("대여 가능 캠핑카 리스트 SQL 에러 in CustomerModel" + e);
		}
		
		return rentableCampingCarList;
	}
	
	// 메소드 2 -> 대여 가능 캠핑카 리스트 조건 검색
	private HashMap<String,String> getReadQueryHash(String searchTerm){
		HashMap<String,String> readQueryHash = new HashMap<>();
		
		readQueryHash.put("캠핑카ID","SELECT * FROM rentcar_list WHERE rent_id='" + searchTerm + "'");
		readQueryHash.put("차명","SELECT * FROM rentcar_list WHERE cc_name='" + searchTerm + "'");
		readQueryHash.put("최소승차인원","SELECT * FROM rentcar_list WHERE cc_sits>='" + searchTerm + "'");
		readQueryHash.put("제조회사","SELECT * FROM rentcar_list WHERE cc_manufacture='" + searchTerm + "'");
		readQueryHash.put("최대주행거리","SELECT * FROM rentcar_list WHERE cc_mileage<='" + searchTerm + "'");
		readQueryHash.put("최대대여비용","SELECT * FROM rentcar_list WHERE cc_rent_price<='" + searchTerm + "'");
		
		return readQueryHash;
	}
	
	public ArrayList<CampingCarDataClass> readRentableCampingCarListBy(String checkedRadio, String searchTerm) {
		// 조건 검색 1~6, 뷰에서 조건 라디오가 체크 되었을 때, 이 메소드를 호출
		HashMap<String,String> readQueryHash = getReadQueryHash(searchTerm);
		ArrayList<CampingCarDataClass> rentableCampingCarList = new ArrayList<>();
		
		try {
			resultSet = executeReadQuery(readQueryHash.get(checkedRadio));
			rentableCampingCarList = toCampingCarFromResultSet(resultSet);
		} catch(Exception e) {
			System.out.println("대여 가능 캠핑카 리스트 조건 검색 오류" + e);
		}
		
		return rentableCampingCarList;		
	}
	
	// 메소드 3 -> 대여 현황 조회
	public ArrayList<RentDataClass> readRentList() {
		// 대여 현황을 출력한다. 뷰에서 대여 현황은 ( 대여 번호 | 대여중인 캠핑카 ID | 대여중인 캠핑카 가격 ) 으로 되어있다.
		ArrayList<RentDataClass> rentList = new ArrayList<>();
		
		try {
			resultSet = executeReadQuery("SELECT * FROM customer_rent_list");
			rentList = toRentFromResultSet(resultSet);
		} catch(Exception e) {
			System.out.println("대여 현황 리스트 SQL 에러 in CustomerModel" + e);
		}

		return rentList;
	}
	
	// 메소드 4-1 -> 대여 시 필요한 Value 얻기 (CampingCarPrice, CompanyID)
	private String[] getRentParams(RentDataClass rent) {
		String[] params = new String[2];
		
		try {
			resultSet = executeReadQuery("SELECT cc_rent_price,camping_rent_company_id FROM rentcar_list where rent_id='"
					+ rent.campingCarID + "'");			
			while (resultSet.next()) {
				params[0] = resultSet.getString(1);
				params[1] = resultSet.getString(2);
			}
		} catch(Exception e) {
			System.out.println("캠핑카 대여 SQL 에러 (SELECT) in CustomerModel" + e);
		}
		
		return params;		
	}
	
	// 메소드 4-2 -> 캠핑카 대여 및 성공 여부 반환
	private int rentCampingCar(RentDataClass rent, String[] params) {
		int rentCheck = 0;
		
		try {
			rentCheck = executeUpdateQuery("INSERT INTO customer_rent_list(start_date,rent_time,due_date,otherthing,others_price,campingcar_company_id,c_license_id,campingcar_id,cc_price)"
							+ " VALUES('" + rent.rentStartDate + "','" + rent.rentPeriod + "','" + rent.rentEndDate + "','"
							+ rent.extraGoods + "','" + rent.extraGoodsPrice + "','" + params[1] + "','"
							+ rent.license + "','" + rent.campingCarID + "','" + params[0] + "')");
		} catch(Exception e) {
			System.out.println("캠핑카 대여 SQL 에러 (INSERT) in CustomerModel" + e);
		}
		
		return rentCheck;
	}
	
	// 메소드 4-3 -> 캠핑카 대여가 성공했을 시 대여 가능 리스트에서 해당 캠핑카 삭제
	private int deleteCampingCarFromRentableList(RentDataClass rent, int rentCheck) {
		int deleteCheck = 0;
		
		if (rentCheck == 1) {
			try {
				deleteCheck = executeUpdateQuery("DELETE FROM rentcar_list WHERE rent_id = '" + rent.campingCarID + "'");
			} catch(Exception e) {
				System.out.println("캠핑카 대여 SQL 에러 (DELETE) in CustomerModel" + e);
			}
		}
		
		return deleteCheck;
	}
	
	// 메소드 4 -> 캠핑카 대여 과정 -> 메소드 4-1,4-2,4-3 실행
	public ResultStateDataClass rentCampingCarDriver(RentDataClass rent) {		
		// 대여 정보가 비어있을시 실패
		if (rent.isNull())			
			return ResultStateDataClass.NULL;
		
		String[] params = getRentParams(rent);
		int rentCheck = rentCampingCar(rent, params);
		int deleteCheck = deleteCampingCarFromRentableList(rent, rentCheck);
		
		// 대여 성공 및 대여 가능 리스트에서 삭제 둘다 성공 -> 성공
		if (rentCheck == 1 && deleteCheck == 1)
			return ResultStateDataClass.SUCCESS;
		
		// 아니면 실패
		return ResultStateDataClass.FAILURE;
	}

	// 쿼리 실행 결과 ResultSet을 데이터 클래스로 변환
	private ArrayList<CampingCarDataClass> toCampingCarFromResultSet(ResultSet result) throws Exception {
		ArrayList<CampingCarDataClass> rentableCampingCarList = new ArrayList<>();
		
		while(result.next()) {
			CampingCarDataClass campingCar = new CampingCarDataClass();
			campingCar.campingCarId =  Integer.toString(result.getInt(1));
			campingCar.campingCarName =  result.getString(2);
			campingCar.campingCarNumber =  result.getString(3);
			campingCar.campingCarSits =  result.getString(4);
			campingCar.campingCarManufacutre =  result.getString(5);
			campingCar.campingCarManufactureYear =  result.getString(6);
			campingCar.campingCarMileage =  result.getString(7);
			campingCar.campingCarRentprice =  result.getString(8);
			campingCar.campingCarRegitstdate =  result.getString(9);
			campingCar.campingCarRentCompanyId =  result.getString(10);
			rentableCampingCarList.add(campingCar);
		}
		
		return rentableCampingCarList;
	}

	private ArrayList<RentDataClass> toRentFromResultSet(ResultSet result) throws Exception {
		ArrayList<RentDataClass> rentList = new ArrayList<>();
		
		while(result.next()) {
			RentDataClass rent = new RentDataClass();
			rent.rentID = result.getString(1);
			rent.campingCarID = result.getString(9);
			rent.campingCarCompanyID = result.getString(7);
			rent.rentPrice = result.getString(10);
			rent.license = result.getString(8);
			rent.rentStartDate = result.getString(2);
			rent.rentPeriod = result.getString(3);
			rent.rentEndDate = result.getString(4);
			rent.extraGoods = result.getString(5);
			rent.extraGoodsPrice = result.getString(6);
			rentList.add(rent);
		}

		return rentList;
	}
}
