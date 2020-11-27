package controller;

import view.CompanyView;
import model.CompanyModel;
import controller.dataClass.CompanyInfo;
import controller.dataClass.ResultState;
import java.util.ArrayList;

public class CompanyController {
	private final CompanyView companyView = new CompanyView();
	private final CompanyModel companyModel = new CompanyModel();
	
	public CompanyController() {
		setVisible(true);
		readCompanyList();
		listenToReadCompany();
		listenToInsertCompany();
		listenToUpdateCompany();
		listenToDeleteCompany();
		listenToRefreshCompany();
	}
	
	public void setVisible(boolean value) {
		companyView.setVisible(true);
	}
	
	public void readCompanyList() {
		ArrayList<CompanyInfo> companyList = companyModel.readCompanyList();
		companyView.readCompanyList(companyList);
	}
	
	public void listenToReadCompany() {
		companyView.searchButton.addActionListener(e -> {
			String searchBy = companyView.companyID.getText();
			CompanyInfo company = companyModel.readCompany(searchBy);
			companyView.readCompany(company);
		});
	}
	
	public void listenToInsertCompany() {
		companyView.insertButton.addActionListener(e ->{
			CompanyInfo company = companyView.getCompanyInput();
			ResultState result = companyModel.insertCompany(company);
			companyView.showCompanyInsertResult(result);
			readCompanyList();
		});
	}
	
	public void listenToUpdateCompany() {
		companyView.updateButton.addActionListener(e ->{
			CompanyInfo company = companyView.getCompanyInput();
			ResultState result = companyModel.updateCompany(company);
			companyView.showCompanyUpdateResult(result);
			readCompanyList();
			System.out.println(result);
		});
	}
	
	public void listenToDeleteCompany() {
		companyView.deleteButton.addActionListener(e ->{
			String companyID = companyView.companyID.getText();
			ResultState result = companyModel.deleteCompany(companyID);
			companyView.showCompanyDeleteResult(result);
			readCompanyList();
		});
	}
	
	public void listenToRefreshCompany() {
		companyView.refreshButton.addActionListener(e ->{
			readCompanyList();
		});
	}
	
	// ------- 테스트 영역 --------
	public static void main(String[] args) {
		CompanyController co = new CompanyController();
	}
}
