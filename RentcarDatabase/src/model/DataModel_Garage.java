package model;

import controller.dataClass.GarageInfo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class DataModel_Garage {

    static Connection con;
    Statement stmt,stmt2;
    ResultSet rs,rs2;
    String Driver="";
    String url="jdbc:mysql://localhost:3306/madang?&serverTimezone=Asia/Seoul&allowPublicKeyRetrieval=true&useSSL=false";
    String userid="madang";
    String pwd="madang";

    public DataModel_Garage() {
        conDB();
    }

    public void conDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //  System.out.println("드라이버 로드 성공");
        } catch(ClassNotFoundException e1) {
            e1.printStackTrace();
        }
        try {
            //  System.out.println("데이터베이스 연결 준비...");
            con=DriverManager.getConnection(url, userid, pwd);
            // System.out.println("데이터베이스 연결 성공");
        } catch(SQLException e1) {
            e1.printStackTrace();
        }
    }

    public ArrayList<GarageInfo> getGarageList() {
        try {
            stmt2 = con.createStatement();
            String query2=" select * from garage"; /* SQL 문 */
            rs2 = stmt2.executeQuery(query2);

            ArrayList<GarageInfo> garageList = new ArrayList<>();
            while(rs2.next()) {
                GarageInfo garage = new GarageInfo();
                garage.id = Integer.toString(rs2.getInt(1));
                garage.name = rs2.getString(2);
                garage.address = rs2.getString(3);
                garage.number = rs2.getString(4);
                garage.manager = rs2.getString(5);
                garage.emailAddress = rs2.getString(6);
                garageList.add(garage);
            }
            return garageList;
        }catch(Exception e1) {
            System.out.println(e1);
            return null;
        }
    }

    public GarageInfo searchGarageById(String garageId) {
        try {
            stmt2 = con.createStatement();
            String query2=" select * from garage where garage_id='"+garageId+"';"; /* SQL 문 */
            rs2 = stmt2.executeQuery(query2);

            GarageInfo garage = new GarageInfo();
            if(rs2.next()) {
                garage.id = Integer.toString(rs2.getInt(1));
                garage.name = rs2.getString(2);
                garage.address = rs2.getString(3);
                garage.number = rs2.getString(4);
                garage.manager = rs2.getString(5);
            }
            return garage;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public int addGarage(GarageInfo garage) {
        try {
            stmt = con.createStatement();
            String query="insert into garage(garage_id,g_name,g_address,g_number,g_manager,g_email)"
                    + " values('"+garage.id
                    +"','"+garage.name
                    +"','"+garage.address
                    +"','"+garage.number
                    +"','"+garage.manager
                    +"','"+garage.emailAddress
                    +"')";
            return stmt.executeUpdate(query);
        }catch(Exception e1) {
            if(garage.isNull()){
                return 2;
            }
            return 0;
        }
    }

    public int updateGarage(GarageInfo garage) {
        try {
            stmt = con.createStatement();
            String query="update garage set g_name='" +garage.name
                    +"',g_address='"+garage.address
                    +"',g_number='"+garage.number
                    +"',g_manager='"+garage.manager
                    +"',g_email='"+garage.emailAddress
                    +"' where garage_id='"+garage.id
                    +"';";
            return stmt.executeUpdate(query);
        }catch(Exception e1) {
            if(garage.isNull()) {
                return 2;
            }
            return 0;
        }
    }

    public int deleteGarage(String garageId) {
        try {
            stmt = con.createStatement();
            String query="DELETE FROM garage WHERE garage_id = '"+garageId+"'";
            return stmt.executeUpdate(query);
        } catch (Exception e1) {
            System.out.println(e1);
            return 0;
        }
    }
    public String toStringFromGarageInfo(GarageInfo garage) {
		String str = garage.id + "\t" +
				garage.name + "\t" +
				garage.address + "\t" +
				garage.number + "\t" +
				garage.manager + "\n";
		return str;
	}
    
   
    
}
