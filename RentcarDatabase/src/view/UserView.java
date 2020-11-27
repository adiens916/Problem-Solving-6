package view;

import java.util.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JRadioButton;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.SystemColor;

import controller.dataClass.CampingCarInfo;
import controller.dataClass.RentInfo;
import controller.UserController;
import controller.dataClass.ResultState;

public class UserView extends JFrame{

	public static UserView getInstance() {
		return UserViewHolder.instance;
	}

	private static class UserViewHolder {
		private static final UserView instance = new UserView();
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserView frame = new UserView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private JPanel contentPane;

	public JTextField searchCampingCar = new JTextField();
    public JButton returnButton = new JButton("반환하러가기"); 
    JTextArea listarea = new JTextArea();
	JTextArea starea = new JTextArea();
	
    JTextField rentPeriod,license,rentStartDate,extraGoodsPrice,extraGoods,rentEndDate,campingCarID;
	public JButton rentButton,backButton,searchButton,refreshButton;
	JRadioButton idRadio,nameRadio,seatRadio,manufactureRadio,mileageRadio,priceRadio;
	public ButtonGroup radioGroup;
		
	public UserView() {
		addComponent();
	}

	private void addComponent() {
		setTitle("캠핑카프로젝트 리팩토링");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1074, 532);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
//뒤로가기 버튼 기능---------------------------------------------------------------------------
		backButton = new JButton("《 뒤로가기");
		backButton.setBorder(new EmptyBorder(0, 0, 0, 0));
		backButton.setBackground(Color.WHITE);
		backButton.setBounds(0, 0, 103, 23);
		contentPane.add(backButton);
//상단 타이틀 Label--------------------------------------------------------------------------
		JLabel titleLabel = new JLabel("SEJONG CAMPING");
		titleLabel.setFont(new Font("양재백두체B", Font.BOLD, 30));
		titleLabel.setBounds(264, 14, 331, 30);
		contentPane.add(titleLabel);
		
		JLabel listLabel = new JLabel("캠핑카 LIST");
		listLabel.setFont(new Font("굴림", Font.BOLD, 20));
		listLabel.setBounds(362, 65, 114, 21);
		contentPane.add(listLabel);
		
		JLabel rentListLabel = new JLabel("대여 현황");
		rentListLabel.setFont(new Font("굴림", Font.BOLD, 18));
		rentListLabel.setBounds(902, 14, 103, 21);
		contentPane.add(rentListLabel);
//캠핑카 데이터 텍스트 영역----------------------------------------------------------------------
		listarea.setBorder(new LineBorder(Color.BLACK, 1, true));
		listarea.setBounds(12, 156, 820, 325);
		contentPane.add(listarea);
//대여 현황 텍스트 영역-------------------------------------------------------------------------		
		starea.setBorder(new LineBorder(Color.BLACK));
		starea.setBounds(844, 38, 203, 82);
		contentPane.add(starea);
		
		returnButton.setBackground(Color.WHITE);
		returnButton.setBorder(new EmptyBorder(0, 0, 0, 0));
		returnButton.setBounds(895, 130, 110, 21);
		contentPane.add(returnButton);

//우측하단 대여 영역--------------------------------------------------------------------------
		JLabel rentLabel = new JLabel("대여");
		rentLabel.setFont(new Font("굴림", Font.BOLD, 18));
		rentLabel.setBounds(926, 161, 37, 21);
		contentPane.add(rentLabel);
		
		JLabel campingCarIDLabel = new JLabel("캠핑카ID");
		campingCarIDLabel.setFont(new Font("굴림", Font.BOLD, 14));
		campingCarIDLabel.setBounds(844, 192, 81, 21);
		contentPane.add(campingCarIDLabel);
		
		JLabel licenseLabel = new JLabel("면허증번호");
		licenseLabel.setFont(new Font("굴림", Font.BOLD, 14));
		licenseLabel.setBounds(844, 234, 81, 21);
		contentPane.add(licenseLabel);
		
		JLabel rentStartDateLabel = new JLabel("대여시작일");
		rentStartDateLabel.setFont(new Font("굴림", Font.BOLD, 14));
		rentStartDateLabel.setBounds(844, 274, 81, 21);
		contentPane.add(rentStartDateLabel);
		
