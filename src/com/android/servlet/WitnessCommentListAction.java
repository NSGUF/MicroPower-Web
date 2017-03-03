package com.android.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.DataBase.DataBaseUtil;
import com.login.User;
import com.login.UserDao;
import com.mircolove.MircoLove;
import com.mircolove.MircoLoveDao;

/**
 * Servlet implementation class WitnessCommentListAction
 */
public class WitnessCommentListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WitnessCommentListAction() {
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
		String parame = request.getParameter("parame1");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		UserDao userDao = new UserDao();
		JSONArray array = null;
		String witness_id = request.getParameter("witness_id");
		// System.out.println(mircolove_id);
		if (parame.equals("commentWit")) {
			Connection conn = null;
			try {
				conn = DataBaseUtil.getConnection();
				ResultSet rs = null;
				Statement stm = conn.createStatement();
				String sql = "select * from T_WITNESSINFO,T_WITNESSINFO_COMMENT,T_WITNESSINFO_COMMENT_CONTENT where T_WITNESSINFO.witness_id=T_WITNESSINFO_COMMENT.witness_id and T_WITNESSINFO_COMMENT.witnessinfo_comment_id=T_WITNESSINFO_COMMENT_CONTENT.witnessinfo_comment_id and T_WITNESSINFO.witness_id='"
						+ witness_id
						+ "' order by witnessinfo_comment_rank,witnessinfo_comment_content_rank asc"; // System.out.println(sqlContent);
				rs = stm.executeQuery(sql);
				// System.out.println(sql);
				array = new JSONArray();
				while (rs.next()) {
					JSONObject obj = new JSONObject();
					obj.put("witnessinfo_comment_id",
							rs.getString("witnessinfo_comment_id"));
					obj.put("witnessinfo_comment_rank",
							rs.getInt("witnessinfo_comment_rank"));
					obj.put("witness_id", rs.getString("witness_id"));
					obj.put("witnessinfo_comment_content_id",
							rs.getString("witnessinfo_comment_content_id"));
					obj.put("from_user_id", rs.getString("from_user_id"));
					obj.put("user_name",
							userDao.getUser(rs.getString("from_user_id"))
									.getPet_name());
					obj.put("user_head",
							userDao.getUser(rs.getString("from_user_id"))
									.getHead_portrait());
					obj.put("to_user_id", rs.getString("to_user_id"));
					obj.put("witnessinfo_comment_content",
							rs.getString("witnessinfo_comment_content"));
					obj.put("witnessinfo_comment_content_time",
							rs.getString("witnessinfo_comment_time"));
					obj.put("witnessinfo_comment_content_rank",
							rs.getInt("witnessinfo_comment_content_rank"));
					array.add(obj);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DataBaseUtil.closeConnection(conn);
			}
		}
		if (array.size() == 0) {
			JSONObject obj = new JSONObject();
			obj.put("flag", "no info");
		}
		out.write(array.toString());
		out.flush();
	}

}
