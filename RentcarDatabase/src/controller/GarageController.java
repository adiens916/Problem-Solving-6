package controller;

import java.util.ArrayList;
import javax.swing.JButton;
import controller.dataClass.GarageInfo;
import controller.dataClass.ResultState;
import model.GarageModel;
import view.GarageView;

public class GarageController {

	private final GarageModel garageModel = new GarageModel();
	private final GarageView garageView = new GarageView();
	public final JButton quit = garageView.quit;
	
	public GarageController() {
		readGarageList();
		listenToRefreshInput();
		listenToCreateGarage();
		listenToReadGarage();
		listenToUpdateGarage();
		listenToDeleteGarage();
	}

	public void setVisible(boolean value) {
		garageView.setVisible(value);
	}

	private void readGarageList() {
		ArrayList<GarageInfo> garageList = garageModel.readGarageList();
		garageView.readGarageList(garageList);
	}

	private void listenToRefreshInput() {
		garageView.btn_Refresh.addActionListener(e -> {
			readGarageList();
			garageView.refreshInput();
		});
	}

	private void listenToCreateGarage() {
		garageView.btn_Insert.addActionListener(e -> {
			GarageInfo garage = garageView.getGarageInput();
			ResultState result = garageModel.createGarage(garage);
			garageView.showCreateResult(result);
			readGarageList();
		});
	}

	private void listenToReadGarage() {
		garageView.btn_Search.addActionListener(e -> {
			String garageId = garageView.getGarageId();
			GarageInfo garage = garageModel.readGarage(garageId);
			garageView.readGarage(garage);
		});
	}

	private void listenToUpdateGarage() {
		garageView.btn_Edit.addActionListener(e -> {
			GarageInfo garage = garageView.getGarageInput();
			ResultState result = garageModel.updateGarage(garage);
			garageView.showUpdateResult(result);
			readGarageList();
		});
	}

	private void listenToDeleteGarage() {
		garageView.btn_Delete.addActionListener(e -> {
			String garageId = garageView.getGarageId();
			ResultState result = garageModel.deleteGarage(garageId);
			garageView.showDeleteResult(result);
			readGarageList();
		});
	}
}
