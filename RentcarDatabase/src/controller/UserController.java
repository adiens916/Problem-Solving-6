package controller;

import model.UserModel;
import view.UserView;
import model.dataClass.CampingCarDataClass;
import model.dataClass.RentDataClass;
import controller.ReturnController;
import controller.AdminController;
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
		listenToReadRentableCampingCarListBy();
		listenToRefreshRentableCampingCarList();
		listenToRentCampingCar();
		listenToReturnCampingCar();
		listenToBack();
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
	
	public void listenToReadRentableCampingCarListBy() {
		userView.searchButton.addActionListener(e -> {
			ArrayList<CampingCarDataClass> rentableCampingCarList;
			String checkedRadio = userView.getCheckedRadio();
			String searchTerm = userView.getSearchTerm();
			
			if(!userView.isCheckedRadioNull(checkedRadio)) {
				rentableCampingCarList = userModel.readRentableCampingCarListBy(checkedRadio, searchTerm);
				userView.readRentableCampingCarList(rentableCampingCarList);
			} else {
				userView.showSearchFailed(ResultStateDataClass.FAILURE);
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
	
	public void listenToBack() {
		userView.backButton.addActionListener(e -> {
			setVisible(false);
			MainController.getInstance().setVisible(true);
		});
	}
}
