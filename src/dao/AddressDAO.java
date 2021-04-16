package dao;

import javax.ejb.Local;

import model.Address;

@Local
public interface AddressDAO {

	public void createAddress(Address address);

	public Address createAddress(String streetAndNumber, String complement, String postalCode, String city, String country);
	
	public void updateAddress(Address address);
	
}
