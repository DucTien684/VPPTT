package com.springboot.vpp1849.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "XuatNhapTon")
public class XuatNhapTon {

	@Id
	@Column(name = "IdPhieu")
	@NotNull
	private String idPhieu;

	@Column(name = "NgayTao")
	@NotNull
	private Date ngayTao;

	@Column(name = "TuNgay")
	private Date tuNgay;

	@Column(name = "DenNgay")
	private Date denNgay;

	@Column(name = "DiaChi")
	@NotNull
	private String diaChi;

	@Column(name = "TrangThai")
	@NotNull
	private String trangThai;

	@Column(name = "GhiChu")
	@NotNull
	private String ghiChu;

	@JsonIgnore
	@NotNull
	@ManyToOne
	@JoinColumn(name = "IdNhanVien")
	private NhanVien nhanVien;

	@JsonIgnore
	@OneToMany(mappedBy = "xuatNhapTon")
	private List<XNT_ChiTiet> xnt_ChiTiet;

	public XuatNhapTon() {
		super();
		// TODO Auto-generated constructor stub
	}

	public XuatNhapTon(@NotNull String idPhieu, @NotNull Date ngayTao, Date tuNgay, Date denNgay,
			@NotNull String diaChi, @NotNull String trangThai, @NotNull String ghiChu, @NotNull NhanVien nhanVien,
			List<XNT_ChiTiet> xnt_ChiTiet) {
		super();
		this.idPhieu = idPhieu;
		this.ngayTao = ngayTao;
		this.tuNgay = tuNgay;
		this.denNgay = denNgay;
		this.diaChi = diaChi;
		this.trangThai = trangThai;
		this.ghiChu = ghiChu;
		this.nhanVien = nhanVien;
		this.xnt_ChiTiet = xnt_ChiTiet;
	}

	public String getIdPhieu() {
		return idPhieu;
	}

	public void setIdPhieu(String idPhieu) {
		this.idPhieu = idPhieu;
	}

	public Date getNgayTao() {
		return ngayTao;
	}

	public void setNgayTao(Date ngayTao) {
		this.ngayTao = ngayTao;
	}

	public Date getTuNgay() {
		return tuNgay;
	}

	public void setTuNgay(Date tuNgay) {
		this.tuNgay = tuNgay;
	}

	public Date getDenNgay() {
		return denNgay;
	}

	public void setDenNgay(Date denNgay) {
		this.denNgay = denNgay;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	public List<XNT_ChiTiet> getXnt_ChiTiet() {
		return xnt_ChiTiet;
	}

	public void setXnt_ChiTiet(List<XNT_ChiTiet> xnt_ChiTiet) {
		this.xnt_ChiTiet = xnt_ChiTiet;
	}

}
