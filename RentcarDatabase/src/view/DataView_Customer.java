package view;

import controller.CustomerController;
import controller.dataClass.CustomerInfo;
import controller.dataClass.GarageInfo;

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

public class DataView_Customer extends JFrame{

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DataView_Customer frame = new DataView_Customer();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private JPanel contentPane;
	private JLabel Label1,Label2,Label3,Label4,Label5,Label6,Label7;
	JButton btn_Search,btn_Insert,btn_Edit,btn_Refresh,btn_Delete,quit;
	JTextField lisenceid,name,address,number,emailaddress;
	JTextArea selectcp = new JTextArea();
	CustomerController customerController = new CustomerController();
	
		
	public void printdata() {
		selectcp.setText("");
		selectcp.append(customerController.printCustomerList());
	}	
	
	public DataView_Customer() {
		setTitle("캠핑카프로젝트 리팩토링");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 840, 442);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
//데이터 출력 영역----------------------------------------------------------------------------
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(311, 98, 493, 287);
		panel.add(scrollPane);
		scrollPane.setViewportView(selectcp);
		printdata();
		
//상단 타이틀 Label--------------------------------------------------------------------------
		Label1 = new JLabel("고객정보 입력 | 수정 | 삭제");
		Label1.setFont(new Font("양재튼튼체B", Font.BOLD, 28));
		Label1.setBounds(217, 4, 391, 39);
		panel.add(Label1);
		Label2 = new JLabel("운전면허번호 검색후 수정,삭제하시면 더 편리합니다.");
		Label2.setBounds(265, 49, 294, 15);
		panel.add(Label2);
		
//새로고침 기능------------------------------------------------------------------------------	
		btn_Refresh = new JButton("새로고침");
		btn_Refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				printdata();
				name.setText("");
				address.setText("");
				number.setText("");
				emailaddress.setText("");
				lisenceid.setText("");
			}
		});
		btn_Refresh.setBounds(16, 18, 96, 20);
		panel.add(btn_Refresh);
		
//데이터 입력-------------------------------------------------------------------------------		
		btn_Search = new JButton("검색");
		btn_Search.setFont(new Font("굴림", Font.PLAIN, 10));
		btn_Search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				selectcp.setText("운전면허번호 \t 이름 \t 주소 \t 번호 \t 이메일 \n");
				selectcp.append(customerController.searchCustomerList(lisenceid.getText()));
				
			}
		});
		btn_Search.setBounds(242, 98, 57, 23);
		panel.add(btn_Search);
		
		Label3 = new JLabel("운전면허번호 입력");
		Label3.setFont(new Font("굴림", Font.BOLD, 15));
		Label3.setBounds(16, 98, 130, 21);
		panel.add(Label3);
		
		Label4 = new JLabel("이름");
		Label4.setFont(new Font("굴림", Font.BOLD, 18));
		Label4.setBounds(16, 141, 120, 20);
		panel.add(Label4);
		
		Label5 = new JLabel("주소");
		Label5.setFont(new Font("굴림", Font.BOLD, 18));
		Label5.setBounds(16, 178, 120, 20);
		panel.add(Label5);
		
		Label6 = new JLabel("번호");
		Label6.setFont(new Font("굴림", Font.BOLD, 18));
		Label6.setBounds(16, 221, 120, 20);
		panel.add(Label6);
		
		Label7 = new JLabel("이메일주소");
		Label7.setFont(new Font("굴림", Font.BOLD, 18));
		Label7.setBounds(16, 260, 120, 20);
		panel.add(Label7);
		
		lisenceid = new JTextField();
		lisenceid.setColumns(10);
		lisenceid.setBounds(158, 99, 72, 20);
		panel.add(lisenceid);
		
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
		
		
		
		
//하단에 입력, 수정, 삭제 버튼 기능-------------------------------------------------------------		
		btn_Insert = new JButton("입력");
		btn_Insert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				customerController.insertCustomerList(getCustomerInput());
				printdata();
				
			}
		});
		btn_Insert.setForeground(Color.BLACK);
		btn_Insert.setFont(new Font("굴림", Font.BOLD, 15));
		btn_Insert.setBounds(34, 316, 70, 29);
		panel.add(btn_Insert);
		
		btn_Edit = new JButton("수정");
		btn_Edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				customerController.editCustomerList(getCustomerInput());
				printdata();
				
			}
		});
		btn_Edit.setForeground(Color.BLACK);
		btn_Edit.setFont(new Font("굴림", Font.BOLD, 15));
		btn_Edit.setBounds(115, 316, 70, 29);
		panel.add(btn_Edit);
		
		
		btn_Delete = new JButton("삭제");
		btn_Delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				customerController.deleteCustomerList(lisenceid.getText());
				printdata();
				
			}
		});
		btn_Delete.setForeground(Color.BLACK);
		btn_Delete.setFont(new Font("굴림", Font.BOLD, 15));
		btn_Delete.setBounds(197, 316, 70, 29);
		panel.add(btn_Delete);
		
		quit = new JButton("닫기");
		quit.setForeground(Color.BLACK);
		quit.setFont(new Font("굴림", Font.BOLD, 15));
		quit.setBounds(115, 355, 70, 22);
		panel.add(quit);
		
	}
	 public CustomerInfo getCustomerInput() {
	        CustomerInfo customer = new CustomerInfo();
	        customer.name = name.getText();
	        customer.address = address.getText();
	        customer.number = number.getText();
	        customer.emailAddress = emailaddress.getText();
	        customer.licenseId = lisenceid.getText();
	        return customer;
	    }
}


