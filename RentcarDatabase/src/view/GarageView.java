package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import model.dataClass.GarageDataClass;

public class GarageView extends AbstractView<GarageDataClass> {

	private JTextField garageIdTextField;
	private JTextField nameTextField;
	private JTextField addressTextField;
	private JTextField phoneNumberTextField;
	private JTextField emailAddressTextField;
	private JTextField managerTextField;

	@Override
	public void refreshInput() {
		nameTextField.setText("");
		addressTextField.setText("");
		phoneNumberTextField.setText("");
		emailAddressTextField.setText("");
		garageIdTextField.setText("");
	}

	@Override
	public GarageDataClass getInput() {
		GarageDataClass garage = new GarageDataClass();
		garage.name = nameTextField.getText();
		garage.address = addressTextField.getText();
		garage.number = phoneNumberTextField.getText();
		garage.manager = managerTextField.getText();
		garage.emailAddress = emailAddressTextField.getText();
		garage.id = garageIdTextField.getText();
		return garage;
	}

	@Override
	public String getId() {
		return garageIdTextField.getText();
	}

	@Override
	String getColumnList() {
		return "차고지ID \t 카센터이름 \t 주소 \t 번호 \t 매니저이름 \t 이메일주소\n";
	}

	@Override
	String toStringFromInfo(GarageDataClass garage) {
		return	garage.id + "\t" +
				garage.name + "\t" +
				garage.address + "\t" +
				garage.number + "\t" +
				garage.manager + "\t" +
				garage.emailAddress + "\n";
	}

	@Override
	void addComponent() {
		setTitle("캠핑카프로젝트 리팩토링");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 921, 441);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

//데이터 출력 영역----------------------------------------------------------------------------
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(311, 98, 576, 287);
		panel.add(scrollPane);
		scrollPane.setViewportView(textArea);

//상단 타이틀 Label--------------------------------------------------------------------------
		JLabel guideLabel = new JLabel("정비소 정보 입력 | 수정 | 삭제");
		guideLabel.setFont(new Font("양재튼튼체B", Font.BOLD, 28));
		guideLabel.setBounds(274, 10, 432, 39);
		panel.add(guideLabel);
		guideLabel = new JLabel("차고지ID 검색후 수정,삭제하시면 더 편리합니다.");
		guideLabel.setBounds(329, 55, 294, 15);
		panel.add(guideLabel);
//새로고침 기능------------------------------------------------------------------------------
		refreshButton = new JButton("새로고침");
		refreshButton.setBounds(16, 18, 96, 20);
		panel.add(refreshButton);

//데이터 입력-------------------------------------------------------------------------------
		readButton = new JButton("검색");
		readButton.setFont(new Font("굴림", Font.PLAIN, 10));
		readButton.setBounds(242, 98, 57, 23);
		panel.add(readButton);

		JLabel nameLabel = new JLabel("이름");
		nameLabel.setFont(new Font("굴림", Font.BOLD, 18));
		nameLabel.setBounds(16, 141, 120, 20);
		panel.add(nameLabel);

		JLabel AddressLabel = new JLabel("주소");
		AddressLabel.setFont(new Font("굴림", Font.BOLD, 18));
		AddressLabel.setBounds(16, 178, 120, 20);
		panel.add(AddressLabel);

		JLabel emailAddressLabel = new JLabel("이메일주소");
		emailAddressLabel.setFont(new Font("굴림", Font.BOLD, 18));
		emailAddressLabel.setBounds(16, 286, 120, 20);
		panel.add(emailAddressLabel);


		JLabel managerLabel = new JLabel("매니저이름");
		managerLabel.setFont(new Font("굴림", Font.BOLD, 18));
		managerLabel.setBounds(16, 255, 120, 20);
		panel.add(managerLabel);

		JLabel garageIdLabel = new JLabel("차고지ID입력");
		garageIdLabel.setFont(new Font("굴림", Font.BOLD, 18));
		garageIdLabel.setBounds(16, 102, 120, 20);
		panel.add(garageIdLabel);

		JLabel phoneNumberLabel = new JLabel("번호");
		phoneNumberLabel.setFont(new Font("굴림", Font.BOLD, 18));
		phoneNumberLabel.setBounds(16, 216, 120, 20);
		panel.add(phoneNumberLabel);

		garageIdTextField = new JTextField();
		garageIdTextField.setColumns(10);
		garageIdTextField.setBounds(158, 99, 72, 20);
		panel.add(garageIdTextField);

		nameTextField = new JTextField();
		nameTextField.setColumns(10);
		nameTextField.setBounds(158, 142, 141, 21);
		panel.add(nameTextField);

		addressTextField = new JTextField();
		addressTextField.setColumns(10);
		addressTextField.setBounds(158, 179, 141, 21);
		panel.add(addressTextField);

		phoneNumberTextField = new JTextField();
		phoneNumberTextField.setColumns(10);
		phoneNumberTextField.setBounds(158, 216, 141, 21);
		panel.add(phoneNumberTextField);

		emailAddressTextField = new JTextField();
		emailAddressTextField.setColumns(10);
		emailAddressTextField.setBounds(158, 285, 141, 21);
		panel.add(emailAddressTextField);

		managerTextField = new JTextField();
		managerTextField.setColumns(10);
		managerTextField.setBounds(158, 252, 141, 21);
		panel.add(managerTextField);

//하단에 입력, 수정, 삭제 버튼 기능-------------------------------------------------------------
		createButton = new JButton("입력");
		createButton.setForeground(Color.BLACK);
		createButton.setFont(new Font("굴림", Font.BOLD, 15));
		createButton.setBounds(34, 316, 70, 29);
		panel.add(createButton);


		updateButton = new JButton("수정");
		updateButton.setForeground(Color.BLACK);
		updateButton.setFont(new Font("굴림", Font.BOLD, 15));
		updateButton.setBounds(115, 316, 70, 29);
		panel.add(updateButton);


		deleteButton = new JButton("삭제");
		deleteButton.setForeground(Color.BLACK);
		deleteButton.setFont(new Font("굴림", Font.BOLD, 15));
		deleteButton.setBounds(197, 316, 70, 29);
		panel.add(deleteButton);

		quit = new JButton("닫기");
		quit.setForeground(Color.BLACK);
		quit.setFont(new Font("굴림", Font.BOLD, 15));
		quit.setBounds(115, 355, 70, 22);
		panel.add(quit);
	}
}


