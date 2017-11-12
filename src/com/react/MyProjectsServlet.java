package com.react;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
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
import com.mircolove.MircoLove;
import com.mircolove.MircoLoveDao;
import com.share.Sharer;
import com.share.SharerDao;

/**
 * Servlet implementation class MyProjectsServlet
 */
public class MyProjectsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MyProjectsServlet() {
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
		// ��������
		response.setHeader("Access-Control-Allow-Origin",
				"http://localhost:3000");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		// ��������
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		// ��ô������Ĳ���
		String[] parameters = { "flag" };
		HashMap<String, String> map = Parameter.getParameter(
				getServletContext(), request, parameters);
		String flag = map.get(parameters[0]);

		PrintWriter out = response.getWriter();
		UserDao userDao = new UserDao();
		JSONArray array = null;
		User user = GetUser.getUser(request);
		List<MircoLove> listMircoLove = null;// һ��һ������Ϣ
		List<DonationInfo> listDonation = null;// ����
		List<Sharer> listShare = null;// �����֤
		if (flag.equals("help")) {
			listMircoLove = MircoLoveDao.getMyMircoLoveList(user.getUser_id());
		} else if (flag.equals("donation")) {
			listDonation = DonationInfoDao.getMyDonationList(user.getUser_id());
		} else if (flag.equals("share")) {
			listShare = SharerDao.getMySharerList(user.getUser_id());
		}
		if (listMircoLove != null) {
			array = new JSONArray();
			for (int i = 0; i < listMircoLove.size(); i++) {
				MircoLove mircolove = listMircoLove.get(i);
				JSONObject obj = new JSONObject();
				try {
					obj.put("id", mircolove.getMircolove_id());
					obj.put("target_amount",
							mircolove.getMircolove_target_amount());
					obj.put("raise_amount",
							mircolove.getMircolove_raise_amount());
					obj.put("open_date", MircoLoveDao.getTime(mircolove
							.getMircolove_open_date()));
					obj.put("title", mircolove.getMircolove_list_title());
					obj.put("image",
							mircolove.getMircolove_list_image().split(" ")[0]);
					obj.put("support_time",
							mircolove.getMircolove_list_support_time());
				} catch (Exception e) {
					e.printStackTrace();
				}
				array.add(obj);
			}
		} else if (listDonation != null) {
			array = new JSONArray();
			for (int i = 0; i < listDonation.size(); i++) {
				DonationInfo donationInfo = listDonation.get(i);
				JSONObject obj = new JSONObject();
				try {
					obj.put("id", donationInfo.getDonation_id());
					obj.put("raise_goods",
							donationInfo.getDonation_raise_goods());
					obj.put("open_date", MircoLoveDao.getTime(donationInfo
							.getDonation_open_date()));
					obj.put("title", donationInfo.getDonation_title());
					obj.put("image", donationInfo.getDonation_image()
							.split(" ")[0]);
					obj.put("select_need_or_dona",
							donationInfo.getDonation_select_need_or_dona());
				} catch (Exception e) {
					e.printStackTrace();
				}
				array.add(obj);
			}
		} else if (listShare != null) {
			array = new JSONArray();
			for (int i = 0; i < listShare.size(); i++) {
				Sharer donationInfo = listShare.get(i);
				JSONObject obj = new JSONObject();
				try {
					obj.put("id", donationInfo.getWitness_id());
					obj.put("open_date",donationInfo
							.getWitness_open_date());
					obj.put("title", donationInfo.getWitness_title());
					obj.put("image",
							donationInfo.getWitness_image().split(" ")[0]);
				} catch (Exception e) {
					e.printStackTrace();
				}
				array.add(obj);
			}
		}
		out.write(array.toString());
		System.out.println(array.toString());
		out.flush();
	}
}
