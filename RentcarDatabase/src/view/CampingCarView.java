package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import controller.dataClass.CampingCarInfo;

public class CampingCarView extends JFrame {

	private JTextArea selectcp = new JTextArea();
	private JPanel contentPane;
	private JTextField cpcid,cpcname,cpcnum,cpcsits,cpcmanufacture,cpcyear,cpcdistance,cpcprice,cpid,registdate;
	private JLabel Label_Title,Label1,Label2,Label3,Label4,Label5,Label6,Label7,Label8,Label9,Label10,Label11;
	public JButton btn_Search,btn_Edit,btn_Delete,btn_Insert,btn_Refresh,quit;

	public String getColumnList() {
		return "캠핑카ID \t 차명 \t 차량번호 \t 승차인원수 \t 제조회사 \t 제조연도 \t 누적주행거리 \t 대여비용 \t캠핑카등록일자 \t 대여회사ID \n";
	}

	public void readCampingCarList(ArrayList<CampingCarInfo> campingCarList) {
		String str = getColumnList();
		for (CampingCarInfo campingCar : campingCarList) {
			str += toStringFromCampingCarInfo(campingCar);
		}
		selectcp.setText(str);
	}

	public void readCampingCar(CampingCarInfo campingCar) {
		String str = getColumnList();
		str += toStringFromCampingCarInfo(campingCar);
		selectcp.setText(str);
	}

	public CampingCarView() {
		setTitle("캠핑카프로젝트 리팩토링");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1174, 496);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
//데이터 출력 영역----------------------------------------------------------------------------
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(311, 98, 840, 343);
		panel.add(scrollPane);
		scrollPane.setViewportView(selectcp);
		// printdata(); //컬럼명 미리 출력해놓기
		
//상단 타이틀 Label--------------------------------------------------------------------------
		Label2 = new JLabel("캠핑카정보 입력 | 수정 | 삭제");
		Label2.setFont(new Font("양재튼튼체B", Font.BOLD, 28));
		Label2.setBounds(457, 0, 443, 39);
		panel.add(Label2);
		Label_Title = new JLabel("캠핑카ID 검색후 수정,삭제하시면 더 편리합니다.");
		Label_Title.setBounds(526, 49, 273, 15);
		panel.add(Label_Title);
		
//새로고침 기능------------------------------------------------------------------------------
		btn_Refresh = new JButton("새로고침");
		btn_Refresh.setBounds(16, 18, 96, 20);
		panel.add(btn_Refresh);

//데이터 입력-------------------------------------------------------------------------------		
		btn_Search = new JButton("검색");
		btn_Search.setFont(new Font("굴림", Font.PLAIN, 10));
		btn_Search.setBounds(210, 48, 57, 23);
		panel.add(btn_Search);

		Label1 = new JLabel("캠핑카ID입력");
		Label1.setFont(new Font("굴림", Font.BOLD, 16));
		Label1.setBounds(16, 49, 110, 20);
		panel.add(Label1);

		Label3 = new JLabel("차명");
		Label3.setFont(new Font("굴림", Font.BOLD, 16));
		Label3.setBounds(16, 80, 110, 20);
		panel.add(Label3);

		Label4 = new JLabel("차량번호");
		Label4.setFont(new Font("굴림", Font.BOLD, 16));
		Label4.setBounds(16, 115, 102, 20);
		panel.add(Label4);

		Label5 = new JLabel("승차인원수");
		Label5.setFont(new Font("굴림", Font.BOLD, 16));
		Label5.setBounds(16, 146, 102, 20);
		panel.add(Label5);

		Label6 = new JLabel("제조회사");
		Label6.setFont(new Font("굴림", Font.BOLD, 16));
		Label6.setBounds(16, 184, 102, 20);
		panel.add(Label6);

		Label7 = new JLabel("제조년도");
		Label7.setFont(new Font("굴림", Font.BOLD, 16));
		Label7.setBounds(16, 218, 102, 20);
		panel.add(Label7);

		Label8 = new JLabel("누적주행거리");
		Label8.setFont(new Font("굴림", Font.BOLD, 16));
		Label8.setBounds(16, 251, 102, 20);
		panel.add(Label8);

		Label9 = new JLabel("대여비용");
		Label9.setFont(new Font("굴림", Font.BOLD, 16));
		Label9.setBounds(16, 282, 102, 20);
		panel.add(Label9);

		Label10 = new JLabel("대여회사ID");
		Label10.setFont(new Font("굴림", Font.BOLD, 16));
		Label10.setBounds(16, 313, 102, 20);
		panel.add(Label10);

		Label11 = new JLabel("차등록일자");
		Label11.setFont(new Font("굴림", Font.BOLD, 16));
		Label11.setBounds(16, 345, 102, 20);
		panel.add(Label11);
		
		cpcid = new JTextField();
		cpcid.setColumns(10);
		cpcid.setBounds(126, 49, 72, 20);
		panel.add(cpcid);
		
