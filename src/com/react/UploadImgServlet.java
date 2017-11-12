package com.react;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class UploadImgServlet
 */
public class UploadImgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// �����ļ���Ŀ¼
	private static String PATH_FOLDER = "/";
	// �����ʱ�ļ���Ŀ¼
	private static String TEMP_FOLDER = "/";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadImgServlet() {
		super();
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init();

		ServletContext servletCtx = config.getServletContext();
		// ��ʼ��·��
		// �����ļ���Ŀ¼
		PATH_FOLDER = servletCtx.getRealPath("/upload");
		// �����ʱ�ļ���Ŀ¼,���xxx.tmp�ļ���Ŀ¼
		TEMP_FOLDER = servletCtx.getRealPath("/uploadTemp");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin",
				"http://localhost:3000");
		response.setHeader("Access-Control-Allow-Credentials", "true");

		request.setCharacterEncoding("utf-8"); // ���ñ���
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		// ��ô����ļ���Ŀ����
		DiskFileItemFactory factory = new DiskFileItemFactory();

		// ���û�����������õĻ����ϴ���� �ļ� ��ռ�� �ܶ��ڴ棬
		// ������ʱ��ŵ� �洢�� , ����洢�ң����Ժ� ���մ洢�ļ� ��Ŀ¼��ͬ
		/**
		 * ԭ�� �����ȴ浽 ��ʱ�洢�ң�Ȼ��������д�� ��ӦĿ¼��Ӳ���ϣ� ������˵ ���ϴ�һ���ļ�ʱ����ʵ���ϴ������ݣ���һ������ .tem
		 * ��ʽ�� Ȼ���ٽ�������д�� ��ӦĿ¼��Ӳ����
		 */
		factory.setRepository(new File(TEMP_FOLDER));
		// ���� ����Ĵ�С�����ϴ��ļ������������û���ʱ��ֱ�ӷŵ� ��ʱ�洢��
		factory.setSizeThreshold(1024 * 1024);

		// ��ˮƽ��API�ļ��ϴ�����
		ServletFileUpload upload = new ServletFileUpload(factory);

		try {
			// �ύ��������Ϣ�������list����
			// ����ζ�ſ����ϴ�����ļ�
			// ��������֯����
			List<FileItem> list = upload.parseRequest(request);
			// ��ȡ�ϴ����ļ�
			FileItem item = getUploadFileItem(list);
			// ��ȡ�ļ���
			String filename = getUploadFileName(item);
			// �������ļ���
			String saveName = new Date().getTime()
					+ filename.substring(filename.lastIndexOf("."));
			// �����ͼƬ�����������·��
			String picUrl = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ request.getContextPath() + "/upload/" + saveName;
			String minPicUrl = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ request.getContextPath() + "/upload/min" + saveName;
			System.out.println("���Ŀ¼:" + PATH_FOLDER);
			System.out.println("�ļ���:" + filename);
			System.out.println("���������·��:" + picUrl);

			// ����д��������
			item.write(new File(PATH_FOLDER, saveName)); // �������ṩ��
			PrintWriter writer = response.getWriter();

			/*
			 * System.out.print("{"); System.out.print("uploadedFile:" + "\"" +
			 * filename + "\""); System.out.print(",uploadedFileGetUrl:\"" +
			 * picUrl + "\""); System.out.print("}");
			 */

			JSONObject result = new JSONObject();
			result.put("name", filename);
			result.put("link", picUrl);
			result.put("minLink", minPicUrl);
			writer.write(result.toString());
			writer.close();

			setMinImage(PATH_FOLDER + "\\" + saveName, PATH_FOLDER + "\\"
					+ "min" + saveName);
		} catch (Exception e) {
			e.printStackTrace();
			/*
			 * PrintWriter writer = response.getWriter(); writer.print("{");
			 * writer.print("error:"+e.toString()); writer.print("}");
			 * writer.close();
			 */
		}
	}

	private FileItem getUploadFileItem(List<FileItem> list) {
		for (FileItem fileItem : list) {
			if (!fileItem.isFormField()) {
				return fileItem;
			}
		}
		return null;
	}

	private String getUploadFileName(FileItem item) {
		// ��ȡ·����
		String value = item.getName();
		// System.out.println(value + ":value");
		// ���������һ����б��
		int start = value.lastIndexOf("/");
		// ��ȡ �ϴ��ļ��� �ַ������֣���1�� ȥ����б�ܣ�
		String filename = value.substring(start + 1);
		return filename;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	protected void setMinImage(String fileNameStr, String minFileName)
			throws IOException {
		// System.out.println("min:" + fileNameStr + "/n" + minFileName);
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
