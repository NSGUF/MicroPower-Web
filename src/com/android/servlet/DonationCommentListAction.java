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
import com.login.UserDao;
import com.mircolove.MircoLoveDao;

/**
 * Servlet implementation class DonationCommentListAction
 */
public class DonationCommentListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DonationCommentListAction() {
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
		String donation_id = request.getParameter("donation_id");
		// System.out.println(mircolove_id);
		if (parame.equals("commentDon")) {
			Connection conn = null;
			try {
				conn = DataBaseUtil.getConnection();
				ResultSet rs = null;
				Statement stm = conn.createStatement();
				String sql = "select * from T_DONATIONINFO,T_DONATIONINFO_COMMENT,T_DONATIONINFO_COMMENT_CONTENT where T_DONATIONINFO.donation_id=T_DONATIONINFO_COMMENT.donation_id and T_DONATIONINFO_COMMENT.donationinfo_comment_id=T_DONATIONINFO_COMMENT_CONTENT.donationinfo_comment_id and T_DONATIONINFO.donation_id='"
						+ donation_id
						+ "' order by donationinfo_comment_rank,donationinfo_comment_content_rank asc"; // System.out.println(sqlContent);
				rs = stm.executeQuery(sql);
				// System.out.println(sql);
				array = new JSONArray();
				while (rs.next()) {
					JSONObject obj = new JSONObject();
					obj.put("donationinfo_comment_id",
							rs.getString("donationinfo_comment_id"));
					obj.put("donationinfo_comment_rank",
							rs.getInt("donationinfo_comment_rank"));
					obj.put("donation_id", rs.getString("donation_id"));
					obj.put("donationinfo_comment_content_id",
							rs.getString("donationinfo_comment_content_id"));
					obj.put("from_user_id", rs.getString("from_user_id"));
					obj.put("user_name",
							userDao.getUser(rs.getString("from_user_id"))
									.getPet_name());
					obj.put("user_head",
							userDao.getUser(rs.getString("from_user_id"))
									.getHead_portrait());
					obj.put("to_user_id", rs.getString("to_user_id"));
					obj.put("donationinfo_comment_content",
							rs.getString("donationinfo_comment_content"));
					obj.put("donationinfo_comment_content_time",
							rs.getString("donationinfo_comment_content_time"));
					obj.put("donationinfo_comment_content_rank",
							rs.getInt("donationinfo_comment_content_rank"));
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
