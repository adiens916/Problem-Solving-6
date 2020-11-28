package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import view.AdminView;
import controller.dataClass.AdminInfo;
import controller.dataClass.CampingCarInfo;
import controller.dataClass.Garage;

public class AdminModel {
	
	// SQL 연결
    private final Connection con = DatabaseConnector.getConnection();
	
	 Statement stmt;
	 ResultSet rs;
	 int result, result1;
	 public ArrayList<CampingCarInfo> getCampingCarList() {
	        ArrayList<CampingCarInfo> campingCarList = new ArrayList<>();
	        try {
	            stmt = con.createStatement();
	            String query = " select * from campingcar_list"; /* SQL 문 */
	            rs = stmt.executeQuery(query);
	            while (rs.next()) {
	                CampingCarInfo campingCar = toCampingCarFromResultSet(rs);
	                campingCarList.add(campingCar);
	            }
	        } catch(Exception e1) {
	            System.out.println(e1);
	        }
	        return campingCarList;
	    }
	 
	 private CampingCarInfo toCampingCarFromResultSet(ResultSet result) {
	        CampingCarInfo campingCar = new CampingCarInfo();
	        try {
	            campingCar.campingCarId = Integer.toString(result.getInt(1));
	            campingCar.campingCarName = result.getString(2);
	            campingCar.campingCarNumber = result.getString(3);
	            campingCar.campingCarSits = result.getString(4);
	            campingCar.campingCarManufacutre = result.getString(5);
	            campingCar.campingCarManufactureYear = result.getString(6);
	            campingCar.campingCarMileage = result.getString(7);
	            campingCar.campingCarRentprice = result.getString(8);
	            campingCar.campingCarRegitstdate = result.getString(9);
	            campingCar.campingCarRentCompanyId = result.getString(10);
	        } catch(Exception e1) {
	            System.out.println(e1);
	        }
	        return campingCar;
	    }
	 
	 
	 public ArrayList<Garage> getGarageList() {
	        try {
	            stmt = con.createStatement();
	            String query2=" select * from garage"; /* SQL 문 */
	            rs = stmt.executeQuery(query2);

	            ArrayList<Garage> garageList = new ArrayList<>();
	            while(rs.next()) {
	            	Garage garage = new Garage();
	                garage.id = Integer.toString(rs.getInt(1));
	                garage.name = rs.getString(2);
	                garage.address = rs.getString(3);
	                garage.number = rs.getString(4);
	                garage.manager = rs.getString(5);
	                garage.emailAddress = rs.getString(6);
	                garageList.add(garage);
	            }
	            return garageList;
	        }catch(Exception e1) {
	            System.out.println(e1);
	            return null;
	        }
	    }
	
	//입력한 데이터를 공백으로 만들기 위한 메소드
	 public void dataRefresh(AdminView adminViewData) {
		adminViewData.torepairId.setText("");
		adminViewData.garageId.setText("");
		adminViewData.repairListLog.setText("");
		adminViewData.repairListFixDate.setText("");
		adminViewData.repairListprice.setText("");
		adminViewData.repairListDuedate.setText("");
		adminViewData.repairListOtherInfo.setText("");
	}
	
	
	public int insetToGarage(ArrayList<AdminInfo> adminData) {
		String fixTest = null;
		String rentId=null;
		String lisenceId = null;
		String companyId = null;
		
		//View로부터 입력받은 데이터값 받아오기
		AdminInfo admin = new AdminInfo();
		for(AdminInfo i : adminData) {
			admin.garageId = i.garageId;
			admin.torepair = i.torepair;
       	 	admin.repairListDuedate = i.repairListDuedate;
       	 	admin.repairListFixDate = i.repairListFixDate;
       	 	admin.repairListLog = i.repairListLog;
       	 	admin.repairListOtherInfo = i.repairListOtherInfo;
       	 	admin.repairListprice = i.repairListprice;
		}
		
		try {
			stmt = con.createStatement();
			String query = "select fix, custom_rent_list_id from campingcar_return where campingcar_list_id='"+admin.torepair+"'";
			rs = stmt.executeQuery(query);
			if(rs.next()) {
				fixTest = rs.getString(1);
				rentId = rs.getString(2);
			}
			stmt = con.createStatement();
			query = "select c_license_id,campingcar_company_id from customer_rent_old_list where rent_id='"+rentId+"';";
			rs = stmt.executeQuery(query);
			if(rs.next()) {
				lisenceId = rs.getString(1);
				companyId = rs.getString(2);
			}
			
			if(admin.isNull(adminData)) {//모든 칸이 입력이 되지 않았을 때
				return 2;
			}else if(fixTest.equals("1")) { //수리할때
				stmt = con.createStatement();
				query = "insert into repair_list(r_log,r_date,r_price,r_due_date,r_other_repair,customer_license_id,campingcar_rent_company_id,garage_id,campingcar_list_id) "
						+ "values('"+admin.repairListLog
						+"','"+admin.repairListFixDate
						+"','"+admin.repairListprice
						+"','"+admin.repairListDuedate
						+"','"+admin.repairListOtherInfo
						+"','"+lisenceId
						+"','"+companyId
						+"','"+admin.garageId
						+"','"+admin.torepair
						+"');";
				result = stmt.executeUpdate(query);
				stmt = con.createStatement();
				query="DELETE FROM campingcar_return WHERE campingcar_list_id = '"+admin.torepair+"'";
				result1 = stmt.executeUpdate(query);
				if(result == 1 && result1==1) {
					getCampingCarList();
					return 1;
				}
			}else if(fixTest.equals("0")) {//수리필요없을 때
				return 0;
			}
		}catch(Exception e1) {
			return 0;
		}
		return 0;
	}
	
