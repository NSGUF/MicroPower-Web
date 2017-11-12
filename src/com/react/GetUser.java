package com.react;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.login.User;
import com.login.UserDao;

public class GetUser {
	public static User getUser(HttpServletRequest request){
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			UserDao userDao = new UserDao();
			String cell_phone_num = "";
			Cookie[] myCookie = request.getCookies();// 创建一个Cookie对象数组
			if (myCookie != null)
				for (int i = 0; i < myCookie.length; i++) {// 设立一个循环，来访问Cookie对象数组的每一个元素
					Cookie newCookie = myCookie[i];
					if (newCookie.getName().equals("username")) {// 判断元素的值是否为username中的
						cell_phone_num = newCookie.getValue();
						user = userDao.login(cell_phone_num);
						break;
					}
				}
		}
		return user;
	}
}
