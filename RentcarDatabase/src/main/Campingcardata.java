package main;

import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.dataClass.CampingCarInfo;
import model.CampingCarModel;


public class Campingcardata extends JFrame{

	private JPanel contentPane;
	JTextField campingCarIdField;
	JButton btnNewButton_1_3;
	JButton btnNewButton_1_3_1;
	JButton btnNewButton_1_3_2;
	JButton quit;
	JTextField campingCarNameField;
	JTextField campingCarNumberField;
	JTextField campingCarSeatsField;
	JTextField campingCarManufacturerField;
	JTextField campingCarBuiltDateField;
	JTextArea selectcp = new JTextArea();
	JButton btnNewButton_1;
	private JLabel lblNewLabel;
	private JTextField campingCarMileageField;
	private JTextField campingCarRentalFeeField;
	private JTextField companyIdField;
	private JTextField campingCarRegistryDateField;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_7;
	private JLabel lblNewLabel_8;
	private JLabel lblNewLabel_9;
	private JLabel lblNewLabel_10;
	private JLabel lblNewLabel_11;

/* 모델을 쓰기 위해 모델 부분 추가 */
	private CampingCarModel model = new CampingCarModel();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Campingcardata frame = new Campingcardata();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

/* 대여 가능한 캠핑카 리스트 출력 */
/* [컨트롤러 부분] 모델에서 데이터를 가져와 뷰로 넘겨줌  */
	public void printdata() {
		ArrayList<CampingCarInfo> campingCarList = model.readCampingCarList();
		showCampingCarList(campingCarList);
	}

/* [뷰 부분] 컨트롤러가 넘겨준 캠핑카 리스트를 가공해서 보여줌 */
	public void showCampingCarList(ArrayList<CampingCarInfo> campingCarList) {
		selectcp.setText("캠핑카ID \t 차명 \t 차량번호 \t 승차인원수 \t 제조회사 \t 제조연도 \t 누적주행거리 \t 대여비용 \t캠핑카등록일자 \t 대여회사ID \n");
		for (CampingCarInfo campingCar : campingCarList) {
			selectcp.append(toStringFromCampingCarInfo(campingCar));
		}
	}

/* [뷰 부분] 캠핑카 정보에서 필요한 부분만 가져와 스트링으로 만듦 */
	public String toStringFromCampingCarInfo(CampingCarInfo campingCar) {
		String str =
				campingCar.id + "\t" +
				campingCar.name + "\t" +
				campingCar.number + "\t" +
				campingCar.seats + "\t" +
				campingCar.manufacturer + "\t" +
				campingCar.builtDate + "\t" +
				campingCar.mileage + "\t" +
				campingCar.rentalFee + "\t" +
				campingCar.registryDate + "\t" +
				campingCar.companyId + "\n";
		return str;
	}


	public Campingcardata() {
		setTitle("17013152 최종혁 -캠핑카 정보관리페이지");

		// model.conDB();

		printdata();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1174, 496);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		/* 출력 영역 */
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(311, 98, 840, 343);
		panel.add(scrollPane);
		
		
		scrollPane.setViewportView(selectcp);
		
