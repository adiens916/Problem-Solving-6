package controller.dataClass;

public class CampingCarInfo {
    public String id;
    public String name;
    public String number;
    public String seats;
    public String manufacturer;
    public String builtDate;
    public String mileage;
    public String rentalFee;
    public String registryDate;
    public String companyId;

    /* 캠핑카 데이터 클래스에 빈 값이 들어 있는지 확인 */
    public boolean isNull() {
        return name.length() == 0 ||
                number.length() == 0 ||
                seats.length() == 0 ||
                manufacturer.length() == 0 ||
                builtDate.length() == 0 ||
                mileage.length() == 0 ||
                rentalFee.length() == 0 ||
                registryDate.length() == 0 ||
                companyId.length() == 0;
    }
}
