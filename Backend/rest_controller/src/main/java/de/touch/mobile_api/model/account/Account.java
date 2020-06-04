package de.touch.mobile_api.model.account;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;

import de.touch.mobile_api.config.Constants.AccountStatus;
import de.touch.mobile_api.config.Constants.Gender;
import de.touch.mobile_api.config.Constants.Role;
import de.touch.mobile_api.util.AccountStatusConverter;
import de.touch.mobile_api.util.GenderConverter;
import de.touch.mobile_api.util.RoleConverter;

/**
 * @author Eric G. Werner <gruschtelapps@gmail.com>
 */
@Entity
@Table(name = "Account")
public class Account implements Serializable {

    //need default constructor for JSON Parsing
	public Account()
	{
		
	}

	public Account(String username, String password, String email, String dob) {
		this.setUsername(username);
        this.setPassword(password);
        this.setEmail(email);
        this.setdob(new Date());
        this.setGender(Gender.None);
        this.setAccountstatus(AccountStatus.Activated);
        this.setRole(Role.User);
	}

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pid;

    @Column(name = "Username", unique=true)
    @Length(max = 25)
    private String username;

    @Column(name = "Password")
    private String password;

    @Column(name = "Email", unique=true)
    private String email;

    @Column(name = "dob")
    private Date dob;

    @Column(name = "Role")
    @Convert(converter = RoleConverter.class)
    private Role role;

    @Column(name = "Gender")
    @Convert(converter = GenderConverter.class)
    private Gender gender;

    @Column(name = "Account_Status")
    @Convert(converter = AccountStatusConverter.class)
    protected AccountStatus accountStatus;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private ProfilePicture profilePicture;

    //
    //
    //

    // ID
    public int getPid() {
        return pid;
    }

    public void setPid(final int pid) {
        this.pid = pid;
    }

    // Username
    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    // Passwort
    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    // E-Mail
    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public void setdob(final Date dob) {
        this.dob = dob;
    }

    public Date getDob() {
        return dob;
    }

    // Role
    public Role getRole() {
        return role;
    }

    public void setRole(final Role role) {
        this.role = role;
    }

    // Gender
    public Gender getGender() {
        return gender;
    }

    public void setGender(final Gender gender) {
        this.gender = gender;
    }

    // Account Status
    public AccountStatus getAccountstatus() {
        return accountStatus;
    }

    public void setAccountstatus(final AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    // Profil Picture
    public ProfilePicture getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(final ProfilePicture profilePicture) {
        this.profilePicture = profilePicture;
    }

    //
    //
    //

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.pid;
        hash = 79 * hash + Objects.hashCode(this.username);
        hash = 79 * hash + Objects.hashCode(this.email);
        hash = 79 * hash + Objects.hashCode(this.password);
        return hash;
    }

    @Override
    public String toString() {
        return "Account [accountStatus=" + accountStatus + ", dob=" + dob + ", email=" + email + ", gender=" + gender
                + ", password=" + password + ", pid=" + pid + ", profilePicture=" + profilePicture + ", role=" + role
                + ", username=" + username + "]";
    }

	public void update(Account new_account) {
        this.setUsername(new_account.getUsername());
        this.setPassword(new_account.getPassword());
        this.setEmail(new_account.getEmail());
        this.setdob(new_account.getDob());
	}    
}