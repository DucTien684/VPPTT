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
@Table(name = "XNT_ChiTiet")
public class XNT_ChiTiet {

	@Id
	@Column(name = "IdXNT_ChiTiet")
	@NotNull
	private String idXNT_ChiTiet;

	@JsonIgnore
	@NotNull
	@ManyToOne
	@JoinColumn(name = "IdPhieu")
	private XuatNhapTon xuatNhapTon;

	@Column(name = "DonViTinh")
	@NotNull
	private String donViTinh;

	@Column(name = "SoLuongTonDauKy")
	private int soLuongTonDauKy;

	@Column(name = "SoLuongNhapTrongKy")
	private int soLuongNhapTrongKy;

	@Column(name = "SoLuongXuatTrongKy")
	private int soLuongXuatTrongKy;

	@Column(name = "SoLuongTonCuoiKy")
	private int soLuongTonCuoiKy;

	@Column(name = "DonGiaDauKy")
	private double donGiaDauKy;

	@Column(name = "DonGiaNhapTrongKy")
	private double donGiaNhapTrongKy;

	@Column(name = "DonGiaXuatTrongKy")
	private double donGiaXuatTrongKy;

	@Column(name = "DonGiaTonCuoiKy")
	private double donGiaTonCuoiKy;

	@JsonIgnore
	@NotNull
	@ManyToOne
	@JoinColumn(name = "IdSanPham")
	private SanPham sanPham;

	@JsonIgnore
	@NotNull
	@ManyToOne
	@JoinColumn(name = "IdDonHangChiTiet")
	private DH_ChiTiet dh_ChiTiet;

	public XNT_ChiTiet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public XNT_ChiTiet(@NotNull String idXNT_ChiTiet, @NotNull XuatNhapTon xuatNhapTon, @NotNull String donViTinh,
			int soLuongTonDauKy, int soLuongNhapTrongKy, int soLuongXuatTrongKy, int soLuongTonCuoiKy,
			double donGiaDauKy, double donGiaNhapTrongKy, double donGiaXuatTrongKy, double donGiaTonCuoiKy,
			@NotNull SanPham sanPham, @NotNull DH_ChiTiet dh_ChiTiet) {
		super();
		this.idXNT_ChiTiet = idXNT_ChiTiet;
		this.xuatNhapTon = xuatNhapTon;
		this.donViTinh = donViTinh;
		this.soLuongTonDauKy = soLuongTonDauKy;
		this.soLuongNhapTrongKy = soLuongNhapTrongKy;
		this.soLuongXuatTrongKy = soLuongXuatTrongKy;
		this.soLuongTonCuoiKy = soLuongTonCuoiKy;
		this.donGiaDauKy = donGiaDauKy;
		this.donGiaNhapTrongKy = donGiaNhapTrongKy;
		this.donGiaXuatTrongKy = donGiaXuatTrongKy;
		this.donGiaTonCuoiKy = donGiaTonCuoiKy;
		this.sanPham = sanPham;
		this.dh_ChiTiet = dh_ChiTiet;
	}

	public String getIdXNT_ChiTiet() {
		return idXNT_ChiTiet;
	}

	public void setIdXNT_ChiTiet(String idXNT_ChiTiet) {
		this.idXNT_ChiTiet = idXNT_ChiTiet;
	}

	public XuatNhapTon getXuatNhapTon() {
		return xuatNhapTon;
	}

	public void setXuatNhapTon(XuatNhapTon xuatNhapTon) {
		this.xuatNhapTon = xuatNhapTon;
	}

	public String getDonViTinh() {
		return donViTinh;
	}

	public void setDonViTinh(String donViTinh) {
		this.donViTinh = donViTinh;
	}

	public int getSoLuongTonDauKy() {
		return soLuongTonDauKy;
	}

	public void setSoLuongTonDauKy(int soLuongTonDauKy) {
		this.soLuongTonDauKy = soLuongTonDauKy;
	}

	public int getSoLuongNhapTrongKy() {
		return soLuongNhapTrongKy;
	}

	public void setSoLuongNhapTrongKy(int soLuongNhapTrongKy) {
		this.soLuongNhapTrongKy = soLuongNhapTrongKy;
	}

	public int getSoLuongXuatTrongKy() {
		return soLuongXuatTrongKy;
	}

	public void setSoLuongXuatTrongKy(int soLuongXuatTrongKy) {
		this.soLuongXuatTrongKy = soLuongXuatTrongKy;
	}

	public int getSoLuongTonCuoiKy() {
		return soLuongTonCuoiKy;
	}

	public void setSoLuongTonCuoiKy(int soLuongTonCuoiKy) {
		this.soLuongTonCuoiKy = soLuongTonCuoiKy;
	}

	public double getDonGiaDauKy() {
		return donGiaDauKy;
	}

	public void setDonGiaDauKy(double donGiaDauKy) {
		this.donGiaDauKy = donGiaDauKy;
	}

	public double getDonGiaNhapTrongKy() {
		return donGiaNhapTrongKy;
	}

	public void setDonGiaNhapTrongKy(double donGiaNhapTrongKy) {
		this.donGiaNhapTrongKy = donGiaNhapTrongKy;
	}

	public double getDonGiaXuatTrongKy() {
		return donGiaXuatTrongKy;
	}

	public void setDonGiaXuatTrongKy(double donGiaXuatTrongKy) {
		this.donGiaXuatTrongKy = donGiaXuatTrongKy;
	}

	public double getDonGiaTonCuoiKy() {
		return donGiaTonCuoiKy;
	}

	public void setDonGiaTonCuoiKy(double donGiaTonCuoiKy) {
		this.donGiaTonCuoiKy = donGiaTonCuoiKy;
	}

	public SanPham getSanPham() {
		return sanPham;
	}

	public void setSanPham(SanPham sanPham) {
		this.sanPham = sanPham;
	}

	public DH_ChiTiet getDh_ChiTiet() {
		return dh_ChiTiet;
	}

	public void setDh_ChiTiet(DH_ChiTiet dh_ChiTiet) {
		this.dh_ChiTiet = dh_ChiTiet;
	}

}
