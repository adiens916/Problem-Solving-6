package view;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.AdminController;
import controller.dataClass.AdminInfo;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import java.awt.Component;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Dimension;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.border.CompoundBorder;

public class AdminView extends JFrame implements ActionListener{
	
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
		
	private JPanel contentPane;
	public JTextField repairListLog,repairListFixDate,repairListprice,repairListDuedate,repairListOtherInfo,torepair, garageId;
	
	DataView_Company companyForm;
	CampingCarView campingcarForm;
	DataView_Customer customerForm;
	GarageView garageForm;
	
	public JTextArea campingCarText=new JTextArea();
	public JTextArea searchText = new JTextArea();
    public JTextArea garageText = new JTextArea();

    JButton backBtn = new JButton("《 뒤로가기");
    JButton resetBtn = new JButton("초기화");
	
	JMenuItem menuCompanyRegist,menuCompanyEdit,menuCompanyDelete;
	JMenuItem menuCampingcarRegist,menuCampingcarEdit,menuCampingcarDelete;
	JMenuItem menuCustomerRegist,menuCustomerEdit,menuCustomerDelete;
	JMenuItem menuGarageRegist,menuGarageEdit,menuGarageDelete;
	
	AdminController adminController;
	

	//------------------------------------------------------------
	public AdminView() {
		setTitle("캠핑카프로젝트 - 관리자페이지");
		
		//컨트롤러 파트---------------------------------
		adminController = new AdminController(this);
		campingCarText.setText(adminController.printCampingcarList());
		garageText.setText(adminController.printGarageList());
		//------------------------------------------
		
		companyForm = new DataView_Company();
		companyForm.quit.addActionListener(this);
		
		campingcarForm = new CampingCarView();
		campingcarForm.quit.addActionListener(this);
		
		customerForm = new DataView_Customer();
		customerForm.quit.addActionListener(this);
		
		garageForm = new GarageView();
		garageForm.quit.addActionListener(this);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1094, 565);
//영역 설정------------------------------------------------------------------------------------------		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel Label1 = new JLabel("수리여부 1 : 수리필요");
		Label1.setFont(new Font("굴림", Font.BOLD, 14));
		Label1.setBounds(113, 12, 142, 21);
		panel.add(Label1);
		
		JLabel Label2 = new JLabel("수리여부 0 : 수리필요없음");
		Label2.setFont(new Font("굴림", Font.BOLD, 14));
		Label2.setBounds(267, 13, 171, 18);
		panel.add(Label2);
		
		//초기화 버튼---------------------------------------------------------------------------------
		resetBtn.setForeground(Color.WHITE);
		resetBtn.setBounds(450, 13, 51, 19);
		panel.add(resetBtn);
		resetBtn.setBorder(new CompoundBorder());
		resetBtn.setBackground(new Color(205, 133, 63));
		
