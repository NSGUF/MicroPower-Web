package com.bg;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mircolove.MircoLove;
import com.mircolove.MircoLoveDao;

/**
 * Servlet implementation class AddSelectServlet
 */
public class AddSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddSelectServlet() {
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

		String id = request.getParameter("love");
		MircoLoveDao.addSelectMircoLove(id);

		List<MircoLove> mircoLoves = MircoLoveDao.getUnselectMircoLoveList();
		request.getSession().setAttribute("mircoloves", mircoLoves);
		response.sendRedirect("bgBetter.jsp");
	}

}
