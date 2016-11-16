package com.sunlj.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(description = "LoginServlet",
	urlPatterns = {"/LoginServlet"},
	initParams = {
		@WebInitParam(name="user", value="username"),
		@WebInitParam(name="password", value="password")
	})
public class RequestSevlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void init() throws ServletException {
		// create DB connection resouce and set it to Servlet context
		if (getServletContext().getInitParameter("dbURL")
				.equals("jdbc:mysql://localhost:3306/blog") &&
				getServletContext().getInitParameter("dbUser")
				.equals("mysql_user") &&
				getServletContext().getInitParameter("dbUserPwd").equals("mysql_pwd")) {
			getServletContext().setAttribute("DB_SUCCESS", "TRUE");
		} else {
			throw new ServletException("DB Connection Error!");
		}
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get request parameters username and password;
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		// get ServletConfig init Params
		String userId = getServletConfig().getInitParameter("user");
		String userPwd = getServletConfig().getInitParameter("password");
		request.setCharacterEncoding("UTF-8");

		log("User = " + username + " Password = " + password);
		if (userId.equals(username) && userPwd.equals(password)) {
			response.sendRedirect("loginSuccess.jsp");
		} else {
			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/index.html");
			PrintWriter	out = response.getWriter();
			out.println("<font color=red>Ether username or password is wrong</font>");
			requestDispatcher.include(request, response);		
		}
	}

}
