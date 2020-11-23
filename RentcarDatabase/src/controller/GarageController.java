package controller;

import controller.dataClass.Garage;
import model.GarageModel;
import view.GarageView;

public class GarageController
		extends ManagementController<GarageModel, GarageView, Garage> {

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
