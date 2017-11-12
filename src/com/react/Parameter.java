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
		// 1������һ��DiskFileItemFactory����
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 2������һ���ļ��ϴ�������
		ServletFileUpload upload = new ServletFileUpload(factory);
		// ����ϴ��ļ�������������
		upload.setHeaderEncoding("UTF-8");
		factory.setSizeThreshold(1024 * 500);// �����ڴ���ٽ�ֵΪ500K
		String pathName = context.getRealPath("/upload"); // ServletActionContext.getServletContext().getRealPath("/upload")
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
				// ����һ��һ��ı���, ��ӡ��Ϣ
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
