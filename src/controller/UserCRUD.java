package controller;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import model.User;

/**
 * Servlet implementation class UserCRUD
 */
@WebServlet("/userCRUD")
public class UserCRUD extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	private UserDAO userDAO;
	
	private List<User> users;
	
    public UserCRUD() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User)request.getSession().getAttribute("user");
		
		if (user == null) {
			response.sendRedirect(request.getContextPath() + "/login?redirect=userCRUD");
			return;
			
		} else if (!user.getUserType().equals("Admin")) {
			response.sendRedirect(request.getContextPath() + "/home");
			return;
		}
		
		users = userDAO.getAllUser();
		
		request.setAttribute("users", users);
		request.getRequestDispatcher("/WEB-INF/crud/userCRUD.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User)request.getSession().getAttribute("user");
		
		if (user == null) {
			response.sendRedirect(request.getContextPath() + "/login?redirect=userCRUD");
			return;
		}
		
		String index = request.getParameter("index");
		User user1 = users.get(Integer.valueOf(index));
		
		switch (request.getParameter("action")) {
			case "delete":
				userDAO.deleteUser(user1.getMailAddress());
				break;
				
			case "edit":
				response.sendRedirect(request.getContextPath() + "/register?edit=" + user1.getMailAddress()); 
				return;
				
			default:
				break;
		}
		
		doGet(request, response);
	}

}
