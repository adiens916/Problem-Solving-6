package controller;

import model.MainModel;

public class MainController {
	
	MainModel MainModel = new MainModel();
	
	public void DataReset() {
		MainModel.resetDatabase();
		MainModel.dropTables();
		MainModel.createTables();
		MainModel.inputSampleData();
	}
	
	
}
