package controller;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookingDAO;
import dao.TransactionDAO;
import model.Booking;
import model.User;

/**
 * Servlet implementation class BookingCRUD
 */
@WebServlet("/bookingCRUD")
public class BookingCRUD extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private BookingDAO bookingDAO;
	
	@EJB
	private TransactionDAO transactionDAO;

	private List<Booking> bookings;
	
    public BookingCRUD() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User)request.getSession().getAttribute("user");
		
		if (user == null) {
			response.sendRedirect(request.getContextPath() + "/login?redirect=bookingCRUD");
			return;
		}
		
		if (user.getUserType().equals("Admin")) {
			bookings = bookingDAO.getAllBooking();
			
		} else {
			bookings = bookingDAO.getUserBooking(user);
		}
		
		request.setAttribute("bookings", bookings);
		request.getRequestDispatcher("/WEB-INF/crud/bookingCRUD.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
