package com.report;

public class Report {
	private String report_id;// varchar(20) primary key,
	private String report_name;// varchar(20) not null,
	private String report_num;// varchar(20) not null,
	private String report_reson;// varchar(300) not null,
	private String report_image;// varchar(1000) not null,
	private int report_ori;// smallint not null,--1£¬ÖúÁ¦¶ùÍ¯£¬2£¬Î¢¾èÔù£¬3£¬·ÖÏí¼ûÖ¤
	private String item_id;// varchar(20) not null
	
	
	public String getReport_id() {
		return report_id;
	}
	public void setReport_id(String report_id) {
		this.report_id = report_id;
	}
	public String getReport_name() {
		return report_name;
	}
	public void setReport_name(String report_name) {
		this.report_name = report_name;
	}
	public String getReport_num() {
		return report_num;
	}
	public void setReport_num(String report_num) {
		this.report_num = report_num;
	}
	public String getReport_reson() {
		return report_reson;
	}
	public void setReport_reson(String report_reson) {
		this.report_reson = report_reson;
	}
	public String getReport_image() {
		return report_image;
	}
	public void setReport_image(String report_image) {
		this.report_image = report_image;
	}
	public int getReport_ori() {
		return report_ori;
	}
	public void setReport_ori(int report_ori) {
		this.report_ori = report_ori;
	}
	public String getItem_id() {
		return item_id;
	}
	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}
	
	
}
