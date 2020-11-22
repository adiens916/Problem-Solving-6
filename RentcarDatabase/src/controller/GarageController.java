package controller;

import controller.dataClass.GarageInfo;
import model.GarageModel;
import view.GarageView;

public class GarageController
		extends ManagementController<GarageModel, GarageView, GarageInfo> {

	public GarageController() {
		model = new GarageModel();
		view = new GarageView();
		quit = view.quit;

		readList();
		listenToRefreshInput();
		listenToCreate();
		listenToRead();
		listenToUpdate();
		listenToDelete();
	}
}
