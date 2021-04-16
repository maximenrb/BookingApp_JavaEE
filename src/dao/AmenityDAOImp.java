package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Amenity;
import model.Room;

@Stateless
public class AmenityDAOImp implements AmenityDAO {
	
	@PersistenceContext(name = "pu")
	EntityManager em;

	@Override
	public void createAmenity(Room room, String name) {
		Amenity a = new Amenity(room, name);
		em.persist(a);
	}

	@Override
	public void deleteAmenity(long id) {
		em.remove(getAmenity(id));
	}

	@Override
	public Amenity getAmenity(long id) {
		return em.find(Amenity.class, id);
	}

	@Override
	public List<Amenity> getRoomAmenity(Room room) {
		return em.createQuery("SELECT a FROM Amenity a WHERE a.room = ?1", Amenity.class).setParameter(1, room).getResultList();
	}
}
