package view;
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

public class AdminView extends JFrame implements ActionListener{
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminView frame = new AdminView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private JPanel contentPane;
	private JTextField torepair, grgid, rlog,rfixdate,rprice,rduedate,rotherinfo;
	
	DataView_Company Companyform;
	DataView_Campingcar Campingcarform;
	DataView_Customer Customerform;
	DataView_Garage Garageform;
	
	JTextArea returnresulttxt=new JTextArea();
	JTextArea srchtxt = new JTextArea();
    JTextArea grgtxt = new JTextArea();

    JButton backbtn = new JButton("《 뒤로가기");
    JButton resetbtn = new JButton("초기화");
	
	JMenuItem Menu_Company_Regist,Menu_Company_Edit,Menu_Company_Delete;
	JMenuItem Menu_Campingcar_Regist,Menu_Campingcar_Edit,Menu_Campingcar_Delete;
	JMenuItem Menu_Customer_Regist,Menu_Customer_Edit,Menu_Customer_Delete;
	JMenuItem Menu_Garage_Regist,Menu_Garage_Edit,Menu_Garage_Delete;
	
	public AdminView() {
		setTitle("캠핑카프로젝트 - 관리자페이지");
		
		Companyform = new DataView_Company();
		Companyform.quit.addActionListener(this);
		
		Campingcarform = new DataView_Campingcar();
		Campingcarform.quit.addActionListener(this);
		
		Customerform = new DataView_Customer();
		Customerform.quit.addActionListener(this);
		
		Garageform = new DataView_Garage();
		Garageform.quit.addActionListener(this);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1094, 565);
//영역 설정------------------------------------------------------------------------------------------		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("수리여부 1 : 수리필요");
		lblNewLabel_4.setFont(new Font("굴림", Font.BOLD, 14));
		lblNewLabel_4.setBounds(113, 12, 142, 21);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_4_1 = new JLabel("수리여부 0 : 수리필요없음");
		lblNewLabel_4_1.setFont(new Font("굴림", Font.BOLD, 14));
		lblNewLabel_4_1.setBounds(267, 13, 171, 18);
		panel.add(lblNewLabel_4_1);
		
		//초기화 버튼---------------------------------------------------------------------------------
		resetbtn.setForeground(Color.WHITE);
		resetbtn.setBounds(450, 13, 51, 19);
		panel.add(resetbtn);
		resetbtn.setBorder(new CompoundBorder());
		resetbtn.setBackground(new Color(205, 133, 63));
		
		//뒤로가기 버튼--------------------------------------------------------------------------------
		backbtn.setForeground(Color.WHITE);
		backbtn.setBounds(513, 13, 66, 19);
		panel.add(backbtn);
		backbtn.setBorder(new CompoundBorder());
		backbtn.setBackground(new Color(205, 133, 63));
		
		
		//반환 내역이 출력되는 필드-------------------------------------------------------------------------
		JLabel lblNewLabel = new JLabel("반환 내역");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 20));
		lblNewLabel.setBounds(12, 10, 110, 21);
		panel.add(lblNewLabel);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 41, 586, 200);
		panel.add(scrollPane);
		scrollPane.setViewportView(returnresulttxt);
		
		//캠핑카 정비소가 출력되는 필드----------------------------------------------------------------------
		JLabel lblNewLabel_3 = new JLabel("캠핑카정비소 선택");
		lblNewLabel_3.setFont(new Font("굴림", Font.BOLD, 20));
		lblNewLabel_3.setBounds(12, 251, 193, 21);
		panel.add(lblNewLabel_3);
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 282, 586, 203);
		panel.add(scrollPane_1);
		scrollPane_1.setViewportView(grgtxt);
		
//상단바 설정-----------------------------------------------------------------------------------------
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorderPainted(false);
		menuBar.setAlignmentY(Component.CENTER_ALIGNMENT);
		setJMenuBar(menuBar);
		
