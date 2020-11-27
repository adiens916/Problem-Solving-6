package controller;

import java.util.ArrayList;
import view.AdminView;
import controller.dataClass.AdminInfo;
import controller.dataClass.CampingCarInfo;
import controller.dataClass.GarageInfo;
import model.AdminModel;
import model.DataModel_CampingCar;

public class AdminController {
	AdminModel adminModel = new AdminModel();
	AdminView adminView;
	public AdminController(AdminView adminView) {
		this.adminView = adminView;
	}
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
		ArrayList<GarageInfo> campingCarList = adminModel.getGarageList();
		
		for (GarageInfo garage : campingCarList) {
			str += garage.id + '\t' +
					garage.name + '\t' +
					garage.address + '\t' +
					garage.number+ '\t' +
					garage.manager + '\t' +
					garage.emailAddress+ '\n';
		}
		
		return str;
	}
	
	
	
	public int InsetGarageData(ArrayList<AdminInfo> adminData) {
		return adminModel.InsetToGarage(adminData);
	}
	public int ReturnToCampingCarData(String cpcid) {
		return adminModel.ReturnToCampingcarList(cpcid);
	}
	public void printSearch(int num) {
		try {
			 ArrayList<String> search = new ArrayList<>();
			 search = adminModel.getSearch(num);
			for(String i : search) {
				adminView.searchText.append(i);
			}
		}catch(Exception e1) {
			e1.printStackTrace();
		}
	}
	public void textFieldReset() {
		adminModel.dataReset(adminView);
	}
}
