package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JRadioButton;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.SystemColor;

public class Customer extends JFrame implements ActionListener{

	private JPanel contentPane;
	 
	
	returncar returncarform;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Customer frame = new Customer();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	    JButton btnNewButton_1 = new JButton("반환하러가기"); 
	    JTextArea listarea = new JTextArea();
		JTextArea starea = new JTextArea();
		
	    JTextField period;
	    JTextField license_id;
	    JTextField start;
	    JTextField addprice;
	    JTextField add;
	    JTextField duedate;
	    JTextField cplist_id;
		JButton rentbtn;
		JTextField carsearch = new JTextField();
		JRadioButton cpcradio;
		JRadioButton carnameradio;
		private JRadioButton ra3;
		private JRadioButton ra4;
		private JRadioButton ra5;
		private JRadioButton ra6;
		private JButton refresh;
		static Connection con;
		   Statement stmt,stmt2,stmt3;
		   ResultSet rs,rs2,r3;
		   String Driver="";
		   String url="jdbc:mysql://localhost:3306/madang?&serverTimezone=Asia/Seoul&allowPublicKeyRetrieval=true&useSSL=false";
		   String userid="madang";
		   String pwd="madang";
		   JButton backbtn;
	public void conDB() { 
	     try {
	       Class.forName("com.mysql.cj.jdbc.Driver");   
	       //System.out.println("드라이버 로드 성공");
	     } catch(ClassNotFoundException e1) {
	         e1.printStackTrace();
	     }
	   try {
	       //System.out.println("데이터베이스 연결 준비...");
	       con=DriverManager.getConnection(url, userid, pwd); 
	       //System.out.println("데이터베이스 연결 성공");
	     } catch(SQLException e1) {
	         e1.printStackTrace();
	       }
	   }
	public Customer() {
		setTitle("17013152 최종혁 -고객페이지");
		returncarform = new returncar();
		returncarform.returnbtn.addActionListener(this);
		conDB();
		returnresult();
		madangrentlist();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1074, 532);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("SEJONG CAMPING");
		lblNewLabel.setFont(new Font("양재백두체B", Font.BOLD, 30));
		lblNewLabel.setBounds(264, 14, 331, 30);
		contentPane.add(lblNewLabel);
		listarea.setBorder(new LineBorder(Color.BLACK, 1, true));
		
		
		listarea.setBounds(12, 156, 820, 325);
		contentPane.add(listarea);
		
		cplist_id = new JTextField();
		cplist_id.setBorder(new EmptyBorder(0, 0, 0, 0));
		cplist_id.setBounds(937, 193, 96, 21);
		contentPane.add(cplist_id);
		cplist_id.setColumns(10);
		