//------대여회사 메뉴 설정-------------------------------------------------------------------------------
		JMenu Menu_Company = new JMenu("대여회사");
		Menu_Company.setHorizontalTextPosition(SwingConstants.CENTER);
		Menu_Company.setHorizontalAlignment(SwingConstants.CENTER);
		Menu_Company.setIconTextGap(60);
		menuBar.add(Menu_Company);
		//등록---------------------------------------------------------------------
		Menu_Company_Regist = new JMenuItem("등록");
		Menu_Company_Regist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Companyform.setVisible(true);
			}
		});
		Menu_Company_Regist.setMargin(new Insets(0, 30, 0, 0));
		Menu_Company_Regist.setPreferredSize(new Dimension(150, 30));
		Menu_Company_Regist.setHorizontalTextPosition(SwingConstants.CENTER);
		Menu_Company_Regist.setHorizontalAlignment(SwingConstants.CENTER);
		Menu_Company.add(Menu_Company_Regist);
		//수정---------------------------------------------------------------------
		Menu_Company_Edit = new JMenuItem("수정");
		Menu_Company_Edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Companyform.setVisible(true);
			}
		});
		Menu_Company_Edit.setPreferredSize(new Dimension(150, 30));
		Menu_Company_Edit.setMargin(new Insets(0, 30, 0, 0));
		Menu_Company_Edit.setHorizontalTextPosition(SwingConstants.CENTER);
		Menu_Company_Edit.setHorizontalAlignment(SwingConstants.CENTER);
		Menu_Company.add(Menu_Company_Edit);
		//삭제---------------------------------------------------------------------
		Menu_Company_Delete = new JMenuItem("삭제");
		Menu_Company_Delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Companyform.setVisible(true);
			}
		});
		Menu_Company_Delete.setPreferredSize(new Dimension(150, 30));
		Menu_Company_Delete.setMargin(new Insets(0, 30, 0, 0));
		Menu_Company_Delete.setHorizontalTextPosition(SwingConstants.CENTER);
		Menu_Company_Delete.setHorizontalAlignment(SwingConstants.CENTER);
		Menu_Company.add(Menu_Company_Delete);
		
//------캠핑카 메뉴 설정--------------------------------------------------------------------------------
		JMenu Menu_Campingcar = new JMenu("캠핑카");
		Menu_Campingcar.setIconTextGap(55);
		Menu_Campingcar.setHorizontalTextPosition(SwingConstants.CENTER);
		Menu_Campingcar.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar.add(Menu_Campingcar);
		//등록---------------------------------------------------------------------
		Menu_Campingcar_Regist = new JMenuItem("등록");
		Menu_Campingcar_Regist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Campingcarform.setVisible(true);
			}
		});
		Menu_Campingcar_Regist.setPreferredSize(new Dimension(150, 30));
		Menu_Campingcar_Regist.setMargin(new Insets(0, 30, 0, 0));
		Menu_Campingcar_Regist.setHorizontalTextPosition(SwingConstants.CENTER);
		Menu_Campingcar_Regist.setHorizontalAlignment(SwingConstants.CENTER);
		Menu_Campingcar.add(Menu_Campingcar_Regist);
		//수정---------------------------------------------------------------------
		Menu_Campingcar_Edit = new JMenuItem("수정");
		Menu_Campingcar_Edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Campingcarform.setVisible(true);
			}
		});
		Menu_Campingcar_Edit.setPreferredSize(new Dimension(150, 30));
		Menu_Campingcar_Edit.setMargin(new Insets(0, 30, 0, 0));
		Menu_Campingcar_Edit.setHorizontalTextPosition(SwingConstants.CENTER);
		Menu_Campingcar_Edit.setHorizontalAlignment(SwingConstants.CENTER);
		Menu_Campingcar.add(Menu_Campingcar_Edit);
		//삭제---------------------------------------------------------------------
		Menu_Campingcar_Delete = new JMenuItem("삭제");
		Menu_Campingcar_Delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Campingcarform.setVisible(true);
			}
		});
		Menu_Campingcar_Delete.setPreferredSize(new Dimension(150, 30));
		Menu_Campingcar_Delete.setMargin(new Insets(0, 30, 0, 0));
		Menu_Campingcar_Delete.setHorizontalTextPosition(SwingConstants.CENTER);
		Menu_Campingcar_Delete.setHorizontalAlignment(SwingConstants.CENTER);
		Menu_Campingcar.add(Menu_Campingcar_Delete);
		
