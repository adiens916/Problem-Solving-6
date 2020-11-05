package main;

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

public class Garagedata extends JFrame{

	private JPanel contentPane;
	 JTextField garageid;
	JButton btnNewButton_1_3;
	JButton btnNewButton_1_3_1;
	JButton btnNewButton_1_3_2;
	JButton quit;
	 JTextField name;
	 JTextField address;
	 JTextField number;
	 JTextField emailaddress;
	 JTextArea selectcp = new JTextArea();;
	 JButton btnNewButton_1;
	 private JLabel lblNewLabel;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Garagedata frame = new Garagedata();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// SQL 연결
		static Connection con;
		   Statement stmt,stmt2;
		   ResultSet rs,rs2;
		   String Driver="";
		   String url="jdbc:mysql://localhost:3306/madang?&serverTimezone=Asia/Seoul&useSSL=false"; 
		   String userid="madang";
		   String pwd="madang";
		   private JTextField gmanager;
		   private JLabel lblNewLabel_1;
		   private JLabel lblNewLabel_1_1;
		   private JLabel lblNewLabel_1_1_1;
		   private JLabel lblNewLabel_2;
		   private JLabel lblNewLabel_1_1_2;
		   private JLabel lblNewLabel_1_1_3;
		   private JLabel lblNewLabel_1_2;
		   
		   
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
		public void printdata() {
			selectcp.setText("차고지ID \t 카센터이름 \t 주소 \t 번호 \t 매니저이름 \t 이메일주소\n");
			try {
				stmt2 = con.createStatement();
				String query2=" select * from garage"; /* SQL 문 */
				rs2 = stmt2.executeQuery(query2);
				
				while(rs2.next()) {
					String str = rs2.getInt(1) + "\t" + rs2.getString(2) + "\t" + rs2.getString(3) + "\t" + rs2.getString(4)
		            +  "\t" + rs2.getString(5)+"\t"+rs2.getString(6)+"\n";
					selectcp.append(str);
				}
			}catch(Exception e1) {
				System.out.println(e1);
			}
		}	
		
	public Garagedata() {
		setTitle("17013152 최종혁 -정비소정보 관리페이지");
		conDB();
		
		printdata();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 921, 441);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		/* 출력 영역 */
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(311, 98, 576, 287);
		panel.add(scrollPane);
		
		
		scrollPane.setViewportView(selectcp);
		
