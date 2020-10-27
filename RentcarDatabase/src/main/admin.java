package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.ComponentOrientation;
import java.awt.SystemColor;
import javax.swing.JTable;
import javax.swing.JPopupMenu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.Insets;
import javax.swing.JSlider;
import java.awt.List;
import javax.swing.JToolBar;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.awt.Window.Type;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;

public class admin extends JFrame implements ActionListener{

	private JPanel contentPane;
	
	Companydata delform;
	Campingcardata campform;
	Customerdata cusform;
	Garagedata grgform;
	JMenuItem mntmNewMenuItem_1_4;
	JMenuItem mntmNewMenuItem_1_4_1;
	JMenuItem mntmNewMenuItem_1_4_2;
	JTextArea returnresulttxt=new JTextArea();
	JButton backbtn = new JButton("《 뒤로가기");
	JButton resetbtn = new JButton("초기화");
	JTextArea srchtxt = new JTextArea();
    JTextField torepair;
    JTextArea grgtxt = new JTextArea();
    private JTextField grgid;
    private JTextField rlog;
    private JTextField rfixdate;
    private JTextField rprice;
    private JTextField rduedate;
    private JTextField rotherinfo;
    
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					admin frame = new admin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	// SQL 연결
	static Connection con;
	   Statement stmt,stmt1,stmt2,stmt3,stmt4;
	   ResultSet rs,rs2,rs4;
	   String Driver="";
	   String url="jdbc:mysql://localhost:3306/madang?&serverTimezone=Asia/Seoul&useSSL=false"; 
	   String userid="madang";
	   String pwd="madang";
	   
	   
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
	public void returnresult(){
		try {
			returnresulttxt.setText("앞쪽 \t 오른쪽 \t 왼쪽 \t 뒤쪽 \t 수리여부 \t 캠핑카ID \t 고유대여ID\n");
			stmt = con.createStatement();
			String query="SELECT * FROM campingcar_return;";
			rs = stmt.executeQuery(query);
             while(rs.next()) {
                String str = rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4)
                +  "\t" + rs.getString(5)+ "\t" + rs.getString(6)+"\t" + rs.getString(7)+"\n";
                returnresulttxt.append(str);
             }
		}catch(Exception e1) {
			System.out.println(e1);
		}
	}
	
	JMenuItem cpcregist;
	JMenuItem cpcedit;
	JMenuItem cpcdelete;
	
	JMenuItem cpcregist_1;
	JMenuItem cpcedit_1;
	JMenuItem cpcdelete_1;
	
	JMenuItem cpcregist_2;
	JMenuItem cpcedit_2;
	JMenuItem cpcdelete_2;
	public admin() {
		setTitle("17013152 최종혁 -관리자페이지");
		conDB();
		returnresult();
		grgresult();
		
		
		delform = new Companydata();
		delform.quit.addActionListener(this);
		
		campform = new Campingcardata();
		campform.quit.addActionListener(this);
		
		cusform = new Customerdata();
		cusform.quit.addActionListener(this);
		
		grgform = new Garagedata();
		grgform.quit.addActionListener(this);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1094, 565);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorderPainted(false);
		menuBar.setAlignmentY(Component.CENTER_ALIGNMENT);
		setJMenuBar(menuBar);
		//대여회사---------------------------------------------------------------------
		JMenu mnNewMenu = new JMenu("대여회사");
		mnNewMenu.setHorizontalTextPosition(SwingConstants.CENTER);
		mnNewMenu.setHorizontalAlignment(SwingConstants.CENTER);
		mnNewMenu.setIconTextGap(60);
		menuBar.add(mnNewMenu);
		
		mntmNewMenuItem_1_4 = new JMenuItem("등록");
		mntmNewMenuItem_1_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				delform.setVisible(true);
			}
		});
		mntmNewMenuItem_1_4.setMargin(new Insets(0, 30, 0, 0));
		mntmNewMenuItem_1_4.setPreferredSize(new Dimension(150, 30));
		mntmNewMenuItem_1_4.setHorizontalTextPosition(SwingConstants.CENTER);
		mntmNewMenuItem_1_4.setHorizontalAlignment(SwingConstants.CENTER);
		mnNewMenu.add(mntmNewMenuItem_1_4);
		//---------------------------------------------------------------------
		mntmNewMenuItem_1_4_1 = new JMenuItem("수정");
		mntmNewMenuItem_1_4_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				delform.setVisible(true);
			}
		});
		mntmNewMenuItem_1_4_1.setPreferredSize(new Dimension(150, 30));
		mntmNewMenuItem_1_4_1.setMargin(new Insets(0, 30, 0, 0));
		mntmNewMenuItem_1_4_1.setHorizontalTextPosition(SwingConstants.CENTER);
		mntmNewMenuItem_1_4_1.setHorizontalAlignment(SwingConstants.CENTER);
		mnNewMenu.add(mntmNewMenuItem_1_4_1);
		//---------------------------------------------------------------------
		
		mntmNewMenuItem_1_4_2 = new JMenuItem("삭제");
		mntmNewMenuItem_1_4_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				delform.setVisible(true);
			}
		});
		mntmNewMenuItem_1_4_2.setPreferredSize(new Dimension(150, 30));
		mntmNewMenuItem_1_4_2.setMargin(new Insets(0, 30, 0, 0));
		mntmNewMenuItem_1_4_2.setHorizontalTextPosition(SwingConstants.CENTER);
		mntmNewMenuItem_1_4_2.setHorizontalAlignment(SwingConstants.CENTER);
		mnNewMenu.add(mntmNewMenuItem_1_4_2);
		//대여회사---------------------------------------------------------------------
		JMenu mnNewMenu_1 = new JMenu("캠핑카");
		mnNewMenu_1.setIconTextGap(55);
		mnNewMenu_1.setHorizontalTextPosition(SwingConstants.CENTER);
		mnNewMenu_1.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar.add(mnNewMenu_1);
		
		cpcregist = new JMenuItem("등록");
		cpcregist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				campform.setVisible(true);
			}
		});
		cpcregist.setPreferredSize(new Dimension(150, 30));
		cpcregist.setMargin(new Insets(0, 30, 0, 0));
		cpcregist.setHorizontalTextPosition(SwingConstants.CENTER);
		cpcregist.setHorizontalAlignment(SwingConstants.CENTER);
		mnNewMenu_1.add(cpcregist);
		
		cpcedit = new JMenuItem("수정");
		cpcedit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				campform.setVisible(true);
			}
		});
		cpcedit.setPreferredSize(new Dimension(150, 30));
		cpcedit.setMargin(new Insets(0, 30, 0, 0));
		cpcedit.setHorizontalTextPosition(SwingConstants.CENTER);
		cpcedit.setHorizontalAlignment(SwingConstants.CENTER);
		mnNewMenu_1.add(cpcedit);
		
		cpcdelete = new JMenuItem("삭제");
		cpcdelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				campform.setVisible(true);
			}
		});
		cpcdelete.setPreferredSize(new Dimension(150, 30));
		cpcdelete.setMargin(new Insets(0, 30, 0, 0));
		cpcdelete.setHorizontalTextPosition(SwingConstants.CENTER);
		cpcdelete.setHorizontalAlignment(SwingConstants.CENTER);
		mnNewMenu_1.add(cpcdelete);
		//캠핑카---------------------------------------------------------------------
		JMenu mnNewMenu_2 = new JMenu("고객");
		mnNewMenu_2.setIconTextGap(55);
		mnNewMenu_2.setHorizontalTextPosition(SwingConstants.CENTER);
		mnNewMenu_2.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar.add(mnNewMenu_2);
		
		cpcregist_1 = new JMenuItem("등록");
		cpcregist_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				cusform.setVisible(true);
			}
		});
		
		cpcregist_1.setPreferredSize(new Dimension(150, 30));
		cpcregist_1.setMargin(new Insets(0, 30, 0, 0));
		cpcregist_1.setHorizontalTextPosition(SwingConstants.CENTER);
		cpcregist_1.setHorizontalAlignment(SwingConstants.CENTER);
		mnNewMenu_2.add(cpcregist_1);
		
		cpcedit_1 = new JMenuItem("수정");
		cpcedit_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				cusform.setVisible(true);
			}
		});
		cpcedit_1.setPreferredSize(new Dimension(150, 30));
		cpcedit_1.setMargin(new Insets(0, 30, 0, 0));
		cpcedit_1.setHorizontalTextPosition(SwingConstants.CENTER);
		cpcedit_1.setHorizontalAlignment(SwingConstants.CENTER);
		mnNewMenu_2.add(cpcedit_1);
		cpcdelete_1 = new JMenuItem("삭제");
		cpcdelete_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				cusform.setVisible(true);
			}
		});
		cpcdelete_1.setPreferredSize(new Dimension(150, 30));
		cpcdelete_1.setMargin(new Insets(0, 30, 0, 0));
		cpcdelete_1.setHorizontalTextPosition(SwingConstants.CENTER);
		cpcdelete_1.setHorizontalAlignment(SwingConstants.CENTER);
		mnNewMenu_2.add(cpcdelete_1);
		//고객---------------------------------------------------------------------
		
		JMenu mnNewMenu_3 = new JMenu("정비소");
		mnNewMenu_3.setIconTextGap(55);
		mnNewMenu_3.setHorizontalTextPosition(SwingConstants.CENTER);
		mnNewMenu_3.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar.add(mnNewMenu_3);
		
		cpcregist_2 = new JMenuItem("등록");
		cpcregist_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				grgform.setVisible(true);
			}
		});
		
		cpcregist_2.setPreferredSize(new Dimension(150, 30));
		cpcregist_2.setMargin(new Insets(0, 30, 0, 0));
		cpcregist_2.setHorizontalTextPosition(SwingConstants.CENTER);
		cpcregist_2.setHorizontalAlignment(SwingConstants.CENTER);
		mnNewMenu_3.add(cpcregist_2);
		
		cpcedit_2 = new JMenuItem("수정");
		cpcedit_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				grgform.setVisible(true);
			}
		});
		cpcedit_2.setPreferredSize(new Dimension(150, 30));
		cpcedit_2.setMargin(new Insets(0, 30, 0, 0));
		cpcedit_2.setHorizontalTextPosition(SwingConstants.CENTER);
		cpcedit_2.setHorizontalAlignment(SwingConstants.CENTER);
		mnNewMenu_3.add(cpcedit_2);
		
		cpcdelete_2 = new JMenuItem("삭제");
		cpcdelete_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				grgform.setVisible(true);
			}
		});
		cpcdelete_2.setPreferredSize(new Dimension(150, 30));
		cpcdelete_2.setMargin(new Insets(0, 30, 0, 0));
		cpcdelete_2.setHorizontalTextPosition(SwingConstants.CENTER);
		cpcdelete_2.setHorizontalAlignment(SwingConstants.CENTER);
		mnNewMenu_3.add(cpcdelete_2);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		JButton grgbtn = new JButton("정비소로보내기");
		grgbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String r_log = rlog.getText();
				String r_date = rfixdate.getText();
				String r_price = rprice.getText();
				String r_duedate = rduedate.getText();
				String r_otherinfo = rotherinfo.getText();
				String lisenceid = null;
				String companyid = null;
				String grg_id = grgid.getText();
				String cpcid = torepair.getText();
				String fixtest = null;
				String rent_id=null;
				try {
					stmt = con.createStatement();
					String query = "select fix, custom_rent_list_id from campingcar_return where campingcar_list_id='"+cpcid+"'";
					rs = stmt.executeQuery(query);
					if(rs.next()) {
						fixtest = rs.getString(1);
						rent_id = rs.getString(2);
					}
					
					
					stmt2 = con.createStatement();
					String query2 = "select c_license_id,campingcar_company_id from customer_rent_old_list where rent_id='"+rent_id+"';";
					rs2 = stmt2.executeQuery(query2);
					if(rs2.next()) {
						lisenceid = rs2.getString(1);
						companyid = rs2.getString(2);
					}
					
					
					if(fixtest.equals("1")) { //수리할때
						stmt3 = con.createStatement();
						String query3 = "insert into repair_list(r_log,r_date,r_price,r_due_date,r_other_repair,customer_license_id,campingcar_rent_company_id,garage_id,campingcar_list_id) "
								+ "values('"+r_log+"','"+r_date+"','"+r_price+"','"+r_duedate+"','"+r_otherinfo+"','"+lisenceid+"','"+companyid+"','"+grg_id+"','"+cpcid+"');";
						int result = stmt3.executeUpdate(query3);
						
						stmt4 = con.createStatement();
						String query4="DELETE FROM campingcar_return WHERE campingcar_list_id = '"+cpcid+"'";
						int result4 = stmt4.executeUpdate(query4);
						if(result == 1 && result4==1) {
							JOptionPane.showMessageDialog(grgbtn, "수리처리 완료되었습니다.");
							datareset();
							returnresult();
							datareset();
						}
					}else if(fixtest.equals("0")) {//수리필요없을때
						JOptionPane.showMessageDialog(grgbtn, "수리할필요없습니다. 반환하세요.");
					}
					
					
				}catch(Exception e1) {
					if(r_log.length()==0||r_date.length()==0||r_price.length()==0||
							r_duedate.length()==0||r_otherinfo.length()==0||grg_id.length()==0||cpcid.length()==0) {
						JOptionPane.showMessageDialog(grgbtn, "빈칸을 모두 채워주세요!");
					}
					//System.out.println("여기임?"+e1);
				}
				
				
			}
		});
		grgbtn.setFont(new Font("굴림", Font.BOLD, 15));
		grgbtn.setBounds(889, 315, 142, 77);
		panel.add(grgbtn);
		JButton toreturnbtn = new JButton("반 환 하 기");
		toreturnbtn.setFont(new Font("굴림", Font.BOLD, 18));
		toreturnbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cpcid = torepair.getText();
				try {
				stmt = con.createStatement();
				stmt2 = con.createStatement();
				
				stmt4 = con.createStatement();
				String fixtest = null;
				stmt4 = con.createStatement();
				String query4 = "select fix from campingcar_return where campingcar_list_id='"+cpcid+"'";
				rs4 = stmt4.executeQuery(query4);
				if(rs4.next()) {fixtest = rs4.getString(1);}		
				
				
				String query="select * from campingcar_list where campingcar_list_id='"+cpcid+"'";
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
	            		  JOptionPane.showMessageDialog(toreturnbtn, "수리가필요한 캠핑카입니다."); 
	            	  }else if(fixtest.equals("0")) {//반환하기!
	            		  
	            		  stmt3 = con.createStatement();
	            		  String query2="insert into rentcar_list values"
	            				  + "('"+cpcid+"','"+cc_name+"','"+cc_number+"','"+cc_sits+"',"
  	             				+ "'"+cc_manufacutre+"','"+cc_manufacture_year+"','"+
  	             				cc_mileage+"','"+cc_rent_price+"','"+cc_regitst_date+"','"+
  	             				campingcar_rent_company_id+"');";
	      	 			  int result = stmt2.executeUpdate(query2);
	      	 			
	      	 			
	      				String query3="DELETE FROM campingcar_return WHERE campingcar_list_id = '"+cpcid+"'";
	      				int result3 = stmt3.executeUpdate(query3);
	      				
	      				JOptionPane.showMessageDialog(toreturnbtn, "반환 완료!"); 
	            		  returnresult();	            		  
	            	  }
				}catch(Exception e1) {
					if(cpcid.length()==0) {
						JOptionPane.showMessageDialog(grgbtn, "캠핑카ID를 입력해주세요!");
					}
					//System.out.println(e1);
				}
			}
		});
		toreturnbtn.setBounds(889, 402, 142, 77);
		panel.add(toreturnbtn);
		
		JLabel lblNewLabel = new JLabel("\uBC18\uD658 \uB0B4\uC5ED");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 20));
		lblNewLabel.setBounds(12, 10, 110, 21);
		panel.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 41, 586, 200);
		panel.add(scrollPane);
		
		
		scrollPane.setViewportView(returnresulttxt);
		
		JLabel lblNewLabel_1 = new JLabel();
		lblNewLabel_1.setFont(new Font("굴림", Font.BOLD, 20));
		lblNewLabel_1.setBounds(12, 210, 110, 21);
		panel.add(lblNewLabel_1);
		
		torepair = new JTextField();
		torepair.setBounds(727, 315, 150, 21);
		panel.add(torepair);
		torepair.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("\uAC80\uC0C9");
		lblNewLabel_2.setFont(new Font("굴림", Font.BOLD, 20));
		lblNewLabel_2.setBounds(614, 10, 110, 21);
		panel.add(lblNewLabel_2);
		
		
		
		srchtxt.setBounds(614, 138, 446, 101);
		panel.add(srchtxt);
		
		JLabel lblNewLabel_3 = new JLabel("\uCEA0\uD551\uCE74\uC815\uBE44\uC18C \uC120\uD0DD");
		lblNewLabel_3.setFont(new Font("굴림", Font.BOLD, 20));
		lblNewLabel_3.setBounds(12, 251, 193, 21);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_1_5 = new JLabel("\uC815\uBE44\uC18CID");
		lblNewLabel_1_5.setFont(new Font("굴림", Font.BOLD, 15));
		lblNewLabel_1_5.setBounds(614, 342, 73, 21);
		panel.add(lblNewLabel_1_5);
		
		grgid = new JTextField();
		grgid.setColumns(10);
		grgid.setBounds(727, 342, 150, 21);
		panel.add(grgid);
		
		JLabel lblNewLabel_1_1 = new JLabel("\uC815\uBE44\uB0B4\uC5ED");
		lblNewLabel_1_1.setFont(new Font("굴림", Font.BOLD, 15));
		lblNewLabel_1_1.setBounds(614, 364, 73, 21);
		panel.add(lblNewLabel_1_1);
		
		rlog = new JTextField();
		rlog.setColumns(10);
		rlog.setBounds(727, 366, 150, 21);
		panel.add(rlog);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("\uC218\uB9AC\uB0A0\uC9DC");
		lblNewLabel_1_1_1.setFont(new Font("굴림", Font.BOLD, 15));
		lblNewLabel_1_1_1.setBounds(614, 389, 73, 21);
		panel.add(lblNewLabel_1_1_1);
		
		rfixdate = new JTextField();
		rfixdate.setColumns(10);
		rfixdate.setBounds(727, 389, 150, 21);
		panel.add(rfixdate);
		
		JLabel lblNewLabel_1_2 = new JLabel("\uC218\uB9AC\uBE44\uC6A9");
		lblNewLabel_1_2.setFont(new Font("굴림", Font.BOLD, 15));
		lblNewLabel_1_2.setBounds(614, 413, 73, 21);
		panel.add(lblNewLabel_1_2);
		
		rprice = new JTextField();
		rprice.setColumns(10);
		rprice.setBounds(727, 413, 150, 21);
		panel.add(rprice);
		
		JLabel lblNewLabel_1_3 = new JLabel("\uB0A9\uC785\uAE30\uD55C");
		lblNewLabel_1_3.setFont(new Font("굴림", Font.BOLD, 15));
		lblNewLabel_1_3.setBounds(614, 437, 73, 21);
		panel.add(lblNewLabel_1_3);
		
		rduedate = new JTextField();
		rduedate.setColumns(10);
		rduedate.setBounds(727, 437, 150, 21);
		panel.add(rduedate);
		
		JLabel lblNewLabel_1_4 = new JLabel("\uAE30\uD0C0\uB0B4\uC5ED\uC815\uBCF4");
		lblNewLabel_1_4.setFont(new Font("굴림", Font.BOLD, 15));
		lblNewLabel_1_4.setBounds(614, 461, 101, 21);
		panel.add(lblNewLabel_1_4);
		
		rotherinfo = new JTextField();
		rotherinfo.setColumns(10);
		rotherinfo.setBounds(727, 461, 150, 21);
		panel.add(rotherinfo);
		
		
		
		JLabel lblNewLabel_1_5_1 = new JLabel("캠핑카ID");
		lblNewLabel_1_5_1.setFont(new Font("굴림", Font.BOLD, 15));
		lblNewLabel_1_5_1.setBounds(614, 318, 88, 21);
		panel.add(lblNewLabel_1_5_1);
		
		JLabel lblNewLabel_4 = new JLabel("수리여부 1 : 수리필요");
		lblNewLabel_4.setFont(new Font("굴림", Font.BOLD, 14));
		lblNewLabel_4.setBounds(113, 12, 142, 21);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("※ 수리여부가 1인 캠핑카의 고유대여ID와 정비소ID를 입력 후  추가정보를 입력");
		lblNewLabel_5.setBounds(614, 279, 446, 26);
		panel.add(lblNewLabel_5);
		
		
		
		JButton btn1 = new JButton("검색1");
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				srchtxt.setText("검색1 결과\n");
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
						srchtxt.append("\n");
					}
					str=count+". " + rs.getString(1) + "\t";
					srchtxt.append(str);
					count++;
					}
				}catch(Exception e1) {
					System.out.println(e1);
				}
			}
		});
		btn1.setBounds(955, 26, 105, 23);
		panel.add(btn1);
		
		JButton btn2 = new JButton("검색2");
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				srchtxt.setText("검색2 결과\n");
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
						srchtxt.append("\n");
					}
						str=count+". " + rs.getString(1) + "\t";
						srchtxt.append(str);
						count++;
					
					}
				}catch(Exception e1) {
					System.out.println(e1);
				}
			}
		});
		
		JButton btn3 = new JButton("검색3");
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				srchtxt.setText("검색3 결과\n");
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
						srchtxt.append("\n");
					}
					str=count+". " + rs.getString(1) + "\t";
					srchtxt.append(str);
					count++;
					}
				}catch(Exception e1) {
					System.out.println(e1);
				}
			}
		});
		btn3.setBounds(955, 79, 105, 23);
		panel.add(btn3);
		
		JButton bnt4 = new JButton("검색4");
		bnt4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				srchtxt.setText("검색4 결과\n");
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
						srchtxt.append("\n");
					}
					str=count+". " + rs.getString(1) + "\t";
					srchtxt.append(str);
					count++;
					}
				}catch(Exception e1) {
					System.out.println(e1);
				}
			}
		});
		
		bnt4.setBounds(955, 105, 105, 23);
		panel.add(bnt4);
		btn2.setBounds(955, 53, 105, 23);
		panel.add(btn2);
		
		JLabel lblNewLabel_7 = new JLabel("1.   \uC218\uB9AC\uBAA9\uB85D \uC911\uC5D0\uC11C 10\uB9CC\uC6D0\uC774\uC0C1 \uB0B4\uC5ED\uC774 \uB098\uC628 \uACE0\uAC1D\uC774\uB984");
		lblNewLabel_7.setBounds(614, 30, 321, 15);
		panel.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("2.   캠핑카의 청구요금이 50만원 이상 지불해야 할 고객이름");
		lblNewLabel_8.setBounds(614, 57, 329, 15);
		panel.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("3.   수리할 필요가 없는, 수리필요여부가 1인 캠핑카");
		lblNewLabel_9.setBounds(614, 83, 329, 15);
		panel.add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("4.   대여기간이 10일이상인 고객이름");
		lblNewLabel_10.setBounds(614, 109, 321, 15);
		panel.add(lblNewLabel_10);
		
		JLabel lblNewLabel_4_1 = new JLabel("수리여부 0 : 수리필요없음");
		lblNewLabel_4_1.setFont(new Font("굴림", Font.BOLD, 14));
		lblNewLabel_4_1.setBounds(267, 13, 171, 18);
		panel.add(lblNewLabel_4_1);
		resetbtn.setForeground(Color.WHITE);
		resetbtn.setBounds(450, 13, 51, 19);
		panel.add(resetbtn);
		resetbtn.setBorder(new CompoundBorder());
		resetbtn.setBackground(new Color(205, 133, 63));
		backbtn.setForeground(Color.WHITE);
		backbtn.setBounds(513, 13, 66, 19);
		panel.add(backbtn);
		backbtn.setBorder(new CompoundBorder());
		backbtn.setBackground(new Color(205, 133, 63));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 282, 586, 203);
		panel.add(scrollPane_1);
		
		
		scrollPane_1.setViewportView(grgtxt);
		
		
		resetbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					stmt1 = con.createStatement();
					String query="DROP SCHEMA IF EXISTS `madang` ;";
					stmt1.executeUpdate(query);
					
					
					stmt1 = con.createStatement();
					query="CREATE SCHEMA IF NOT EXISTS `madang` DEFAULT CHARACTER SET utf8 ;";
					stmt1.executeUpdate(query);
					
					stmt1 = con.createStatement();
					query="USE `madang`;";
					stmt1.executeUpdate(query);
					
					stmt1 = con.createStatement();
	                query="DROP TABLE IF EXISTS `customer_rent_old_list`;";
	                 stmt1.executeUpdate(query);
	                 query="DROP TABLE IF EXISTS `rentcar_list`;";
	                 stmt1.executeUpdate(query);
	                 query="DROP TABLE IF EXISTS `campingcar_return`;";
	                 stmt1.executeUpdate(query);
	                 query="DROP TABLE IF EXISTS `repair_list`;";
	                 stmt1.executeUpdate(query);
	                 query="DROP TABLE IF EXISTS `garage`;";
	                 stmt1.executeUpdate(query);
	                 query="DROP TABLE IF EXISTS `customer_rent_list`;";
	                 stmt1.executeUpdate(query);
	                 query="DROP TABLE IF EXISTS `campingcar_list`;";
	                 stmt1.executeUpdate(query);
	                 query="DROP TABLE IF EXISTS `customer`;"; 
	                 stmt1.executeUpdate(query);
	                 query="DROP TABLE IF EXISTS `campingcar_rent_company`;";
	                 stmt1.executeUpdate(query);
	                 //   System.out.println("삭제완료!");
	                    
	               query="CREATE TABLE IF NOT EXISTS `campingcar_rent_company` (\r\n" + 
	               		"  `camping_rent_company_id` INT NOT NULL AUTO_INCREMENT,\r\n" + 
	               		"  `cp_name` VARCHAR(45) NOT NULL,\r\n" + 
	               		"  `cp_address` VARCHAR(45) NOT NULL,\r\n" + 
	               		"  `cp_number` VARCHAR(45) NOT NULL,\r\n" + 
	               		"  `cp_mng_email` VARCHAR(45) NOT NULL,\r\n" + 
	               		"  `cp_mng_name` VARCHAR(45) NOT NULL,\r\n" + 
	               		"  PRIMARY KEY (`camping_rent_company_id`))";
	               stmt1.executeUpdate(query);
	               
	               query="CREATE TABLE IF NOT EXISTS `customer` (\r\n" + 
	               		"  `license_id` INT NOT NULL,\r\n" + 
	               		"  `c_name` VARCHAR(45) NOT NULL,\r\n" + 
	               		"  `c_address` VARCHAR(45) NOT NULL,\r\n" + 
	               		"  `c_number` VARCHAR(45) NOT NULL,\r\n" + 
	               		"  `c_email` VARCHAR(100) NOT NULL,\r\n" + 
	               		"  PRIMARY KEY (`license_id`))";
	               stmt1.executeUpdate(query);
	               
	               query="CREATE TABLE IF NOT EXISTS `campingcar_list` (\r\n" + 
	               		"  `campingcar_list_id` INT NOT NULL AUTO_INCREMENT,\r\n" + 
	               		"  `cc_name` VARCHAR(45) NOT NULL,\r\n" + 
	               		"  `cc_number` VARCHAR(45) NOT NULL,\r\n" + 
	               		"  `cc_sits` INT NOT NULL,\r\n" + 
	               		"  `cc_manufacture` VARCHAR(45) NOT NULL,\r\n" + 
	               		"  `cc_manufacture_year` INT NOT NULL,\r\n" + 
	               		"  `cc_mileage` INT NOT NULL,\r\n" + 
	               		"  `cc_rent_price` INT NOT NULL,\r\n" + 
	               		"  `cc_regist_date` DATE NOT NULL,\r\n" + 
	               		"  `campingcar_rent_company_id` INT NOT NULL,\r\n" + 
	               		"  PRIMARY KEY (`campingcar_list_id`),\r\n" + 
	               		"  INDEX `fk_campingcar_list_campingcar_rent_company1_idx` (`campingcar_rent_company_id` ASC) VISIBLE,\r\n" + 
	               		"  CONSTRAINT `fk_campingcar_list_campingcar_rent_company1`\r\n" + 
	               		"    FOREIGN KEY (`campingcar_rent_company_id`)\r\n" + 
	               		"    REFERENCES `campingcar_rent_company` (`camping_rent_company_id`)\r\n" + 
	               		"    ON DELETE CASCADE\r\n" + 
	               		"    ON UPDATE NO ACTION)";
	               stmt1.executeUpdate(query);
	               
	               query="CREATE TABLE IF NOT EXISTS `customer_rent_list` (\r\n" + 
	               		"  `rent_id` INT NOT NULL AUTO_INCREMENT,\r\n" + 
	               		"  `start_date` DATE NOT NULL,\r\n" + 
	               		"  `rent_time` INT NOT NULL,\r\n" + 
	               		"  `due_date` DATE NOT NULL,\r\n" + 
	               		"  `otherthing` VARCHAR(45) NOT NULL,\r\n" + 
	               		"  `others_price` INT NOT NULL,\r\n" + 
	               		"  `campingcar_company_id` INT NOT NULL,\r\n" + 
	               		"  `c_license_id` INT NOT NULL,\r\n" + 
	               		"  `campingcar_id` INT NOT NULL,\r\n" + 
	               		"  `cc_price` INT NOT NULL,\r\n" + 
	               		"  PRIMARY KEY (`rent_id`),\r\n" + 
	               		"  INDEX `fk_customer rent list_customer1_idx` (`c_license_id` ASC) VISIBLE,\r\n" + 
	               		"  INDEX `fk_customer rent list_campingcar rent company1_idx` (`campingcar_company_id` ASC) VISIBLE,\r\n" + 
	               		"  INDEX `fk_customer_rent_list_campingcar_list1_idx` (`campingcar_id` ASC) VISIBLE,\r\n" + 
	               		"  CONSTRAINT `fk_customer rent list_customer1`\r\n" + 
	               		"    FOREIGN KEY (`c_license_id`)\r\n" + 
	               		"    REFERENCES `customer` (`license_id`)\r\n" + 
	               		"    ON DELETE CASCADE\r\n" + 
	               		"    ON UPDATE NO ACTION,\r\n" + 
	               		"  CONSTRAINT `fk_customer rent list_campingcar rent company1`\r\n" + 
	               		"    FOREIGN KEY (`campingcar_company_id`)\r\n" + 
	               		"    REFERENCES `campingcar_rent_company` (`camping_rent_company_id`)\r\n" + 
	               		"    ON DELETE CASCADE\r\n" + 
	               		"    ON UPDATE NO ACTION,\r\n" + 
	               		"  CONSTRAINT `fk_customer_rent_list_campingcar_list1`\r\n" + 
	               		"    FOREIGN KEY (`campingcar_id`)\r\n" + 
	               		"    REFERENCES `campingcar_list` (`campingcar_list_id`)\r\n" + 
	               		"    ON DELETE CASCADE\r\n" + 
	               		"    ON UPDATE NO ACTION)";
	               stmt1.executeUpdate(query);
	               
	               query="CREATE TABLE IF NOT EXISTS `garage` (" + 
	                     "  `garage_id` INT NOT NULL AUTO_INCREMENT," + 
	                     "  `g_name` VARCHAR(45) NOT NULL," + 
	                     "  `g_address` VARCHAR(45) NOT NULL," + 
	                     "  `g_number` VARCHAR(45) NOT NULL," + 
	                     "  `g_manager` VARCHAR(45) NOT NULL," + 
	                     "  `g_email` VARCHAR(100) NOT NULL," + 
	                     "  PRIMARY KEY (`garage_id`))";
	               stmt1.executeUpdate(query);
	               
	               query="CREATE TABLE IF NOT EXISTS `repair_list` (\r\n" + 
	               		"  `repair list_id` INT NOT NULL AUTO_INCREMENT,\r\n" + 
	               		"  `r_log` VARCHAR(45) NOT NULL,\r\n" + 
	               		"  `r_date` DATE NOT NULL,\r\n" + 
	               		"  `r_price` INT NOT NULL,\r\n" + 
	               		"  `r_due_date` DATE NOT NULL,\r\n" + 
	               		"  `r_other_repair` VARCHAR(45) NOT NULL,\r\n" + 
	               		"  `customer_license_id` INT NOT NULL,\r\n" + 
	               		"  `campingcar_rent_company_id` INT NOT NULL,\r\n" + 
	               		"  `garage_id` INT NOT NULL,\r\n" + 
	               		"  `campingcar_list_id` INT NOT NULL,\r\n" + 
	               		"  PRIMARY KEY (`repair list_id`),\r\n" + 
	               		"  INDEX `fk_repair list_customer1_idx` (`customer_license_id` ASC) VISIBLE,\r\n" + 
	               		"  INDEX `fk_repair list_campingcar rent company1_idx` (`campingcar_rent_company_id` ASC) VISIBLE,\r\n" + 
	               		"  INDEX `fk_repair list_garage1_idx` (`garage_id` ASC) VISIBLE,\r\n" + 
	               		"  CONSTRAINT `fk_repair list_customer1`\r\n" + 
	               		"    FOREIGN KEY (`customer_license_id`)\r\n" + 
	               		"    REFERENCES `customer` (`license_id`)\r\n" + 
	               		"    ON DELETE CASCADE\r\n" + 
	               		"    ON UPDATE NO ACTION,\r\n" + 
	               		"  CONSTRAINT `fk_repair list_campingcar rent company1`\r\n" + 
	               		"    FOREIGN KEY (`campingcar_rent_company_id`)\r\n" + 
	               		"    REFERENCES `campingcar_rent_company` (`camping_rent_company_id`)\r\n" + 
	               		"    ON DELETE CASCADE\r\n" + 
	               		"    ON UPDATE NO ACTION,\r\n" + 
	               		"  CONSTRAINT `fk_repair list_garage1`\r\n" + 
	               		"    FOREIGN KEY (`garage_id`)\r\n" + 
	               		"    REFERENCES `garage` (`garage_id`)\r\n" + 
	               		"    ON DELETE CASCADE\r\n" + 
	               		"    ON UPDATE NO ACTION)";
	               stmt1.executeUpdate(query);
	               
	               query="CREATE TABLE IF NOT EXISTS `campingcar_return` (\r\n" + 
	               		"  `front` VARCHAR(100) NOT NULL,\r\n" + 
	               		"  `right` VARCHAR(100) NOT NULL,\r\n" + 
	               		"  `left` VARCHAR(100) NOT NULL,\r\n" + 
	               		"  `back` VARCHAR(100) NOT NULL,\r\n" + 
	               		"  `fix` INT NOT NULL,\r\n" + 
	               		"  `campingcar_list_id` INT NOT NULL,\r\n" + 
	               		"  `custom_rent_list_id` INT NOT NULL,\r\n" + 
	               		"  INDEX `fk_campingcar return_campingcar list1_idx` (`campingcar_list_id` ASC) VISIBLE,\r\n" + 
	               		"  UNIQUE INDEX `custom_rent_list_id_UNIQUE` (`custom_rent_list_id` ASC) VISIBLE,\r\n" + 
	               		"  CONSTRAINT `fk_campingcar return_campingcar list1`\r\n" + 
	               		"    FOREIGN KEY (`campingcar_list_id`)\r\n" + 
	               		"    REFERENCES `campingcar_list` (`campingcar_list_id`)\r\n" + 
	               		"    ON DELETE CASCADE\r\n" + 
	               		"    ON UPDATE NO ACTION)";
	               stmt1.executeUpdate(query);
	               
	               query="CREATE TABLE IF NOT EXISTS `rentcar_list` (\r\n" + 
	               		"  `rent_id` INT NOT NULL,\r\n" + 
	               		"  `cc_name` VARCHAR(45) NOT NULL,\r\n" + 
	               		"  `cc_number` VARCHAR(45) NOT NULL,\r\n" + 
	               		"  `cc_sits` INT NOT NULL,\r\n" + 
	               		"  `cc_manufacture` VARCHAR(45) NOT NULL,\r\n" + 
	               		"  `cc_manufacture_year` INT NOT NULL,\r\n" + 
	               		"  `cc_mileage` INT NOT NULL,\r\n" + 
	               		"  `cc_rent_price` INT NULL,\r\n" + 
	               		"  `cc_regist_date` DATE NOT NULL,\r\n" + 
	               		"  `camping_rent_company_id` INT NOT NULL,\r\n" + 
	               		"  PRIMARY KEY (`rent_id`),\r\n" + 
	               		"  INDEX `fk_rentcar_list_campingcar_rent_company1_idx` (`camping_rent_company_id` ASC) VISIBLE,\r\n" + 
	               		"  CONSTRAINT `fk_rentcar_list_campingcar_rent_company1`\r\n" + 
	               		"    FOREIGN KEY (`camping_rent_company_id`)\r\n" + 
	               		"    REFERENCES `campingcar_rent_company` (`camping_rent_company_id`)\r\n" + 
	               		"    ON DELETE CASCADE\r\n" + 
	               		"    ON UPDATE NO ACTION)";
	               stmt1.executeUpdate(query);
	               
	               query="CREATE TABLE IF NOT EXISTS `customer_rent_old_list` (\r\n" + 
	               		"  `rent_id` INT NOT NULL,\r\n" + 
	               		"  `start_date` DATE NOT NULL,\r\n" + 
	               		"  `retn_time` INT NOT NULL,\r\n" + 
	               		"  `due_date` DATE NOT NULL,\r\n" + 
	               		"  `otherthing` VARCHAR(45) NOT NULL,\r\n" + 
	               		"  `others_price` INT NOT NULL,\r\n" + 
	               		"  `campingcar_company_id` INT NOT NULL,\r\n" + 
	               		"  `c_license_id` INT NOT NULL,\r\n" + 
	               		"  `campingcar_id` INT NOT NULL,\r\n" + 
	               		"  `cc_price` INT NOT NULL,\r\n" + 
	               		"  PRIMARY KEY (`rent_id`))";
	               stmt1.executeUpdate(query);
		            //System.out.println("테이블 초기화 완료");
		            
	               query="insert into campingcar_rent_company values('1','SAMSUNG','서울시 서초구','02-345-5676','samsungcprent@naver.com','김삼성'),"+
	            		 "('2','LG','서울시 강남구','02-546-5474','lgcprent@naver.com','최엘쥐'),"+
	                     "('3','HYUNDAI','서울시 강서구','02-368-7374','hyundaicprent@naver.com','강현대'),"+
	                     "('4','BOOGATI','서울시 강북구','02-485-9536','boogaticprent@naver.com','고부지'),"+
	                     "('5','SHEVORAE','서울시 용산구','02-854-9642','shevoraecprent@naver.com','쉐보레'),"+
	                     "('6','PORCHE','서울시 종로구','02-458-4736','porchecprent@naver.com','김포쉐'),"+
	                     "('7','SYOUNG','서울시 마포구','02-146-9074','sangyoungcprent@naver.com','주쌍용'),"+
	                     "('8','BMW','서울시 영등포구','02-136-9654','bmwcprent@naver.com','빔덤유'),"+
	                     "('9','GMC','경기도 성남','02-725-0526','gmccprent@naver.com','주엠시'),"+
	                     "('10','TOYOTA','서울시 동작구','02-6427-3743','toyotacprent@naver.com','토요타'),"+
	                     "('11','HONDA','서울시 관악구','02-9558-1584','hondacprent@naver.com','잠혼다'),"+
	                     "('12','KIA','서울시 영등포구','02-435-3023','kiacprent@naver.com','최키아'),"+
	                     "('13','RAMBORG','서울시 광진구','02-435-3023','ramborgcprent@naver.com','이람보'),"+
	                     "('14','SAMSUNG','서울시 종로구','02-345-5348','samsung2cprent@naver.com','최삼수'),"+
	                     "('15','HYUNDAI','서울시 강동구','02-546-2346','hyndai2cprent@naver.com','김현수');";
	                    stmt1.executeUpdate(query);
	                   
	                   query="insert into campingcar_list values('1','TIGERJK','23하 4534','8','samsung','1945','20000','30','2017-04-20','1')," + 
	                         "('2','LIONG2','45호 3453','6','LG','1975','31000','30','2019-05-20','2')," + 
	                         "('3','DOG46','33호 4245','8','HYUNDAI','2021','68000','20','2021-05-20','3')," + 
	                         "('4','CAT32','36하 8553','7','BOOGATI','2020','99000','50','2020-04-25','4')," + 
	                         "('5','PENCILMANIA','26하 4525','9','SHEVORAE','2020','300000','30','2020-01-20','5')," + 
	                         "('6','CORANDO','25하 9626','10','PORCHE','1945','294033','32','2017-04-20','6')," + 
	                         "('7','CAMPFILE','95하 4216','5','SSANGYOUNG','2015','305924','54','2017-07-20','7')," + 
	                         "('8','BOOMBA','73하 4791','8','HYUNDAI','2017','24924','43','2018-01-05','3')," + 
	                         "('9','GARGARRI','743하 5274','8','samsung','2014','12353','65','2017-03-06','1')," + 
	                         "('10','GOMAN','937하 5930','8','HYUNDAI','2015','402044','85','2017-03-10','3')," + 
	                         "('11','GOODSCORE','793하 4362','5','HYUNDAI','2017','500','12','2017-07-19','3')," + 
	                         "('12','DAMBOO','274하 6927','8','samsung','2012','7942','53','2017-04-13','1')," + 
	                         "('13','EARLYBURN','407하 2423','4','KIA','2018','13414','84','2018-06-10','13')," + 
	                         "('14','BESTY','501하 5035','8','KIA','2019','93423','894','2019-07-30','15')," + 
	                         "('15','ROLEBACK','36하 6959','10','KIA','2020','19494','15','2020-05-20','11')," +
	                         "('16','NONEBREAK','34하 2423','13','HYUNDAI','2020','19494','15','2020-06-09','3'),"+
	                         "('17','ROLEBACK','54하 6959','5','KIA','1944','19494','15','1944-05-20','13')," + 
	                         "('18','RANDY','23하 3421','6','HYUNDAI','1967','35421','45','1967-05-20','6')," + 
	                         "('19','ROMA','347하 3462','7','samsung','2020','34133','46','2020-05-20','8')," + 
	                         "('20','ROOMER','45하 5684','4','SHEVORAE','2020','31546','36','2020-05-20','7')," + 
	                         "('21','RANAD','613하 9578','7','HYUNDAI','2020','23431','38','2020-05-20','8')," + 
	                         "('22','CODEA','35하 5684','8','HYUNDAI','2020','345425','35','2020-05-20','4')," + 
	                         "('23','FEDCRT','38하 3456','9','HYUNDAI','2020','34345','83','2020-05-20','1')," + 
	                         "('24','NFRSV','84하 3458','4','KIA','2020','3533','76','2020-05-20','2')," + 
	                         "('25','EFCGE','29하 3559','6','HYUNDAI','2020','2454','34','2020-05-20','3')," + 
	                         "('26','ECC4','68하 3354','8','KIA','2020','4624','47','2020-05-20','7')," + 
	                         "('27','FEVSR5','35하 3482','9','HYUNDAI','2020','34563','26','2020-05-20','9')," + 
	                         "('28','NSXCT3','74하 4594','4','KIA','2020','23462','34','2020-05-20','9')," + 
	                         "('29','DDDDEF','28하 9563','8','HYUNDAI','2020','24624','100','2020-05-20','4')," + 
	                         "('30','EREER3','83하 2348','9','KIA','2020','134345','120','2020-05-20','7'),"+
	                         "('31','BESTCAR','83하 3231','10','KIA','2020','111311','120','2020-05-20','14')";
	                         
	                stmt1.executeUpdate(query);
	                
	                   query="insert into customer values('1111111','김마당','서울시 영등포구','010-3735-2396','uiurihappy@naver.com')," + 
	                         "('2235235','최종혁','서울시 서초구','010-9773-5873','cjh@naver.com')," + 
	                         "('3235245','이수','서울시 강남구','010-2135-1343','isu@gmail.com')," + 
	                         "('4353522','싸이','서울시 강동구','010-4787-9133','psy@nate.com')," + 
	                         "('5356356','양다일','경기도 일산','010-1377-6464','dail@naver.com')," + 
	                         "('6235565','이지금','서울시 강동구','010-5468-1381','dlwlrma@naver.com')," + 
	                         "('7245245','최원재','서울시 마포구','010-7893-5133','kidcozyboy@naver.com')," + 
	                         "('8567433','문지훈','서울시 마포구','010-4861-1669','porkcutlet@naver.com')," + 
	                         "('9467242','우원재','인천시 계양구','010-1384-8319','santakiller@gmail.com')," + 
	                         "('1023454','염현수','서울시 강북구','010-1731-9997','yumdda@naver.com')," + 
	                         "('1124635','한요한','경기도 분당구','010-3656-3961','yohans@gmail.com')," + 
	                         "('1256735','구창모','서울시 영등포구','010-4881-8386','ganggang@hanmail.net')," + 
	                         "('1323554','윤진영','서울시 마포구','010-0203-3080','clloud@naver.com')," + 
	                         "('1473565','김민경','서울시 강서구','010-8816-8479','liik@naver.com')," + 
	                         "('1484534','신동갑','서울시 영등포구','010-8403-9930','thequiet@naver.com');";
	                  stmt1.executeUpdate(query);
	                  
	                 query="insert into customer_rent_list values('1','2019-11-14','7','2019-11-11','캠핑의자','2','1','1023454','17','30')," + 
	                       "('2','2019-01-04','4','2019-01-01','바베큐그릴','4','2','1124635','18','20')," + 
	                       "('3','2019-07-12','3','2019-07-09','부탄가스','1','3','1256735','19','15')," + 
	                       "('4','2019-08-31','5','2019-08-28','일회용품','1','4','1323554','20','25')," + 
	                       "('5','2019-10-10','10','2019-10-07','바베큐그릴','4','5','1111111','21','42')," + 
	                       "('6','2019-06-14','8','2019-06-11','부탄가스','1','6','1473565','22','35')," + 
	                       "('7','2019-12-25','7','2019-12-22','숯','2','7','1484534','23','30')," + 
	                       "('8','2019-05-04','15','2019-05-01','일회용품','1','8','2235235','24','65')," + 
	                       "('9','2019-08-25','12','2019-08-22','캠핑의자','2','9','3235245','25','50')," + 
	                       "('10','2019-07-06','9','2019-07-03','천막','3','10','4353522','26','40')," + 
	                       "('11','2019-04-25','7','2019-04-22','낚시대','6','11','5356356','27','30')," + 
	                       "('12','2019-02-14','6','2019-02-11','천막','3','12','6235565','28','26')," + 
	                       "('13','2019-01-13','15','2019-01-10','일회용품','1','13','7245245','29','65')," + 
	                       "('14','2019-05-16','11','2019-05-13','캠핑의자','2','14','8567433','30','46')," + 
	                       "('15','2019-11-25','7','2019-11-22','바베큐그릴','4','15','9467242','31','30')";
	                       
	                    stmt1.executeUpdate(query);
	                    
	                    query="insert into campingcar_return values('양호','양호','양호','양호','0','30','17')," + 
	                          "('양호','양호','양호','양호','0','29','18')," + 
	                          "('양호','양호','불량','양호','1','28','19')," + 
	                          "('양호','양호','양호','양호','0','27','20')," + 
	                          "('양호','양호','불량','양호','1','26','21')," + 
	                          "('양호','양호','양호','양호','0','25','22')," + 
	                          "('불량','양호','양호','양호','1','24','23')," + 
	                          "('양호','불량','양호','양호','1','23','24')," + 
	                          "('양호','양호','양호','양호','0','22','25')," + 
	                          "('양호','양호','양호','양호','0','20','27')," + 
	                          "('양호','양호','양호','불량','1','19','28')," + 
	                          "('양호','양호','양호','양호','0','18','29')," + 
	                          "('양호','양호','양호','양호','0','17','30')," +
	                          "('양호','불량','양호','양호','1','16','31');";
	                       stmt1.executeUpdate(query);
	                       
	                    query="insert into garage values('1','정의로운카센터','서울시 양천구','010-0943-5493','정카터','justice@naver.com')," + 
	                          "('2','잘고침카센터','서울시 금천구','010-2343-5454','잘수요','sdgse@naver.com')," + 
	                          "('3','KIAPROF','서울시 구로구','010-6756-7856','이민호','sefsfsd@naver.com')," + 
	                          "('4','DENTIST','서울시 강동구','010-4536-5643','원빈','asdfsadf@naver.com')," + 
	                          "('5','AMZACAR','서울시 송파구','010-1233-4543','정우성','sdfsdf@naver.com')," + 
	                          "('6','READYCL','서울시 광진구','010-7533-2364','강남','wefwef@naver.com')," + 
	                          "('7','이지금카센터','서울시 종로구','010-4537-8543','아이유','sdvsv@naver.com')," + 
	                          "('8','서초구카센터','서울시 진구','010-3424-7345','이지원','sewewe@naver.com')," + 
	                          "('9','강서구카센터','서울시 용산구','010-7342-9774','강만월','dgasdd@naver.com')," + 
	                          "('10','흑석동카센터','서울시 성북구','010-3425-6542','최수혁','asdfsdfe@naver.com')," + 
	                          "('11','신우오카센터','서울시 덕양구','010-7934-2346','이강산','wefwefd@naver.com')," + 
	                          "('12','감고구카센터','서울시 양천구','010-9357-4935','이승배','bdadvasd@naver.com')," + 
	                          "('13','충하오카센터','서울시 서초구','010-4824-1293','이세종','sdfasdfae@naver.com')," + 
	                          "('14','감사카센터','서울시 강남구','010-6319-0372','강군무','wefwefwq@naver.com')," + 
	                          "('15','빅마마카센터','서울시 강남구','010-3589-4524','킹훈남','sdadsd@naver.com');";
	                    stmt1.executeUpdate(query);
	                    
	                    query= "insert into repair_list values('1','타이어','2019-11-25','4','2019-11-28','범퍼', '1111111', '1', '1', '342')," + 
	                          "('2','라디에이터','2019-01-11','8','2019-01-14','배터리', '2235235', '2', '2', '342')," + 
	                          "('3','클러치','2019-07-18','7','2019-07-21','운전대', '3235245', '3', '3', '45')," + 
	                          "('4','기어','2019-09-08','9','2019-09-11','차축', '4353522', '4', '4', '655')," + 
	                          "('5','소음기','2019-10-23','13','2019-10-26','추진축', '5356356', '5', '5', '956')," + 
	                          "('6','브레이크','2019-06-25','5','2019-06-28','타이어', '6235565', '6', '6', '453')," + 
	                          "('7','운전대','2020-01-04','18','2019-01-07','엑셀', '7245245', '7', '7', '457')," + 
	                          "('8','라디에이터','2019-05-22','9','2019-05-25','배기관', '8567433', '8', '8', '97')," + 
	                          "('9','발전기','2019-09-09','7','2019-09-12','배터리', '9467242', '9', '9', '78')," + 
	                          "('10','보닛','2019-07-18','11','2019-07-21','클러치', '1023454', '10', '10', '57')," + 
	                          "('11','기어','2019-05-05','13','2019-05-08','엑셀', '1124635', '11', '11', '89')," + 
	                          "('12','트렁크','2019-02-23','12','2019-02-26','소음기', '1256735', '12', '12', '56')," + 
	                          "('13','브레이크','2019-01-31','5','2019-02-03','냉각 펜', '1323554', '13', '13', '86')," + 
	                          "('14','엔진','2019-05-30','16','2019-06-03','배기관', '1473565', '14', '14', '76')," + 
	                          "('15','발전기','2019-12-05','13','2019-12-08','추진축', '1484534', '15', '15', '54');";
	                     stmt1.executeUpdate(query);
	                     
	               query="insert into rentcar_list values('1','TIGERJK','23하 4534','8','SAMSUNG','1945','20000','30','2017-04-20','1')," + 
	                         "('2','LIONG2','45호 3453','6','LG','1975','31000','30','2019-05-20','2')," + 
	                         "('3','DOG46','33호 4245','8','HYUNDAI','2021','68000','20','2021-05-20','3')," + 
	                         "('4','CAT32','36하 8553','7','BOOGATI','2020','99000','50','2020-04-25','4')," + 
	                         "('5','PENCILMANIA','26하 4525','9','SHEVORAE','2020','300000','30','2020-01-20','5')," + 
	                         "('6','CORANDO','25하 9626','10','PORCHE','1945','294033','32','2017-04-20','6')," + 
	                         "('7','CAMPFILE','95하 4216','5','SYOUNG','2015','305924','54','2017-07-20','7')," + 
	                         "('8','BOOMBA','73하 4791','8','HYUNDAI','2017','24924','43','2018-01-05','3')," + 
	                         "('9','GARGARRI','743하 5274','8','samsung','2014','12353','65','2017-03-06','1')," + 
	                         "('10','GOMAN','937하 5930','8','HYUNDAI','2015','402044','85','2017-03-10','3')," + 
	                         "('11','GOODSCORE','793하 4362','5','HYUNDAI','2017','500','12','2017-07-19','3')," + 
	                         "('12','DAMBOO','274하 6927','8','samsung','2012','7942','53','2017-04-13','1')," + 
	                         "('13','EARLYBURN','407하 2423','4','KIA','2018','13414','84','2018-06-10','12')," + 
	                         "('14','BESTY','501하 5035','8','KIA','2019','93423','894','2019-07-30','12')";

	                   stmt1.executeUpdate(query);
	               /*query ="insert into customer_rent_old_list values"+
	            		   +
	                       "('17','2019-11-25','7','2019-11-22','바베큐그릴','4','15','9467242','16','30'),"+
	                       "('18','2019-11-25','7','2019-11-22','바베큐그릴','4','15','9467242','16','30'),"+
	                       "('19','2019-11-25','7','2019-11-22','바베큐그릴','4','15','9467242','16','30'),"+
	                       "('20','2019-11-25','7','2019-11-22','바베큐그릴','4','15','9467242','16','30'),"+
	                       "('21','2019-11-25','7','2019-11-22','바베큐그릴','4','15','9467242','16','30'),"+
	                       "('22','2019-11-25','7','2019-11-22','바베큐그릴','4','15','9467242','16','30'),"+
	                       "('23','2019-11-25','7','2019-11-22','바베큐그릴','4','15','9467242','16','30'),"+
	                       "('24','2019-11-25','7','2019-11-22','바베큐그릴','4','15','9467242','16','30'),"+
	                       "('25','2019-11-25','7','2019-11-22','바베큐그릴','4','15','9467242','16','30'),"+
	                       "('26','2019-11-25','7','2019-11-22','바베큐그릴','4','15','9467242','16','30'),"+
	                       "('27','2019-11-25','7','2019-11-22','바베큐그릴','4','15','9467242','16','30'),"+
	                       "('28','2019-11-25','7','2019-11-22','바베큐그릴','4','15','9467242','16','30'),"+
	                       "('29','2019-11-25','7','2019-11-22','바베큐그릴','4','15','9467242','16','30'),"+
	                       "('30','2019-11-25','7','2019-11-22','바베큐그릴','4','15','9467242','16','30')";
	               stmt1.executeUpdate(query);	    */           
	               //System.out.println("입력완료!");
	                   JOptionPane.showMessageDialog(resetbtn, "초기화완료"); 
			            returnresult();
			            grgresult();
			            datareset();
	            }catch(Exception Insert) {
	               System.out.println("데이터 입력에 오류 발생!\n"+Insert);
	               JOptionPane.showMessageDialog(resetbtn, "초기화실패"); 
	            }
			}
		});
	}
	
	
	public void grgresult(){
		try {
			grgtxt.setText("차고지ID \t 카센터이름 \t 주소 \t 번호 \t 매니저이름 \t 이메일주소\n");
			stmt = con.createStatement();
			String query="SELECT * FROM garage;";
			rs = stmt.executeQuery(query);
             while(rs.next()) {
               String str = rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4)
                +  "\t" + rs.getString(5)+ "\t" + rs.getString(6)+"\n";
                grgtxt.append(str);
             }
		}catch(Exception e1) {
			System.out.println(e1);
		}
	}
	public void datareset() {
		rlog.setText("");
		rfixdate.setText("");
		rprice.setText("");
		rduedate.setText("");
		rotherinfo.setText("");
		grgid.setText("");
		torepair.setText("");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==delform.quit) {
			delform.setVisible(false);
			setVisible(true);
		}
		if(e.getSource()==campform.quit) {
			campform.setVisible(false);
			setVisible(true);
		}
		if(e.getSource()==cusform.quit) {
			cusform.setVisible(false);
			setVisible(true);
		}
		if(e.getSource()==grgform.quit) {
			grgform.setVisible(false);
			setVisible(true);
			grgresult();
		}
		
	}
}