//------고객 메뉴 설정---------------------------------------------------------------------------------
		JMenu Menu_Customer = new JMenu("고객");
		Menu_Customer.setIconTextGap(55);
		Menu_Customer.setHorizontalTextPosition(SwingConstants.CENTER);
		Menu_Customer.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar.add(Menu_Customer);
		//등록---------------------------------------------------------------------
		Menu_Customer_Regist = new JMenuItem("등록");
		Menu_Customer_Regist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Customerform.setVisible(true);
			}
		});
		Menu_Customer_Regist.setPreferredSize(new Dimension(150, 30));
		Menu_Customer_Regist.setMargin(new Insets(0, 30, 0, 0));
		Menu_Customer_Regist.setHorizontalTextPosition(SwingConstants.CENTER);
		Menu_Customer_Regist.setHorizontalAlignment(SwingConstants.CENTER);
		Menu_Customer.add(Menu_Customer_Regist);
		//수정---------------------------------------------------------------------
		Menu_Customer_Edit = new JMenuItem("수정");
		Menu_Customer_Edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Customerform.setVisible(true);
			}
		});
		Menu_Customer_Edit.setPreferredSize(new Dimension(150, 30));
		Menu_Customer_Edit.setMargin(new Insets(0, 30, 0, 0));
		Menu_Customer_Edit.setHorizontalTextPosition(SwingConstants.CENTER);
		Menu_Customer_Edit.setHorizontalAlignment(SwingConstants.CENTER);
		Menu_Customer.add(Menu_Customer_Edit);
		//삭제---------------------------------------------------------------------
		Menu_Customer_Delete = new JMenuItem("삭제");
		Menu_Customer_Delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Customerform.setVisible(true);
			}
		});
		Menu_Customer_Delete.setPreferredSize(new Dimension(150, 30));
		Menu_Customer_Delete.setMargin(new Insets(0, 30, 0, 0));
		Menu_Customer_Delete.setHorizontalTextPosition(SwingConstants.CENTER);
		Menu_Customer_Delete.setHorizontalAlignment(SwingConstants.CENTER);
		Menu_Customer.add(Menu_Customer_Delete);
		
//------정비소 메뉴 설정---------------------------------------------------------------------------------
		JMenu Menu_Garage = new JMenu("정비소");
		Menu_Garage.setIconTextGap(55);
		Menu_Garage.setHorizontalTextPosition(SwingConstants.CENTER);
		Menu_Garage.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar.add(Menu_Garage);
		//등록---------------------------------------------------------------------
		Menu_Garage_Regist = new JMenuItem("등록");
		Menu_Garage_Regist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Garageform.setVisible(true);
			}
		});
		Menu_Garage_Regist.setPreferredSize(new Dimension(150, 30));
		Menu_Garage_Regist.setMargin(new Insets(0, 30, 0, 0));
		Menu_Garage_Regist.setHorizontalTextPosition(SwingConstants.CENTER);
		Menu_Garage_Regist.setHorizontalAlignment(SwingConstants.CENTER);
		Menu_Garage.add(Menu_Garage_Regist);
		//수정---------------------------------------------------------------------
		Menu_Garage_Edit = new JMenuItem("수정");
		Menu_Garage_Edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Garageform.setVisible(true);
			}
		});
		Menu_Garage_Edit.setPreferredSize(new Dimension(150, 30));
		Menu_Garage_Edit.setMargin(new Insets(0, 30, 0, 0));
		Menu_Garage_Edit.setHorizontalTextPosition(SwingConstants.CENTER);
		Menu_Garage_Edit.setHorizontalAlignment(SwingConstants.CENTER);
		Menu_Garage.add(Menu_Garage_Edit);
		//삭제---------------------------------------------------------------------
		Menu_Garage_Delete = new JMenuItem("삭제");
		Menu_Garage_Delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Garageform.setVisible(true);
			}
		});
		Menu_Garage_Delete.setPreferredSize(new Dimension(150, 30));
		Menu_Garage_Delete.setMargin(new Insets(0, 30, 0, 0));
		Menu_Garage_Delete.setHorizontalTextPosition(SwingConstants.CENTER);
		Menu_Garage_Delete.setHorizontalAlignment(SwingConstants.CENTER);
		Menu_Garage.add(Menu_Garage_Delete);
		
