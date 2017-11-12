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
			Cookie[] myCookie = request.getCookies();// ����һ��Cookie��������
			if (myCookie != null)
				for (int i = 0; i < myCookie.length; i++) {// ����һ��ѭ����������Cookie���������ÿһ��Ԫ��
					Cookie newCookie = myCookie[i];
					if (newCookie.getName().equals("username")) {// �ж�Ԫ�ص�ֵ�Ƿ�Ϊusername�е�
						cell_phone_num = newCookie.getValue();
						user = userDao.login(cell_phone_num);
						break;
					}
				}
		}
		return user;
	}
}
