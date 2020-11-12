package controller.dataClass;

public class CustomerInfo {
    public String licenseId;
    public String name;
    public String address;
    public String number;
    public String emailAddress;

    public boolean isNull() {
        return licenseId.length() == 0 ||
                name.length() == 0 ||
                address.length() == 0 ||
                number.length() == 0 ||
                emailAddress.length() == 0;
    }
}
