package com.volunteer;

public class Volunteer {
	private String wallet_id;// varchar(100),--֧����/΢���˺Ŷ�ά��
	private String is_volunteer;// smallint not null default 0,--1Ϊ�ǣ�0Ϊ��
	private String verify_info;// varchar(1000),--�����Ϣ��
	private String verify_image;// varchar(1000),--�����Ϣ��һ����ͼƬ������
	private String ID_card;// varchar(100),--���֤��
	private String verify_state;// smallint,--1������ˣ�2��δͨ����ˣ�3�����ͨ��

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