//------우측상단 영역 설정------------------------------------------------------------------------------	
		JLabel lblNewLabel_2 = new JLabel("검색");
		lblNewLabel_2.setFont(new Font("굴림", Font.BOLD, 20));
		lblNewLabel_2.setBounds(614, 10, 110, 21);
		panel.add(lblNewLabel_2);
		
		JButton btn1 = new JButton("검색1");
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				srchtxt.setText("검색1 결과\n");
			}
		});
		btn1.setBounds(955, 26, 105, 23);
		panel.add(btn1);
		
		JButton btn2 = new JButton("검색2");
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				srchtxt.setText("검색2 결과\n");
				
			}
		});
		btn2.setBounds(955, 53, 105, 23);
		panel.add(btn2);
		
		JButton btn3 = new JButton("검색3");
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				srchtxt.setText("검색3\n");
				
			}
		});
		btn3.setBounds(955, 79, 105, 23);
		panel.add(btn3);
		
		JButton bnt4 = new JButton("검색4");
		bnt4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				srchtxt.setText("검색4 결과\n");
				
			}
		});
		bnt4.setBounds(955, 105, 105, 23);
		panel.add(bnt4);
		
		JLabel search1 = new JLabel("1.   수리목록 중에서 10만원이상 내역이 나온 고객이름");
		search1.setBounds(614, 30, 321, 15);
		panel.add(search1);
		
		JLabel search2 = new JLabel("2.   캠핑카의 청구요금이 50만원 이상 지불해야 할 고객이름");
		search2.setBounds(614, 57, 329, 15);
		panel.add(search2);
		
		JLabel search3 = new JLabel("3.   수리할 필요가 없는, 수리필요여부가 1인 캠핑카");
		search3.setBounds(614, 83, 329, 15);
		panel.add(search3);
		
		JLabel search4 = new JLabel("4.   대여기간이 10일이상인 고객이름");
		search4.setBounds(614, 109, 321, 15);
		panel.add(search4);
		
		//검색결과 출력되는 곳-------------------------------------------------------------------------------
		srchtxt.setBounds(614, 138, 446, 101);
		panel.add(srchtxt);
		
