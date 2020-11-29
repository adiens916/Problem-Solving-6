package view;

import model.dataClass.CompanyDataClass;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CompanyView extends AbstractView<CompanyDataClass>{

	public JTextField companyID;
	public JTextField companyName;
	public JTextField companyAddress;
	public JTextField companyPhoneNumber;
	public JTextField companyOfficerEmail;
	public JTextField companyOfficerName;

	@Override
	public void refreshInput() {
		companyID.setText("");
		companyName.setText("");
		companyAddress.setText("");
		companyPhoneNumber.setText("");
		companyOfficerEmail.setText("");
		companyOfficerName.setText("");
	}

	@Override
	public CompanyDataClass getInput() {
		CompanyDataClass company = new CompanyDataClass();
		company.companyID = companyID.getText();
		company.companyName = companyName.getText();
		company.companyAddress = companyAddress.getText();
		company.companyPhoneNumber = companyPhoneNumber.getText();
		company.companyOfficerEmail = companyOfficerEmail.getText();
		company.companyOfficerName = companyOfficerName.getText();
		return company;
	}

	@Override
	public String getId() {
		return companyID.getText();
	}

	@Override
	String getColumnList() {
		return "회사ID \t 회사명 \t 주소 \t 전화번호 \t 담당자이메일  \t 담당자이름 \n";
	}

	@Override
	String toStringFromInfo(CompanyDataClass company) {
		return company.companyID + '\t' +
				company.companyName + '\t' +
				company.companyAddress + '\t' +
				company.companyPhoneNumber + '\t' +
				company.companyOfficerEmail + '\t' +
				company.companyOfficerName + '\n';
	}

	@Override
	void addComponent() {
		setTitle("회사정보 관리페이지");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1001, 453);
		JPanel contentPane = new JPanel();
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
		scrollPane.setViewportView(textArea);
		
//Label--------------------------------------------------------------------------
		JLabel titleLabel = new JLabel("회사 정보 입력 | 수정 | 삭제");
		titleLabel.setFont(new Font("양재튼튼체B", Font.BOLD, 28));
		titleLabel.setBounds(317, 4, 391, 39);
		panel.add(titleLabel);

		JLabel instructionLabel = new JLabel("회사ID 검색후 수정,삭제하시면 더 편리합니다.");
		instructionLabel.setBounds(389, 49, 256, 15);
		panel.add(instructionLabel);

		JLabel companyIDLabel = new JLabel("회사ID입력");
		companyIDLabel.setFont(new Font("굴림", Font.BOLD, 18));
		companyIDLabel.setBounds(16, 100, 120, 20);
		panel.add(companyIDLabel);

		JLabel companyNameLabel = new JLabel("회사명");
		companyNameLabel.setFont(new Font("굴림", Font.BOLD, 18));
		companyNameLabel.setBounds(16, 143, 96, 20);
		panel.add(companyNameLabel);

		JLabel companyAddressLabel = new JLabel("주소");
		companyAddressLabel.setFont(new Font("굴림", Font.BOLD, 18));
		companyAddressLabel.setBounds(16, 176, 96, 20);
		panel.add(companyAddressLabel);

		JLabel companyPhoneNumberLabel = new JLabel("전화번호");
		companyPhoneNumberLabel.setFont(new Font("굴림", Font.BOLD, 18));
		companyPhoneNumberLabel.setBounds(16, 215, 96, 20);
		panel.add(companyPhoneNumberLabel);

		JLabel companyOfficerEmailLabel = new JLabel("담당자이메일");
		companyOfficerEmailLabel.setFont(new Font("굴림", Font.BOLD, 16));
		companyOfficerEmailLabel.setBounds(16, 253, 96, 20);
		panel.add(companyOfficerEmailLabel);

		JLabel companyOfficerNameLabel = new JLabel("담당자이름");
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
		readButton = new JButton("검색");
		readButton.setBounds(210, 100, 60, 23);
		panel.add(readButton);
		
		refreshButton = new JButton("새로고침");
		refreshButton.setBounds(16, 18, 96, 20);
		panel.add(refreshButton);
		
		createButton = new JButton("입력");
		createButton.setForeground(Color.BLACK);
		createButton.setFont(new Font("굴림", Font.BOLD, 15));
		createButton.setBounds(42, 322, 70, 29);
		panel.add(createButton);
		
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
		
		quit = new JButton("닫기");
		quit.setForeground(Color.BLACK);
		quit.setFont(new Font("굴림", Font.BOLD, 15));
		quit.setBounds(123, 361, 70, 22);
		panel.add(quit);
	}
}
