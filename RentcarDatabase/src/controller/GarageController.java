package controller;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import controller.dataClass.GarageInfo;
import model.DataModel_Garage;

public class GarageController {

	DataModel_Garage garageModel = new DataModel_Garage();

	//----
	public String printGarageList() {

		String str = "";
		ArrayList<GarageInfo> garageList = garageModel.getGarageList();
		
		for (GarageInfo garage : garageList) {
			str += garage.id + "\t" + garage.name + "\t" + garage.address + "\t" + garage.number + "\t" + garage.manager
					+ "\t" + garage.emailAddress + "\n";
		}
		return str;
	}
	
	//----
	public String searchGarageList(String id) {
		String str = "";
		
		str = garageModel.toStringFromGarageInfo(garageModel.searchGarageById(id));
		
		return str;
	
	}
	
	//----
	public void insertGarageList(GarageInfo garage) {
		
		int result = garageModel.addGarage(garage);
		
		if (result == 1) {
			JOptionPane.showMessageDialog(null, "입력완료!");
		} else if (result == 2) {
			JOptionPane.showMessageDialog(null, "빈칸을 모두채워주세요");
		} else {
			JOptionPane.showMessageDialog(null, "다시입력해주세요!");
		}

	}
	
	public void editGarageList(GarageInfo garage) {
		
		
		int result = garageModel.updateGarage(garage);
		
		
		if (result == 1) {
			JOptionPane.showMessageDialog(null, "수정완료");
		} else if (result == 2) {
			JOptionPane.showMessageDialog(null, "빈칸을 모두채워주세요");
		} else {
			JOptionPane.showMessageDialog(null, "다시입력하세요!");
		}
	}
	
	public void deleteGarageList(String id) {
		
		
		/* 모델에 id를 넘겨주고, 처리 결과를 가져옴 */
		int result = garageModel.deleteGarage(id);

		if (result == 1) {
			JOptionPane.showMessageDialog(null, "삭제 완료");
		}else {
			JOptionPane.showMessageDialog(null, "ID를 입력해주세요.");
		}
	}
	
	
	
}
