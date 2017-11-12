package com.bg;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.donation.DonationInfo;
import com.donation.DonationInfoDao;
import com.login.User;
import com.login.UserDao;
import com.mircolove.MircoLove;
import com.mircolove.MircoLoveDao;
import com.volunteer.VolunteerDao;

/**
 * Servlet implementation class VirifyDonationServlet
 */
public class VirifyDonationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VirifyDonationServlet() {
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

		String mircolove_id = request.getParameter("donation");
		String virify = request.getParameter("virify");
		if (virify.equals("1")) {
			DonationInfoDao.VirifyMircoLove(mircolove_id);
		} else {
			DonationInfoDao.UnvirifyMircoLove(mircolove_id);
		}
		List<DonationInfo> donations = DonationInfoDao.getNotVirifyDonation();
		request.getSession().setAttribute("donations", donations);
		response.sendRedirect("bgDonation.jsp");
	}

}
