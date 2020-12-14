package Domain;

import DAO.Customer_dataAccess;
import Helpers.EntryVerifiers;

import java.sql.SQLException;

public class Customer extends User{
    private int customer_id;
    private String securityQuestion, securityAnswer;

    public Customer(int customer_id, String securityQuestion, String securityAnswer) {
        this.customer_id = customer_id;
        this.securityQuestion = securityQuestion;
        this.securityAnswer = securityAnswer;
    }

    public Customer() {
    }

    //constructor for userMgt tableview
    public Customer(int customer_id, String username, String pwd, String firstName, String lastName){
        setCustomer_id(customer_id);
        setUserName(username);
        setUserPassword(pwd);
        setFirstName(firstName);
        setLastName(lastName);
    }
    public Customer(int SSN, String username, String password, String first_name, String last_name, String streetAddress,
                    String cityAddress, String stateAddress, String zipAddress, String phoneNum, String email,
                    String securityQuestion, String securityAnswer){
        setCustomer_id(SSN);
        setUserName(username);
        setUserPassword(password);
        setFirstName(first_name);
        setLastName(last_name);
        setStreetAddress(streetAddress);
        setCityAddress(cityAddress);
        setStateAddress(stateAddress);
        setZipAddress(zipAddress);
        setUserPhoneNumber(phoneNum);
        setUserEmail(email);
        setSecurityQuestion(securityQuestion);
        setSecurityAnswer(securityAnswer);

    }

    public Customer(String username) {
        setUserName(username);
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getSecurityQuestion() {
        return securityQuestion;
    }

    public void setSecurityQuestion(String securityQuestion) {
        this.securityQuestion = securityQuestion;
    }

    public String getSecurityAnswer() {
        return securityAnswer;
    }

    public void setSecurityAnswer(String securityAnswer) {
        this.securityAnswer = securityAnswer;
    }


}
