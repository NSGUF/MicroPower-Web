package com.android.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.login.User;
import com.login.UserDao;
import com.mircolove.MircoLove;
import com.mircolove.MircoLoveDao;

/**
 * Servlet implementation class MircoLoveListAction
 */
public class MircoLoveListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String parame = request.getParameter("parame1");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		UserDao userDao = new UserDao();
		JSONArray array = null;
		User user = null;
		List<MircoLove> listMircoLove = null;
		if (parame.equals("selectmircolove")) {
			listMircoLove = MircoLoveDao.getSelectMircoLoveList();
		} else if (parame.equals("mircolove")) {
			listMircoLove = MircoLoveDao.getMircoLoveList();
		}
		if (listMircoLove != null) {
			array = new JSONArray();
			for (int i = 0; i < listMircoLove.size(); i++) {
				MircoLove mircolove = listMircoLove.get(i);
				JSONObject obj = new JSONObject();
				try {
					obj.put("mircolove_id", mircolove.getMircolove_id());
					obj.put("mircolove_target_amount",
							mircolove.getMircolove_target_amount());
					obj.put("mircolove_raise_amount",
							mircolove.getMircolove_raise_amount());
					obj.put("mircolove_open_date", MircoLoveDao
							.getTime(mircolove.getMircolove_open_date()));
					obj.put("mircolove_divid_num",
							mircolove.getMircolove_divid_num());
					obj.put("mircolove_list_title",
							mircolove.getMircolove_list_title());
					obj.put("mircolove_list_describe",
							mircolove.getMircolove_list_describe());
					obj.put("mircolove_list_image",
							mircolove.getMircolove_list_image());
					obj.put("mircolove_list_min_image",
							mircolove.getMircolove_list_min_image());
					obj.put("mircolove_list_select",
							mircolove.getMircolove_list_select());
					obj.put("mircolove_list_addr",
							mircolove.getMircolove_list_addr() + "1");
					obj.put("mircolove_list_support_time",
							mircolove.getMircolove_list_support_time());
					obj.put("mircolove_verify_state",
							mircolove.getMircolove_verify_state());
					obj.put("is_delete", mircolove.getIs_delete());
					obj.put("user_id", mircolove.getUser_id());

					user = userDao.getUser(mircolove.getUser_id());
					obj.put("user_name", user.getPet_name());
					obj.put("user_head", user.getHead_portrait());
				} catch (Exception e) {
				}
				array.add(obj);
			}
		}
		out.write(array.toString());
		out.flush();
	}
}
