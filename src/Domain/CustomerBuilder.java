package Domain;

import java.sql.SQLException;

public class CustomerBuilder {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String ssn;
    private String streetAddress;
    private String cityAddress;
    private String stateAddress;
    private String zipAddress;
    private String phoneNumber;
    private String email;
    private String secQuestion;
    private String secretAnswer;
    private int customer_id;

    public CustomerBuilder setUsername(String username) {
        this.username = username;
        return this;
    }

    public CustomerBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public CustomerBuilder setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public CustomerBuilder setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public CustomerBuilder setSSN(String ssn) {
        this.ssn = ssn;
        return this;
    }

    public CustomerBuilder setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
        return this;
    }

    public CustomerBuilder setCityAddress(String cityAddress) {
        this.cityAddress = cityAddress;
        return this;
    }

    public CustomerBuilder setStateAddress(String stateAddress) {
        this.stateAddress = stateAddress;
        return this;
    }

    public CustomerBuilder setZipAddress(String zipAddress) {
        this.zipAddress = zipAddress;
        return this;
    }

    public CustomerBuilder setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public CustomerBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public CustomerBuilder setSecQuestion(String secQuestion) {
        this.secQuestion = secQuestion;
        return this;
    }

    public CustomerBuilder setSecretAnswer(String secretAnswer) {
        this.secretAnswer = secretAnswer;
        return this;
    }

    public Customer createCustomer() throws SQLException, ClassNotFoundException {
        return new Customer(secretAnswer);
    }
}