		//뒤로가기 버튼--------------------------------------------------------------------------------
		backBtn.setForeground(Color.WHITE);
		backBtn.setBounds(513, 13, 66, 19);
		panel.add(backBtn);
		backBtn.setBorder(new CompoundBorder());
		backBtn.setBackground(new Color(205, 133, 63));
		
		
		//반환 내역이 출력되는 필드-------------------------------------------------------------------------
		JLabel Label3 = new JLabel("반환 내역");
		Label3.setFont(new Font("굴림", Font.BOLD, 20));
		Label3.setBounds(12, 10, 110, 21);
		panel.add(Label3);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 41, 586, 200);
		panel.add(scrollPane);
		scrollPane.setViewportView(campingCarText);
		
		//캠핑카 정비소가 출력되는 필드----------------------------------------------------------------------
		JLabel Label4 = new JLabel("캠핑카정비소 선택");
		Label4.setFont(new Font("굴림", Font.BOLD, 20));
		Label4.setBounds(12, 251, 193, 21);
		panel.add(Label4);
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
		JMenu menuCompany = new JMenu("대여회사");
		menuCompany.setHorizontalTextPosition(SwingConstants.CENTER);
		menuCompany.setHorizontalAlignment(SwingConstants.CENTER);
		menuCompany.setIconTextGap(60);
		menuBar.add(menuCompany);
		//등록---------------------------------------------------------------------
		menuCompanyRegist = new JMenuItem("등록");
		menuCompanyRegist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				companyForm.setVisible(true);
			}
		});
		menuCompanyRegist.setMargin(new Insets(0, 30, 0, 0));
		menuCompanyRegist.setPreferredSize(new Dimension(150, 30));
		menuCompanyRegist.setHorizontalTextPosition(SwingConstants.CENTER);
		menuCompanyRegist.setHorizontalAlignment(SwingConstants.CENTER);
		menuCompany.add(menuCompanyRegist);
		//수정---------------------------------------------------------------------
		menuCompanyEdit = new JMenuItem("수정");
		menuCompanyEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				companyForm.setVisible(true);
			}
		});
		menuCompanyEdit.setPreferredSize(new Dimension(150, 30));
		menuCompanyEdit.setMargin(new Insets(0, 30, 0, 0));
		menuCompanyEdit.setHorizontalTextPosition(SwingConstants.CENTER);
		menuCompanyEdit.setHorizontalAlignment(SwingConstants.CENTER);
		menuCompany.add(menuCompanyEdit);
		//삭제---------------------------------------------------------------------
		menuCompanyDelete = new JMenuItem("삭제");
		menuCompanyDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				companyForm.setVisible(true);
			}
		});
		menuCompanyDelete.setPreferredSize(new Dimension(150, 30));
		menuCompanyDelete.setMargin(new Insets(0, 30, 0, 0));
		menuCompanyDelete.setHorizontalTextPosition(SwingConstants.CENTER);
		menuCompanyDelete.setHorizontalAlignment(SwingConstants.CENTER);
		menuCompany.add(menuCompanyDelete);
		
//------캠핑카 메뉴 설정--------------------------------------------------------------------------------
		JMenu menuCampingcar = new JMenu("캠핑카");
		menuCampingcar.setIconTextGap(55);
		menuCampingcar.setHorizontalTextPosition(SwingConstants.CENTER);
		menuCampingcar.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar.add(menuCampingcar);
		//등록---------------------------------------------------------------------
		menuCampingcarRegist = new JMenuItem("등록");
		menuCampingcarRegist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				campingcarForm.setVisible(true);
			}
		});
		menuCampingcarRegist.setPreferredSize(new Dimension(150, 30));
		menuCampingcarRegist.setMargin(new Insets(0, 30, 0, 0));
		menuCampingcarRegist.setHorizontalTextPosition(SwingConstants.CENTER);
		menuCampingcarRegist.setHorizontalAlignment(SwingConstants.CENTER);
		menuCampingcar.add(menuCampingcarRegist);
		//수정---------------------------------------------------------------------
		menuCampingcarEdit = new JMenuItem("수정");
		menuCampingcarEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				campingcarForm.setVisible(true);
			}
		});
		menuCampingcarEdit.setPreferredSize(new Dimension(150, 30));
		menuCampingcarEdit.setMargin(new Insets(0, 30, 0, 0));
		menuCampingcarEdit.setHorizontalTextPosition(SwingConstants.CENTER);
		menuCampingcarEdit.setHorizontalAlignment(SwingConstants.CENTER);
		menuCampingcar.add(menuCampingcarEdit);
		//삭제---------------------------------------------------------------------
		menuCampingcarDelete = new JMenuItem("삭제");
		menuCampingcarDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				campingcarForm.setVisible(true);
			}
		});
		menuCampingcarDelete.setPreferredSize(new Dimension(150, 30));
		menuCampingcarDelete.setMargin(new Insets(0, 30, 0, 0));
		menuCampingcarDelete.setHorizontalTextPosition(SwingConstants.CENTER);
		menuCampingcarDelete.setHorizontalAlignment(SwingConstants.CENTER);
		menuCampingcar.add(menuCampingcarDelete);
		
