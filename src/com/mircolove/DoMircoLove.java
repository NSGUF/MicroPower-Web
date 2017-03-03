package com.mircolove;

public class DoMircoLove {
	private String do_mircolove_id;// varchar(20) primary key,
	private String do_mircolove_time;// date not null,--帮助时间
	private double do_mircolove_amount;// int,--帮助总金额
	private String user_id;// varchar(20),
	private String mircolove_id;// varchar(20),
	private String mircolove_comment_id;// varchar(20),

	public DoMircoLove() {
		super();
	}

	public DoMircoLove(String do_mircolove_id, String do_mircolove_time,
			int do_mircolove_amount, String user_id, String mircolove_id,
			String mircolove_comment_id) {
		super();
		this.do_mircolove_id = do_mircolove_id;
		this.do_mircolove_time = do_mircolove_time;
		this.do_mircolove_amount = do_mircolove_amount;
		this.user_id = user_id;
		this.mircolove_id = mircolove_id;
		this.mircolove_comment_id = mircolove_comment_id;
	}

	public String getDo_mircolove_id() {
		return do_mircolove_id;
	}

	public void setDo_mircolove_id(String do_mircolove_id) {
		this.do_mircolove_id = do_mircolove_id;
	}

	public String getDo_mircolove_time() {
		return do_mircolove_time;
	}

	public void setDo_mircolove_time(String do_mircolove_time) {
		this.do_mircolove_time = do_mircolove_time;
	}

	public double getDo_mircolove_amount() {
		return do_mircolove_amount;
	}

	public void setDo_mircolove_amount(double do_mircolove_amount) {
		this.do_mircolove_amount = do_mircolove_amount;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getMircolove_id() {
		return mircolove_id;
	}

	public void setMircolove_id(String mircolove_id) {
		this.mircolove_id = mircolove_id;
	}

	public String getMircolove_comment_id() {
		return mircolove_comment_id;
	}

	public void setMircolove_comment_id(String mircolove_comment_id) {
		this.mircolove_comment_id = mircolove_comment_id;
	}

	@Override
	public String toString() {
		return "DoMircoLove [do_mircolove_id=" + do_mircolove_id
				+ ", do_mircolove_time=" + do_mircolove_time
				+ ", do_mircolove_amount=" + do_mircolove_amount + ", user_id="
				+ user_id + ", mircolove_id=" + mircolove_id
				+ ", mircolove_comment_id=" + mircolove_comment_id + "]";
	}

}
