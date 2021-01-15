package com.springboot.vpp1849.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "DH_ChiTiet")
public class DH_ChiTiet {

	@Id
	@Column(name = "IdDonHangChiTiet")
	@NotNull
	private String idDonHangChiTiet;

	@Column(name = "DonGia")
	@NotNull
	private double donGia;

	@Column(name = "SoLuong")
	@NotNull
	private int soLuong;

	@JsonIgnore
	@NotNull
	@ManyToOne
	@JoinColumn(name = "IdDonHang")
	private DonHang donHang;

	@JsonIgnore
	@NotNull
	@ManyToOne
	@JoinColumn(name = "IdSanPham")
	private SanPham sanPham;

	public DH_ChiTiet() {

	}

	public DH_ChiTiet(@NotNull String idDonHangChiTiet, @NotNull double donGia, @NotNull int soLuong,
			@NotNull DonHang donHang, @NotNull SanPham sanPham) {
		super();
		this.idDonHangChiTiet = idDonHangChiTiet;
		this.donGia = donGia;
		this.soLuong = soLuong;
		this.donHang = donHang;
		this.sanPham = sanPham;
	}

	public String getIdDonHangChiTiet() {
		return idDonHangChiTiet;
	}

	public void setIdDonHangChiTiet(String idDonHangChiTiet) {
		this.idDonHangChiTiet = idDonHangChiTiet;
	}

	public double getDonGia() {
		return donGia;
	}

	public void setDonGia(double donGia) {
		this.donGia = donGia;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public DonHang getDonHang() {
		return donHang;
	}

	public void setDonHang(DonHang donHang) {
		this.donHang = donHang;
	}

	public SanPham getSanPham() {
		return sanPham;
	}

	public void setSanPham(SanPham sanPham) {
		this.sanPham = sanPham;
	}

}
