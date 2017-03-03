package com.android.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.DataBase.DataBaseUtil;
import com.donation.DonationInfo;
import com.donation.DonationInfoDao;
import com.login.User;
import com.login.UserDao;
import com.mircolove.MircoLoveDao;
import com.share.Sharer;
import com.share.SharerDao;

/**
 * Servlet implementation class WitnessListAction
 */
public class WitnessListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WitnessListAction() {
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
		String parame = request.getParameter("parame1");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		UserDao userDao = new UserDao();
		User user = null;
		List<Sharer> listDonation = null;
		JSONArray array = null;
		if (parame.equals("witness")) {
			listDonation = SharerDao.getSharerList();
			if (listDonation != null) {
				array = new JSONArray();
				for (int i = 0; i < listDonation.size(); i++) {
					Sharer donationInfo = listDonation.get(i);
					JSONObject obj = new JSONObject();
					try {
						obj.put("witness_id", donationInfo.getWitness_id());
						obj.put("witness_open_date",
								donationInfo.getWitness_open_date());
						obj.put("witness_title",
								donationInfo.getWitness_title());
						obj.put("witness_describe",
								donationInfo.getWitness_describe());
						obj.put("witness_image",
								donationInfo.getWitness_image());
						obj.put("witness_min_image",
								donationInfo.getWitness_min_image());
						obj.put("witness_addr", donationInfo.getWitness_addr()
								+ "1");
						obj.put("is_witness_black",
								donationInfo.getIs_witness_black());
						obj.put("is_delete", donationInfo.getIs_delete());
						obj.put("witness_verify_state",
								donationInfo.getWitness_verify_state());
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
