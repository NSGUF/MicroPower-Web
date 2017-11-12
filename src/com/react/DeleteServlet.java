package com.react;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.donation.DonationInfoDao;
import com.mircolove.MircoLoveDao;
import com.share.SharerDao;

/**
 * Servlet implementation class DeleteServlet
 */
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteServlet() {
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
		response.setHeader("Access-Control-Allow-Origin",
				"http://localhost:3000");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		String[] paramters = { "id", "flag" };

		HashMap<String, String> map = Parameter.getParameter(
				getServletContext(), request, paramters);
		String id = map.get(paramters[0]);
		String flag = map.get(paramters[1]);
		boolean flag1 = false;
		System.out.println(id + flag);
		if (flag.equals("help")) {
			MircoLoveDao mircoLoveDao = new MircoLoveDao();
			flag1 = mircoLoveDao.setMircoLoveDelete(id);
		} else if (flag.equals("donation")) {
			DonationInfoDao dao = new DonationInfoDao();
			flag1 = dao.setDonationInfoDelete(id);
		} else if (flag.equals("share")) {
			SharerDao dao = new SharerDao();
			flag1 = dao.setShareDelete(id);
		}

		JSONObject result = new JSONObject();
		result.put("flag", flag1);
		PrintWriter out = response.getWriter();
		out.write(result.toString());
		out.flush();
	}

}
