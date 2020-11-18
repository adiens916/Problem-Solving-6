package controller;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import controller.dataClass.CampingCarInfo;
import controller.dataClass.CompanyInfo;
import model.DataModel_CampingCar;

public class CampingcarController {

	DataModel_CampingCar campingCarModel = new DataModel_CampingCar();

	// ----
	public String printCampingcarList() {

		String str = "캠핑카ID \t 차명 \t 차량번호 \t 승차인원수 \t 제조회사 \t 제조연도 \t 누적주행거리 \t 대여비용 \t캠핑카등록일자 \t 대여회사ID \n";

		str += campingCarModel.getCampingCarList();
		
		return str;
	}

	// ----
	public String searchCampingCarList(String id) {

		String str = "캠핑카ID \t 차명 \t 차량번호 \t 승차인원수 \t 제조회사 \t 제조연도 \t 누적주행거리 \t 대여비용 \t캠핑카등록일자 \t 대여회사ID \n";

		str += campingCarModel.searchCampingCar(id);

		return str;

	}

	// ----
	public void insertCampingCarList(CampingCarInfo campingCar) {

		int result = campingCarModel.addCampingCar(campingCar);

		if (result == 1) {
			JOptionPane.showMessageDialog(null, "입력완료!");
		} else if (result == 2) {
			JOptionPane.showMessageDialog(null, "모든 텍스트 필드를 채워주세요!");
		} else {
			JOptionPane.showMessageDialog(null, "다시입력해주세요!");
		}

	}

	public void editCampingCarList(CampingCarInfo campingCar) {

		int result = campingCarModel.updateCampingCar(campingCar);

		if (result == 1) {
			JOptionPane.showMessageDialog(null, "수정완료");
		} else if (result == 2) {
			JOptionPane.showMessageDialog(null, "빈칸을 모두채워주세요");
		} else {
			JOptionPane.showMessageDialog(null, "다시입력하세요!");
		}
	}

	public void deleteCampingCarList(String id) {


		int result = campingCarModel.deleteCampingCar(id);

		if (result == 1) {
			JOptionPane.showMessageDialog(null, "삭제 완료");
		} else {
			JOptionPane.showMessageDialog(null, "다시입력하세요!");
		}
	}

}
