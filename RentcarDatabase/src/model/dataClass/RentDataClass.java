package model.dataClass;

public class RentDataClass {
	public String campingCarID;
	public String license;
	public String rentStartDate;
	public String rentPeriod;
	public String rentEndDate;
	public String extraGoods;
	public String extraGoodsPrice;
	
	public boolean isNull() {
		return campingCarID.length() == 0 ||
				license.length() == 0 ||
				rentStartDate.length() == 0 ||
				rentPeriod.length() == 0 ||
				rentEndDate.length() == 0 ||
				extraGoods.length() == 0 ||
				extraGoodsPrice.length() == 0;
	}
}