		garageid = new JTextField();
		garageid.setColumns(10);
		garageid.setBounds(158, 99, 72, 20);
		panel.add(garageid);
			
		
		JButton btnNewButton = new JButton("검색");
		btnNewButton.setFont(new Font("굴림", Font.PLAIN, 10));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					selectcp.setText("");
					stmt2 = con.createStatement();
					String id = garageid.getText();
					selectcp.setText("차고지ID \t 카세턴이름 \t 주소 \t 번호 \t 매니저이름 \n");
					String query2=" select * from garage where garage_id='"+id+"';"; /* SQL 문 */
					rs2 = stmt2.executeQuery(query2);
					String str = null;
					if(rs2.next()) {
			            str = rs2.getInt(1) + "\t" + rs2.getString(2) + "\t" + rs2.getString(3) + "\t" + rs2.getString(4)
			            +  "\t" + rs2.getString(5)+"\n";
					}
		            selectcp.append(str);
				}catch(Exception e1) {
					System.out.println(e1);
				}
			}
		});
		btnNewButton.setBounds(242, 98, 57, 23);
		panel.add(btnNewButton);
		
		name = new JTextField();
		name.setColumns(10);
		name.setBounds(158, 142, 141, 21);
		panel.add(name);
		
		address = new JTextField();
		address.setColumns(10);
		address.setBounds(158, 179, 141, 21);
		panel.add(address);
		
		number = new JTextField();
		number.setColumns(10);
		number.setBounds(158, 216, 141, 21);
		panel.add(number);
		
		emailaddress = new JTextField();
		emailaddress.setColumns(10);
		emailaddress.setBounds(158, 285, 141, 21);
		panel.add(emailaddress);
		btnNewButton_1_3 = new JButton("수정");
		btnNewButton_1_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String a1 = name.getText();
				String a2 = address.getText();
				String a3 = number.getText();
				String a4 = emailaddress.getText();
				String a5 = gmanager.getText();
				String id = garageid.getText();
				try {
					selectcp.setText("");
					stmt = con.createStatement();
					
					String query="update garage set g_name='"+a1+"',g_address='"+a2+"',g_number='"+a3+"',g_manager='"+a4+"',g_email='"+a5+"' where garage_id='"+id+"';";
				
					int result = stmt.executeUpdate(query);
					if(result==1) {
		            JOptionPane.showMessageDialog(btnNewButton_1_3, "수정완료"); 
		            //수정하고나서출력!
		            printdata();
					}else {
						 JOptionPane.showMessageDialog(btnNewButton_1_3, "다시입력하세요!"); 
					}
				}catch(Exception e1) {
					if(a1.length()==0||a2.length()==0||a3.length()==0||a4.length()==0||id.length()==0) {
						JOptionPane.showMessageDialog(btnNewButton_1_3, "빈칸을 모두채워주세요"); 
						printdata();
					}
					//System.out.println(e1);
				}
			}
		});
		btnNewButton_1_3.setForeground(Color.BLACK);
		btnNewButton_1_3.setFont(new Font("굴림", Font.BOLD, 15));
		btnNewButton_1_3.setBounds(115, 316, 70, 29);
		panel.add(btnNewButton_1_3);
		
		
		btnNewButton_1_3_1 = new JButton("삭제");
		btnNewButton_1_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					stmt = con.createStatement();
					
					String id = garageid.getText();
					String query="DELETE FROM garage WHERE garage_id = '"+id+"'";
					int result = stmt.executeUpdate(query);
					
					if(result == 1) {
						JOptionPane.showMessageDialog(btnNewButton_1_3_1, "삭제 완료");
						printdata();
						garageid.setText("");
					}else {
						JOptionPane.showMessageDialog(btnNewButton_1_3_1, "ID를 입력해주세요.");
						//ystem.out.println("실패");
					}
				}catch(Exception e1) {
					System.out.println(e1);
				}
			 			
			}
		});
		btnNewButton_1_3_1.setForeground(Color.BLACK);
		btnNewButton_1_3_1.setFont(new Font("굴림", Font.BOLD, 15));
		btnNewButton_1_3_1.setBounds(197, 316, 70, 29);
		panel.add(btnNewButton_1_3_1);
		
		quit = new JButton("닫기");
		quit.setForeground(Color.BLACK);
		quit.setFont(new Font("굴림", Font.BOLD, 15));
		quit.setBounds(115, 355, 70, 22);
		panel.add(quit);
		
		btnNewButton_1 = new JButton("새로고침");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				printdata();
				name.setText("");
				address.setText("");
				number.setText("");
				emailaddress.setText("");
				garageid.setText("");
			}
		});
		btnNewButton_1.setBounds(16, 18, 96, 20);
		panel.add(btnNewButton_1);
		
		btnNewButton_1_3_2 = new JButton("입력");
		btnNewButton_1_3_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String a1 = name.getText();
				String a2 = address.getText();
				String a3 = number.getText();
				String a4 = gmanager.getText();
				String a5 = emailaddress.getText();
				String id = garageid.getText();
				try {
					stmt = con.createStatement();
					String query="insert into garage(garage_id,g_name,g_address,g_number,g_manager,g_email)"
							+ " values('"+id+"','"+a1+"','"+a2+"','"+a3+"','"+a4+"','"+a5+"')";
					int result =  stmt.executeUpdate(query);
					if (result == 1) {
						JOptionPane.showMessageDialog(btnNewButton_1_3_2, "입력완료!");
						printdata();
					}else {
						JOptionPane.showMessageDialog(btnNewButton_1_3_2, "다시입력해주세요!");
					}
				}catch(Exception e1) {
					if(a1.length()==0||a2.length()==0||a3.length()==0||a4.length()==0||a5.length()==0||id.length()==0){
						JOptionPane.showMessageDialog(btnNewButton_1_3_2, "빈칸을 모두채워주세요"); 
						printdata();
					}
					//System.out.println(e1);
				}
			}
		});
		btnNewButton_1_3_2.setForeground(Color.BLACK);
		btnNewButton_1_3_2.setFont(new Font("굴림", Font.BOLD, 15));
		btnNewButton_1_3_2.setBounds(34, 316, 70, 29);
		panel.add(btnNewButton_1_3_2);
		
		lblNewLabel = new JLabel("차고지ID 검색후 수정,삭제하시면 더 편리합니다.");
		lblNewLabel.setBounds(329, 55, 294, 15);
		panel.add(lblNewLabel);
		
		gmanager = new JTextField();
		gmanager.setColumns(10);
		gmanager.setBounds(158, 252, 141, 21);
		panel.add(gmanager);
		
		lblNewLabel_1 = new JLabel("이름");
		lblNewLabel_1.setFont(new Font("굴림", Font.BOLD, 18));
		lblNewLabel_1.setBounds(16, 141, 120, 20);
		panel.add(lblNewLabel_1);
		
		lblNewLabel_1_1 = new JLabel("주소");
		lblNewLabel_1_1.setFont(new Font("굴림", Font.BOLD, 18));
		lblNewLabel_1_1.setBounds(16, 178, 120, 20);
		panel.add(lblNewLabel_1_1);
		
		lblNewLabel_1_1_1 = new JLabel("이메일주소");
		lblNewLabel_1_1_1.setFont(new Font("굴림", Font.BOLD, 18));
		lblNewLabel_1_1_1.setBounds(16, 286, 120, 20);
		panel.add(lblNewLabel_1_1_1);
		
		lblNewLabel_2 = new JLabel("정비소 정보 입력 | 수정 | 삭제");
		lblNewLabel_2.setFont(new Font("양재튼튼체B", Font.BOLD, 28));
		lblNewLabel_2.setBounds(274, 10, 432, 39);
		panel.add(lblNewLabel_2);
		
		lblNewLabel_1_1_2 = new JLabel("매니저이름");
		lblNewLabel_1_1_2.setFont(new Font("굴림", Font.BOLD, 18));
		lblNewLabel_1_1_2.setBounds(16, 255, 120, 20);
		panel.add(lblNewLabel_1_1_2);
		
		lblNewLabel_1_1_3 = new JLabel("차고지ID입력");
		lblNewLabel_1_1_3.setFont(new Font("굴림", Font.BOLD, 18));
		lblNewLabel_1_1_3.setBounds(16, 102, 120, 20);
		panel.add(lblNewLabel_1_1_3);
		
		lblNewLabel_1_2 = new JLabel("번호");
		lblNewLabel_1_2.setFont(new Font("굴림", Font.BOLD, 18));
		lblNewLabel_1_2.setBounds(16, 216, 120, 20);
		panel.add(lblNewLabel_1_2);
		
		
	}
}


