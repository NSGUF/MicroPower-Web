package com.android.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.comment.CommentContent;
import com.comment.CommentContentDao;
import com.mircolove.DoMircoLove;
import com.mircolove.DoMircoLoveDao;
import com.mircolove.MircoLove;
import com.mircolove.MircoLoveDao;

/**
 * Servlet implementation class ChangeDataAction
 */
public class ChangeDataAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ChangeDataAction() {
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
		MircoLoveDao mircoLoveDao = new MircoLoveDao();

		String parame = request.getParameter("parame1");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		JSONArray array = new JSONArray();

		if (parame.equals("changeData")) {
			try {
				String id = request.getParameter("id");
				MircoLove m = new MircoLove();
				m = mircoLoveDao.getMircoLove(id);
				JSONObject obj = new JSONObject();
				obj.put("mircolove_list_support_time",
						m.getMircolove_list_support_time());
				obj.put("mircolove_raise_amount", m.getMircolove_raise_amount());
				obj.put("mircolove_open_date", m.getMircolove_open_date());
				array.add(obj);
			} catch (Exception e) {
			}
		}

		out.write(array.toString());
		out.flush();
	}

}
