package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.OfferDAO;
import model.Offer;

/**
 * Servlet implementation class displaySearch
 */
@WebServlet("/displaySearch")
public class DisplaySearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private OfferDAO offerDAO;
	
	private List<Offer> offers;

    public DisplaySearch() { 
    	super(); 
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		
		try {

		String searchedVille = request.getParameter("ville");
		String searchedStartAvailabilityStr = request.getParameter("startStay");
		String searchedEndAvailabilityStr = request.getParameter("endStay");
		int searchedCapacity = Integer.parseInt(request.getParameter("capacity"));
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar searchedStartAvailability = new GregorianCalendar();
		Calendar searchedEndAvailability = new GregorianCalendar();
		
		try {
			searchedStartAvailability.setTime(sdf.parse(searchedStartAvailabilityStr));
			searchedEndAvailability.setTime(sdf.parse(searchedEndAvailabilityStr));
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		offers = offerDAO.SearchedOffer(searchedVille, searchedStartAvailability, searchedEndAvailability, searchedCapacity);
		
		request.setAttribute("offers", offers);
		request.setAttribute("searchType", "new");

				
		} catch(NullPointerException npe) {
			npe.printStackTrace();
			System.err.println("Missing params: " + npe.getMessage());
			response.sendRedirect(request.getContextPath() + "/home");
		}	
		
		RequestDispatcher vue = getServletContext().getRequestDispatcher("/WEB-INF/home.jsp");
		vue.forward(request, response);
			
	}

}
