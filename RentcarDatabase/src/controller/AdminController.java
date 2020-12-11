package controller;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import model.dataClass.AdminDataClass;
import model.dataClass.GarageDataClass;
import model.dataClass.ResultStateDataClass;
import view.AdminView;
import model.AdminModel;

public class AdminController {
	private final AdminModel adminModel = new AdminModel();
	private final AdminView adminView = AdminView.getInstance();
	
	public static AdminController getInstance() {
		return AdminControllerHolder.instance;
	}

	private static class AdminControllerHolder {
		private static final AdminController instance = new AdminController();
	}
	public AdminController(){
		 insertGarageData();	
		 printCampingCarRentList();
		 returnToCampingCarData();
		 printGarageList();
		 printSearch();
		 setVisible(true);
	}
	public void setVisible(boolean value) {
		adminView.setVisible(value);
	}
	public void printCampingCarRentList() {
		String str = "앞쪽 \t 오른쪽 \t 왼쪽 \t 뒤쪽 \t 수리여부 \t 캠핑카ID \t 고유대여ID\n";
		ArrayList<AdminDataClass> adminList = adminModel.getCampingCarReturnList();
		try {
			for(AdminDataClass admin : adminList) {
				str +=
				admin.front + '\t' +
				admin.right + '\t' +
				admin.left + '\t' +
				admin.back + '\t' +
				admin.repairListFixDate + '\t' +
				admin.campingcarId + '\t' +
				admin.rentId + '\n';
			}
		}catch(Exception e1) {
			e1.printStackTrace();
		}
		adminView.campingCarText.setText(str);
	}

	public void printGarageList() {
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
		adminView.garageText.setText(str);
	}
	

	public void insertGarageData() {
		adminView.insertToGarageButton.addActionListener(e -> {
			AdminDataClass adminData = adminView.getAdminInput();
			ResultStateDataClass result = adminModel.insertToGarage(adminData);
			adminView.showInsertToGarageResult(result);
		});
	}
	
	public void returnToCampingCarData() {
		adminView.returnButton.addActionListener(e -> {
			AdminDataClass adminData = adminView.getAdminInput();
			ResultStateDataClass result = adminModel.returnToCampingcarList(adminData.torepair);
			adminView.showReturnToCampingCaraListResult(result);
			adminView.refreshInput();
			printCampingCarRentList();
		});
		
	}
	
	public void printSearch() {
	
		adminView.searchButton[0].addActionListener(e -> {
			adminView.searchText.setText("검색1\n");
			try {
				ArrayList<String> search = new ArrayList<>();
				search = adminModel.getSearch(1);
				for (String i : search) {
					adminView.searchText.append(i);
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		adminView.searchButton[1].addActionListener(e -> {
			adminView.searchText.setText("검색2\n");

			try {
				ArrayList<String> search = new ArrayList<>();
				search = adminModel.getSearch(2);
				for (String i : search) {
					adminView.searchText.append(i);
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		adminView.searchButton[2].addActionListener(e -> {		
			adminView.searchText.setText("검색3\n");
			try {
				ArrayList<String> search = new ArrayList<>();
				search = adminModel.getSearch(3);
				for (String i : search) {
					adminView.searchText.append(i);
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		adminView.searchButton[3].addActionListener(e -> {
			adminView.searchText.setText("검색4\n");
			try {
				ArrayList<String> search = new ArrayList<>();
				search = adminModel.getSearch(4);
				for (String i : search) {
					adminView.searchText.append(i);
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
	}
}
