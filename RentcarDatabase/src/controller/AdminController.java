package controller;

import java.util.ArrayList;
import view.AdminView;
import controller.dataClass.AdminInfo;
import controller.dataClass.Garage;
import model.AdminModel;

public class AdminController {
	private AdminModel adminModel = new AdminModel();
	AdminView adminView;
	public AdminController(AdminView adminView) {
		this.adminView = adminView;
	}
	public void printCampingCarList() {
		try {
			ArrayList<AdminInfo> adminList = new ArrayList<>();
			adminList = adminModel.getCampingCarReturnList();
			adminView.campingCarText.setText("앞쪽 \t 오른쪽 \t 왼쪽 \t 뒤쪽 \t 수리여부 \t 캠핑카ID \t 고유대여ID\n");
			for(AdminInfo i : adminList) {
				adminView.campingCarText.append(i.front + "\t");
				adminView.campingCarText.append(i.right + "\t");
				adminView.campingCarText.append(i.left + "\t");
				adminView.campingCarText.append(i.back + "\t");
				adminView.campingCarText.append(i.repairListFixDate + "\t");
				adminView.campingCarText.append(i.campingcarId + "\t");
				adminView.campingCarText.append(i.rentId + "\t");
				adminView.campingCarText.append("\n");
			}
		}catch(Exception e1) {
			e1.printStackTrace();
		}
	}
	
	public void printGarageList() {
		try {
			ArrayList<Garage> adminList = new ArrayList<>();
			adminList = adminModel.getGarageList();
			adminView.garageText.setText("차고지ID \t 카센터이름 \t 주소 \t 번호 \t 매니저이름 \t 이메일주소\n");
			for(Garage i : adminList) {
				adminView.garageText.append(i.id + "\t");
				adminView.garageText.append(i.name + "\t");
				adminView.garageText.append(i.address + "\t");
				adminView.garageText.append(i.number + "\t");
				adminView.garageText.append(i.manager + "\t");
				adminView.garageText.append(i.emailAddress + "\t");
				adminView.garageText.append("\n");
			}
		}catch(Exception e1) {
			e1.printStackTrace();
		}
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
