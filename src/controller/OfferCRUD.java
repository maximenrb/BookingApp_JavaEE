package controller;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.OfferDAO;
import model.Offer;
import model.User;

/**
 * Servlet implementation class OfferCRUD
 */
@WebServlet("/offerCRUD")
public class OfferCRUD extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@EJB
	private OfferDAO offerDAO;
	
	private List<Offer> offers;
	
    public OfferCRUD() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User)request.getSession().getAttribute("user");
		
		if (user == null) {
			response.sendRedirect(request.getContextPath() + "/login?redirect=offerCRUD");
			return;
		}

		if (user.getUserType().equals("Admin")) {
			offers = offerDAO.getAllOffer();
			
		} else {
			offers = offerDAO.getUserOffer(user);
		}

		request.setAttribute("offers", offers);
		request.getRequestDispatcher("/WEB-INF/crud/offerCRUD.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User)request.getSession().getAttribute("user");
		
		if (user == null) {
			response.sendRedirect(request.getContextPath() + "/login?redirect=offerCRUD");
			return;
		}
		
		String index = request.getParameter("index");
		Offer offer = offers.get(Integer.valueOf(index));
		
		switch (request.getParameter("action")) {
			case "delete":
				offerDAO.deleteOffer(offer);
				break;
				
			case "edit":
				response.sendRedirect(request.getContextPath() + "/addOffer?edit=" + offer.getId()); 
				return;
				
			default:
				break;
		}
		
		doGet(request, response);
	}

}
