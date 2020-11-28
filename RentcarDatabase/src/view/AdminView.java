package view;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controller.*;
import controller.dataClass.*;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Component;
import javax.swing.border.CompoundBorder;

public class AdminView extends JFrame {
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminView frame = new AdminView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
		
	private JPanel contentPanel;
	public JTextField repairListLog,repairListFixDate,repairListprice,repairListDuedate,repairListOtherInfo,torepairId, garageId;

	//------------------------------------------

	CompanyController companyController = new CompanyController();
	CampingCarController campingCarController = new CampingCarController();
	CustomerController customerController = new CustomerController();
	GarageController garageController = new GarageController();
	AdminInfo admin = new AdminInfo();
	
	public JTextArea campingCarText=new JTextArea();
	public JTextArea searchText = new JTextArea();
    public JTextArea garageText = new JTextArea();

    public JButton backButton = new JButton("《 뒤로가기");
    JButton resetButton = new JButton("초기화");
    JButton searchButton1 = new JButton("검색1");
    JButton searchButton2 = new JButton("검색1");
    JButton searchButton3 = new JButton("검색1");
    JButton searchButton4 = new JButton("검색1");
	JButton insertToGarageButton = new JButton("정비소로보내기");
	JButton returnButton = new JButton("반 환 하 기");
	
	JLabel fix1Label = new JLabel("수리여부 1 : 수리필요");
	JLabel fix2Label = new JLabel("수리여부 0 : 수리필요없음");
	JLabel returnTextLabel = new JLabel("반환 내역");
	JLabel selectGarageLabel = new JLabel("캠핑카정비소 선택");
	JLabel searchLabel = new JLabel("검색");
	JLabel infoLabel = new JLabel("※ 수리여부가 1인 캠핑카의 고유대여ID와 정비소ID를 입력 후  추가정보를 입력");
	JLabel campingCarIdLabel = new JLabel("캠핑카ID");
	JLabel garageIdLabel = new JLabel("정비소ID");
	JLabel garageResultLabel = new JLabel("정비내역");
	JLabel fixDateLabel = new JLabel("수리날짜");
	JLabel fixPriceLabel = new JLabel("수리비용");
	JLabel fixDueDateLabel = new JLabel("납입기한");
	JLabel othersLabel = new JLabel("기타내역정보");
    JLabel search1 = new JLabel("1.   수리목록 중에서 10만원이상 내역이 나온 고객이름");	
	JLabel search2 = new JLabel("2.   캠핑카의 청구요금이 50만원 이상 지불해야 할 고객이름");
	JLabel search3 = new JLabel("3.   수리할 필요가 없는, 수리필요여부가 1인 캠핑카");
	JLabel search4 = new JLabel("4.   대여기간이 10일이상인 고객이름");

	CRUDMenu<CompanyController> menuCompany;
	CRUDMenu<CampingCarController> menuCampingcar;
	CRUDMenu<CustomerController> menuCustomer;
	CRUDMenu<GarageController> menuGarage;
	AdminController adminController;
	

