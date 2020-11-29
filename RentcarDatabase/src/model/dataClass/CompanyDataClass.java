package model.dataClass;

public class CompanyDataClass {
	public String companyID;
	public String companyName;
	public String companyAddress;
	public String companyPhoneNumber;
	public String companyOfficerName;
	public String companyOfficerEmail;
	
	public boolean isNull() {
		return companyID.length() == 0 ||
				companyName.length() == 0 ||
				companyAddress.length() == 0 ||
				companyPhoneNumber.length() == 0 ||
				companyOfficerName.length() == 0 ||
				companyOfficerEmail.length() == 0;
	}
}
