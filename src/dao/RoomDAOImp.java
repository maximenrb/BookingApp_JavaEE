package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Accommodation;
import model.Room;

@Stateless
public class RoomDAOImp implements RoomDAO {
	
	@PersistenceContext(unitName = "pu")
	private EntityManager em;

	@Override
	public Room createRoom(Accommodation accommodation, String name) {
		Room r = new Room(accommodation, name);
		em.persist(r);
		return r;
	}

	@Override
	public void deleteRoom(long id) {
		em.remove(getRoom(id));		
	}

	@Override
	public Room getRoom(long id) {
		return em.find(Room.class, id);
	}

	@Override
	public int getNumberOfRoomsInAccommodation(int accommodationId) {
		return em.createQuery("SELECT COUNT(r) FROM Room r WHERE r.accommodation.id = ?1", Room.class).setParameter(1, accommodationId).getFirstResult();
	}

	@Override
	public List<Room> getAccommodationRoom(Accommodation accommodation) {
		return em.createQuery("SELECT r FROM Room r WHERE r.accommodation = ?1", Room.class).setParameter(1, accommodation).getResultList();
	}
}
