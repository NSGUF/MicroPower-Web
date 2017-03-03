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
import com.donation.DonationInfo;
import com.donation.DonationInfoDao;
import com.login.User;
import com.login.UserDao;
import com.mircolove.MircoLoveDao;

/**
 * Servlet implementation class DoMircoLoveListAction
 */
public class DoMircoLoveListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DoMircoLoveListAction() {
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
		String mircolove_id = request.getParameter("mircolove_id");
		// System.out.println(mircolove_id);
		if (parame.equals("commentMir")) {
			Connection conn = null;
			try {
				conn = DataBaseUtil.getConnection();
				ResultSet rs = null;
				Statement stm = conn.createStatement();
				String sql = "select * from T_DO_MIRCOLOVE_CHILDREN,T_MIRCOLOVE_CHILDREN_COMMENT,T_MIRCOLOVE_CHILDREN_COMMENT_CONTENT where T_DO_MIRCOLOVE_CHILDREN.mircolove_comment_id=T_MIRCOLOVE_CHILDREN_COMMENT.mircolove_comment_id and T_MIRCOLOVE_CHILDREN_COMMENT.mircolove_comment_id=T_MIRCOLOVE_CHILDREN_COMMENT_CONTENT.mircolove_comment_id and T_DO_MIRCOLOVE_CHILDREN.mircolove_id='"
						+ mircolove_id
						+ "' order by mircolove_comment_rank,mircolove_comment_content_rank asc"; // System.out.println(sqlContent);
				rs = stm.executeQuery(sql);
				// System.out.println(sql);
				array = new JSONArray();
				while (rs.next()) {
					JSONObject obj = new JSONObject();
					obj.put("do_mircolove_id", rs.getString("do_mircolove_id"));
					obj.put("do_mircolove_time", MircoLoveDao.getTime(rs
							.getString("do_mircolove_time")));
					obj.put("do_mircolove_amount",
							rs.getInt("do_mircolove_amount"));
					obj.put("user_id", rs.getString("user_id"));
					obj.put("user_name",
							userDao.getUser(rs.getString("user_id"))
									.getPet_name());
					obj.put("user_head",
							userDao.getUser(rs.getString("user_id"))
									.getHead_portrait());
					obj.put("mircolove_comment_id",
							rs.getString("mircolove_comment_id"));
					obj.put("mircolove_comment_rank",
							rs.getInt("mircolove_comment_rank"));
					obj.put("mircolove_id", rs.getString("mircolove_id"));
					obj.put("mircolove_comment_content_id",
							rs.getString("mircolove_comment_content_id"));
					obj.put("from_user_id", rs.getString("from_user_id"));
					obj.put("to_user_id", rs.getString("to_user_id"));
					obj.put("mircolove_comment_content",
							rs.getString("mircolove_comment_content"));
					obj.put("mircolove_comment_content_time",
							rs.getString("mircolove_comment_content_time"));
					obj.put("mircolove_comment_content_rank",
							rs.getInt("mircolove_comment_content_rank"));
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
