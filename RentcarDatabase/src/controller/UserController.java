package controller;

import model.CustomerModel;

import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import controller.dataClass.*;

public class UserController {
	
	CustomerModel customerModel = new CustomerModel();
	
	
	public String printRentedCampingCarList() {
		
		ArrayList<String> list = customerModel.Rented_CampingCar_List();
		
		String str = "캠핑카ID \t 금액\n";
		
		for(int i=0;i<list.size();i++) {
			str += list.get(i);
		}
		
		return str;
		
	}
	
	public String printCampingCarList() {
		
		ArrayList<String> list = customerModel.CampingCar_List();
		
		String str = "캠핑카ID \t차명 \t차량번호 \t 승차인원수 \t 제조회사 \t 제조연도 \t 누적주행거리 \t 대여비용 \t캠핑카등록일자 \t 대여회사ID \n";
		
		for(int i=0;i<list.size();i++) {
			str += list.get(i);
		}
		
		return str;
		
	}
	
	public String printCampingCarListByButton(ButtonGroup bg, String info) {
		
		ArrayList<String> list = null;
		Enumeration<AbstractButton> btn = bg.getElements();
		String btnText = "";
		String str = "캠핑카ID \t차명 \t차량번호 \t 승차인원수 \t 제조회사 \t 제조연도 \t 누적주행거리 \t 대여비용 \t캠핑카등록일자 \t 대여회사ID \n";
		
		while (btn.hasMoreElements()) { 
			AbstractButton ab = btn.nextElement();

			if (((JRadioButton) ab).isSelected()) {
				btnText = ((JRadioButton) ab).getText();
			}
		}
		
		
		switch (btnText) {
		case "캠핑카ID":
			list = customerModel.CampingCar_List_By_CampingCarID(info);
			break;
			
		case "차명":
			list = customerModel.CampingCar_List_By_CampingCarName(info);
			break;
			
		case "최소승차인원":
			list = customerModel.CampingCar_List_By_CampingCarSeats(info);
			break;
			
		case "제조회사":
			list = customerModel.CampingCar_List_By_CampingCarManufacture(info);
			break;
			
		case "최대주행거리":
			list = customerModel.CampingCar_List_By_CampingCarMileage(info);
			break;
			
		case "최대대여비용(단위:만원)":
			list = customerModel.CampingCar_List_By_CampingCarPrice(info);
			break;
			
		default:
			return str;
		}

	
		for(int i=0;i<list.size();i++) {
			str += list.get(i);
		}
		
		
		return str;
	}
	
	public void rentCampingCar(RentInfo rent) {
		
		int res = customerModel.Rent_CampingCar(rent);
		switch(res){
		case 1: 
			JOptionPane.showMessageDialog(null, "대여 완료");
			break;
		case 2:
			JOptionPane.showMessageDialog(null, "옳바른 데이터값을 입력해주세요.");
			break;
		default:
			JOptionPane.showMessageDialog(null, "데이터값 재입력");
			break;
		}
	}
	
}
