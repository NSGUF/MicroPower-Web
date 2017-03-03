package com.share;

public class Sharer {
	private String witness_id;// witness_num varchar(20) primary key,
	private String witness_title;// witness_title varchar(60) not null,
	private String witness_open_date;
	private String witness_describe;// wit_describe varchar(300) not null,
	private String witness_image;// photo varchar(300) not null,
	private String witness_min_image;// photo varchar(300) not null,
	private int witness_verify_state = 1;
	private String witness_addr;
	private int is_witness_black = 0;
	private int is_delete = 0;
	private String user_id;

	public String getWitness_id() {
		return witness_id;
	}

	public void setWitness_id(String witness_id) {
		this.witness_id = witness_id;
	}

	public String getWitness_title() {
		return witness_title;
	}

	public void setWitness_title(String witness_title) {
		this.witness_title = witness_title;
	}

	public String getWitness_open_date() {
		return witness_open_date;
	}

	public void setWitness_open_date(String witness_open_date) {
		this.witness_open_date = witness_open_date;
	}

	public String getWitness_describe() {
		return witness_describe;
	}

	public void setWitness_describe(String witness_describe) {
		this.witness_describe = witness_describe;
	}

	public String getWitness_image() {
		return witness_image;
	}

	public void setWitness_image(String witness_image) {
		this.witness_image = witness_image;
	}

	public String getWitness_min_image() {
		return witness_min_image;
	}

	public void setWitness_min_image(String witness_min_image) {
		this.witness_min_image = witness_min_image;
	}

	public int getWitness_verify_state() {
		return witness_verify_state;
	}

	public void setWitness_verify_state(int witness_verify_state) {
		this.witness_verify_state = witness_verify_state;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getWitness_addr() {
		return witness_addr;
	}

	public void setWitness_addr(String witness_addr) {
		this.witness_addr = witness_addr;
	}

	public int getIs_witness_black() {
		return is_witness_black;
	}

	public void setIs_witness_black(int is_witness_black) {
		this.is_witness_black = is_witness_black;
	}

	public int getIs_delete() {
		return is_delete;
	}

	public void setIs_delete(int is_delete) {
		this.is_delete = is_delete;
	}

}
