package CONTROLLER;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import controller.dataClass.AdminInfo;
import controller.dataClass.CampingCarInfo;
import controller.dataClass.Garage;
import model.AdminModel;

public class AdminController {
	AdminModel adminModel = new AdminModel();
	
	public String printCampingcarList() {
		String str = "캠핑카ID \t 차명 \t 차량번호 \t 승차인원수 \t 제조회사 \t 제조연도 \t 누적주행거리 \t 대여비용 \t캠핑카등록일자 \t 대여회사ID \n";
		ArrayList<CampingCarInfo> campingCarList = adminModel.getCampingCarList();
		for (CampingCarInfo campingCar : campingCarList) {
			str += campingCar.id + '\t' +
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
		return str;
	}

	public String printGarageList() {
		String str = "차고지ID \t 카센터이름 \t 주소 \t 번호 \t 매니저이름 \t 이메일주소\n";
		ArrayList<Garage> campingCarList = adminModel.getGarageList();
		for (Garage garage : campingCarList) {
			str += garage.id + '\t' +
					garage.name + '\t' +
					garage.address + '\t' +
					garage.number+ '\t' +
					garage.manager + '\t' +
					garage.emailAddress+ '\n';
		}
		return str;
	}
	
	
	
	public void insetGarageData(ArrayList<AdminInfo> adminData) {
		int result = adminModel.insetToGarage(adminData);
		if(result ==1 ) {JOptionPane.showMessageDialog(null, "처리 완료");}
		else if(result ==0 ) JOptionPane.showMessageDialog(null, "수리할필요없습니다. 반환하세요.");
		else if(result ==2 ) JOptionPane.showMessageDialog(null, "빈칸을 모두 채워주세요");
	}
	
	public void returnToCampingCarData(String cpcid) {
		int result = adminModel.returnToCampingcarList(cpcid);
		if(result==1) JOptionPane.showMessageDialog(null, "수리가필요한 캠핑카입니다."); 
		else if(result==2) JOptionPane.showMessageDialog(null, "반환 완료!"); 
		else if(result==3) JOptionPane.showMessageDialog(null, "캠핑카ID를 입력해주세요!");
		
	}
	
	public void printSearch(int num, JTextArea searchText) {
		try {
			 ArrayList<String> search = new ArrayList<>();
			 search = adminModel.getSearch(num);
			for(String i : search) {
				//adminView.searchText.append(i);
				searchText.append(i);
			}
		}catch(Exception e1) {
			e1.printStackTrace();
		}
	}
}
