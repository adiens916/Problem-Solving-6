package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import model.dataClass.CampingCarDataClass;
import model.dataClass.ResultStateDataClass;

public class CampingCarView extends AbstractView<CampingCarDataClass>  {

	private JTextField campingCarIdTextField;
	private JTextField campingCarNameTextField;
	private JTextField campingCarNumberTextField;
	private JTextField campingCarSitsTextField;
	private JTextField campingCarManufacutreTextField;
	private JTextField campingCarManufactureYearTextField;
	private JTextField campingCarMileageTextField;
	private JTextField campingCarRentpriceTextField;
	private JTextField campingCarRegitstdateTextField;
	private JTextField campingCarRentCompanyIdTextField;

	@Override
	public void refreshInput() {
		campingCarIdTextField.setText("");
		campingCarNameTextField.setText("");
		campingCarNumberTextField.setText("");
		campingCarSitsTextField.setText("");
		campingCarManufacutreTextField.setText("");
		campingCarManufactureYearTextField.setText("");
		campingCarMileageTextField.setText("");
		campingCarRentpriceTextField.setText("");
		campingCarRegitstdateTextField.setText("");
		campingCarRentCompanyIdTextField.setText("");

	}

	@Override
	public CampingCarDataClass getInput() {
		CampingCarDataClass campingCar = new CampingCarDataClass();
		campingCar.campingCarId = campingCarIdTextField.getText();
		campingCar.campingCarName = campingCarNameTextField.getText();
		campingCar.campingCarNumber = campingCarNumberTextField.getText();
		campingCar.campingCarSits = campingCarSitsTextField.getText();
		campingCar.campingCarManufacutre = campingCarManufacutreTextField.getText();
		campingCar.campingCarManufactureYear = campingCarManufactureYearTextField.getText();
		campingCar.campingCarMileage = campingCarMileageTextField.getText();
		campingCar.campingCarRentprice = campingCarRentpriceTextField.getText();
		campingCar.campingCarRegitstdate = campingCarRegitstdateTextField.getText();
		campingCar.campingCarRentCompanyId = campingCarRentCompanyIdTextField.getText();
		return campingCar;
	}

	@Override
	public String getId() {
		return campingCarIdTextField.getText();
	}

	@Override
	String getColumnList() {
		return "캠핑카ID \t 차명 \t 차량번호 \t 승차인원수 \t 제조회사 \t 제조연도 \t 누적주행거리 \t 대여비용 \t캠핑카등록일자 \t 대여회사ID \n";
	}

	@Override
	String toStringFromInfo(CampingCarDataClass campingCar) {
		return campingCar.campingCarId + '\t' + campingCar.campingCarName + '\t' + campingCar.campingCarNumber + '\t'
				+ campingCar.campingCarSits + '\t' + campingCar.campingCarManufacutre + '\t'
				+ campingCar.campingCarManufactureYear + '\t' + campingCar.campingCarMileage + '\t'
				+ campingCar.campingCarRentprice + '\t' + campingCar.campingCarRegitstdate + '\t'
				+ campingCar.campingCarRentCompanyId + '\n';
	}

	@Override
	void addComponent() {
		setTitle("캠핑카프로젝트 리팩토링");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1174, 496);
		JPanel contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPanel);
		
		JPanel panel = new JPanel();
		contentPanel.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

// 데이터 출력 영역----------------------------------------------------------------------------
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(311, 98, 840, 343);
		panel.add(scrollPane);
		scrollPane.setViewportView(textArea);

		JLabel guideLabel1 = new JLabel("캠핑카정보 입력 | 수정 | 삭제");
		guideLabel1.setFont(new Font("양재튼튼체B", Font.BOLD, 28));
		guideLabel1.setBounds(457, 0, 443, 39);
		panel.add(guideLabel1);
		
		JLabel guideLabel2 = new JLabel("캠핑카ID 검색후 수정,삭제하시면 더 편리합니다.");
		guideLabel2.setBounds(526, 49, 273, 15);
		panel.add(guideLabel2);
		
//새로고침 기능------------------------------------------------------------------------------
		refreshButton = new JButton("새로고침");
		refreshButton.setBounds(16, 18, 96, 20);
		panel.add(refreshButton);
		
