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
		// 1、创建一个DiskFileItemFactory工厂
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 2、创建一个文件上传解析器
		ServletFileUpload upload = new ServletFileUpload(factory);
		// 解决上传文件名的中文乱码
		upload.setHeaderEncoding("UTF-8");
		factory.setSizeThreshold(1024 * 500);// 设置内存的临界值为500K
		String pathName = this.getServletConfig().getServletContext()
				.getRealPath("/upload"); // ServletActionContext.getServletContext().getRealPath("/upload")
		// ;//获取服务器路径
		File linshi = new File(pathName);// 当超过500K的时候，存到一个临时文件夹中
		if (!linshi.exists()) {
			linshi.mkdir();
		}
		factory.setRepository(linshi);
		upload.setSizeMax(1024 * 1024 * 40);// 设置上传的文件总的大小不能超过5M
		try {
			// 1. 得到 FileItem 的集合 items
			List<FileItem> /* FileItem */items = upload.parseRequest(request);
			// 2. 遍历 items:
			for (FileItem item : items) {
				// System.out.println(j++);
				// 若是一个一般的表单域, 打印信息
				if (item.isFormField()) {
					String name = item.getFieldName();
					pet_name = item.getString("utf-8");
					// System.out.println(name + ": " + pet_name);
				} else {// 若是文件域则把文件保存到 e:\\files 目录下.
					fileName = item.getName();
					if (!fileName.equals("")) {
						long sizeInBytes = item.getSize();
						System.out.println(fileName);
						System.out.println(sizeInBytes);

						InputStream in = item.getInputStream();
						byte[] buffer = new byte[1024];
						int len = 0;
						String fileNameStr = pathName + "\\" + fileName;// 文件最终上传的位置
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
		Cookie[] myCookie = request.getCookies();// 创建一个Cookie对象数组
		if (myCookie != null)
			for (int i = 0; i < myCookie.length; i++) {// 设立一个循环，来访问Cookie对象数组的每一个元素
				Cookie newCookie = myCookie[i];
				if (newCookie.getName().equals("username")) {// 判断元素的值是否为username中的
					cell_phone_id = newCookie.getValue();
					user = userDao.login(cell_phone_id);
				}
			}
		user = userDao.modifyUser(user, pet_name, head_portrait);
		request.getSession().setAttribute("user", user);
		request.getRequestDispatcher("setting.jsp").forward(request, response);
	}
}
