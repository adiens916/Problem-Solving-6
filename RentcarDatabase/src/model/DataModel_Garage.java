package model;

import view.dataClass.GarageInfo;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JDesktopPane;
import java.awt.Panel;
import javax.swing.JLabel;

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

    public String getGarageList() {
        StringBuilder results = new StringBuilder();
        try {
            stmt2 = con.createStatement();
            String query2=" select * from garage"; /* SQL 문 */
            rs2 = stmt2.executeQuery(query2);

            while(rs2.next()) {
                String str = rs2.getInt(1) + "\t" + rs2.getString(2) + "\t" + rs2.getString(3) + "\t" + rs2.getString(4)
                        +  "\t" + rs2.getString(5)+"\t"+rs2.getString(6)+"\n";
                results.append(str);
            }
        }catch(Exception e1) {
            System.out.println(e1);
        }
        return results.toString();
    }

    public String getGarageById(String garageId) {
        StringBuilder results = new StringBuilder();
        try {
            stmt2 = con.createStatement();
            String query2=" select * from garage where garage_id='"+garageId+"';"; /* SQL 문 */
            rs2 = stmt2.executeQuery(query2);
            String str = null;
            if(rs2.next()) {
                str = rs2.getInt(1) + "\t" + rs2.getString(2) + "\t" + rs2.getString(3) + "\t" + rs2.getString(4)
                        + "\t" + rs2.getString(5) + "\n";
            }
            results.append(str);
        } catch (SQLException throwables) {
                throwables.printStackTrace();
        }
        return results.toString();
    }

    public int addGarage(GarageInfo garage) {
        int result = 0;
        try {
            stmt = con.createStatement();
            String query="insert into garage(garage_id,g_name,g_address,g_number,g_manager,g_email)"
                    + " values('"+garage.garageId
                    +"','"+garage.name
                    +"','"+garage.address
                    +"','"+garage.number
                    +"','"+garage.manager
                    +"','"+garage.emailAddress
                    +"')";
            result =  stmt.executeUpdate(query);
        }catch(Exception e1) {
            if(isNullData(garage)){
                result = 2;
            }
        }
        return result;
    }

    public int updateGarage(GarageInfo garage) {
        int result = 0;
        try {
            stmt = con.createStatement();
            String query="update garage set g_name='" +garage.name
                    +"',g_address='"+garage.address
                    +"',g_number='"+garage.number
                    +"',g_manager='"+garage.manager
                    +"',g_email='"+garage.emailAddress
                    +"' where garage_id='"+garage.garageId
                    +"';";
            result = stmt.executeUpdate(query);
        }catch(Exception e1) {
            if(isNullData(garage)) {
                result = 2;
            }
        }
        return result;
    }

    public int deleteGarage(String garageId) {
        int result = 0;
        try {
            stmt = con.createStatement();
            String query="DELETE FROM garage WHERE garage_id = '"+garageId+"'";
            result = stmt.executeUpdate(query);
        } catch (Exception e1) {
            System.out.println(e1);
        }
        return result;
    }

    private boolean isNullData(GarageInfo garage) {
        return garage.name.length() == 0
                || garage.address.length() == 0
                || garage.number.length() == 0
                || garage.manager.length() == 0
                || garage.garageId.length() == 0;
    }
}
