package model;

import view.CarStateInReturn;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ReturnModel {

    JTextField cpid;
    JButton returnbtn;

    static Connection con;
    Statement stmt,stmt2,stmt3,stmt4;
    ResultSet rs,rs2,r3;
    String url="jdbc:mysql://localhost:3306/madang?&serverTimezone=Asia/Seoul&allowPublicKeyRetrieval=true&useSSL=false";
    String userid="madang";
    String pwd="madang";

    public void conDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // System.out.println("드라이버 로드 성공");
        } catch(ClassNotFoundException e1) {
            e1.printStackTrace();
        }
        try {
            // System.out.println("데이터베이스 연결 준비...");
            con=DriverManager.getConnection(url, userid, pwd);
            // System.out.println("데이터베이스 연결 성공");
        } catch(SQLException e1) {
            e1.printStackTrace();
        }
    }

    public ReturnModel() {
        conDB();
    }

    public void returnCar(CarStateInReturn state) {
        try {
            stmt = con.createStatement();
            stmt2 = con.createStatement();
            stmt4 = con.createStatement();
            String query2=" select * from customer_rent_list where campingcar_id='"+state.carId+"'";
            rs2 = stmt2.executeQuery(query2);

            String rent_id = null;
            String a1=null;
            String a2=null;
            String a3=null;
            String a4=null;
            String a5=null;
            String a6=null;
            String a7=null;
            String a8=null;
            String a9=null;
            if(rs2.next()) {
                rent_id = rs2.getString(1);
                a1 = rs2.getString(2);a2 = rs2.getString(3);a3 = rs2.getString(4);
                a4 = rs2.getString(5);a5 = rs2.getString(6);a6 = rs2.getString(7);
                a7 = rs2.getString(8);a8 = rs2.getString(9);a9 = rs2.getString(10);
            }

            String query="insert into campingcar_return values('"
                    +state.front+"','" +state.right+"','" +state.left+"','" +state.back+"','"
                    +state.fix+"','" +state.carId+"','" +rent_id+"')";
            int result = stmt.executeUpdate(query);
            if( result ==1 ){
                JOptionPane.showMessageDialog(returnbtn, "반환완료");
                try {
                    String query4="insert into customer_rent_old_list values('"
                            +rent_id+"','"+a1+"','"+a2+"','"+a3+"','"+a4+"','"+a5+"','"+a6+"','"+a7+"','"+a8+"','"+a9+"')";
                    int result4 = stmt4.executeUpdate(query4);
                    stmt3 = con.createStatement();
                    String query3="DELETE FROM customer_rent_list WHERE rent_id = '"+rent_id+"'and c_license_id='1111111'";
                    int result2 = stmt3.executeUpdate(query3);

                }catch(Exception e1) {
                    System.out.println(e1);
                }
            }
        }catch(Exception e1) {
            System.out.println(e1);
            JOptionPane.showMessageDialog(returnbtn, "차의 모든 상태 및 캠핑카ID를 확인해주세요.");
        }
    }

    public void deletedata() {
        try {
            stmt = con.createStatement();
            String cpid1 = cpid.getText();
            String query="DELETE FROM customer_rent_list WHERE rent_id = '"+cpid1+"'";
            int result = stmt.executeUpdate(query);
        }catch(Exception e1) {
            System.out.println(e1);
        }
    }
}
