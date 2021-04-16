package dao;

import java.util.List;

import javax.ejb.Local;

import model.Amenity;
import model.Room;

@Local
public interface AmenityDAO {
	
	public void createAmenity(Room room, String name);
	
	public void deleteAmenity(long id);
	
	public Amenity getAmenity(long id);

	public List<Amenity> getRoomAmenity(Room room);
	
}