		campingCarIdField = new JTextField();
		campingCarIdField.setColumns(10);
		campingCarIdField.setBounds(126, 49, 72, 20);
		panel.add(campingCarIdField);
			
		
		JButton btnNewButton = new JButton("검색");
		btnNewButton.setFont(new Font("굴림", Font.PLAIN, 10));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
/* 검색을 MVC 패턴화 */
				String id = campingCarIdField.getText(); // [컨트롤러] 뷰에서 사용자 입력 가져 옴 (getText가 [뷰])
				CampingCarInfo target = model.readCampingCar(id); // [컨트롤러] 모델에 값을 전달하고 결과값 가져옴
				selectcp.setText(""); // [이하 뷰 영역] 결과값을 레이아웃에 맞게 출력
				selectcp.setText("캠핑카ID \t 차명 \t 차량번호 \t 승차인원수 \t 제조회사 \t 제조연도 \t 누적주행거리 \t 대여비용 \t캠핑카등록일자 \t 대여회사ID \n");
				selectcp.append(toStringFromCampingCarInfo(target));
			}
		});
		btnNewButton.setBounds(210, 48, 57, 23);
		panel.add(btnNewButton);
		
		campingCarNameField = new JTextField();
		campingCarNameField.setColumns(10);
		campingCarNameField.setBounds(126, 81, 141, 21);
		panel.add(campingCarNameField);
		
		campingCarNumberField = new JTextField();
		campingCarNumberField.setColumns(10);
		campingCarNumberField.setBounds(126, 112, 141, 21);
		panel.add(campingCarNumberField);
		
		campingCarSeatsField = new JTextField();
		campingCarSeatsField.setColumns(10);
		campingCarSeatsField.setBounds(126, 143, 141, 21);
		panel.add(campingCarSeatsField);
		
		campingCarManufacturerField = new JTextField();
		campingCarManufacturerField.setColumns(10);
		campingCarManufacturerField.setBounds(126, 181, 141, 21);
		panel.add(campingCarManufacturerField);
		
		campingCarBuiltDateField = new JTextField();
		campingCarBuiltDateField.setColumns(10);
		campingCarBuiltDateField.setBounds(126, 217, 141, 21);
		panel.add(campingCarBuiltDateField);
		btnNewButton_1_3 = new JButton("수정");
		btnNewButton_1_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnNewButton_1_3.setForeground(Color.BLACK);
		btnNewButton_1_3.setFont(new Font("굴림", Font.BOLD, 15));
		btnNewButton_1_3.setBounds(115, 380, 70, 29);
		panel.add(btnNewButton_1_3);
		
		
		btnNewButton_1_3_1 = new JButton("삭제");
		btnNewButton_1_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
/* 삭제 부분을 MVC 패턴화 */
				String id = campingCarIdField.getText(); // [컨트롤러] getText(뷰 영역)을 통해 사용자 입력 가져 옴
				String result = model.deleteCampingCar(id);
				if (result.equals("SUCCESS")) {
					JOptionPane.showMessageDialog(btnNewButton_1_3_1, "삭제 완료");
					campingCarIdField.setText("");
				} else {
					JOptionPane.showMessageDialog(btnNewButton_1_3_1, "ID를 입력해주세요.");
				}
				printdata();
			}
		});
		btnNewButton_1_3_1.setForeground(Color.BLACK);
		btnNewButton_1_3_1.setFont(new Font("굴림", Font.BOLD, 15));
		btnNewButton_1_3_1.setBounds(197, 380, 70, 29);
		panel.add(btnNewButton_1_3_1);
		
		quit = new JButton("닫기");
		quit.setForeground(Color.BLACK);
		quit.setFont(new Font("굴림", Font.BOLD, 15));
		quit.setBounds(115, 419, 70, 22);
		panel.add(quit);
		
		btnNewButton_1 = new JButton("새로고침");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
