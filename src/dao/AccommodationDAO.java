package dao;

import java.util.List;

import javax.ejb.Local;

import model.Accommodation;
import model.Address;
import model.HouseRules;
import model.User;

@Local
public interface AccommodationDAO {

	public void createAccommodation(Accommodation accommodation);
	
	public void createAccommodation(User user, String name, Address address, HouseRules houseRules, String type, int capacity, int numberOfRooms, String description);
	
	public void deleteAccommodation(Accommodation accommodation);
	
	Accommodation getAccommodation(int accommodationId);
	
	public void updateAccommodation(Accommodation accommodation);
	
	public List<Accommodation> getAllAccommodation();
	
	public List<Accommodation> getUserAccommodation(User user);
	
}
