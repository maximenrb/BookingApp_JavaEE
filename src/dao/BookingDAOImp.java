package dao;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Booking;
import model.Offer;
import model.Transaction;
import model.User;

@Stateless
public class BookingDAOImp implements BookingDAO {

	@PersistenceContext(unitName = "pu")
	private EntityManager em;

	@Override
	public void createBooking(User user, Offer offer, Transaction transaction, Date arrivalDate, Date departureDate, int nbPerson, double totalPrice) {
		em.persist( new Booking(user, offer, transaction, arrivalDate, departureDate, nbPerson, totalPrice) );
	}

	@Override
	public void deleteBooking(int bookingId) {
		em.remove(em.find(Booking.class, bookingId));
	}

	@Override
	public List<Booking> getAllBooking() {
		return em.createQuery("SELECT b FROM Booking b", Booking.class).getResultList();
	}

	@Override
	public List<Booking> getUserBooking(User user) {
		return em.createQuery("SELECT b FROM Booking b WHERE b.user = ?1", Booking.class).setParameter(1, user).getResultList();
	}
}
