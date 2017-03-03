package com.android.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.DataBase.DataBaseUtil;

/**
 * Servlet implementation class ViewPagerAction
 */
public class ViewPagerAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ViewPagerAction() {
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
		String parame = request.getParameter("parame1");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		JSONArray array = null;
		if (parame.equals("viewpager_images")) {
			array = new JSONArray();
			Connection conn = DataBaseUtil.getConnection();
			String sql = "select * from T_VIEWPAGERIMAGE";
			try {
				Statement stm = conn.createStatement();
				ResultSet rs = stm.executeQuery(sql);
				while (rs.next()) {
					String image = rs.getString("viewpager_images");
					// String[] imageArr = image.split(" ");
					// for (int i = 0; i < imageArr.length; i++) {
					JSONObject obj = new JSONObject();
					// obj.put("viewpager_images", imageArr[i]);
					// array.add(obj);
					// }
					obj.put("viewpager_images", image);
					array.add(obj);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DataBaseUtil.closeConnection(conn);
			}
		}
		out.write(array.toString());
		out.flush();
	}

}
