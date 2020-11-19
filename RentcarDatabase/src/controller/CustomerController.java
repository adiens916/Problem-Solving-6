package controller;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import controller.dataClass.CustomerInfo;
import model.DataModel_Customer;

public class CustomerController {
	
	DataModel_Customer customerModel = new DataModel_Customer();

	//----
	public String printCustomerList() {

		String str = "운전면허번호 \t 이름 \t 주소 \t 번호 \t 이메일 \n";
		
		ArrayList<CustomerInfo> customerList = customerModel.getCustomerList();
		
		for (CustomerInfo customer : customerList) {
			str += customer.licenseId + "\t" +
	                customer.name + "\t" +
	                customer.address + "\t" +
	                customer.number + "\t" +
	                customer.emailAddress +"\n";
		}
		return str;
	}
	
	//----
	public String searchCustomerList(String id) {
		String str = "";
		
		str = customerModel.toStringFromCustomerInfo(customerModel.searchCustomerById(id));
		
		return str;
	
	}
	
	//----
	public void insertCustomerList(CustomerInfo customer) {
		
		int result = customerModel.addCustomer(customer);
		
		if (result == 1) {
			JOptionPane.showMessageDialog(null, "입력완료!");
		} else if (result == 2) {
			JOptionPane.showMessageDialog(null, "빈칸을 모두채워주세요");
		} else {
			JOptionPane.showMessageDialog(null, "다시입력해주세요!");
		}

	}
	
	public void editCustomerList(CustomerInfo customer) {
		
		
		int result = customerModel.updateCustomer(customer);
		
		
		if (result == 1) {
			JOptionPane.showMessageDialog(null, "수정완료");
		} else if (result == 2) {
			JOptionPane.showMessageDialog(null, "빈칸을 모두채워주세요");
		} else {
			JOptionPane.showMessageDialog(null, "다시입력하세요!");
		}
	}
	
	public void deleteCustomerList(String id) {
		
		
		/* 모델에 id를 넘겨주고, 처리 결과를 가져옴 */
		int result = customerModel.deleteCustomer(id);

		if (result == 1) {
			JOptionPane.showMessageDialog(null, "삭제 완료");
		}else {
			JOptionPane.showMessageDialog(null, "ID를 입력해주세요.");
		}
	}
}
