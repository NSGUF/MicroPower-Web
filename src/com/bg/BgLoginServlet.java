package com.bg;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.login.UserDao;
import com.mircolove.MircoLove;
import com.mircolove.MircoLoveDao;

/**
 * Servlet implementation class BgLoginServlet
 */
public class BgLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BgLoginServlet() {
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
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		String userName = request.getParameter("userName");
		String userPwd = request.getParameter("userPwd");
		UserDao userDao = new UserDao();

		if (userDao.bgUserIsExist(userName, userPwd)) {
			
			response.sendRedirect("BgHelpServlet");
		} else {
			response.sendRedirect("login.jsp");
		}
	}

}