		JLabel rentPeriodLabel = new JLabel("대여기간");
		rentPeriodLabel.setFont(new Font("굴림", Font.BOLD, 14));
		rentPeriodLabel.setBounds(844, 312, 81, 21);
		contentPane.add(rentPeriodLabel);
		
		JLabel rentEndDateLabel = new JLabel("납입기한");
		rentEndDateLabel.setFont(new Font("굴림", Font.BOLD, 14));
		rentEndDateLabel.setBounds(844, 351, 81, 21);
		contentPane.add(rentEndDateLabel);
		
		JLabel extraGoodsLabel = new JLabel("추가물품");
		extraGoodsLabel.setFont(new Font("굴림", Font.BOLD, 14));
		extraGoodsLabel.setBounds(844, 386, 81, 21);
		contentPane.add(extraGoodsLabel);
		
		JLabel extraGoodsPriceLabel = new JLabel("물품금액");
		extraGoodsPriceLabel.setFont(new Font("굴림", Font.BOLD, 14));
		extraGoodsPriceLabel.setBounds(844, 417, 81, 21);
		contentPane.add(extraGoodsPriceLabel);
		
		campingCarID = new JTextField();
		campingCarID.setBorder(new EmptyBorder(0, 0, 0, 0));
		campingCarID.setBounds(937, 193, 96, 21);
		contentPane.add(campingCarID);
		campingCarID.setColumns(10);
		
		rentPeriod = new JTextField();
		rentPeriod.setBorder(new EmptyBorder(0, 0, 0, 0));
		rentPeriod.setColumns(10);
		rentPeriod.setBounds(937, 312, 96, 21);
		contentPane.add(rentPeriod);
		
		license = new JTextField();
		license.setBorder(new EmptyBorder(0, 0, 0, 0));
		license.setColumns(10);
		license.setBounds(937, 234, 96, 21);
		contentPane.add(license);
		
		rentStartDate = new JTextField();
		rentStartDate.setBorder(new EmptyBorder(0, 0, 0, 0));
		rentStartDate.setColumns(10);
		rentStartDate.setBounds(937, 274, 96, 21);
		contentPane.add(rentStartDate);
		
		extraGoodsPrice = new JTextField();
		extraGoodsPrice.setBorder(new EmptyBorder(0, 0, 0, 0));
		extraGoodsPrice.setColumns(10);
		extraGoodsPrice.setBounds(937, 418, 96, 21);
		contentPane.add(extraGoodsPrice);
		
		extraGoods = new JTextField();
		extraGoods.setBorder(new EmptyBorder(0, 0, 0, 0));
		extraGoods.setColumns(10);
		extraGoods.setBounds(937, 386, 96, 21);
		contentPane.add(extraGoods);
		
		rentEndDate = new JTextField();
		rentEndDate.setBorder(new EmptyBorder(0, 0, 0, 0));
		rentEndDate.setColumns(10);
		rentEndDate.setBounds(937, 351, 96, 21);
		contentPane.add(rentEndDate);

//검색기능 (라디오 버튼, 검색 버튼, 새로고침 버튼)-----------------------------------------------------

		searchCampingCar = new JTextField();
		searchCampingCar.setBorder(new EmptyBorder(0, 0, 0, 0));
		searchCampingCar.setColumns(10);
		searchCampingCar.setBounds(328, 125, 91, 21);
		contentPane.add(searchCampingCar);
		
		idRadio = new JRadioButton("캠핑카ID");
		idRadio.setBackground(SystemColor.menu);
		idRadio.setBounds(138, 92, 81, 23);
		idRadio.setActionCommand("캠핑카ID");
		contentPane.add(idRadio);
		
		nameRadio = new JRadioButton("차명");
		nameRadio.setBackground(SystemColor.menu);
		nameRadio.setBounds(217, 92, 53, 23);
		nameRadio.setActionCommand("차명");
		contentPane.add(nameRadio);
		
		seatRadio = new JRadioButton("최소승차인원");
		seatRadio.setBackground(SystemColor.menu);
		seatRadio.setBounds(270, 92, 101, 23);
		seatRadio.setActionCommand("최소승차인원");
		contentPane.add(seatRadio);
		
		manufactureRadio = new JRadioButton("제조회사");
		manufactureRadio.setBackground(SystemColor.menu);
		manufactureRadio.setBounds(371, 92, 80, 23);
		manufactureRadio.setActionCommand("제조회사");
		contentPane.add(manufactureRadio);
		
