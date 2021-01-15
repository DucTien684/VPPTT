package com.springboot.vpp1849.model.DTO;

import java.util.Date;

public class DonHangDTO {
	private String idDonHang;
	private Date ngayLapDH;
	private String tenSP;
	private Float tongSoTien;
	private String trangThai;

	public DonHangDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DonHangDTO(String idDonHang, Date ngayLapDH, String tenSP, Float tongSoTien, String trangThai) {
		super();
		this.idDonHang = idDonHang;
		this.ngayLapDH = ngayLapDH;
		this.tenSP = tenSP;
		this.tongSoTien = tongSoTien;
		this.trangThai = trangThai;
	}

	public String getIdDonHang() {
		return idDonHang;
	}

	public void setIdDonHang(String idDonHang) {
		this.idDonHang = idDonHang;
	}

	public Date getNgayLapDH() {
		return ngayLapDH;
	}

	public void setNgayLapDH(Date ngayLapDH) {
		this.ngayLapDH = ngayLapDH;
	}

	public String getTenSP() {
		return tenSP;
	}

	public void setTenSP(String tenSP) {
		this.tenSP = tenSP;
	}

	public Float getTongSoTien() {
		return tongSoTien;
	}

	public void setTongSoTien(Float tongSoTien) {
		this.tongSoTien = tongSoTien;
	}

	public String getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}

}
