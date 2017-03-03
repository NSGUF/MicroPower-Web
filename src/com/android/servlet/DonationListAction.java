package com.android.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.donation.DonationInfo;
import com.donation.DonationInfoDao;
import com.login.User;
import com.login.UserDao;
import com.mircolove.MircoLoveDao;

/**
 * Servlet implementation class DonationListAction
 */
public class DonationListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DonationListAction() {
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
		String parame = request.getParameter("parame1");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		UserDao userDao = new UserDao();
		User user = null;
		List<DonationInfo> listDonation = null;
		JSONArray array = null;
		if (parame.equals("donation")) {
			listDonation = DonationInfoDao.getDonationList();
			if (listDonation != null) {
				array = new JSONArray();
				for (int i = 0; i < listDonation.size(); i++) {
					DonationInfo donationInfo = listDonation.get(i);
					JSONObject obj = new JSONObject();
					try {
						obj.put("donation_id", donationInfo.getDonation_id());
						obj.put("donation_raise_goods",
								donationInfo.getDonation_raise_goods());
						obj.put("donation_trans_cost",
								donationInfo.getDonation_trans_cost());
						obj.put("donation_close_date",
								donationInfo.getDonation_close_date());
						obj.put("donation_open_date", MircoLoveDao
								.getTime(donationInfo.getDonation_open_date()));
						obj.put("donation_title",
								donationInfo.getDonation_title());
						obj.put("donation_describe",
								donationInfo.getDonation_describe());
						obj.put("donation_image",
								donationInfo.getDonation_image());
						obj.put("donation_min_image",
								donationInfo.getDonation_min_image());
						obj.put("donation_select_need_or_dona",
								donationInfo.getDonation_select_need_or_dona());
						obj.put("donation_addr",
								donationInfo.getDonation_addr() + "1");
						obj.put("is_donation_black",
								donationInfo.getIs_donation_black());
						obj.put("is_success", donationInfo.getIs_success());
						obj.put("is_delete", donationInfo.getIs_delete());
						obj.put("donation_verify_state",
								donationInfo.getDonation_verify_state());
						obj.put("user_id", donationInfo.getUser_id());
						user = userDao.getUser(donationInfo.getUser_id());
						obj.put("user_name", user.getPet_name());
						obj.put("user_head", user.getHead_portrait());

					} catch (Exception e) {

					}
					array.add(obj);
				}
			}
			out.write(array.toString());
			out.flush();
		}
	}
}
