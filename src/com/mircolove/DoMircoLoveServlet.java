package com.mircolove;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.comment.CommentContent;
import com.comment.CommentContentDao;
import com.login.User;
import com.login.UserDao;

/**
 * Servlet implementation class DoMircoLoveServlet
 */
public class DoMircoLoveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DoMircoLoveServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		DoMircoLoveDao doMircoLoveDao = new DoMircoLoveDao();
		DoMircoLove doMircoLove = new DoMircoLove();

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		CommentContent commentContent = new CommentContent();
		CommentContentDao commentContentDao = new CommentContentDao();
		String do_mircolove_amount = request
				.getParameter("do_mircolove_amount");
		String mircolove_comment_content = request
				.getParameter("mircolove_comment_content");
		if (mircolove_comment_content.equals("")) {
			mircolove_comment_content = "加油!";
		}

		String mircolove_id = request.getParameter("mircolove_id");

		String from_user_id = request.getParameter("from_user_id");
		String to_user_id = request.getParameter("to_user_id");
		String user_id = from_user_id;

		String mircolove_comment_id = "MCOMMENTID"
				+ commentContentDao.listCommentCount();
		int mircolove_comment_rank = (int) commentContentDao.listCommentCount();
		String mircolove_comment_content_id = "CONTENTID"
				+ commentContentDao.listContentCount();
		String do_mircolove_id = "DOMID" + doMircoLoveDao.listCount();

		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String mircolove_comment_content_time = formatter.format(date);

		int mircolove_comment_content_rank = (int) commentContentDao
				.listContentCount(mircolove_comment_id);// ,--对于楼主的评论整个消息的个数，根据这个可以排序

		String do_mircolove_time = mircolove_comment_content_time;

		// 得到user
		User user = null;
		UserDao userDao = new UserDao();

		String cell_phone_id = "";
		Cookie[] myCookie = request.getCookies();// 创建一个Cookie对象数组
		if (myCookie != null)
			for (int i = 0; i < myCookie.length; i++) {// 设立一个循环，来访问Cookie对象数组的每一个元素
				Cookie newCookie = myCookie[i];
				if (newCookie.getName().equals("username")) {// 判断元素的值是否为username中的
					cell_phone_id = newCookie.getValue();
					user = userDao.login(cell_phone_id);
				}
			}
		doMircoLove.setDo_mircolove_id(do_mircolove_id);
		doMircoLove.setDo_mircolove_amount(Integer
				.parseInt(do_mircolove_amount));
		doMircoLove.setDo_mircolove_time(do_mircolove_time);
		doMircoLove.setUser_id(user_id);
		doMircoLove.setMircolove_id(mircolove_id);
		doMircoLove.setMircolove_comment_id(mircolove_comment_id);

		commentContent.setMircolove_comment_id(mircolove_comment_id);
		commentContent.setMircolove_id(mircolove_id);
		commentContent.setMircolove_comment_rank(mircolove_comment_rank);
		commentContent
				.setMircolove_comment_content_id(mircolove_comment_content_id);
		commentContent.setFrom_user_id(from_user_id);
		commentContent.setTo_user_id(to_user_id);
		commentContent.setMircolove_comment_content(mircolove_comment_content);
		commentContent
				.setMircolove_comment_content_rank(mircolove_comment_content_rank);
		commentContent
				.setMircolove_comment_content_time(mircolove_comment_content_time);

		commentContentDao.saveComment(commentContent);
		commentContentDao.saveCntent(commentContent);

		doMircoLoveDao.setMircoloveRaiseAmount(do_mircolove_amount,
				mircolove_id);
		doMircoLoveDao.setMircoloveSupportNum(mircolove_id);
		doMircoLoveDao.saveDoMircoLove(doMircoLove);
		request.getRequestDispatcher("doItemFinish.jsp").forward(request,
				response);
	}

}
