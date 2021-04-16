package dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Address;

@Stateless
public class AddressDAOImp implements AddressDAO {

	@PersistenceContext(unitName = "pu")
	private EntityManager em;
	
	@Override
	public void createAddress(Address address) {
		em.persist(address);
	}
	
	@Override
	public Address createAddress(String streetAndNumber, String complement, String postalCode, String city, String country) {
		Address address = new Address(streetAndNumber, complement, postalCode, city, country);
		em.persist(address);
		return address;
	}

	@Override
	public void updateAddress(Address address) {
		em.merge(address);
	}
}
