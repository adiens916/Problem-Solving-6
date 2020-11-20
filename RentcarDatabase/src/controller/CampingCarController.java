package controller;

import java.util.ArrayList;
import controller.dataClass.CampingCarInfo;
import controller.dataClass.ResultState;
import model.CampingCarModel;
import view.CampingCarView;

import javax.swing.*;

public class CampingCarController {

	private final CampingCarModel campingCarModel = new CampingCarModel();
	private final CampingCarView campingCarView = new CampingCarView();
	public final JButton quit = campingCarView.quit;

	public CampingCarController() {
		readCampingCarList();
		listenToRefreshInput();
		listenToCreateCampingCar();
		listenToReadCampingCar();
		listenToUpdateCampingCar();
		listenToDeleteCampingCar();
	}

	public void setVisible(boolean value) {
		campingCarView.setVisible(value);
	}

	private void readCampingCarList() {
		ArrayList<CampingCarInfo> campingCarList = campingCarModel.readCampingCarList();
		campingCarView.readCampingCarList(campingCarList);
	}

	private void listenToRefreshInput() {
		campingCarView.btn_Refresh.addActionListener(e -> {
			readCampingCarList();
			campingCarView.refreshInput();
		});
	}

	private void listenToCreateCampingCar() {
		campingCarView.btn_Insert.addActionListener(e -> {
			CampingCarInfo campingCar = campingCarView.getCampingCarInput();
			ResultState result = campingCarModel.createCampingCar(campingCar);
			campingCarView.showCreateResult(result);
			readCampingCarList();
		});
	}

	private void listenToReadCampingCar() {
		campingCarView.btn_Search.addActionListener(e -> {
			String campingCarId = campingCarView.getCampingCarId();
			CampingCarInfo campingCar = campingCarModel.readCampingCar(campingCarId);
			campingCarView.readCampingCar(campingCar);
		});
	}

	private void listenToUpdateCampingCar() {
		campingCarView.btn_Edit.addActionListener(e -> {
			CampingCarInfo campingCar = campingCarView.getCampingCarInput();
			ResultState result = campingCarModel.updateCampingCar(campingCar);
			campingCarView.showUpdateResult(result);
			readCampingCarList();
		});
	}

	private void listenToDeleteCampingCar() {
		campingCarView.btn_Delete.addActionListener(e -> {
			String campingCarId = campingCarView.getCampingCarId();
			ResultState result = campingCarModel.deleteCampingCar(campingCarId);
			campingCarView.showDeleteResult(result);
			readCampingCarList();
		});
	}
}
