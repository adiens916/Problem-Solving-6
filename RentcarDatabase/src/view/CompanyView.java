package View;

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
import java.awt.Dimension;
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

import Controller.dataClass.CompanyInfo;
import Controller.dataClass.ResultState;
import java.util.*;

public class CompanyView extends JFrame{

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CompanyView frame = new CompanyView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private JPanel contentPane;
	private JLabel instructionLabel,companyIDLabel,titleLabel,companyNameLabel,companyAddressLabel,companyPhoneNumberLabel,companyOfficerEmailLabel,companyOfficerNameLabel;
	public JTextField companyID, companyName, companyAddress, companyPhoneNumber, companyOfficerEmail, companyOfficerName;
	JTextArea companyTextArea = new JTextArea();
	public JButton searchButton, insertButton, updateButton, refreshButton, deleteButton, quitButton;
		
	public CompanyView() {
		addComponent();
	}
	
	private void addComponent() {
		setTitle("회사정보 관리페이지");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1001, 453);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
//TextArea----------------------------------------------------------------------------
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(311, 98, 652, 287);
		panel.add(scrollPane);
		scrollPane.setViewportView(companyTextArea);
		
//Label--------------------------------------------------------------------------
		titleLabel = new JLabel("회사 정보 입력 | 수정 | 삭제");
		titleLabel.setFont(new Font("양재튼튼체B", Font.BOLD, 28));
		titleLabel.setBounds(317, 4, 391, 39);
		panel.add(titleLabel);
		
		instructionLabel = new JLabel("회사ID 검색후 수정,삭제하시면 더 편리합니다.");
		instructionLabel.setBounds(389, 49, 256, 15);
		panel.add(instructionLabel);
		
		companyIDLabel = new JLabel("회사ID입력");
		companyIDLabel.setFont(new Font("굴림", Font.BOLD, 18));
		companyIDLabel.setBounds(16, 100, 120, 20);
		panel.add(companyIDLabel);
		
		companyNameLabel = new JLabel("회사명");
		companyNameLabel.setFont(new Font("굴림", Font.BOLD, 18));
		companyNameLabel.setBounds(16, 143, 96, 20);
		panel.add(companyNameLabel);
		
		companyAddressLabel = new JLabel("주소");
		companyAddressLabel.setFont(new Font("굴림", Font.BOLD, 18));
		companyAddressLabel.setBounds(16, 176, 96, 20);
		panel.add(companyAddressLabel);
		
		companyPhoneNumberLabel = new JLabel("전화번호");
		companyPhoneNumberLabel.setFont(new Font("굴림", Font.BOLD, 18));
		companyPhoneNumberLabel.setBounds(16, 215, 96, 20);
		panel.add(companyPhoneNumberLabel);
		
		companyOfficerEmailLabel = new JLabel("담당자이메일");
		companyOfficerEmailLabel.setFont(new Font("굴림", Font.BOLD, 16));
		companyOfficerEmailLabel.setBounds(16, 253, 96, 20);
		panel.add(companyOfficerEmailLabel);
		
		companyOfficerNameLabel = new JLabel("담당자이름");
		companyOfficerNameLabel.setFont(new Font("굴림", Font.BOLD, 16));
		companyOfficerNameLabel.setBounds(16, 289, 96, 20);
		panel.add(companyOfficerNameLabel);

//TextField ----------------------------------------------------------------		
		companyID = new JTextField();
		companyID.setColumns(10);
		companyID.setBounds(126, 101, 72, 20);
		panel.add(companyID);
		
		companyName = new JTextField();
		companyName.setColumns(10);
		companyName.setBounds(126, 140, 141, 21);
		panel.add(companyName);
		
		companyAddress = new JTextField();
		companyAddress.setColumns(10);
		companyAddress.setBounds(126, 176, 141, 21);
		panel.add(companyAddress);
		
		companyPhoneNumber = new JTextField();
		companyPhoneNumber.setColumns(10);
		companyPhoneNumber.setBounds(126, 212, 141, 21);
		panel.add(companyPhoneNumber);
		
		companyOfficerEmail = new JTextField();
		companyOfficerEmail.setColumns(10);
		companyOfficerEmail.setBounds(126, 250, 141, 21);
		panel.add(companyOfficerEmail);
		
