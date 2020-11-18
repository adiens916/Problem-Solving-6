package controller.dataClass;

import java.util.ArrayList;

import view.AdminView;
import model.AdminModel;

public class AdminInfo {
	//CapingCarReturn리스트를 불러오기 위한 변수들
	public String front,right,left,back,fixData,campingcarId,rentId;
	//Garage리스트를 불러오기 위한 변수들
	public String torepair,garageId,repairListLog,repairListFixDate,repairListprice,repairListDuedate,repairListOtherInfo;
	//입력된 데이터들이 모두 입려되었는지 확인하기 위한 메소드
	 public boolean isNull(ArrayList<AdminInfo> adminData) {
		 AdminInfo admin = adminData.get(0);
		 return admin.repairListLog.length()==0||admin.repairListFixDate.length()==0||
				admin.repairListprice.length()==0|| admin.repairListDuedate.length()==0||
				admin.repairListOtherInfo.length()==0||admin.garageId.length()==0||
				admin.torepair.length()==0;
	 }
	 
	 //ReturnToCampingcarList메소드에서 Campingcar_list의 컬럼들을 받기위한 변수들
	 public String campingCarName = null;
	 public	String campingCarNumber = null;
	 public	String campingCarSits = null;
	 public	String campingCarManufacutre = null;
	 public	String campingCarManufactureYear = null;
	 public	String campingCarMileage = null;
	 public	String campingCarRentprice = null;
	 public	String campingCarRegitstdate = null;
	 public	String campingCarRentCompanyId = null;
}
