package com.address;

public class Address {
	private String name;
	private String cellphone;
	private String province;
	private String city;
	private String county;
	private String detail;
	private String is_default;

	public Address(String name, String cellphone, String province, String city,
			String county, String detail,String is_default) {
		super();
		this.name = name;
		this.cellphone = cellphone;
		this.province = province;
		this.city = city;
		this.county = county;
		this.detail = detail;
		this.is_default=is_default;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getIs_default() {
		return is_default;
	}

	public void setIs_default(String is_default) {
		this.is_default = is_default;
	}

}