		companyOfficerName = new JTextField();
		companyOfficerName.setColumns(10);
		companyOfficerName.setBounds(126, 286, 141, 21);
		panel.add(companyOfficerName);
		
//Button-------------------------------------------------------------
		searchButton = new JButton("검색");
		searchButton.setBounds(210, 100, 60, 23);
		panel.add(searchButton);
		
		refreshButton = new JButton("새로고침");
		refreshButton.setBounds(16, 18, 96, 20);
		panel.add(refreshButton);
		
		insertButton = new JButton("입력");
		insertButton.setForeground(Color.BLACK);
		insertButton.setFont(new Font("굴림", Font.BOLD, 15));
		insertButton.setBounds(42, 322, 70, 29);
		panel.add(insertButton);
		
		updateButton = new JButton("수정");
		updateButton.setForeground(Color.BLACK);
		updateButton.setFont(new Font("굴림", Font.BOLD, 15));
		updateButton.setBounds(123, 322, 70, 29);
		panel.add(updateButton);
		
		deleteButton = new JButton("삭제");
		deleteButton.setForeground(Color.BLACK);
		deleteButton.setFont(new Font("굴림", Font.BOLD, 15));
		deleteButton.setBounds(205, 322, 70, 29);
		panel.add(deleteButton);
		
		quitButton = new JButton("닫기");
		quitButton.setForeground(Color.BLACK);
		quitButton.setFont(new Font("굴림", Font.BOLD, 15));
		quitButton.setBounds(123, 361, 70, 22);
		panel.add(quitButton);
	}
	
	public String getCompanyColumnList() {
		return "회사ID \t 회사명 \t 주소 \t 전화번호 \t 담당자이메일  \t 담당자이름 \n";
	}
	
	public String getCompanyID() {
		return companyID.getText();
	}
	
	public void readCompanyList(ArrayList<CompanyInfo> companyList) {
		companyTextArea.setText("");
		String list = getCompanyColumnList();
		for (CompanyInfo company : companyList) {
			list += toStringFromCompanyInfo(company);
		}
		companyTextArea.append(list);
	}
	
	public void readCompany(CompanyInfo company) {
		companyTextArea.setText("");
		String list = getCompanyColumnList();
		list += toStringFromCompanyInfo(company);
		companyTextArea.append(list);
	}
	
	public String toStringFromCompanyInfo(CompanyInfo company) {
		return company.companyID + '\t' +
				company.companyName + '\t' +
				company.companyAddress + '\t' +
				company.companyPhoneNumber + '\t' +
				company.companyOfficerEmail + '\t' +
				company.companyOfficerName + '\n';
	}
	
	public CompanyInfo getCompanyInput() {
		CompanyInfo company = new CompanyInfo();
		company.companyID = companyID.getText();
		company.companyName = companyName.getText();
		company.companyAddress = companyAddress.getText();
		company.companyPhoneNumber = companyPhoneNumber.getText();
		company.companyOfficerEmail = companyOfficerEmail.getText();
		company.companyOfficerName = companyOfficerName.getText();
		return company;
	}
	
	public void showCompanyInsertResult(ResultState result) {
		if (result == ResultState.SUCCESS) {
			JOptionPane.showMessageDialog(null,"캠핑카 회사 입력 완료");
		} else if (result == ResultState.NULL){
			JOptionPane.showMessageDialog(null,"모든 입력 칸을 채워주세요");
		} else {
			JOptionPane.showMessageDialog(null,"캠핑카 회사 입력 실패");
		}
	}
	
	public void showCompanyUpdateResult(ResultState result) {
		if (result == ResultState.SUCCESS) {
			JOptionPane.showMessageDialog(null,"캠핑카 회사 수정 완료");
		} else if (result == ResultState.NULL){
			JOptionPane.showMessageDialog(null,"모든 입력 칸을 채워주세요");
		} else {
			JOptionPane.showMessageDialog(null,"캠핑카 회사 수정 실패");
		}
	}
	
	public void showCompanyDeleteResult(ResultState result) {
		if (result == ResultState.SUCCESS) {
			JOptionPane.showMessageDialog(null,"캠핑카 회사 삭제 완료");
		} else if (result == ResultState.NULL){
			JOptionPane.showMessageDialog(null,"삭제할 캠핑카 회사 ID를 입력해주세요");
		} else {
			JOptionPane.showMessageDialog(null,"캠핑카 회사 삭제 실패");
		}
	}
	
}