	//------------------------------------------------------------
	public AdminView() {
		setTitle("캠핑카프로젝트 리팩토링");
		
		//컨트롤러 파트---------------------------------
		adminController = new AdminController();
		campingCarText.setText(adminController.printCampingcarList());
		garageText.setText(adminController.printGarageList());

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1094, 565);
//영역 설정------------------------------------------------------------------------------------------		
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPanel);
		JPanel panel = new JPanel();
		contentPanel.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		fix1Label.setFont(new Font("굴림", Font.BOLD, 14));
		fix1Label.setBounds(113, 12, 142, 21);
		panel.add(fix1Label);
		
		fix2Label.setFont(new Font("굴림", Font.BOLD, 14));
		fix2Label.setBounds(267, 13, 171, 18);
		panel.add(fix2Label);
		
		//초기화 버튼---------------------------------------------------------------------------------
		resetButton.setForeground(Color.WHITE);
		resetButton.setBounds(450, 13, 51, 19);
		panel.add(resetButton);
		resetButton.setBorder(new CompoundBorder());
		resetButton.setBackground(new Color(205, 133, 63));
		
		//뒤로가기 버튼--------------------------------------------------------------------------------
		backButton.setForeground(Color.WHITE);
		backButton.setBounds(513, 13, 66, 19);
		panel.add(backButton);
		backButton.setBorder(new CompoundBorder());
		backButton.setBackground(new Color(205, 133, 63));
		
		
		//반환 내역이 출력되는 필드-------------------------------------------------------------------------
		returnTextLabel.setFont(new Font("굴림", Font.BOLD, 20));
		returnTextLabel.setBounds(12, 10, 110, 21);
		panel.add(returnTextLabel);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 41, 586, 200);
		panel.add(scrollPane);
		scrollPane.setViewportView(campingCarText);
		
		//캠핑카 정비소가 출력되는 필드----------------------------------------------------------------------
		selectGarageLabel.setFont(new Font("굴림", Font.BOLD, 20));
		selectGarageLabel.setBounds(12, 251, 193, 21);
		panel.add(selectGarageLabel);
		JScrollPane scrollPane1 = new JScrollPane();
		scrollPane1.setBounds(12, 282, 586, 203);
		panel.add(scrollPane1);
		scrollPane1.setViewportView(garageText);
		
//상단바 설정-----------------------------------------------------------------------------------------
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorderPainted(false);
		menuBar.setAlignmentY(Component.CENTER_ALIGNMENT);
		setJMenuBar(menuBar);
		
//------대여회사 메뉴 설정-------------------------------------------------------------------------------
		menuCompany = new CRUDMenu<>("대여회사", companyController, this);
		menuBar.add(menuCompany);

//------캠핑카 메뉴 설정--------------------------------------------------------------------------------
		menuCampingcar = new CRUDMenu<>("캠핑카", campingCarController, this);
		menuBar.add(menuCampingcar);

//------고객 메뉴 설정---------------------------------------------------------------------------------
		menuCustomer = new CRUDMenu<>("고객", customerController, this);
		menuBar.add(menuCustomer);

//------정비소 메뉴 설정---------------------------------------------------------------------------------
		menuGarage = new CRUDMenu<>("정비소", garageController, this);
		menuBar.add(menuGarage);

//------우측상단 영역 설정------------------------------------------------------------------------------	
		searchLabel.setFont(new Font("굴림", Font.BOLD, 20));
		searchLabel.setBounds(614, 10, 110, 21);
		panel.add(searchLabel);
		
		searchButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchText.setText("검색1 결과\n");
				adminController.printSearch(1,searchText);
			}
		});
		searchButton1.setBounds(955, 26, 105, 23);
		panel.add(searchButton1);
				
		searchButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchText.setText("검색2 결과\n");
				adminController.printSearch(2,searchText);
			}
		});
		searchButton2.setBounds(955, 53, 105, 23);
		panel.add(searchButton2);
		
		
		searchButton3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchText.setText("검색3\n");
				adminController.printSearch(3,searchText);
			}
		});
		searchButton3.setBounds(955, 79, 105, 23);
		panel.add(searchButton3);
		
		searchButton4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchText.setText("검색4 결과\n");
				adminController.printSearch(4,searchText);
			}
		});
		searchButton4.setBounds(955, 105, 105, 23);
		panel.add(searchButton4);
		
		search1.setBounds(614, 30, 321, 15);
		panel.add(search1);
		
		search2.setBounds(614, 57, 329, 15);
		panel.add(search2);
		
		search3.setBounds(614, 83, 329, 15);
		panel.add(search3);
		
		search4.setBounds(614, 109, 321, 15);
		panel.add(search4);
		
		//검색결과 출력되는 곳-------------------------------------------------------------------------------
		searchText.setBounds(614, 138, 446, 101);
		panel.add(searchText);
		
