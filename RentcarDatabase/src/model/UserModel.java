package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import controller.dataClass.CampingCarInfo;
import controller.dataClass.RentInfo;
import controller.dataClass.ResultState;

public class UserModel {
	// DB 연결 -> DatabaseConnector를 통해 연결
	private final Connection connection = DatabaseConnector.getConnection();
	private Statement statement;
	private ResultSet resultSet;

	public ArrayList<CampingCarInfo> readRentableCampingCarList() {
		// 맨 처음 뷰를 띄웠을때, 대여 가능한 캠핑카 리스트 전체를 불러오는 기능
		ArrayList<CampingCarInfo> rentableCampingCarList = new ArrayList<>();

		try {
			statement = connection.createStatement();
			String Query = "SELECT * FROM rentcar_list";
			resultSet = statement.executeQuery(Query);

			while (resultSet.next()) {
				CampingCarInfo rentableCampingCar = toCampingCarFromResultSet(resultSet);
				rentableCampingCarList.add(rentableCampingCar);
			}

		} catch (Exception e1) {
			System.out.println("대여 가능 캠핑카 리스트 SQL 에러 in CustomerModel" + e1);
		}

		return rentableCampingCarList;
	}

	public ArrayList<CampingCarInfo> readRentableCampingCarListByID(String searchCampingCarID) {
		// 조건 검색 1, 뷰에서 캠핑카ID 라디오 버튼이 체크되었을 때, 이 메소드를 호출.
		ArrayList<CampingCarInfo> rentableCampingCarList = new ArrayList<>();

		try {
			statement = connection.createStatement();
			String Query = "SELECT * FROM rentcar_list WHERE rent_id='" + searchCampingCarID + "'";
			resultSet = statement.executeQuery(Query);

			while (resultSet.next()) {
				CampingCarInfo rentableCampingCar = toCampingCarFromResultSet(resultSet);
				rentableCampingCarList.add(rentableCampingCar);
			}

		} catch (Exception e1) {
			System.out.println("대여 가능 캠핑카 리스트 By 캠핑카ID SQL 에러 in CustomerModel" + e1);
		}

		return rentableCampingCarList;
	}

	public ArrayList<CampingCarInfo> readRentableCampingCarListByName(String searchCampingCarName) {
		// 조건 검색 2, 뷰에서 차명 라디오 버튼이 체크되었을 때, 이 메소드를 호출.
		ArrayList<CampingCarInfo> rentableCampingCarList = new ArrayList<>();

		try {
			statement = connection.createStatement();
			String Query = "SELECT * FROM rentcar_list WHERE cc_name='" + searchCampingCarName + "'";
			resultSet = statement.executeQuery(Query);

			while (resultSet.next()) {
				CampingCarInfo rentableCampingCar = toCampingCarFromResultSet(resultSet);
				rentableCampingCarList.add(rentableCampingCar);
			}

		} catch (Exception e1) {
			System.out.println("대여 가능 캠핑카 리스트 By 캠핑카 이름 SQL 에러 in CustomerModel" + e1);
		}

		return rentableCampingCarList;
	}

	public ArrayList<CampingCarInfo> readRentableCampingCarListBySeats(String searchCampingCarSeat) {
		// 조건 검색 3, 뷰에서 최소승차인원 라디오 버튼이 체크되었을 때, 이 메소드를 호출.
		ArrayList<CampingCarInfo> rentableCampingCarList = new ArrayList<>();

		try {
			statement = connection.createStatement();
			String Query = "SELECT * FROM rentcar_list WHERE cc_sits>='" + searchCampingCarSeat + "'";
			resultSet = statement.executeQuery(Query);

			while (resultSet.next()) {
				CampingCarInfo rentableCampingCar = toCampingCarFromResultSet(resultSet);
				rentableCampingCarList.add(rentableCampingCar);
			}

		} catch (Exception e1) {
			System.out.println("대여 가능 캠핑카 리스트 By 캠핑카 좌석 SQL 에러 in CustomerModel" + e1);
		}

		return rentableCampingCarList;
	}

