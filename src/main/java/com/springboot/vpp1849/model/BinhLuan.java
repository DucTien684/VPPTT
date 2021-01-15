package com.springboot.vpp1849.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "BinhLuan")
public class BinhLuan {

	@Id
	@Column(name = "IdBinhLuan")
	@NotNull
	private String idBinhLuan;

	@Column(name = "ThoiGian")
	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd//MM/yyyy")
	private Date thoiGian;

	@Column(name = "NoiDung")
	@NotNull
	private String noiDung;

	@JsonIgnore
	@NotNull
	@ManyToOne
	@JoinColumn(name = "IdKhachHang")
	private KhachHang khachHang;

	@JsonIgnore
	@NotNull
	@ManyToOne
	@JoinColumn(name = "IdSanPham")
	private SanPham sanPham;

	public BinhLuan() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BinhLuan(@NotNull String idBinhLuan, @NotNull Date thoiGian, @NotNull String noiDung,
			@NotNull KhachHang khachHang, @NotNull SanPham sanPham) {
		super();
		this.idBinhLuan = idBinhLuan;
		this.thoiGian = thoiGian;
		this.noiDung = noiDung;
		this.khachHang = khachHang;
		this.sanPham = sanPham;
	}

	public String getIdBinhLuan() {
		return idBinhLuan;
	}

	public void setIdBinhLuan(String idBinhLuan) {
		this.idBinhLuan = idBinhLuan;
	}

	public Date getThoiGian() {
		return thoiGian;
	}

	public void setThoiGian(Date thoiGian) {
		this.thoiGian = thoiGian;
	}

	public String getNoiDung() {
		return noiDung;
	}

	public void setNoiDung(String noiDung) {
		this.noiDung = noiDung;
	}

	public KhachHang getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}

	public SanPham getSanPham() {
		return sanPham;
	}

	public void setSanPham(SanPham sanPham) {
		this.sanPham = sanPham;
	}

}
