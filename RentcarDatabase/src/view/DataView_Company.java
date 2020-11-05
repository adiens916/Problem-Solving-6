package view;

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

public class DataView_Company extends JFrame{

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DataView_Company frame = new DataView_Company();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private JPanel contentPane;
	private JLabel Label1,Label2,Label3,Label4,Label5,Label6,Label7,Label8;
	JTextField cpid,cpname,cpaddress,cpnumber,cpmngemail,cpmngname;
	JTextArea selectcp = new JTextArea();
	JButton btn_Search,btn_Insert,btn_Edit,btn_Refresh,btn_Delete,quit;
	
	public void printdata() {
		selectcp.setText("회사 ID \t 회사명 \t 주소 \t 전화번호 \t 담당자이메일 \t\t 담당자이름 \n");
	}	
		
	public DataView_Company() {
		setTitle("캠핑카프로젝트 리팩토링");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1001, 453);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
//데이터 출력 영역----------------------------------------------------------------------------
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(311, 98, 652, 287);
		panel.add(scrollPane);
		scrollPane.setViewportView(selectcp);
		printdata();
		
//새로고침 기능------------------------------------------------------------------------------
		btn_Refresh = new JButton("새로고침");
		btn_Refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				printdata();
			}
		});
		btn_Refresh.setBounds(16, 18, 96, 20);
		panel.add(btn_Refresh);
		
//상단 타이틀 Label--------------------------------------------------------------------------
		Label1 = new JLabel("회사ID 검색후 수정,삭제하시면 더 편리합니다.");
		Label1.setBounds(389, 49, 256, 15);
		panel.add(Label1);
		Label3 = new JLabel("회사 정보 입력 | 수정 | 삭제");
		Label3.setFont(new Font("양재튼튼체B", Font.BOLD, 28));
		Label3.setBounds(317, 4, 391, 39);
		panel.add(Label3);
		
		btn_Search = new JButton("검색");
		btn_Search.setFont(new Font("굴림", Font.PLAIN, 10));
		btn_Search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btn_Search.setBounds(210, 100, 57, 23);
		panel.add(btn_Search);
		
		Label2 = new JLabel("회사ID입력");
		Label2.setFont(new Font("굴림", Font.BOLD, 18));
		Label2.setBounds(16, 100, 120, 20);
		panel.add(Label2);
		
		Label4 = new JLabel("회사명");
		Label4.setFont(new Font("굴림", Font.BOLD, 18));
		Label4.setBounds(16, 143, 96, 20);
		panel.add(Label4);
		
		Label5 = new JLabel("주소");
		Label5.setFont(new Font("굴림", Font.BOLD, 18));
		Label5.setBounds(16, 176, 96, 20);
		panel.add(Label5);
		
		Label6 = new JLabel("전화번호");
		Label6.setFont(new Font("굴림", Font.BOLD, 18));
		Label6.setBounds(16, 215, 96, 20);
		panel.add(Label6);
		
		Label7 = new JLabel("담당자이메일");
		Label7.setFont(new Font("굴림", Font.BOLD, 16));
		Label7.setBounds(16, 253, 96, 20);
		panel.add(Label7);
		
		Label8 = new JLabel("담당자이름");
		Label8.setFont(new Font("굴림", Font.BOLD, 16));
		Label8.setBounds(16, 289, 96, 20);
		panel.add(Label8);
		
		cpid = new JTextField();
		cpid.setColumns(10);
		cpid.setBounds(126, 101, 72, 20);
		panel.add(cpid);
		
		cpname = new JTextField();
		cpname.setColumns(10);
		cpname.setBounds(126, 140, 141, 21);
		panel.add(cpname);
		
		cpaddress = new JTextField();
		cpaddress.setColumns(10);
		cpaddress.setBounds(126, 176, 141, 21);
		panel.add(cpaddress);
		
		cpnumber = new JTextField();
		cpnumber.setColumns(10);
		cpnumber.setBounds(126, 212, 141, 21);
		panel.add(cpnumber);
		
		cpmngemail = new JTextField();
		cpmngemail.setColumns(10);
		cpmngemail.setBounds(126, 250, 141, 21);
		panel.add(cpmngemail);
		
		cpmngname = new JTextField();
		cpmngname.setColumns(10);
		cpmngname.setBounds(126, 286, 141, 21);
		panel.add(cpmngname);
		
//하단에 입력, 수정, 삭제 버튼 기능-------------------------------------------------------------
		btn_Insert = new JButton("입력");
		btn_Insert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btn_Insert.setForeground(Color.BLACK);
		btn_Insert.setFont(new Font("굴림", Font.BOLD, 15));
		btn_Insert.setBounds(42, 322, 70, 29);
		panel.add(btn_Insert);
		
		
		btn_Edit = new JButton("수정");
		btn_Edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btn_Edit.setForeground(Color.BLACK);
		btn_Edit.setFont(new Font("굴림", Font.BOLD, 15));
		btn_Edit.setBounds(123, 322, 70, 29);
		panel.add(btn_Edit);
		
		btn_Delete = new JButton("삭제");
		btn_Delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			 			
			}
		});
		btn_Delete.setForeground(Color.BLACK);
		btn_Delete.setFont(new Font("굴림", Font.BOLD, 15));
		btn_Delete.setBounds(205, 322, 70, 29);
		panel.add(btn_Delete);
		
		quit = new JButton("닫기");
		quit.setForeground(Color.BLACK);
		quit.setFont(new Font("굴림", Font.BOLD, 15));
		quit.setBounds(123, 361, 70, 22);
		panel.add(quit);
	}
	
}


