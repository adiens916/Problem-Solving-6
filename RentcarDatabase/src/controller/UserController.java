}package controller;

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
	// 멤버 변수 유저 모델, 유저 뷰
	private final UserModel userModel = new UserModel();
	private final UserView userView = UserView.getInstance();
	
	// 싱글턴 기법으로 유저 컨트롤러는 하나의 인스턴스만 사용
	public static UserController getInstance() {
		return UserControllerHolder.instance;
	}

	private static class UserControllerHolder {
		private static final UserController instance = new UserController();
	}
	
	// 생성자
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
	
	// UserView 보이게 하는 메소드
	public void setVisible(boolean value) {
		userView.setVisible(value);
	}
	
	// 현재 대여 가능한 전체 캠핑카 리스트  
	public void readRentableCampingCarList() {
		ArrayList<CampingCarDataClass> rentableCampingCarList = userModel.readRentableCampingCarList();
		userView.readRentableCampingCarList(rentableCampingCarList);
	}
	
	// 대여 현황 리스트
	public void readRentList() {
		ArrayList<RentDataClass> rentList = userModel.readRentList();
		userView.readRentList(rentList);		
	}
	
	// 대여 가능한 캠핑카 리스트 조건 검색
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
	
	// 새로고침
	public void listenToRefreshRentableCampingCarList() {
		userView.refreshButton.addActionListener(e -> {
			readRentableCampingCarList();
		});
	}
	
	// 캠핑카 대여
	public void listenToRentCampingCar() {
		userView.rentButton.addActionListener(e -> {
			RentDataClass rent = userView.getRentInput();
			ResultStateDataClass result = userModel.rentCampingCarDriver(rent);
			userView.showRentResult(result);
			readRentableCampingCarList();
			readRentList();
		});
	}
	
	// 캠핑카 반환 클릭 시 ReturnController로 전환
	public void listenToReturnCampingCar() {
		userView.returnButton.addActionListener(e -> {
			setVisible(false);
			ReturnController.getInstance().setVisible(true);
		});
	}
	
	// 뒤로가기 버튼 클릭 시 MainController로 전환
	public void listenToBack() {
		userView.backButton.addActionListener(e -> {
			setVisible(false);
			MainController.getInstance().setVisible(true);
		});
	}
}
