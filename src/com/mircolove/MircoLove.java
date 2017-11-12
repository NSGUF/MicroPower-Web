package com.mircolove;

public class MircoLove {

	private String mircolove_id;
	private double mircolove_target_amount;
	private double mircolove_raise_amount = 0;
	private String mircolove_open_date;
	private int mircolove_divid_num;
	private String mircolove_list_title;
	private String mircolove_list_describe;
	private String mircolove_list_image;
	private String mircolove_list_min_image;
	private int mircolove_list_select = 0;
	private String mircolove_list_addr;
	private int mircolove_list_support_time = 0;
	private int mircolove_verify_state = 1;
	private int is_delete = 0;
	private String user_id;

	public String getMircolove_id() {
		return mircolove_id;
	}

	public void setMircolove_id(String mircolove_id) {
		this.mircolove_id = mircolove_id;
	}

	public double getMircolove_target_amount() {
		return mircolove_target_amount;
	}

	public void setMircolove_target_amount(double mircolove_target_amount) {
		this.mircolove_target_amount = mircolove_target_amount;
	}

	public double getMircolove_raise_amount() {
		return mircolove_raise_amount;
	}

	public void setMircolove_raise_amount(double mircolove_raise_amount) {
		this.mircolove_raise_amount = mircolove_raise_amount;
	}

	public String getMircolove_open_date() {
		return mircolove_open_date;
	}

	public void setMircolove_open_date(String mircolove_open_date) {
		this.mircolove_open_date = mircolove_open_date;
	}

	public int getMircolove_divid_num() {
		return mircolove_divid_num;
	}

	public void setMircolove_divid_num(int mircolove_divid_num) {
		this.mircolove_divid_num = mircolove_divid_num;
	}

	public String getMircolove_list_title() {
		return mircolove_list_title;
	}

	public void setMircolove_list_title(String mircolove_list_title) {
		this.mircolove_list_title = mircolove_list_title;
	}

	public String getMircolove_list_describe() {
		return mircolove_list_describe;
	}

	public void setMircolove_list_describe(String mircolove_list_describe) {
		this.mircolove_list_describe = mircolove_list_describe;
	}

	public String getMircolove_list_image() {
		return mircolove_list_image;
	}

	public void setMircolove_list_image(String mircolove_list_image) {
		this.mircolove_list_image = mircolove_list_image;
	}

	public String getMircolove_list_min_image() {
		return mircolove_list_min_image;
	}

	public void setMircolove_list_min_image(String mircolove_list_min_image) {
		this.mircolove_list_min_image = mircolove_list_min_image;
	}

	public int getMircolove_list_select() {
		return mircolove_list_select;
	}

	public void setMircolove_list_select(int mircolove_list_select) {
		this.mircolove_list_select = mircolove_list_select;
	}

	public String getMircolove_list_addr() {
		return mircolove_list_addr;
	}

	public void setMircolove_list_addr(String mircolove_list_addr) {
		this.mircolove_list_addr = mircolove_list_addr;
	}

	public int getMircolove_list_support_time() {
		return mircolove_list_support_time;
	}

	public void setMircolove_list_support_time(int mircolove_list_support_time) {
		this.mircolove_list_support_time = mircolove_list_support_time;
	}

	public int getMircolove_verify_state() {
		return mircolove_verify_state;
	}

	public void setMircolove_verify_state(int mircolove_verify_state) {
		this.mircolove_verify_state = mircolove_verify_state;
	}

	public int getIs_delete() {
		return is_delete;
	}

	public void setIs_delete(int is_delete) {
		this.is_delete = is_delete;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	@Override
	public String toString() {
		return "MircoLove [mircolove_id=" + mircolove_id
				+ ", mircolove_target_amount=" + mircolove_target_amount
				+ ", mircolove_raise_amount=" + mircolove_raise_amount
				+ ", mircolove_open_date=" + mircolove_open_date
				+ ", mircolove_divid_num=" + mircolove_divid_num
				+ ", mircolove_list_title=" + mircolove_list_title
				+ ", mircolove_list_describe=" + mircolove_list_describe
				+ ", mircolove_list_image=" + mircolove_list_image
				+ ", mircolove_list_min_image=" + mircolove_list_min_image
				+ ", mircolove_list_select=" + mircolove_list_select
				+ ", mircolove_list_addr=" + mircolove_list_addr
				+ ", mircolove_list_support_time="
				+ mircolove_list_support_time + ", mircolove_verify_state="
				+ mircolove_verify_state + ", is_delete=" + is_delete
				+ ", user_id=" + user_id + ", getMircolove_id()="
				+ getMircolove_id() + ", getMircolove_target_amount()="
				+ getMircolove_target_amount()
				+ ", getMircolove_raise_amount()="
				+ getMircolove_raise_amount() + ", getMircolove_open_date()="
				+ getMircolove_open_date() + ", getMircolove_divid_num()="
				+ getMircolove_divid_num() + ", getMircolove_list_title()="
				+ getMircolove_list_title() + ", getMircolove_list_describe()="
				+ getMircolove_list_describe() + ", getMircolove_list_image()="
				+ getMircolove_list_image()
				+ ", getMircolove_list_min_image()="
				+ getMircolove_list_min_image()
				+ ", getMircolove_list_select()=" + getMircolove_list_select()
				+ ", getMircolove_list_addr()=" + getMircolove_list_addr()
				+ ", getMircolove_list_support_time()="
				+ getMircolove_list_support_time()
				+ ", getMircolove_verify_state()="
				+ getMircolove_verify_state() + ", getIs_delete()="
				+ getIs_delete() + ", getUser_id()=" + getUser_id()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

}
