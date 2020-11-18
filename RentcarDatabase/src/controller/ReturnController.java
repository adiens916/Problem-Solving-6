package controller;

import controller.dataClass.ReturnInfo;
import model.ReturnModel;

public class ReturnController {
	
	ReturnModel returnModel = new ReturnModel();
	
	/*
	 * 반환버튼 기능
	 * */
	public void returnCar(ReturnInfo state) {
		
		returnModel.printProcessingResult(returnModel.returnCar(state));
		
	}
	
}
