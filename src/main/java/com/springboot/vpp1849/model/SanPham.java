package com.springboot.vpp1849.model;

import java.text.DecimalFormat;
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
@Table(name = "SanPham")
public class SanPham {

	@Id
	@Column(name = "IdSanPham")
	@NotNull
	private String idSanPham;

	@Column(name = "MaSP")
	@NotNull
	private String maSP;

	@Column(name = "DonViTinh")
	@NotNull
	private String donViTinh;

	@Column(name = "SanXuat")
	@NotNull
	private String sanXuat;

	@Column(name = "ThuongHieu")
	@NotNull
	private String thuongHieu;

	@Column(name = "ThongTin")
	@NotNull
	private String thongTin;

	@Column(name = "TenSP")
	@NotNull
	private String tenSP;

	@Column(name = "HinhAnh")
	@NotNull
	private String hinhAnh;

	@Column(name = "GiaTien")
	@NotNull
	private double giaTien;

	@Column(name = "NgayDang")
	@NotNull
	private Date ngayDang;

	@Column(name = "NgayXoa")
	private Date ngayXoa;

	@Column(name = "NgayCapNhat")
	private Date ngayCapNhat;

	@Column(name = "SoLuongConLai")
	@NotNull
	private int soLuongConLai;

	@JsonIgnore
	@NotNull
	@ManyToOne
	@JoinColumn(name = "IdNhomSPCon")
	private SP_NhomCon sp_NhomCon;

	@JsonIgnore
	@NotNull
	@ManyToOne
	@JoinColumn(name = "IdNhanVien")
	private NhanVien nhanVien;

	@JsonIgnore
	@NotNull
	@ManyToOne
	@JoinColumn(name = "IdNCC")
	private NhaCungCap nhaCungCap;

	@JsonIgnore
	@OneToMany(mappedBy = "sanPham")
	private List<DH_ChiTiet> dh_ChiTiet;

	@JsonIgnore
	@OneToMany(mappedBy = "sanPham")
	private List<BinhLuan> binhLuan;

	public SanPham() {

	}

	public SanPham(@NotNull String idSanPham, @NotNull String maSP, @NotNull String donViTinh, @NotNull String sanXuat,
			@NotNull String thuongHieu, @NotNull String thongTin, @NotNull String tenSP, @NotNull String hinhAnh,
			@NotNull double giaTien, @NotNull Date ngayDang, Date ngayXoa, Date ngayCapNhat, @NotNull int soLuongConLai,
			@NotNull SP_NhomCon sp_NhomCon, @NotNull NhanVien nhanVien, @NotNull NhaCungCap nhaCungCap,
			List<DH_ChiTiet> dh_ChiTiet, List<BinhLuan> binhLuan) {
		super();
		this.idSanPham = idSanPham;
		this.maSP = maSP;
		this.donViTinh = donViTinh;
		this.sanXuat = sanXuat;
		this.thuongHieu = thuongHieu;
		this.thongTin = thongTin;
		this.tenSP = tenSP;
		this.hinhAnh = hinhAnh;
		this.giaTien = giaTien;
		this.ngayDang = ngayDang;
		this.ngayXoa = ngayXoa;
		this.ngayCapNhat = ngayCapNhat;
		this.soLuongConLai = soLuongConLai;
		this.sp_NhomCon = sp_NhomCon;
		this.nhanVien = nhanVien;
		this.nhaCungCap = nhaCungCap;
		this.dh_ChiTiet = dh_ChiTiet;
		this.binhLuan = binhLuan;
	}

	public String getIdSanPham() {
		return idSanPham;
	}

	public void setIdSanPham(String idSanPham) {
		this.idSanPham = idSanPham;
	}

	public String getMaSP() {
		return maSP;
	}

	public void setMaSP(String maSP) {
		this.maSP = maSP;
	}

	public String getDonViTinh() {
		return donViTinh;
	}

	public void setDonViTinh(String donViTinh) {
		this.donViTinh = donViTinh;
	}

	public String getSanXuat() {
		return sanXuat;
	}

	public void setSanXuat(String sanXuat) {
		this.sanXuat = sanXuat;
	}

	public String getThuongHieu() {
		return thuongHieu;
	}

	public void setThuongHieu(String thuongHieu) {
		this.thuongHieu = thuongHieu;
	}

	public String getThongTin() {
		return thongTin;
	}

	public void setThongTin(String thongTin) {
		this.thongTin = thongTin;
	}

	public String getTenSP() {
		return tenSP;
	}

	public void setTenSP(String tenSP) {
		this.tenSP = tenSP;
	}

	public String getHinhAnh() {
		return hinhAnh;
	}

	public void setHinhAnh(String hinhAnh) {
		this.hinhAnh = hinhAnh;
	}

	public double getGiaTien() {
		return giaTien;
	}

	public void setGiaTien(double giaTien) {
		this.giaTien = giaTien;
	}

	public Date getNgayDang() {
		return ngayDang;
	}

	public void setNgayDang(Date ngayDang) {
		this.ngayDang = ngayDang;
	}

	public Date getNgayXoa() {
		return ngayXoa;
	}

	public void setNgayXoa(Date ngayXoa) {
		this.ngayXoa = ngayXoa;
	}

	public Date getNgayCapNhat() {
		return ngayCapNhat;
	}

	public void setNgayCapNhat(Date ngayCapNhat) {
		this.ngayCapNhat = ngayCapNhat;
	}

	public int getSoLuongConLai() {
		return soLuongConLai;
	}

	public void setSoLuongConLai(int soLuongConLai) {
		this.soLuongConLai = soLuongConLai;
	}

	public SP_NhomCon getSp_NhomCon() {
		return sp_NhomCon;
	}

	public void setSp_NhomCon(SP_NhomCon sp_NhomCon) {
		this.sp_NhomCon = sp_NhomCon;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	public NhaCungCap getNhaCungCap() {
		return nhaCungCap;
	}

	public void setNhaCungCap(NhaCungCap nhaCungCap) {
		this.nhaCungCap = nhaCungCap;
	}

	public List<DH_ChiTiet> getDh_ChiTiet() {
		return dh_ChiTiet;
	}

	public void setDh_ChiTiet(List<DH_ChiTiet> dh_ChiTiet) {
		this.dh_ChiTiet = dh_ChiTiet;
	}

	public List<BinhLuan> getBinhLuan() {
		return binhLuan;
	}

	public void setBinhLuan(List<BinhLuan> binhLuan) {
		this.binhLuan = binhLuan;
	}

	public String getGiaTienFormat() {
		DecimalFormat formatter = new DecimalFormat("###,###,###");

		String format = formatter.format((int) this.giaTien);

		return format;
	}

}
