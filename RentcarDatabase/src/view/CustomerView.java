package view;
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

public class CustomerView extends JFrame implements ActionListener{
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerView frame = new CustomerView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private JPanel contentPane;
	ReturnView Returncarform;

	JTextField carsearch = new JTextField();
    JButton btnNewButton_1 = new JButton("반환하러가기"); 
    JTextArea listarea = new JTextArea();
	JTextArea starea = new JTextArea();
	
    JTextField period,license_id,start,addprice,add,duedate,cplist_id;
	JButton btn_Rent,btn_Back,btn_Refresh;
	JRadioButton ra1,ra2,ra3,ra4,ra5,ra6;
		
	public CustomerView() {
		setTitle("캠핑카프로젝트 리팩토링");
		Returncarform = new ReturnView();
		Returncarform.returnbtn.addActionListener(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1074, 532);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
//뒤로가기 버튼 기능---------------------------------------------------------------------------
		btn_Back = new JButton("《 뒤로가기");
		btn_Back.setBorder(new EmptyBorder(0, 0, 0, 0));
		btn_Back.setBackground(Color.WHITE);
		btn_Back.setBounds(0, 0, 103, 23);
		contentPane.add(btn_Back);
//상단 타이틀 Label--------------------------------------------------------------------------
		JLabel Label1 = new JLabel("SEJONG CAMPING");
		Label1.setFont(new Font("양재백두체B", Font.BOLD, 30));
		Label1.setBounds(264, 14, 331, 30);
		contentPane.add(Label1);
		
		JLabel Label2 = new JLabel("캠핑카 LIST");
		Label2.setFont(new Font("굴림", Font.BOLD, 20));
		Label2.setBounds(362, 65, 114, 21);
		contentPane.add(Label2);
		
		JLabel Label3 = new JLabel("대여 현황");
		Label3.setFont(new Font("굴림", Font.BOLD, 18));
		Label3.setBounds(902, 14, 103, 21);
		contentPane.add(Label3);
//캠핑카 데이터 텍스트 영역----------------------------------------------------------------------
		listarea.setBorder(new LineBorder(Color.BLACK, 1, true));
		listarea.setBounds(12, 156, 820, 325);
		contentPane.add(listarea);
//대여 현황 텍스트 영역-------------------------------------------------------------------------		
		starea.setBorder(new LineBorder(Color.BLACK));
		starea.setBounds(844, 38, 203, 82);
		contentPane.add(starea);
		
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Returncarform.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(895, 130, 110, 21);
		contentPane.add(btnNewButton_1);

//우측하단 대여 영역--------------------------------------------------------------------------
		JLabel Label4 = new JLabel("대여");
		Label4.setFont(new Font("굴림", Font.BOLD, 18));
		Label4.setBounds(926, 161, 37, 21);
		contentPane.add(Label4);
		
		JLabel Label5 = new JLabel("캠핑카ID");
		Label5.setFont(new Font("굴림", Font.BOLD, 14));
		Label5.setBounds(844, 192, 81, 21);
		contentPane.add(Label5);
		
		JLabel Label6 = new JLabel("면허증번호");
		Label6.setFont(new Font("굴림", Font.BOLD, 14));
		Label6.setBounds(844, 234, 81, 21);
		contentPane.add(Label6);
		
		JLabel Label7 = new JLabel("대여시작일");
		Label7.setFont(new Font("굴림", Font.BOLD, 14));
		Label7.setBounds(844, 274, 81, 21);
		contentPane.add(Label7);
		
		JLabel Label8 = new JLabel("대여기간");
		Label8.setFont(new Font("굴림", Font.BOLD, 14));
		Label8.setBounds(844, 312, 81, 21);
		contentPane.add(Label8);
		
		JLabel Label9 = new JLabel("납입기한");
		Label9.setFont(new Font("굴림", Font.BOLD, 14));
		Label9.setBounds(844, 351, 81, 21);
		contentPane.add(Label9);
		
		JLabel Label10 = new JLabel("추가물품");
		Label10.setFont(new Font("굴림", Font.BOLD, 14));
		Label10.setBounds(844, 386, 81, 21);
		contentPane.add(Label10);
		
		JLabel Label11 = new JLabel("물품금액");
		Label11.setFont(new Font("굴림", Font.BOLD, 14));
		Label11.setBounds(844, 417, 81, 21);
		contentPane.add(Label11);
		
		cplist_id = new JTextField();
		cplist_id.setBorder(new EmptyBorder(0, 0, 0, 0));
		cplist_id.setBounds(937, 193, 96, 21);
		contentPane.add(cplist_id);
		cplist_id.setColumns(10);
		
		period = new JTextField();
		period.setBorder(new EmptyBorder(0, 0, 0, 0));
		period.setColumns(10);
		period.setBounds(937, 312, 96, 21);
		contentPane.add(period);
		
		license_id = new JTextField();
		license_id.setBorder(new EmptyBorder(0, 0, 0, 0));
		license_id.setColumns(10);
		license_id.setBounds(937, 234, 96, 21);
		contentPane.add(license_id);
		
		start = new JTextField();
		start.setBorder(new EmptyBorder(0, 0, 0, 0));
		start.setColumns(10);
		start.setBounds(937, 274, 96, 21);
		contentPane.add(start);
		
		addprice = new JTextField();
		addprice.setBorder(new EmptyBorder(0, 0, 0, 0));
		addprice.setColumns(10);
		addprice.setBounds(937, 418, 96, 21);
		contentPane.add(addprice);
		
		add = new JTextField();
		add.setBorder(new EmptyBorder(0, 0, 0, 0));
		add.setColumns(10);
		add.setBounds(937, 386, 96, 21);
		contentPane.add(add);
		
		duedate = new JTextField();
		duedate.setBorder(new EmptyBorder(0, 0, 0, 0));
		duedate.setColumns(10);
		duedate.setBounds(937, 351, 96, 21);
		contentPane.add(duedate);

//검색기능 (라디오 버튼, 검색 버튼, 새로고침 버튼)-----------------------------------------------------
		carsearch = new JTextField();
		carsearch.setBorder(new EmptyBorder(0, 0, 0, 0));
		carsearch.setColumns(10);
		carsearch.setBounds(328, 125, 91, 21);
		contentPane.add(carsearch);
		
		ra1 = new JRadioButton("캠핑카ID");
		ra1.setBackground(SystemColor.menu);
		ra1.setBounds(138, 92, 81, 23);
		
		ra2 = new JRadioButton("차명");
		ra2.setBackground(SystemColor.menu);
		ra2.setBounds(217, 92, 53, 23);
		
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
		
		contentPane.add(ra1);
		contentPane.add(ra2);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(ra1);
		bg.add(ra2);
		bg.add(ra3);
		bg.add(ra4);
		bg.add(ra5);
		bg.add(ra6);
		
		JButton carserchbtn = new JButton("검색");
		carserchbtn.setBorder(new EmptyBorder(0, 0, 0, 0));
		carserchbtn.setBackground(Color.WHITE);
		carserchbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		carserchbtn.setBounds(425, 125, 61, 21);
		contentPane.add(carserchbtn);
		
		btn_Refresh = new JButton("새로고침");
		btn_Refresh.setBorder(new EmptyBorder(0, 0, 0, 0));
		btn_Refresh.setBackground(Color.WHITE);
		btn_Refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cplist_id.setText("");
				license_id.setText("");
				period.setText("");
				start.setText("");
				duedate.setText("");
				add.setText("");
				addprice.setText("");
			}
		});
		btn_Refresh.setBounds(489, 125, 91, 21);
		contentPane.add(btn_Refresh);
		
//대여버튼 기능-------------------------------------------------------------------------	
		btn_Rent = new JButton("대여");
		btn_Rent.setBackground(Color.WHITE);
		btn_Rent.setBorder(new EmptyBorder(0, 0, 0, 0));
		btn_Rent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_Rent.setBounds(902, 450, 91, 23);
		contentPane.add(btn_Rent);
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
		if(e.getSource()== Returncarform.returnbtn) {
			Returncarform.setVisible(false);
			setVisible(true);
		}
	}
}
