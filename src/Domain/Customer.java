package Domain;

import DAO.Customer_dataAccess;
import Helpers.EntryVerifiers;

import java.sql.SQLException;

public class Customer extends User{
    private int customer_id;
    private String securityQuestion, securityAnswer;



    public Customer() {
    }
    public Customer(String secretAnswer) throws SQLException, ClassNotFoundException {

    }

    public String getSecurityQuestion() {
        return securityQuestion;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
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
