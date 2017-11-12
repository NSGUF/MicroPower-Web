package com.bg;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.donation.DonationInfo;
import com.donation.DonationInfoDao;
import com.share.Sharer;
import com.share.SharerDao;

/**
 * Servlet implementation class VirifyShareServlet
 */
public class VirifyShareServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VirifyShareServlet() {
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
		String mircolove_id = request.getParameter("share");
		String virify = request.getParameter("virify");
		if (virify.equals("1")) {
			SharerDao.VirifyMircoLove(mircolove_id);
		} else {
			SharerDao.UnvirifyMircoLove(mircolove_id);
		}
		List<Sharer> shares = SharerDao.getNotVirifySharer();
		request.getSession().setAttribute("shares", shares);
		response.sendRedirect("bgShare.jsp");
	}

}
