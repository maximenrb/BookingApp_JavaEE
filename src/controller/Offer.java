package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AccommodationDAO;
import dao.AmenityDAO;
import dao.BookingDAO;
import dao.OfferDAO;
import dao.PictureDAO;
import dao.RoomDAO;
import dao.TransactionDAO;
import dao.UserDAO;
import model.Amenity;
import model.Picture;
import model.Room;
import model.Transaction;
import model.User;

/**
 * Servlet implementation class Accommodation
 */
@WebServlet("/offer")
public class Offer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private UserDAO userDAO;
	
	@EJB
	private AccommodationDAO accommodationDAO;
	
	@EJB
	private PictureDAO pictureDAO;
	
	@EJB
	private OfferDAO offerDAO;
	
	@EJB
	private BookingDAO bookingDAO;
	
	@EJB
	private RoomDAO roomDAO;
	
	@EJB
	private AmenityDAO amenityDAO;
	
	@EJB
	private TransactionDAO transactionDAO;
	
	private model.Offer offer;
	private List<Room> rooms;
	private List<List<Amenity>> allAmenity;
	private List<Picture> pictures;
	
    public Offer() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Offer ID: " + request.getParameter("id"));
		
		offer = offerDAO.getOffer(Integer.valueOf(request.getParameter("id")));
		rooms = roomDAO.getAccommodationRoom(offer.getAccommodation());
		
		allAmenity = new ArrayList<>();
		
		for (Room room : rooms) {
			allAmenity.add(amenityDAO.getRoomAmenity(room));
		}
		
		sendOfferPage(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User)request.getSession().getAttribute("user");
		
		if (user == null) {
			response.sendRedirect(request.getContextPath() + "/login?redirect=offer?id=" + offer.getId());
			return;
			
		} else if (user.getMailAddress().equals(offer.getUser().getMailAddress())) {
			request.setAttribute("alertType", "alert-warning");
			request.setAttribute("alertMessage", "Vous ne pouvez pas réserver votre propre logement !");
			
			sendOfferPage(request, response);
			return;
		}
		
		String arrivalDate = request.getParameter("arrivalDate");
		String arrivalTime = request.getParameter("arrivalTime");
		String departureDate = request.getParameter("departureDate");
		String departureTime = request.getParameter("departureTime");
		String nbPerson = request.getParameter("nbPerson");
		
		String arrivalDatetime = arrivalDate + "T" + arrivalTime;
		String departureDatetime = departureDate + "T" + departureTime;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
		
		// Calculate number of night and total booking price
	    long nbNight = ChronoUnit.DAYS.between(LocalDate.parse(arrivalDatetime, dtf), LocalDate.parse(departureDatetime, dtf));
		double totalPrice = (double)nbNight * offer.getPricePerNight() + offer.getCleaningFee();
		
		// Get last version of user from database
		User senderUser = userDAO.getUser(user.getMailAddress());
		
		// Check if user balance is higher than booking price
		if (totalPrice > senderUser.getBalance()) {
			request.setAttribute("alertType", "alert-danger");
			request.setAttribute("alertMessage", "Le solde de votre compte est insuffisant ! Vous avez " + senderUser.getBalance() + 
					" € sur les " + totalPrice + " € demandés. Veuillez le recharger pour procéder à la réservation.");
			
			sendOfferPage(request, response);
			return;
		} 

		// Update sender and receiver user balance
		userDAO.debit(senderUser.getMailAddress(), totalPrice);
		userDAO.credit(offer.getUser().getMailAddress(), totalPrice);
		
		// Create associated transaction
		Transaction transaction = transactionDAO.createTransaction(user, offer.getUser(), totalPrice);
		
		// Update
		request.getSession().setAttribute("user", userDAO.getUser(user.getMailAddress()));
		
		// TODO error message if 0 night reserved
		// TODO verif date and time
		
		try {
			bookingDAO.createBooking(user, offer, transaction, sdf.parse(arrivalDatetime), sdf.parse(departureDatetime), Integer.valueOf(nbPerson), totalPrice);
			
		} catch (NumberFormatException | ParseException e) {
			System.err.println("Error while parsing date: " + e.getMessage());
		}
		
		response.sendRedirect(request.getContextPath() + "/bookingCRUD");
	}

	public void sendOfferPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("offer", offer);
		request.setAttribute("rooms", rooms);
		request.setAttribute("allAmenity", allAmenity);
		request.setAttribute("pictures", pictures);
		
		request.getRequestDispatcher("/WEB-INF/offer/offer.jsp").forward(request, response);
	}
}
