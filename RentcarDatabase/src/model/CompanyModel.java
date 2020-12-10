package model;

import model.dataClass.CompanyDataClass;
import java.sql.ResultSet;

public class CompanyModel extends AbstractModel<CompanyDataClass> {

	@Override
	String getCreateQuery(CompanyDataClass company) {
		return "INSERT INTO campingcar_rent_company(cp_name,cp_address,cp_number,cp_mng_email,cp_mng_name) VALUES('" +
				company.companyName + "','" +
				company.companyAddress + "','" +
				company.companyPhoneNumber + "','" +
				company.companyOfficerEmail + "','" +
				company.companyOfficerName + "')";
	}

	@Override
	String getReadAllQuery() {
		return "SELECT * FROM campingcar_rent_company";
	}

	@Override
	String getReadQuery(String id) {
		return "SELECT * FROM campingcar_rent_company WHERE camping_rent_company_id='" + id + "'";
	}

	@Override
	String getUpdateQuery(CompanyDataClass company) {
		return "update campingcar_rent_company set " +
				"cp_name='" + company.companyName + "'" +
				",cp_address='" + company.companyAddress +
				"',cp_number='" + company.companyPhoneNumber + "'" +
				",cp_mng_name='" + company.companyOfficerName +
				"',cp_mng_email='" + company.companyOfficerEmail + "'" +
				" where camping_rent_company_id='" + company.companyID + "'";
	}

	@Override
	String getDeleteQuery(String id) {
		return "DELETE FROM campingcar_rent_company WHERE camping_rent_company_id = '" + id + "'";
	}

	@Override
	boolean isNullData(CompanyDataClass company) {
		return company.isNull();
	}

	@Override
	CompanyDataClass toInfoFromResultSet(ResultSet resultSet) throws Exception {
		CompanyDataClass company = new CompanyDataClass();
		company.companyID = Integer.toString(resultSet.getInt(1));
		company.companyName = resultSet.getString(2);
		company.companyAddress = resultSet.getString(3);
		company.companyPhoneNumber = resultSet.getString(4);
		company.companyOfficerEmail = resultSet.getString(5);
		company.companyOfficerName = resultSet.getString(6);
		return company;
	}

	/*public ArrayList<CompanyInfo> refreshCompanyList() {
		// 뷰에서 새로고침 기능
		// Company_List를 다시 한번 실행해주기 때문에 마찬가지로 ArrayList를 반환
		ArrayList<String> Company_List = new ArrayList<>();
		Company_List = Company_List();

		return Company_List;
	}*/
}
