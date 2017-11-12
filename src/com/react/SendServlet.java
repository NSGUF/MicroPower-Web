package com.react;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class SendServlet
 */
public class SendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SendServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin",
				"http://localhost:3000");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		
		String[] paramters={"validate","tellphone"};
		
		HashMap<String , String> map = Parameter.getParameter(getServletContext(), request,paramters);
		
		String tellphone=map.get(paramters[1]);
		String validate=map.get(paramters[0]);
		String PostData = "account=NSGUF1015&password=123546789&mobile="
				+ tellphone
				+ "&content="
				+ java.net.URLEncoder.encode("您的订132单编码：" + validate + "请联系客服。",
						"gb2312");
		/*String PostData = "account=NSGUF1015&password=123546789&mobile="
				+ tellphone
				+ "&content="
				+ java.net.URLEncoder.encode("您的订单编码：" + validate + "。如需帮助请联系客服。",
						"gb2312");*/
		/*System.out.println(tellphone);
		System.out.println(validate);
		System.out.println(PostData);*/
		String ret = Send
				.SMS(PostData, "http://sms.106jiekou.com/gbk/sms.aspx");
		
		//System.out.println(ret);
		JSONObject result = new JSONObject();
		result.put("flag", ret);
		response.getWriter().write(result.toString());
		response.getWriter().close();
	}
}
