package controller;

import controller.dataClass.Garage;
import model.GarageModel;
import view.GarageView;

public class GarageController
		extends AbstractController<GarageModel, GarageView, Garage> {

	@Override
	void setModelAndView() {
		model = new GarageModel();
		view = new GarageView();
		quit = view.quit;
	}
}