	public ArrayList<CampingCarInfo> readRentableCampingCarListByManufacture(String searchCampingCarManufacture) {
		// 조건 검색 4, 뷰에서 제조회사 라디오 버튼이 체크되었을 때, 이 메소드를 호출.
		ArrayList<CampingCarInfo> rentableCampingCarList = new ArrayList<>();

		try {
			statement = connection.createStatement();
			String Query = "SELECT * FROM rentcar_list WHERE cc_manufacture='" + searchCampingCarManufacture + "'";
			resultSet = statement.executeQuery(Query);

			while (resultSet.next()) {
				CampingCarInfo rentableCampingCar = toCampingCarFromResultSet(resultSet);
				rentableCampingCarList.add(rentableCampingCar);
			}

		} catch (Exception e1) {
			System.out.println("대여 가능 캠핑카 리스트 By 캠핑카 제조회사 SQL 에러 in CustomerModel" + e1);
		}

		return rentableCampingCarList;
	}

	public ArrayList<CampingCarInfo> readRentableCampingCarListByMileage(String searchCampingCarMileage) {
		// 조건 검색 5, 뷰에서 최대주행거리 라디오 버튼이 체크되었을 때, 이 메소드를 호출.
		ArrayList<CampingCarInfo> rentableCampingCarList = new ArrayList<>();

		try {
			statement = connection.createStatement();
			String Query = "SELECT * FROM rentcar_list WHERE cc_mileage<='" + searchCampingCarMileage + "'";
			resultSet = statement.executeQuery(Query);

			while (resultSet.next()) {
				CampingCarInfo rentableCampingCar = toCampingCarFromResultSet(resultSet);
				rentableCampingCarList.add(rentableCampingCar);
			}

		} catch (Exception e1) {
			System.out.println("대여 가능 캠핑카 리스트 By 캠핑카 최대주행거리 SQL 에러 in CustomerModel" + e1);
		}

		return rentableCampingCarList;
	}

	public ArrayList<CampingCarInfo> readRentableCampingCarListByPrice(String searchCampingCarPrice) {
		// 조건 검색 6, 뷰에서 최대대여비용 라디오 버튼이 체크되었을 때, 이 메소드를 호출.
		ArrayList<CampingCarInfo> rentableCampingCarList = new ArrayList<>();

		try {
			statement = connection.createStatement();
			String Query = "SELECT * FROM rentcar_list WHERE cc_rent_price<='" + searchCampingCarPrice + "'";
			resultSet = statement.executeQuery(Query);

			while (resultSet.next()) {
				CampingCarInfo rentableCampingCar = toCampingCarFromResultSet(resultSet);
				rentableCampingCarList.add(rentableCampingCar);
			}

		} catch (Exception e1) {
			System.out.println("대여 가능 캠핑카 리스트 By 캠핑카 최대대여비용 SQL 에러 in CustomerModel" + e1);
		}

		return rentableCampingCarList;
	}

	public ArrayList<RentInfo> readRentList() {
		// 대여 현황을 출력한다. 뷰에서 대여 현황은 ( 대여 번호 | 대여중인 캠핑카 ID | 대여중인 캠핑카 가격 ) 으로 되어있다.
		// Query를 실행한 결과를 받아오는 ResultSet을 리턴하는 것으로 일단 해놨으나, ResultSet을 가공해서 스트링으로 만들어
		// 리턴하는 방향으로 바꿀수도 있다. -> ArrayList<String>을 반환하는 것으로 바꿈
		ArrayList<RentInfo> rentList = new ArrayList<>();

		try {
			statement = connection.createStatement();
			String Query = "SELECT * FROM customer_rent_list";
			resultSet = statement.executeQuery(Query);

			while (resultSet.next()) {
				RentInfo rent = toRentFromResultSet(resultSet);
				rentList.add(rent);
			}

		} catch (Exception e1) {
			System.out.println("대여 현황 리스트 SQL 에러 in CustomerModel" + e1);
		}

		return rentList;
	}

