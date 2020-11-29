package controller;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import model.dataClass.AdminDataClass;
import model.dataClass.CampingCarDataClass;
import model.dataClass.GarageDataClass;
import model.AdminModel;

public class AdminController {
	
	AdminModel adminModel = new AdminModel();
	
	public String printCampingcarList() {
		String str = "캠핑카ID \t 차명 \t 차량번호 \t 승차인원수 \t 제조회사 \t 제조연도 \t 누적주행거리 \t 대여비용 \t캠핑카등록일자 \t 대여회사ID \n";
		ArrayList<CampingCarDataClass> campingCarList = adminModel.getCampingCarList();
		for (CampingCarDataClass campingCar : campingCarList) {
			str +=
			campingCar.campingCarId + '\t' +
            campingCar.campingCarName + '\t' +
            campingCar.campingCarNumber + '\t' +
            campingCar.campingCarSits + '\t' +
            campingCar.campingCarManufacutre + '\t' +
            campingCar.campingCarManufactureYear + '\t' +
            campingCar.campingCarMileage + '\t' +
            campingCar.campingCarRentprice + '\t' +
            campingCar.campingCarRegitstdate + '\t' +
            campingCar.campingCarRentCompanyId + '\n';
		}
		return str;
	}

	public String printGarageList() {
		String str = "차고지ID \t 카센터이름 \t 주소 \t 번호 \t 매니저이름 \t 이메일주소\n";
		ArrayList<GarageDataClass> campingCarList = adminModel.getGarageList();
		for (GarageDataClass garage : campingCarList) {
			str += garage.id + '\t' +
					garage.name + '\t' +
					garage.address + '\t' +
					garage.number+ '\t' +
					garage.manager + '\t' +
					garage.emailAddress+ '\n';
		}
		return str;
	}
	
	
	
	public void insetGarageData(ArrayList<AdminDataClass> adminData) {
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
