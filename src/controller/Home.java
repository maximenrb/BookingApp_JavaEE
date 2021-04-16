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
import dao.PictureDAO;
import model.Offer;


@WebServlet("/home")
public class Home extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@EJB
	private OfferDAO offerDAO;
	
	@EJB
	private PictureDAO pictureDAO;
       
    public Home() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Offer> offers = offerDAO.getLastOffer();
		
		request.setAttribute("offers", offers);
		request.setAttribute("searchType", "default");	
		
 		getServletContext().getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
	}

}
