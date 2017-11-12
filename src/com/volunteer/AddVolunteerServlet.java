package com.volunteer;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.react.GetUser;
import com.react.Parameter;

/**
 * Servlet implementation class AddVolunteerServlet
 */
public class AddVolunteerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddVolunteerServlet() {
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
		String[] paramters = { "wallet_id", "id_before", "id_after", "info",
				"links" };

		HashMap<String, String> map = Parameter.getParameter(
				getServletContext(), request, paramters);

		String wallet_id = map.get(paramters[0]);
		String id_before = map.get(paramters[1]);
		String id_after = map.get(paramters[2]);
		String info = map.get(paramters[3]);
		String links = map.get(paramters[4]);

		Volunteer volunteer = new Volunteer(wallet_id, "", info, links,
				id_before + " " + id_after, "");

		boolean flag = VolunteerDao.saveVolunteer(volunteer,
				GetUser.getUser(request).getUser_id());
		
		System.out.println(wallet_id.length());
		System.out.println(info.length());
		System.out.println(links.length());
		System.out.println(id_before.length());
		System.out.println(id_after.length());
		JSONObject result = new JSONObject();
		result.put("flag", flag);
		PrintWriter out = response.getWriter();
		out.write(result.toString());
		out.flush();
	}

}
