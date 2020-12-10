package controller;

import model.dataClass.GarageDataClass;
import model.GarageModel;
import view.GarageView;

public class GarageController
		extends AbstractController<GarageModel, GarageView, GarageDataClass> {

	@Override
	void setModelAndView() {
		model = new GarageModel();
		view = new GarageView();
		quit = view.quit;
	}
}
