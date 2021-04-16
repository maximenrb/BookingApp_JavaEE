package dao;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import model.Booking;
import model.Offer;
import model.Transaction;
import model.User;

@Local
public interface BookingDAO {

	public void createBooking(User user, Offer offer, Transaction transaction, Date arrivalDate, Date departureDate, int nbPerson, double totalPrice);
	
	public void deleteBooking(int bookingId);
	
	public List<Booking> getAllBooking();
	
	public List<Booking> getUserBooking(User user);
}
