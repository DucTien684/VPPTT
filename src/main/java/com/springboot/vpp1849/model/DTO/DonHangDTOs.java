package com.springboot.vpp1849.model.DTO;

import com.springboot.vpp1849.model.DH_ChiTiet;
import com.springboot.vpp1849.model.DonHang;
import com.springboot.vpp1849.model.KhachHang;

public class DonHangDTOs {

	private String tenSP;
	private KhachHang khachHang;
	private DonHang donHang;
	private DH_ChiTiet dh_ChiTiet;

	public DonHangDTOs() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DonHangDTOs(String tenSP, KhachHang khachHang, DonHang donHang, DH_ChiTiet dh_ChiTiet) {
		super();
		this.tenSP = tenSP;
		this.khachHang = khachHang;
		this.donHang = donHang;
		this.dh_ChiTiet = dh_ChiTiet;
	}

	public String getTenSP() {
		return tenSP;
	}

	public void setTenSP(String tenSP) {
		this.tenSP = tenSP;
	}

	public KhachHang getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}

	public DonHang getDonHang() {
		return donHang;
	}

	public void setDonHang(DonHang donHang) {
		this.donHang = donHang;
	}

	public DH_ChiTiet getDh_ChiTiet() {
		return dh_ChiTiet;
	}

	public void setDh_ChiTiet(DH_ChiTiet dh_ChiTiet) {
		this.dh_ChiTiet = dh_ChiTiet;
	}

}
