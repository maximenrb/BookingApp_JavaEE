package dao;


import javax.ejb.Local;

import model.Transaction;
import model.User;

@Local
public interface TransactionDAO {
	
	public Transaction createTransaction(User sender, User receiver, double amount);
	
	public Transaction getTransaction(long id);
	
}