/* 컨트롤러 + 뷰로 구성됨 */
				printdata();
				campingCarNameField.setText("");
				campingCarNumberField.setText("");
				campingCarSeatsField.setText("");
				campingCarManufacturerField.setText("");
				campingCarBuiltDateField.setText("");
				campingCarMileageField.setText("");
				campingCarRentalFeeField.setText("");
				campingCarRegistryDateField.setText("");
				companyIdField.setText("");
				campingCarIdField.setText("");
			}
		});
		btnNewButton_1.setBounds(16, 18, 96, 20);
		panel.add(btnNewButton_1);
		
		btnNewButton_1_3_2 = new JButton("입력");
		btnNewButton_1_3_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CampingCarInfo campingCar = getCampingCarInput();
				String result = model.createCampingCar(campingCar);
				if (result.equals("SUCCESS")) {
					JOptionPane.showMessageDialog(btnNewButton_1_3_2, "입력완료!");
				} else if (result.equals("NULL")) {
					JOptionPane.showMessageDialog(btnNewButton_1_3_2, "빈칸을 모두채워주세요");
				} else {
					JOptionPane.showMessageDialog(btnNewButton_1_3_2, "다시입력해주세요!");
				}
				printdata();
			}
		});
		btnNewButton_1_3_2.setForeground(Color.BLACK);
		btnNewButton_1_3_2.setFont(new Font("굴림", Font.BOLD, 15));
		btnNewButton_1_3_2.setBounds(34, 380, 70, 29);
		panel.add(btnNewButton_1_3_2);
		
		lblNewLabel = new JLabel("캠핑카ID 검색후 수정,삭제하시면 더 편리합니다.");
		lblNewLabel.setBounds(526, 49, 273, 15);
		panel.add(lblNewLabel);
		
		campingCarMileageField = new JTextField();
		campingCarMileageField.setColumns(10);
		campingCarMileageField.setBounds(126, 248, 141, 21);
		panel.add(campingCarMileageField);
		
		campingCarRentalFeeField = new JTextField();
		campingCarRentalFeeField.setColumns(10);
		campingCarRentalFeeField.setBounds(126, 279, 141, 21);
		panel.add(campingCarRentalFeeField);
		
		companyIdField = new JTextField();
		companyIdField.setColumns(10);
		companyIdField.setBounds(126, 310, 141, 21);
		panel.add(companyIdField);
		
		campingCarRegistryDateField = new JTextField();
		campingCarRegistryDateField.setColumns(10);
		campingCarRegistryDateField.setBounds(126, 345, 141, 21);
		panel.add(campingCarRegistryDateField);
		
		lblNewLabel_1 = new JLabel("캠핑카ID입력");
		lblNewLabel_1.setFont(new Font("굴림", Font.BOLD, 16));
		lblNewLabel_1.setBounds(16, 49, 110, 20);
		panel.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("캠핑카정보 입력 | 수정 | 삭제");
		lblNewLabel_2.setFont(new Font("양재튼튼체B", Font.BOLD, 28));
		lblNewLabel_2.setBounds(457, 0, 443, 39);
		panel.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("차명");
		lblNewLabel_3.setFont(new Font("굴림", Font.BOLD, 16));
		lblNewLabel_3.setBounds(16, 80, 110, 20);
		panel.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("차량번호");
		lblNewLabel_4.setFont(new Font("굴림", Font.BOLD, 16));
		lblNewLabel_4.setBounds(16, 115, 102, 20);
		panel.add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("승차인원수");
		lblNewLabel_5.setFont(new Font("굴림", Font.BOLD, 16));
		lblNewLabel_5.setBounds(16, 146, 102, 20);
		panel.add(lblNewLabel_5);
		
		lblNewLabel_6 = new JLabel("제조회사");
		lblNewLabel_6.setFont(new Font("굴림", Font.BOLD, 16));
		lblNewLabel_6.setBounds(16, 184, 102, 20);
		panel.add(lblNewLabel_6);
		
		lblNewLabel_7 = new JLabel("제조년도");
		lblNewLabel_7.setFont(new Font("굴림", Font.BOLD, 16));
		lblNewLabel_7.setBounds(16, 218, 102, 20);
		panel.add(lblNewLabel_7);
		
		lblNewLabel_8 = new JLabel("누적주행거리");
		lblNewLabel_8.setFont(new Font("굴림", Font.BOLD, 16));
		lblNewLabel_8.setBounds(16, 251, 102, 20);
		panel.add(lblNewLabel_8);
		
		lblNewLabel_9 = new JLabel("대여비용");
		lblNewLabel_9.setFont(new Font("굴림", Font.BOLD, 16));
		lblNewLabel_9.setBounds(16, 282, 102, 20);
		panel.add(lblNewLabel_9);
		
		lblNewLabel_10 = new JLabel("대여회사ID");
		lblNewLabel_10.setFont(new Font("굴림", Font.BOLD, 16));
		lblNewLabel_10.setBounds(16, 313, 102, 20);
		panel.add(lblNewLabel_10);
		
		lblNewLabel_11 = new JLabel("차등록일자");
		lblNewLabel_11.setFont(new Font("굴림", Font.BOLD, 16));
		lblNewLabel_11.setBounds(16, 345, 102, 20);
		panel.add(lblNewLabel_11);
	}

/* [뷰 영역에 속함] 사용자 입력을 데이터 클래스로 모아서 반환 = 컨트롤러에게 넘겨 줄 것 */
	private CampingCarInfo getCampingCarInput() {
		CampingCarInfo campingCar = new CampingCarInfo();
		campingCar.name = campingCarNameField.getText();
		campingCar.number = campingCarNumberField.getText();
		campingCar.seats = campingCarSeatsField.getText();
		campingCar.manufacturer = campingCarManufacturerField.getText();
		campingCar.builtDate = campingCarBuiltDateField.getText();
		campingCar.mileage = campingCarMileageField.getText();
		campingCar.rentalFee = campingCarRentalFeeField.getText();
		campingCar.registryDate = campingCarRegistryDateField.getText();
		campingCar.companyId = companyIdField.getText();
		return campingCar;
	}
}


