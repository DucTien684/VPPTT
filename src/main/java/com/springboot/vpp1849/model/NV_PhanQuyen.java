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
@Table(name = "NV_PhanQuyen")
public class NV_PhanQuyen {
	@Id
	@Column(name = "IdPhanQuyen")
	@NotNull
	private String idPhanQuyen;

	@Column(name = "NhiemVu")
	@NotNull
	private String nhiemVu;

	@Column(name = "HanhDong")
	@NotNull
	private String hanhDong;

	@JsonIgnore
	@NotNull
	@ManyToOne
	@JoinColumn(name = "IdChucVu")
	private NV_ChucVu nv_ChucVu;

	public NV_PhanQuyen() {

	}

	public NV_PhanQuyen(@NotNull String idPhanQuyen, @NotNull String nhiemVu, @NotNull String hanhDong,
			@NotNull NV_ChucVu nv_ChucVu) {
		super();
		this.idPhanQuyen = idPhanQuyen;
		this.nhiemVu = nhiemVu;
		this.hanhDong = hanhDong;
		this.nv_ChucVu = nv_ChucVu;
	}

	public String getIdPhanQuyen() {
		return idPhanQuyen;
	}

	public void setIdPhanQuyen(String idPhanQuyen) {
		this.idPhanQuyen = idPhanQuyen;
	}

	public String getNhiemVu() {
		return nhiemVu;
	}

	public void setNhiemVu(String nhiemVu) {
		this.nhiemVu = nhiemVu;
	}

	public String getHanhDong() {
		return hanhDong;
	}

	public void setHanhDong(String hanhDong) {
		this.hanhDong = hanhDong;
	}

	public NV_ChucVu getNv_ChucVu() {
		return nv_ChucVu;
	}

	public void setNv_ChucVu(NV_ChucVu nv_ChucVu) {
		this.nv_ChucVu = nv_ChucVu;
	}

}
