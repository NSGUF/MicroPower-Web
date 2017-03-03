package com.mircolove;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.comment.CommentContent;
import com.comment.CommentContentDao;
import com.login.User;
import com.login.UserDao;

/**
 * Servlet implementation class DeleteMircoloveServlet
 */
public class DeleteMircoloveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteMircoloveServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		MircoLoveDao mircoLoveDao = new MircoLoveDao();

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String mircolove_id = request.getParameter("mircolove_id");

		mircoLoveDao.setMircoLoveDelete(mircolove_id);
		request.getRequestDispatcher("doItemFinish.jsp").forward(request,
				response);
	}

}
