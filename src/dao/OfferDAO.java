package dao;

import java.util.Calendar;
import java.util.List;

import javax.ejb.Local;

import model.Accommodation;
import model.Offer;
import model.User;

@Local
public interface OfferDAO {

	public void createOffer(User user, Accommodation accommodation, Calendar startAvailability, Calendar endAvailability, double pricePerNight, double cleaningFee);

	public void deleteOffer(Offer offer);
	
	Offer getOffer(int offerId);
	
	public void updateOffer(Offer offer);
	
	public List<Offer> getAllOffer();
	
	public List<Offer> getUserOffer(User user);

	public List<Offer> getLastOffer();
	
	public List<Offer> SearchedOffer(String ville, Calendar startAvailability, Calendar endAvailability, int capacity);

}
