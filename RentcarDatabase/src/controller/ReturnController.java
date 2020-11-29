package controller;

import model.dataClass.ResultStateDataClass;
import model.dataClass.ReturnDataClass;
import model.ReturnModel;
import view.ReturnView;

public class ReturnController {

	private final ReturnModel returnModel = new ReturnModel();
	private final ReturnView returnView = new ReturnView();

	public static ReturnController getInstance() {
		return ReturnControllerHolder.instance;
	}

	private static class ReturnControllerHolder {
		private static final ReturnController instance = new ReturnController();
	}

	private ReturnController() {
		addListenerToReturnCar();
	}

	public void setVisible(boolean value) {
		returnView.refreshInput();
		returnView.setVisible(value);
	}

	private void addListenerToReturnCar() {
		returnView.returnButton.addActionListener(e -> {
			ReturnDataClass state = returnView.getCarStateInput();
			ResultStateDataClass result = returnModel.returnCar(state);
			returnView.showReturnResult(result);

			if (result == ResultStateDataClass.SUCCESS) {
				setVisible(false);
				UserController.getInstance().readRentList();
				UserController.getInstance().readRentableCampingCarList();
				UserController.getInstance().setVisible(true);
			}
		});
	}
}
