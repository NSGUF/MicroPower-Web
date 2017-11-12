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
 * Servlet implementation class VirifyServlet
 */
public class VirifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VirifyServlet() {
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
		String mircolove_id = request.getParameter("love");
		String virify = request.getParameter("virify");

		if (virify.equals("1")) {
			MircoLoveDao.VirifyMircoLove(mircolove_id);
		} else {
			MircoLoveDao.UnvirifyMircoLove(mircolove_id);
		}
		List<MircoLove> mircoLoves = MircoLoveDao.getNotVirifyMircoLove();
		request.getSession().setAttribute("mircoloves", mircoLoves);
		response.sendRedirect("bgHelp.jsp");
	}

}