		cpcname = new JTextField();
		cpcname.setColumns(10);
		cpcname.setBounds(126, 81, 141, 21);
		panel.add(cpcname);

		cpcnum = new JTextField();
		cpcnum.setColumns(10);
		cpcnum.setBounds(126, 112, 141, 21);
		panel.add(cpcnum);
		
		cpcsits = new JTextField();
		cpcsits.setColumns(10);
		cpcsits.setBounds(126, 143, 141, 21);
		panel.add(cpcsits);
		cpcmanufacture = new JTextField();
		cpcmanufacture.setColumns(10);
		cpcmanufacture.setBounds(126, 181, 141, 21);
		panel.add(cpcmanufacture);

		cpcdistance = new JTextField();
		cpcdistance.setColumns(10);
		cpcdistance.setBounds(126, 248, 141, 21);
		panel.add(cpcdistance);

		cpcprice = new JTextField();
		cpcprice.setColumns(10);
		cpcprice.setBounds(126, 279, 141, 21);
		panel.add(cpcprice);

		cpid = new JTextField();
		cpid.setColumns(10);
		cpid.setBounds(126, 310, 141, 21);
		panel.add(cpid);

		registdate = new JTextField();
		registdate.setColumns(10);
		registdate.setBounds(126, 345, 141, 21);
		panel.add(registdate);
		
//하단에 입력, 수정, 삭제 버튼 기능-------------------------------------------------------------
		btn_Insert = new JButton("입력");
		btn_Insert.setForeground(Color.BLACK);
		btn_Insert.setFont(new Font("굴림", Font.BOLD, 15));
		btn_Insert.setBounds(34, 380, 70, 29);
		panel.add(btn_Insert);
		
		cpcyear = new JTextField();
		cpcyear.setColumns(10);
		cpcyear.setBounds(126, 217, 141, 21);
		panel.add(cpcyear);
		btn_Edit = new JButton("수정");
		btn_Edit.setForeground(Color.BLACK);
		btn_Edit.setFont(new Font("굴림", Font.BOLD, 15));
		btn_Edit.setBounds(115, 380, 70, 29);
		panel.add(btn_Edit);

		btn_Delete = new JButton("삭제");
		btn_Delete.setForeground(Color.BLACK);
		btn_Delete.setFont(new Font("굴림", Font.BOLD, 15));
		btn_Delete.setBounds(197, 380, 70, 29);
		panel.add(btn_Delete);

		quit = new JButton("닫기");
		quit.setForeground(Color.BLACK);
		quit.setFont(new Font("굴림", Font.BOLD, 15));
		quit.setBounds(115, 419, 70, 22);
		panel.add(quit);
	}

	public void showCreateResult(String result) {
		if (result.equals("SUCCESS")) {
			JOptionPane.showMessageDialog(null, "입력완료!");
		} else if (result.equals("NULL")) {
			JOptionPane.showMessageDialog(null, "모든 텍스트 필드를 채워주세요!");
		} else {
			JOptionPane.showMessageDialog(null, "다시입력해주세요!");
		}
	}

	public void showUpdateResult(String result) {
		if (result.equals("SUCCESS")) {
			JOptionPane.showMessageDialog(null, "수정완료");
		} else if (result.equals("NULL")) {
			JOptionPane.showMessageDialog(null, "빈칸을 모두채워주세요");
		} else {
			JOptionPane.showMessageDialog(null, "다시입력하세요!");
		}
	}

	public void showDeleteResult(String result) {
		if (result.equals("SUCCESS")) {
			JOptionPane.showMessageDialog(null, "삭제 완료");
		} else {
			JOptionPane.showMessageDialog(null, "다시입력하세요!");
		}
	}

	public void refreshInput() {
		cpcname.setText("");
		cpcnum.setText("");
		cpcsits.setText("");
		cpcmanufacture.setText("");
		cpcyear.setText("");
		cpcdistance.setText("");
		cpcprice.setText("");
		registdate.setText("");
		cpid.setText("");
		cpcid.setText("");
	}

	public String getCampingCarId() {
		return cpcid.getText();
	}
	
	public CampingCarInfo getCampingCarInput() {
		CampingCarInfo campingCar = new CampingCarInfo();
		campingCar.id = cpcid.getText();
		campingCar.name = cpcname.getText();
		campingCar.number =cpcnum.getText();
		campingCar.seats =cpcsits.getText();
		campingCar.manufacturer = cpcmanufacture.getText();
		campingCar.builtDate =cpcyear.getText();
		campingCar.mileage =cpcdistance.getText();
		campingCar.rentalFee =cpcprice.getText();
		campingCar.registryDate=registdate.getText();
		campingCar.companyId =cpid.getText();
		return campingCar;
	}

	private String toStringFromCampingCarInfo(CampingCarInfo campingCar) {
		return campingCar.id + '\t' +
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

}