//데이터 입력-------------------------------------------------------------------------------		
		readButton = new JButton("검색");
		readButton.setFont(new Font("굴림", Font.PLAIN, 10));
		readButton.setBounds(210, 48, 57, 23);
		panel.add(readButton);

		//라벨
		JLabel guideLabel3 = new JLabel("캠핑카ID입력");
		guideLabel3.setFont(new Font("굴림", Font.BOLD, 16));
		guideLabel3.setBounds(16, 49, 110, 20);
		panel.add(guideLabel3);

		JLabel campingCarNameLabel = new JLabel("차명");
		campingCarNameLabel.setFont(new Font("굴림", Font.BOLD, 16));
		campingCarNameLabel.setBounds(16, 80, 110, 20);
		panel.add(campingCarNameLabel);

		JLabel campingCarNumberLabel = new JLabel("차량번호");
		campingCarNumberLabel.setFont(new Font("굴림", Font.BOLD, 16));
		campingCarNumberLabel.setBounds(16, 115, 102, 20);
		panel.add(campingCarNumberLabel);
	
		JLabel campingCarSitsLabel = new JLabel("승차인원수");
		campingCarSitsLabel.setFont(new Font("굴림", Font.BOLD, 16));
		campingCarSitsLabel.setBounds(16, 146, 102, 20);
		panel.add(campingCarSitsLabel);

		JLabel campingCarManufacutreLabel = new JLabel("제조회사");
		campingCarManufacutreLabel.setFont(new Font("굴림", Font.BOLD, 16));
		campingCarManufacutreLabel.setBounds(16, 184, 102, 20);
		panel.add(campingCarManufacutreLabel);

		JLabel campingCarManufactureYearLabel = new JLabel("제조년도");
		campingCarManufactureYearLabel.setFont(new Font("굴림", Font.BOLD, 16));
		campingCarManufactureYearLabel.setBounds(16, 218, 102, 20);
		panel.add(campingCarManufactureYearLabel);

		JLabel campingCarMileageLabel = new JLabel("누적주행거리");
		campingCarMileageLabel.setFont(new Font("굴림", Font.BOLD, 16));
		campingCarMileageLabel.setBounds(16, 251, 102, 20);
		panel.add(campingCarMileageLabel);

		JLabel campingCarRentpriceLabel = new JLabel("대여비용");
		campingCarRentpriceLabel.setFont(new Font("굴림", Font.BOLD, 16));
		campingCarRentpriceLabel.setBounds(16, 282, 102, 20);
		panel.add(campingCarRentpriceLabel);

		JLabel campingCarRegitstdateLabel = new JLabel("차등록일자");
		campingCarRegitstdateLabel.setFont(new Font("굴림", Font.BOLD, 16));
		campingCarRegitstdateLabel.setBounds(16, 345, 102, 20);
		panel.add(campingCarRegitstdateLabel);

		JLabel campingCarRentCompanyIdLabel = new JLabel("대여회사ID");
		campingCarRentCompanyIdLabel.setFont(new Font("굴림", Font.BOLD, 16));
		campingCarRentCompanyIdLabel.setBounds(16, 313, 102, 20);
		panel.add(campingCarRentCompanyIdLabel);
		
		//텍스트 필드
		campingCarIdTextField = new JTextField();
		campingCarIdTextField.setColumns(10);
		campingCarIdTextField.setBounds(126, 49, 72, 20);
		panel.add(campingCarIdTextField);
		
		campingCarNameTextField = new JTextField();
		campingCarNameTextField.setColumns(10);
		campingCarNameTextField.setBounds(126, 81, 141, 21);
		panel.add(campingCarNameTextField);

		campingCarNumberTextField = new JTextField();
		campingCarNumberTextField.setColumns(10);
		campingCarNumberTextField.setBounds(126, 112, 141, 21);
		panel.add(campingCarNumberTextField);
		
		campingCarSitsTextField = new JTextField();
		campingCarSitsTextField.setColumns(10);
		campingCarSitsTextField.setBounds(126, 143, 141, 21);
		panel.add(campingCarSitsTextField);
		
		campingCarManufacutreTextField = new JTextField();
		campingCarManufacutreTextField.setColumns(10);
		campingCarManufacutreTextField.setBounds(126, 181, 141, 21);
		panel.add(campingCarManufacutreTextField);

		campingCarMileageTextField = new JTextField();
		campingCarMileageTextField.setColumns(10);
		campingCarMileageTextField.setBounds(126, 248, 141, 21);
		panel.add(campingCarMileageTextField);

		campingCarRentpriceTextField = new JTextField();
		campingCarRentpriceTextField.setColumns(10);
		campingCarRentpriceTextField.setBounds(126, 279, 141, 21);
		panel.add(campingCarRentpriceTextField);

		campingCarRentCompanyIdTextField = new JTextField();
		campingCarRentCompanyIdTextField.setColumns(10);
		campingCarRentCompanyIdTextField.setBounds(126, 310, 141, 21);
		panel.add(campingCarRentCompanyIdTextField);

		campingCarRegitstdateTextField = new JTextField();
		campingCarRegitstdateTextField.setColumns(10);
		campingCarRegitstdateTextField.setBounds(126, 345, 141, 21);
		panel.add(campingCarRegitstdateTextField);
		
		campingCarManufactureYearTextField = new JTextField();
		campingCarManufactureYearTextField.setColumns(10);
		campingCarManufactureYearTextField.setBounds(126, 217, 141, 21);
		panel.add(campingCarManufactureYearTextField);
		
//하단에 입력, 수정, 삭제 버튼 기능-------------------------------------------------------------
		createButton = new JButton("입력");
		createButton.setForeground(Color.BLACK);
		createButton.setFont(new Font("굴림", Font.BOLD, 15));
		createButton.setBounds(34, 380, 70, 29);
		panel.add(createButton);

		updateButton = new JButton("수정");
		updateButton.setForeground(Color.BLACK);
		updateButton.setFont(new Font("굴림", Font.BOLD, 15));
		updateButton.setBounds(115, 380, 70, 29);
		panel.add(updateButton);

		deleteButton = new JButton("삭제");
		deleteButton.setForeground(Color.BLACK);
		deleteButton.setFont(new Font("굴림", Font.BOLD, 15));
		deleteButton.setBounds(197, 380, 70, 29);
		panel.add(deleteButton);

		quit = new JButton("닫기");
		quit.setForeground(Color.BLACK);
		quit.setFont(new Font("굴림", Font.BOLD, 15));
		quit.setBounds(115, 419, 70, 22);
		panel.add(quit);
	}
}