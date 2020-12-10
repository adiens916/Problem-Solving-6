package controller;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.dataClass.CustomerDataClass;
import model.CustomerModel;
import view.CustomerView;

public class CustomerController
		extends AbstractController<CustomerModel, CustomerView, CustomerDataClass>{

	@Override
	void setModelAndView() {
		model = new CustomerModel();
		view = new CustomerView();
		quit = view.quit;
	}
}