//------고객 메뉴 설정---------------------------------------------------------------------------------
		JMenu menuCustomer = new JMenu("고객");
		menuCustomer.setIconTextGap(55);
		menuCustomer.setHorizontalTextPosition(SwingConstants.CENTER);
		menuCustomer.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar.add(menuCustomer);
		//등록---------------------------------------------------------------------
		menuCustomerRegist = new JMenuItem("등록");
		menuCustomerRegist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				customerForm.setVisible(true);
			}
		});
		menuCustomerRegist.setPreferredSize(new Dimension(150, 30));
		menuCustomerRegist.setMargin(new Insets(0, 30, 0, 0));
		menuCustomerRegist.setHorizontalTextPosition(SwingConstants.CENTER);
		menuCustomerRegist.setHorizontalAlignment(SwingConstants.CENTER);
		menuCustomer.add(menuCustomerRegist);
		//수정---------------------------------------------------------------------
		menuCustomerEdit = new JMenuItem("수정");
		menuCustomerEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				customerForm.setVisible(true);
			}
		});
		menuCustomerEdit.setPreferredSize(new Dimension(150, 30));
		menuCustomerEdit.setMargin(new Insets(0, 30, 0, 0));
		menuCustomerEdit.setHorizontalTextPosition(SwingConstants.CENTER);
		menuCustomerEdit.setHorizontalAlignment(SwingConstants.CENTER);
		menuCustomer.add(menuCustomerEdit);
		//삭제---------------------------------------------------------------------
		menuCustomerDelete = new JMenuItem("삭제");
		menuCustomerDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				customerForm.setVisible(true);
			}
		});
		menuCustomerDelete.setPreferredSize(new Dimension(150, 30));
		menuCustomerDelete.setMargin(new Insets(0, 30, 0, 0));
		menuCustomerDelete.setHorizontalTextPosition(SwingConstants.CENTER);
		menuCustomerDelete.setHorizontalAlignment(SwingConstants.CENTER);
		menuCustomer.add(menuCustomerDelete);
		
//------정비소 메뉴 설정---------------------------------------------------------------------------------
		JMenu menuGarage = new JMenu("정비소");
		menuGarage.setIconTextGap(55);
		menuGarage.setHorizontalTextPosition(SwingConstants.CENTER);
		menuGarage.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar.add(menuGarage);
		//등록---------------------------------------------------------------------
		menuGarageRegist = new JMenuItem("등록");
		menuGarageRegist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				garageForm.setVisible(true);
			}
		});
		menuGarageRegist.setPreferredSize(new Dimension(150, 30));
		menuGarageRegist.setMargin(new Insets(0, 30, 0, 0));
		menuGarageRegist.setHorizontalTextPosition(SwingConstants.CENTER);
		menuGarageRegist.setHorizontalAlignment(SwingConstants.CENTER);
		menuGarage.add(menuGarageRegist);
		//수정---------------------------------------------------------------------
		menuGarageEdit = new JMenuItem("수정");
		menuGarageEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				garageForm.setVisible(true);
			}
		});
		menuGarageEdit.setPreferredSize(new Dimension(150, 30));
		menuGarageEdit.setMargin(new Insets(0, 30, 0, 0));
		menuGarageEdit.setHorizontalTextPosition(SwingConstants.CENTER);
		menuGarageEdit.setHorizontalAlignment(SwingConstants.CENTER);
		menuGarage.add(menuGarageEdit);
		//삭제---------------------------------------------------------------------
		menuGarageDelete = new JMenuItem("삭제");
		menuGarageDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				garageForm.setVisible(true);
			}
		});
		menuGarageDelete.setPreferredSize(new Dimension(150, 30));
		menuGarageDelete.setMargin(new Insets(0, 30, 0, 0));
		menuGarageDelete.setHorizontalTextPosition(SwingConstants.CENTER);
		menuGarageDelete.setHorizontalAlignment(SwingConstants.CENTER);
		menuGarage.add(menuGarageDelete);
		
