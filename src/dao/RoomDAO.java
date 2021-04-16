package dao;

import java.util.List;

import javax.ejb.Local;

import model.Accommodation;
import model.Room;

@Local
public interface RoomDAO {

	public Room createRoom(Accommodation accommodation, String name);
	
	public void deleteRoom(long id);
	
	public Room getRoom(long id);
	
	public int getNumberOfRoomsInAccommodation(int id);
	
	public List<Room> getAccommodationRoom(Accommodation accommodation);
	
}
