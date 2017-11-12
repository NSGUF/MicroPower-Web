package com.volunteer;

public class Volunteer {
	private String wallet_id;// varchar(100),--支付宝/微信账号二维码
	private String is_volunteer;// smallint not null default 0,--1为是，0为否
	private String verify_info;// varchar(1000),--审核信息，
	private String verify_image;// varchar(1000),--审核信息，一般是图片和文字
	private String ID_card;// varchar(100),--身份证号
	private String verify_state;// smallint,--1，待审核，2，未通过审核，3审核已通过

	public Volunteer(String wallet_id, String is_volunteer, String verify_info,
			String verify_image, String iD_card, String verify_state) {
		super();
		this.wallet_id = wallet_id;
		this.is_volunteer = is_volunteer;
		this.verify_info = verify_info;
		this.verify_image = verify_image;
		ID_card = iD_card;
		this.verify_state = verify_state;
	}

	public String getWallet_id() {
		return wallet_id;
	}

	public void setWallet_id(String wallet_id) {
		this.wallet_id = wallet_id;
	}

	public String getIs_volunteer() {
		return is_volunteer;
	}

	public void setIs_volunteer(String is_volunteer) {
		this.is_volunteer = is_volunteer;
	}

	public String getVerify_info() {
		return verify_info;
	}

	public void setVerify_info(String verify_info) {
		this.verify_info = verify_info;
	}

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

	public String getVerify_state() {
		return verify_state;
	}

	public void setVerify_state(String verify_state) {
		this.verify_state = verify_state;
	}

}