//우측하단 영역 설정-----------------------------------------------------------------------------------------		
		infoLabel.setBounds(614, 279, 446, 26);
		panel.add(infoLabel);
		//------정비소로 보내기-----------------------------------------------------------------------------
		insertToGarageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<AdminInfo> adminData = new ArrayList<>();
				getInput();
           	 	adminData.add(admin);
				adminController.insetGarageData(adminData);
				refreshInput();
			}
		});
		insertToGarageButton.setFont(new Font("굴림", Font.BOLD, 15));
		insertToGarageButton.setBounds(889, 315, 142, 77);
		panel.add(insertToGarageButton);
		
		//------다시 캠핑카 업체로 반환하기---------------------------------------------------------------------
		returnButton.setFont(new Font("굴림", Font.BOLD, 18));
		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adminController.returnToCampingCarData(torepairId.getText());
				refreshInput();
			}
		});
		returnButton.setBounds(889, 402, 142, 77);
		panel.add(returnButton);
		
		//-------데이터 입력------------------------------------------------------------------------------
		campingCarIdLabel.setFont(new Font("굴림", Font.BOLD, 15));
		campingCarIdLabel.setBounds(614, 318, 88, 21);
		panel.add(campingCarIdLabel);
		
		torepairId = new JTextField();
		torepairId.setBounds(727, 315, 150, 21);
		panel.add(torepairId);
		torepairId.setColumns(10);
		
		garageIdLabel.setFont(new Font("굴림", Font.BOLD, 15));
		garageIdLabel.setBounds(614, 342, 73, 21);
		panel.add(garageIdLabel);
		
		garageId = new JTextField();
		garageId.setColumns(10);
		garageId.setBounds(727, 342, 150, 21);
		panel.add(garageId);
		
		garageResultLabel.setFont(new Font("굴림", Font.BOLD, 15));
		garageResultLabel.setBounds(614, 364, 73, 21);
		panel.add(garageResultLabel);
		
		repairListLog = new JTextField();
		repairListLog.setColumns(10);
		repairListLog.setBounds(727, 366, 150, 21);
		panel.add(repairListLog);
		
		fixDateLabel.setFont(new Font("굴림", Font.BOLD, 15));
		fixDateLabel.setBounds(614, 389, 73, 21);
		panel.add(fixDateLabel);
		
		repairListFixDate = new JTextField();
		repairListFixDate.setColumns(10);
		repairListFixDate.setBounds(727, 389, 150, 21);
		panel.add(repairListFixDate);
		
		fixPriceLabel.setFont(new Font("굴림", Font.BOLD, 15));
		fixPriceLabel.setBounds(614, 413, 73, 21);
		panel.add(fixPriceLabel);
		
		repairListprice = new JTextField();
		repairListprice.setColumns(10);
		repairListprice.setBounds(727, 413, 150, 21);
		panel.add(repairListprice);
		
		fixDueDateLabel.setFont(new Font("굴림", Font.BOLD, 15));
		fixDueDateLabel.setBounds(614, 437, 73, 21);
		panel.add(fixDueDateLabel);
		
		repairListDuedate = new JTextField();
		repairListDuedate.setColumns(10);
		repairListDuedate.setBounds(727, 437, 150, 21);
		panel.add(repairListDuedate);
		
		othersLabel.setFont(new Font("굴림", Font.BOLD, 15));
		othersLabel.setBounds(614, 461, 101, 21);
		panel.add(othersLabel);
		
		repairListOtherInfo = new JTextField();
		repairListOtherInfo.setColumns(10);
		repairListOtherInfo.setBounds(727, 461, 150, 21);
		panel.add(repairListOtherInfo);

		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
	}
	
	public void refreshInput() {
		torepairId.setText("");
		garageId.setText("");
		repairListLog.setText("");
		repairListFixDate.setText("");
		repairListprice.setText("");
		repairListDuedate.setText("");
		repairListOtherInfo.setText("");
	}
	
	public AdminInfo getInput() {
   	 	admin.garageId  = garageId.getText();
   	 	admin.torepair = torepairId.getText();
   	 	admin.repairListDuedate = repairListDuedate.getText();
   	 	admin.repairListFixDate = repairListFixDate.getText();
   	 	admin.repairListLog = repairListLog.getText();
   	 	admin.repairListOtherInfo = repairListOtherInfo.getText();
   	 	admin.repairListprice = repairListprice.getText();
		return admin;
	}
}
