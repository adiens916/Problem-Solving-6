package controller;

import model.dataClass.CampingCarDataClass;
import model.CampingCarModel;
import view.CampingCarView;

public class CampingCarController
		extends AbstractController<CampingCarModel, CampingCarView, CampingCarDataClass> {

	@Override
	void setModelAndView() {
		model = new CampingCarModel();
		view = new CampingCarView();
		quit = view.quit;
	}
}
