package com.app.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import shadow.org.bson.Document;

import java.io.IOException;

import com.app.database.DatabaseConnection;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email =  request.getParameter("email_key");
		String password = request.getParameter("password_key");
		
		Document user = DatabaseConnection.userLogin(email);
		
		if(user != null) {
			if(email.equals(user.getString("userEmail")) && password.equals(user.getString("userPassword")) && user.getBoolean("isVerified")) {
				HttpSession session = request.getSession();
				session.setAttribute("name_key", user.getString("firstName") + " " + user.getString("lastName"));
				response.sendRedirect("home.jsp");
			}else if(email.equals(user.getString("userEmail")) && password.equals(user.getString("userPassword")) && !user.getBoolean("isVerified")) {
				System.out.println("Account not verified, please retry");
			}else if(email.equals(user.getString("userEmail")) && !password.equals(user.getString("userPassword")) && user.getBoolean("isVerified")) {
				System.out.println("The password is invalid");
			}
		}else {
			System.out.println("No Account Found");
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}