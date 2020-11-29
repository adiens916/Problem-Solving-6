package model.dataClass;

public class GarageDataClass {
    public String id;
    public String name;
    public String address;
    public String number;
    public String manager;
    public String emailAddress;

    public boolean isNull() {
        return name.length() == 0 ||
                address.length() == 0 ||
                number.length() == 0 ||
                manager.length() == 0 ||
                emailAddress.length() == 0;
    }
}
