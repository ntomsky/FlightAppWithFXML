package Domain;

public class CustomerBuilder {
    private int customer_id;
    private String securityQuestion;
    private String securityAnswer;
    private int id;
    private String username;
    private String pwd;
    private String firstName;
    private String lastName;
    private String ssn;
    private String password;
    private String first_name;
    private String last_name;
    private String streetAddress;
    private String cityAddress;
    private String stateAddress;
    private String zipAddress;
    private String phoneNum;
    private String email;

    public CustomerBuilder setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
        return this;
    }

    public CustomerBuilder setSecurityQuestion(String securityQuestion) {
        this.securityQuestion = securityQuestion;
        return this;
    }

    public CustomerBuilder setSecurityAnswer(String securityAnswer) {
        this.securityAnswer = securityAnswer;
        return this;
    }

    public CustomerBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public CustomerBuilder setUsername(String username) {
        this.username = username;
        return this;
    }

    public CustomerBuilder setPwd(String pwd) {
        this.pwd = pwd;
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

    public CustomerBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public CustomerBuilder setFirst_name(String first_name) {
        this.first_name = first_name;
        return this;
    }

    public CustomerBuilder setLast_name(String last_name) {
        this.last_name = last_name;
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

    public CustomerBuilder setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
        return this;
    }

    public CustomerBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public Customer createCustomer() {
        return new Customer(customer_id, securityQuestion, securityAnswer);
    }
}