//------우측상단 영역 설정------------------------------------------------------------------------------	
		JLabel Label5 = new JLabel("검색");
		Label5.setFont(new Font("굴림", Font.BOLD, 20));
		Label5.setBounds(614, 10, 110, 21);
		panel.add(Label5);
		
		JButton btn1 = new JButton("검색1");
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchText.setText("검색1 결과\n");
				adminController.printSearch(1);
			}
		});
		btn1.setBounds(955, 26, 105, 23);
		panel.add(btn1);
		
		JButton btn2 = new JButton("검색2");
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchText.setText("검색2 결과\n");
				adminController.printSearch(2);
			}
		});
		btn2.setBounds(955, 53, 105, 23);
		panel.add(btn2);
		
		JButton btn3 = new JButton("검색3");
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchText.setText("검색3\n");
				adminController.printSearch(3);
			}
		});
		btn3.setBounds(955, 79, 105, 23);
		panel.add(btn3);
		
		JButton bnt4 = new JButton("검색4");
		bnt4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchText.setText("검색4 결과\n");
				adminController.printSearch(4);
			}
		});
		bnt4.setBounds(955, 105, 105, 23);
		panel.add(bnt4);
		
		JLabel search1 = new JLabel("1.   수리목록 중에서 10만원이상 내역이 나온 고객이름");
		search1.setBounds(614, 30, 321, 15);
		panel.add(search1);
		
		JLabel search2 = new JLabel("2.   캠핑카의 청구요금이 50만원 이상 지불해야 할 고객이름");
		search2.setBounds(614, 57, 329, 15);
		panel.add(search2);
		
		JLabel search3 = new JLabel("3.   수리할 필요가 없는, 수리필요여부가 1인 캠핑카");
		search3.setBounds(614, 83, 329, 15);
		panel.add(search3);
		
		JLabel search4 = new JLabel("4.   대여기간이 10일이상인 고객이름");
		search4.setBounds(614, 109, 321, 15);
		panel.add(search4);
		
		//검색결과 출력되는 곳-------------------------------------------------------------------------------
		searchText.setBounds(614, 138, 446, 101);
		panel.add(searchText);
		
