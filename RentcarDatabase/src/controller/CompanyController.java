package controller;

import controller.dataClass.CompanyInfo;
import model.CompanyModel;
import view.CompanyView;

public class CompanyController
		extends AbstractController<CompanyModel, CompanyView, CompanyInfo> {

	@Override
	void setModelAndView() {
		model = new CompanyModel();
		view = new CompanyView();
		quit = view.quit;
	}
}
