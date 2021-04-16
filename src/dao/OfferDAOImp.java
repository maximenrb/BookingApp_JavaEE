package dao;

import java.util.Calendar;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Accommodation;
import model.Offer;
import model.User;

@Stateless
public class OfferDAOImp implements OfferDAO {
	
	@PersistenceContext(unitName = "pu")
	private EntityManager em;

	@Override
	public void createOffer(User user, Accommodation accommodation, Calendar startAvailability, Calendar endAvailability, double pricePerNight, double cleaningFee) {
		Offer offer = new Offer(user, accommodation, startAvailability, endAvailability, pricePerNight, cleaningFee);
		em.persist(offer);
	}
	
	@Override
	public void deleteOffer(Offer offer) {
		em.remove(em.find(Offer.class, offer.getId()));
	}
	
	@Override
	public Offer getOffer(int offerId) {
		return em.find(Offer.class, offerId);
	}
	
	@Override
	public void updateOffer(Offer offer) {
		em.merge(offer);
	}

	@Override
	public List<Offer> getAllOffer() {
		return em.createQuery("SELECT o FROM Offer o", Offer.class).getResultList();
	}

	@Override
	public List<Offer> getUserOffer(User user) {
		return em.createQuery("SELECT o FROM Offer o WHERE o.user = ?1", Offer.class).setParameter(1, user).getResultList();
	}
	
	@Override
	public List<Offer> getLastOffer() {
		return em.createQuery("SELECT o FROM Offer o ORDER BY o.id DESC", Offer.class).getResultList();
	}
	
	@Override
	public List<Offer> SearchedOffer(String ville, Calendar startStay, Calendar endStay, int capacity){	
		return em.createQuery("SELECT o FROM Offer o, Accommodation a, Address ad WHERE o.accommodation = a AND a.address = ad AND ad.city = :ville "
				+ "AND o.startAvailability < :startStay "
				+ "AND (o.endAvailability > :endStay OR o.endAvailability = :endStay) "
				+ "AND a.capacity = :capacity",Offer.class)
				.setParameter("ville", ville)
				.setParameter("startStay", startStay)
				.setParameter("endStay", endStay)
				.setParameter("capacity", capacity)
				.getResultList();
	}
}
