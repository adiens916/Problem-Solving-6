package model.dataClass;

public class ReturnDataClass {
    public String front;
    public String right;
    public String left;
    public String back;
    public String fix;
    public String campingCarId;

    public boolean isNull() {
        return front.length() == 0 ||
                right.length() == 0 ||
                left.length() == 0 ||
                back.length() == 0 ||
                fix.length() == 0 ||
                campingCarId.length() == 0;
    }

}
