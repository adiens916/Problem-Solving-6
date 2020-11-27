package controller;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import controller.dataClass.Customer;
import model.CustomerModel;
import view.CustomerView;

public class CustomerController
		extends AbstractController<CustomerModel, CustomerView, Customer>{

	@Override
	void setModelAndView() {
		model = new CustomerModel();
		view = new CustomerView();
		quit = view.quit;
	}
}
