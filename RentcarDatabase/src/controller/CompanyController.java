package controller;

import model.DataModel_Company;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import controller.dataClass.*;

public class CompanyController {
	
	DataModel_Company companyModel = new DataModel_Company();
	
	//----
		public String printCompanyList() {

			String str = "회사 ID \t 회사명 \t 주소 \t 전화번호 \t 담당자이메일 \t\t 담당자이름 \n";
			
			
			str += companyModel.Company_List();;
			
			return str;
		}
		
		
		//----
		public String searchCustomerList(String id) {
			
			String str = "회사 ID \t 회사명 \t 주소 \t 전화번호 \t 담당자이메일 \t\t 담당자이름 \n"; 
			
			str += companyModel.search_Company_By_CompanyID(id);
			
			return str;
		
		}
		
		//----
		public void insertCompanyList(CompanyInfo company) {
			
			int result = companyModel.insert_Company_Data(company);
			
			if (result == 1) {
				JOptionPane.showMessageDialog(null, "입력완료!");
			}
			else if(result == 2) {
				JOptionPane.showMessageDialog(null, "모든 텍스트 필드를 채워주세요!");
			}
			else {
				JOptionPane.showMessageDialog(null, "다시입력해주세요!");
			}

		}
		
		public void editCompanyList(CompanyInfo company) {
			
			
			int result = companyModel.update_Company_Data(company);
			
			
			if (result == 1) {
				JOptionPane.showMessageDialog(null, "수정완료");
			} else if (result == 2) {
				JOptionPane.showMessageDialog(null, "빈칸을 모두채워주세요");
			} else {
				JOptionPane.showMessageDialog(null, "다시입력하세요!");
			}
		}
		
		
		public void deleteCompanyList(String id) {
			
			
			/* 모델에 id를 넘겨주고, 처리 결과를 가져옴 */
			int result = companyModel.delete_Company_Data(id);

			if (result == 1) {
				JOptionPane.showMessageDialog(null, "삭제 완료");
			}else {
				JOptionPane.showMessageDialog(null, "다시입력하세요!");
			}
		}
	
}
