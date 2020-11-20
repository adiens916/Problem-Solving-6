package controller;

import javax.swing.JButton;
import controller.dataClass.ResultState;
import controller.dataClass.ReturnInfo;
import model.ReturnModel;
import view.ReturnView;

public class ReturnController {
	
	private final ReturnModel returnModel = new ReturnModel();
	private final ReturnView returnView = new ReturnView();
	public final JButton returnButton = returnView.returnbtn;

	public ReturnController() {
		listenToReturnCar();
	}

	public void setVisible(boolean value) {
		returnView.setVisible(value);
	}

	private void listenToReturnCar() {
		returnButton.addActionListener(e -> {
			ReturnInfo state = returnView.getCarStateInput();
			ResultState result = returnModel.returnCar(state);
			returnView.showReturnResult(result);
		});
	}
}
