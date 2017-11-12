package com.react;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.login.User;
import com.share.Sharer;
import com.share.SharerDao;

/**
 * Servlet implementation class ShareServlet
 */
public class ShareServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShareServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
		SharerDao sharerDao = new SharerDao();
		Sharer sharer = new Sharer();

		String witness_id = "WITNESS" + sharerDao.listCount();

		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String witness_open_date = formatter.format(date);
		String[] paramters = { "title", "describe", "links", "minLinks" };
		HashMap<String, String> map = Parameter.getParameter(
				getServletContext(), request, paramters);

		String witness_title = map.get(paramters[0]);
		String witness_describe = map.get(paramters[1]);
		String witness_image = map.get(paramters[2]);
		String witness_min_image = map.get(paramters[3]);
		User user = null;
		user = GetUser.getUser(request);
		sharer.setUser_id(user.getUser_id());
		sharer.setWitness_describe(witness_describe);
		sharer.setWitness_id(witness_id);
		sharer.setWitness_image(witness_image);
		sharer.setWitness_min_image(witness_min_image);
		sharer.setWitness_title(witness_title);
		sharer.setWitness_open_date(witness_open_date);
		boolean flag = sharerDao.saveSharer(sharer);

		JSONObject result = new JSONObject();
		result.put("flag", flag);
		PrintWriter out = response.getWriter();
		out.write(result.toString());
		out.flush();
	}

}
