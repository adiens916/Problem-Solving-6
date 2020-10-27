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

public class Customerdata extends JFrame{

	private JPanel contentPane;
	 JTextField lisenceid;
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
					Customerdata frame = new Customerdata();
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
		   private JLabel lblNewLabel_1_1_2;
		   private JLabel lblNewLabel_1_1_3;
		   private JLabel lblNewLabel_2;
		   
		   
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
		       //System.out.println("데이터베이스 연결 성공");
		     } catch(SQLException e1) {
		         e1.printStackTrace();
		       }
		   }
		public void printdata() {
			selectcp.setText("운전면허번호 \t 이름 \t 주소 \t 번호 \t 이메일 \n");
			try {
				stmt2 = con.createStatement();
				String query2=" select * from customer"; /* SQL 문 */
				rs2 = stmt2.executeQuery(query2);
				
				while(rs2.next()) {
					String str = rs2.getInt(1) + "\t" + rs2.getString(2) + "\t" + rs2.getString(3) + "\t" + rs2.getString(4)
		            +  "\t" + rs2.getString(5)+"\n";
					selectcp.append(str);
				}
			}catch(Exception e1) {
				System.out.println(e1);
			}
		}	
		
	public Customerdata() {
		setTitle("17013152 최종혁 -손님정보 관리페이지");
		conDB();
		
		printdata();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 840, 442);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		/* 출력 영역 */
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(311, 98, 493, 287);
		panel.add(scrollPane);
		
		
		scrollPane.setViewportView(selectcp);
		
		lisenceid = new JTextField();
		lisenceid.setColumns(10);
		lisenceid.setBounds(158, 99, 72, 20);
		panel.add(lisenceid);
			
		
		JButton btnNewButton = new JButton("검색");
		btnNewButton.setFont(new Font("굴림", Font.PLAIN, 10));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					selectcp.setText("");
					stmt2 = con.createStatement();
					String id = lisenceid.getText();
					selectcp.setText("운전면허번호 \t 이름 \t 주소 \t 번호 \t 이메일 \n");
					String query2=" select * from customer where license_id='"+id+"';"; /* SQL 문 */
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
		number.setBounds(158, 218, 141, 21);
		panel.add(number);
		
		emailaddress = new JTextField();
		emailaddress.setColumns(10);
		emailaddress.setBounds(158, 257, 141, 21);
		panel.add(emailaddress);
		btnNewButton_1_3 = new JButton("수정");
		btnNewButton_1_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String a1 = name.getText();
				String a2 = address.getText();
				String a3 = number.getText();
				String a4 = emailaddress.getText();
				
				String id = lisenceid.getText();
				try {
					selectcp.setText("");
					stmt = con.createStatement();
					String query="update customer set c_name='"+a1+"'"
							+ ",c_address='"+a2+"',c_number='"+a3+"'"
							+ ",c_email='"+a4+"' where license_id='"+id+"'";
				
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
					
					String id = lisenceid.getText();
					String query="DELETE FROM customer WHERE license_id = '"+id+"'";
					int result = stmt.executeUpdate(query);
					
					if(result == 1) {
						JOptionPane.showMessageDialog(btnNewButton_1_3_1, "삭제 완료");
						printdata();
						lisenceid.setText("");
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
				lisenceid.setText("");
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
				String a4 = emailaddress.getText();
				String id = lisenceid.getText();
				try {
					
					
					stmt = con.createStatement();
					String query="insert into customer(license_id,c_name,c_address,c_number,c_email)"
							+ " values('"+id+"','"+a1+"','"+a2+"','"+a3+"','"+a4+"')";
					if(a1.length()==0||a2.length()==0||a3.length()==0||a4.length()==0||id.length()==0){
						JOptionPane.showMessageDialog(btnNewButton_1_3_2, "빈칸을 모두채워주세요"); 
						printdata();
					}else {
						int result =  stmt.executeUpdate(query);
						if (result == 1) {
							JOptionPane.showMessageDialog(btnNewButton_1_3_2, "입력완료!");
							printdata();
						}else {
							JOptionPane.showMessageDialog(btnNewButton_1_3_2, "다시입력해주세요!");
						}
					}
					
				}catch(Exception e1) {
					int re=0;
					try {
						stmt2 = con.createStatement();
						String query2 = "select license_id from customer";
						rs = stmt2.executeQuery(query2);
						while(rs.next()) {
							if(id.equals(rs.getString(1))) {
								re=1;
								break;
							}
						}
						if(re==1) {
						JOptionPane.showMessageDialog(btnNewButton_1_3_2, "이미가입하신고객입니다.");
						printdata();
						}						
					}catch(Exception e2) {
						System.out.println(e2);
					}
					System.out.println(e1);
				}
			}
		});
		btnNewButton_1_3_2.setForeground(Color.BLACK);
		btnNewButton_1_3_2.setFont(new Font("굴림", Font.BOLD, 15));
		btnNewButton_1_3_2.setBounds(34, 316, 70, 29);
		panel.add(btnNewButton_1_3_2);
		
		lblNewLabel = new JLabel("운전면허번호 검색후 수정,삭제하시면 더 편리합니다.");
		lblNewLabel.setBounds(265, 49, 294, 15);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("운전면허번호 입력");
		lblNewLabel_1.setFont(new Font("굴림", Font.BOLD, 15));
		lblNewLabel_1.setBounds(16, 98, 130, 21);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("이름");
		lblNewLabel_1_1.setFont(new Font("굴림", Font.BOLD, 18));
		lblNewLabel_1_1.setBounds(16, 141, 120, 20);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("주소");
		lblNewLabel_1_1_1.setFont(new Font("굴림", Font.BOLD, 18));
		lblNewLabel_1_1_1.setBounds(16, 178, 120, 20);
		panel.add(lblNewLabel_1_1_1);
		
		lblNewLabel_1_1_2 = new JLabel("번호");
		lblNewLabel_1_1_2.setFont(new Font("굴림", Font.BOLD, 18));
		lblNewLabel_1_1_2.setBounds(16, 221, 120, 20);
		panel.add(lblNewLabel_1_1_2);
		
		lblNewLabel_1_1_3 = new JLabel("이메일주소");
		lblNewLabel_1_1_3.setFont(new Font("굴림", Font.BOLD, 18));
		lblNewLabel_1_1_3.setBounds(16, 260, 120, 20);
		panel.add(lblNewLabel_1_1_3);
		
		lblNewLabel_2 = new JLabel("고객정보 입력 | 수정 | 삭제");
		lblNewLabel_2.setFont(new Font("양재튼튼체B", Font.BOLD, 28));
		lblNewLabel_2.setBounds(217, 4, 391, 39);
		panel.add(lblNewLabel_2);
		
		
	}
}


