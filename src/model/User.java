package model;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "user")
public class User {

	@Id	
	@Column(name = "mail", nullable = false)
	private String mailAddress;

	@Column(name = "password", nullable = false)
	private String hashedPassword;

	@Column(nullable = false)
	private String firstname;

	@Column(nullable = false)
	private String name;

	@Column(name = "phone", nullable = false)
	private String phoneNumber;

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Calendar date;

	@Column(name = "type", nullable = false)
	private String userType;

	@Column(nullable = false)
	private double balance;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "user")
	private List<Accommodation> accommodations;

	public User() {}

	public User(String mailAddress, String hashedPassword, String firstname, String name, String phoneNumber, String userType, double balance) {
		this.mailAddress = mailAddress;
		this.hashedPassword = hashedPassword;
		this.firstname = firstname;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.date = new GregorianCalendar();
		this.userType = userType;
		this.balance = balance;
	}
	
	public void update( String firstname, String name, String phoneNumber) {
		this.firstname = firstname;
		this.name = name;
		this.phoneNumber = phoneNumber;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	public String getHashedPassword() {
		return hashedPassword;
	}

	public void setHashedPassword(String hashedPassword) {
		this.hashedPassword = hashedPassword;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	
}