	public ResultState rentCampingCar(RentInfo rent) {
		// 캠핑카를 대여하는 기능 -> 원하는 캠핑카를 대여했다 가정하고, 캠핑카 대여 현황 리스트에 Insert한다.
		// 인자로 7개의 String을 받는데, 이는 Insert Query에 삽입할 Values로 쓰인다.
		// String CampingCar_ID, String Customer_License, String Rent_Start_Date,
		// String Rent_Period, String Rent_End_Date, String Extra_Goods, String
		// Extra_Goods_Price
		int rentCheck = 0;
		
		if (rent.isNull()) {
			System.out.println("대여할 캠핑카 정보를 모두다 입력해주세요.");
			return ResultState.NULL;
		} else {
			try {
				statement = connection.createStatement();
				String selectQuery = "SELECT cc_rent_price,camping_rent_company_id FROM rentcar_list where rent_id='"
						+ rent.campingCarID + "'";
				resultSet = statement.executeQuery(selectQuery);

				// 단, 여기서 CampingCar_CompanyID와 CampingCar_Price는 함수 인자로 넘겨받지 않고 (뷰에서 입력받지 않고)
				// 캠핑카 테이블에서 캠핑카 아이디를 키로 하여 받아온다.
				String campingCarPrice = "";
				String campingCarCompanyID = "";
				if (resultSet.next()) {
					campingCarPrice = resultSet.getString(1);
					campingCarCompanyID = resultSet.getString(2);
				}

				// 대여 기능 수행 -> 대여 현황 리스트에 해당 캠핑카의 정보를 Insert.
				try {
					statement = connection.createStatement();
					String insertQuery = "INSERT INTO customer_rent_list(start_date,rent_time,due_date,otherthing,others_price,campingcar_company_id,c_license_id,campingcar_id,cc_price)"
							+ " VALUES('" + rent.rentStartDate + "','" + rent.rentPeriod + "','" + rent.rentEndDate + "','"
							+ rent.extraGoods + "','" + rent.extraGoodsPrice + "','" + campingCarCompanyID + "','"
							+ rent.license + "','" + rent.campingCarID + "','" + campingCarPrice + "')";
					int checkInsert = statement.executeUpdate(insertQuery);
					// 대여 현황 리스트에 Insert가 제대로 되었다면, 대여 가능 리스트에서 해당 차의 행을 지워준다.
					if (checkInsert == 1) {
						try {
							statement = connection.createStatement();
							String deleteQuery = "DELETE FROM rentcar_list WHERE rent_id = '" + rent.campingCarID + "'";
							int checkDelete = statement.executeUpdate(deleteQuery);
							// 마지막 Delete까지 완료되었으면, Rent_Check를 1로 바꿔줌.
							if (checkDelete == 1)
								return ResultState.SUCCESS;
						} catch (Exception e3) {
							System.out.println("캠핑카 대여 SQL 에러 (DELETE) in CustomerModel" + e3);
						}
					}
				} catch (Exception e2) {
					System.out.println("캠핑카 대여 SQL 에러 (INSERT) in CustomerModel" + e2);
				}

			} catch (Exception e1) {
				System.out.println("캠핑카 대여 SQL 에러 (SELECT) in CustomerModel" + e1);
			}
		}

		return ResultState.FAILURE;
	}

	/* [모델] ResultSet을 캠핑카 데이터 클래스 형태로 바꿔주는 기능 */
	private CampingCarInfo toCampingCarFromResultSet(ResultSet result) throws Exception {
		CampingCarInfo campingCar = new CampingCarInfo();
		campingCar.id = Integer.toString(result.getInt(1));
		campingCar.name = result.getString(2);
		campingCar.number = result.getString(3);
		campingCar.seats = result.getString(4);
		campingCar.manufacturer = result.getString(5);
		campingCar.builtDate = result.getString(6);
		campingCar.mileage = result.getString(7);
		campingCar.rentalFee = result.getString(8);
		campingCar.registryDate = result.getString(9);
		campingCar.companyId = result.getString(10);
		return campingCar;
	}

	private RentInfo toRentFromResultSet(ResultSet result) throws Exception {
		RentInfo rent = new RentInfo();

//		rent.rentID = result.getString(1);
		rent.campingCarID = result.getString(9);
//		rent.campingCarCompanyID = result.getString(7);
//		rent.rentPrice = result.getString(10);
		rent.license = result.getString(8);
		rent.rentStartDate = result.getString(2);
		rent.rentPeriod = result.getString(3);
		rent.rentEndDate = result.getString(4);
		rent.extraGoods = result.getString(5);
		rent.extraGoodsPrice = result.getString(6);

		return rent;
	}
	
	// ----- 테스트 영역 -----	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
