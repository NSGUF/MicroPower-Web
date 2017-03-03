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
		// �õ�user
		User user = null;
		UserDao userDao = new UserDao();

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
					if (name.equals("witness_title")) {
						witness_title = item.getString("utf-8");
					} else if (name.equals("witness_describe")) {
						witness_describe = item.getString("utf-8");
					}
					// String str = item.getString("utf-8");
					// System.out.println(name + ": " + str);
				} else {// �����ļ�������ļ����浽 e:\\files Ŀ¼��.
					fileName = item.getName();
					if (!fileName.equals("")) {
						long sizeInBytes = item.getSize();
						// System.out.println(fileName);
						// System.out.println(sizeInBytes);
						InputStream in = item.getInputStream();
						byte[] buffer = new byte[1024];
						int len = 0;
						String fileNameStr = pathName + "\\" + fileName;// �ļ������ϴ���λ��
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
		Cookie[] myCookie = request.getCookies();// ����һ��Cookie��������
		if (myCookie != null)
			for (int i = 0; i < myCookie.length; i++) {// ����һ��ѭ����������Cookie���������ÿһ��Ԫ��
				Cookie newCookie = myCookie[i];
				if (newCookie.getName().equals("username")) {// �ж�Ԫ�ص�ֵ�Ƿ�Ϊusername�е�
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
		// ����ղ��ϴ����ļ�
		java.io.File file = new java.io.File(fileNameStr);
		// ����Image����
		Image src = javax.imageio.ImageIO.read(file);
		float tagsize = 200;
		int old_w = src.getWidth(null); // �õ�Դͼ��
		int old_h = src.getHeight(null); // �õ�Դͼ��
		int new_w = 0;
		int new_h = 0;

		float tempdouble;
		if (old_w > old_h) {
			tempdouble = old_w / tagsize;
		} else {
			tempdouble = old_h / tagsize;
		}
		new_w = Math.round(old_w / tempdouble);
		new_h = Math.round(old_h / tempdouble);// ������ͼ����
		BufferedImage tag = new BufferedImage(new_w, new_h,
				BufferedImage.TYPE_INT_RGB);
		tag.getGraphics().drawImage(src, 0, 0, new_w, new_h, null); // ������С���ͼ

		// FileOutputStream newimage=new FileOutputStream(minFileName); //������ļ���
		// JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(newimage);
		// encoder.encode(tag); //��JPEG����

		String formatName = minFileName
				.substring(minFileName.lastIndexOf(".") + 1);
		// FileOutputStream out = new FileOutputStream(dstName);
		// JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		// encoder.encode(dstImage);
		ImageIO.write(tag, /* "GIF" */formatName /* format desired */, new File(
				minFileName) /* target */);
	}
}
