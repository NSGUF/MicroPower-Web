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

import com.address.Address;
import com.address.AddressDao;
import com.donation.DonationInfo;
import com.donation.DonationInfoDao;
import com.mircolove.MircoLove;
import com.mircolove.MircoLoveDao;
import com.share.Sharer;
import com.share.SharerDao;

/**
 * Servlet implementation class ShowAddressServlet
 */
public class ShowAddressServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShowAddressServlet() {
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
		// 跨域问题
		response.setHeader("Access-Control-Allow-Origin",
				"http://localhost:3000");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		// 编码问题
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		// 获得传过来的参数
		String[] parameters = { "flag" };
		HashMap<String, String> map = Parameter.getParameter(
				getServletContext(), request, parameters);
		String flag = map.get(parameters[0]);

		PrintWriter out = response.getWriter();

		JSONArray array = null;
		List<Address> listAddress = null;
		listAddress = AddressDao.getListAddress(GetUser.getUser(request)
				.getUser_id());
		if (listAddress != null) {
			array = new JSONArray();
			for (int i = 0; i < listAddress.size(); i++) {
				Address address = listAddress.get(i);
				JSONObject obj = new JSONObject();
				try {
					obj.put("name", address.getName());
					obj.put("cellphone", address.getCellphone());
					obj.put("detail",
							address.getProvince() + " " + address.getCity()
									+ " " + address.getCounty() + " "
									+ address.getDetail());
					obj.put("is_default", address.getIs_default());
				} catch (Exception e) {
					e.printStackTrace();
				}
				array.add(obj);
			}
		}
		out.write(array.toString());
		out.flush();
	}

}
