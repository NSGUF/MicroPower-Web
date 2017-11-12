package com.react;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.login.User;

/**
 * Servlet implementation class IsLoginServlet
 */
public class IsLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public IsLoginServlet() {
		super();
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

		PrintWriter out = response.getWriter();
		User user = GetUser.getUser(request);
		String userHeadUrl = "";
		boolean flag = false;
		if (user != null) {
			userHeadUrl = user.getHead_portrait();
			flag = true;
		}

		System.out.println("ok");
		JSONObject result = new JSONObject();
		result.put("flag", flag);
		result.put("userHeadUrl", userHeadUrl);
		out.write(result.toString());
		System.out.println(result.toString());
		out.flush();
	}

}
