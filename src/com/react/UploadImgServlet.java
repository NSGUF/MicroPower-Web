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

	// 保存文件的目录
	private static String PATH_FOLDER = "/";
	// 存放临时文件的目录
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
		// 初始化路径
		// 保存文件的目录
		PATH_FOLDER = servletCtx.getRealPath("/upload");
		// 存放临时文件的目录,存放xxx.tmp文件的目录
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

		request.setCharacterEncoding("utf-8"); // 设置编码
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获得磁盘文件条目工厂
		DiskFileItemFactory factory = new DiskFileItemFactory();

		// 如果没以下两行设置的话，上传大的 文件 会占用 很多内存，
		// 设置暂时存放的 存储室 , 这个存储室，可以和 最终存储文件 的目录不同
		/**
		 * 原理 它是先存到 暂时存储室，然后在真正写到 对应目录的硬盘上， 按理来说 当上传一个文件时，其实是上传了两份，第一个是以 .tem
		 * 格式的 然后再将其真正写到 对应目录的硬盘上
		 */
		factory.setRepository(new File(TEMP_FOLDER));
		// 设置 缓存的大小，当上传文件的容量超过该缓存时，直接放到 暂时存储室
		factory.setSizeThreshold(1024 * 1024);

		// 高水平的API文件上传处理
		ServletFileUpload upload = new ServletFileUpload(factory);

		try {
			// 提交上来的信息都在这个list里面
			// 这意味着可以上传多个文件
			// 请自行组织代码
			List<FileItem> list = upload.parseRequest(request);
			// 获取上传的文件
			FileItem item = getUploadFileItem(list);
			// 获取文件名
			String filename = getUploadFileName(item);
			// 保存后的文件名
			String saveName = new Date().getTime()
					+ filename.substring(filename.lastIndexOf("."));
			// 保存后图片的浏览器访问路径
			String picUrl = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ request.getContextPath() + "/upload/" + saveName;
			String minPicUrl = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ request.getContextPath() + "/upload/min" + saveName;
			System.out.println("存放目录:" + PATH_FOLDER);
			System.out.println("文件名:" + filename);
			System.out.println("浏览器访问路径:" + picUrl);

			// 真正写到磁盘上
			item.write(new File(PATH_FOLDER, saveName)); // 第三方提供的
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
		// 获取路径名
		String value = item.getName();
		// System.out.println(value + ":value");
		// 索引到最后一个反斜杠
		int start = value.lastIndexOf("/");
		// 截取 上传文件的 字符串名字，加1是 去掉反斜杠，
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
