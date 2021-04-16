package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AccommodationDAO;
import dao.OfferDAO;
import model.Accommodation;
import model.User;

/**
 * Servlet implementation class AddOffer
 */
@WebServlet("/addOffer")
public class AddOffer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	private AccommodationDAO accommodationDAO;
	
	@EJB
	private OfferDAO offerDAO;
	
	private List<Accommodation> accommodations;
	private model.Offer editOffer;

    public AddOffer() { 
    	super(); 
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final HttpSession session = request.getSession();
		
		User user = (User)session.getAttribute("user");
		
		if (user == null) {
			response.sendRedirect(request.getContextPath() + "/login?redirect=addOffer");
			return;
		}
		
		if (request.getParameter("edit") != null) {
			editOffer = offerDAO.getOffer(Integer.valueOf( request.getParameter("edit") ));

			if (editOffer == null) {
				request.setAttribute("alertType", "alert-warning");
				request.setAttribute("alertMessage", "Cette offre n'existe pas !");
				request.getRequestDispatcher("/WEB-INF/offer/addOffer.jsp").forward(request, response);
				return;
				
			} else if ( !editOffer.getUser().getMailAddress().equals(user.getMailAddress()) && !user.getUserType().equals("Admin") ) {
				editOffer = null;
				
				request.setAttribute("alertType", "alert-danger");
				request.setAttribute("alertMessage", "Vous n'êtes pas autorisé à modifier cette offre !");
				request.getRequestDispatcher("/WEB-INF/offer/addOffer.jsp").forward(request, response);
				return;
			}
			
			request.setAttribute("offer", editOffer);
			request.getRequestDispatcher("/WEB-INF/addOffer.jsp").forward(request, response);
			return;
		}
		
		this.accommodations = accommodationDAO.getUserAccommodation(user);
		
		if (this.accommodations.isEmpty()) {
			System.out.println("You must add Accommodation first");
			
			request.setAttribute("alertType", "alert-warning");
			request.setAttribute("alertMessage", "Vous devez ajouter au moins un logement avant de pouvoir créer une offre !");
			request.getRequestDispatcher("/WEB-INF/accommodation/addAccommodation.jsp").forward(request, response);
			
		} else {
			request.setAttribute("accommodations", accommodations);
			request.getRequestDispatcher("/WEB-INF/offer/addOffer.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final HttpSession session = request.getSession();
		
		User user = (User)session.getAttribute("user");
		
		if (user == null) {
			response.sendRedirect(request.getContextPath() + "/login?redirect=addOffer");
			return;
		}
		
		String accommodationIndex = request.getParameter("accommodationIndex");
		String startAvailabilityStr = request.getParameter("startAvailability");
		String endAvailabilityStr = request.getParameter("endAvailability");
		String pricePerNight = request.getParameter("pricePerNight");
		String cleaningFee = request.getParameter("cleaningFee");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar startAvailability = new GregorianCalendar();
		Calendar endAvailability = new GregorianCalendar();
		
		try {
			startAvailability.setTime(sdf.parse(startAvailabilityStr));
			endAvailability.setTime(sdf.parse(endAvailabilityStr));
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		if (editOffer != null) {			
			editOffer.update(startAvailability, endAvailability, Double.valueOf(pricePerNight), Double.valueOf(cleaningFee));
			offerDAO.updateOffer(editOffer);
			
			editOffer = null;
			
			response.sendRedirect(request.getContextPath() + "/offerCRUD");
			return;
		}

		Accommodation accommodation = accommodations.get(Integer.valueOf(accommodationIndex));
		
		if (pricePerNight != null && cleaningFee != null) {
			offerDAO.createOffer(user, accommodation, startAvailability, endAvailability, Double.valueOf(pricePerNight), Double.valueOf(cleaningFee));	
		}

		request.setAttribute("alertType", "alert-success");
		request.setAttribute("alertMessage", "Offre créée avec succès !");
		request.setAttribute("accommodations", accommodations);
		
		request.getRequestDispatcher("/WEB-INF/offer/addOffer.jsp").forward(request, response);
	}
}
