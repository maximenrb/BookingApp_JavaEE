package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.User;

@Stateless
public class UserDAOImp implements UserDAO {
	
	@PersistenceContext(unitName = "pu")
	private EntityManager em;

	@Override
	public User createUser(String mailAddress, String hashedPassword, String firstname, String name, String phoneNumber, String userType, double amount) {
		User u = new User(mailAddress, hashedPassword, firstname, name, phoneNumber, userType, amount);
		em.persist(u);
		return u;
	}

	@Override
	public void deleteUser(String mailAddress) {
		em.remove(em.find(User.class, mailAddress));
	}

	@Override
	public User getUser(String mailAddress) {
		return em.find(User.class, mailAddress);
	}
	
	@Override
	public void updateUser(User user) {
		em.merge(user);
	}

	@Override
	public List<User> getAllUser() {
		return em.createQuery("SELECT u FROM User u", User.class).getResultList();
	}

	@Override
	public void changeName(String mailAddress, String name) {
		User u = getUser(mailAddress);
		u.setName(name);
	}

	@Override
	public void changeFirstname(String mailAddress, String firstname) {
		User u = getUser(mailAddress);
		u.setFirstname(firstname);
	}

	@Override
	public void changePhoneNumber(String mailAddress, String phoneNumber) {
		User u = getUser(mailAddress);
		u.setPhoneNumber(phoneNumber);
	}

	@Override
	public void changePassword(String mailAddress, String hashedPassword) {
		User u = getUser(mailAddress);
		u.setHashedPassword(hashedPassword);
	}

	@Override
	public void credit(String mailAddress, double amount) {
		User u = getUser(mailAddress);
		u.setBalance(u.getBalance() + amount);
	}

	@Override
	public void debit(String mailAddress, double amount) {
		User u = getUser(mailAddress);
		u.setBalance(u.getBalance() - amount);
	}
}
