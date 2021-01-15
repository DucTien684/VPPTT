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
@Table(name = "ND_SoDiaChi")
public class ND_SoDiaChi {

	@Id
	@Column(name = "IdSoDiaChi")
	@NotNull
	private String idSoDiaChi;

	@Column(name = "Tinh")
	@NotNull
	private String tinh;

	@Column(name = "Huyen")
	@NotNull
	private String huyen;

	@Column(name = "Xa")
	@NotNull
	private String xa;

	@Column(name = "DiaChiCuThe")
	@NotNull
	private String diaChiCuThe;

	@Column(name = "HoTen")
	@NotNull
	private String hoTen;

	@Column(name = "SDT")
	@NotNull
	private String sdt;

	@JsonIgnore
	@NotNull
	@ManyToOne
	@JoinColumn(name = "IdKhachHang")
	private KhachHang khachHang;

	public ND_SoDiaChi() {

	}

	public ND_SoDiaChi(@NotNull String idSoDiaChi, @NotNull String tinh, @NotNull String huyen, @NotNull String xa,
			@NotNull String diaChiCuThe, @NotNull String hoTen, @NotNull String sdt, @NotNull KhachHang khachHang) {
		super();
		this.idSoDiaChi = idSoDiaChi;
		this.tinh = tinh;
		this.huyen = huyen;
		this.xa = xa;
		this.diaChiCuThe = diaChiCuThe;
		this.hoTen = hoTen;
		this.sdt = sdt;
		this.khachHang = khachHang;
	}

	public String getIdSoDiaChi() {
		return idSoDiaChi;
	}

	public void setIdSoDiaChi(String idSoDiaChi) {
		this.idSoDiaChi = idSoDiaChi;
	}

	public String getTinh() {
		return tinh;
	}

	public void setTinh(String tinh) {
		this.tinh = tinh;
	}

	public String getHuyen() {
		return huyen;
	}

	public void setHuyen(String huyen) {
		this.huyen = huyen;
	}

	public String getXa() {
		return xa;
	}

	public void setXa(String xa) {
		this.xa = xa;
	}

	public String getDiaChiCuThe() {
		return diaChiCuThe;
	}

	public void setDiaChiCuThe(String diaChiCuThe) {
		this.diaChiCuThe = diaChiCuThe;
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

	public KhachHang getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}

}
