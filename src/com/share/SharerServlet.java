package com.share;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.login.User;
import com.login.UserDao;

public class SharerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SharerServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		SharerDao sharerDao = new SharerDao();
		Sharer sharer = new Sharer();

		String witness_id = "WITNESS" + sharerDao.listCount();
		String witness_title = "";
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String witness_open_date = formatter.format(date);

		String witness_describe = "";// wit_describe varchar(300) not null,
		String witness_image = "";// photo varchar(300) not null,
		String witness_min_image = "";// photo varchar(300) not null,
		// 得到user
		User user = null;
		UserDao userDao = new UserDao();

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
					if (name.equals("witness_title")) {
						witness_title = item.getString("utf-8");
					} else if (name.equals("witness_describe")) {
						witness_describe = item.getString("utf-8");
					}
					// String str = item.getString("utf-8");
					// System.out.println(name + ": " + str);
				} else {// 若是文件域则把文件保存到 e:\\files 目录下.
					fileName = item.getName();
					if (!fileName.equals("")) {
						long sizeInBytes = item.getSize();
						// System.out.println(fileName);
						// System.out.println(sizeInBytes);
						InputStream in = item.getInputStream();
						byte[] buffer = new byte[1024];
						int len = 0;
						String fileNameStr = pathName + "\\" + fileName;// 文件最终上传的位置
						String minFileName = pathName + "\\min" + fileName;
						System.out.println(fileNameStr);
						OutputStream out = new FileOutputStream(fileNameStr);
						while ((len = in.read(buffer)) != -1) {
							out.write(buffer, 0, len);
						}
						witness_image = witness_image
								+ "http://localhost:8080/MicroPower/upload/"
								+ fileName + " ";
						witness_min_image = witness_min_image
								+ "http://localhost:8080/MicroPower/upload/"
								+ fileName + " ";
						out.close();
						in.close();
						setMinImage(fileNameStr, minFileName);
					}
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		}

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
		sharer.setUser_id(user.getUser_id());
		sharer.setWitness_describe(witness_describe);
		sharer.setWitness_id(witness_id);
		sharer.setWitness_image(witness_image);
		sharer.setWitness_min_image(witness_min_image);
		sharer.setWitness_title(witness_title);
		sharer.setWitness_open_date(witness_open_date);
		sharerDao.saveSharer(sharer);

		request.getRequestDispatcher("doItemFinish.jsp").forward(request,
				response);
	}

	private void setMinImage(String fileNameStr, String minFileName)
			throws IOException {
		// 读入刚才上传的文件
		java.io.File file = new java.io.File(fileNameStr);
		// 构造Image对象
		Image src = javax.imageio.ImageIO.read(file);
		float tagsize = 200;
		int old_w = src.getWidth(null); // 得到源图宽
		int old_h = src.getHeight(null); // 得到源图长
		int new_w = 0;
		int new_h = 0;

		float tempdouble;
		if (old_w > old_h) {
			tempdouble = old_w / tagsize;
		} else {
			tempdouble = old_h / tagsize;
		}
		new_w = Math.round(old_w / tempdouble);
		new_h = Math.round(old_h / tempdouble);// 计算新图长宽
		BufferedImage tag = new BufferedImage(new_w, new_h,
				BufferedImage.TYPE_INT_RGB);
		tag.getGraphics().drawImage(src, 0, 0, new_w, new_h, null); // 绘制缩小后的图

		// FileOutputStream newimage=new FileOutputStream(minFileName); //输出到文件流
		// JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(newimage);
		// encoder.encode(tag); //近JPEG编码

		String formatName = minFileName
				.substring(minFileName.lastIndexOf(".") + 1);
		// FileOutputStream out = new FileOutputStream(dstName);
		// JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		// encoder.encode(dstImage);
		ImageIO.write(tag, /* "GIF" */formatName /* format desired */, new File(
				minFileName) /* target */);
	}
}
