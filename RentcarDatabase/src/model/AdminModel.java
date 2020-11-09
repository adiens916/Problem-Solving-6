package model;

import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import view.AdminView;
import view.dataClass.AdminInfo;

public class AdminModel {
	// ConnectToDatabase 클래스에서 데이터베이스와 연결해주는 메소드(conDB)를 가져온다.
	private Connection con = DatabaseConnector.connection;
	//AdminView에서 입력받은 데이터를 가져오기 위한 객체
	 AdminView adminInfo = new AdminView();
	 Statement stmt;
	 ResultSet rs;
	 //데이터베이스로부터 끌어온 데이터들을 출력해주기 위한 객체
	 StringBuilder results = new StringBuilder();
	 //데이터베이스에 Insert후 성공여부를 알기위한 변수
	 int result;


	 //관리자 페이지 오픈과 동시에 고객이 캠핑카를 반환할 때 캠핑카의 상태를 출력하기 위한 메소드
	 public String printCampincarreturn(){
		 try {
			results.append("앞쪽 \t 오른쪽 \t 왼쪽 \t 뒤쪽 \t 수리여부 \t 캠핑카ID \t 고유대여ID\n");
			stmt = con.createStatement();
			String query="SELECT * FROM campingcar_return;";
			rs = stmt.executeQuery(query);
             while(rs.next()) {
                String str = rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4)
                +  "\t" + rs.getString(5)+ "\t" + rs.getString(6)+"\t" + rs.getString(7)+"\n";
                results.append(str);
             }
		}catch(Exception e1) {
			System.out.println("에러 내용:"+e1);
		}
		return results.toString();
	}
	//AdminView에서 우측하단에 차고지현황에 대한 리스트를 출력하기 위한 메소드
	public String printGarageResult(){
		try {
			results.append("차고지ID \t 카센터이름 \t 주소 \t 번호 \t 매니저이름 \t 이메일주소\n");
			stmt = con.createStatement();
			String query="SELECT * FROM garage;";
			rs = stmt.executeQuery(query);
             while(rs.next()) {
               String str = rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4)
                +  "\t" + rs.getString(5)+ "\t" + rs.getString(6)+"\n";
               results.append(str);
             }
		}catch(Exception e1) {
			System.out.println(e1);
		}
		return results.toString();
	}
	//입력한 데이터를 초기화시켜주기 위한 메소드
	public void datareset() {
		/*컨트롤러에서 받아야되는 객체
		1. AdminView에서 정비소로 보내는 JTextField 객체 가져오기
		admin.rlog.setText("");
		rfixdate.setText("");
		rprice.setText("");
		rduedate.setText("");
		rotherinfo.setText("");
		grgid.setText("");
		torepair.setText("");
		*/
	}
	/*AdminView에서 입력받은 캠핑카ID, 정비소ID, 정비내역, 수리날짜, 수리비용, 납입기한, 기타내역정보을 "repair_list"테이블에 insert해주기 위한 메소드
	  - 입력받은 정보로부터 capingcar_return 테이블에서 수리여부 변수로 1이면 정비소로보내고 0이면 수리필요없다는 메세지를 띄워준다.
	  - 수리가 필요하다면 AdminView로부터 입력받은 정보들과 customer_rent_old_list테이블에서 받아온 운전면허번호화 캠핑카등록id를 repair_list에 insert해준다.
	*/
	public void InsetToGarage(AdminInfo admin) {
		/*컨트롤러에서 받아야되는 객체
		 * 1. "정비소로보내기"버튼 grgbtn 객체
		*/
		try {
			stmt = con.createStatement();
			String query = "select fix, custom_rent_list_id from campingcar_return where campingcar_list_id='"+admin.cpcid+"'";
			rs = stmt.executeQuery(query);
			if(rs.next()) {
				admin.fixtest = rs.getString(1);
				admin.rent_id = rs.getString(2);
			}


			stmt = con.createStatement();
			query = "select c_license_id,campingcar_company_id from customer_rent_old_list where rent_id='"+admin.rent_id+"';";
			rs = stmt.executeQuery(query);
			if(rs.next()) {
				admin.lisenceid = rs.getString(1);
				admin.companyid = rs.getString(2);
			}


			if(admin.fixtest.equals("1")) { //수리할때
				stmt = con.createStatement();
				query = "insert into repair_list(r_log,r_date,r_price,r_due_date,r_other_repair,customer_license_id,campingcar_rent_company_id,garage_id,campingcar_list_id) "
						+ "values('"+admin.r_log+"','"+admin.r_date+"','"+admin.r_price+"','"+admin.r_duedate+"'"
						+ ",'"+admin.r_otherinfo+"','"+admin.lisenceid+"','"+admin.companyid+"','"+admin.grg_id+"'"
						+ ",'"+admin.cpcid+"');";
				int result = stmt.executeUpdate(query);

				stmt = con.createStatement();
				query="DELETE FROM campingcar_return WHERE campingcar_list_id = '"+admin.cpcid+"'";
				int result1 = stmt.executeUpdate(query);
				if(result == 1 && result1==1) {
					//JOptionPane.showMessageDialog(grgbtn, "수리처리 완료되었습니다.");
					//datareset();
					//returnresult();
					//datareset();
				}
			}else if(admin.fixtest.equals("0")) {//수리필요없을때
				//JOptionPane.showMessageDialog(grgbtn, "수리할필요없습니다. 반환하세요.");
			}


		}catch(Exception e1) {
			if(admin.r_log.length()==0||admin.r_date.length()==0||admin.r_price.length()==0||
				admin.r_duedate.length()==0||admin.r_otherinfo.length()==0||admin.grg_id.length()==0||
				admin.cpcid.length()==0) {
				//JOptionPane.showMessageDialog(grgbtn, "빈칸을 모두 채워주세요!");
			}
		}
	}

	/*
	 * 정비소로 보내지 않고 반환하기
	 * - 먼저 수리여부 변수가 1인지 0인지 판단(0이면 반환)
	 * - 반환을 하면 다른 사람이 사용할 수 있도록 rent_list테이블에 반환하는 캠핑카의 정보가 들어가 있어야한다.
	 * - 입력받은 정보로부터 repair_list가 아닌 rentcar_list에 insert해준다.
	 */
	public void ReturnToCampingcarList(AdminInfo admin) {
		/*컨트롤러에서 받아야되는 객체
		 * 1. "반환하기"버튼 toreturnbtn 객체
		*/
		String cpcid = admin.torepair;
		try {
		stmt = con.createStatement();
		String fixtest = null;
		String query = "select fix from campingcar_return where campingcar_list_id='"+cpcid+"'";
		rs = stmt.executeQuery(query);
		if(rs.next()) {fixtest = rs.getString(1);}


		query="select * from campingcar_list where campingcar_list_id='"+cpcid+"'";
		rs = stmt.executeQuery(query);
		String cc_name=null;
		String cc_number=null;
		String cc_sits=null;
		String cc_manufacutre=null;
		String cc_manufacture_year=null;
		String cc_mileage=null;
		String cc_rent_price = null;
		String cc_regitst_date =null;
		String campingcar_rent_company_id=null;
         if(rs.next()) {
        	 cc_name = rs.getString(2);
        	 cc_number =rs.getString(3);
        	 cc_sits =rs.getString(4);
        	 cc_manufacutre =rs.getString(5);
        	 cc_manufacture_year =rs.getString(6);
        	 cc_mileage =rs.getString(7);
        	 cc_rent_price =rs.getString(8);
        	 cc_regitst_date =rs.getString(9);
        	 campingcar_rent_company_id =rs.getString(10);
         }

        	  if(fixtest.equals("1")) {
        		 // JOptionPane.showMessageDialog(toreturnbtn, "수리가필요한 캠핑카입니다.");
        	  }else if(fixtest.equals("0")) {//반환하기!
        		  query="insert into rentcar_list values"
        				  + "('"+cpcid+"','"+cc_name+"','"+cc_number+"','"+cc_sits+"',"
           				+ "'"+cc_manufacutre+"','"+cc_manufacture_year+"','"+
           				cc_mileage+"','"+cc_rent_price+"','"+cc_regitst_date+"','"+
           				campingcar_rent_company_id+"');";
  	 			result = stmt.executeUpdate(query);
  				query="DELETE FROM campingcar_return WHERE campingcar_list_id = '"+cpcid+"'";
  				result = stmt.executeUpdate(query);

  				//JOptionPane.showMessageDialog(toreturnbtn, "반환 완료!");
        		//returnresult();
        	  }
		}catch(Exception e1) {
			if(cpcid.length()==0) {
				//JOptionPane.showMessageDialog(grgbtn, "캠핑카ID를 입력해주세요!");
			}
		}
	}

	//검색 1
	public String Search1() {
		results.append("검색1 결과\n");
		try {
		stmt = con.createStatement();
		String query = "select c_name\r\n" +
				"from (select r_price,customer_license_id\r\n" +
				"   from repair_list\r\n" +
				"    where r_price >=10) rp,\r\n" +
				"    customer cs\r\n" +
				"where cs.license_id=rp.customer_license_id\r\n" +
				"group by cs.c_name;\r\n" +
				"";
		rs = stmt.executeQuery(query);
		String str =null;
		int count=1;
		while(rs.next()) {
			if(count%5==0) {
				results.append("\n");
			}
			str=count+". " + rs.getString(1) + "\t";
			results.append(str);
			count++;
			}
		}catch(Exception e1) {
			System.out.println(e1);
		}

		return results.toString();
	}
	//검색 2
	public String Search2() {
		results.append("검색2 결과\n");
		try {
		stmt = con.createStatement();
		String query = "select c_name\r\n" +
				"FROM    (SELECT  cc_price,c_license_id\r\n" +
				"    FROM   customer_rent_list\r\n" +
				"    WHERE  cc_price >= 50) rl,\r\n" +
				"    customer cs\r\n" +
				"WHERE    cs.license_id=rl.c_license_id\r\n" +
				"GROUP BY cs.c_name;\r\n" +
				"";
		rs = stmt.executeQuery(query);
		String str =null;
		int count=1;
		while(rs.next()) {
			if(count%5==0) {
				results.append("\n");
			}
				str=count+". " + rs.getString(1) + "\t";
				results.append(str);
				count++;
			}
		}catch(Exception e1) {
			System.out.println(e1);
		}
		return results.toString();
	}
	//검색 3
	public String Search3() {
		results.append("검색3 결과\n");
		try {
		stmt = con.createStatement();
		String query = "select g_name\r\n" +
				"from (select cc_manufacture, campingcar_list_id\r\n" +
				"   from campingcar_list\r\n" +
				"   where cc_manufacture_year >=2000) cl, \r\n" +
				"    garage g\r\n" +
				"where g.garage_id = cl.campingcar_list_id\r\n" +
				"group by g.g_name;";
		rs = stmt.executeQuery(query);
		String str =null;
		int count=1;
		while(rs.next()) {
			if(count%5==0) {
				results.append("\n");
			}
			str=count+". " + rs.getString(1) + "\t";
			results.append(str);
			count++;
			}
		}catch(Exception e1) {
			System.out.println(e1);
		}
			return results.toString();
		}
	//검색 4
	public String Search4() {
		results.append("검색4 결과\n");
		try {
		stmt = con.createStatement();
		String query = "select c_name\r\n" +
				"FROM    (SELECT  rent_time,c_license_id\r\n" +
				"    FROM   customer_rent_list\r\n" +
				"    WHERE  rent_time>=10) rl,\r\n" +
				"    customer cs\r\n" +
				"WHERE    cs.license_id=rl.c_license_id\r\n" +
				"GROUP BY cs.c_name;";
		rs = stmt.executeQuery(query);
		String str =null;
		int count=1;
		while(rs.next()) {
			if(count%5==0) {
				results.append("\n");
			}
			str=count+". " + rs.getString(1) + "\t";
			results.append(str);
			count++;
			}
		}catch(Exception e1) {
			System.out.println(e1);
		}
		return results.toString();
	}
}
