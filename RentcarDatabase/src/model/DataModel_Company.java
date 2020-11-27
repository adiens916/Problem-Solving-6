package model;

import controller.dataClass.CampingCarInfo;
import controller.dataClass.CompanyInfo;
import controller.dataClass.ResultState;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class CompanyModel {
	// DB 연결 -> DatabaseConnector를 통해 연결
	private final Connection connection = DatabaseConnector.connection;
	private Statement statement;
	private ResultSet resultSet;
	
	// 전체 캠핑카 회사 리스트 출력 Read
	public ArrayList<CompanyInfo> readCompanyList() {
		// 뷰에서 캠핑카 회사 리스트를 반환해주는 기능
		ArrayList<CompanyInfo> companyList = new ArrayList<>();

		try {
			statement = connection.createStatement();
			String Query = "SELECT * FROM campingcar_rent_company";
			resultSet = statement.executeQuery(Query);
			// 캠핑카 회사의 리스트 ResultSet으로 받아온 이후에 ArrayList<String>으로 가공
			while (resultSet.next()) {
				CompanyInfo company = toCompanyFromResultSet(resultSet);
				companyList.add(company);
			}
		} catch (Exception e1) {
			System.out.println("캠핑카 회사 목록 불러오기 에러 in DataModel_Company" + e1);
		}
		// 캠핑카 회사 리스트 정보를 담은 ArrayList를 반환
		return companyList;
	}

	/*public ArrayList<CompanyInfo> refreshCompanyList() {
		// 뷰에서 새로고침 기능
		// Company_List를 다시 한번 실행해주기 때문에 마찬가지로 ArrayList를 반환
		ArrayList<String> Company_List = new ArrayList<>();
		Company_List = Company_List();

		return Company_List;
	}*/
	
	// ID를 통해 캠핑카 회사 검색
	public CompanyInfo readCompany(String companyID) {
		CompanyInfo company = new CompanyInfo();
		
		try {
			statement = connection.createStatement();
			String Query = "SELECT * FROM campingcar_rent_company WHERE camping_rent_company_id='" + companyID + "'";
			resultSet = statement.executeQuery(Query);

			if (resultSet.next()) {
				company = toCompanyFromResultSet(resultSet);
			}
		} catch (Exception e1) {
			System.out.println("캠핑카 회사 정보 검색 에러 in DataModel_Company" + e1);
		}

		return company;
	}

	// 캠핑카 데이터 입력 Insert
	public ResultState insertCompany(CompanyInfo company) {
		if(company.isNull()) {
			return ResultState.NULL;
		}
		
		try {
			statement = connection.createStatement();
			String Query = "INSERT INTO campingcar_rent_company(cp_name,cp_address,cp_number,cp_mng_email,cp_mng_name) VALUES('"
					+ company.companyName + "','" + company.companyAddress + "','" + company.companyPhoneNumber + "','"
					+ company.companyOfficerEmail + "','" + company.companyOfficerName + "')";
			int rowCount = statement.executeUpdate(Query);
			if (rowCount!=0) {
				return ResultState.SUCCESS;
			}
			
		} catch (Exception e1) {			
			System.out.println("캠핑카 회사 데이터 입력 에러 in DataModel_Company" + e1);
		}

		return ResultState.FAILURE;
	}
	
	// 캠핑카 데이터 수정 Update
	public ResultState updateCompany(CompanyInfo company) {
		if(company.isNull()) {
			return ResultState.NULL;
		}

		try {
			statement = connection.createStatement();
			String Query = "update campingcar_rent_company set cp_name='" + company.companyName + "'" + ",cp_address='"
					+ company.companyAddress + "',cp_number='" + company.companyPhoneNumber + "'" + ",cp_mng_name='"
					+ company.companyOfficerName + "',cp_mng_email='" + company.companyOfficerEmail + "'"
					+ " where camping_rent_company_id='" + company.companyID + "'";
			int rowCount = statement.executeUpdate(Query);
			if(rowCount!=0) {
				return ResultState.SUCCESS;
			}
			
		} catch (Exception e1) {
			System.out.println("캠핑카 회사 데이터 수정 에러 in DataModel_Company");
		}

		return ResultState.FAILURE;
	}
	
	// 캠핑카 데이터 삭제 Delete
	public ResultState deleteCompany(String companyID) {
		if(companyID.length()==0) {
			return ResultState.NULL;
		}
		
		try {
			statement = connection.createStatement();
			String Query = "DELETE FROM campingcar_rent_company WHERE camping_rent_company_id = '" + companyID + "'";
			int rowCount = statement.executeUpdate(Query);
			if(rowCount!=0) {
				return ResultState.SUCCESS;
			}
		} catch (Exception e1) {
			System.out.println("캠핑카 회사 데이터 삭제 에러 in DataModel_Company");
		}

		return ResultState.FAILURE;
	}
	
	/* [모델] ResultSet을 캠핑카 회사 데이터 클래스 형태로 바꿔주는 기능 */
	private CompanyInfo toCompanyFromResultSet(ResultSet result) throws Exception {
		CompanyInfo company = new CompanyInfo();
		company.companyID = Integer.toString(result.getInt(1));
		company.companyName = result.getString(2);
		company.companyAddress = result.getString(3);
		company.companyPhoneNumber = result.getString(4);
		company.companyOfficerEmail = result.getString(5);
		company.companyOfficerName = result.getString(6);
		return company;
	}
}