		mileageRadio = new JRadioButton("최대주행거리");
		mileageRadio.setBackground(SystemColor.menu);
		mileageRadio.setBounds(446, 92, 101, 23);
		mileageRadio.setActionCommand("최대주행거리");
		contentPane.add(mileageRadio);
		
		priceRadio = new JRadioButton("최대대여비용(단위:만원)");
		priceRadio.setBackground(SystemColor.menu);
		priceRadio.setBounds(543, 92, 160, 23);
		priceRadio.setActionCommand("최대대여비용");
		contentPane.add(priceRadio);
		
		// UserView 조건 검색 Radio Button Group
		radioGroup = new ButtonGroup();
		radioGroup.add(idRadio);
		radioGroup.add(nameRadio);
		radioGroup.add(seatRadio);
		radioGroup.add(manufactureRadio);
		radioGroup.add(mileageRadio);
		radioGroup.add(priceRadio);
		
		// 조건 검색 1~6 라디오 선택에 따라.
		searchButton = new JButton("검색");
		searchButton.setBorder(new EmptyBorder(0, 0, 0, 0));
		searchButton.setBackground(Color.WHITE);
		searchButton.setBounds(425, 125, 61, 21);
		contentPane.add(searchButton);
		
		refreshButton = new JButton("새로고침");
		refreshButton.setBorder(new EmptyBorder(0, 0, 0, 0));
		refreshButton.setBackground(Color.WHITE);
		refreshButton.setBounds(489, 125, 91, 21);
		contentPane.add(refreshButton);
		
//대여버튼 기능-------------------------------------------------------------------------	
		rentButton = new JButton("대여");
		rentButton.setBackground(Color.WHITE);
		rentButton.setBorder(new EmptyBorder(0, 0, 0, 0));
		rentButton.setBounds(902, 450, 91, 23);
		contentPane.add(rentButton);
	}
	
	private String getCampingCarColumnList() {
		return "캠핑카ID \t 차명 \t 차량번호 \t 승차인원수 \t 제조회사 \t 제조연도 \t 누적주행거리 \t 대여비용 \t캠핑카등록일자 \t 대여회사ID \n";
	}
	
	private String getRentColumnList() {
		return "번호 \t 캠핑카ID \t 금액 \n";
	}
	
	public void readRentableCampingCarList(ArrayList<CampingCarInfo> rentableCampingCarList) {
		listarea.setText("");
		String list = getCampingCarColumnList();
		for (CampingCarInfo CampingCar : rentableCampingCarList ) {
			list += toStringFromCampingCarInfo(CampingCar);
		}
		listarea.append(list);
	}
	
	public void readRentList(ArrayList<RentInfo> rentList) {
		starea.setText("");
		String list = getRentColumnList();
		int rentNumber = 1;
		for (RentInfo rent : rentList) {
			list += rentNumber + toStringFromRentInfo(rent);
			rentNumber++;
		}
		starea.append(list);
	}
	
	private String toStringFromCampingCarInfo(CampingCarInfo campingCar) {
		return  campingCar.id + '\t' +
				campingCar.name + '\t' +
				campingCar.number + '\t' +
				campingCar.seats+ '\t' +
				campingCar.manufacturer + '\t' +
				campingCar.builtDate+ '\t' +
				campingCar.mileage + '\t' +
				campingCar.rentalFee + '\t' +
				campingCar.registryDate + '\t' +
				campingCar.companyId + '\n';
	}
	
	private String toStringFromRentInfo(RentInfo rent) {
		return  '\t' +
				rent.campingCarID + '\t' /*+
				rent.rentPrice + '\n'*/;
	}
	
	public RentInfo getRentInput() {
		RentInfo rent = new RentInfo();
		
		rent.campingCarID = campingCarID.getText();
		rent.license = license.getText();
		rent.rentStartDate = rentStartDate.getText();
		rent.rentPeriod = rentPeriod.getText();
		rent.rentEndDate = rentEndDate.getText();
		rent.extraGoods = extraGoods.getText();
		rent.extraGoodsPrice = extraGoodsPrice.getText();
		
		return rent;
	}
	
	public void showRentResult(ResultState result) {
		if (result == ResultState.SUCCESS) {
			JOptionPane.showMessageDialog(null,"대여 완료");
		} else {
			JOptionPane.showMessageDialog(null, "대여 실패 모든 대여 정보 칸을 채워주세요");
		}
	}
	/*private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}*/
}
