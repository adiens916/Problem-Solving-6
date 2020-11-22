package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import controller.dataClass.GarageInfo;

public class GarageView extends ManagementView<GarageInfo> {

	private JTextField garageid,name,address,number,emailaddress,gmanager;

	@Override
	public void refreshInput() {
		name.setText("");
		address.setText("");
		number.setText("");
		emailaddress.setText("");
		garageid.setText("");
	}

	@Override
	public GarageInfo getInput() {
		GarageInfo garage = new GarageInfo();
		garage.name = name.getText();
		garage.address = address.getText();
		garage.number = number.getText();
		garage.manager = gmanager.getText();
		garage.emailAddress = emailaddress.getText();
		garage.id = garageid.getText();
		return garage;
	}

	@Override
	public String getId() {
		return garageid.getText();
	}

	@Override
	String getColumnList() {
		return "차고지ID \t 카센터이름 \t 주소 \t 번호 \t 매니저이름 \t 이메일주소\n";
	}

	@Override
	String toStringFromInfo(GarageInfo garage) {
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
		scrollPane.setViewportView(selectcp);

//상단 타이틀 Label--------------------------------------------------------------------------
		JLabel label1 = new JLabel("정비소 정보 입력 | 수정 | 삭제");
		label1.setFont(new Font("양재튼튼체B", Font.BOLD, 28));
		label1.setBounds(274, 10, 432, 39);
		panel.add(label1);
		label1 = new JLabel("차고지ID 검색후 수정,삭제하시면 더 편리합니다.");
		label1.setBounds(329, 55, 294, 15);
		panel.add(label1);
//새로고침 기능------------------------------------------------------------------------------
		btn_Refresh = new JButton("새로고침");
		btn_Refresh.setBounds(16, 18, 96, 20);
		panel.add(btn_Refresh);

//데이터 입력-------------------------------------------------------------------------------
		btn_Search = new JButton("검색");
		btn_Search.setFont(new Font("굴림", Font.PLAIN, 10));
		btn_Search.setBounds(242, 98, 57, 23);
		panel.add(btn_Search);

		JLabel label2 = new JLabel("이름");
		label2.setFont(new Font("굴림", Font.BOLD, 18));
		label2.setBounds(16, 141, 120, 20);
		panel.add(label2);

		JLabel label3 = new JLabel("주소");
		label3.setFont(new Font("굴림", Font.BOLD, 18));
		label3.setBounds(16, 178, 120, 20);
		panel.add(label3);

		JLabel label4 = new JLabel("이메일주소");
		label4.setFont(new Font("굴림", Font.BOLD, 18));
		label4.setBounds(16, 286, 120, 20);
		panel.add(label4);


		JLabel label5 = new JLabel("매니저이름");
		label5.setFont(new Font("굴림", Font.BOLD, 18));
		label5.setBounds(16, 255, 120, 20);
		panel.add(label5);

		JLabel label6 = new JLabel("차고지ID입력");
		label6.setFont(new Font("굴림", Font.BOLD, 18));
		label6.setBounds(16, 102, 120, 20);
		panel.add(label6);

		JLabel label7 = new JLabel("번호");
		label7.setFont(new Font("굴림", Font.BOLD, 18));
		label7.setBounds(16, 216, 120, 20);
		panel.add(label7);

		garageid = new JTextField();
		garageid.setColumns(10);
		garageid.setBounds(158, 99, 72, 20);
		panel.add(garageid);

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
		number.setBounds(158, 216, 141, 21);
		panel.add(number);

		emailaddress = new JTextField();
		emailaddress.setColumns(10);
		emailaddress.setBounds(158, 285, 141, 21);
		panel.add(emailaddress);

		gmanager = new JTextField();
		gmanager.setColumns(10);
		gmanager.setBounds(158, 252, 141, 21);
		panel.add(gmanager);

//하단에 입력, 수정, 삭제 버튼 기능-------------------------------------------------------------
		btn_Insert = new JButton("입력");
		btn_Insert.setForeground(Color.BLACK);
		btn_Insert.setFont(new Font("굴림", Font.BOLD, 15));
		btn_Insert.setBounds(34, 316, 70, 29);
		panel.add(btn_Insert);


		btn_Edit = new JButton("수정");
		btn_Edit.setForeground(Color.BLACK);
		btn_Edit.setFont(new Font("굴림", Font.BOLD, 15));
		btn_Edit.setBounds(115, 316, 70, 29);
		panel.add(btn_Edit);


		btn_Delete = new JButton("삭제");
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
}


