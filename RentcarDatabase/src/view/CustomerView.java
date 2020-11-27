package view;

import controller.dataClass.Customer;
import controller.dataClass.ResultState;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CustomerView extends AbstractView<Customer>{

	private JTextField lisenceid;
	private JTextField name;
	private JTextField address;
	private JTextField number;
	private JTextField emailaddress;

	@Override
	public void refreshInput() {
		name.setText("");
		address.setText("");
		number.setText("");
		emailaddress.setText("");
		lisenceid.setText("");
	}

	@Override
	public Customer getInput() {
		Customer customer = new Customer();
		customer.name = name.getText();
		customer.address = address.getText();
		customer.number = number.getText();
		customer.emailAddress = emailaddress.getText();
		customer.licenseId = lisenceid.getText();
		return customer;
	}

	@Override
	public String getId() {
		return lisenceid.getText();
	}

	@Override
	String getColumnList() {
		return "운전면허번호 \t 이름 \t 주소 \t 번호 \t 이메일 \n";
	}

	@Override
	String toStringFromInfo(Customer customer) {
		return customer.licenseId + "\t" +
				customer.name + "\t" +
				customer.address + "\t" +
				customer.number + "\t" +
				customer.emailAddress +"\n";
	}

	@Override
	public void showCreateResult(ResultState result) {
		if (result == ResultState.REGISTERED) {
			JOptionPane.showMessageDialog(null, "이미 가입하신 고객입니다.");
		} else {
			super.showCreateResult(result);
		}
	}

	@Override
	void addComponent() {
		setTitle("캠핑카프로젝트 리팩토링");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 840, 442);
		JPanel contentPane = new JPanel();
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
		scrollPane.setViewportView(textArea);
		
//상단 타이틀 Label--------------------------------------------------------------------------
		JLabel label1 = new JLabel("고객정보 입력 | 수정 | 삭제");
		label1.setFont(new Font("양재튼튼체B", Font.BOLD, 28));
		label1.setBounds(217, 4, 391, 39);
		panel.add(label1);
		JLabel label2 = new JLabel("운전면허번호 검색후 수정,삭제하시면 더 편리합니다.");
		label2.setBounds(265, 49, 294, 15);
		panel.add(label2);
		
//새로고침 기능------------------------------------------------------------------------------	
		refreshButton = new JButton("새로고침");
		refreshButton.setBounds(16, 18, 96, 20);
		panel.add(refreshButton);
		
//데이터 입력-------------------------------------------------------------------------------		
		readButton = new JButton("검색");
		readButton.setFont(new Font("굴림", Font.PLAIN, 10));
		readButton.setBounds(242, 98, 57, 23);
		panel.add(readButton);

		JLabel label3 = new JLabel("운전면허번호 입력");
		label3.setFont(new Font("굴림", Font.BOLD, 15));
		label3.setBounds(16, 98, 130, 21);
		panel.add(label3);

		JLabel label4 = new JLabel("이름");
		label4.setFont(new Font("굴림", Font.BOLD, 18));
		label4.setBounds(16, 141, 120, 20);
		panel.add(label4);

		JLabel label5 = new JLabel("주소");
		label5.setFont(new Font("굴림", Font.BOLD, 18));
		label5.setBounds(16, 178, 120, 20);
		panel.add(label5);

		JLabel label6 = new JLabel("번호");
		label6.setFont(new Font("굴림", Font.BOLD, 18));
		label6.setBounds(16, 221, 120, 20);
		panel.add(label6);

		JLabel label7 = new JLabel("이메일주소");
		label7.setFont(new Font("굴림", Font.BOLD, 18));
		label7.setBounds(16, 260, 120, 20);
		panel.add(label7);
		
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


