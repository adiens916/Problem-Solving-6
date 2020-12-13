package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import model.dataClass.AdminDataClass;
import model.dataClass.CampingCarDataClass;
import model.dataClass.GarageDataClass;
//import view.AdminView;
import model.dataClass.ResultStateDataClass;

public class AdminModel {

	// SQL 연결
	private final Connection connection = DatabaseConnector.getConnection();

	Statement stmt;
	ResultSet rs;
	int result, result1;

	public ArrayList<AdminDataClass> getCampingCarReturnList() {
		ArrayList<AdminDataClass> adminList = new ArrayList<>();
		try {
			stmt = connection.createStatement();
			String query = "SELECT * FROM campingcar_return;";
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				AdminDataClass admin = toCampingCarReturnFromResultSet(rs);
				adminList.add(admin);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			return null;
		}
		return adminList;
	}

	private AdminDataClass toCampingCarReturnFromResultSet(ResultSet result) throws Exception {
		AdminDataClass adminList = new AdminDataClass();
		adminList.front = result.getString(1);
		adminList.right = result.getString(2);
		adminList.left = result.getString(3);
		adminList.back = result.getString(4);
		adminList.repairListFixDate = result.getString(5);
		adminList.campingcarId = result.getString(6);
		adminList.rentId = result.getString(7);
		return adminList;
	}

	public ArrayList<GarageDataClass> getGarageList() {
		try {
			stmt = connection.createStatement();
			String query2 = " select * from garage"; /* SQL 문 */
			rs = stmt.executeQuery(query2);

			ArrayList<GarageDataClass> garageList = new ArrayList<>();
			while (rs.next()) {
				GarageDataClass garage = new GarageDataClass();
				garage.id = Integer.toString(rs.getInt(1));
				garage.name = rs.getString(2);
				garage.address = rs.getString(3);
				garage.number = rs.getString(4);
				garage.manager = rs.getString(5);
				garage.emailAddress = rs.getString(6);
				garageList.add(garage);
			}
			return garageList;
		} catch (Exception e1) {
			System.out.println(e1);
			return null;
		}
	}

