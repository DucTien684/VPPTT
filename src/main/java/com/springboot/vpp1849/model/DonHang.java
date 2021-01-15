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
@Table(name = "DonHang")
public class DonHang {

	@Id
	@Column(name = "IdDonHang")
	@NotNull
	private String idDonHang;

	@Column(name = "NgayLapDH")
	@NotNull
	private Date ngayLapDH;

	@Column(name = "TongSoTien")
	@NotNull
	private double tongSoTien;

	@Column(name = "Thue")
	private int thue;

	@Column(name = "DiaChiGiaoHang")
	@NotNull
	private String diaChiGiaoHang;

	@Column(name = "TrangThai")
	@NotNull
	private int trangThai;

	@Column(name = "NgayXoa")
	private Date ngayXoa;

	@JsonIgnore
	@NotNull
	@ManyToOne
	@JoinColumn(name = "IdKhachHang")
	private KhachHang khachHang;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "IdNhanVien")
	private NhanVien nhanVien;

	@JsonIgnore
	@OneToMany(mappedBy = "donHang")
	private List<DH_ChiTiet> dh_ChiTiet;

	public DonHang() {

	}

	public DonHang(@NotNull String idDonHang, @NotNull Date ngayLapDH, @NotNull double tongSoTien,
			@NotNull int trangThai) {
		super();
		this.idDonHang = idDonHang;
		this.ngayLapDH = ngayLapDH;
		this.tongSoTien = tongSoTien;
		this.trangThai = trangThai;
	}

	public DonHang(@NotNull String idDonHang, @NotNull Date ngayLapDH, @NotNull double tongSoTien, int thue,
			@NotNull String diaChiGiaoHang, @NotNull int trangThai, @NotNull KhachHang khachHang,
			@NotNull NhanVien nhanVien, List<DH_ChiTiet> dh_ChiTiet, Date ngayXoa) {
		super();
		this.idDonHang = idDonHang;
		this.ngayLapDH = ngayLapDH;
		this.tongSoTien = tongSoTien;
		this.thue = thue;
		this.diaChiGiaoHang = diaChiGiaoHang;
		this.trangThai = trangThai;
		this.ngayXoa = ngayXoa;
		this.khachHang = khachHang;
		this.nhanVien = nhanVien;
		this.dh_ChiTiet = dh_ChiTiet;
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

	public double getTongSoTien() {
		return tongSoTien;
	}

	public void setTongSoTien(double tongSoTien) {
		this.tongSoTien = tongSoTien;
	}

	public int getThue() {
		return thue;
	}

	public void setThue(int thue) {
		this.thue = thue;
	}

	public String getDiaChiGiaoHang() {
		return diaChiGiaoHang;
	}

	public void setDiaChiGiaoHang(String diaChiGiaoHang) {
		this.diaChiGiaoHang = diaChiGiaoHang;
	}

	public int getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(int trangThai) {
		this.trangThai = trangThai;
	}

	public Date getNgayXoa() {
		return ngayXoa;
	}

	public void setNgayXoa(Date ngayXoa) {
		this.ngayXoa = ngayXoa;
	}

	public KhachHang getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	public List<DH_ChiTiet> getDh_ChiTiet() {
		return dh_ChiTiet;
	}

	public void setDh_ChiTiet(List<DH_ChiTiet> dh_ChiTiet) {
		this.dh_ChiTiet = dh_ChiTiet;
	}

}
