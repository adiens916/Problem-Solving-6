package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class returncar extends JFrame {

	 JPanel contentPane;
	 JTextField front;
	 JTextField right;
	 JTextField back;
	 JTextField left;
	 JTextField fix;
	static Connection con;
	   Statement stmt,stmt2,stmt3,stmt4;
	   ResultSet rs,rs2,r3;
	   String Driver="";
	   String url="jdbc:mysql://localhost:3306/madang?&serverTimezone=Asia/Seoul&allowPublicKeyRetrieval=true&useSSL=false";
	   String userid="madang";
	   String pwd="madang";
	   JTextField cpid;
	   JButton returnbtn; 
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
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					returncar frame = new returncar();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public returncar() {
		setTitle("17013152 최종혁 -랜트카반환페이지");
		conDB();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 464, 369);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("앞");
		lblNewLabel_1.setFont(new Font("굴림", Font.BOLD, 18));
		lblNewLabel_1.setBounds(12, 106, 27, 22);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("오른쪽");
		lblNewLabel_1_1.setFont(new Font("굴림", Font.BOLD, 18));
		lblNewLabel_1_1.setBounds(156, 21, 55, 22);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("뒤");
		lblNewLabel_1_2.setFont(new Font("굴림", Font.BOLD, 18));
		lblNewLabel_1_2.setBounds(334, 105, 27, 22);
		contentPane.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("왼쪽");
		lblNewLabel_1_3.setFont(new Font("굴림", Font.BOLD, 18));
		lblNewLabel_1_3.setBounds(167, 193, 45, 22);
		contentPane.add(lblNewLabel_1_3);
		
		front = new JTextField();
		front.setBounds(35, 106, 45, 21);
		contentPane.add(front);
		front.setColumns(10);
		
		right = new JTextField();
		right.setColumns(10);
		right.setBounds(217, 22, 45, 21);
		contentPane.add(right);
		
		back = new JTextField();
		back.setColumns(10);
		back.setBounds(358, 107, 45, 21);
		contentPane.add(back);
		
		left = new JTextField();
		left.setColumns(10);
		left.setBounds(210, 193, 45, 21);
		contentPane.add(left);
		
		JLabel lblNewLabel_2 = new JLabel("수리여부(1:수리O, 0:수리x)");
		lblNewLabel_2.setFont(new Font("굴림", Font.BOLD, 15));
		lblNewLabel_2.setBounds(52, 239, 198, 21);
		contentPane.add(lblNewLabel_2);
		
		fix = new JTextField();
		fix.setBounds(247, 237, 55, 21);
		contentPane.add(fix);
		fix.setColumns(10);
		
		returnbtn = new JButton("반환");
		returnbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String front1 = front.getText();
					String right1 = right.getText();
					String left1 = left.getText();
					String back1 = back.getText();
					String fix1 = fix.getText();
					String cpid1 = cpid.getText();
					
					stmt = con.createStatement();
					stmt2 = con.createStatement();
					stmt4 = con.createStatement();
					String query2=" select * from customer_rent_list where campingcar_id='"+cpid1+"'";
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
					
					String query="insert into campingcar_return values('"+front1+"','"+right1+"','"+left1+"','"+back1+"','"+fix1+"','"+cpid1+"','"+rent_id+"')";
					int result = stmt.executeUpdate(query);
		             if( result ==1 ){
				            JOptionPane.showMessageDialog(returnbtn, "반환완료");
				            try {
				            	String query4="insert into customer_rent_old_list values('"+rent_id+"','"+a1+"','"+a2+"','"+a3+"','"+a4+"','"+a5+"','"+a6+"','"+a7+"','"+a8+"','"+a9+"')";
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
		});
		returnbtn.setFont(new Font("양재튼튼체B", Font.BOLD, 15));
		returnbtn.setBounds(309, 237, 74, 52);
		contentPane.add(returnbtn);
		
		JLabel lblNewLabel = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/car.png")).getImage();
		lblNewLabel.setIcon(new ImageIcon(img));
		lblNewLabel.setBounds(95, 36, 331, 161);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_2_1 = new JLabel("캠핑카ID");
		lblNewLabel_2_1.setFont(new Font("굴림", Font.BOLD, 15));
		lblNewLabel_2_1.setBounds(174, 268, 67, 21);
		contentPane.add(lblNewLabel_2_1);
		
		cpid = new JTextField();
		cpid.setColumns(10);
		cpid.setBounds(247, 266, 55, 21);
		contentPane.add(cpid);
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
