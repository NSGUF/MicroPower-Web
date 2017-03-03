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
import com.mircolove.DoMircoLove;
import com.mircolove.MircoLoveDao;
import com.share.SharerDao;

/**
 * Servlet implementation class DoCommentAction
 */
public class DoCommentAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DoCommentAction() {
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
		DonationInfoDao donationInfoDao = new DonationInfoDao();
		SharerDao sharerDao = new SharerDao();

		UserDao userDao = new UserDao();

		CommentContent commentContent = new CommentContent();
		CommentContentDao commentContentDao = new CommentContentDao();

		String parame = request.getParameter("parame1");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");

		String flag = "";

		if (parame.equals("doComment")) {
			try {
				String comment = request.getParameter("comment");
				String flagDOrW = request.getParameter("flag");

				String from_user_id = request.getParameter("from_user_id");
				String to_user_id = "";
				String comment_id = "";
				int comment_rank = 0;

				// String user_id = from_user_id;
				Date date = new Date();
				SimpleDateFormat formatter = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				String comment_content_time = formatter.format(date);
				int comment_content_rank = 0;// ,--对于楼主的评论整个消息的个数，根据这个可以排序

				commentContent.setFrom_user_head(userDao.getUser(from_user_id)
						.getHead_portrait());
				commentContent.setFrom_user_id(from_user_id);
				commentContent.setFrom_user_name(userDao.getUser(from_user_id)
						.getPet_name());
				commentContent.setMircolove_comment_content(comment);
				commentContent
						.setMircolove_comment_content_time(comment_content_time);
				commentContent
						.setMircolove_comment_content_rank(comment_content_rank);

				if (flagDOrW.equals("donation_id")) {

					String id = request.getParameter("donation_id");
					// System.out.println(id + "--");
					commentContent.setMircolove_id(id);
					comment_rank = commentContentDao.listDCCount(id);
					comment_id = "DON" + comment_rank;
					String comment_content_id = "CCI"
							+ commentContentDao.listDCCommentCount();
					to_user_id = donationInfoDao.getDonationInfo(id)
							.getUser_id();
					commentContent.setTo_user_id(to_user_id);
					commentContent.setMircolove_comment_id(comment_id);
					commentContent.setMircolove_comment_rank(comment_rank);

					commentContent
							.setMircolove_comment_content_id(comment_content_id);
					commentContentDao.saveDonationInfoComment(commentContent);
					commentContentDao.saveDonationInfoCntent(commentContent);
				} else if (flagDOrW.equals("witness_id")) {
					String id = request.getParameter("witness_id");
					System.out.println(id + "--");
					commentContent.setMircolove_id(id);
					comment_rank = commentContentDao.listWCCount(id);
					comment_id = "WIT" + comment_rank;
					String comment_content_id = "CCI"
							+ commentContentDao.listWCCommentCount();
					to_user_id = sharerDao.getSharer(id).getUser_id();
					commentContent.setMircolove_comment_rank(comment_rank);
					commentContent.setTo_user_id(to_user_id);
					commentContent.setMircolove_comment_id(comment_id);

					commentContent
							.setMircolove_comment_content_id(comment_content_id);
					commentContentDao.saveWitnessComment(commentContent);
					commentContentDao.saveWitnessCntent(commentContent);
				}
				flag = "success";
			} catch (Exception e) {
				flag = e.toString();
			}
		}
		out.write(flag);
		out.flush();
	}
}
