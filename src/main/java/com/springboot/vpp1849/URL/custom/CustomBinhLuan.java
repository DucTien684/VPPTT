package com.springboot.vpp1849.URL.custom;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class CustomBinhLuan {

	private String tenKH;

	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date ngayBL;
	private String noiDungBL;

	public String getTenKH() {
		return tenKH;
	}

	public void setTenKH(String tenKH) {
		this.tenKH = tenKH;
	}

	public Date getNgayBL() {
		return ngayBL;
	}

	public void setNgayBL(Date ngayBL) {
		this.ngayBL = ngayBL;
	}

	public String getNoiDungBL() {
		return noiDungBL;
	}

	public void setNoiDungBL(String noiDungBL) {
		this.noiDungBL = noiDungBL;
	}

}