//우측하단 영역 설정-----------------------------------------------------------------------------------------		
		JLabel Label6 = new JLabel("※ 수리여부가 1인 캠핑카의 고유대여ID와 정비소ID를 입력 후  추가정보를 입력");
		Label6.setBounds(614, 279, 446, 26);
		panel.add(Label6);
		//------정비소로 보내기-----------------------------------------------------------------------------
		JButton grgBtn = new JButton("정비소로보내기");
		grgBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<AdminInfo> adminData = new ArrayList<>();
           	 	AdminInfo admin = new AdminInfo();
           	 	admin.garageId  = garageId.getText();
           	 	admin.torepair = torepair.getText();
           	 	admin.repairListDuedate = repairListDuedate.getText();
           	 	admin.repairListFixDate = repairListFixDate.getText();
           	 	admin.repairListLog = repairListLog.getText();
           	 	admin.repairListOtherInfo = repairListOtherInfo.getText();
           	 	admin.repairListprice = repairListprice.getText();
           	 	adminData.add(admin);
           	 	
				int result = adminController.InsetGarageData(adminData);
				if(result ==1 ) {
					JOptionPane.showMessageDialog(grgBtn, "처리 완료");
					adminController.textFieldReset();
				}else if(result ==0 ) {
					JOptionPane.showMessageDialog(grgBtn, "수리할필요없습니다. 반환하세요.");
				}else if(result ==2 ) {
					JOptionPane.showMessageDialog(grgBtn, "빈칸을 모두 채워주세요");
				}
				System.out.println(result);
			}
		});
		grgBtn.setFont(new Font("굴림", Font.BOLD, 15));
		grgBtn.setBounds(889, 315, 142, 77);
		panel.add(grgBtn);
		
		//------다시 캠핑카 업체로 반환하기---------------------------------------------------------------------
		JButton returnBtn = new JButton("반 환 하 기");
		returnBtn.setFont(new Font("굴림", Font.BOLD, 18));
		returnBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = adminController.ReturnToCampingCarData(torepair.getText());
				if(result==1) JOptionPane.showMessageDialog(returnBtn, "수리가필요한 캠핑카입니다."); 
				else if(result==2) JOptionPane.showMessageDialog(returnBtn, "반환 완료!"); 
				else if(result==3) JOptionPane.showMessageDialog(returnBtn, "캠핑카ID를 입력해주세요!");

			}
		});
		returnBtn.setBounds(889, 402, 142, 77);
		panel.add(returnBtn);
		
		//-------데이터 입력------------------------------------------------------------------------------
		JLabel Label7 = new JLabel("캠핑카ID");
		Label7.setFont(new Font("굴림", Font.BOLD, 15));
		Label7.setBounds(614, 318, 88, 21);
		panel.add(Label7);
		
		torepair = new JTextField();
		torepair.setBounds(727, 315, 150, 21);
		panel.add(torepair);
		torepair.setColumns(10);
		
		JLabel Label8 = new JLabel("정비소ID");
		Label8.setFont(new Font("굴림", Font.BOLD, 15));
		Label8.setBounds(614, 342, 73, 21);
		panel.add(Label8);
		
		garageId = new JTextField();
		garageId.setColumns(10);
		garageId.setBounds(727, 342, 150, 21);
		panel.add(garageId);
		
		JLabel Label9 = new JLabel("정비내역");
		Label9.setFont(new Font("굴림", Font.BOLD, 15));
		Label9.setBounds(614, 364, 73, 21);
		panel.add(Label9);
		
		repairListLog = new JTextField();
		repairListLog.setColumns(10);
		repairListLog.setBounds(727, 366, 150, 21);
		panel.add(repairListLog);
		
		JLabel Label10 = new JLabel("수리날짜");
		Label10.setFont(new Font("굴림", Font.BOLD, 15));
		Label10.setBounds(614, 389, 73, 21);
		panel.add(Label10);
		
		repairListFixDate = new JTextField();
		repairListFixDate.setColumns(10);
		repairListFixDate.setBounds(727, 389, 150, 21);
		panel.add(repairListFixDate);
		
		JLabel Label11 = new JLabel("수리비용");
		Label11.setFont(new Font("굴림", Font.BOLD, 15));
		Label11.setBounds(614, 413, 73, 21);
		panel.add(Label11);
		
		repairListprice = new JTextField();
		repairListprice.setColumns(10);
		repairListprice.setBounds(727, 413, 150, 21);
		panel.add(repairListprice);
		
		JLabel Label12 = new JLabel("납입기한");
		Label12.setFont(new Font("굴림", Font.BOLD, 15));
		Label12.setBounds(614, 437, 73, 21);
		panel.add(Label12);
		
		repairListDuedate = new JTextField();
		repairListDuedate.setColumns(10);
		repairListDuedate.setBounds(727, 437, 150, 21);
		panel.add(repairListDuedate);
		
		JLabel Label13 = new JLabel("기타내역정보");
		Label13.setFont(new Font("굴림", Font.BOLD, 15));
		Label13.setBounds(614, 461, 101, 21);
		panel.add(Label13);
		
		repairListOtherInfo = new JTextField();
		repairListOtherInfo.setColumns(10);
		repairListOtherInfo.setBounds(727, 461, 150, 21);
		panel.add(repairListOtherInfo);

		resetBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//
			}
		});
	}
	

	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==companyForm.quit) {
			companyForm.setVisible(false);
			setVisible(true);
		}
		if(e.getSource()==campingcarForm.quit) {
			campingcarForm.setVisible(false);
			setVisible(true);
		}
		if(e.getSource()==customerForm.quit) {
			customerForm.setVisible(false);
			setVisible(true);
		}
		if(e.getSource()==garageForm.quit) {
			garageForm.setVisible(false);
			setVisible(true);
		}
		
	}
}
