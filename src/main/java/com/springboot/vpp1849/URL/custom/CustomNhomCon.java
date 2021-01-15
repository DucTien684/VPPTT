package com.springboot.vpp1849.URL.custom;

import java.util.List;

import com.springboot.vpp1849.model.SP_NhomCon;

public class CustomNhomCon {
	private String idNhomSP;
	private String tenNhomSP;
	private List<SP_NhomCon> sp_NhomCon;

	public CustomNhomCon() {
		super();
	}

	public CustomNhomCon(String idNhomSP, String tenNhomSP, List<SP_NhomCon> sp_NhomCon) {
		super();
		this.idNhomSP = idNhomSP;
		this.tenNhomSP = tenNhomSP;
		this.sp_NhomCon = sp_NhomCon;
	}

	public String getIdNhomSP() {
		return idNhomSP;
	}

	public void setIdNhomSP(String idNhomSP) {
		this.idNhomSP = idNhomSP;
	}

	public String getTenNhomSP() {
		return tenNhomSP;
	}

	public void setTenNhomSP(String tenNhomSP) {
		this.tenNhomSP = tenNhomSP;
	}

	public List<SP_NhomCon> getSp_NhomCon() {
		return sp_NhomCon;
	}

	public void setSp_NhomCon(List<SP_NhomCon> sp_NhomCon) {
		this.sp_NhomCon = sp_NhomCon;
	}

}
