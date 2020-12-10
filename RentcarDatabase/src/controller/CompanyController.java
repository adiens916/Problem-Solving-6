package controller;

import model.dataClass.CompanyDataClass;
import model.CompanyModel;
import view.CompanyView;

public class CompanyController
		extends AbstractController<CompanyModel, CompanyView, CompanyDataClass> {

	@Override
	void setModelAndView() {
		model = new CompanyModel();
		view = new CompanyView();
		quit = view.quit;
	}
}