	/*
	 * 정비소로 보내지 않고 반환하기
	 * - 먼저 수리여부 변수가 1인지 0인지 판단(0이면 반환)
	 * - 반환을 하면 다른 사람이 사용할 수 있도록 rent_list테이블에 반환하는 캠핑카의 정보가 들어가 있어야한다.
	 * - 입력받은 정보로부터 repair_list가 아닌 rentcar_list에 insert해준다.
	 */
	public int returnToCampingcarList(String cpcid) {
		try {
		stmt = con.createStatement();
		String fixtest = null;
		String query = "select fix from campingcar_return where campingcar_list_id='"+cpcid+"'";
		rs = stmt.executeQuery(query);
		if(rs.next()) {fixtest = rs.getString(1);}		
		
		
		query="select * from campingcar_list where campingcar_list_id='"+cpcid+"'";
		rs = stmt.executeQuery(query);
		
		AdminInfo admin = new AdminInfo();
         if(rs.next()) {
        	 admin.campingCarName = rs.getString(2);
        	 admin.campingCarNumber =rs.getString(3);
        	 admin.campingCarSits =rs.getString(4);
        	 admin.campingCarManufacutre =rs.getString(5);
        	 admin.campingCarManufactureYear =rs.getString(6);
        	 admin.campingCarMileage =rs.getString(7);
        	 admin.campingCarRentprice =rs.getString(8);
        	 admin.campingCarRegitstdate =rs.getString(9);
        	 admin.campingCarRentCompanyId =rs.getString(10);
         }
         
        	  if(fixtest.equals("1")) {
        		  return 1;
        	  }else if(fixtest.equals("0")) {//반환하기!
        		  query="insert into rentcar_list values"
        				  + "('"+cpcid+"','"
        				  +admin.campingCarName+
        				  "','"+admin.campingCarNumber
        				  +"','"+admin.campingCarSits
        				  +"','"+admin.campingCarManufacutre
        				  +"','"+admin.campingCarManufactureYear
        				  +"','"+admin.campingCarMileage
        				  +"','"+admin.campingCarRentprice
        				  +"','"+admin.campingCarRegitstdate
        				  +"','"+admin.campingCarRentCompanyId+"');";
  	 			result = stmt.executeUpdate(query);
  				query="DELETE FROM campingcar_return WHERE campingcar_list_id = '"+cpcid+"'";
  				result = stmt.executeUpdate(query);
  				//getCampingCarReturnList();
  				return 2;
        	  }
		}catch(Exception e1) {
			if(cpcid.length()==0) {
				return 3;
			}
		}
		return 0;
	}
	
	public ArrayList<String> getSearch(int num){
		 ArrayList<String> search = new ArrayList<>();
		 try {
			stmt = con.createStatement();
			String query[] = new String[5];
			query[1]="select c_name from (select r_price,customer_license_id from repair_list where r_price >=10) rp, customer cs where cs.license_id=rp.customer_license_id group by cs.c_name;";
			query[2]="select c_name FROM (SELECT cc_price,c_license_id FROM customer_rent_list WHERE  cc_price >= 50) rl, customer cs WHERE cs.license_id=rl.c_license_id GROUP BY cs.c_name;";
			query[3]="select c_name FROM (SELECT cc_price,c_license_id FROM customer_rent_list WHERE  cc_price >= 50) rl, customer cs WHERE cs.license_id=rl.c_license_id GROUP BY cs.c_name;";
			query[4]="select g_name from (select cc_manufacture, campingcar_list_id from campingcar_list where cc_manufacture_year >=2000) cl, garage g where g.garage_id = cl.campingcar_list_id group by g.g_name;";
			rs = stmt.executeQuery(query[num]);
			int count=1;
            while(rs.next()) {
              String str = count+". " + rs.getString(1) + "\t";
              search.add(str);
              count++;
            }
		}catch(Exception e1) {
			e1.printStackTrace();
		}
		return search;
	 }
	
}
