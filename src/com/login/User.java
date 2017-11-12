package com.login;

public class User {
	private String user_id = "";
	private String head_portrait = "";
	private String pet_name = "";
	private String cell_phone_id = "";
	private String wallet_id = "";
	private String wechat_id = "";
	private String micro_blog_id = "";
	private String qq_id = "";
	private String alipay_id = "";
	private int is_volunteer = 0;
	private String verify_info = "";
	private String verify_image="";
	private int verify_state = 1;
	private int is_black = 0;
	private String open_date = "";
	private String ID_card="";
	
	
	public String getVerify_image() {
		return verify_image;
	}

	public void setVerify_image(String verify_image) {
		this.verify_image = verify_image;
	}

	public String getID_card() {
		return ID_card;
	}

	public void setID_card(String iD_card) {
		ID_card = iD_card;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getHead_portrait() {
		return head_portrait;
	}

	public void setHead_portrait(String head_portrait) {
		this.head_portrait = head_portrait;
	}

	public String getPet_name() {
		return pet_name;
	}

	public void setPet_name(String pet_name) {
		this.pet_name = pet_name;
	}

	public String getCell_phone_id() {
		return cell_phone_id;
	}

	public void setCell_phone_id(String cell_phone_id) {
		this.cell_phone_id = cell_phone_id;
	}

	public String getWallet_id() {
		return wallet_id;
	}

	public void setWallet_id(String string) {
		this.wallet_id = string;
	}

	public String getWechat_id() {
		return wechat_id;
	}

	public void setWechat_id(String wechat_id) {
		this.wechat_id = wechat_id;
	}

	public String getMicro_blog_id() {
		return micro_blog_id;
	}

	public void setMicro_blog_id(String micro_blog_id) {
		this.micro_blog_id = micro_blog_id;
	}

	public String getQq_id() {
		return qq_id;
	}

	public void setQq_id(String qq_id) {
		this.qq_id = qq_id;
	}

	public String getAlipay_id() {
		return alipay_id;
	}

	public void setAlipay_id(String alipay_id) {
		this.alipay_id = alipay_id;
	}

	public int getIs_volunteer() {
		return is_volunteer;
	}

	public void setIs_volunteer(int is_volunteer) {
		this.is_volunteer = is_volunteer;
	}

	public String getVerify_info() {
		return verify_info;
	}

	public void setVerify_info(String verify_info) {
		this.verify_info = verify_info;
	}

	public int getVerify_state() {
		return verify_state;
	}

	public void setVerify_state(int verify_state) {
		this.verify_state = verify_state;
	}

	public int getIs_black() {
		return is_black;
	}

	public void setIs_black(int is_black) {
		this.is_black = is_black;
	}

	public String getOpen_date() {
		return open_date;
	}

	public void setOpen_date(String open_date) {
		this.open_date = open_date;
	}

}
