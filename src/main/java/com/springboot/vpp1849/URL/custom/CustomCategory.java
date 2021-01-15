package com.springboot.vpp1849.URL.custom;

import java.util.List;

import com.springboot.vpp1849.model.SanPham;

public class CustomCategory {
	private String idNhom;
	private String tenNhom;
	private List<SanPham> sanPham;

	public CustomCategory() {
		super();
	}

	public CustomCategory(String idNhom, String tenNhom, List<SanPham> sanPham) {
		super();
		this.idNhom = idNhom;
		this.tenNhom = tenNhom;
		this.sanPham = sanPham;
	}

	public String getIdNhom() {
		return idNhom;
	}

	public void setIdNhom(String idNhom) {
		this.idNhom = idNhom;
	}

	public String getTenNhom() {
		return tenNhom;
	}

	public void setTenNhom(String tenNhom) {
		this.tenNhom = tenNhom;
	}

	public List<SanPham> getSanPham() {
		return sanPham;
	}

	public void setSanPham(List<SanPham> sanPham) {
		this.sanPham = sanPham;
	}

}
