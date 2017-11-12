package com.react;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.login.User;
import com.login.UserDao;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
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

		String[] paramters = { "tellphone" };

		HashMap<String, String> map = Parameter.getParameter(
				getServletContext(), request, paramters);

		String cell_phone_id = map.get(paramters[0]);
		// System.out.println(ret);
		UserDao userDao = new UserDao();
		User user = null;
		if (userDao.userIsExist(cell_phone_id)) {// 若没有此账号，向数据库添加此账号信息
			String user_id = "USER_ID" + userDao.userCount();
			String head_portrait = "http://localhost:8080/MicroPower/images/bett/b1.jpg";
			String pet_name = cell_phone_id;
			user = new User();
			user.setUser_id(user_id);
			user.setHead_portrait(head_portrait);
			user.setCell_phone_id(cell_phone_id);
			user.setPet_name(pet_name);
			userDao.saveUser(user);
		}
		// 登录
		else {
			user = userDao.login(cell_phone_id);
		}
		String userName = request.getParameter("cell_phone_id");
		Cookie theUsername = new Cookie("username", userName);
		theUsername.setMaxAge(2 * 24 * 60 * 60);
		response.addCookie(theUsername);
		request.getSession().setAttribute("user", user);
		
		JSONObject result = new JSONObject();
		result.put("flag", true);
		response.getWriter().write(result.toString());
		response.getWriter().close();
	}

}