		rentbtn = new JButton("대여");
		rentbtn.setBackground(Color.WHITE);
		rentbtn.setBorder(new EmptyBorder(0, 0, 0, 0));
		rentbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rent();
			}
		});
		rentbtn.setBounds(902, 450, 91, 23);
		contentPane.add(rentbtn);
		
		JLabel lblNewLabel_1 = new JLabel("\uCEA0\uD551\uCE74 LIST");
		lblNewLabel_1.setFont(new Font("굴림", Font.BOLD, 20));
		lblNewLabel_1.setBounds(362, 65, 114, 21);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("\uB300\uC5EC \uD604\uD669");
		lblNewLabel_1_1.setFont(new Font("굴림", Font.BOLD, 18));
		lblNewLabel_1_1.setBounds(902, 14, 103, 21);
		contentPane.add(lblNewLabel_1_1);
		starea.setBorder(new LineBorder(Color.BLACK));
		
		
		starea.setBounds(844, 38, 203, 82);
		contentPane.add(starea);
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.setBorder(new EmptyBorder(0, 0, 0, 0));
		
		
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				returncarform.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(895, 130, 110, 21);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("캠핑카ID");
		lblNewLabel_1_1_1.setFont(new Font("굴림", Font.BOLD, 14));
		lblNewLabel_1_1_1.setBounds(844, 192, 81, 21);
		contentPane.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("\uB300\uC5EC");
		lblNewLabel_1_1_2.setFont(new Font("굴림", Font.BOLD, 18));
		lblNewLabel_1_1_2.setBounds(926, 161, 37, 21);
		contentPane.add(lblNewLabel_1_1_2);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("\uB300\uC5EC\uAE30\uAC04");
		lblNewLabel_1_1_1_1.setFont(new Font("굴림", Font.BOLD, 14));
		lblNewLabel_1_1_1_1.setBounds(844, 312, 81, 21);
		contentPane.add(lblNewLabel_1_1_1_1);
		
		period = new JTextField();
		period.setBorder(new EmptyBorder(0, 0, 0, 0));
		period.setColumns(10);
		period.setBounds(937, 312, 96, 21);
		contentPane.add(period);
		
		JLabel lblNewLabel_1_1_1_2 = new JLabel("\uBA74\uD5C8\uC99D\uBC88\uD638");
		lblNewLabel_1_1_1_2.setFont(new Font("굴림", Font.BOLD, 14));
		lblNewLabel_1_1_1_2.setBounds(844, 234, 81, 21);
		contentPane.add(lblNewLabel_1_1_1_2);
		
		license_id = new JTextField();
		license_id.setBorder(new EmptyBorder(0, 0, 0, 0));
		license_id.setColumns(10);
		license_id.setBounds(937, 234, 96, 21);
		contentPane.add(license_id);
		
		JLabel lblNewLabel_1_1_1_3 = new JLabel("\uB300\uC5EC\uC2DC\uC791\uC77C");
		lblNewLabel_1_1_1_3.setFont(new Font("굴림", Font.BOLD, 14));
		lblNewLabel_1_1_1_3.setBounds(844, 274, 81, 21);
		contentPane.add(lblNewLabel_1_1_1_3);
		
		start = new JTextField();
		start.setBorder(new EmptyBorder(0, 0, 0, 0));
		start.setColumns(10);
		start.setBounds(937, 274, 96, 21);
		contentPane.add(start);
		
		JLabel lblNewLabel_1_1_1_3_1 = new JLabel("\uBB3C\uD488\uAE08\uC561");
		lblNewLabel_1_1_1_3_1.setFont(new Font("굴림", Font.BOLD, 14));
		lblNewLabel_1_1_1_3_1.setBounds(844, 417, 81, 21);
		contentPane.add(lblNewLabel_1_1_1_3_1);
		
		addprice = new JTextField();
		addprice.setBorder(new EmptyBorder(0, 0, 0, 0));
		addprice.setColumns(10);
		addprice.setBounds(937, 418, 96, 21);
		contentPane.add(addprice);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("\uCD94\uAC00\uBB3C\uD488");
		lblNewLabel_1_1_1_1_1.setFont(new Font("굴림", Font.BOLD, 14));
		lblNewLabel_1_1_1_1_1.setBounds(844, 386, 81, 21);
		contentPane.add(lblNewLabel_1_1_1_1_1);
		
		add = new JTextField();
		add.setBorder(new EmptyBorder(0, 0, 0, 0));
		add.setColumns(10);
		add.setBounds(937, 386, 96, 21);
		contentPane.add(add);
		
		JLabel duedateerer = new JLabel("\uB0A9\uC785\uAE30\uD55C");
		duedateerer.setFont(new Font("굴림", Font.BOLD, 14));
		duedateerer.setBounds(844, 351, 81, 21);
		contentPane.add(duedateerer);
		
		duedate = new JTextField();
		duedate.setBorder(new EmptyBorder(0, 0, 0, 0));
		duedate.setColumns(10);
		duedate.setBounds(937, 351, 96, 21);
		contentPane.add(duedate);
		
		carsearch = new JTextField();
		carsearch.setBorder(new EmptyBorder(0, 0, 0, 0));
		carsearch.setColumns(10);
		carsearch.setBounds(328, 125, 91, 21);
		contentPane.add(carsearch);
		
		JButton carserchbtn = new JButton("검색");
		carserchbtn.setBorder(new EmptyBorder(0, 0, 0, 0));
		carserchbtn.setBackground(Color.WHITE);
		carserchbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					listarea.setText("캠핑카ID \t 차명 \t 차량번호 \t 승차인원수 \t 제조회사 \t 제조연도 \t 누적주행거리 \t 대여비용 \t캠핑카등록일자 \t 대여회사ID \n");
					stmt = con.createStatement();
					String searchdata = carsearch.getText();
					String query = null;
					if(cpcradio.isSelected()) {
						query="SELECT * FROM rentcar_list where rent_id='"+searchdata+"';";
						rs = stmt.executeQuery(query);
			             while(rs.next()) {
			                String str = rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4)
			                +  "\t" + rs.getString(5)+ "\t" + rs.getString(6)+"\t"+ rs.getString(7)+"\t"+ rs.getString(8)+"만원\t"+ rs.getString(9)+"\t"+ rs.getString(10)+"\n";
			                listarea.append(str);
			             }
					}else if(carnameradio.isSelected()) {
						query="SELECT * FROM rentcar_list where cc_name='"+searchdata+"';";
						rs = stmt.executeQuery(query);
			             while(rs.next()) {
			                String str = rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4)
			                +  "\t" + rs.getString(5)+ "\t" + rs.getString(6)+"\t"+ rs.getString(7)+"\t"+ rs.getString(8)+"만원\t"+ rs.getString(9)+"\t"+ rs.getString(10)+"\n";
			                listarea.append(str);
			             }
					}else if(ra3.isSelected()) {
						query="SELECT * FROM rentcar_list where cc_sits>='"+searchdata+"';";
						rs = stmt.executeQuery(query);
			             while(rs.next()) {
			                String str = rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4)
			                +  "\t" + rs.getString(5)+ "\t" + rs.getString(6)+"\t"+ rs.getString(7)+"\t"+ rs.getString(8)+"만원\t"+ rs.getString(9)+"\t"+ rs.getString(10)+"\n";
			                listarea.append(str);
			             }
					}else if(ra4.isSelected()) {
						query="SELECT * FROM rentcar_list where cc_manufacture='"+searchdata+"';";
						rs = stmt.executeQuery(query);
			             while(rs.next()) {
			                String str = rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4)
			                +  "\t" + rs.getString(5)+ "\t" + rs.getString(6)+"\t"+ rs.getString(7)+"\t"+ rs.getString(8)+"만원\t"+ rs.getString(9)+"\t"+ rs.getString(10)+"\n";
			                listarea.append(str);
			             }
					}else if(ra5.isSelected()) {
						query="SELECT * FROM rentcar_list where cc_mileage<='"+searchdata+"';";
						rs = stmt.executeQuery(query);
			             while(rs.next()) {
			                String str = rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4)
			                +  "\t" + rs.getString(5)+ "\t" + rs.getString(6)+"\t"+ rs.getString(7)+"\t"+ rs.getString(8)+"만원\t"+ rs.getString(9)+"\t"+ rs.getString(10)+"\n";
			                listarea.append(str);
			             }
					}else if(ra6.isSelected()) {
						query="SELECT * FROM rentcar_list where cc_rent_price<='"+searchdata+"';";
						rs = stmt.executeQuery(query);
			             while(rs.next()) {
			                String str = rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4)
			                +  "\t" + rs.getString(5)+ "\t" + rs.getString(6)+"\t"+ rs.getString(7)+"\t"+ rs.getString(8)+"만원\t"+ rs.getString(9)+"\t"+ rs.getString(10)+"\n";
			                listarea.append(str);
			             }
					}
					
				}catch(Exception e1) {
					System.out.println(e1);
				}
			}
		});
		carserchbtn.setBounds(425, 125, 61, 21);
		contentPane.add(carserchbtn);
		
		cpcradio = new JRadioButton("캠핑카ID");
		cpcradio.setBackground(SystemColor.menu);
		cpcradio.setBounds(138, 92, 81, 23);
		
		carnameradio = new JRadioButton("차명");
		carnameradio.setBackground(SystemColor.menu);
		carnameradio.setBounds(217, 92, 53, 23);
		
		
		contentPane.add(cpcradio);
		contentPane.add(carnameradio);
		
		ra3 = new JRadioButton("최소승차인원");
		ra3.setBackground(SystemColor.menu);
		ra3.setBounds(270, 92, 101, 23);
		contentPane.add(ra3);
		
		ra4 = new JRadioButton("제조회사");
		ra4.setBackground(SystemColor.menu);
		ra4.setBounds(371, 92, 80, 23);
		contentPane.add(ra4);
		
		ra5 = new JRadioButton("최대주행거리");
		ra5.setBackground(SystemColor.menu);
		ra5.setBounds(446, 92, 101, 23);
		contentPane.add(ra5);
		
		ra6 = new JRadioButton("최대대여비용(단위:만원)");
		ra6.setBackground(SystemColor.menu);
		ra6.setBounds(543, 92, 160, 23);
		contentPane.add(ra6);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(cpcradio);
		bg.add(carnameradio);
		bg.add(ra3);
		bg.add(ra4);
		bg.add(ra5);
		bg.add(ra6);
		
		refresh = new JButton("새로고침");
		refresh.setBorder(new EmptyBorder(0, 0, 0, 0));
		refresh.setBackground(Color.WHITE);
		refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				returnresult();
				madangrentlist();
				cplist_id.setText("");
				license_id.setText("");
				period.setText("");
				start.setText("");
				duedate.setText("");
				add.setText("");
				addprice.setText("");
			}
		});
		refresh.setBounds(489, 125, 91, 21);
		contentPane.add(refresh);
		
		backbtn = new JButton("《 뒤로가기");
		backbtn.setBorder(new EmptyBorder(0, 0, 0, 0));
		backbtn.setBackground(Color.WHITE);
		backbtn.setBounds(0, 0, 103, 23);
		contentPane.add(backbtn);
		
	}
	public void returnresult(){
		try {
			listarea.setText("캠핑카ID \t 차명 \t 차량번호 \t 승차인원수 \t 제조회사 \t 제조연도 \t 누적주행거리 \t 대여비용 \t캠핑카등록일자 \t 대여회사ID \n");
			stmt = con.createStatement();
			String query="SELECT * FROM rentcar_list;";
			rs = stmt.executeQuery(query);
             while(rs.next()) {
                String str = rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4)
                +  "\t" + rs.getString(5)+ "\t" + rs.getString(6)+"\t"+ rs.getString(7)+"\t"+ rs.getString(8)+"만원\t"+ rs.getString(9)+"\t"+ rs.getString(10)+"\n";
                listarea.append(str);
             }
		}catch(Exception e1) {
			System.out.println(e1);
		}  
	}
	public void madangrentlist() {
		try {
			starea.setText("번호 \t 캠핑카ID \t 금액\n");
			stmt3 = con.createStatement();
			String query3=" select campingcar_id,cc_price from customer_rent_list where c_license_id='1111111'";
			rs2 = stmt3.executeQuery(query3);
			String str = null;
			int count =1;
			while(rs2.next()) {
	            str = count +"\t" + rs2.getString(1) + "\t" + rs2.getString(2)+"\n";
	            starea.append(str);
	            count++;
			}
		}catch(Exception e1) {
			System.out.println(e1);
		}
	}
	public void rent() {
		String ccid = cplist_id.getText();
		String lisence =license_id.getText();
		String rentperiod = period.getText();
		String strt = start.getText();
		String duedate2 = duedate.getText();
		String add2 = add.getText();
		String addprice2 = addprice.getText();
		String campingcpid = null;
		String carprice = null;
		try {
			stmt = con.createStatement();
			stmt2 = con.createStatement();
			stmt3 = con.createStatement();
			
			
			String query="SELECT cc_rent_price,camping_rent_company_id FROM rentcar_list where rent_id='"+ccid+"';";
			rs = stmt.executeQuery(query);
             if(rs.next()) {
            	 carprice = rs.getString(1);
            	campingcpid=  rs.getString(2);
             }
			
			String query2="insert into customer_rent_list(start_date,rent_time,due_date,otherthing,others_price,campingcar_company_id,c_license_id,campingcar_id,cc_price) values('"+strt+"','"+rentperiod+"','"+duedate2+"','"+add2+"','"+addprice2+"','"+campingcpid+"','"+lisence+"','"+ccid+"','"+carprice+"');";
			int result = stmt2.executeUpdate(query2);
             if( result ==1 ){
            	 JOptionPane.showMessageDialog(rentbtn, "대여 완료");
            	 madangrentlist();
            	 try {
		    			stmt3 = con.createStatement();
		    			String query3="DELETE FROM customer_rent_list WHERE rent_id = '"+campingcpid+"'";
		    			int result2 = stmt3.executeUpdate(query3);
		    		}catch(Exception e1) {
		    			
		    			System.out.println("1"+e1);
		    		}
             }else {
            	 JOptionPane.showMessageDialog(rentbtn, "데이터값 재입력");
             }
		}catch(Exception e1) {
			//System.out.println("2"+e1);
			JOptionPane.showMessageDialog(rentbtn, "옳바른 데이터값을 입력해주세요.");
			if(ccid.length()==0||lisence.length()==0||rentperiod.length()==0||strt.length()==0||duedate2.length()==0||add2.length()==0||addprice2.length()==0||campingcpid.length()==0||carprice.length()==0) {
        		JOptionPane.showMessageDialog(rentbtn, "빈칸을 모두채워주세요");            		
        	}
			System.out.println("데이터값을 입력해주세요");
		}
	}

	
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()== returncarform.returnbtn) {
			returnresult();
			madangrentlist();
			returncarform.setVisible(false);
			setVisible(true);
		}
	}
}
