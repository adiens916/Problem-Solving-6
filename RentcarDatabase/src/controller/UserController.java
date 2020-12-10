package controller;

import model.UserModel;
import view.UserView;
import model.dataClass.CampingCarDataClass;
import model.dataClass.RentDataClass;
import controller.ReturnController;
import model.dataClass.ResultStateDataClass;
import java.util.ArrayList;
import javax.swing.ButtonGroup;


public class UserController {
	private final UserModel userModel = new UserModel();
	private final UserView userView = UserView.getInstance();

	public static UserController getInstance() {
		return UserControllerHolder.instance;
	}

	private static class UserControllerHolder {
		private static final UserController instance = new UserController();
	}

	public UserController(){		
		readRentableCampingCarList();
		readRentList();
		listenToReadRentableCampingCarList();
		listenToRefreshRentableCampingCarList();
		listenToRentCampingCar();
		listenToReturnCampingCar();
		setVisible(true);
	}
	
	public void setVisible(boolean value) {
		userView.setVisible(value);
	}
	
	public void readRentableCampingCarList() {
		ArrayList<CampingCarDataClass> rentableCampingCarList = userModel.readRentableCampingCarList();
		userView.readRentableCampingCarList(rentableCampingCarList);
	}
	
	public void readRentList() {
		ArrayList<RentDataClass> rentList = userModel.readRentList();
		userView.readRentList(rentList);		
	}
	
	public String getCheckedRadio(ButtonGroup radioGroup) {
		String checkedRadio = "";
		
		try {
			checkedRadio = radioGroup.getSelection().getActionCommand();
		} catch(Exception e) {
			System.out.println("검색 실패 : "+e);
		}
		
		return checkedRadio;
	}
	
	public void listenToReadRentableCampingCarList() {
		userView.searchButton.addActionListener(e -> {
			ButtonGroup radioGroup = userView.getRadioGroup();
			String checkedRadio = getCheckedRadio(radioGroup);
			String searchBy = userView.getSearchCampingCar();
			ArrayList<CampingCarDataClass> rentableCampingCarList;
			
			switch(checkedRadio) {
				case "캠핑카ID" :
					rentableCampingCarList = userModel.readRentableCampingCarListByID(searchBy);
					userView.readRentableCampingCarList(rentableCampingCarList);
					break;
				case "차명" :
					rentableCampingCarList = userModel.readRentableCampingCarListByName(searchBy);
					userView.readRentableCampingCarList(rentableCampingCarList);
					break;
				case "최소승차인원" :
					rentableCampingCarList = userModel.readRentableCampingCarListBySeats(searchBy);
					userView.readRentableCampingCarList(rentableCampingCarList);
					break;
				case "제조회사" :
					rentableCampingCarList = userModel.readRentableCampingCarListByManufacture(searchBy);
					userView.readRentableCampingCarList(rentableCampingCarList);
					break;
				case "최대주행거리" :
					rentableCampingCarList = userModel.readRentableCampingCarListByMileage(searchBy);
					userView.readRentableCampingCarList(rentableCampingCarList);
					break;
				case "최대대여비용" :
					rentableCampingCarList = userModel.readRentableCampingCarListByPrice(searchBy);
					userView.readRentableCampingCarList(rentableCampingCarList);
					break;
				default :	
					userView.showSearchFailed();
					break;
			}			
		});
	}
	
	public void listenToRefreshRentableCampingCarList() {
		userView.refreshButton.addActionListener(e -> {
			readRentableCampingCarList();
		});
	}
	
	public void listenToRentCampingCar() {
		userView.rentButton.addActionListener(e -> {
			RentDataClass rent = userView.getRentInput();
			ResultStateDataClass result = userModel.rentCampingCar(rent);
			userView.showRentResult(result);
			readRentableCampingCarList();
			readRentList();
		});
	}
	
	public void listenToReturnCampingCar() {
		userView.returnButton.addActionListener(e -> {
			setVisible(false);
			ReturnController.getInstance().setVisible(true);
		});
	}
	
	// ------- 테스트 영역 --------
	public static void main(String[] args) {
		UserController us = new UserController();
	}
}
