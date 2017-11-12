package com.volunteer;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.react.GetUser;

/**
 * Servlet implementation class IsVolunteerServlet
 */
public class IsVolunteerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public IsVolunteerServlet() {
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

		int flag = VolunteerDao.userIsExist(GetUser.getUser(request)
				.getUser_id());

		JSONObject result = new JSONObject();
		result.put("flag", flag);
		PrintWriter out = response.getWriter();
		out.write(result.toString());
		out.flush();
	}

}
