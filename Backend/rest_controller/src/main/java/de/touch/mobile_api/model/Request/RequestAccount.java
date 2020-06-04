package de.touch.mobile_api.model.Request;

import java.io.Serializable;
import java.util.Date;

import de.touch.mobile_api.config.Constants.AccountStatus;
import de.touch.mobile_api.config.Constants.Role;
import de.touch.mobile_api.model.account.Account;


/**
 * RequestAccount
 */
public class RequestAccount implements Serializable{

    private int id;
    private String username;

    private String password;

    private String email;
    
    private Date dob;

    private String gender;
    private String accountStatus;
    private String role;


    //need default constructor for JSON Parsing
	public RequestAccount(Account user)
	{
        this.setId(user.getPid());
		this.setUsername(user.getUsername());
        this.setPassword("");
        this.setEmail(user.getEmail());
        this.setDob(user.getDob());
        this.setGender(user.getGender().getDescription());
        this.setAccountStatus(user.getAccountstatus().getDescription());
        this.setRole(user.getRole().getDescription());
    }
    
    public RequestAccount(int id, String username) {
        this.setId(id);
        this.setUsername(username);
    }

	public RequestAccount(int id, String username, String password, String email, Date dob, String gender) {
        this.setId(id);
        this.setUsername(username);
        this.setPassword(password);
        this.setEmail(email);
        this.setDob(dob);
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    @Override
    public String toString() {
        return "RequestAccount [accountStatus=" + accountStatus + ", dob=" + dob + ", email=" + email + ", gender="
                + gender + ", password=" + password + ", role=" + role + ", username=" + username + "]";
    }  
}