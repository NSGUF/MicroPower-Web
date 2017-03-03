package com.donation;

public class DonationInfo {
	private String donation_id;
	private String donation_raise_goods;
	private int donation_trans_cost;
	private String donation_close_date;
	private String donation_open_date;
	private String donation_title;
	private String donation_describe;
	private String donation_image;
	private String donation_min_image;
	private int donation_select_need_or_dona;
	private String donation_addr;
	private int is_donation_black = 0;
	private int is_success = 0;
	private int is_delete = 0;
	private int donation_verify_state = 1;
	private String user_id;

	public String getDonation_id() {
		return donation_id;
	}

	public void setDonation_id(String donation_id) {
		this.donation_id = donation_id;
	}

	public String getDonation_raise_goods() {
		return donation_raise_goods;
	}

	public void setDonation_raise_goods(String donation_raise_goods) {
		this.donation_raise_goods = donation_raise_goods;
	}

	public int getDonation_trans_cost() {
		return donation_trans_cost;
	}

	public void setDonation_trans_cost(int donation_trans_cost) {
		this.donation_trans_cost = donation_trans_cost;
	}

	public int getIs_delete() {
		return is_delete;
	}

	public void setIs_delete(int is_delete) {
		this.is_delete = is_delete;
	}

	public String getDonation_close_date() {
		return donation_close_date;
	}

	public void setDonation_close_date(String donation_close_date) {
		this.donation_close_date = donation_close_date;
	}

	public String getDonation_open_date() {
		return donation_open_date;
	}

	public void setDonation_open_date(String donation_open_date) {
		this.donation_open_date = donation_open_date;
	}

	public String getDonation_title() {
		return donation_title;
	}

	public void setDonation_title(String donation_title) {
		this.donation_title = donation_title;
	}

	public String getDonation_describe() {
		return donation_describe;
	}

	public void setDonation_describe(String donation_describe) {
		this.donation_describe = donation_describe;
	}

	public String getDonation_image() {
		return donation_image;
	}

	public void setDonation_image(String donation_image) {
		this.donation_image = donation_image;
	}

	public String getDonation_min_image() {
		return donation_min_image;
	}

	public void setDonation_min_image(String donation_min_image) {
		this.donation_min_image = donation_min_image;
	}

	public int getDonation_select_need_or_dona() {
		return donation_select_need_or_dona;
	}

	public void setDonation_select_need_or_dona(int donation_select_need_or_dona) {
		this.donation_select_need_or_dona = donation_select_need_or_dona;
	}

	public String getDonation_addr() {
		return donation_addr;
	}

	public void setDonation_addr(String donation_addr) {
		this.donation_addr = donation_addr;
	}

	public int getIs_donation_black() {
		return is_donation_black;
	}

	public void setIs_donation_black(int is_donation_black) {
		this.is_donation_black = is_donation_black;
	}

	public int getIs_success() {
		return is_success;
	}

	public void setIs_success(int is_success) {
		this.is_success = is_success;
	}

	public int getDonation_verify_state() {
		return donation_verify_state;
	}

	public void setDonation_verify_state(int donation_verify_state) {
		this.donation_verify_state = donation_verify_state;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

}
