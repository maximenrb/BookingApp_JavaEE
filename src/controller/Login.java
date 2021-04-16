package controller;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import function.Hash;
import model.User;


@WebServlet("/login")
public class Login extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
	@EJB
	private UserDAO userDAO;
	
	private String redirect = null;
	
    public Login() {
        super();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	User user = (User) request.getSession().getAttribute("user");
    	
    	if (user != null) {
    		response.sendRedirect(request.getContextPath() + "/home");
    		
    	} else {
    		if (request.getParameter("redirect") != null) {
    			redirect = request.getParameter("redirect");
    		}
    		
    		RequestDispatcher vue = getServletContext().getRequestDispatcher("/WEB-INF/account/login.jsp");
    		vue.forward(request, response);
    	}	
	}
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			final HttpSession session = request.getSession();
			
			String mailAddress = request.getParameter("mail");
			String password = request.getParameter("pass");	
			String hpass = Hash.sha256(password);
			User user = userDAO.getUser(mailAddress);
			
			if (user == null || !user.getHashedPassword().equals(hpass)) {
				request.setAttribute("alertType", "alert-warning");
				request.setAttribute("alertMessage", "Les identifiants sont incorrects !");
				request.getRequestDispatcher("/WEB-INF/account/login.jsp").forward(request, response);
				
			} else {
				session.setAttribute("user", user);
				
				if (redirect != null) {
					response.sendRedirect(request.getContextPath() + "/" + redirect);
				} else {
					response.sendRedirect(request.getContextPath() + "/home");
				}
			}
		} catch (NullPointerException npe) {
			System.err.println("Missing params");
			//response.sendRedirect(request.getContextPath() + "/home");
		}
	}

}
