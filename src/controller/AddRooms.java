package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AccommodationDAO;
import dao.AmenityDAO;
import dao.RoomDAO;
import model.Accommodation;
import model.Room;
import model.User;

@WebServlet("/addRooms")
public class AddRooms extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@EJB
	private AccommodationDAO accommodationDAO;
	
	@EJB
	private RoomDAO roomDAO;
	
	@EJB
	private AmenityDAO amenityDAO;
	
	private List<Accommodation> accommodations;
       
    public AddRooms() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final HttpSession session = request.getSession();
		
		User user = (User) session.getAttribute("user");
		
		if (user == null) {
			response.sendRedirect(request.getContextPath() + "/login?redirect=addRooms");
			return;
		}
		
		accommodations = accommodationDAO.getUserAccommodation(user);
		
		if (accommodations.isEmpty()) {
			System.out.println("You must add Accommodation first");
			
			request.setAttribute("alertType", "alert-warning");
			request.setAttribute("alertMessage", "Vous devez ajouter au moins un logement avant de pouvoir ajouter des pièces !");
			request.getRequestDispatcher("/WEB-INF/accommodation/addAccommodation.jsp").forward(request, response);
			
		} else {
			request.setAttribute("accommodations", accommodations);
			request.getRequestDispatcher("/WEB-INF/accommodation/addRooms.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accommodationIndex = request.getParameter("accommodationIndex");
		Accommodation accommodation = accommodations.get(Integer.valueOf(accommodationIndex));
		
		String type = request.getParameter("type");
		
		ArrayList<String> amenities = new ArrayList<String>();
		String[] values = null;
		
		switch (type) {
		
		case "bedroom":
			values = request.getParameterValues("bedroomAmenities");
			String singleBed = request.getParameter("singleBedNumber");
			String doubleBed = request.getParameter("doubleBedNumber");
			int singleBedNumber = 0;
			int doubleBedNumber = 0;
			try {
				singleBedNumber = Integer.parseInt(singleBed);
				doubleBedNumber = Integer.parseInt(doubleBed);
				for (int i=0 ; i<singleBedNumber ; i++) amenities.add("singleBed");
				for (int i=0 ; i<doubleBedNumber ; i++) amenities.add("doubleBed");
			} catch (NumberFormatException nfe) {
				nfe.printStackTrace();
			}
			break;
			
		case "bathroom":
			values = request.getParameterValues("bathroomAmenities");
			break;
			
		case "kitchen":
			values = request.getParameterValues("kitchenAmenities");
			break;
		}
		
		if (values != null) for (int i=0 ; i<values.length ; i++) amenities.add(values[i]);
		
		if (amenities == null || amenities.size() == 0) {
			request.setAttribute("alertType", "alert-warning");
			request.setAttribute("alertMessage", "Veuillez sélectionner au moins un équipement !");
			doGet(request, response);
		} else {
			Room room = roomDAO.createRoom(accommodation, type);
			for (int i=0 ; i<amenities.size() ; i++) amenityDAO.createAmenity(room, amenities.get(i));
			request.setAttribute("alertType", "alert-success");
			request.setAttribute("alertMessage", "La pièce a été ajoutée avec succès !");
			doGet(request, response);
		}
		
	}

}
