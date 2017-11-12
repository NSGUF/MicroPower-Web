package com.bg;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.login.User;
import com.login.UserDao;
import com.volunteer.VolunteerDao;

/**
 * Servlet implementation class VirifyVolunteerServlet
 */
public class VirifyVolunteerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VirifyVolunteerServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String id = request.getParameter("volunteer");
		String virify = request.getParameter("virify");
		if (virify.equals("1")) {
			VolunteerDao.virifyVolunteer(id);
		} else {
			VolunteerDao.unirifyVolunteer(id);
		}
		List<User> users = UserDao.getVolunteer();
		request.getSession().setAttribute("users", users);
		response.sendRedirect("bgVolunteer.jsp");
	}

}
