package controller;

import model.DatabaseInitializer;
import model.dataClass.AdminDataClass;
import model.dataClass.GarageDataClass;
import model.dataClass.ResultStateDataClass;
import model.AdminModel;
import view.AdminView;
import view.MenuForSubPage;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.util.ArrayList;


public class AdminController {
	private final AdminModel adminModel = new AdminModel();
	private final AdminView adminView = AdminView.getInstance();

	CompanyController companyController = new CompanyController();
	CampingCarController campingCarController = new CampingCarController();
	CustomerController customerController = new CustomerController();
	GarageController garageController = new GarageController();

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
		 addListenersToGoToSubPage();
		 addListenerToResetButton();
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
		for (int i = 0; i < 4; i++) {
			int index = i + 1;
			adminView.searchButton[i].addActionListener(e -> {
				try {
					ArrayList<String> search = adminModel.getSearch(index);
					adminView.printSearchResult(index, search);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			});
		}
	}

	private void addListenersToGoToSubPage() {
		addListenerToEachSubPage(adminView.companyMenu, companyController);
		addListenerToEachSubPage(adminView.campingCarMenu, campingCarController);
		addListenerToEachSubPage(adminView.customerMenu, customerController);
		addListenerToEachSubPage(adminView.garageMenu, garageController);
	}

	private void addListenerToEachSubPage(MenuForSubPage menu, AbstractController controller) {
		for (JMenuItem menuItem : menu.getMenuItems()) {
			menuItem.addActionListener(actionEvent -> {
				AdminController.getInstance().setVisible(false);
				controller.setVisible(true);
			});
		}
	}

	private void addListenerToResetButton() {
		adminView.resetButton.addActionListener(actionEvent -> {
			try {
				DatabaseInitializer.getInstance().init();
				// 초기화 후 반환 내역과 정비고 목록 갱신 & 입력란 새로고침
				printCampingCarRentList();
				printGarageList();
				adminView.refreshInput();
				JOptionPane.showMessageDialog(null, "초기화 완료");
			} catch (Exception e) {
				System.out.println("데이터 입력에 오류 발생!\n" + e);
				JOptionPane.showMessageDialog(null, "초기화 실패");
			}
		});
	}
}