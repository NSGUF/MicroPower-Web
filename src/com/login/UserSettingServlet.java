package com.login;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class UserSettingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserSettingServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		UserDao userDao = new UserDao();
		String head_portrait = "";
		String pet_name = "";// request.getParameter("pet_name");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String fileName = "";
		// 1������һ��DiskFileItemFactory����
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 2������һ���ļ��ϴ�������
		ServletFileUpload upload = new ServletFileUpload(factory);
		// ����ϴ��ļ�������������
		upload.setHeaderEncoding("UTF-8");
		factory.setSizeThreshold(1024 * 500);// �����ڴ���ٽ�ֵΪ500K
		String pathName = this.getServletConfig().getServletContext()
				.getRealPath("/upload"); // ServletActionContext.getServletContext().getRealPath("/upload")
		// ;//��ȡ������·��
		File linshi = new File(pathName);// ������500K��ʱ�򣬴浽һ����ʱ�ļ�����
		if (!linshi.exists()) {
			linshi.mkdir();
		}
		factory.setRepository(linshi);
		upload.setSizeMax(1024 * 1024 * 40);// �����ϴ����ļ��ܵĴ�С���ܳ���5M
		try {
			// 1. �õ� FileItem �ļ��� items
			List<FileItem> /* FileItem */items = upload.parseRequest(request);
			// 2. ���� items:
			for (FileItem item : items) {
				// System.out.println(j++);
				// ����һ��һ��ı���, ��ӡ��Ϣ
				if (item.isFormField()) {
					String name = item.getFieldName();
					pet_name = item.getString("utf-8");
					// System.out.println(name + ": " + pet_name);
				} else {// �����ļ�������ļ����浽 e:\\files Ŀ¼��.
					fileName = item.getName();
					if (!fileName.equals("")) {
						long sizeInBytes = item.getSize();
						System.out.println(fileName);
						System.out.println(sizeInBytes);

						InputStream in = item.getInputStream();
						byte[] buffer = new byte[1024];
						int len = 0;
						String fileNameStr = pathName + "\\" + fileName;// �ļ������ϴ���λ��
						System.out.println(fileNameStr);
						try {
							OutputStream out = new FileOutputStream(fileNameStr);
							while ((len = in.read(buffer)) != -1) {
								out.write(buffer, 0, len);
							}
							head_portrait = "http://localhost:8080/MicroPower/upload/"
									+ fileName;
							out.close();
							in.close();
						} catch (Exception e) {
							System.out.print(e.toString());
						}
					}
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		}

		User user = null;
		String cell_phone_id = "";
		Cookie[] myCookie = request.getCookies();// ����һ��Cookie��������
		if (myCookie != null)
			for (int i = 0; i < myCookie.length; i++) {// ����һ��ѭ����������Cookie���������ÿһ��Ԫ��
				Cookie newCookie = myCookie[i];
				if (newCookie.getName().equals("username")) {// �ж�Ԫ�ص�ֵ�Ƿ�Ϊusername�е�
					cell_phone_id = newCookie.getValue();
					user = userDao.login(cell_phone_id);
				}
			}
		user = userDao.modifyUser(user, pet_name, head_portrait);
		request.getSession().setAttribute("user", user);
		request.getRequestDispatcher("setting.jsp").forward(request, response);
	}
}
