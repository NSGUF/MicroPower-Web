package com.react;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class Parameter {
	public static HashMap<String , String> getParameter(ServletContext context,
			HttpServletRequest request, String[] parameters) {
		String result = "";
		HashMap<String , String> map = new HashMap<String , String>();  
		// 1、创建一个DiskFileItemFactory工厂
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 2、创建一个文件上传解析器
		ServletFileUpload upload = new ServletFileUpload(factory);
		// 解决上传文件名的中文乱码
		upload.setHeaderEncoding("UTF-8");
		factory.setSizeThreshold(1024 * 500);// 设置内存的临界值为500K
		String pathName = context.getRealPath("/upload"); // ServletActionContext.getServletContext().getRealPath("/upload")
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
				// 若是一个一般的表单域, 打印信息
				if (item.isFormField()) {
					String name = item.getFieldName();
					for(int i=0;i<parameters.length;i++){
						if (name.equals(parameters[i])) {
							result = item.getString("utf-8");
							map.put(name,result);
						}
					}
					
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return map;
	}
	
	

}
