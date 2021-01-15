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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "NhanVien")
public class NhanVien {

	@Id
	@Column(name = "IdNhanVien")
	@NotNull
	private String idNhanVien;

	@Column(name = "HoTen")
	@NotNull
	private String hoTen;

	@Column(name = "GioiTinh")
	@NotNull
	private String gioiTinh;

	@Column(name = "NgaySinh")
	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date ngaySinh;

	@Column(name = "DiaChi")
	@NotNull
	private String diaChi;

	@Column(name = "SDT")
	@NotNull
	private String sdt;

	@Column(name = "Anh")
	private String anh;

	@Column(name = "Email")
	@NotNull
	private String email;

	@Column(name = "MatKhau")
	@NotNull
	private String matKhau;

	@Column(name = "NgayXoa")
	private Date ngayXoa;

	@JsonIgnore
	@NotNull
	@ManyToOne
	@JoinColumn(name = "IdChucVu")
	private NV_ChucVu nv_ChucVu;

	@JsonIgnore
	@OneToMany(mappedBy = "nhanVien")
	private List<SanPham> sanPham;

	@JsonIgnore
	@OneToMany(mappedBy = "nhanVien")
	private List<DonHang> donHang;

	public NhanVien() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NhanVien(@NotNull String idNhanVien, @NotNull String hoTen, @NotNull String gioiTinh, @NotNull Date ngaySinh,
			@NotNull String diaChi, @NotNull String sdt, String anh, @NotNull String email, @NotNull String matKhau,
			@NotNull NV_ChucVu nv_ChucVu, List<SanPham> sanPham, List<DonHang> donHang, Date ngayXoa) {
		super();
		this.idNhanVien = idNhanVien;
		this.hoTen = hoTen;
		this.gioiTinh = gioiTinh;
		this.ngaySinh = ngaySinh;
		this.diaChi = diaChi;
		this.sdt = sdt;
		this.anh = anh;
		this.email = email;
		this.matKhau = matKhau;
		this.ngayXoa = ngayXoa;
		this.nv_ChucVu = nv_ChucVu;
		this.sanPham = sanPham;
		this.donHang = donHang;
	}

	public String getIdNhanVien() {
		return idNhanVien;
	}

	public void setIdNhanVien(String idNhanVien) {
		this.idNhanVien = idNhanVien;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public String getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public Date getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public String getAnh() {
		return anh;
	}

	public void setAnh(String anh) {
		this.anh = anh;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	public Date getNgayXoa() {
		return ngayXoa;
	}

	public void setNgayXoa(Date ngayXoa) {
		this.ngayXoa = ngayXoa;
	}

	public NV_ChucVu getNv_ChucVu() {
		return nv_ChucVu;
	}

	public void setNv_ChucVu(NV_ChucVu nv_ChucVu) {
		this.nv_ChucVu = nv_ChucVu;
	}

	public List<SanPham> getSanPham() {
		return sanPham;
	}

	public void setSanPham(List<SanPham> sanPham) {
		this.sanPham = sanPham;
	}

	public List<DonHang> getDonHang() {
		return donHang;
	}

	public void setDonHang(List<DonHang> donHang) {
		this.donHang = donHang;
	}

}
