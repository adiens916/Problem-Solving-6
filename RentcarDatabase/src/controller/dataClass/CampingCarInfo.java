package controller.dataClass;

public class CampingCarInfo {
	public String campingCarId;
	public String campingCarName = null;
	public String campingCarNumber = null;
	public String campingCarSits = null;
	public String campingCarManufacutre = null;
	public String campingCarManufactureYear = null;
	public String campingCarMileage = null;
	public String campingCarRentprice = null;
	public String campingCarRegitstdate = null;
	public String campingCarRentCompanyId = null;


    /* 캠핑카 데이터 클래스에 빈 값이 들어 있는지 확인 */
	public boolean isNull() {

		return campingCarId.length() == 0 || campingCarName.length() == 0 || campingCarNumber.length() == 0
				|| campingCarSits.length() == 0 || campingCarManufacutre.length() == 0
				|| campingCarManufactureYear.length() == 0 || campingCarMileage.length() == 0
				|| campingCarRentprice.length() == 0 || campingCarRegitstdate.length() == 0
				|| campingCarRentCompanyId.length() == 0;
	}
}
