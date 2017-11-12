package com.react;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.login.User;
import com.login.UserDao;

/**
 * Servlet implementation class SettingServlet
 */
public class SettingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SettingServlet() {
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
		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		User user = GetUser.getUser(request);
		UserDao userDao = new UserDao();
		PrintWriter out = response.getWriter();
		String[] parameters = { "head", "petName", "flag" };
		HashMap<String, String> map = Parameter.getParameter(
				getServletContext(), request, parameters);
		System.out.println(map.get(parameters[2]));
		if (map.get(parameters[2]) != null
				&& map.get(parameters[2]).equals("getInfo")) {
			JSONObject result = new JSONObject();
			result.put("head", user.getHead_portrait());
			result.put("petName", user.getPet_name());
			result.put("tellphone", user.getCell_phone_id());
			out.write(result.toString());
			System.out.println(result.toString());
		} else if (map.get(parameters[2]) != null
				&& map.get(parameters[2]).equals("upload")) {
			String head = map.get(parameters[0]);
			String petName = map.get(parameters[1]);
			userDao.modifyUser(user, petName, head);
			JSONObject result = new JSONObject();
			result.put("flag", true);
			out.write(result.toString());
			System.out.println(result.toString());
		}
		out.flush();
	}

}
