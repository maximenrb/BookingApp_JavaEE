package dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Transaction;
import model.User;

@Stateless
public class TransactionDAOImp implements TransactionDAO {

	@PersistenceContext(unitName = "pu")
	private EntityManager em;

	@Override
	public Transaction createTransaction(User sender, User receiver, double amount) {
		Transaction transaction = new Transaction(sender, receiver, amount);
		em.persist(transaction);
		return transaction;
	}

	@Override
	public Transaction getTransaction(long id) {
		return em.find(Transaction.class, id);
	}
}
