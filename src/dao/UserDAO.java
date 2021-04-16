package dao;


import java.util.List;

import javax.ejb.Local;

import model.User;

@Local
public interface UserDAO {
	
	public User createUser(String mailAddress, String hashedPassword, String firstname, String name, String phoneNumber, String userType, double balance);
	
	public void deleteUser(String mailAddress);

	public User getUser(String mailAddress);
	
	public void updateUser(User user);
	
	public List<User> getAllUser();

	public void changeName(String mailAddress, String name);
	
	public void changeFirstname(String mailAddress, String firstname);
	
	public void changePhoneNumber(String mailAddress, String phoneNumber);
	
	public void changePassword(String mailAddress, String hashedPassword);
	
	public void credit(String mailAddress, double amount);
	
	public void debit(String mailAddress, double amount);
	
}
