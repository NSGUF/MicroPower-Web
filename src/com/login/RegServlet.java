package com.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RegServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		UserDao userDao = new UserDao();
		String cell_phone_id = request.getParameter("cell_phone_id");
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
		request.getRequestDispatcher("setting.jsp").forward(request, response);
	}
}