package controller;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AccommodationDAO;
import dao.BookingDAO;
import dao.OfferDAO;
import dao.TransactionDAO;
import dao.UserDAO;
import model.User;

@WebServlet("/adminSearch")
public class AdminSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private UserDAO userDAO;
	
	@EJB
	private AccommodationDAO accommodationDAO;
	
	@EJB
	private OfferDAO offerDAO;
	
	@EJB
	private BookingDAO bookingDAO;
	
	@EJB
	private TransactionDAO transactionDAO;
       
    public AdminSearch() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User)request.getSession().getAttribute("user");
		
		if (user == null) {
			response.sendRedirect(request.getContextPath() + "/login?redirect=adminSearch");
			return;
			
		} else if (!user.getUserType().equals("Admin")) {
			response.sendRedirect(request.getContextPath() + "/home");
			return;
		}

		request.getRequestDispatcher("/WEB-INF/admin/adminSearch.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User)request.getSession().getAttribute("user");
		
		if (user == null) {
			response.sendRedirect(request.getContextPath() + "/login?redirect=adminSearch");
			return;
			
		} else if (!user.getUserType().equals("Admin")) {
			response.sendRedirect(request.getContextPath() + "/home");
			return;
		}
		
		String searchedUserStr = request.getParameter("searchInput");
		User searchedUser = userDAO.getUser(searchedUserStr);
		
		if (searchedUser == null) {
			request.setAttribute("alertType", "alert-warning");
			request.setAttribute("alertMessage", "L'utilisateur recherché n'existe pas dans la base de donnée !");
			request.getRequestDispatcher("/WEB-INF/admin/adminSearch.jsp").forward(request, response);
			return;
		}
		
		request.setAttribute("accommodations", accommodationDAO.getUserAccommodation(searchedUser));
		request.setAttribute("offers", offerDAO.getUserOffer(searchedUser));
		request.setAttribute("bookings", bookingDAO.getUserBooking(searchedUser));
		
		request.getRequestDispatcher("/WEB-INF/admin/adminSearch.jsp").forward(request, response);
	}
}
