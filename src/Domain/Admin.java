package Domain;

public class Admin extends User{

    public String getMyNewValue() {
        return myNewValue;
    }

    public void setMyNewValue(String myNewValue) {
        this.myNewValue = myNewValue;
    }

    String myNewValue = "myNewValue";

}
