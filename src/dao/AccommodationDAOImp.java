package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Accommodation;
import model.Address;
import model.HouseRules;
import model.User;

@Stateless
public class AccommodationDAOImp implements AccommodationDAO {
	
	@PersistenceContext(unitName = "pu")
	private EntityManager em;


	@Override
	public void createAccommodation(Accommodation accommodation) {
		em.persist(accommodation);
	}
	
	@Override
	public void createAccommodation(User user, String name, Address address, HouseRules houseRules, String type, int capacity, int numberOfRooms, String description) {
		em.persist( new Accommodation(user, name, address, houseRules, type, capacity, numberOfRooms, description) );
	}

	@Override
	public void deleteAccommodation(Accommodation accommodation) {
		// Don't use em.find() because it doesn't get pictures, and so create a persistent error
		em.remove( getAccommodation(accommodation.getId()) );
	}
	
	@Override
	public Accommodation getAccommodation(int accommodationId) {
		return em.createQuery("SELECT a FROM Accommodation a WHERE a.id = ?1", Accommodation.class).setParameter(1, accommodationId).getSingleResult();
		//return em.find(Accommodation.class, accommodationId);
	}
	

	@Override
	public void updateAccommodation(Accommodation accommodation) {
		em.createQuery("UPDATE Accommodation a SET a.name = ?1, a.type = ?2, a.numberOfRooms = ?3, a.capacity = ?4, a.description = ?5 WHERE a.id = ?6")
		.setParameter(1, accommodation.getName())
		.setParameter(2, accommodation.getType())
		.setParameter(3, accommodation.getNumberOfRooms())
		.setParameter(4, accommodation.getCapacity())
		.setParameter(5, accommodation.getDescription())
		.setParameter(6, accommodation.getId())
		.executeUpdate();
		
		em.merge(accommodation.getAddress());
		em.merge(accommodation.getHouseRules());
	}

	@Override
	public List<Accommodation> getAllAccommodation() {
		return em.createQuery("SELECT a FROM Accommodation a", Accommodation.class).getResultList();
	}

	@Override
	public List<Accommodation> getUserAccommodation(User user) {
		return em.createQuery("SELECT a FROM Accommodation a WHERE a.user = ?1", Accommodation.class).setParameter(1, user).getResultList();
	}
}