	public ResultStateDataClass insertToGarage(AdminDataClass adminData) {
		String fixTest = null;
		String rentId = null;
		String lisenceId = null;
		String companyId = null;
		
		try {
			stmt = connection.createStatement();
			String query = "select fix, custom_rent_list_id from campingcar_return where campingcar_list_id='"
					+ adminData.torepair + "'";
			rs = stmt.executeQuery(query);
			if (rs.next()) {
				fixTest = rs.getString(1);
				rentId = rs.getString(2);
			}
			System.out.println("확인: "+fixTest);
			stmt = connection.createStatement();
			query = "select c_license_id,campingcar_company_id from customer_rent_old_list where rent_id='" + rentId
					+ "';";
			rs = stmt.executeQuery(query);
			if (rs.next()) {
				lisenceId = rs.getString(1);
				companyId = rs.getString(2);
			}

			if (adminData.isNull()) {// 모든 칸이 입력이 되지 않았을 때
				return ResultStateDataClass.NULL;
			} else if (fixTest.equals("1")) { // 수리할때
				stmt = connection.createStatement();
				query = "insert into repair_list(r_log,r_date,r_price,r_due_date,r_other_repair,customer_license_id,campingcar_rent_company_id,garage_id,campingcar_list_id) "
						+ "values('" + adminData.repairListLog + "','" + adminData.repairListFixDate + "','"
						+ adminData.repairListprice + "','" + adminData.repairListDuedate + "','"
						+ adminData.repairListOtherInfo + "','" + lisenceId + "','" + companyId + "','"
						+ adminData.garageId + "','" + adminData.torepair + "');";
				result = stmt.executeUpdate(query);
				stmt = connection.createStatement();
				query = "DELETE FROM campingcar_return WHERE campingcar_list_id = '" + adminData.torepair + "'";
				result1 = stmt.executeUpdate(query);
				if (result == 1 && result1 == 1) {
					getCampingCarReturnList();
					return ResultStateDataClass.SUCCESS;
				}
			} else if (fixTest.equals("0")) {// 수리필요없을 때
				return ResultStateDataClass.BACK;
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			return ResultStateDataClass.FAILURE;
		}
		return ResultStateDataClass.FAILURE;
	}

	/*
	 * 정비소로 보내지 않고 반환하기 - 먼저 수리여부 변수가 1인지 0인지 판단(0이면 반환) - 반환을 하면 다른 사람이 사용할 수 있도록
	 * rent_list테이블에 반환하는 캠핑카의 정보가 들어가 있어야한다. - 입력받은 정보로부터 repair_list가 아닌
	 * rentcar_list에 insert해준다.
	 */
	public ResultStateDataClass returnToCampingcarList(String cpcid) {
		try {
			stmt = connection.createStatement();
			String fixtest = null;
			String query = "select fix from campingcar_return where campingcar_list_id='" + cpcid + "'";
			rs = stmt.executeQuery(query);
			if (rs.next()) {
				fixtest = rs.getString(1);
			}

			query = "select * from campingcar_list where campingcar_list_id='" + cpcid + "'";
			rs = stmt.executeQuery(query);

			AdminDataClass admin = new AdminDataClass();
			if (rs.next()) {
				admin.campingCarName = rs.getString(2);
				admin.campingCarNumber = rs.getString(3);
				admin.campingCarSits = rs.getString(4);
				admin.campingCarManufacutre = rs.getString(5);
				admin.campingCarManufactureYear = rs.getString(6);
				admin.campingCarMileage = rs.getString(7);
				admin.campingCarRentprice = rs.getString(8);
				admin.campingCarRegitstdate = rs.getString(9);
				admin.campingCarRentCompanyId = rs.getString(10);
			}

			if (fixtest.equals("1")) {
				return ResultStateDataClass.BACK;
			} else if (fixtest.equals("0")) {// 반환하기!
				query = "insert into rentcar_list values" + "('" + cpcid + "','" + admin.campingCarName + "','"
						+ admin.campingCarNumber + "','" + admin.campingCarSits + "','" + admin.campingCarManufacutre
						+ "','" + admin.campingCarManufactureYear + "','" + admin.campingCarMileage + "','"
						+ admin.campingCarRentprice + "','" + admin.campingCarRegitstdate + "','"
						+ admin.campingCarRentCompanyId + "');";
				result = stmt.executeUpdate(query);
				query = "DELETE FROM campingcar_return WHERE campingcar_list_id = '" + cpcid + "'";
				result = stmt.executeUpdate(query);
				// getCampingCarReturnList();
				return ResultStateDataClass.SUCCESS;
			}
		} catch (Exception e1) {
			if (cpcid.length() == 0) {
				return ResultStateDataClass.NULL;
			}
		}
		return ResultStateDataClass.FAILURE;
	}

	public ArrayList<String> getSearch(int num) {
		ArrayList<String> search = new ArrayList<>();
		try {
			stmt = connection.createStatement();
			String query[] = new String[5];
			query[1] = "select c_name from (select r_price,customer_license_id from repair_list where r_price >=10) rp, customer cs where cs.license_id=rp.customer_license_id group by cs.c_name;";
			query[2] = "select c_name FROM (SELECT cc_price,c_license_id FROM customer_rent_list WHERE  cc_price >= 50) rl, customer cs WHERE cs.license_id=rl.c_license_id GROUP BY cs.c_name;";
			query[3] = "select g_name from (select cc_manufacture, campingcar_list_id from campingcar_list where cc_manufacture_year >=2000) cl, garage g where g.garage_id = cl.campingcar_list_id group by g.g_name;";
			query[4] = "select c_name FROM (SELECT rent_time, c_license_id FROM customer_rent_list WHERE rent_time>=10) rl, customer cs WHERE cs.license_id=rl.c_license_id GROUP BY cs.c_name;";
			rs = stmt.executeQuery(query[num]);
			int count = 1;
			while (rs.next()) {
				String str = count + ". " + rs.getString(1) + "\t";
				search.add(str);
				count++;
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return search;
	}

}
