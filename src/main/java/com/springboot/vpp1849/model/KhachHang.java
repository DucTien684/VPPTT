package com.springboot.vpp1849.model;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "KhachHang")
public class KhachHang {

	@Id
	@Column(name = "IdKhachHang")
	
	private String idKhachHang;

	@Column(name = "HoTen")
	@NotBlank( message = "Vui lòng nhập họ tên")
	private String hoTen;

	@Column(name = "SDT")
	private String sdt;

	@Column(name = "Email")
	private String email;

	@Column(name = "MatKhau")
	private String matKhau;

	@Column(name = "ngayDK")
	@NotNull
	private Date ngayDK;

	@Column(name = "NgayXoa")
	private Date ngayXoa;

	@Column(name = "TrangThai")
	private int trangThai;


	@OneToMany(mappedBy = "khachHang")
	private List<BinhLuan> binhLuan;


	@OneToMany(mappedBy = "khachHang")
	private List<DonHang> hoaDon;

	@OneToMany(mappedBy="khachHang", fetch = FetchType.EAGER)
	private List<ND_SoDiaChi> soDiaChi;

	public KhachHang() {

	}

	public KhachHang(@NotNull String idKhachHang, @NotNull String hoTen, String sdt, String email, String matKhau,
					 @NotNull Date ngayDK, Date ngayXoa, int trangThai, List<BinhLuan> binhLuan, List<DonHang> hoaDon,
					 List<ND_SoDiaChi> soDiaChi) {
		super();
		this.idKhachHang = idKhachHang;
		this.hoTen = hoTen;
		this.sdt = sdt;
		this.email = email;
		this.matKhau = matKhau;
		this.ngayDK = ngayDK;
		this.ngayXoa = ngayXoa;
		this.trangThai = trangThai;
		this.binhLuan = binhLuan;
		this.hoaDon = hoaDon;
		this.soDiaChi = soDiaChi;
	}

	public String getIdKhachHang() {
		return idKhachHang;
	}

	public void setIdKhachHang(String idKhachHang) {
		this.idKhachHang = idKhachHang;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
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

	public Date getNgayDK() {
		return ngayDK;
	}

	public void setNgayDK(Date ngayDK) {
		this.ngayDK = ngayDK;
	}

	public Date getNgayXoa() {
		return ngayXoa;
	}

	public void setNgayXoa(Date ngayXoa) {
		this.ngayXoa = ngayXoa;
	}

	public int getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(int trangThai) {
		this.trangThai = trangThai;
	}

	public Collection<BinhLuan> getBinhLuan() {
		return binhLuan;
	}

	public void setBinhLuan(List<BinhLuan> binhLuan) {
		this.binhLuan = binhLuan;
	}

	public Collection<DonHang> getHoaDon() {
		return hoaDon;
	}

	public void setHoaDon(List<DonHang> hoaDon) {
		this.hoaDon = hoaDon;
	}

	public List<ND_SoDiaChi> getSoDiaChi() {
		return soDiaChi;
	}

	public void setSoDiaChi(List<ND_SoDiaChi> soDiaChi) {
		this.soDiaChi = soDiaChi;
	}

}
