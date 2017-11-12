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
import com.mircolove.MircoLove;
import com.mircolove.MircoLoveDao;

/**
 * Servlet implementation class ChildServlet
 */
public class ChildServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ChildServlet() {
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
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		MircoLoveDao mircoLoveDao = new MircoLoveDao();
		MircoLove mircoLove = new MircoLove();
		// 得到user
		User user = null;
		// 得到mircoLove的属性值
		String mircolove_id = "MIRCOLOVE" + mircoLoveDao.listCount();
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String mircolove_open_date = formatter.format(date);

		String[] paramters = { "target_amount", "divide_num", "list_title",
				"list_describe", "links", "minLinks" };

		HashMap<String, String> map = Parameter.getParameter(
				getServletContext(), request, paramters);

		String mircolove_target_amount = map.get(paramters[0]);
		String mircolove_divid_num = map.get(paramters[1]);
		String mircolove_list_title = map.get(paramters[2]);// =
															// request.getParameter("list_title");;
		String mircolove_list_describe = map.get(paramters[3]);// =
																// request.getParameter("list_describe");;
		String mircolove_list_image = map.get(paramters[4]).replace(",", " ");// =
																				// request.getParameter("img");;
		String mircolove_list_min_image = map.get(paramters[5]).replace(",",
				" ");
		// System.out.println(mircolove_list_image);
		
		user = GetUser.getUser(request);
		mircoLove.setMircolove_id(mircolove_id);
		mircoLove.setMircolove_target_amount(Integer
				.valueOf(mircolove_target_amount));
		mircoLove.setMircolove_open_date(mircolove_open_date);
		mircoLove.setMircolove_divid_num(Integer.valueOf(mircolove_divid_num));
		mircoLove.setMircolove_list_title(mircolove_list_title);
		mircoLove.setMircolove_list_describe(mircolove_list_describe);
		mircoLove.setMircolove_list_image(mircolove_list_image);
		mircoLove.setMircolove_list_min_image(mircolove_list_min_image);
		mircoLove.setUser_id(user.getUser_id());

		boolean flag = mircoLoveDao.saveMircoLove(mircoLove);

		JSONObject result = new JSONObject();
		result.put("flag", flag);
		PrintWriter out = response.getWriter();
		out.write(result.toString());
		out.flush();
	}
}