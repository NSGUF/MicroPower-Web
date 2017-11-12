package com.react;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.address.Address;
import com.address.AddressDao;

/**
 * Servlet implementation class AddReceiveServlet
 */
public class AddReceiveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddReceiveServlet() {
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

		AddressDao aDao = new AddressDao();

		String[] paramters = { "name", "cellphone", "province", "city",
				"county", "detail" };

		HashMap<String, String> map = Parameter.getParameter(
				getServletContext(), request, paramters);

		String name = map.get(paramters[0]);
		String cellphone = map.get(paramters[1]);
		String province = map.get(paramters[2]);
		String city = map.get(paramters[3]);
		String county = map.get(paramters[4]);
		String detail = map.get(paramters[5]);

		Address a = new Address(name, cellphone, province, city, county,
				detail, "");
		boolean flag = aDao
				.addAddress(a, GetUser.getUser(request).getUser_id());
		JSONObject result = new JSONObject();
		result.put("flag", flag);
		PrintWriter out = response.getWriter();
		out.write(result.toString());
		out.flush();
	}

}
