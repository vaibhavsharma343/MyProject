package com.app.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.app.database.DatabaseConnection;
import com.app.helper.EmailUtil;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstName =  request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		long mobileNum = Long.parseLong(request.getParameter("mobileNum"));
		String email =  request.getParameter("email");
		String password = request.getParameter("password");
		
		boolean saveDataStatus = DatabaseConnection.insertUserData(firstName, lastName, mobileNum, email, password);
		
		if(saveDataStatus) {
			int OTP =  (int)(Math.random() * 900000 ) + 100000;
			boolean OTPSentStatus = EmailUtil.sendRegisterOTP(email, firstName + " " + lastName, OTP);
			if(OTPSentStatus) {
				HttpSession session = request.getSession();
				session.setAttribute("sentOTP", OTP);
				session.setAttribute("userEmail", email);
				response.sendRedirect("verify_otp_page.html");
			}
			else {
//				System.out.println("OTP SENT FAILED");
				System.out.println("OTP SENT FAILED");
			}
		}else {
			System.out.println("Save Data Failed");
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