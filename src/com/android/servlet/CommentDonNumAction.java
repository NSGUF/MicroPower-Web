package com.android.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.comment.CommentContent;
import com.comment.CommentContentDao;
import com.donation.DonationInfoDao;
import com.login.UserDao;
import com.share.SharerDao;

/**
 * Servlet implementation class CommentDonNumAction
 */
public class CommentDonNumAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CommentDonNumAction() {
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
		CommentContentDao commentContentDao = new CommentContentDao();

		String parame = request.getParameter("parame1");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		String flag = "";
		if (parame.equals("commentDonNum")) {
			try {
				String id = request.getParameter("donation_id");
				flag = (commentContentDao.listDCCount(id) - 1) + "";
			} catch (Exception e) {
				flag = e.toString();
			}
		}
		out.write(flag);
		out.flush();
	}

}
