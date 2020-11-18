package view;

import controller.MainController;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.Font;
import java.awt.Image;
import javax.swing.JTextField;
import java.awt.SystemColor;
import javax.swing.DropMode;
import javax.swing.ImageIcon;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.UIManager;

public class MainView extends JFrame implements ActionListener {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainView frame = new MainView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private JPanel contentPane;
	
	AdminView Adminform;
	CustomerView Customerform;
	MainController mainController;
	

	public MainView() {
		setTitle("캠핑카프로젝트 리팩토링");
		
//------컨트롤러의 객체 생성 및 리셋------------------------------------------------------------------------------------
		mainController = new MainController();
		mainController.DataReset();
		
//------다른View의 form객체 생성------------------------------------------------------------------------------------
		Adminform = new AdminView();
		Adminform.backbtn.addActionListener(this);

		Customerform = new CustomerView();
		Customerform.btn_Back.addActionListener(this);
//------배경설정-------------------------------------------------------------------------------------------------
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 675, 473);
//------버튼, 텍스트상장, 라벨과 같은 객체를 넣기 위한 팔레트 생성----------------------------------------------------------------
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
//------관리자View로 가기위한 버튼------------------------------------------------------------------------------------
		JButton ToAdmin = new JButton("관리자");
		ToAdmin.setBackground(new Color(204, 153, 153));
		ToAdmin.setBorder(null);
		ToAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == ToAdmin) {
					setVisible(false);
					Adminform.setVisible(true);
				}
			}
		});
		ToAdmin.setFont(new Font("굴림", Font.BOLD, 20));
		ToAdmin.setBounds(130, 275, 116, 92);
		ToAdmin.setForeground(new Color(0, 0, 0));
		contentPane.add(ToAdmin);
//------손님View로 가기위한 버튼------------------------------------------------------------------------------------
		JButton ToCustomer = new JButton("손님");
		ToCustomer.setBackground(new Color(204, 153, 153));
		ToCustomer.setBorder(null);
		ToCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == ToCustomer) {
					setVisible(false);
					Customerform.setVisible(true);
				}
			}
		});
		ToCustomer.setForeground(Color.BLACK);
		ToCustomer.setFont(new Font("굴림", Font.BOLD, 20));
		ToCustomer.setBounds(403, 275, 116, 92);
		contentPane.add(ToCustomer);
//------로고 이미지를 넣기위한 라벨 생성-------------------------------------------------------------------------------
		JLabel ImageLabel = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("logo.png")).getImage();
		ImageLabel.setIcon(new ImageIcon(img));
		ImageLabel.setBounds(278, 47, 97, 86);
		contentPane.add(ImageLabel);
//------텍스트를 넣기위한 라벨 생성----------------------------------------------------------------------------------
		JLabel TitleLabel = new JLabel("SEJONG CAMPING CAR");
		TitleLabel.setFont(new Font("Arial", Font.BOLD, 40));
		TitleLabel.setBounds(93, 176, 481, 58);
		contentPane.add(TitleLabel);
	}

//------해당 버튼으로 이동하기위해  ActionPerformed오버라이드해주기--------------------------------------------------------
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == Customerform.btn_Back) {
			Customerform.setVisible(false);
			setVisible(true);
		}
		if (e.getSource() == Adminform.backbtn) {
			Adminform.setVisible(false);
			setVisible(true);
		}
	}
}
