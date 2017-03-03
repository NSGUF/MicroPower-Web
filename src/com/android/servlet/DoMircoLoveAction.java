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
import com.mircolove.DoMircoLove;
import com.mircolove.DoMircoLoveDao;
import com.mircolove.MircoLoveDao;

public class DoMircoLoveAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DoMircoLoveAction() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		DoMircoLoveDao doMircoLoveDao = new DoMircoLoveDao();
		DoMircoLove doMircoLove = new DoMircoLove();
		CommentContent commentContent = new CommentContent();
		CommentContentDao commentContentDao = new CommentContentDao();
		MircoLoveDao mircoLoveDao = new MircoLoveDao();

		String parame = request.getParameter("parame1");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");

		String flag = "";

		if (parame.equals("doHelp")) {
			try {
				String do_mircolove_amount = request
						.getParameter("do_mircolove_amount");
				String mircolove_comment_content = request
						.getParameter("mircolove_comment_content");
				if (mircolove_comment_content.equals("")) {
					mircolove_comment_content = "加油!";
				}

				String mircolove_id = request.getParameter("mircolove_id");
				String from_user_id = request.getParameter("from_user_id");
				String to_user_id = mircoLoveDao.getMircoLove(mircolove_id)
						.getUser_id();

				String mircolove_comment_id = "MCOMMENTID"
						+ commentContentDao.listCommentCount();
				int mircolove_comment_rank = (int) commentContentDao
						.listCommentCount();
				String mircolove_comment_content_id = "CONTENTID"
						+ commentContentDao.listContentCount();

				String do_mircolove_id = "DOMID12" + doMircoLoveDao.listCount();
				String user_id = from_user_id;
				Date date = new Date();
				SimpleDateFormat formatter = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				String mircolove_comment_content_time = formatter.format(date);
				int mircolove_comment_content_rank = (int) commentContentDao
						.listContentCount(mircolove_comment_id);// ,--对于楼主的评论整个消息的个数，根据这个可以排序
				String do_mircolove_time = mircolove_comment_content_time;
				doMircoLove.setDo_mircolove_id(do_mircolove_id);
				doMircoLove.setDo_mircolove_amount(Double
						.parseDouble(do_mircolove_amount));
				doMircoLove.setDo_mircolove_time(do_mircolove_time);
				doMircoLove.setUser_id(user_id);
				doMircoLove.setMircolove_id(mircolove_id);
				doMircoLove.setMircolove_comment_id(mircolove_comment_id);

				commentContent.setMircolove_comment_id(mircolove_comment_id);
				commentContent.setMircolove_id(mircolove_id);
				commentContent
						.setMircolove_comment_rank(mircolove_comment_rank);
				commentContent
						.setMircolove_comment_content_id(mircolove_comment_content_id);
				commentContent.setFrom_user_id(from_user_id);
				commentContent.setTo_user_id(to_user_id);
				commentContent
						.setMircolove_comment_content(mircolove_comment_content);
				commentContent
						.setMircolove_comment_content_rank(mircolove_comment_content_rank);
				commentContent
						.setMircolove_comment_content_time(mircolove_comment_content_time);

				// System.out.println(commentContent.toString());
				// System.out.println(doMircoLove.toString());
				commentContentDao.saveComment(commentContent);
				commentContentDao.saveCntent(commentContent);

				doMircoLoveDao.setMircoloveRaiseAmount(do_mircolove_amount,
						mircolove_id);
				doMircoLoveDao.setMircoloveSupportNum(mircolove_id);
				doMircoLoveDao.saveDoMircoLove(doMircoLove);
				flag = "success";
			} catch (Exception e) {
				flag = e.toString();
			}
		}

		out.write(flag);
		out.flush();
	}

}
