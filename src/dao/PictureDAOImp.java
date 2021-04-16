package dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Accommodation;
import model.Picture;

@Stateless
public class PictureDAOImp implements PictureDAO {
	
	@PersistenceContext(unitName = "pu")
	private EntityManager em;
	
	@Override
	public void createPicture(Accommodation accommodation, String url) {
		em.persist(new Picture(url));
	}
}
