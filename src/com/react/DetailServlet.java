package com.react;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.donation.DonationInfo;
import com.donation.DonationInfoDao;
import com.login.User;
import com.login.UserDao;
import com.mircolove.MircoLove;
import com.mircolove.MircoLoveDao;
import com.share.Sharer;
import com.share.SharerDao;

/**
 * Servlet implementation class DetailServlet
 */
public class DetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DetailServlet() {
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
		String[] parameters = { "flag", "id" };
		HashMap<String, String> map = Parameter.getParameter(
				getServletContext(), request, parameters);
		String flag = map.get(parameters[0]);
		String id = map.get(parameters[1]);
		System.out.println(id);
		User user = GetUser.getUser(request);
		UserDao userDao = new UserDao();
		if (flag.equals("help")) {
			MircoLoveDao mDao = new MircoLoveDao();
			MircoLove mircolove = mDao.getMircoLove(id);
			JSONObject obj = new JSONObject();
			try {
				obj.put("id", mircolove.getMircolove_id());
				obj.put("target_amount", mircolove.getMircolove_target_amount());
				obj.put("raise_amount", mircolove.getMircolove_raise_amount());
				obj.put("open_date", MircoLoveDao.getTime(mircolove
						.getMircolove_open_date()));
				obj.put("title", mircolove.getMircolove_list_title());
				obj.put("image", mircolove.getMircolove_list_image());
				obj.put("support_time",
						mircolove.getMircolove_list_support_time());
				obj.put("describe", mircolove.getMircolove_list_describe());
				obj.put("user_id", mircolove.getUser_id());
				user = userDao.getUser(mircolove.getUser_id());
				obj.put("user_name", user.getPet_name());
				obj.put("user_head", user.getHead_portrait());
			} catch (Exception e) {
				e.printStackTrace();
			}
			response.getWriter().write(obj.toString());
			System.out.println(obj.toString());
		} else if (flag.equals("donation")) {
			DonationInfoDao mDao = new DonationInfoDao();
			DonationInfo donationInfo = mDao.getDonationInfo(id);
			JSONObject obj = new JSONObject();
			try {
				obj.put("id", donationInfo.getDonation_id());
				obj.put("raise_goods", donationInfo.getDonation_raise_goods());
				obj.put("open_date", MircoLoveDao.getTime(donationInfo
						.getDonation_open_date()));
				obj.put("title", donationInfo.getDonation_title());
				obj.put("image", donationInfo.getDonation_image());
				obj.put("describe", donationInfo.getDonation_describe());
				obj.put("select_need_or_dona",
						donationInfo.getDonation_select_need_or_dona());
				obj.put("user_id", donationInfo.getUser_id());
				obj.put("trans_cost", donationInfo.getDonation_trans_cost());
				user = userDao.getUser(donationInfo.getUser_id());
				obj.put("user_name", user.getPet_name());
				obj.put("user_head", user.getHead_portrait());
			} catch (Exception e) {
				e.printStackTrace();
			}
			response.getWriter().write(obj.toString());
			System.out.println(obj.toString());
		} else if (flag.equals("share")) {
			SharerDao mDao = new SharerDao();
			Sharer donationInfo = mDao.getSharer(id);
			JSONObject obj = new JSONObject();
			try {
				obj.put("id", donationInfo.getWitness_id());
				obj.put("open_date",MircoLoveDao.getTime(donationInfo.getWitness_open_date()));
				obj.put("title", donationInfo.getWitness_title());
				obj.put("image", donationInfo.getWitness_image());
				obj.put("user_id", donationInfo.getUser_id());
				obj.put("describe", donationInfo.getWitness_describe());
				user = userDao.getUser(donationInfo.getUser_id());
				obj.put("user_name", user.getPet_name());
				obj.put("user_head", user.getHead_portrait());
			} catch (Exception e) {
				e.printStackTrace();
			}
			response.getWriter().write(obj.toString());
			System.out.println(obj.toString());
		}
	}
}
