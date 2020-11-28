package controller;

import java.awt.EventQueue;

import controller.dataClass.CampingCarInfo;
import model.CampingCarModel;
import view.AdminView;
import view.CampingCarView;

public class CampingCarController extends AbstractController<CampingCarModel, CampingCarView, CampingCarInfo> {

	@Override
	void setModelAndView() {
		model = new CampingCarModel();
		view = new CampingCarView();
		quit = view.quit;
	}
}
