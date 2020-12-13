package model;

import model.dataClass.RentDataClass;
import model.dataClass.ResultStateDataClass;
import model.dataClass.ReturnDataClass;

import java.sql.*;

public class ReturnModel {

	private final Connection con = DatabaseConnector.getConnection();
	private Statement statement;

	private ReturnDataClass state;
	private RentDataClass rentalInfo;

	// 반환 버튼 클릭시 데이터베이스에 업데이트 해주는 메소드
	public ResultStateDataClass returnCar(ReturnDataClass state) {
		this.state = state;

		try {
			checkNullInput();
			getMatchedOneFromRentedList(); // 대여 정보 불러옴
			addToReturnList(); // 반환하는 것이므로 반환 내역에 현재 캠핑카 상태 등록
			deleteFromRentedList(); // 반환했으므로 현재 대여 중인 목록에선 제거
			moveToWaitingList(); // 반환했어도 아직 해야할 게 남아있으므로, 대여 목록에서 임시 리스트로 옮겨놓음

			return ResultStateDataClass.SUCCESS;
		} catch (NullInputException e) {
			return ResultStateDataClass.NULL;
		} catch (NoSuchRentalException e) {
			return ResultStateDataClass.NONEXISTENT;
		} catch (ExecutionFailureException e) {
			e.printStackTrace();
			return ResultStateDataClass.RETURN_FAILURE;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultStateDataClass.FAILURE;
		}
	}

	private void checkNullInput() throws NullInputException {
		// 사용자 입력 중 빈 값이 있으면, 예외로 NULL 있다고 알려줌
		if (state.isNull()) {
			throw new NullInputException();
		}
	}

	private void getMatchedOneFromRentedList() throws SQLException, NoSuchRentalException {
		// 캠핑카 ID와 일치하는 대여 정보 있는지 확인
		statement = con.createStatement();
		ResultSet resultSet = statement.executeQuery(getReadQueryFromRentalList());
		// 해당 대여 정보가 있으면 저장
		if (resultSet.next()) {
			rentalInfo = toRentalInfoFromResultSet(resultSet);
		// 없으면 사용자에게 알리기
		} else {
			throw new NoSuchRentalException();
		}
	}

	private void addToReturnList() throws SQLException, ExecutionFailureException {
		// 반환된 차량 상태 + 대여 정보를, 반환 내역에 넣기
		int result = statement.executeUpdate(getInsertQueryToReturnList());
		// 작업 실패 시 사용자에게 알리기
		if (result == 0) {
			throw new ExecutionFailureException();
		}
	}

	private void deleteFromRentedList() throws SQLException {
		// 현재 대여 목록에서 제거
		statement.executeUpdate(getDeleteQueryFromRentalList());
	}

	private void moveToWaitingList() throws SQLException {
		// 반환 후, 최종 판단 대기 중인 리스트에 넣기
		statement.executeUpdate(getInsertQueryToWaitingList());
	}

	private RentDataClass toRentalInfoFromResultSet(ResultSet resultSet) throws SQLException {
		RentDataClass rental = new RentDataClass();
		rental.rentID = resultSet.getString(1);
		rental.campingCarID = resultSet.getString(8);
		rental.campingCarCompanyID = resultSet.getString(7);
		rental.rentPrice = resultSet.getString(10);
		rental.license = resultSet.getString(9);
		rental.rentStartDate = resultSet.getString(2);
		rental.rentPeriod = resultSet.getString(3);
		rental.rentEndDate = resultSet.getString(4);
		rental.extraGoods = resultSet.getString(5);
		rental.extraGoodsPrice = resultSet.getString(6);
		return rental;
	}

	private String getReadQueryFromRentalList() {
		return " select * from customer_rent_list where campingcar_id='" + state.campingCarId + "'";
	}

	private String getInsertQueryToReturnList() {
		return "insert into campingcar_return values('" +
				state.front + "','" +
				state.right + "','" +
				state.left + "','" +
				state.back + "','" +
				state.fix + "','" +
				state.campingCarId + "','" +
				rentalInfo.rentID + "')";
	}

	private String getDeleteQueryFromRentalList() {
		return "DELETE FROM customer_rent_list WHERE rent_id = '" + rentalInfo.rentID + "';";
	}

	private String getInsertQueryToWaitingList() {
		return "insert into customer_rent_old_list values('" +
				rentalInfo.rentID + "','" +
				rentalInfo.rentStartDate + "','" +
				rentalInfo.rentPeriod + "','" +
				rentalInfo.rentEndDate + "','" +
				rentalInfo.extraGoods + "','" +
				rentalInfo.extraGoodsPrice + "','" +
				rentalInfo.campingCarCompanyID + "','" +
				rentalInfo.campingCarID + "','" +
				rentalInfo.license + "','" +
				rentalInfo.rentPrice + "')";
	}
}

class NullInputException extends Exception {}

class NoSuchRentalException extends Exception {}

class ExecutionFailureException extends Exception {}