//우측하단 영역 설정-----------------------------------------------------------------------------------------		
		JLabel lblNewLabel_5 = new JLabel("※ 수리여부가 1인 캠핑카의 고유대여ID와 정비소ID를 입력 후  추가정보를 입력");
		lblNewLabel_5.setBounds(614, 279, 446, 26);
		panel.add(lblNewLabel_5);
		//------정비소로 보내기-----------------------------------------------------------------------------
		JButton grgbtn = new JButton("정비소로보내기");
		grgbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		grgbtn.setFont(new Font("굴림", Font.BOLD, 15));
		grgbtn.setBounds(889, 315, 142, 77);
		panel.add(grgbtn);
		
		//------다시 캠핑카 업체로 반환하기---------------------------------------------------------------------
		JButton returnbtn = new JButton("반 환 하 기");
		returnbtn.setFont(new Font("굴림", Font.BOLD, 18));
		returnbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		returnbtn.setBounds(889, 402, 142, 77);
		panel.add(returnbtn);
		
		//-------데이터 입력------------------------------------------------------------------------------
		JLabel lblNewLabel_1_5_1 = new JLabel("캠핑카ID");
		lblNewLabel_1_5_1.setFont(new Font("굴림", Font.BOLD, 15));
		lblNewLabel_1_5_1.setBounds(614, 318, 88, 21);
		panel.add(lblNewLabel_1_5_1);
		
		torepair = new JTextField();
		torepair.setBounds(727, 315, 150, 21);
		panel.add(torepair);
		torepair.setColumns(10);
		
		JLabel lblNewLabel_1_5 = new JLabel("정비소ID");
		lblNewLabel_1_5.setFont(new Font("굴림", Font.BOLD, 15));
		lblNewLabel_1_5.setBounds(614, 342, 73, 21);
		panel.add(lblNewLabel_1_5);
		
		grgid = new JTextField();
		grgid.setColumns(10);
		grgid.setBounds(727, 342, 150, 21);
		panel.add(grgid);
		
		JLabel lblNewLabel_1_1 = new JLabel("정비내역");
		lblNewLabel_1_1.setFont(new Font("굴림", Font.BOLD, 15));
		lblNewLabel_1_1.setBounds(614, 364, 73, 21);
		panel.add(lblNewLabel_1_1);
		
		rlog = new JTextField();
		rlog.setColumns(10);
		rlog.setBounds(727, 366, 150, 21);
		panel.add(rlog);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("수리날짜");
		lblNewLabel_1_1_1.setFont(new Font("굴림", Font.BOLD, 15));
		lblNewLabel_1_1_1.setBounds(614, 389, 73, 21);
		panel.add(lblNewLabel_1_1_1);
		
		rfixdate = new JTextField();
		rfixdate.setColumns(10);
		rfixdate.setBounds(727, 389, 150, 21);
		panel.add(rfixdate);
		
		JLabel lblNewLabel_1_2 = new JLabel("수리비용");
		lblNewLabel_1_2.setFont(new Font("굴림", Font.BOLD, 15));
		lblNewLabel_1_2.setBounds(614, 413, 73, 21);
		panel.add(lblNewLabel_1_2);
		
		rprice = new JTextField();
		rprice.setColumns(10);
		rprice.setBounds(727, 413, 150, 21);
		panel.add(rprice);
		
		JLabel lblNewLabel_1_3 = new JLabel("납입기한");
		lblNewLabel_1_3.setFont(new Font("굴림", Font.BOLD, 15));
		lblNewLabel_1_3.setBounds(614, 437, 73, 21);
		panel.add(lblNewLabel_1_3);
		
		rduedate = new JTextField();
		rduedate.setColumns(10);
		rduedate.setBounds(727, 437, 150, 21);
		panel.add(rduedate);
		
		JLabel lblNewLabel_1_4 = new JLabel("기타내역정보");
		lblNewLabel_1_4.setFont(new Font("굴림", Font.BOLD, 15));
		lblNewLabel_1_4.setBounds(614, 461, 101, 21);
		panel.add(lblNewLabel_1_4);
		
		rotherinfo = new JTextField();
		rotherinfo.setColumns(10);
		rotherinfo.setBounds(727, 461, 150, 21);
		panel.add(rotherinfo);

		resetbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
	}
	

//	public void datareset() {
//		rlog.setText("");
//		rfixdate.setText("");
//		rprice.setText("");
//		rduedate.setText("");
//		rotherinfo.setText("");
//		grgid.setText("");
//		torepair.setText("");
//	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==Companyform.quit) {
			Companyform.setVisible(false);
			setVisible(true);
		}
		if(e.getSource()==Campingcarform.quit) {
			Campingcarform.setVisible(false);
			setVisible(true);
		}
		if(e.getSource()==Customerform.quit) {
			Customerform.setVisible(false);
			setVisible(true);
		}
		if(e.getSource()==Garageform.quit) {
			Garageform.setVisible(false);
			setVisible(true);
		}
		
	}
}
