package com.react;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class QuitServlet
 */
public class QuitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public QuitServlet() {
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

		HttpSession session = request.getSession();
		session.removeAttribute("user");
		session.invalidate();

		Cookie[] myCookie = request.getCookies();// 创建一个Cookie对象数组
		if (myCookie != null) {
			for (int i = 0; i < myCookie.length; i++) {
				if (myCookie[i].getName().equals("username")) {
					myCookie[i].setMaxAge(0);
					response.addCookie(myCookie[i]);
				}
			}
		}
		JSONObject result = new JSONObject();
		result.put("flag", true);
		PrintWriter out = response.getWriter();
		out.write(result.toString());
		out.flush();
	}

}
