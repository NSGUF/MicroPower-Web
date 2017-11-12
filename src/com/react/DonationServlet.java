package com.react;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.donation.DonationInfo;
import com.donation.DonationInfoDao;
import com.login.User;
import com.mircolove.MircoLove;
import com.mircolove.MircoLoveDao;

/**
 * Servlet implementation class DonationServlet
 */
public class DonationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DonationServlet() {
		super();
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
		response.setHeader("Access-Control-Allow-Origin",
				"http://localhost:3000");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		DonationInfoDao donationDao = new DonationInfoDao();
		DonationInfo donationInfo = new DonationInfo();
		// 得到user
		User user = null;
		// 得到donation的属性值
		String donation_id = "DONATIONINFO" + donationDao.listCount();
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String donation_open_date = formatter.format(date);

		String[] paramters = { "title", " trans_cost", "raise_goods",
				"close_date", "describe", "links", "minLinks", "need_or_dona" };

		HashMap<String, String> map = Parameter.getParameter(
				getServletContext(), request, paramters);

		String donation_title = map.get(paramters[0]);
		String donation_trans_cost = map.get(paramters[1]);
		String donation_raise_goods = map.get(paramters[2]);
		String donation_close_date = map.get(paramters[3]);
		String donation_describe = map.get(paramters[4]);
		String donation_image = map.get(paramters[5]);
		String donation_min_image = map.get(paramters[6]);
		String donation_select_need_or_dona = map.get(paramters[7]);

		user = GetUser.getUser(request);

		donationInfo.setDonation_id(donation_id);
		donationInfo.setDonation_raise_goods(donation_raise_goods);
		if (donation_trans_cost == null) {
			donation_trans_cost = "0";
		}
		donationInfo.setDonation_trans_cost(Integer
				.valueOf(donation_trans_cost));
		donationInfo.setDonation_close_date(donation_close_date);
		donationInfo.setDonation_open_date(donation_open_date);
		donationInfo.setDonation_title(donation_title);
		donationInfo.setDonation_describe(donation_describe);
		donationInfo.setDonation_image(donation_image);
		donationInfo.setDonation_min_image(donation_min_image);
		donationInfo.setDonation_select_need_or_dona(Integer
				.valueOf(donation_select_need_or_dona));
		donationInfo.setUser_id(user.getUser_id());
		boolean flag = donationDao.saveDonation(donationInfo);

		JSONObject result = new JSONObject();
		result.put("flag", flag);
		PrintWriter out = response.getWriter();
		out.write(result.toString());
		out.flush();
	}

}
